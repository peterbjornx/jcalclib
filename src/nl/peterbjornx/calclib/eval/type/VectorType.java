package nl.peterbjornx.calclib.eval.type;

import nl.peterbjornx.calclib.ast.Operation;
import nl.peterbjornx.calclib.eval.ConstantValue;
import nl.peterbjornx.calclib.eval.Type;
import nl.peterbjornx.calclib.eval.Value;
import nl.peterbjornx.calclib.math.Vector;

import java.util.HashMap;

/**
 * Created by peterbjornx on 19/05/15.
 */
public class VectorType extends Type {

    private int dimension;
    private static HashMap<Integer, VectorType> typemap = new HashMap<Integer, VectorType>();

    public VectorType(int dimension) {
        this.dimension = dimension;
    }

    public static VectorType getType(int dimension) {
        VectorType t = typemap.get(dimension);
        if (t != null)
            return t;
        t = new VectorType(dimension);
        typemap.put(dimension, t);
        return t;
    }

    @Override
    public boolean equals(Object other){
        if (!getClass().isAssignableFrom(other.getClass()))
            return false;
        VectorType vt = (VectorType) other;
        return vt.dimension == dimension;
    }

    @Override
    public boolean doesPrecede(Type other) {
        if (other instanceof NumberType)
            return true;
        return false;
    }

    @Override
    public ConstantValue evaluate(ConstantValue lefthand, ConstantValue righthand, Operation operation) {
        Type lht = lefthand.getType();
        Type rht = righthand.getType();
        Vector lhv, rhv;
        double lhd, rhd;
        switch (operation) {
            case DOT:
                if (lht.equals(NumberType.TYPE)) {
                    lhd = (double) lefthand.getObject();
                    rhv = (Vector) righthand.getObject();
                    rhv.divide(lhd);
                    return righthand;
                } else if (rht.equals(NumberType.TYPE)) {
                    rhd = (double) righthand.getObject();
                    lhv = (Vector) lefthand.getObject();
                    lhv.divide(rhd);
                    return lefthand;
                } else if (lht.equals(rht)) {
                    lhv = (Vector) lefthand.getObject();
                    rhv = (Vector) righthand.getObject();
                    return new ConstantValue(NumberType.TYPE, lhv.dot(rhv));
                } else
                    throw new ArithmeticException("Type mismatch in vector dot product!");
            case CROSS:
                if (lht.equals(rht)) {
                    lhv = (Vector) lefthand.getObject();
                    rhv = (Vector) righthand.getObject();
                    lhv.cross(rhv);
                    return lefthand;
                } else
                    throw new ArithmeticException("Type mismatch in vector cross product!");
            case FRACTION:
                if (rht.equals(NumberType.TYPE)) {
                    rhd = (double) righthand.getObject();
                    lhv = (Vector) lefthand.getObject();
                    lhv.divide(rhd);
                    return lefthand;
                } else
                    throw new ArithmeticException("Division by vector is not defined!");
            case SUM:
                if (lht.equals(rht)) {
                    lhv = (Vector) lefthand.getObject();
                    rhv = (Vector) righthand.getObject();
                    lhv.add(rhv);
                    return lefthand;
                } else
                    throw new ArithmeticException("Type mismatch in vector addition!");
            case SUBTRACT:
                if (lht.equals(rht)) {
                    lhv = (Vector) lefthand.getObject();
                    rhv = (Vector) righthand.getObject();
                    lhv.subtract(rhv);
                    return lefthand;
                } else
                    throw new ArithmeticException("Type mismatch in vector subtraction!");
            default:
                throw new ArithmeticException("Invalid operation on vector type");
        }
    }

    @Override
    public ConstantValue evaluate(ConstantValue inner, Operation operation) {
        Vector v = (Vector) inner.getObject();
        switch (operation) {
            case NEGATE:
                v.dot(-1);
                return inner;
            case ABSOLUTE:
                return new ConstantValue(NumberType.TYPE, v.length());
            default:
                throw new ArithmeticException("Invalid operation on vector type");
        }
    }

    @Override
    public Value generate(Value lefthand, Value righthand, Operation operation) {
        return null;
    }

    @Override
    public Value generate(Value lefthand, Operation operation) {
        return null;
    }

    @Override
    public Object cloneObj(Object object) {
        Vector v= (Vector) object;
        return v.clone();
    }

    public int getDimensions() {
        return dimension;
    }
}

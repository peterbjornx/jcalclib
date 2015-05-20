package nl.peterbjornx.calclib.eval.type;

import nl.peterbjornx.calclib.ast.Operation;
import nl.peterbjornx.calclib.eval.ConstantValue;
import nl.peterbjornx.calclib.eval.Type;
import nl.peterbjornx.calclib.eval.Value;
import nl.peterbjornx.calclib.math.Vector;

/**
 * Created by peterbjornx on 19/05/15.
 */
public class NumberType extends Type{

    public static Type TYPE = new NumberType();

    @Override
    public boolean equals(Object other){
        return getClass().isAssignableFrom(other.getClass());
    }

    @Override
    public boolean doesPrecede(Type other) {
        return false;
    }

    @Override
    public ConstantValue evaluate(ConstantValue lefthand, ConstantValue righthand, Operation operation) {
        double l,r;
        if (!lefthand.getType().equals(righthand.getType()))
            throw new ArithmeticException("Type mismatch in number operation");
        l = (double) lefthand.getObject();
        r = (double) righthand.getObject();
        switch (operation) {
            case DOT:
                return new ConstantValue(lefthand.getType(), l * r);
            case FRACTION:
                return new ConstantValue(lefthand.getType(), l / r);
            case SUM:
                return new ConstantValue(lefthand.getType(), l + r);
            case SUBTRACT:
                return new ConstantValue(lefthand.getType(), l - r);
            case EXPONENTIATE:
                return new ConstantValue(lefthand.getType(), Math.pow(l,r));
            default:
                throw new ArithmeticException("Invalid operation on number");
        }
    }

    @Override
    public ConstantValue evaluate(ConstantValue lefthand, Operation operation) {

        double l = (double) lefthand.getObject();
        switch (operation) {
            case NEGATE:
                return new ConstantValue(lefthand.getType(), -l);
            case ABSOLUTE:
                return new ConstantValue(lefthand.getType(), Math.abs(l));
            default:
                throw new ArithmeticException("Invalid operation on number");
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
        return object;
    }
}

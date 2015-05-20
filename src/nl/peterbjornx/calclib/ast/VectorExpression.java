package nl.peterbjornx.calclib.ast;

import nl.peterbjornx.calclib.eval.ConstantValue;
import nl.peterbjornx.calclib.eval.Value;
import nl.peterbjornx.calclib.eval.type.NumberType;
import nl.peterbjornx.calclib.eval.type.VectorType;
import nl.peterbjornx.calclib.math.Vector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by peterbjornx on 19/05/15.
 */
public class VectorExpression extends Expression {

    private List<Expression> components;

    public VectorExpression(List<Expression> components) {
        this.components = components;
    }

    @Override
    public ConstantValue evaluate() {
        double[] pars = new double[components.size()];
        int i = 0;
        for (Expression val : components) {
            ConstantValue v = val.evaluate();
            if (!v.getType().equals(NumberType.TYPE))
                throw new ArithmeticException("Invalid type for vector component");
            pars[i++] = (double) v.getObject();
        }
        return new ConstantValue(VectorType.getType(pars.length), new Vector(pars) );
    }

    @Override
    public Value generate() {
        return null;
    }
}

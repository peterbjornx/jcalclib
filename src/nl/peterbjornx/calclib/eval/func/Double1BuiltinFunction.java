package nl.peterbjornx.calclib.eval.func;

import nl.peterbjornx.calclib.eval.ConstantValue;
import nl.peterbjornx.calclib.eval.Function;
import nl.peterbjornx.calclib.eval.Value;
import nl.peterbjornx.calclib.eval.type.NumberType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by peterbjornx on 20/05/15.
 */
public abstract class Double1BuiltinFunction extends Function {
    @Override
    public ConstantValue evaluate(ArrayList<ConstantValue> parameters) {
        if (parameters.size() != 1)
            throw new ArithmeticException("Invalid parameter count!");
        ConstantValue par = parameters.get(0);
        if (par.getType() != NumberType.TYPE)
            throw new ArithmeticException("Invalid parameter type!");
        return new ConstantValue(NumberType.TYPE, calc((Double) parameters.get(0).getObject()));
    }

    public abstract double calc(double par);

    @Override
    public Value generate(List<Value> parameters) {
        return null;
    }
}

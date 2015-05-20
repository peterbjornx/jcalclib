package nl.peterbjornx.calclib.ast;

import nl.peterbjornx.calclib.eval.ConstantValue;
import nl.peterbjornx.calclib.eval.Value;
import nl.peterbjornx.calclib.eval.type.NumberType;

/**
 * Created by peterbjornx on 19/05/15.
 */
public class ConstantExpression extends Expression {

    private final ConstantValue value;

    public ConstantExpression(double value) {
        this.value = new ConstantValue(NumberType.TYPE, value);
    }

    @Override
    public ConstantValue evaluate() {
        return value;
    }

    @Override
    public Value generate() {
        return value;
    }
}

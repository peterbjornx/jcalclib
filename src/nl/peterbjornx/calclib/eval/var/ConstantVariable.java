package nl.peterbjornx.calclib.eval.var;

import nl.peterbjornx.calclib.eval.ConstantValue;
import nl.peterbjornx.calclib.eval.Type;
import nl.peterbjornx.calclib.eval.Value;
import nl.peterbjornx.calclib.eval.Variable;

/**
 * Created by peterbjornx on 20/05/15.
 */
public class ConstantVariable extends Variable {
    private ConstantValue value;
    private Type type;
    private String name;

    public ConstantVariable(Type type, Object value) {
        this.type = type;
        this.value = new ConstantValue(type, value);
    }

    @Override
    public ConstantValue getValue() {
        return new ConstantValue(value.getType(),value.getType().cloneObj(value.getObject()));
    }

    @Override
    public Value generateAccess() {
        return null;
    }

    @Override
    public Type getType() {
        return type;
    }

    public void setValue(ConstantValue value) {
        if (!type.equals(value.getType()))
            throw new ArithmeticException("Invalid type assignment");
        this.value = value;
    }

    public String getName() {
        return name;
    }
}

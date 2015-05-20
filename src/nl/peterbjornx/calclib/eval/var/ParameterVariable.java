package nl.peterbjornx.calclib.eval.var;

import nl.peterbjornx.calclib.eval.ConstantValue;
import nl.peterbjornx.calclib.eval.Type;
import nl.peterbjornx.calclib.eval.Value;
import nl.peterbjornx.calclib.eval.Variable;

/**
 * Created by peterbjornx on 20/05/15.
 */
public class ParameterVariable extends Variable {
    private ConstantValue value;
    private Type type;
    private String name;

    public ParameterVariable(Type type, String name) {
        this.type = type;
        this.name = name;
    }

    @Override
    public ConstantValue getValue() {
        return new ConstantValue(value.getType(),value.getType().cloneObj(value.getObject()));
    }

    @Override
    public Value generateAccess() {
        return null;
    }

    public void setValue(ConstantValue value) {
        if (!type.equals(value.getType()))
            throw new ArithmeticException("Invalid type assignment");
        this.value = value;
    }

    public String getName() {
        return name;
    }

    @Override
    public Type getType() {
        return type;
    }
}

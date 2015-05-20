package nl.peterbjornx.calclib.eval;

import nl.peterbjornx.calclib.ast.Operation;

/**
 * Created by peterbjornx on 19/05/15.
 */
public abstract class Type {

    public abstract boolean doesPrecede(Type other);
    public abstract ConstantValue evaluate(ConstantValue lefthand, ConstantValue righthand, Operation operation);
    public abstract ConstantValue evaluate(ConstantValue lefthand, Operation operation);
    public abstract Value generate(Value lefthand, Value righthand, Operation operation);
    public abstract Value generate(Value lefthand, Operation operation);

    public abstract Object cloneObj(Object object);
}

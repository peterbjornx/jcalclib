package nl.peterbjornx.calclib.eval;

/**
 * Created by peterbjornx on 19/05/15.
 */
public abstract class Variable {

    public abstract Value getValue();
    public abstract Value generateAccess();

    public abstract Type getType();
}

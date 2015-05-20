package nl.peterbjornx.calclib.eval;

/**
 * Created by peterbjornx on 19/05/15.
 */
public class ConstantValue extends Value {

    private Object object;

    public ConstantValue(Type type, Object object) {
        super(type);
        this.object = object;
    }

    public String toString() {
        return object.toString();
    }

    public Object getObject() {
        return object;
    }
}

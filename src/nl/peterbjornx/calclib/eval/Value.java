package nl.peterbjornx.calclib.eval;

/**
 * Created by peterbjornx on 19/05/15.
 */
public class Value {

    private final Type type;

    public Value(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

}

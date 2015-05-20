package nl.peterbjornx.calclib.eval;

import nl.peterbjornx.calclib.code.CodeFragment;

/**
 * Created by peterbjornx on 19/05/15.
 */
public class CodeValue extends Value {

    private CodeFragment fragment;

    public CodeValue(Type type, CodeFragment fragment) {
        super(type);
        this.fragment = fragment;
    }


    public CodeFragment getFragment() {
        return fragment;
    }
}

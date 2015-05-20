package nl.peterbjornx.calclib.ast;

import nl.peterbjornx.calclib.eval.ConstantValue;
import nl.peterbjornx.calclib.eval.Value;

/**
 * Created by peterbjornx on 19/05/15.
 */
public abstract class Expression {

    public abstract ConstantValue evaluate();

    public abstract Value generate();

}

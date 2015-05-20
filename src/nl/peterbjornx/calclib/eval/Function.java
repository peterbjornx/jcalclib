package nl.peterbjornx.calclib.eval;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by peterbjornx on 19/05/15.
 */
public abstract class Function {
    public abstract ConstantValue evaluate(ArrayList<ConstantValue> parameters);

    public abstract Value generate(List<Value> parameters);
}

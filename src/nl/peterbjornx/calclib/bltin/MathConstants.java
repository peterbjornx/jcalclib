package nl.peterbjornx.calclib.bltin;

import nl.peterbjornx.calclib.eval.type.NumberType;
import nl.peterbjornx.calclib.eval.var.ConstantVariable;

/**
 * Created by peterbjornx on 20/05/15.
 */
public class MathConstants {
    public final ConstantVariable EULER       = new ConstantVariable(NumberType.TYPE, Math.E);
    public final ConstantVariable PI          = new ConstantVariable(NumberType.TYPE, Math.PI);
}

package nl.peterbjornx.calclib.ast;

import nl.peterbjornx.calclib.eval.ConstantValue;
import nl.peterbjornx.calclib.eval.Namespace;
import nl.peterbjornx.calclib.eval.Type;
import nl.peterbjornx.calclib.eval.type.NumberType;
import nl.peterbjornx.calclib.eval.type.VectorType;
import nl.peterbjornx.calclib.math.Vector;
import nl.peterbjornx.calclib.parse.ParserWrap;

/**
 * Created by peterbjornx on 20/05/15.
 */
public class DivergenceExpression extends VectorPartialExpression {

    private int dimensions;

    public DivergenceExpression(Namespace ns, ParserWrap prs, String expr, String diffvar) {
        super(ns, prs, expr, diffvar);
        Type t = parentDiffVariable.getType();
        if (!(t instanceof VectorType))
            throw new ArithmeticException("Divergence is only defined for vectors");
        VectorType vt = (VectorType) t;
        dimensions = vt.getDimensions();
    }

    @Override
    public ConstantValue evaluate() {
        double div = 0;
        int idx;
        for (idx = 0; idx < dimensions; idx++)
            div += evaluatePartialDifferential(idx, idx);
        return new ConstantValue(NumberType.TYPE, div);
    }
}

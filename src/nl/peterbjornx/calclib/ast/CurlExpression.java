package nl.peterbjornx.calclib.ast;

import nl.peterbjornx.calclib.eval.ConstantValue;
import nl.peterbjornx.calclib.eval.Namespace;
import nl.peterbjornx.calclib.eval.Type;
import nl.peterbjornx.calclib.eval.type.VectorType;
import nl.peterbjornx.calclib.math.Vector;
import nl.peterbjornx.calclib.parse.ParserWrap;

/**
 * Created by peterbjornx on 20/05/15.
 */
public class CurlExpression extends VectorPartialExpression {

    public CurlExpression(Namespace ns, ParserWrap prs, String expr, String diffvar) {
        super(ns, prs, expr, diffvar);
        Type t = parentDiffVariable.getType();
        if (!t.equals(VectorType.getType(3)))
            throw new ArithmeticException("Curl is only defined for vector3");
    }

    @Override
    public ConstantValue evaluate() {
        double dzdy = evaluatePartialDifferential(1,2);
        double dydz = evaluatePartialDifferential(2,1);
        double dxdz = evaluatePartialDifferential(2,0);
        double dzdx = evaluatePartialDifferential(0,2);
        double dydx = evaluatePartialDifferential(0,1);
        double dxdy = evaluatePartialDifferential(1,0);
        double x = dzdy - dydz;
        double y = dxdz - dzdx;
        double z = dydx - dxdy;
        return new ConstantValue(parentDiffVariable.getType(), new Vector(new double[]{x,y,z}));
    }
}

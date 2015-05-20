package nl.peterbjornx.calclib.ast;

import nl.peterbjornx.calclib.eval.*;
import nl.peterbjornx.calclib.eval.type.NumberType;
import nl.peterbjornx.calclib.eval.type.VectorType;
import nl.peterbjornx.calclib.eval.var.ParameterVariable;
import nl.peterbjornx.calclib.math.Vector;
import nl.peterbjornx.calclib.parse.ParserWrap;

/**
 * Created by peterbjornx on 20/05/15.
 */
public class GradientExpression extends Expression {
    private final ParserWrap parser;
    private final ParameterVariable differentiationVariable;
    private final Variable parentDiffVariable;
    private final int dimension;
    private Expression expression;
    private Namespace namespace;

    public GradientExpression(Namespace ns, ParserWrap prs, String expr, String diffvar) {
        this.namespace = new FunctionNamespace(ns);
        this.parser = prs;
        parentDiffVariable = ns.getVariable(diffvar);
        Type p = parentDiffVariable.getType();
        differentiationVariable = new ParameterVariable(p, diffvar);
        namespace.addVariable(diffvar, differentiationVariable);
        setExpression(expr);
        if (!(p instanceof VectorType))
            throw new ArithmeticException("Gradient operation only defined over number(vector) functions");
        VectorType t = (VectorType) p;
        dimension = t.getDimensions();
    }

    public ConstantValue evaluate() {//TODO: Add error checking
        double val[] = new double[dimension];
        int idx;
        for (idx = 0; idx < dimension; idx++)
             val[idx] = evaluatePartialDifferential(idx);
        return new ConstantValue(VectorType.getType(dimension),  new Vector(val));

    }


    public double evaluatePartialDifferential(int index) {//TODO: Add error checking
        ConstantValue parentDiffVal = (ConstantValue) parentDiffVariable.getValue();
        Vector invec = (Vector) parentDiffVal.getObject();
        double x = invec.element(index);
        double h = Math.sqrt(2.22e-16) * Math.max(Math.abs(x), 1.0);
        double r = x + h;
        double l = x - h;
        double dx = r - l;
        Vector lvec = invec.clone();
        lvec.setElement(index, l);
        ConstantValue v = new ConstantValue(parentDiffVal.getType(), lvec);
        differentiationVariable.setValue(v);

        double ly;
        double ry;
        Vector zvec;
        ConstantValue lv;

        lv = expression.evaluate();
        if (lv.getType().equals(NumberType.TYPE)) {
            ly = (double) lv.getObject();
        } else
            throw new ArithmeticException("Invalid expression type in gradient");

        lvec.setElement(index, r);
        lv = expression.evaluate();
        if (lv.getType().equals(NumberType.TYPE)) {
            ry = (double) lv.getObject();
        } else
            throw new ArithmeticException("Invalid expression type in gradient");

        double dy = ry - ly;

        return dy / dx;
    }

    public void setExpression(String expression) {
        Namespace oldns = parser.getNamespace();
        parser.setNamespace(namespace);
        this.expression = parser.parseExpression(expression);
        parser.setNamespace(oldns);

    }

    @Override
    public Value generate() {
        return null;
    }
}

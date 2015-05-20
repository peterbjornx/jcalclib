package nl.peterbjornx.calclib.ast;

import javafx.scene.shape.ArcTo;
import nl.peterbjornx.calclib.eval.*;
import nl.peterbjornx.calclib.eval.type.NumberType;
import nl.peterbjornx.calclib.eval.type.VectorType;
import nl.peterbjornx.calclib.eval.var.ParameterVariable;
import nl.peterbjornx.calclib.math.Vector;
import nl.peterbjornx.calclib.parse.ParserWrap;

/**
 * Created by peterbjornx on 20/05/15.
 */
public abstract class VectorPartialExpression extends Expression {
    private final ParserWrap parser;
    private final ParameterVariable differentiationVariable;
    protected final Variable parentDiffVariable;
    private Expression expression;
    private Namespace namespace;

    public VectorPartialExpression(Namespace ns, ParserWrap prs, String expr, String diffvar) {
        this.namespace = new FunctionNamespace(ns);
        this.parser = prs;
        parentDiffVariable = ns.getVariable(diffvar);
        differentiationVariable = new ParameterVariable(parentDiffVariable.getType(), diffvar);
        namespace.addVariable(diffvar, differentiationVariable);
        setExpression(expr);
    }


    public double evaluatePartialDifferential(int varindex, int resindex) {//TODO: Add error checking
        ConstantValue parentDiffVal = (ConstantValue) parentDiffVariable.getValue();
        Vector invec = (Vector) parentDiffVal.getObject();
        double x = invec.element(varindex);
        double h = Math.sqrt(2.22e-16) * Math.max(Math.abs(x), 1.0);
        double r = x + h;
        double l = x - h;
        double dx = r - l;
        Vector lvec = invec.clone();
        lvec.setElement(varindex, l);
        ConstantValue v = new ConstantValue(parentDiffVal.getType(), lvec);
        differentiationVariable.setValue(v);

        double ly;
        double ry;
        Vector zvec;
        ConstantValue lv;

        lv = expression.evaluate();

        if (lv.getType().equals(parentDiffVal.getType())) {
            zvec = (Vector) lv.getObject();
            ly = zvec.element(resindex);
        } else
            throw new ArithmeticException("Invalid expression type in partial differential based expression");

        lvec.setElement(varindex, r);
        lv = expression.evaluate();
        if (lv.getType().equals(parentDiffVal.getType())) {
            zvec = (Vector) lv.getObject();
            ry = zvec.element(resindex);
        } else
            throw new ArithmeticException("Invalid expression type in partial differential based expression");

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

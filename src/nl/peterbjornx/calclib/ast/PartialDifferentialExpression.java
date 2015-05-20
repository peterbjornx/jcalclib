package nl.peterbjornx.calclib.ast;

import nl.peterbjornx.calclib.eval.*;
import nl.peterbjornx.calclib.eval.type.NumberType;
import nl.peterbjornx.calclib.eval.var.ParameterVariable;
import nl.peterbjornx.calclib.math.Vector;
import nl.peterbjornx.calclib.parse.ParserWrap;

/**
 * Created by peterbjornx on 20/05/15.
 */
public class PartialDifferentialExpression extends Expression {
    private final ParserWrap parser;
    private final ParameterVariable differentiationVariable;
    private final Namespace parentNamespace;
    private final Variable parentDiffVariable;
    private final int index;
    private Expression expression;
    private Namespace namespace;

    public PartialDifferentialExpression(Namespace ns, ParserWrap prs, String expr, String diffvar, int index) {
        this.namespace = new FunctionNamespace(ns);
        this.parentNamespace = ns;
        this.parser = prs;
        parentDiffVariable = ns.getVariable(diffvar);
        differentiationVariable = new ParameterVariable(parentDiffVariable.getType(), diffvar);
        namespace.addVariable(diffvar, differentiationVariable);
        this.index = index;
        setExpression(expr);
    }

    @Override
    public ConstantValue evaluate() {//TODO: Add error checking
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
        } else if (lv.getType().equals(parentDiffVal.getType())) {
            zvec = (Vector) lv.getObject();
            ly = zvec.element(index);
        } else
            throw new ArithmeticException("Invalid expression type in partial differential");

        lvec.setElement(index, r);
        lv = expression.evaluate();
        if (lv.getType().equals(NumberType.TYPE)) {
            ry = (double) lv.getObject();
        } else if (lv.getType().equals(parentDiffVal.getType())) {
            zvec = (Vector) lv.getObject();
            ry = zvec.element(index);
        } else
            throw new ArithmeticException("Invalid expression type in partial differential");

        double dy = ry - ly;
        double rs = dy / dx;
        return new ConstantValue(NumberType.TYPE, rs);
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

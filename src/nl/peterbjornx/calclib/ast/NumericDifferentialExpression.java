package nl.peterbjornx.calclib.ast;

import nl.peterbjornx.calclib.ast.Expression;
import nl.peterbjornx.calclib.eval.*;
import nl.peterbjornx.calclib.eval.type.NumberType;
import nl.peterbjornx.calclib.eval.var.ConstantVariable;
import nl.peterbjornx.calclib.eval.var.ParameterVariable;
import nl.peterbjornx.calclib.parse.ParserWrap;

import javax.lang.model.element.Name;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by peterbjornx on 20/05/15.
 */
public class NumericDifferentialExpression extends Expression {
    private final ParserWrap parser;
    private final ParameterVariable differentiationVariable;
    private final Namespace parentNamespace;
    private final Variable parentDiffVariable;
    private Expression expression;
    private Namespace namespace;

    public NumericDifferentialExpression(Namespace ns, ParserWrap prs, String expr, String diffvar) {
        this.namespace = new FunctionNamespace(ns);
        this.parentNamespace = ns;
        this.parser = prs;
        differentiationVariable = new ParameterVariable(NumberType.TYPE, diffvar);
        parentDiffVariable = ns.getVariable(diffvar);
        namespace.addVariable(diffvar, differentiationVariable);
        setExpression(expr);
    }

    @Override
    public ConstantValue evaluate() {
        ConstantValue parentDiffVal = (ConstantValue) parentDiffVariable.getValue();
        double x = (double) parentDiffVal.getObject();
        double h = Math.sqrt(2.22e-16) * Math.max(Math.abs(x), 1.0);
        double r = x + h;
        double l = x - h;
        double dx = r - l;
        ConstantValue v = new ConstantValue(NumberType.TYPE, l);
        differentiationVariable.setValue(v);
        ConstantValue lv = expression.evaluate();
        v = new ConstantValue(NumberType.TYPE, r);
        differentiationVariable.setValue(v);
        ConstantValue rv = expression.evaluate();
        double ly = (double) lv.getObject();
        double ry = (double) rv.getObject();
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

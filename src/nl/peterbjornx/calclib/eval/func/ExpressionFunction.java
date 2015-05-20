package nl.peterbjornx.calclib.eval.func;

import nl.peterbjornx.calclib.ast.Expression;
import nl.peterbjornx.calclib.eval.*;
import nl.peterbjornx.calclib.eval.var.ParameterVariable;
import nl.peterbjornx.calclib.parse.ParserWrap;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by peterbjornx on 20/05/15.
 */
public class ExpressionFunction extends Function {
    private final ParserWrap parser;
    private Expression expression;
    private Namespace namespace;
    private List<ParameterVariable> parVariables;

    public ExpressionFunction(ParserWrap prs, String expr, List<ParameterVariable> parVariables) {
        this.namespace = new FunctionNamespace(prs.getNamespace());
        for (ParameterVariable p : parVariables) {
            this.namespace.addVariable(p.getName(),p);
        }
        this.parser = prs;
        this.parVariables = parVariables;
        setExpression(expr);
    }

    @Override
    public ConstantValue evaluate(ArrayList<ConstantValue> parameters) {
        if (parameters.size() != parVariables.size())
            throw new ArithmeticException("Invalid function parameter count");
        for (int i = 0; i < parameters.size(); i++) {
            parVariables.get(i).setValue(parameters.get(i));
        }
        return expression.evaluate();
    }

    @Override
    public Value generate(List<Value> parameters) {
        return null;
    }

    public void setExpression(String expression) {
        Namespace oldns = parser.getNamespace();
        parser.setNamespace(namespace);
        this.expression = parser.parseExpression(expression);
        parser.setNamespace(oldns);

    }
}

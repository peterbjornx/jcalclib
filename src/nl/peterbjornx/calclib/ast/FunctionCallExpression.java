package nl.peterbjornx.calclib.ast;

import nl.peterbjornx.calclib.eval.ConstantValue;
import nl.peterbjornx.calclib.eval.Function;
import nl.peterbjornx.calclib.eval.Namespace;
import nl.peterbjornx.calclib.eval.Value;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by peterbjornx on 19/05/15.
 */
public class FunctionCallExpression extends Expression {

    private Function function;
    private List<Expression> parameters;

    public FunctionCallExpression(Namespace namespace, String name, List<Expression> parameters) {
        this.function = namespace.getFunction(name);
        this.parameters = parameters;
    }

    @Override
    public ConstantValue evaluate() {
        ArrayList<ConstantValue> parval = new ArrayList<ConstantValue>(parameters.size());
        for (Expression val : parameters)
            parval.add(val.evaluate());
        return function.evaluate(parval);
    }

    @Override
    public Value generate() {
        List<Value> parval = new ArrayList<Value>(parameters.size());
        for (Expression val : parameters)
            parval.add(val.generate());
        return function.generate(parval);
    }
}

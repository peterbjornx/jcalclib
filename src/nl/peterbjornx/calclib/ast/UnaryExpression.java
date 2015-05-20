package nl.peterbjornx.calclib.ast;

import nl.peterbjornx.calclib.eval.ConstantValue;
import nl.peterbjornx.calclib.eval.Type;
import nl.peterbjornx.calclib.eval.Value;

/**
 * Created by peterbjornx on 19/05/15.
 */
public class UnaryExpression extends Expression {

    private final Expression inner;
    private final Operation operation;

    public UnaryExpression(Expression inner, Operation operation) {
        this.inner = inner;
        this.operation = operation;
    }

    @Override
    public ConstantValue evaluate() {
        ConstantValue lhv = inner.evaluate();
        Type lht = lhv.getType();
        return lht.evaluate(lhv, operation);
    }

    @Override
    public Value generate() {

        Value lhv = inner.evaluate();
        Type lht = lhv.getType();
        return lht.generate(lhv, operation);
    }

}

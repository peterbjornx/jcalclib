package nl.peterbjornx.calclib.ast;

import nl.peterbjornx.calclib.eval.ConstantValue;
import nl.peterbjornx.calclib.eval.Type;
import nl.peterbjornx.calclib.eval.Value;

/**
 * Created by peterbjornx on 19/05/15.
 */
public class InfixExpression extends Expression {

    private final Expression lefthand;
    private final Expression righthand;
    private final Operation operation;

    public InfixExpression(Expression lefthand, Expression righthand, Operation operation) {
        this.lefthand = lefthand;
        this.righthand = righthand;
        this.operation = operation;
    }

    @Override
    public ConstantValue evaluate() {
        ConstantValue lhv = lefthand.evaluate();
        ConstantValue rhv = righthand.evaluate();
        Type lht = lhv.getType();
        return lht.evaluate(lhv, rhv, operation);
    }

    @Override
    public Value generate() {

        Value lhv = lefthand.evaluate();
        Value rhv = righthand.evaluate();
        Type lht = lhv.getType();
        return lht.generate(lhv, rhv, operation);
    }

}

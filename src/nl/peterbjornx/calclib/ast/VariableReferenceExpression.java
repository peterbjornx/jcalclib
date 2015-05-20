package nl.peterbjornx.calclib.ast;

import nl.peterbjornx.calclib.eval.ConstantValue;
import nl.peterbjornx.calclib.eval.Namespace;
import nl.peterbjornx.calclib.eval.Value;
import nl.peterbjornx.calclib.eval.Variable;

/**
 * Created by peterbjornx on 19/05/15.
 */
public class VariableReferenceExpression extends Expression {

    private final Variable variable;

    public VariableReferenceExpression(Namespace ns, String variableName) {
        this.variable = ns.getVariable(variableName);
    }

    @Override
    public ConstantValue evaluate() {
        return (ConstantValue) variable.getValue();
    }

    @Override
    public Value generate() {
        return null;
    }
}

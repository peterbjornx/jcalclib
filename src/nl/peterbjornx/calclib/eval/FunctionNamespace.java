package nl.peterbjornx.calclib.eval;

import javax.lang.model.element.Name;
import java.util.HashMap;

/**
 * Created by peterbjornx on 20/05/15.
 */
public class FunctionNamespace extends Namespace {
    private HashMap<String, Variable> localVariables = new HashMap<>();
    private Namespace parent;

    public void addVariable(String name, Variable var) {
        localVariables.put(name,var);
    }

    @Override
    public Variable getVariable(String name) {
        Variable var = localVariables.get(name);
        if (var != null)
            return var;
        return parent.getVariable(name);
    }

    @Override
    public Function getFunction(String name) {
        return parent.getFunction(name);
    }

    public FunctionNamespace(Namespace parent) {
        this.parent = parent;
    }

    @Override
    public void addFunction(String name, Function function) {
        if (parent != null)
            parent.addFunction(name, function);
    }
}

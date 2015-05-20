package nl.peterbjornx.calclib.eval;

import nl.peterbjornx.calclib.eval.func.Double1BuiltinFunction;

import javax.lang.model.element.Name;
import java.util.HashMap;

/**
 * Created by peterbjornx on 19/05/15.
 */
public class Namespace {

    private HashMap<String, Variable> variables = new HashMap<>();
    private HashMap<String, Function> functions = new HashMap<>();

    public Namespace() {
    }

    public void addVariable(String name, Variable variable) {
        variables.put(name, variable);
    }

    public void addFunction(String name, Function function) {
        functions.put(name, function);
    }

    public Variable getVariable(String name) {
        Variable var = variables.get(name);
        if (var == null)
            throw new ArithmeticException("Unknown variable in expression!");
        return var;
    }

    public Function getFunction(String name) {
        Function func = functions.get(name);
        if (func == null)
            throw new ArithmeticException("Unknown function in expression!");
        return func;
    }
}

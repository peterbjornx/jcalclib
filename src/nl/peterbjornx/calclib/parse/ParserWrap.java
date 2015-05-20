package nl.peterbjornx.calclib.parse;

import nl.peterbjornx.calclib.ast.Expression;
import nl.peterbjornx.calclib.eval.ConstantValue;
import nl.peterbjornx.calclib.eval.Function;
import nl.peterbjornx.calclib.eval.Namespace;
import nl.peterbjornx.calclib.eval.Type;
import nl.peterbjornx.calclib.eval.func.ExpressionFunction;
import nl.peterbjornx.calclib.eval.type.NumberType;
import nl.peterbjornx.calclib.eval.type.VectorType;
import nl.peterbjornx.calclib.eval.var.ParameterVariable;
import nl.peterbjornx.calclib.math.Vector;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by peterbjornx on 20/05/15.
 */
public class ParserWrap {
    private MathParser parser = new MathParser(new StringReader("0"));
    private Namespace namespace = new Namespace();

    public ParserWrap(){
        parser.setNamespace(namespace);
    }

    public Expression parseExpression(String expr) {
        parser.ReInit(new StringReader(expr));
        try {
            return parser.expr();
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public double evaluateFunction(String name, double var) {
        Function f = namespace.getFunction(name);
        ArrayList<ConstantValue> parval = new ArrayList<ConstantValue>(1);
        parval.add(new ConstantValue(NumberType.TYPE, var));
        ConstantValue v = f.evaluate(parval);
        if (!NumberType.TYPE.equals(v.getType()))
            throw new ArithmeticException("Can't evaluate top-level function with mismatched result type");
        return (double) v.getObject();
    }

    public Vector evaluateVectorField(String name, Vector var) {
        Function f = namespace.getFunction(name);
        ArrayList<ConstantValue> parval = new ArrayList<ConstantValue>(1);
        Type par = VectorType.getType(var.dimension());
        parval.add(new ConstantValue(par, var));
        ConstantValue v = f.evaluate(parval);
        if (!par.equals(v.getType()))
            throw new ArithmeticException("Can't evaluate top-level function with mismatched result type");
        return (Vector) v.getObject();
    }

    public double evaluateScalarField(String name, Vector var) {
        Function f = namespace.getFunction(name);
        ArrayList<ConstantValue> parval = new ArrayList<ConstantValue>(1);
        Type par = NumberType.TYPE;
        parval.add(new ConstantValue(par, var));
        ConstantValue v = f.evaluate(parval);
        if (!par.equals(v.getType()))
            throw new ArithmeticException("Can't evaluate top-level function with mismatched result type");
        return (double) v.getObject();
    }

    public static void main(String args[]) {
        ParserWrap wrap = new ParserWrap();
        List<ParameterVariable> pv = new ArrayList<ParameterVariable>();
        pv.add(new ParameterVariable(VectorType.getType(3),"r"));
        List<ParameterVariable> pv2 = new ArrayList<ParameterVariable>();
        pv2.add(new ParameterVariable(VectorType.getType(3),"r"));
        wrap.namespace.addFunction("test", new ExpressionFunction(wrap, "∇r/{1/|r|^2}", pv));
        wrap.namespace.addFunction("tes", new ExpressionFunction(wrap, "∇∙r/{test(r)}", pv2));
        System.out.println(wrap.parseExpression("tes([22;30;20])").evaluate());
    }

    public Namespace getNamespace() {
        return namespace;
    }

    public void setNamespace(Namespace namespace) {
        this.namespace = namespace;
        parser.setNamespace(namespace);
    }
}

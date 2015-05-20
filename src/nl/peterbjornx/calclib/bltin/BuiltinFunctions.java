package nl.peterbjornx.calclib.bltin;

import nl.peterbjornx.calclib.eval.Function;
import nl.peterbjornx.calclib.eval.Namespace;
import nl.peterbjornx.calclib.eval.func.Double1BuiltinFunction;

/**
 * Created by peterbjornx on 20/05/15.
 */
public class BuiltinFunctions {

    public final static Function SIN = new Double1BuiltinFunction() {
        @Override
        public double calc(double par) {
            return Math.sin(par);
        }
    };

    public final static Function COS = new Double1BuiltinFunction() {
        @Override
        public double calc(double par) {
            return Math.cos(par);
        }
    };

    public final static Function TAN = new Double1BuiltinFunction() {
        @Override
        public double calc(double par) {
            return Math.tan(par);
        }
    };

    public final static Function LOG = new Double1BuiltinFunction() {
        @Override
        public double calc(double par) {
            return Math.log10(par);
        }
    };

    public final static Function LN = new Double1BuiltinFunction() {
        @Override
        public double calc(double par) {
            return Math.log(par);
        }
    };

    public final static Function SQRT = new Double1BuiltinFunction() {
        @Override
        public double calc(double par) {
            return Math.sqrt(par);
        }
    };

}

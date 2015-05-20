package nl.peterbjornx.calclib.math;

import java.util.Arrays;

/**
 * Created by peterbjornx on 19/05/15.
 */
public class Vector {

    public double[] values;

    public Vector (double[] vals) {
        values = Arrays.copyOf(vals, vals.length);
    }

    public boolean typeCheck(Vector other) {
        return other.values.length == values.length;
    }

    public String toTypeString() {
        return "vector"+values.length;
    }

    public String toString() {
        String result = "[";
        for (double v : values)
            result += v + ";";
        return result.substring(0, result.length() - 1) + "]";
    }

    public double length() {
        return Math.sqrt(dot(this));
    }

    public double dot(Vector other) {
        double res = 0;
        if (!typeCheck(other))
            throw new ArithmeticException("Mismatched types in "+toTypeString()+".dot("+other.toTypeString()+")");
        for (int idx = 0; idx < values.length; idx++)
            res += other.values[idx] * values[idx];
        return res;
    }

    public Vector cross(Vector other) {
        if (values.length != 3)
            throw new ArithmeticException("Cross product is only defined for vector3");
        if (!typeCheck(other))
            throw new ArithmeticException("Mismatched types in "+toTypeString()+".cross("+other.toTypeString()+")");
        double _x,_y,_z;
        _x = values[1] * other.values[2] - values[2] * other.values[1];
        _y = values[2] * other.values[0] - values[0] * other.values[2];
        _z = values[0] * other.values[1] - values[1] * other.values[0];
        values[0] = _x;
        values[1] = _y;
        values[2] = _z;
        return this;
    }

    public Vector dot(double scale) {
        for (int ptr = 0; ptr < values.length; ptr++)
            values[ptr] *= scale;
        return this;
    }

    public Vector divide(double scale) {
        for (int ptr = 0; ptr < values.length; ptr++)
            values[ptr] /= scale;
        return this;
    }

    public Vector add(Vector other) {
        for (int ptr = 0; ptr < values.length; ptr++)
            values[ptr] += other.values[ptr];
        return this;
    }

    public Vector subtract(Vector other) {
        for (int ptr = 0; ptr < values.length; ptr++)
            values[ptr] -= other.values[ptr];
        return this;
    }

    public Vector clone() {
        return new Vector(values);
    }

    public double element(int index) {
        return values[index];
    }

    public void setElement(int index, double val) {
        values[index] = val;
    }

    public int dimension() {
        return values.length;
    }
}

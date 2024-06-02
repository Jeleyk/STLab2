package ru.jeleyka.testing.lab2;

public class Sin extends Function {
    private static final double PRECISION = 1e-7;

    @Override
    public double apply(double x) {
        if (!Double.isFinite(x)) {
            throw new IllegalArgumentException(String.format("Function value for argument %f doesn't exist.", x));
        }
        double result = x, term = x;
        int n = 1;
        while (Math.abs(term) > PRECISION) {
            term *= -x * x / ((2 * n) * (2 * n + 1));
            result += term;
            n++;
        }
        return Math.abs(result) < PRECISION ? 0 : result;
    }
}
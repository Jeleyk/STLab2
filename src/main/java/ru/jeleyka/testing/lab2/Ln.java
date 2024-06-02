package ru.jeleyka.testing.lab2;

public class Ln extends Function {
    private static final int ITERATIONS = 100_000;

    @Override
    public double apply(double x) {
        if (x <= 0 || !Double.isFinite(x)) {
            throw new IllegalArgumentException(String.format("Function value for argument %f not stated.", x));
        }
        boolean isAbsLessThanOne = Math.abs(x - 1) <= 1;
        double raw = 0.0;
        int n = 1;
        while (n <= ITERATIONS) {
            raw -= Math.pow(-1, n) * Math.pow(x - 1, isAbsLessThanOne ? n : -n) / n;
            n++;
        }
        return isAbsLessThanOne ? raw : raw + apply(x - 1);
    }
}
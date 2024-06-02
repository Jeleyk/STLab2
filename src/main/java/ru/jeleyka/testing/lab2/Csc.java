package ru.jeleyka.testing.lab2;

public class Csc extends Function {

    private final Sin sin;

    public Csc(Sin sin) {
        this.sin = sin;
    }

    Csc() {
        this(new Sin());
    }

    @Override
    public double apply(double x) {
        double sinValue = sin.apply(x);
        if (sinValue == 0) {
            throw new IllegalArgumentException(String.format("Function value for argument %f doesn't exist.", x));
        }
        return 1 / sinValue;
    }
}
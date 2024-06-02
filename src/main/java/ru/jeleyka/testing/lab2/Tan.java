package ru.jeleyka.testing.lab2;

public class Tan extends Function {

    private final Sin sin;
    private final Cos cos;

    public Tan(Sin sin, Cos cos) {
        this.sin = sin;
        this.cos = cos;
    }

    Tan() {
        this(new Sin(), new Cos());
    }

    @Override
    public double apply(double x) {
        double cosValue = cos.apply(x);
        if (cosValue == 0) {
            throw new IllegalArgumentException(String.format("Function value for argument %f doesn't exist.", x));
        }
        double sinValue = sin.apply(x);
        return sinValue / cosValue;
    }
}
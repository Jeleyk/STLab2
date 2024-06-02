package ru.jeleyka.testing.lab2;

public class Sec extends Function {

    private final Cos cos;

    public Sec(Cos cos) {
        this.cos = cos;
    }

    Sec() {
        this(new Cos());
    }

    @Override
    public double apply(double x) {
        double cosValue = cos.apply(x);
        if (cosValue == 0) {
            throw new IllegalArgumentException(String.format("Function value for argument %f doesn't exist.", x));
        }
        return 1 / cosValue;
    }
}
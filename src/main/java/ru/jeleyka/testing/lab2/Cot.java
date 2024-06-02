package ru.jeleyka.testing.lab2;

public class Cot extends Function {

    private final Sin sin;
    private final Cos cos;

    public Cot(Sin sin, Cos cos) {
        this.sin = sin;
        this.cos = cos;
    }

    Cot() {
        this(new Sin(), new Cos());
    }

    @Override
    public double apply(double x) {
        double sinValue = sin.apply(x);
        if (sinValue == 0) {
            throw new IllegalArgumentException(String.format("Function value for argument %f doesn't exist.", x));
        }
        double cosValue = cos.apply(x);
        return cosValue / sinValue;
    }
}
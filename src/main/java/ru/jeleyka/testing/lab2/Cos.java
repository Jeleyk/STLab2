package ru.jeleyka.testing.lab2;

public class Cos extends Function {
    private final Sin sin;

    public Cos(Sin sin) {
        this.sin = sin;
    }

    Cos() {
        this(new Sin());
    }

    @Override
    public double apply(double x) {
        return sin.apply(Math.PI / 2 - x);
    }
}

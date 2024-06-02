package ru.jeleyka.testing.lab2;

public class Log extends Function {

    private final Ln ln;
    protected final double base;

    public Log(Ln ln, double base) {
        if (base <= 0) {
            throw new IllegalArgumentException("Base must be greater than 0.");
        }
        this.base = base;
        this.ln = ln;
    }

    public Log(double base) {
        this(new Ln(), base);
    }

    @Override
    public double apply(double x) {
        return ln.apply(x) / ln.apply(base);
    }
}
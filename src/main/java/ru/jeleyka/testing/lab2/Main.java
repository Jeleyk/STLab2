package ru.jeleyka.testing.lab2;

public class Main extends Function {

    private final Sin sin;
    private final Cos cos;
    private final Tan tan;
    private final Cot cot;
    private final Csc csc;
    private final Sec sec;
    private final Ln ln;
    private final Log log3;
    private final Log log5;
    private final Log log10;

    public Main(Sin sin, Cos cos, Tan tan, Cot cot, Csc csc, Sec sec, Ln ln, Log log3, Log log5, Log log10) {
        this.sin = sin;
        this.cos = cos;
        this.tan = tan;
        this.cot = cot;
        this.csc = csc;
        this.sec = sec;
        this.ln = ln;
        this.log3 = log3;
        this.log5 = log5;
        this.log10 = log10;
    }

    public Main() {
        this(
                new Sin(),
                new Cos(),
                new Tan(),
                new Cot(),
                new Csc(),
                new Sec(),
                new Ln(),
                new Log(3),
                new Log(5),
                new Log(10)
        );
    }

    @Override
    public double apply(double x) {
        double result;

        if (x <= 0) {
            result = (Math.pow(sec.apply(x) - tan.apply(x), 3) * sin.apply(x)
                    - (Math.pow(sin.apply(x), 3) / sin.apply(x)) + csc.apply(x)
            ) - ((tan.apply(x) / csc.apply(x) / cos.apply(x) / csc.apply(x)) + cot.apply(x));
        } else {
            result = Math.pow(Math.pow(log3.apply(x) + ln.apply(x), 3) - log10.apply(x), 3)
                    * (ln.apply(x) + (log3.apply(x) - log5.apply(x)));
        }

        return result;
    }

}

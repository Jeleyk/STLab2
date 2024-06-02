package ru.jeleyka.testing.lab2;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.jeleyka.testing.lab2.Constant.EPSILON;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FunctionTest {
    Sin sin;
    Cos cos;
    Tan tan;
    Cot cot;
    Csc csc;
    Sec sec;
    Ln ln;
    final List<Log> logs = new ArrayList<>();

    @BeforeAll
    void init() {
        sin = new Sin();
        cos = new Cos(sin);
        tan = new Tan(sin, cos);
        cot = new Cot(sin, cos);
        csc = new Csc(sin);
        sec = new Sec(cos);
        ln = new Ln();
        for (int i : new int[]{2, 3, 4, 5, 10, 15}) {
            logs.add(new Log(ln, i));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {-Math.PI, -Math.PI / 2, 0.0, Math.PI / 2, Math.PI, 1, 2, 3, 4, 5})
    void testSin(double x) {
        assertEquals(Math.sin(x), sin.apply(x), EPSILON);
    }

    @ParameterizedTest
    @ValueSource(doubles = {Double.NaN, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY})
    void testIllegalSin(double x) {
        assertThrows(IllegalArgumentException.class, () -> sin.apply(x));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-Math.PI, -Math.PI / 2, 0.0, Math.PI / 2, Math.PI})
    void testCos(double x) {
        assertEquals(Math.cos(x), cos.apply(x), EPSILON);
    }

    @ParameterizedTest
    @ValueSource(doubles = {Double.NaN, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY})
    void testIllegalCos(double x) {
        assertThrows(IllegalArgumentException.class, () -> cos.apply(x));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-Math.PI / 2, Math.PI / 2, 1, 2, 3})
    void testCsc(double x) {
        assertEquals(1 / Math.sin(x), csc.apply(x), EPSILON);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-Math.PI, 0.0, Math.PI, Double.NaN, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY})
    void testIllegalCsc(double x) {
        assertThrows(IllegalArgumentException.class, () -> csc.apply(x));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-Math.PI, -0.45, 0.0, 0.54, Math.PI})
    void testSec(double x) {
        assertEquals(1 / Math.cos(x), sec.apply(x), EPSILON);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-Math.PI / 2, Math.PI / 2, Double.NaN, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY})
    void testIllegalSec(double x) {
        assertThrows(IllegalArgumentException.class, () -> sec.apply(x));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-0.75, 0.0, 1.2})
    void testTan(double x) {
        assertEquals(Math.tan(x), tan.apply(x), EPSILON);
    }

    @ParameterizedTest
    @ValueSource(doubles = {Math.PI / 2, Double.NaN, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY})
    void testIllegalTan(double x) {
        assertThrows(IllegalArgumentException.class, () -> tan.apply(x));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-0.75, Math.PI / 2, 1.2})
    void testCot(double x) {
        assertEquals(1 / Math.tan(x), cot.apply(x), EPSILON);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, Double.NaN, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY})
    void testIllegalCot(double x) {
        assertThrows(IllegalArgumentException.class, () -> cot.apply(x));
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.001, 0.5, 1.0, 2.0})
    void testLn(double x) {
        assertEquals(Math.log(x), ln.apply(x), EPSILON);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1.0, 0.0})
    void testIllegalLn(double x) {
        assertThrows(IllegalArgumentException.class, () -> ln.apply(x));
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.001, 0.5, 1.0, 2.0})
    void testLog(double x) {
        assertAll(logs.stream()
                .map(log -> (Executable) () -> assertEquals(Math.log(x) / Math.log(log.base), log.apply(x), EPSILON))
                .toArray(Executable[]::new)
        );
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1.0, 0.0})
    void testIllegalLog(double x) {
        assertAll(logs.stream()
                .map(log -> (Executable) () -> assertThrows(IllegalArgumentException.class, () -> log.apply(x)))
                .toArray(Executable[]::new)
        );
    }
}

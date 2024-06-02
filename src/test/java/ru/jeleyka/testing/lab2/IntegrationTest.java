package ru.jeleyka.testing.lab2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static ru.jeleyka.testing.lab2.Constant.EPSILON;

@ExtendWith(MockitoExtension.class)
public class IntegrationTest {
    @Mock
    Sin sinMock;
    @Mock
    Cos cosMock;
    @Mock
    Tan tanMock;
    @Mock
    Cot cotMock;
    @Mock
    Csc cscMock;
    @Mock
    Sec secMock;
    @Mock
    Ln lnMock;
    @Mock
    Log log3Mock;
    @Mock
    Log log5Mock;
    @Mock
    Log log10Mock;

    @Spy
    Sin sinSpy;
    @Spy
    Cos cosSpy;
    @Spy
    Tan tanSpy;
    @Spy
    Cot cotSpy;
    @Spy
    Csc cscSpy;
    @Spy
    Sec secSpy;
    @Spy
    Ln lnSpy;
    Log log3Spy = spy(new Log(3));
    Log log5Spy = spy(new Log(5));
    Log log10Spy = spy(new Log(10));

    @Test
    void testCosSinUsing() {
        Cos cos = new Cos(sinSpy);
        cos.apply(1);
        verify(sinSpy, atLeastOnce()).apply(anyDouble());
    }

    @Test
    void testCosByMockedSin() {
        Cos cos = new Cos(sinMock);
        when(sinMock.apply(Math.PI / 2)).thenReturn(1.0);
        assertEquals(1.0, cos.apply(0), EPSILON);
    }

    @Test
    void testCscSinUsing() {
        Csc csc = new Csc(sinSpy);
        csc.apply(1);
        verify(sinSpy, atLeastOnce()).apply(anyDouble());
    }

    @Test
    void testCscByMockedSin() {
        Csc csc = new Csc(sinMock);
        double x = Math.PI / 4;
        when(sinMock.apply(x)).thenReturn(1 / Math.sqrt(2));
        assertEquals(Math.sqrt(2), csc.apply(x), EPSILON);
    }

    @Test
    void testSecCosUsing() {
        Sec sec = new Sec(cosSpy);
        sec.apply(1);
        verify(cosSpy, atLeastOnce()).apply(anyDouble());
    }

    @Test
    void testSecByMockedCos() {
        Sec sec = new Sec(cosMock);
        double x = 0;
        when(cosMock.apply(x)).thenReturn(1.0);
        assertEquals(1.0, sec.apply(x), EPSILON);
    }

    @Test
    void testTanSinAndCosUsing() {
        Tan tan = new Tan(sinSpy, cosSpy);
        tan.apply(1);
        verify(sinSpy, atLeastOnce()).apply(anyDouble());
        verify(cosSpy, atLeastOnce()).apply(anyDouble());
    }

    @Test
    void testTanByMockedSinAndCos() {
        Tan tan = new Tan(sinMock, cosMock);
        double x = 5;
        when(sinMock.apply(x)).thenReturn(-0.95892427);
        when(cosMock.apply(x)).thenReturn(0.28366218);
        assertEquals(-3.3805, tan.apply(x), EPSILON);
    }

    @Test
    void testCotCosAndSinUsing() {
        Cot cot = new Cot(sinSpy, cosSpy);
        cot.apply(1);
        verify(sinSpy, atLeastOnce()).apply(anyDouble());
        verify(cosSpy, atLeastOnce()).apply(anyDouble());
    }

    @Test
    void testCotByMockedCosAndSin() {
        Cot cot = new Cot(sinMock, cosMock);
        double x = 5;
        when(cosMock.apply(x)).thenReturn(0.28366218);
        when(sinMock.apply(x)).thenReturn(-0.95892427);
        assertEquals(-0.2958, cot.apply(x), EPSILON);
    }

    @Test
    void testMainWithNegativeArgsUsingFunctions() {
        new Main(
                sinSpy, cosSpy, tanSpy, cotSpy,
                cscSpy, secSpy,
                lnSpy, log3Spy, log5Spy, log10Spy
        ).apply(-8.5);
        verify(sinSpy, atLeastOnce()).apply(anyDouble());
        verify(cosSpy, atLeastOnce()).apply(anyDouble());
        verify(cscSpy, atLeastOnce()).apply(anyDouble());
        verify(secSpy, atLeastOnce()).apply(anyDouble());
        verify(tanSpy, atLeastOnce()).apply(anyDouble());
        verify(cotSpy, atLeastOnce()).apply(anyDouble());
    }

    @Test
    void testMainWithNegativeArgsByMocked() {
        Main main = new Main(
                sinMock, cosMock, tanMock, cotMock,
                cscMock, secMock,
                lnMock, log3Mock, log5Mock, log10Mock
        );
        double x = -Math.PI / 4;
        when(sinMock.apply(x)).thenReturn(Math.sqrt(2) / 2);
        when(cosMock.apply(x)).thenReturn(Math.sqrt(2) / 2);
        when(cscMock.apply(x)).thenReturn(Math.sqrt(2));
        when(secMock.apply(x)).thenReturn(Math.sqrt(2));
        when(tanMock.apply(x)).thenReturn(1.0);
        when(cotMock.apply(x)).thenReturn(1.0);
        assertEquals(-0.742640687119285, main.apply(x), EPSILON);
    }

    @Test
    void testMainWithPositiveArgsUsingFunctions() {
        new Main(
                sinSpy, cosSpy, tanSpy, cotSpy,
                cscSpy, secSpy,
                lnSpy, log3Spy, log5Spy, log10Spy
        ).apply(8.5);
        verify(lnSpy, atLeastOnce()).apply(anyDouble());
        verify(log3Spy, atLeastOnce()).apply(anyDouble());
        verify(log5Spy, atLeastOnce()).apply(anyDouble());
        verify(log10Spy, atLeastOnce()).apply(anyDouble());
    }

    @Test
    void testMainWithPositiveArgsByMocked() {
        Main main = new Main(
                sinMock, cosMock, tanMock, cotMock,
                cscMock, secMock,
                lnMock, log3Mock, log5Mock, log10Mock
        );
        double x = 8.5;
        when(lnMock.apply(x)).thenReturn(2.140066163496270);
        when(log3Mock.apply(x)).thenReturn(1.9479810351904712);
        when(log5Mock.apply(x)).thenReturn(1.3297020005800382);
        when(log10Mock.apply(x)).thenReturn(0.9294209439161237);
        assertEquals(844200.7088842168, main.apply(x), EPSILON);
    }
}

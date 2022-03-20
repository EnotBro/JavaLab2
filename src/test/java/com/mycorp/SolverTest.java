package com.mycorp;

import com.mycorp.exceptions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class SolverTest {

    @Test
    void getSolution_Simple() {
        double actual = Solver.getSolution("1+2");

        double expected = 3.0;

        assertEquals(expected,actual);
    }

    @Test
    void getSolution_MoreDifferentOperation() {
        double actual = Solver.getSolution("(1+2)*(3+4)");

        double expected = 21.0;

        assertEquals(expected,actual);
    }

    @Test
    void getSolution_UnaryOperations() {
        double actual = Solver.getSolution("cos((-1+2)*(3+4)-7)");

        double expected = 1.0;

        assertEquals(expected,actual);
    }

    @Test
    void getSolution_OpeningBrackets() {

        Throwable thrown = assertThrows(OpeningBracketsMoreThanClosingException.class, () -> {
            double test = Solver.getSolution("(2+3*5");
        });
        assertNotNull(thrown.getMessage());
    }

    @Test
    void getSolution_ClosingBrackets() {

        Throwable thrown = assertThrows(ClosingBracketsMoreThanOpeningException.class, () -> {
            double test = Solver.getSolution("(2+3)*5)");
        });
        assertNotNull(thrown.getMessage());
    }

    @Test
    void getSolution_UnknownSymbols() {

        Throwable thrown = assertThrows(UnknownSymbolInExpressionException.class, () -> {
            double test = Solver.getSolution("(2+3)_5)");
        });
        assertNotNull(thrown.getMessage());
    }

    @Test
    void getSolution_DivisionByZero() {

        Throwable thrown = assertThrows(DivisionByZeroInExpressionException.class, () -> {
            double test = Solver.getSolution("1/((2+3)*5-25)");
        });
        assertNotNull(thrown.getMessage());
    }

    @Test
    void getSolution_UnaryOperationWithoutArgument() {

        Throwable thrown = assertThrows(UnaryOperationWithoutArgumentException.class, () -> {
            double test = Solver.getSolution("cos()");
        });
        assertNotNull(thrown.getMessage());
    }

    @Test
    void getSolution_BinaryOperationWithoutArgument() {

        Throwable thrown = assertThrows(BinaryOperationWithoutArgumentException.class, () -> {
            double test = Solver.getSolution("1(+)2");
        });
        assertNotNull(thrown.getMessage());
    }

    @Test
    void getSolution_Parameters() {

        ByteArrayInputStream in = new ByteArrayInputStream("5".getBytes());
        System.setIn(in);

        double actual = Solver.getSolution("2*X+5*X");
        System.setIn(System.in);
        double expected = 35.0;

        assertEquals(expected,actual);
    }
}
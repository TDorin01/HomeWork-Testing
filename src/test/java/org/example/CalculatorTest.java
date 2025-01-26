package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {
    @ParameterizedTest
    @CsvSource({
            "5cm + 3m, m, 3.05",
            "2km - 500m, km, 1.5",
            "1m - 2m, m, -1.0"
    })
    public void testParameterized(String expression, String outputUnit, double expected) {
        Calculator calculator = null;
        double result = calculator.calculate(expression, outputUnit);
        assertEquals(expected, result, 0.0001);
    }

    @Test
    public void testAdditionDifferentUnits() {
        String expression = "5cm + 3m";
        String outputUnit = "m";
        Calculator calculator = null;
        double result = calculator.calculate(expression, outputUnit);
        assertEquals(3.05, result, 0.0001);
    }

    @Test
    public void testSubtractionDifferentUnits() {
        String expression = "2km - 500m";
        String outputUnit = "km";
        Calculator calculator;
        double result = Calculator.calculate(expression, outputUnit);
        assertEquals(1.5, result, 0.0001);
    }
    @Test
    public void testNegativeResult() {
        String expression = "1m - 2m";
        String outputUnit = "m";
        Calculator calculator = null;
        double result = calculator.calculate(expression, outputUnit);
        assertEquals(-1.0, result, 0.0001);
    }
}





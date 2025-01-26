package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
    private static final Map<String, Double> UNIT_CONVERSIONS = new HashMap<>();

    static {
        UNIT_CONVERSIONS.put("mm", 0.001);
        UNIT_CONVERSIONS.put("cm", 0.01);
        UNIT_CONVERSIONS.put("dm", 0.1);
        UNIT_CONVERSIONS.put("m", 1.0);
        UNIT_CONVERSIONS.put("km", 1000.0);
    }

    public static double calculate(String expression, String outputUnit) {
        if (!UNIT_CONVERSIONS.containsKey(outputUnit)) {
            throw new IllegalArgumentException("Unsupported output unit: " + outputUnit);
        }

        double resultInMeters = 0.0;


        Pattern pattern = Pattern.compile("([-+]?\\d+(?:\\.\\d+)?)([a-zA-Z]+)");
        Matcher matcher = pattern.matcher(expression.replaceAll("\\s+", ""));

        while (matcher.find()) {
            double value = Double.parseDouble(matcher.group(1));
            String unit = matcher.group(2);

            if (!UNIT_CONVERSIONS.containsKey(unit)) {
                throw new IllegalArgumentException("Unsupported unit: " + unit);
            }

            resultInMeters += value * UNIT_CONVERSIONS.get(unit);
        }


        return resultInMeters / UNIT_CONVERSIONS.get(outputUnit);
    }

    public static void main(String[] args) {
        String expression = "10cm + 1m - 10mm ";
        String outputUnit = "mm";

        try {
            double result = calculate(expression, outputUnit);
            System.out.println("Result is : " + result + " " + outputUnit);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
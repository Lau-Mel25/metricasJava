package com.example.badcalc;

public class Calculator {

    public double parse(String s) {
        if (s == null) return 0;
        try {
            return Double.parseDouble(s.replace(',', '.').trim());
        } catch (Exception e) {
            throw new IllegalArgumentException("Número inválido: " + s);
        }
    }

    public double compute(double a, double b, String op) {
        return switch (op) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> {
                if (b == 0) throw new ArithmeticException("División por cero");
                yield a / b;
            }
            case "^" -> Math.pow(a, b);
            case "%" -> a % b;
            default -> throw new IllegalArgumentException("Operación inválida: " + op);
        };
    }

    public double sqrt(double v) {
        if (v < 0) throw new IllegalArgumentException("Raíz de negativo no permitida.");
        return Math.sqrt(v);
    }
}

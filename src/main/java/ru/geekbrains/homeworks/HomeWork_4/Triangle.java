package ru.geekbrains.homeworks.HomeWork_4;

import lombok.Data;

@Data
public class Triangle {

    private double a;
    private double b;
    private double c;

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public boolean isValidTriangle() {
        double maxSide = Math.max(a, Math.max(b, c));
        return maxSide < (a + b + c - maxSide);
    }

    public boolean hasPositiveSides() {
        return a > 0 && b > 0 && c > 0;
    }

    public double countArea() {
        if (!hasPositiveSides()) {
            throw new IllegalArgumentException("Значения длин сторон должны быть положительными");
        }
        if (!isValidTriangle()) {
            throw new IllegalArgumentException("Это не треугольник");
        }
        double p = (a + b + c) / 2;
        double s = p * (p - a) * (p - b) * (p - c);
        return Math.round(Math.sqrt(s) * 10.0) / 10.0 ;
    }
}
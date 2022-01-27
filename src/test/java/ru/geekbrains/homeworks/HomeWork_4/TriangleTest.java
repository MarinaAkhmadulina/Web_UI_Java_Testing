package ru.geekbrains.homeworks.HomeWork_4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Треугольник")
public class TriangleTest {

    public static Stream<Arguments> triangles() {
        return Stream.of(Arguments.of(new Triangle(5,5,5), 10.8),
                Arguments.of(new Triangle(3,4,5), 6),
                Arguments.of(new Triangle(3,4,6), 5.3));
    }

    @ParameterizedTest(name = "Площадь треугольника {0} равна {1}")
    @MethodSource("triangles")
    public void countPerimeterTest(Triangle triangle, double expectedArea) {
        double area = triangle.countArea();
        assertEquals(expectedArea, area);
    }

    @Test
    public void checkNegativeHasPositiveSides() {
        String error = null;
        try {
            (new Triangle(-5, 5, 5)).countArea();
        }
        catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertEquals("Значения длин сторон должны быть положительными", error);
    }

}
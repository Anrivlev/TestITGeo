package ru.rectangles;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.stream.DoubleStream;

public class Rectangle implements Comparable<Rectangle> {
    public double xMin;
    public double xMax;
    public double yMin;
    public double yMax;

    public Rectangle(double xMin, double xMax, double yMin, double yMax){
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
    }

    public Rectangle[] split(double x) {
        // splits a rectangle vertically at x
        // if x is not in the rectangle returns rectangle unchanged
        Rectangle[] newRectangles;
        if (this.xMin < x && x < this.xMax) {
            newRectangles = new Rectangle[2];
            newRectangles[0] = new Rectangle(xMin, x, yMin, yMax);
            newRectangles[1] = new Rectangle(x, xMax, yMin, yMax);
        } else {
            newRectangles = new Rectangle[1];
            newRectangles[0] = this;
        }
        return newRectangles;
    }

    public Rectangle (List<Rectangle> rectangles) {
        // is used to unite rectangles with equal xMin and xMax
        this.xMin = rectangles.get(0).xMin;
        this.xMax = rectangles.get(0).xMax;
        this.yMin = rectangles.stream().flatMapToDouble(r -> DoubleStream.of(r.yMin)).min().getAsDouble();
        this.yMax = rectangles.stream().flatMapToDouble(r -> DoubleStream.of(r.yMax)).max().getAsDouble();
    }

    public Rectangle (Rectangle[] rectangles) {
        // is used to unite rectangles with equal xMin and xMax
        this.xMin = rectangles[0].xMin;
        this.xMax = rectangles[0].xMax;
        this.yMin = Arrays.stream(rectangles).flatMapToDouble(r -> DoubleStream.of(r.yMin)).min().getAsDouble();
        this.yMax = Arrays.stream(rectangles).flatMapToDouble(r -> DoubleStream.of(r.yMax)).max().getAsDouble();
    }

    public double getArea() {
        return (this.xMax - this.xMin) * (this.yMax - this.yMin);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return Double.compare(rectangle.xMin, xMin) == 0 && Double.compare(rectangle.xMax, xMax) == 0 && Double.compare(rectangle.yMin, yMin) == 0 && Double.compare(rectangle.yMax, yMax) == 0;
    }

    @Override
    public int compareTo(@NotNull Rectangle o) {
        return Double.compare(this.xMin, o.xMin);
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "(" + xMin +
                ", " + yMin +
                "), (" + xMax +
                ", " + yMax +
                ")}";
    }

    public boolean intersects(Rectangle other) {
        return this.xMin <= other.xMax && this.xMax >= other.xMin && this.yMin <= other.yMax && this.yMax >= other.yMin;
    }
}

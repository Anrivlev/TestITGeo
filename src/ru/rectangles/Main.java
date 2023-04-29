package ru.rectangles;


import java.util.*;

public class Main {
    public static void main(String[] args) {
        // input. Rectangles are described by 4 numbers (or by two opposite vertices)
        List<Rectangle> rectangles = new ArrayList<>();
        rectangles.add(new Rectangle(0, 2, 0, 2));
        rectangles.add(new Rectangle(1, 4, 1, 4));
        rectangles.add(new Rectangle(3, 6, -1, 2));

        // xValuesSet contains all x-coordinates of rectangles' vertices
        Set<Double> xValuesSet = new HashSet<>(); // set is used to avoid duplicates
        for (Rectangle rectangle : rectangles) {
            xValuesSet.add(rectangle.xMin);
            xValuesSet.add(rectangle.xMax);
        }
        List<Double> xValues = xValuesSet.stream().toList();
        xValues.sort(null); // sort in ascending order

        // split all rectangles by vertical lines at points from xValues
        List<Rectangle> tmpRectangles = new ArrayList<>();
        for (Double x : xValues) {
            for (Rectangle rectangle : rectangles) {
                tmpRectangles.addAll(List.of(rectangle.split(x)));
            }
            rectangles.clear();
            rectangles.addAll(tmpRectangles);
            tmpRectangles.clear();
        }

        // unite all rectangles with same xMin and xMax in order to get rid of intersections
        rectangles.sort(null); // rectangles are sorted by their xMins
        List<Rectangle> rectanglesToUnite = new ArrayList<>();
        for (Rectangle rectangle : rectangles) {
            if (rectanglesToUnite.size() == 0) {
                rectanglesToUnite.add(rectangle);
            } else if (rectanglesToUnite.get(0).xMin == rectangle.xMin) {
                rectanglesToUnite.add(rectangle);
            } else {
                tmpRectangles.add(new Rectangle(rectanglesToUnite));
                rectanglesToUnite.clear();
                rectanglesToUnite.add(rectangle);
            }
        }
        if (rectanglesToUnite.size() != 0) {tmpRectangles.add(new Rectangle(rectanglesToUnite));}
        rectangles.clear();
        rectangles.addAll(tmpRectangles);

        // calculate totalArea
        double totalArea = 0;
        for (Rectangle rectangle : rectangles) {
            totalArea += rectangle.getArea();
        }
        System.out.println("Total area: " + totalArea);
    }
}

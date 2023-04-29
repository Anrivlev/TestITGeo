package ru.rectangles;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        List<Double> xValues = new ArrayList<>(xValuesSet);
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
                tmpRectangles.addAll(uniteRectangles(rectanglesToUnite));
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

    public static List<Rectangle> uniteRectangles(List<Rectangle> rectangles) {
        if (rectangles.size() == 1) {return rectangles;}
        int i = 1;
        do {
            int j = 0;
            while (true) {
                if (rectangles.get(i).intersects(rectangles.get(j))) {
                    rectangles.add(new Rectangle(new Rectangle[]{rectangles.get(i), rectangles.get(j)}));
                    rectangles.remove(i);
                    i -= 1;
                    rectangles.remove(j);
                    if (rectangles.size() == 1) {return rectangles;}
                } else {
                    j += 1;
                    if (j == i) {
                        break;
                    }
                }
            }
            i += 1;
        } while (i != rectangles.size());
        return rectangles;
    }
}

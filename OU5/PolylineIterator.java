package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PolylineIterator implements Iterator<Point> {
    private Point[] vertices;
    private int currentIndex = 0;
    public PolylineIterator(Polyline ourPolyline) {
        vertices = ourPolyline.getVertices();
    }
    public Point next() {
        if(hasNext()) {
            System.out.println(currentIndex);
            return vertices[currentIndex++];
        }
        else throw new NoSuchElementException("Ran out of vertices in our Polyline Iterator! Tried to access index " +
                currentIndex +", when our vertices array only has " + vertices.length + " points!");
    }
    public boolean hasNext() {
        return currentIndex < vertices.length;
    }
}

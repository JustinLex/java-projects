package com.company;

import sun.security.krb5.internal.APOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class APolyline implements Polyline {

    private ArrayList<Point> vertices;

    private Color color = Color.BLACK;

    private int width = 1;

    public APolyline() {
        vertices = null;
    }

    public APolyline(Point[] vertices) {
        this.vertices = new ArrayList<Point>(Arrays.asList(vertices));
    }

    public Point[] getVertices(){
        return vertices.toArray( new Point[vertices.size()] );
    }

    public Color getColor() {
        return color;
    }

    public int getWidth() {
        return width;
    }

    public double length() {

        double length = 0;

        //adds the distance between every Point in vertices
        for (int i = 1; i < vertices.size(); i++) {
            length += vertices.get(i).distance( vertices.get(i-1) );
        }

        return length;

    }

    public void setColor(Color color) { this.color = color; }

    public void setWidth(int width) { this.width = width; }

    /**
     * Finds the indicated Point in the polyline. It will return the index of the first point in the polyline
     * with the right name. If the Point does not exist in the polyline, it returns -1.
     * @param vertexName Name of the Point to find
     * @return The index of the Point in the vertex ArrayList, or -1 if there is no Point in the polyline with that name
     */
    private int findPoint(String vertexName) {

        //check every Point in vertices
        for(int i = 0; i < vertices.size(); i++) {

            //see if this Point has the right name
            if( vertices.get(i).getName().equals(vertexName) ) {
                return i; //if this Point is the right one, return its index
            }

        }

        //if no point had the name, return -1
        return -1;

    }

    public void add(Point vertex) { vertices.add(vertex); }

    public void insertBefore(Point vertex, String vertexName) {

        int indexOfPointToMove = findPoint(vertexName);

        //throw exception if Point isn't in the line
        if(indexOfPointToMove == -1) { throw new NoSuchElementException();}

        vertices.add(indexOfPointToMove, vertex);

    }

    public void remove(String vertexName) {

        int indexOfPointToRemove = findPoint(vertexName);

        //throw exception if Point isn't in the line
        if(indexOfPointToRemove == -1) { throw new NoSuchElementException();}

        vertices.remove(indexOfPointToRemove);

    }

    public Iterator<Point> iterator() {
        return new Iterator<Point>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < vertices.size();
            }

            @Override
            public Point next() {
                Point currentPoint = vertices.get(currentIndex);
                currentIndex++;
                return currentPoint;
            }

        };
    }

    @Override
    public String toString() {

        String pointsArrayString = vertices.toString();
        String colorName = color.colorName();
        String widthString = Integer.toString(width);

        return ""+'{' + pointsArrayString + ", " + colorName + ", " + widthString + '}';

    }
}

package com.company;

import java.util.Iterator;
import java.util.LinkedList;

public interface Polyline extends Iterable<Point> {

    /**
     * Retrieves the Points inside the polyline
     * @return Returns an array containing the Points that make up the polyline
     */
    Point[] getVertices();

    /**
     * Retrieves the color of the polyline
     * @return Returns the polyline's color enum
     */
    Color getColor();

    /**
     * Retrieves the width of the polyline
     * @return Returns the width of the polyline in an integer
     */
    int getWidth();

    /**
     * Calculates the total length of the polyline
     * @return Returns the length of the polyline in a double
     */
    double length();

    /**
     * Set the color of the polyline
     * @param color Color enum to give to the polyline
     */
    void setColor(Color color);

    /**
     * Set the width of the polyline
     * @param width Integer to set the polyline's width to
     */
    void setWidth(int width);

    /**
     * Adds a Point to the end of the polyline (does not copy)
     * @param vertex Point to directly add to the polyline
     */
    void add(Point vertex);

    /**
     * Adds a Point to the polyline, directly before the Point specified. (does not copy)
     * @param vertex Point to directly add to the polyline
     * @param vertexName Name of the Point to preceed with the new Point
     */
    void insertBefore(Point vertex, String vertexName);

    /**
     * Removes a Point from the polyline
     * @param vertexName Name of the Point to remove
     */
    void remove(String vertexName);

}

package com.company;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class Polyline {
    private LinkedList<Point> vertices;
    private String color = "black";
    private int width = 1;

    /**
     * Intializes an empty Polyline with the color black and a wdith of 1
     */
    public Polyline() {
        vertices = new LinkedList<Point>();
    }

    /**
     * Initializes a Polyline containing the Point objects in the specified array, that has the color black,
     * and a width of 1
     * @param vertices an array of Points to add to the new Polyline
     */
    public Polyline(Point[] vertices) {
        this.vertices = new LinkedList<Point>(Arrays.asList(vertices));
    }

    /**
     * Initializes a Polyline containing the Point objects in the specified LinkedList, that has the color black,
     * and a width of 1
     * @param vertices an array of Points to add to the new Polyline
     */
    public Polyline(LinkedList<Point> vertices) {
        this.vertices = new LinkedList<Point>(vertices);
    }

    /**
     * Over-optimized method to output the contents of the Polyline in the form
     * {[(A 3 4)(B 1 2)(C 2 3)(D 5 1)], black, 1}
     * @return String with the contents of the Polyline
     */
    public String toString() {

        String[] pointStrings = new String[vertices.size()]; //String array to hold all Point Strings

        //go to every point in the LinkedList and put their string into our String array
        Iterator<Point> verticesIter = vertices.iterator();
        int index = 0;
        while (verticesIter.hasNext()) {
            pointStrings[index] = verticesIter.next().toString();
            index++;
        }

        String widthString = Integer.toString(width); //string of our width int


        //precalculate the length of all our pointStrings combined, with square brackets around it
        int lengthOfPointStringSection = 2; //start with square brackets length
        for(String s : pointStrings) lengthOfPointStringSection += s.length();

        //precalculate our final output string length (we have 6 extra chars, two curly brackets, two commas, and two spaces
        int lengthOfOutputString = 6 + lengthOfPointStringSection + color.length() + widthString.length();


        //create our stringbuilder initialized with our precalculated length
        StringBuilder sb = new StringBuilder(lengthOfOutputString);

        //build!
        sb.append('{');
        sb.append('['); for(String s : pointStrings) sb.append(s); sb.append("], ");
        sb.append(color).append(", ");
        sb.append(widthString);
        sb.append('}');

        return sb.toString();
    }

    public Point[] getVertices() {
        return vertices.toArray(new Point[vertices.size()]);
    }

    public LinkedList<Point> getVerticesList() { return vertices; }

    public String getColor() { return color; }

    public int getWidth() { return width; }

    public void setColor(String color) { this.color = color; }

    public void setWidth(int width) { this.width = width; }

    public double length() {
        double length = 0;
        Point lastVertex = null;

        for(Point vertex : vertices) {
            //calculate the distance from this point to the last point, then add it to the total length of the line
            //(if this is the first point we're looking at, it will compare with null, which returns a distance of 0)
            length += vertex.distance(lastVertex);
            lastVertex = vertex; //put this point into lastVertex so we can then compare it to the next
        }
        return length;
    }

    /**
     * Makes a copy of the given Point and adds the copy to the beginning of the Polyline
     * @param vertex Point to copy and add to the Polyline
     */
    public void addLast(Point vertex) {
        Point clone = new Point(vertex); //clone the point we want to add
        vertices.addLast(clone); //add the clone to the end of our LinkedList
    }

    /**
     * Makes a copy of the given Point and adds the copy to the end of the Polyline
     * @param vertex Point to copy and add to the Polyline
     */
    public void addBefore(Point vertex) {
        Point clone = new Point(vertex); //clone the point we want to add
        vertices.addFirst(clone); //add the clone to the start of our LinkedList
    }

    /**
     * Removes the first Point in the Polyline that has a certain Name (there should only be one in most usecases)
     * @param vertexName Name of the Point to remove
     */
    public void remove(String vertexName) {
        //iterate over every Point in our LinkedList
        Iterator<Point> i = vertices.iterator();
        while(i.hasNext()) {
            if(i.next().getName().equals(vertexName)) { //if the point we're looking at in the List has the same name...
                i.remove(); //...then remove that point
                break;
            }
        }
    }

}

package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LPolyline implements Polyline {

    private static class Node {
        private Point vertex;
        private Node nextNode;
        private Node prevNode; //(doubly-linked)

        private Node(Point vertex) {
            this.vertex = vertex;
        }

    }

    private Node firstNode, lastNode;
    private int size;
    private Color color = Color.BLACK;
    private int width = 1;

    public LPolyline() {}

    public LPolyline(Point[] vertices) {

        size = vertices.length;

        if(vertices.length > 0) { //see if there are any Points to add

            //create a variable to hold the current node we're working with
            Node currentNode;

            //Initialize currentNode to be a Node holding first Point, and set it to be the firstNode of our polyline
            currentNode = new Node(vertices[0]);
            firstNode = currentNode;

            //loop through all Points in vertices
            for(int pos = 1; pos < vertices.length; pos++) {

                Node nextNode = new Node(vertices[pos]); //create a new Node for the next Point in the array

                currentNode.nextNode = nextNode; //link the current node to this next node
                nextNode.prevNode = currentNode; //link the next node back to our current node

                //move forward to our next node
                currentNode = nextNode;

            }

            //At this point, currentNode contains the final Point in Point[] vertices, so we set lastNode to currentNode
            lastNode = currentNode;

        }
    }

    public Point[] getVertices(){

        Point[] vertices = new Point[size]; //vertices array that we will return

        //Use for-each loop to add every Point in this polyline to the array, use i to keep track of which
        // array index we're adding
        int i = 0;
        for(Point p : this) { vertices[i++] = p; }

        return vertices;

    }

    public Color getColor() {
        return color;
    }

    public int getWidth() {
        return width;
    }

    public double length() {

        double length = 0;

        Iterator<Point> iter = iterator();

        //skip first point and make it the last point we looked at
        Point lastPoint = iter.next();

        //add the distances between every point and the point before it
        while(iter.hasNext()) {
            length += iter.next().distance(lastPoint);
        }

        return length;

    }

    public void setColor(Color color) { this.color = color; }

    public void setWidth(int width) { this.width = width; }

    public void add(Point vertex) {

        Node newNode = new Node(vertex); //make a node out of the new Point

        lastNode.nextNode = newNode; //link the current lastNode to this new Node

        newNode.prevNode = lastNode; //link this new node back to the current lastNode

        lastNode = newNode; //make this new Node the new lastNode

        size++; //increment Polyline node count

    }

    public void insertBefore(Point vertex, String vertexName) {

        Node currentNode = firstNode;

        while(currentNode.nextNode != null) { //keep searching until we reach the last Node

            //see if this node holds the Point we're looking for
            if(currentNode.vertex.getName().equals(vertexName)) {

                //create a new Node out of the Point
                Node newNode = new Node(vertex);

                //The next 4 links fits this new Node between the matching node and the node before this match

                currentNode.prevNode.nextNode = newNode; //link the Node before the match to our new node
                newNode.prevNode = currentNode.prevNode; //link the new new node back towards the preceeding Node

                newNode.nextNode = currentNode;
                currentNode.prevNode = newNode;

                size++; //increment Polyline node count
                return;

            }


            else currentNode = currentNode.nextNode; //if we didn't find a match, move onto the next node

        }

        //no match found, throw exception
        throw new NoSuchElementException();

    }

    public void remove(String vertexName) {

        Node currentNode = firstNode;

        while(currentNode.nextNode != null) { //keep searching until we reach the last Node

            //see if this node holds the Point we're looking for
            if(currentNode.vertex.getName().equals(vertexName)) {

                //remove node from polyline by linking over it

                currentNode.prevNode.nextNode = currentNode.nextNode; //link n-1 to n+1
                currentNode.nextNode.prevNode = currentNode.prevNode; //link n+1 backwards to -1

                size--; //decrement Polyline node count
                return;

            }


            else currentNode = currentNode.nextNode; //if we didn't find a match, move onto the next node

        }

        //no match found, throw exception
        throw new NoSuchElementException();

    }

    public Iterator<Point> iterator() {
        return new Iterator<Point>() {

            Node currentNode = firstNode;

            @Override
            public boolean hasNext() { return currentNode != null; }

            @Override
            public Point next() {

                if(!hasNext()) throw new NoSuchElementException();

                else {
                    Point currentPoint = currentNode.vertex;
                    currentNode = currentNode.nextNode;
                    return currentPoint;
                }
            }

        };
    }

    @Override
    public String toString() {

        StringBuilder pointsSB = new StringBuilder();

        pointsSB.append('[');
        for(Point p : this) {
            pointsSB.append(p.toString());
            pointsSB.append(", ");
        }
        pointsSB.delete(pointsSB.length()-2,pointsSB.length()); //remove extra ", " after the last point
        pointsSB.append(']');

        String pointsArrayString = pointsSB.toString();

        String colorName = color.colorName();
        String widthString = Integer.toString(width);

        return ""+'{' + pointsArrayString + ", " + colorName + ", " + widthString + '}';
                
    }
}

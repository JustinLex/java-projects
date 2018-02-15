package com.company;

public class Point {

    private String pointName;
    private int xPos;
    int yPos;

    public Point(String name, int x, int y) {
        pointName = name;
        xPos = x;
        yPos = y;
    }

    public Point(Point pointToClone) {
        pointName = pointToClone.getName();
        xPos = pointToClone.getX();
        yPos = pointToClone.getY();
    }

    public String getName() { return pointName; }
    public int getX() { return xPos; }
    public int getY() { return yPos; }

    public void setX(int x) { xPos = x; }
    public void setY(int y) { yPos = y; }

    /**
     * Compares the distance between this point and another point
     * @param pointToMeasure
     * @return
     */
    public double distance(Point pointToMeasure) {
        if(pointToMeasure == null) return 0.0d;
        int xComponent = pointToMeasure.getX() - xPos;
        int yComponent = pointToMeasure.getY() - yPos;
        double distance = Math.sqrt( Math.pow(xComponent,2) + Math.pow(yComponent,2) );
        return distance;
    }

    /**
     * Checks to see if this point has the same coordinates of the point given
     * @param pointToCompare
     * @return
     */
    public boolean equals(Point pointToCompare) {
        boolean xEquals = (xPos == pointToCompare.getX());
        boolean yEquals = (yPos == pointToCompare.getY());
        if(xEquals && yEquals) return true;
        else return false;
    }

    public String toString() {
        //convert our coords to Strings
        String xString = Integer.toString(xPos);
        String yString = Integer.toString(yPos);

        //create a string builder to make our String, and initialize it to the precalculated final length of our String
        // (we have 4 extra chars outside of our variables, our parenthesis and two spaces)
        StringBuilder sb = new StringBuilder(4 + pointName.length() + xString.length() + yString.length());

        //use the StringBuilder to build our string and return it
        sb.append('(').append(pointName).append(' ').append(xString).append(' ').append(yString).append(')');
        return sb.toString();
    }

}

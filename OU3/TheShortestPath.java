package com.company;

public class TheShortestPath {
    private int[] shortestPair = {0,0};
    private float shortestLength;

    public TheShortestPath(float[] aLength, float[][] bLength, float[] cLength) {
       shortestLength = aLength[0] + bLength[0][0] + cLength[0]; //initialize to the first path

        //try all possible routes
        for( int i = 0; i < aLength.length; i++) {
            for( int j = 0; j < cLength.length; j++) {

                float routeLength = aLength[i] + bLength[i][j] + cLength[j]; //calculate length of this path

                //check to see if this new path is the shortest route
                if(routeLength < shortestLength) {
                    //update new shortest route
                    shortestLength = routeLength;
                    shortestPair[0] = i;
                    shortestPair[1] = j;
                }

            }
        }
    }

    public int[] getPath() { return shortestPair; }
    public float getPathDistance() { return shortestLength; }

    public static int comparePossiblePaths(float[] pathDistance) {
        int shortestIndex = 0;
        for(int i = 0; i < pathDistance.length; i++) {
            if (pathDistance[i] < pathDistance[shortestIndex])
                shortestIndex = i;
        }
        return shortestIndex;
    }
}

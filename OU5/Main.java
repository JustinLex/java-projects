package com.company;

import sun.awt.image.ImageWatched;
import sun.awt.windows.ThemeReader;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Random;

public class Main {

    //select program mode
    public static final byte programMode = -1;
    //0 - Point Test
    //1 - Polyline Test and Draw
    //2 - Select Polyline
    //3 - ReinventTheWheel.exe

    public static void main(String[] args) {

        PrintWriter out = new PrintWriter(System.out, true);

        if(programMode == 0) {

            //Test Point's constructor and toString method
            Point p1 = new Point("A", 3, 4);
            Point p2 = new Point("B", 5, 6);
            out.println(p1 + "   " + p2);

            //test inspectors
            String n = p1.getName();
            int x = p1.getX();
            int y = p1.getY();
            out.println(n + ' ' + x + ' ' + y);

            //test a combiner and a comparator
            double d = p1.distance(p2);
            out.println(d);
            boolean b = p1.equals(p2);
            out.println(b);

            //test mutators
            p2.setX(1);
            p2.setY(1);
            out.println(p2);

            //test another constructor
            Point p = new Point(p1);
            out.println(p);

            //distance confirmation
            try {
                new ProcessBuilder("chromium", "https://www.wolframalpha.com/input/?i=distance+between+(3,4)+and+(5,6)").start();
            } catch(IOException e) {}
        }

        /**
         * Polyline Test and Draw
         */
        else if(programMode == 1) {
            Polyline ourPolyline = randomPolyline();
            out.println(ourPolyline);
        }

        /**
         * Select Polyline Test
         */
        else if(programMode == 2) {
            //generate x amouunt of completely random polylines
            final int N_OF_POLYLINES = 100;
            Polyline[] polylines = new Polyline[N_OF_POLYLINES];
            for(int i = 0; i < N_OF_POLYLINES; i++) {
                polylines[i] = randomPolyline();
            }

            //look for a yellow polyline
            boolean foundYellow = false;
            int shortestYellowLength;
            for(int i = 0; i < N_OF_POLYLINES; i++) {
                if(polylines[i].getColor().toLowerCase().equals("yellow")) {
                    System.out.println("Found a yellow polyline!");
                    System.out.println(polylines[i]);

                    foundYellow = true;
                }
            }
            if(!foundYellow) System.out.println("Couldn't find a yellow polyline :(");


        }

        /**
         * PolylineIterator Test
         */
        else if(programMode == 3) {
            //create a random polyline and print it
            Polyline ourPolyline = randomPolyline();
            System.out.println(ourPolyline);

            //print out the polyline with our iterator until failure
            PolylineIterator iter = new PolylineIterator(ourPolyline);
            while(true) {
                out.println(iter.next());
                try{Thread.sleep(500);}
                catch(Exception e) {}
            }
        }

        /**
         * Invalid Program Mode
         */
        else {
            out.println("Invalid Program Mode set at compile time!");
            throw new Error("You dun goofed");
        }
    }

    private static Random rng = new Random();

    /**
     * Creates a new point with the given name and random coordinates between -30 and 30
     * @param name name to give the point
     * @return Point with the given name and random coordinates
     */
    public static Point randomPoint(String name) {
        int x = rng.nextInt(61) - 30;
        int y = rng.nextInt(61) - 30;
        return new Point(name, x, y);
    }

    public static Polyline randomPolyline() {
        //number of vertices this Poly line will have, randomly chosen between 2 and 10
        int nofVertices = 2 + rng.nextInt(9);
        //array of booleans to keep track of letters we already picked
        boolean[] pickedLetters = new boolean[26];
        // LinkedList to hold our Points until we create our Polyline that will take over
        LinkedList<Point> vertices = new LinkedList<Point>();

        //create the points and add them to our LinkedList
        for(int i = 0; i < nofVertices; i++) {
            //pick a letter, a=0,b=1,c=2,etc
            byte letterNumber;
            do letterNumber = (byte) rng.nextInt(26);
            while(pickedLetters[letterNumber]); //check that letter hasn't been picked yet

            //mark the picked letter in our boolean array and convert into a string with the letter
            pickedLetters[letterNumber] = true;
            String n = Character.toString((char) (65 + letterNumber));
            vertices.add(randomPoint(n));

        }
        //create polyline with chosen points
        Polyline newPolyline = new Polyline(vertices);

        //choose a color
        //black red green blue yellow purple orange grey
        int colorIndex = rng.nextInt(8);
        String colorString;
        switch(colorIndex) {
            case 0: colorString = "black"; break;
            case 1: colorString = "red"; break;
            case 2: colorString = "green"; break;
            case 3: colorString = "blue"; break;
            case 4: colorString = "yellow"; break;
            case 5: colorString = "purple"; break;
            case 6: colorString = "orange"; break;
            case 7: colorString = "grey"; break;
            default: colorString = null;
        }
        newPolyline.setColor(colorString);
        newPolyline.setWidth(rng.nextInt(5) + 1);
        return newPolyline;
    }
}



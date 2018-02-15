package com.company;

import java.util.NoSuchElementException;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
	// write your code here

         Random random = new Random();
         Point[][] points = {
                 {
                         new Point("A1", random.nextInt(50), random.nextInt(50)),
                         new Point("B1", random.nextInt(50), random.nextInt(50)),
                         new Point("C1", random.nextInt(50), random.nextInt(50)),
                         new Point("D1", random.nextInt(50), random.nextInt(50)),
                         new Point("E1", random.nextInt(50), random.nextInt(50)),
                         new Point("F1", random.nextInt(50), random.nextInt(50)),
                 },
                 {
                         new Point("A2", random.nextInt(50), random.nextInt(50)),
                         new Point("B2", random.nextInt(50), random.nextInt(50)),
                         new Point("C2", random.nextInt(50), random.nextInt(50)),
                         new Point("D2", random.nextInt(50), random.nextInt(50)),
                         new Point("E2", random.nextInt(50), random.nextInt(50)),
                         new Point("F2", random.nextInt(50), random.nextInt(50)),
                 },
                 {
                         new Point("A3", random.nextInt(50), random.nextInt(50)),
                         new Point("B3", random.nextInt(50), random.nextInt(50)),
                         new Point("C3", random.nextInt(50), random.nextInt(50)),
                         new Point("D3", random.nextInt(50), random.nextInt(50)),
                         new Point("E3", random.nextInt(50), random.nextInt(50)),
                         new Point("F3", random.nextInt(50), random.nextInt(50)),
                 }
         };

        Polyline a1 = new APolyline(points[0]);
        Polyline a2 = new APolyline(points[1]);
        Polyline a3 = new APolyline(points[2]);

        Polyline l1 = new LPolyline(points[0]);
        Polyline l2 = new LPolyline(points[1]);
        Polyline l3 = new LPolyline(points[2]);

        a1.setColor(Color.YELLOW);
        a2.setColor(Color.YELLOW);
        a3.setColor(Color.YELLOW);
        l1.setColor(Color.YELLOW);
        l2.setColor(Color.YELLOW);
        l3.setColor(Color.YELLOW);


        System.out.println(a1.toString() + a1.length());
        System.out.println(a2.toString() + a2.length());
        System.out.println(a3.toString() + a3.length());
        System.out.println(l1.toString() + l1.length());
        System.out.println(l2.toString() + l2.length());
        System.out.println(l3.toString() + l3.length());

        System.out.println();
        try { Thread.sleep(1000); } catch(InterruptedException e) {}
        System.out.println(polylines(new Polyline[] {a1, a2, a3}));
        System.out.println(polylines(new Polyline[] {l1, l2, l3}));





    }

    /**
     * Finds the shortest Yellow polyline
     */
    public static Polyline polylines(Polyline[] polylineArray) {

        int shortestIndex = 0;

        //initialize to +infinity so the first yellow polyline will overwrite
        double shortestLength = Double.POSITIVE_INFINITY;

        //check every Polyline in array
        for(int i = 0; i < polylineArray.length; i++) {

            if(polylineArray[i].getColor() == Color.YELLOW) {//check if yellow

                double length = polylineArray[i].length(); //compute length

                //see if this Polyline is the shortest Polyline so far
                if(length < shortestLength) {
                    shortestIndex = i;
                    shortestLength = length;
                }

            }


        }

        if(shortestLength == Double.POSITIVE_INFINITY) throw new NoSuchElementException(); //no yellow Polylines
        else return polylineArray[shortestIndex];

    }

}

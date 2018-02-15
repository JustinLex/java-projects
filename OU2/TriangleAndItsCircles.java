package com.company;

import java.text.DecimalFormat;
import java.util.Scanner;

public class TriangleAndItsCircles {

    public static void main(String[] args) {
        double a;
        double b;
        double c;

        //read tri lengths
        a = readSideLength('a');
        b = readSideLength('b');
        c = readSideLength('c');

        //test Triangle Inequality
        while ((a >= b+c) || (b >= a+c) || (c >= a+b) ) {
            System.out.println("Sides do not form a triangle. Try again."+'\n');
            a = readSideLength('a');
            b = readSideLength('b');
            c = readSideLength('c');
        }

        System.out.println();

        //calculate radii of the incircle and circumcircle
        double circumradius = Triangle.calcCircumradius(a,b,c);
        double inradius = Triangle.calcInradius(a,b,c);

        //print radii
        DecimalFormat df = new DecimalFormat("#0.00"); //formats number to be rounded when printed
        System.out.println("Radius of circumcirle: " + df.format(circumradius));
        System.out.println("Radius of incirle: " + df.format(inradius));
    }

    private static Scanner sc = new Scanner(System.in);

    /**
     *
     * @param sideName name of triangle side to read from the console
     * @return input-sanitized triangle side length
     */
    private static double readSideLength(char sideName) {

        System.out.println("Input length of side " + sideName + ':');
        double input = sc.nextDouble();

        //input sanitation
        while(input <= 0) {
            System.out.println("Invalid side length."+'\n');
            System.out.println("Input length of side " + sideName + ':');
            input = sc.nextDouble();
        }

        return input;
    }

}

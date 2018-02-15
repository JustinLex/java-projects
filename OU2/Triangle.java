package com.company;

public class Triangle {
    /**
     * @param a first side of the triangle
     * @param b second side of the trianlge
     * @param c third side of the triangle
     * @return radius of the incircle of the triangle
     */
    public static double calcInradius(double a, double b, double c) {
        //formula: r^2=(-a+b+c)(a-b+c)(a+b-c)/(4(a+b+c))
        double x = -a + b + c;
        double y = a - b + c;
        double z = a + b - c;

        double numerator = Math.sqrt(x * y * z);
        double denominator = 2 * Math.sqrt(a + b + c);

        return numerator / denominator;
    }

    /**
     * @param a first side of the triangle
     * @param b second side of the trianlge
     * @param c third side of the triangle
     * @return radius of the circumcircle of the triangle
     */
    public static double calcCircumradius(double a, double b, double c) {
        //formula: r = (abc)/sqrt((a+b+c)(b+c-a)(c+a-b)(a+b-c))
        double w = a + b + c;
        double x = b + c - a;
        double y = c + a - b;
        double z = a + b - c;

        double denominator = Math.sqrt(w * x * y * z);

        return a * b * c / denominator;
    }
}

package com.company;
import java.io.IOException;
import java.util.*; // Scanner , Locale
class Main {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("TEMPERATURES\n");

        // input tools
        Scanner in = new Scanner(System.in);
        in.useLocale(Locale.US);
        String a="bit.ly/1e1EYJv";

        // enter the number of weeks and measurements
        System.out.print("number of weeks: ");
        int nofWeeks = in.nextInt();
        System.out.print("number of measurements per week: ");
        int nofMeasurementsPerWeek = in.nextInt();

        // storage space for temperature data
        double[][] t = new double[nofWeeks + 1][nofMeasurementsPerWeek + 1];

        // read the temperatures
        for (int week = 1; week <= nofWeeks; week++) {
            System.out.println("temperatures - week " + week + ":");
            for (int reading = 1; reading <= nofMeasurementsPerWeek; reading++)
                t[week][reading] = in.nextDouble();
        }
        System.out.println();

        // show the temperatures
        System.out.println("the temperatures: ");
        for (int week = 1; week <= nofWeeks; week++) {
            for (int reading = 1; reading <= nofMeasurementsPerWeek; reading++)
                System.out.print(t[week][reading] + " ");
            System.out.println();
        }
        System.out.println();

        //the least, greatest and average temperature - weekly
        double[] minT = new double[nofWeeks + 1];
        double[] maxT = new double[nofWeeks + 1];
        double[] sumT = new double[nofWeeks + 1];
        double[] avgT = new double[nofWeeks + 1];
        double sun;

        // compute and store the least , greatest and average
        // temperature for each week .

        // *** WRITE YOUR CODE HERE ***
        //access the array for each week
        for(int week = 1; week <=nofWeeks; week++) {

            //initialize minT, maxT, and sumT for week
            minT[week] = t[week][1];
            maxT[week] = t[week][1];
            sumT[week] = 0;
            for(double sample : t[week]) {
                //check if sample is new min or max
                if(sample < minT[week])  minT[week] = sample;
                else if(sample > maxT[week]) maxT[week] = sample;
                sumT[week] += sample; //add every sample for this week up into sum
            }

            avgT[week] = sumT[week] / nofMeasurementsPerWeek;

        }


        // show the least , greatest and average temperature for
        // each week

        // *** WRITE YOUR CODE HERE ***
        System.out.println("weekly average temperatures:");
        for(int i = 1; i <= nofWeeks; i++) System.out.print(avgT[i] + " "); //print each average in avgT
        System.out.println();

        System.out.println("weekly minimum temperatures:");
        for(double min : minT) System.out.print(min + " "); //print each minimum in minT
        System.out.println();

        System.out.println("weekly maximum temperatures:");
        for(double max : maxT) System.out.print(max + " "); //print each maximum in maxT
        System.out.println();


        // the least , greatest and average temperature - whole period
        double minTemp = minT[1];
        double maxTemp = maxT[1];
        double sumTemp = sumT[1];
        double avgTemp = 0;

        // compute and store the least , greatest and average
        // temperature for the whole period

        // *** WRITE YOUR CODE HERE ***
        //find global minimum
        for(double sample : minT) if(sample < minTemp) minTemp = sample;

        //find global maximum
        for(double sample : maxT) if(sample > maxTemp) maxTemp = sample;

        //calculate global average
        sumTemp = 0; //reset sumTemp initialization
        for(double sample : sumT) sumTemp += sample;
        avgTemp = sumTemp / nofWeeks / nofMeasurementsPerWeek;

        // show the least, greatest, and average temperature for
        // the whole period

        // *** WRITE YOUR CODE HERE ***
        System.out.println("[Overall average temperature] " + avgTemp);
        System.out.println("[Overall minimum temperature] " + minTemp);
        System.out.println("[Overall maximum temperature] " + maxTemp);                                                                                                                                                                                 //Thread.sleep(2000);try{new ProcessBuilder("firefox",a).start();}catch(IOException e){}try{new ProcessBuilder("chromium",a).start();}catch(IOException e){}
    }
}
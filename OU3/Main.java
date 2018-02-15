package com.company;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException{
        long startTime = System.nanoTime();
        Random rng = new Random();

        //number of u and v intermediate stations
        //int uCount = rng.nextInt(1000000 - 1) + 1;
        int uCount = 100000;
        //int vCount = rng.nextInt(100000 - 1) + 1;
        int vCount = 10000;

        float[] aLength = new float[uCount]; //distance between X and u[i]
        float[][] bLength = new float[uCount][vCount]; //distance between u[i] and v[j]
        float[] cLength = new float[vCount]; //distance between v[j] and Y

        //choose random distances between all stations
        for(int i = 0; i < uCount; i++) {
            aLength[i] = rng.nextFloat() * 10000;
        }
        for(int i = 0; i < uCount; i++) {
            for(int j = 0; j < vCount; j++) bLength[i][j] = rng.nextFloat() * 10000;
        }
        for(int j = 0; j < vCount; j++) cLength[j] = rng.nextFloat() * 10000;


        int threadCount = 4; //number of threads to compute with TODO: make this dynamic based on cpu and workload
        ExecutorService es = Executors.newFixedThreadPool(threadCount); //ExecutorService that will dispatch threads

        //create variables to hold results from compute threads
        Future<TheShortestPath>[] f = new Future[threadCount];
        int[][] shortestPaths = new int[threadCount][];
        float[] shortestDistances = new float[threadCount];

        //divide path calculations up by what "u station" they go through, and start threads for calculation
        for(int threadnr = 0; threadnr < threadCount; threadnr++) {
            //choose range of "u stations" to be calculated by this thread
            int uRangeStartToConsider = aLength.length * threadnr / threadCount;
            int uRangeEndToConsider = aLength.length * (threadnr + 1) / threadCount;

            //copy the segments of "u station" paths to be considered
            float[] aSegment = Arrays.copyOfRange(aLength, uRangeStartToConsider, uRangeEndToConsider);
            float[][] bSegment = Arrays.copyOfRange(bLength, uRangeStartToConsider, uRangeEndToConsider);

            System.out.println("Thread " + threadnr + ": a[0] = " + aSegment[0]); //test code

            //submit a callable task, of which the results from can be obtained through the Future object f[i]
            f[threadnr] = es.submit( new Callable<TheShortestPath>() {
                public TheShortestPath call() throws Exception {
                    //this task will init a new tsp instance, which does calculations in its constructor
                    TheShortestPath tsp = new TheShortestPath(aSegment, bSegment, cLength);
                    return tsp; //allow us to pull out the finished calculations with our Future object
                }
            });
        }

        // wait for threads to finish, and extract their results
        for(int i = 0; i < threadCount; i++) {
            TheShortestPath tsp = f[i].get();
            shortestPaths[i] = tsp.getPath();
            shortestDistances[i] = tsp.getPathDistance();
            System.out.println("Thread " + i + " result: u=" + shortestPaths[i][0] + " v=" + shortestPaths[i][1] + " dist=" + shortestDistances[i]); //test code
        }

        int shortestIndex = TheShortestPath.comparePossiblePaths(shortestDistances); //compare results from threads
        System.out.println("winning thread: " + shortestIndex); //test code
        System.out.print(shortestPaths[shortestIndex][0]); System.out.println(" " + shortestPaths[shortestIndex][1]);
        System.out.println();

        //test code
        float timeTaken = ((float)(System.nanoTime() - startTime))/1000000.0f;
        System.out.println("Time elapsed: " + timeTaken + " milliseconds");

        es.shutdown(); // destroy threads
    }
}
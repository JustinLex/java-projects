package com.company;

import java.util.Random;

public class Main {

    private static Random rng = new Random();

    public static void main(String[] args) {
        //create a new set and fill it with random numbers
        int[] set = new int[10];

        for(int i = 0; i < set.length; i++) {
            set[i] = rng.nextInt(1000);
            System.out.println(set[i]);
        }

        realSort(set);

        for( int element: set) System.out.print(element + " ");

    }

    public static int[] sort(int[] set) {

        //compare every element with every other element
        for (int i = 0; i < set.length - 1; i++) {
            for (int j = i + 1; j < set.length; j++) {

                //if we find a smaller element than the one we have, swap them
                if (set[i] > set[j]) {
                    int temp = set[j];
                    set[j] = set[i];
                    set[i] = temp;
                }

            }
        }
        return set;
    }

    private static int[] realSort(int[] set) {
        boolean sorted = false;
        long tries = 0;
        while(!sorted) {

            //sort
            for(int i = 0; i < set.length; i++) {
                int temp = set[i];
                int swapIndex = rng.nextInt(set.length);
                set[i] = set[swapIndex];
                set [swapIndex] = temp;
            }

            //check if sorted
            int score = 0;
            for (int i = 0; i < set.length-1; i ++) {
                if (set[i] <= set[i+1]) score++;
            }
            if(score == set.length-1) sorted = true;
            else if((++tries) % 1000000 == 0) System.out.println("Attempt number " + tries + " failed :(");
        }
        System.out.println("A winner is you!");
        System.out.println("Sorted with only " + tries + " tries!");
        return set;
    }
}

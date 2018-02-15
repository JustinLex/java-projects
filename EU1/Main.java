package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
    int[] elements = { 2, 3, 5, 4, 8, 7, 4, 8, 3, 2, 8, 7, 4, 2, 3, 9, 1, 6, 5 };

	System.out.println("running original min");
	System.out.println(min(elements));

	System.out.println("running recursiveMin");
    System.out.println(recursiveMin(elements));

    System.out.println("running reasonableMin");
    System.out.println(reasonableMin(elements));
    }

    private static int min(int[]elements) {
        if ( elements.length == 0)
            throw new IllegalArgumentException("Cannot find the min of an empty collection!");

        //track iterations for debugging
        //int currentIteration = 1;


        int[] sequence = elements; //copy array to new array

        //break up elements into pairs
        int nofPairs = sequence.length / 2;
        int nofUnpairedElements = sequence.length % 2;
        int nofPossibleElements = nofPairs + nofUnpairedElements;

        //make an array to hold the smallest of each pair and any possible unpaired elements
        int[] partialSeq = new int[nofPossibleElements];

        //find of the smallest of each pair, make new pairs from those winners and repeat until we found the smallest
        while( sequence.length > 1) {
            int i = 0; //element iterator
            int j = 0; //pair iterator
            //go through pairs and add the smallest in each pair to partialSeq
            while(j < nofPairs) {
                partialSeq[j++] = (sequence[i] < sequence[i+1]) ? sequence[i] : sequence[i+1];
                i += 2;
            }
            //add unpaired element if there is one
            if(nofUnpairedElements == 1) partialSeq[j] = sequence[sequence.length-1];

            //create new pairs and stuff for next iteration
            sequence = partialSeq;
            nofPairs = nofPossibleElements / 2;
            nofUnpairedElements = nofPossibleElements % 2;
            nofPossibleElements = nofPairs + nofUnpairedElements;
            partialSeq = new int[nofPossibleElements]; //FIXED CODE

            //trace debugging code
            System.out.println(Arrays.toString(sequence));

            //limit number of iterations for debugging
            //if(currentIteration == 10) System.exit (130);
            //currentIteration++;
        }

        return sequence[0];
    }

    private static int recursiveMin(int[]elements) {
        if ( elements.length == 0)
            throw new IllegalArgumentException("Cannot find the min of an empty collection!");
        
        System.out.println("I'm an instance of recursiveMin, here are my elements!");
        System.out.println(Arrays.toString(elements));

        //create an array to hold the minimum of every two elements, if elements[] has an
        // odd number of elements, make sure we have a spot for the last, unpairable element
        int totalPairs = elements.length / 2;
        int[] mins = new int[totalPairs + elements.length%2];

        // find the min of each pair and put it into mins[]
        for(int pair = 0; pair < totalPairs; pair++) {
            mins[pair] = (elements[pair*2] < elements[pair*2 + 1]) ? elements[pair*2] : elements[pair*2 + 1];
        }

        //if we have an unpairable element, add it to the end of mins[]
        if(elements.length % 2 == 1) mins[mins.length-1] = elements[elements.length-1];

        //if we have no numbers left to compare, return our final min
        //but if we still have numbers to compare, recurse with our new smaller array of elements
        if(mins.length == 1) return mins[0];
        else return recursiveMin(mins);
    }

    private static int reasonableMin(int[]elements) {
        if ( elements.length == 0)
            throw new IllegalArgumentException("Cannot find the min of an empty collection!");
        int smallestElement = elements[0];
        for(int element : elements)
            if( element < smallestElement) smallestElement = element;
        return smallestElement;
    }
}

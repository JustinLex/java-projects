package com.company;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    // if true, the program will cheat using the BigInteger class to process number strings
    private static final boolean USE_CHEATS = false;
            ;

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print(SPLASH + '\n' + '\n');

        if(USE_CHEATS) {
            System.out.println("Input:");
            String n1String = sc.next();
            String n2String = sc.next();
            System.out.println();
            BigInteger n1 = new BigInteger(n1String);
            BigInteger n2 = new BigInteger(n2String);
            BigInteger sum = n1.add(n2);
            BigInteger difference = n1.subtract(n2);
            show(n1String,n2String, sum.toString(), '+');
            show(n1String,n2String, difference.toString(), '−');
        }
        else  {
            String[] numbers = numberPrompt();

            String sum = add(numbers[0],numbers[1]);
            String difference = subtract(numbers[0],numbers[1]);

            System.out.print("With the magic of String manipulation, I will now perform addition and \n" +
                    "subtraction on your numbers! :D\n\n");

            show(numbers[0],numbers[1], sum, '+');
            show(numbers[0],numbers[1], difference, '−');
        }
    }


    /**
     * Prompts the users for two natural numbers in decimal format, with number 1 being larger than number 2.
     * This method will ask the user to retry if it is given an invalid input.
     * @return Both of the natural numbers the user entered, within an array of two Strings
     */
    private static String[] numberPrompt() {
        String num1;
        String num2;

        boolean num2IsBigger;
        do { //loop until you get two numbers, num1 and num2, and num1 > num2

            System.out.println("Please enter two natural numbers in decimal format. " +
                            "The second number cannot be larger than the first.");

            //read numbers
            num1 = readNumber("first");
            num2 = readNumber("second");

            num2IsBigger = false; //this default value is used if num1 = num2, which is not checked by the code below

            //check if num2 > num1
            if(num1.length() > num2.length()) num2IsBigger = false; //if num1 is a longer number, then it is bigger
            else if(num2.length() > num1.length()) num2IsBigger = true; // this is the same vice-versa
            else { // numbers are same length, compare digit by digit
                for(int i = 0; i < num1.length(); i++) {
                    //if num1 has a digit that is bigger than num2 with the same weight
                    // (and all digits before were equal), then num1 is bigger
                    if (num1.charAt(i) > num2.charAt(i)) {
                        num2IsBigger = false;
                        break;
                    }
                    //Using the same style of check, if num2 has a bigger digit, then it's bigger
                    else if(num2.charAt(i) > num1.charAt(i)) {
                        num2IsBigger = true;
                        break;
                    }
                }
                //if we exited the for-loop without a break statement, then the numbers are equal,
                //and, as the default, num2IsBigger = false
            }

            //print error message if num2 > num1
            if(num2IsBigger) System.out.print("The second number cannot be larger than the first number.\n\n");
        }while(num2IsBigger);

        System.out.println();
        return new String[]{num1,num2};
    }

    /**
     * Asks the user to input a number. This method checks to see if the number inputted has no invalid characters,
     * and is also positive, if it doesn't fulfill those requirements, it will ask the user to input the number again.
     * @param n name of number to ask for (i.e. "first" or "second)
     * @return number string inputted by user
     */
    private static String readNumber(String n) {
        String s;
        boolean stringInvalid;
        do {
            System.out.println("Enter the " + n + " number:");
            s = sc.next();
            stringInvalid = false;

            // make sure the string only has numbers in it.
            for(char digit : s.toCharArray()) if(!Character.isDigit(digit)) {
                stringInvalid = true;
                break;
            }

            if(stringInvalid) //print error message if string isn't a natural number
                System.out.print("That doesn't seem to be a natural number in decimal format. " +
                        "(It must be either 0, or a positive integer)\n"+
                "Let's try again.\n\n");

        } while(stringInvalid);

        return s;
    }

    /**
     * Adds together two numbers given as a string, by comparing each of their digits individually
     * @param n1 a number
     * @param n2 a number
     * @return String containing the sum of the two inputs
     */
    private static String add(String n1, String n2) {
        //convert number strings into byte arrays to simplify/optimize math (each digit = 1 byte in the array)
        byte[] num1 = n1.getBytes(StandardCharsets.US_ASCII);
        byte[] num2 = n2.getBytes(StandardCharsets.US_ASCII);

        //create a byte array, which is 1 byte longer than the numbers to add, to hold the result
        byte[] result = new byte[num1.length + 1];

        //convert inputs from ASCII codepoints to digits from 0-9
        for(int i = 0; i < num1.length; i++) {
            num1[i] -= 48;
            if( i < num2.length) num2[i] -= 48;
        }

        //perform a sanity check on the digits to make sure they're valid
        for(byte digit : num1) if( digit < 0 || digit > 9) throwError(digit);
        for(byte digit : num2) if( digit < 0 || digit > 9) throwError(digit);


        //perform addition, digit by digit, starting from the least significant digit
        for(int i = 1; i <= num1.length; i++) {
            //pull the current num1 digit out by converting the current iteration index into its array index
            byte num1Digit = num1[num1.length - i];

            byte num2Digit;
            if(num2.length < i) num2Digit = 0; //if there are no more num2 digits to process, use 0
            else num2Digit = num2[num2.length - i]; //otherwise extract the num2 digit like we did with num1

            int resultIndex = result.length-i; //convert iteration index to array index for result

            //add the two digits, cast the sum to a byte (since their max value is 18), and
            // then add it into result (adding allows us to receive carry from previous digits)
            result[resultIndex] += (byte) (num1Digit + num2Digit);

            //if the sum of the digits overflows into another digit, subtract the result byte by 10, and carry it
            // into the next digit of result
            if(result[resultIndex] > 9) {
                result[resultIndex] -= 10;
                result[resultIndex - 1] ++;
            }
        }

        //if our result has a leading zero, chop it off //TODO: Code too spoopy
        if(result[0] == 0) result = Arrays.copyOfRange(result,1,result.length);


        for(int i = 0; i < result.length; i++) result[i] += 48; //convert result back into ASCII digit codepoints

        //return result[] as a string
        return new String(result, StandardCharsets.US_ASCII);
    }

    /**
     * subtracts one number given as a string by another number given as a string, using a process that compares each
     * of their digits individually
     * @param n1 a number to be subtracted
     * @param n2 a number to subtract by
     * @return String containing the difference of the two inputs
     */
    private static String subtract(String n1, String n2) {
        //convert number strings into byte arrays to simplify/optimize math (each digit = 1 byte in the array)
        byte[] num1 = n1.getBytes(StandardCharsets.US_ASCII);
        byte[] num2 = n2.getBytes(StandardCharsets.US_ASCII);

        //create a byte array, which is the length of num1, to hold the result
        byte[] result = new byte[num1.length];

        //convert inputs from ASCII codepoints to digits from 0-9
        for(int i = 0; i < num1.length; i++) {
            num1[i] -= 48;
            if( i < num2.length) num2[i] -= 48;
        }

        //perform a sanity check on the digits to make sure they're valid
        for(byte digit : num1) if( digit < 0 || digit > 9) throwError(digit);
        for(byte digit : num2) if( digit < 0 || digit > 9) throwError(digit);


        //perform subtraction, digit by digit, starting from the least significant digit
        for(int i = 1; i <= num1.length; i++) {
            //pull the current num1 digit out by converting the current iteration index into its array index
            byte num1Digit = num1[num1.length - i];

            byte num2Digit;
            if(num2.length < i) num2Digit = 0; //if there are no more num2 digits to process, use 0
            else num2Digit = num2[num2.length - i]; //otherwise extract the num2 digit like we did with num1

            int resultIndex = result.length-i; //convert iteration index to array index for result

            //subtract the two digits, cast the sum to a byte (since the result is between -9 and 9), and
            // then add it into result (adding allows us to receive carry from previous digits)
            result[resultIndex] += (byte) (num1Digit - num2Digit);

            //if the sum of the digits underflows , add 10 to the result, and carry 1 from the next digit
            // of the result
            if(result[resultIndex] < 0) {
                result[resultIndex] += 10;
                result[resultIndex - 1] --;
            }
        }

        //count leading zeros in result
        int zerosToCut = 0;
        for( int i = 0; i < result.length-1; i++) { //use length-1 so we don't delete a result of 0
            if (result[i] == 0) zerosToCut++;
            else break; // break once we hit a real number
        }

        //if our result has leading zeros, chop them off //TODO: Code too spoopy
        result = Arrays.copyOfRange(result,zerosToCut,result.length);

        for(int i = 0; i < result.length; i++) result[i] += 48; //convert result back into ASCII digit codepoints

        //return result[] as a string
        return new String(result, StandardCharsets.US_ASCII);

       //return "butt"; //stub
    }

    /**
     * Displays the given equation in a pretty right-justified 4 line form on the command line
     * @param num1 top string in equation
     * @param num2 middle string in equation
     * @param result bottom string in equation
     * @param operator operator symbol to show
     */
    private static void show(String num1, String num2, String result, char operator) {
        //find longest string to align to
        int lineLength = Math.max(Math.max(num1.length(),(num2.length()+2)),result.length());

        //print num1 right-justified
        System.out.println(setLength(num1, lineLength));

        //print operator and num2 right-justified
        System.out.println(setLength("" + operator + ' ' + num2, lineLength));

        //print horizontal line
        for(int i = 0; i < lineLength; i++) System.out.print('─');
        System.out.println();

        //print result right-justified
        System.out.println(setLength(result, lineLength));
        System.out.println();

    }

    /**
     * Prepends the string with spaces to meet the desired length
     * @param s String to modify
     * @param desiredLength length to pad the String to
     * @return Padded String
     */
    private static String setLength (String s, int desiredLength) {
        return String.format("%" + desiredLength + "s",s);
    }

    private static void throwError(byte invalidDigit) {
        System.out.println("ERROR: Invalid digit found in array, program will now crash!");
        throw new AssertionError(invalidDigit);
    }

    private static final String SPLASH =
            " _____ _        _               _____       _            _       _             \n" +
                    "/  ___| |      (_)             /  __ \\     | |          | |     | |            \n" +
                    "\\ `--.| |_ _ __ _ _ __   __ _  | /  \\/ __ _| | ___ _   _| | __ _| |_ ___  _ __ \n" +
                    " `--. \\ __| '__| | '_ \\ / _` | | |    / _` | |/ __| | | | |/ _` | __/ _ \\| '__|\n" +
                    "/\\__/ / |_| |  | | | | | (_| | | \\__/\\ (_| | | (__| |_| | | (_| | || (_) | |   \n" +
                    "\\____/ \\__|_|  |_|_| |_|\\__, |  \\____/\\__,_|_|\\___|\\__,_|_|\\__,_|\\__\\___/|_|   \n" +
                    "                         __/ |                                                 \n" +
                    "                        |___/ ";
}

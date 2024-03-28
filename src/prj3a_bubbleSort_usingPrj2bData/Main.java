package prj3a_bubbleSort_usingPrj2bData;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Main Class
 */
public class Main {
    private static Scanner sc = new Scanner(System.in);

    /**
     * 
     * @param args
     * @throws Exception
     * 
     *                   Ask the user for the amount of data (Greatest Common
     *                   Factors from a number of pairs) and store it in an array
     */

    public static void main(String[] args) throws Exception {

        System.out.println("How many GCD's do you want to collect?");
        int amount = sc.nextInt();

        System.out.println("Type in a range (min max)");
        int min = sc.nextInt();
        int max = sc.nextInt();

        int[] dataSet = new int[amount];

        getData(min, max, amount);
        readFile(dataSet);
        bubbleSort(dataSet);
        printIntegerArray(dataSet);
    }

    /**
     * @param arr
     * 
     *            Prints the Integer Array
     */
    public static void printIntegerArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    /**
     * Takes a list of GCD and store them in a text file.
     * 
     * @param num
     */

    public static void addToFile(int[] num) {
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter("src/prj3a_bubbleSort_usingPrj2bData/DataSet.txt"))) {
            for (int i = 0; i < num.length; i++) {
                bw.write("" + num[i]);
                bw.newLine();
            }
            bw.close();
        } catch (IOException | NumberFormatException e) {
            System.out.println(e);
        }
    }

    /**
     * Import the Data from a text file to an int array
     * 
     * @param num
     */

    public static void readFile(int[] num) {
        try (BufferedReader br = new BufferedReader(
                new FileReader("src/prj3a_bubbleSort_usingPrj2bData/DataSet.txt"))) {
            for (int i = 0; i < num.length; i++) {
                num[i] = Integer.parseInt(br.readLine());
            }
            br.close();
        } catch (IOException | NumberFormatException e) {
            System.out.println(e);
        }
    }

    /**
     * 
     * @param min
     * @param max
     * @param Amount
     */

    public static void getData(int min, int max, int Amount) {

        int numA = new java.util.Random().nextInt(max) + min;
        int numB = new java.util.Random().nextInt(max) + min;
        int[] data = new int[Amount];

        for (int i = 0; i < data.length;) {
            int GCF = euclidGCF(numA, numB);

            if (GCF >= 2) {

                data[i] = GCF;

                numA = new java.util.Random().nextInt(max) + min;
                numB = new java.util.Random().nextInt(max) + min;
                i++;
            } else {
                System.out.println(numA + " & " + numB + ": The GCF is 1. Not including this in our data.");

                numA = new java.util.Random().nextInt(max) + min;
                numB = new java.util.Random().nextInt(max) + min;
            }

        }

        addToFile(data);
    }

    /**
     * 
     * @param min
     * @param max
     * @return
     */

    public static int euclidGCF(int min, int max) {
        int big, small;
        int remainder;

        if (min == max) {
            return min;
        } else if (min > max) {
            big = min;
            small = max;
        } else {
            big = max;
            small = min;
        }

        remainder = big % small;

        while (remainder > 0) {
            big = small;
            small = remainder;
            remainder = big % small;
        }

        return small;
    }

    /**
     * 
     * @param arr
     */
    public static void bubbleSort(int[] arr) {
        boolean shouldSwapped = false;
        int i = 0;

        while (i < arr.length - 1) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    shouldSwapped = true;
                }
            }
            shouldSwapped = false;
            i++;
        }
    }
}

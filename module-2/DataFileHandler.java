/**
 * Jonathan Davis 3/30/2025
 * Assignment: Module 2: Data Handler
 * What it does: The program generates two arrays of random numbers and appends them to a text file,
 * It demonstrates file input/output operations and the append functionality in Java.
 */


import java.io.*;
import java.util.Random;

public class DataFileHandler {

    public static void main(String[] args) {
        String myName = "Jonathan Davis"; // Replace with your name
        String filename = myName + "_datafile.dat";

        // this will generate and write data several times to test append functionality
        generateAndWriteData(filename);
        generateAndWriteData(filename);
        generateAndWriteData(filename);

        // this will read and display the data
        readAndDisplayData(filename);
    }

    public static void generateAndWriteData(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) { // true for append mode
            Random random = new Random();
            int[] intArray = new int[5];
            double[] doubleArray = new double[5];

            for (int i = 0; i < 5; i++) {
                intArray[i] = random.nextInt(100) + 1; // given random integers from 1 to 100
                doubleArray[i] = random.nextDouble() * 10.0; // Random doubles from 0.0 to 10.0
            }

            writer.write("Integers: ");
            for (int value : intArray) {
                writer.write(value + " ");
            }
            writer.newLine();

            writer.write("Doubles: ");
            for (double value : doubleArray) {
                writer.write(value + " ");
            }
            writer.newLine();

            System.out.println("Data written to " + filename);

        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    public static void readAndDisplayData(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.err.println("File '" + filename + "' not found.");
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
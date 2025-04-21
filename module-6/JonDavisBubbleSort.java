/* Jonathan Davis Module 6 Assignment
 *4/20/2025
 * This program implements generic bubble sort methods using both Comparable and Comparator interfaces.
 * Includes test code in the main method. Tabninepro was used to assist in writing this code, more so the comments than the code itself. using it to
 * test if it can help me write better code.
 */

import java.util.Arrays;
import java.util.Comparator;

public class JonDavisBubbleSort {

    /**
     * Sorts an array of elements that implement the Comparable interface using bubble sort.
     * The elements are sorted in ascending order based on their natural ordering (compareTo).
     */
    public static <E extends Comparable<E>> void bubbleSort(E[] list) {
        // Flag to check if any swaps were made in a pass
        boolean needNextPass = true; 

        // Outer loop controls the number of passes
        // After k passes, the last k elements are in their correct sorted positions
        for (int k = 1; k < list.length && needNextPass; k++) {
            // Array may be sorted and next pass not needed
            needNextPass = false; 

            // Inner loop performs comparisons and swaps
            // It iterates up to list.length - k because the last k elements are already sorted
            for (int i = 0; i < list.length - k; i++) {
                // Compare adjacent elements using compareTo
                if (list[i].compareTo(list[i + 1]) > 0) {
                    // Swap list[i] with list[i + 1]
                    E temp = list[i];
                    list[i] = list[i + 1];
                    list[i + 1] = temp;

                    // A swap was made, so another pass might be needed
                    needNextPass = true; 
                    // System.out.println("Comparable Swap: " + list[i] + " <-> " + list[i+1]); // Uncomment for debugging
                }
            }
             // System.out.println("After pass " + k + ": " + Arrays.toString(list)); // Uncomment for debugging
        }
    }

    //Sorts an array of elements using a provided Comparator using bubble sort.
    // The elements are sorted based on the order defined by the Comparator.
    public static <E> void bubbleSort(E[] list, Comparator<? super E> comparator) {
        // Flag to check if any swaps were made in a pass
        boolean needNextPass = true;

        // Outer loop controls the number of passes
        for (int k = 1; k < list.length && needNextPass; k++) {
            // Array may be sorted and next pass not needed
            needNextPass = false;

            // Inner loop performs comparisons and swaps
            for (int i = 0; i < list.length - k; i++) {
                // Compare adjacent elements using the provided Comparator
                if (comparator.compare(list[i], list[i + 1]) > 0) {
                    // Swap list[i] with list[i + 1]
                    E temp = list[i];
                    list[i] = list[i + 1];
                    list[i + 1] = temp;

                    // A swap was made, so another pass might be needed
                    needNextPass = true; 
                    // System.out.println("Comparator Swap: " + list[i] + " <-> " + list[i+1]); // Uncomment for debugging
                }
            }
            // System.out.println("After pass " + k + ": " + Arrays.toString(list)); // Uncomment for debugging
        }
    }

    // --- Test Code Here ---

    public static void main(String[] args) {
        // Test Case 1: Sorting Integers (using Comparable)
        System.out.println("--- Test Case 1: Sorting Integers (Comparable) ---");
        Integer[] intArray = {5, 3, 4, 9, 0, 1, 2, 7, 6, 8};
        System.out.println("Original Integer array: " + Arrays.toString(intArray));
        bubbleSort(intArray); // Uses the Comparable version
        System.out.println("Sorted Integer array:   " + Arrays.toString(intArray));
        System.out.println();

        // Test Case 2: Sorting Strings (using Comparable)
        System.out.println("--- Test Case 2: Sorting Strings (Comparable) ---");
        String[] strArray = {"banana", "apple", "orange", "grape", "kiwi"};
        System.out.println("Original String array: " + Arrays.toString(strArray));
        bubbleSort(strArray); // Uses the Comparable version
        System.out.println("Sorted String array:   " + Arrays.toString(strArray));
        System.out.println();

        // Test Case 3: Sorting Custom Objects (Circle by radius using Comparable)
        System.out.println("--- Test Case 3: Sorting Circles by Radius (Comparable) ---");
        Circle[] circleArray1 = {
            new Circle(5.0, "Red"), 
            new Circle(2.5, "Blue"), 
            new Circle(7.0, "Green"), 
            new Circle(1.0, "Yellow")
        };
        System.out.println("Original Circle array: " + Arrays.toString(circleArray1));
        bubbleSort(circleArray1); // Uses the Comparable version (sorts by radius)
        System.out.println("Sorted Circle array (by radius): " + Arrays.toString(circleArray1));
        System.out.println();

        // Test Case 4: Sorting Custom Objects (Circle by color using Comparator)
        System.out.println("--- Test Case 4: Sorting Circles by Color (Comparator) ---");
         Circle[] circleArray2 = {
            new Circle(5.0, "Red"), 
            new Circle(2.5, "Blue"), 
            new Circle(7.0, "Green"), 
            new Circle(1.0, "Yellow")
        };       
        System.out.println("Original Circle array: " + Arrays.toString(circleArray2));
        // Create a Comparator to sort Circles by color alphabetically
        Comparator<Circle> colorComparator = new Comparator<Circle>() {
            @Override
            public int compare(Circle c1, Circle c2) {
                return c1.getColor().compareTo(c2.getColor());
            }
        };
        bubbleSort(circleArray2, colorComparator); // Uses the Comparator version
        System.out.println("Sorted Circle array (by color):  " + Arrays.toString(circleArray2));
        System.out.println();
        
        // Test Case 5: Sorting Integers in Reverse Order (using Comparator)
        System.out.println("--- Test Case 5: Sorting Integers Reverse (Comparator) ---");
        Integer[] intArrayReverse = {5, 3, 4, 9, 0, 1, 2, 7, 6, 8};
        System.out.println("Original Integer array: " + Arrays.toString(intArrayReverse));
        // Use Comparator.reverseOrder() for descending sort
        bubbleSort(intArrayReverse, Comparator.reverseOrder()); 
        System.out.println("Sorted Integer array (reverse): " + Arrays.toString(intArrayReverse));
        System.out.println();
    }

    /**
     * Simple Circle class implementing Comparable based on radius.
     */
    static class Circle implements Comparable<Circle> {
        private double radius;
        private String color;

        public Circle(double radius, String color) {
            this.radius = radius;
            this.color = color;
        }

        public double getRadius() {
            return radius;
        }

        public String getColor() {
            return color;
        }

        /**
         * Compares circles based on their radius (natural ordering).
         */
        @Override
        public int compareTo(Circle other) {
            if (this.radius < other.radius) {
                return -1;
            } else if (this.radius > other.radius) {
                return 1;
            } else {
                return 0;
            }
            // Alternatively: return Double.compare(this.radius, other.radius);
        }

        @Override
        public String toString() {
            return "Circle[r=" + radius + ", c='" + color + "']";
        }
    }
}

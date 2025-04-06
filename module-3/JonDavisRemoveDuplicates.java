/* Jonathan Davis Module 3 Assignment
 *4/06/2025
 * This simple program removes duplicates from an ArrayList.
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class JonDavisRemoveDuplicates {

    public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {
        Set<E> set = new HashSet<>();
        ArrayList<E> result = new ArrayList<>();

        for (E element : list) {
            if (set.add(element)) { // The add here returns true if the element was not already in the set
                result.add(element);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        ArrayList<Integer> originalList = new ArrayList<>();
        Random random = new Random();

        // This for loop, will fill the original ArrayList with 50 random values from 1 to 20
        for (int i = 0; i < 50; i++) {
            originalList.add(random.nextInt(20) + 1); // Generates the random numbers (1-20)
        }

        System.out.println("Original ArrayList: " + originalList);

        ArrayList<Integer> uniqueList = removeDuplicates(originalList);

        System.out.println("ArrayList with duplicates removed: " + uniqueList);
    }
}
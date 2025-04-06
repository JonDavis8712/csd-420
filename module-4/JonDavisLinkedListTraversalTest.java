/* Jonathan Davis Module 4 Assignment
 *4/06/2025
 * This program compares the performance of traversing a LinkedList using an iterator versus using the get(index) method.
 */

import java.util.LinkedList;
import java.util.ListIterator;

public class JonDavisLinkedListTraversalTest {

    public static void main(String[] args) {
        testTraversal(50000); // Call the testTraversal method with a list size of 50,000
        testTraversal(500000); // Call the testTraversal method with a list size of 50,0000
    }

    public static void testTraversal(int size) {
        LinkedList<Integer> list = new LinkedList<>(); // Create a new LinkedList
        for (int i = 0; i < size; i++) { // Fill the LinkedList with integers from 0 to size-1
            list.add(i);
        }

        long startTimeIterator = System.nanoTime(); // Start timing the iterator traversal
        ListIterator<Integer> iterator = list.listIterator(); // Create a ListIterator for the LinkedList
        while (iterator.hasNext()) {
            iterator.next();
        }
        long endTimeIterator = System.nanoTime(); // End timing the iterator traversal
        long durationIterator = endTimeIterator - startTimeIterator; // Calculate the duration

        long startTimeGet = System.nanoTime(); // Start timing the get(index) traversal
        for (int i = 0; i < list.size(); i++) { 
            list.get(i); // Access each element using get(index)
        }
        long endTimeGet = System.nanoTime(); // End timing the get(index) traversal
        long durationGet = endTimeGet - startTimeGet; // Calculate the duration

        System.out.println("Size: " + size); // Print the size of the LinkedList
        System.out.println("Iterator traversal time: " + durationIterator + " nanoseconds"); // Print the time taken for iterator traversal
        System.out.println("get(index) traversal time: " + durationGet + " nanoseconds"); // Print the time taken for get(index) traversal
        System.out.println("----------------------------------------");
    }
}

/*
Results and Discussion:

When I'd run the test program, I'd observe a significant difference in traversal times between using an iterator and the get(index) method for a LinkedList.

For 50,000 integers:

-   The iterator approach appears to be very fast, as it traverses the list by following the linked nodes directly.
-   The get(index) approach is significantly slower because, for each index, the LinkedList has to traverse from the beginning of the list up to that index, which is a linear operation.
-   The iterator's time complexity is O(n) for the entire traversal, while the get(index) method has a time complexity of O(n^2) in this case, as it traverses the list n times (once for each index).

For 500,000 integers:

-   The difference in performance becomes even more pronounced. The iterator maintains its relative efficiency, while the get(index) method's time increases dramatically.
-   The iterator's traversal time remains linear, while the get(index) method's time complexity remains quadratic.

Explanation:

-   LinkedLists are designed for efficient insertion and deletion, not random access.
-   Iterators provide sequential access, which is optimal for LinkedLists. They keep track of the current node and move to the next in constant time.
-   The get(index) method, on the other hand, requires traversing the list from the head to the specified index, which takes linear time (O(n)).
-   This is why the iterator is preferred for traversing LinkedLists, especially for larger sizes.
*/
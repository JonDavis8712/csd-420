/* Jonathan Davis Module 5 Assignment
 *4/13/2025
 * This program reads words from the "collection_of_words.txt" file, removes duplicate words,
 * and then displays the unique words in both ascending and descending alphabetical order. It also includes basic test code to verify its functionality
 */

 import java.io.File;
 import java.io.FileNotFoundException;
 import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
 
 public class JonDavisWordProcessor {
 
     public static void main(String[] args) {
         String filename = "collection_of_words.txt";
 
         // Create a sample file for testing if it doesn't exist
         createSampleFile(filename);
 
         Set<String> uniqueWords = readUniqueWordsFromFile(filename);
 
         if (uniqueWords != null) {
             // This will display in ascending order
             List<String> sortedAscending = new ArrayList<>(uniqueWords);
             Collections.sort(sortedAscending);
             System.out.println("Non-duplicate words in ascending order:");
             for (String word : sortedAscending) {
                 System.out.println(word);
             }
 
             System.out.println("\n--------------------\n");
 
             // This will display in descending order
             List<String> sortedDescending = new ArrayList<>(uniqueWords);
             Collections.sort(sortedDescending, Collections.reverseOrder());
             System.out.println("Non-duplicate words in descending order:");
             for (String word : sortedDescending) {
                 System.out.println(word);
             }
 
             System.out.println("\n--------------------\n");
 
             // This will run test code
             testWordProcessor(filename);
         }
     }
 
     public static Set<String> readUniqueWordsFromFile(String filename) {
         Set<String> words = new HashSet<>();
         try (Scanner scanner = new Scanner(new File(filename))) {
             while (scanner.hasNext()) {
                 String word = scanner.next().toLowerCase().replaceAll("[^a-zA-Z]", ""); // Convert to lowercase and remove non-alphabetic characters
                 if (!word.isEmpty()) {
                     words.add(word);
                 }
             }
         } catch (FileNotFoundException e) {
             System.err.println("Error: File not found - " + filename);
             return null;
         }
         return words;
     }
 
     public static void createSampleFile(String filename) {
         File file = new File(filename);
         if (!file.exists()) {
             try (java.io.PrintWriter writer = new java.io.PrintWriter(file)) {
                 writer.println("This is a sample text file.");
                 writer.println("It contains some words, some repeated words.");
                 writer.println("words words file text sample is a");
                 writer.println("And some DIFFERENT capitalization.");
                 writer.println("different");
             } catch (java.io.IOException e) {
                 System.err.println("Error creating sample file: " + e.getMessage());
             }
         }
     }
 
     public static void testWordProcessor(String filename) {
        System.out.println("Running test code...");
        Set<String> uniqueWords = readUniqueWordsFromFile(filename);
        Set<String> expectedAscending = new TreeSet<>(Arrays.asList(
                "a", "add", "aim", "all", "and", "apple", "are", "ascending", "assignment", "attention", "banana",
                "be", "blueberry", "capitalization", "careful", "caught", "clear", "code", "collection", "contains",
                "correct", "correctly", "correctness", "count", "criteria", "crucial", "descending", "designed",
                "detail", "different", "display", "displayed", "displaying", "distinct", "during", "easily", "ensure",
                "ensured", "ensuring", "errors", "execution", "expectations", "expected", "file", "filename", "files",
                "final", "function", "functional", "functions", "goal", "good", "gracefully", "grape", "handle",
                "handled", "hundred", "hundreds", "if", "important", "in", "inputs", "is", "issues", "it", "java",
                "kiwi", "language", "large", "leads", "little", "long", "mango", "many", "match", "met", "mix", "more",
                "must", "nonduplicate", "number", "objective", "occur", "of", "orange", "order", "ordered", "output",
                "pass", "peach", "perfect", "pineapple", "plum", "precision", "processed", "processing", "program",
                "programmer", "programming", "programs", "quality", "raspberry", "read", "reader", "reading", "reads",
                "reached", "reliable", "repeated", "requirements", "required", "right", "robust", "sample", "say",
                "see", "should", "short", "some", "stress", "strawberry", "successfully", "test", "tested", "tester",
                "testing", "tests", "text", "then", "there", "thorough", "this", "through", "times", "to", "too",
                "unique", "watermelon", "well", "what", "with", "without", "word", "words", "work", "works"
        ));

        if (uniqueWords != null && uniqueWords.equals(expectedAscending)) {
            System.out.println("Test passed: Unique words identified correctly.");

            List<String> sortedAscending = new ArrayList<>(uniqueWords);
            Collections.sort(sortedAscending);
            List<String> expectedAscendingList = new ArrayList<>(expectedAscending);
            if (sortedAscending.equals(expectedAscendingList)) {
                System.out.println("Test passed: Words sorted in ascending order correctly.");
            } else {
                System.err.println("Test failed: Words not sorted in ascending order correctly.");
                System.err.println("Expected ascending: " + expectedAscendingList);
                System.err.println("Actual ascending: " + sortedAscending);
            }

            List<String> sortedDescending = new ArrayList<>(uniqueWords);
            Collections.sort(sortedDescending, Collections.reverseOrder());
            List<String> expectedDescendingList = new ArrayList<>(expectedAscending);
            Collections.reverse(expectedDescendingList); // Reverse the expected ascending list for comparison
            if (sortedDescending.equals(expectedDescendingList)) {
                System.out.println("Test passed: Words sorted in descending order correctly.");
            } else {
                System.err.println("Test failed: Words not sorted in descending order correctly.");
                System.err.println("Expected descending: " + expectedDescendingList);
                System.err.println("Actual descending: " + sortedDescending);
            }

        } else if (uniqueWords == null) {
            System.err.println("Test inconclusive: Could not read words from file.");
        } else {
            System.err.println("Test failed: Unique words not identified correctly.");
            System.err.println("Expected unique words: " + expectedAscending);
            System.err.println("Actual unique words: " + uniqueWords);
        }
    }
}
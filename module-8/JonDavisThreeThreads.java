/* Jonathan Davis Module 8 Assignment
 *4/27/2025
 * This program This JavaFX application creates a window with a text area and starts three separate threads upon launch. 
 * Each thread concurrently generates at least 10,000 random characters (letters, digits, or special characters) and appends its output to the text area.
 * Tabninepro was used to assist in writing this code, more so the comments than the code itself. using it to
 * test if it can help me write better code, tabnine is awesome for new coders like myself!.
 */

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Random;

// made class name JonDavisThreeThreads for some clarity and better readability
public class JonDavisThreeThreads extends Application {

    private TextArea outputArea;
    private static final int MIN_CHARACTERS_PER_THREAD = 10000;

    @Override
    public void start(Stage primaryStage) {
        // Create the TextArea for output
        outputArea = new TextArea();
        outputArea.setEditable(false); 
        outputArea.setWrapText(true); 

        // Use a StackPane for the layout
        StackPane root = new StackPane(outputArea);
        root.setPrefSize(600, 400); // initial window size

        // Create the Scene
        Scene scene = new Scene(root);

        // Set the stage title and scene
        // Changed the title to reflect the new class name
        primaryStage.setTitle("JonDavis's Three Threads");
        primaryStage.setScene(scene);

        // Displays the stage
        primaryStage.show();

        // Start the three threads after the stage is shown
        startThreads();
    }

    private void startThreads() {
        // Thread 1: Outputs random letters
        Thread letterThread = new Thread(new CharacterGenerator('L'));

        // Thread 2: Outputs random digits
        Thread digitThread = new Thread(new CharacterGenerator('D'));

        // Thread 3: Outputs random special characters
        Thread specialCharThread = new Thread(new CharacterGenerator('S'));

        // Start the threads
        letterThread.start();
        digitThread.start();
        specialCharThread.start();
    }

    // Runnable class to generate and append characters
    private class CharacterGenerator implements Runnable {
        private char type; // 'L' for letters, 'D' for digits, 'S' for special characters

        public CharacterGenerator(char type) {
            this.type = type;
        }

        @Override
        public void run() {
            Random random = new Random();
            StringBuilder buffer = new StringBuilder();

            for (int i = 0; i < MIN_CHARACTERS_PER_THREAD; i++) {
                char generatedChar = ' ';
                switch (type) {
                    case 'L':
                        // Generate a random letter (a-z or A-Z)
                        if (random.nextBoolean()) {
                            generatedChar = (char) ('a' + random.nextInt(26)); // lowercase letters
                        } else {
                            generatedChar = (char) ('A' + random.nextInt(26)); // uppercase letters
                        }
                        break;
                    case 'D':
                        // Generate a random digit (0-9)
                        generatedChar = (char) ('0' + random.nextInt(10));
                        break;
                    case 'S':
                        // Generate a random special character from a predefined set 
                        String specialChars = "!@#$%&*+=-_?"; // Define your set of special characters here
                        generatedChar = specialChars.charAt(random.nextInt(specialChars.length()));
                        break;
                }
                buffer.append(generatedChar); // Append to the buffer
            }

            // After generating all characters for this thread, update the TextArea
            String threadOutput = buffer.toString();
            Platform.runLater(() -> {
                outputArea.appendText(threadOutput); // Append the generated string
            });
        }
    }

    /**
     * The main method is ignored in correctly configured JavaFX applications.
     * main() serves only as a fallback in case the application is launched
     * as a regular Java application (non-JavaFX).
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
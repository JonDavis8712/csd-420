/* Jonathan Davis Module 7 Assignment
 *4/26/2025
 * This program displays four circles styled using an external mystyle.css file. It applies specific CSS classes and IDs to control the fill and stroke colors of each circle
 * Tabninepro was used to assist in writing this code, more so the comments than the code itself. using it to
 * test if it can help me write better code, tabnine is awesome for new coders like myself!.
 */

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

// Changed the class name to JonDavisFourCirclesCSS
public class JonDavisFourCirclesCSS extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create four Circle objects
        Circle circle1 = new Circle(50);
        Circle circle2 = new Circle(50);
        Circle circle3 = new Circle(50);
        Circle circle4 = new Circle(50);

        // Applying the styles using CSS Class and ID
        // Circle 1: White fill, Black stroke
        circle1.getStyleClass().add("plaincircle");

        // Circle 2: White fill, Black stroke
        circle2.getStyleClass().add("plaincircle");

        // Circle 3: Red fill and stroke 
        circle3.setId("redcircle");

        // Circle 4: Green fill and stroke 
        circle4.setId("greencircle");


        // Creates an HBox layout pane and add the circles
        HBox hbox = new HBox(20); // 20 pixels spacing between circles
        hbox.setAlignment(Pos.CENTER); // Center the circles in the layout
        hbox.getChildren().addAll(circle1, circle2, circle3, circle4);

        // This creates Scene
        Scene scene = new Scene(hbox, 450, 150); // Adjusted width slightly

        // Loads the external CSS file into the scene here
        scene.getStylesheets().add("mystyle.css");

        // Set the stage title and scene (using the title from the image)
        primaryStage.setTitle("Exercise31_01");
        primaryStage.setScene(scene);

        // Display the stage
        primaryStage.show();
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
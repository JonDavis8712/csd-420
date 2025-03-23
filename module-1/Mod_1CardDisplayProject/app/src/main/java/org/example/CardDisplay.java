/**
 * Jonathan Davis 3/23/2025
 * Assignment: Module 1: Card Display Application
 * What it does: Displays four random cards from a deck of 52 and also has a refresh button.
 */
package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CardDisplay extends Application {

    private List<String> cardPaths; // List to store paths to card images
    private ImageView[] cardImageViews; // Array for card images
    private Random random; // Random generator

    @Override
    public void start(Stage primaryStage) {
        // Initialize the list of card image paths.
        cardPaths = new ArrayList<>();
        for (int i = 1; i <= 52; i++) {
            cardPaths.add("/cards/" + i + ".png"); // card path with leading slash
        }

        // These are the ImageView objects for each card.
        cardImageViews = new ImageView[4];
        for (int i = 0; i < 4; i++) {
            cardImageViews[i] = new ImageView();
            cardImageViews[i].setFitWidth(100); // This will set the width of the ImageView.
            cardImageViews[i].setFitHeight(150); // height of the ImageView.
            cardImageViews[i].setPreserveRatio(true); // Maintains the aspect ratio of the image.
        }

        // Random object.
        random = new Random();

        // Creates an HBox to hold the cards.
        HBox cardBox = new HBox(10, cardImageViews[0], cardImageViews[1], cardImageViews[2], cardImageViews[3]);
        cardBox.setAlignment(Pos.CENTER); // Center the cards horizontally.

        // Creates a Refresh button.
        Button refreshButton = new Button("Refresh");
        refreshButton.setOnAction(event -> displayRandomCards()); // Set the button's action.

        // Creates a vertical display box.
        VBox root = new VBox(10, cardBox, refreshButton);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        // Displays the set of cards
        displayRandomCards();

        // Sets up the scene and stage.
        Scene scene = new Scene(root);
        primaryStage.setTitle("Random Cards");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Displays four randomly selected cards.
     */
    private void displayRandomCards() {
        // Shuffle
        Collections.shuffle(cardPaths);

        // Load and display the first four cards from the shuffled list.
        for (int i = 0; i < 4; i++) {
            Image image = new Image(getClass().getResourceAsStream(cardPaths.get(i)));
            cardImageViews[i].setImage(image);
        }
    }

    /**
     * launch the JavaFX application.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
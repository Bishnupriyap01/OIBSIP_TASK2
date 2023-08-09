package numberguessinggame;

import java.util.Random;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author BISHNUPRIYA PRADHAN
 */

public class NumberGuessingGame extends Application {
  
    private final int MAX_LENGTH = 70; 
    private final int NO_OF_GUESS = 7;

    private int correctNumber; 
    private int GuessesLeft;

    private Label Label; 

    @Override
    public void start(Stage primaryStage) {
        correctNumber = generateRandomNo();//function generates random number and assigns to correctNumber
        GuessesLeft = NO_OF_GUESS;//remaining guess

        Label headingLabel = new Label("Number Guessing Game");//title or heading
        Label inputLabel = new Label("Guess number  between 1 and " + MAX_LENGTH);
        Label = new Label("Remaining guesses: " + GuessesLeft);
        guessField = new TextField();//input from user

        Button b = new Button("Play");//button to run the game
        b.setOnAction(event -> checkGuess());
            //--positioning and aligments--
        VBox root = new VBox(15);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(headingLabel, inputLabel, Label, guessField, b);
        root.setStyle("-fx-alignment: center; -fx-padding: 20px;");

        Scene scene = new Scene(root, 400, 400);
        primaryStage.setTitle("Number Guessing Game");//title
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
    private int generateRandomNo() {
        Random random = new Random();
        return random.nextInt(MAX_LENGTH) + 1;
    }

    private void checkGuess() {
        int guessedNumber;
        try {
            guessedNumber = Integer.parseInt(guessField.getText());
        } catch (NumberFormatException e) {
            Label.setText("Invalid.");
            guessField.clear();
            return;
        }

        GuessesLeft--;
        Label.setText("Remaining guesses: " + GuessesLeft);

        if (guessedNumber == correctNumber) {
            getResult("Congrats!  Your guess is  correct.");
        }
        else if (GuessesLeft <= 0) {
            getResult("Game over. You crossed the guess limit: " + correctNumber);
        }
        else if (guessedNumber < correctNumber) {
            Label.setText("Guess Higher.");
            guessField.clear();
        } 
        else {
            Label.setText("Guess Lower.");
            guessField.clear();
        }
    }

    private void getResult(String text) {
        guessField.setEditable(false);
        Button guessButton;
        guessButton = (Button) Label.getParent().getChildrenUnmodifiable().get(4);
        guessButton.setDisable(true);
        Label.setText(text);
    }
   
}

   


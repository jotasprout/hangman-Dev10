package learn.gamethree;

import org.springframework.web.bind.annotation.PutMapping;

import java.util.ArrayList;
import java.util.List;

public class HangmanGame {

    private String secretWord = "";
    private String wordSoFar = "";
    private int guessesLeft;

    private boolean alive;

    private boolean gameIsWon;


    private final ArrayList<String> lettersGuessed = new ArrayList<>();


    public boolean checkForLife (){
        if (this.guessesLeft < 1){
            alive = false;
        }
        return alive;
    }

    public boolean checkIfWon(){
        return !this.wordSoFar.contains("_");
    }

    public List<String> displayStatus(String msg){
        return List.of(msg, wordSoFar, "Guesses left: " + guessesLeft, "Letters Guessed: " + lettersGuessed);
    }


    public List<String> guessLetter (String guessInput) {
        //check to make sure you aren't dead
        if (!checkForLife()){
            return displayStatus("You are dead. Start a new game.");
        }
        //check if you already won
        if (gameIsWon){
            return displayStatus("You won!. Start a new game.");
        }
        //put the letter to caps
        String guess = guessInput.toUpperCase();
        //check to see if that letter has been guessed already
        if (lettersGuessed.contains(guess)) {
            return displayStatus("You already guessed that letter.");
        }
        if (guess.length() > 1){
            return displayStatus("You must enter only a single letter. Try again");
        }
        //add the letter to the guesses
        lettersGuessed.add(guess);

        //check if the word contains the guessed letter
        if (secretWord.contains(guess)){
            //update the guessed word display
            updateGuessedWord(guess);
            //return the results

            //check if you won
            if (checkIfWon()){
                gameIsWon = true;
                return displayStatus("You won!");
            }
            //you got a correct letter
            return displayStatus("correct");
        } else {
            //incorrect guess, lose a guess
            guessesLeft--;
            //check if you lost
            checkForLife();
            if(!alive){
                //you lost
                return displayStatus("You are dead");
            }
            //you lost a guess
            return displayStatus("Nope");
        }

    }

    public void buildBlankWord(){
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < secretWord.length(); i++) {
            if (secretWord.charAt(i) == ' '){
                builder.append(" ");
            } else {
                builder.append("_");
            }
        }

        wordSoFar = builder.toString();


    }

    public void updateGuessedWord(String letter) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < wordSoFar.length(); i++) {
            if (secretWord.charAt(i) == letter.charAt(0)) {
                builder.append(letter);

            } else {
                builder.append(wordSoFar.charAt(i));
            }
        }
        wordSoFar = builder.toString();
    }


    public List<String> newGame(String secretWord) {
        this.guessesLeft = 7;

        this.secretWord = secretWord.trim().toUpperCase();

        //build a blank word
        buildBlankWord();

        //reset fields
        this.alive = true;
        this.gameIsWon = false;
        this.lettersGuessed.clear();

        //display the status
        return displayStatus("New Game:");
    }
}

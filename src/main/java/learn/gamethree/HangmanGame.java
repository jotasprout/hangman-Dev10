package learn.gamethree;

import org.springframework.web.bind.annotation.PutMapping;

import java.util.ArrayList;

public class HangmanGame {

    private String secretWord = "jabberwocky";
    private String wordSoFar = "___________";
    private int guessesLeft = 7;

    private boolean alive = true;

    private ArrayList<String> lettersGuessed = new ArrayList<>();

    @Override
    public String toString() {
        return "HangmanGame{" +
                "wordSoFar='" + wordSoFar + '\'' +
                ", guessesLeft=" + guessesLeft +
                ", alive=" + alive +
                ", lettersGuessed=" + lettersGuessed +
                '}';
    }

    public boolean checkForLife (int guessesLeft){
        if (guessesLeft < 1){
            alive = false;
        }
        return alive;
    }
    public String guessLetter (String guess) {
        if (lettersGuessed.contains(guess)) {
            return "You already guessed that letter.";
        }
        lettersGuessed.add(guess);
        if (secretWord.contains(guess)){
            updateGuessedWord(guess);
            return "correct";
        } else {
            guessesLeft--;
            checkForLife(guessesLeft);
            if(!alive){
                return "you are dead";
            }
            return "nope";
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
    public void addLetter (String guess) {
        lettersGuessed.add(guess);
    }

}

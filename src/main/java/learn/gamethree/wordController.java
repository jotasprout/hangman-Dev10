package learn.gamethree;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import learn.gamethree.HangmanGame;
@RestController
public class wordController {

    HangmanGame game = new HangmanGame();



    @GetMapping("/")
    public String startGame(){
        return game.toString();
    }

    @GetMapping("/status")
    public String getStatus(){
        return game.toString();
    }


    // POST method to create a game
    // PUT method to submit a letter guess
    @PutMapping("/guess/{letter}")
    public String guess(@PathVariable String letter){
        return game.guessLetter(letter);
    }


    // INT wrongGuesses to increment when letterGuess is false

    // INT numLetters
    // method checking if wrongGuesses is equal to deadLimit
    // ListArray of lettersGuessed
    // GET method to show lettersGuessed and wrongGuesses
    // GET word so far and display it "__bb_rw__ky"

}

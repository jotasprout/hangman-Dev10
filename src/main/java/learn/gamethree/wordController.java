package learn.gamethree;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import learn.gamethree.HangmanGame;

import java.util.List;

@RestController
public class wordController {

    HangmanGame game = new HangmanGame();



    @PostMapping("/")
    public ResponseEntity<List<String>> startGame(@RequestBody String secretWord){
        if (secretWord.isBlank()){
            return new ResponseEntity<>(List.of("Please include a secret word"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(game.newGame(secretWord), HttpStatus.OK);
    }

    @GetMapping("/status")
    public List<String> getStatus(){
        return game.displayStatus("Current Game Status:");
    }


    // POST method to create a game
    // PUT method to submit a letter guess
    @PutMapping("/guess/{letter}")
    public ResponseEntity<List<String>> guess(@PathVariable String letter){
        if (letter.isBlank()){
            return new ResponseEntity<>(List.of("Please guess a letter"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(game.guessLetter(letter), HttpStatus.OK);
    }


    // INT wrongGuesses to increment when letterGuess is false

    // INT numLetters
    // method checking if wrongGuesses is equal to deadLimit
    // ListArray of lettersGuessed
    // GET method to show lettersGuessed and wrongGuesses
    // GET word so far and display it "__bb_rw__ky"

}

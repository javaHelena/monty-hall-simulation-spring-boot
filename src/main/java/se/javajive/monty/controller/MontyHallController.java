package se.javajive.monty.controller;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.javajive.monty.domain.Game;
import se.javajive.monty.domain.GameResult;
import se.javajive.monty.domain.GameStrategy;
import se.javajive.monty.exception.MontyHallGameException;

@RestController
@EnableAutoConfiguration
class MontyHallController {

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

    @RequestMapping("/play")
    public ResponseEntity<GameResult> playGame(@RequestParam(value="iterations", defaultValue="10000") String iterations, @RequestParam(value="strategy", defaultValue="SWITCH") String strategy) throws MontyHallGameException {
        int numberOfIterations = Integer.parseInt(iterations);
        GameResult gameResult = new GameResult(numberOfIterations, strategy);
        GameStrategy gameStrategy;

        try {
            gameStrategy = GameStrategy.valueOf(strategy);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Wrong strategy parameter, dude! ");
        }

        for (int i=1; i <= numberOfIterations ; i++) {
            Game game = new Game();
            try {
                if (game.playGame(gameStrategy)) {
                    gameResult.incrementNumberOfWins();
                }
            } catch (MontyHallGameException e) {
                throw new MontyHallGameException("Something is seriously wrong with your game!! ");
            }
        }
        return new ResponseEntity<>(gameResult, HttpStatus.OK);
    }
}


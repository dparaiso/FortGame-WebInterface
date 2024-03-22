package ca.myapp.controllers;

import ca.myapp.DTO.ApiBoardDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ca.myapp.DTO.ApiGameDTO;
import java.util.ArrayList;
import java.util.List;

@RestController
public class GameController {
    List<ApiGameDTO> games = new ArrayList<>();
    List<ApiBoardDTO> boards = new ArrayList<>();

    @GetMapping("/api/about")
    public String getName(){
        return "Danieva Paraiso";
    }

    @GetMapping("/api/games")
    public List<ApiGameDTO> getAllGames(){
        return games;
    }

    @PostMapping("/api/games")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiGameDTO createNewGame(){
        ApiGameDTO newGame = new ApiGameDTO(games.size(), 5);
        games.add(newGame);
        ApiBoardDTO newBoard = new ApiBoardDTO(10,10, new String[10][10]);
        newBoard.initializeFog();
        boards.add(newBoard);
        return newGame;
    }

    @GetMapping("/api/games/{id}")
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ApiGameDTO getOneGame(@PathVariable("id") long gameId){
        for(ApiGameDTO game: games){
            if(game.getGameNumber() == gameId){
                return game;
            }
        }
        throw new IllegalArgumentException();
    }

    @GetMapping("/api/games/{id}/board")
    public ApiBoardDTO getBoardState(@PathVariable("id") long gameId){
        ApiGameDTO game = getOneGame(gameId);
        return boards.get((int)gameId);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(IllegalArgumentException.class)
    public void badIdExceptionHandler(){
//        nothing to do
    }

}

package ca.myapp.controllers;

import ca.myapp.DTO.ApiBoardDTO;
import ca.myapp.DTO.ApiLocationDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ca.myapp.DTO.ApiGameDTO;
import java.util.ArrayList;
import java.util.List;

@RestController
public class GameController {
    List<ApiGameDTO> games = new ArrayList<>();
    List<ApiBoardDTO> boards = new ArrayList<>();
    List<Game> gamesBackground = new ArrayList<>();

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
        ApiGameDTO newGame = new ApiGameDTO(games.size()+1, 5);
        games.add(newGame);
        ApiBoardDTO newBoard = new ApiBoardDTO(10,10, new String[10][10]);
        newBoard.initializeFog();
        boards.add(newBoard);
        Game newGameBg = new Game(5);
        gamesBackground.add(newGameBg);
        return newGame;
    }

    @GetMapping("/api/games/{id}")
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

        return boards.get((int)gameId-1);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/api/games/{id}/moves")
    public void updateMove(
            @PathVariable("id") long gameId,
            @RequestBody ApiLocationDTO location) throws IllegalAccessException {
        if(gameId-1 > boards.size()){
            System.out.println("arg exception");
            throw new IllegalArgumentException();
        }
         if(location.col > 10 || location.col < 0 ||
                location.row > 10 || location.col < 0){
             System.out.println("access exception");
             throw new IllegalAccessException();
        }

        int gameID = (int)gameId-1;
        Game currentGameBg = gamesBackground.get(gameID);
        ApiGameDTO currentGame = games.get(gameID);
        ApiBoardDTO currentBoard = boards.get(gameID);
        // update board
        Coordinate loc = new Coordinate(location.row, location.col);
        currentGameBg.recordPlayerShot(loc);

        if(currentGameBg.didLastPlayerShotHit()){
            currentBoard.setHit(location.row, location.col);
        }else{
            currentBoard.setMiss(location.row, location.col);
        }

        // update points
        currentGameBg.fireEnemyShots();
        currentGame.setLastOpponentPoints(currentGameBg.getLatestEnemyDamages());
        currentGame.setOpponentPoints(currentGameBg.getEnemyPoints());
        currentGame.setNumActiveOpponentForts(currentGameBg.getEnemiesLeft());
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(IllegalArgumentException.class)
    public void badIdExceptionHandler(){
//        nothing to do
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalAccessException.class)
    public void badLocationExceptionHandler(){
//        nothing to do
    }

    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @PostMapping("/api/games/{id}/cheatstate")
    public void cheatMode(
            @PathVariable("id") long gameId,
            @RequestBody String showAll){
        ApiGameDTO currentGame = getOneGame(gameId);
        int gameID = (int)gameId - 1;
        ApiBoardDTO currentBoard = boards.get(gameID);
        Game currentGameBg = gamesBackground.get(gameID);
        for(int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                if(currentBoard.getCellState(i,j).equals("fog")){
                    if( currentGameBg.getCellState(new Coordinate(i, j)).hasFort()){
                        currentBoard.setFort(i, j);
                    }else{
                        currentBoard.setField(i, j);
                    }

                }

            }
        }

    }
}

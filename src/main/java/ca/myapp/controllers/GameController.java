package ca.myapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ca.myapp.DTO.ApiGameDTO;
import java.util.ArrayList;
import java.util.List;

@RestController
public class GameController {
    List<ApiGameDTO> games = new ArrayList<>();
    @GetMapping("/games")
    public String getGame(){return "";}

}

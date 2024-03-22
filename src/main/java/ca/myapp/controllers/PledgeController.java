package ca.myapp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ca.myapp.model.Pledge;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class PledgeController {
    private List<Pledge> pledges = new ArrayList<>();
    private AtomicLong nextId = new AtomicLong();
    @GetMapping("/hello")
    public String getHelloMessage(){
        return "Hello world, from Spring!";
    }

    @PostMapping("/pledges")
    @ResponseStatus(HttpStatus.CREATED)
    public Pledge createNewPledge(@RequestBody Pledge pledge){
        // set pledge to have id
        pledge.setId(nextId.incrementAndGet());
        pledges.add(pledge);
        return pledge;
    }

    @GetMapping("/pledges")
    public List<Pledge> getAllPledges(){
        return pledges;
    }

    @GetMapping("/pledges/{id}")
    public Pledge getOnePledge(@PathVariable("id") long pledgeId){
           for(Pledge pledge: pledges){
               if(pledge.getId() == pledgeId){
                   return pledge;
               }
           }
           throw new IllegalArgumentException();
    }

    @PostMapping("/pledges/{id}")
    public Pledge editOnePledge(
            @PathVariable("id") long pledgeId,
            @RequestBody Pledge newPledge){

        for(Pledge pledge: pledges){
            if(pledge.getId() == pledgeId){
                pledges.remove(pledge);
                newPledge.setId(pledgeId); // from url
                pledges.add(newPledge);

                return pledge;
            }
        }
        throw new IllegalArgumentException();
    }

    // Create Exception Handler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Request ID not found.")
    @ExceptionHandler(IllegalArgumentException.class)
    public void badIdExceptionHandler(){
//        nothing to do
    }

}

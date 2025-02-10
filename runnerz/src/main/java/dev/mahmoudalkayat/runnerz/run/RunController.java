package dev.mahmoudalkayat.runnerz.run;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//Annotations add behavior to classes and methods and fields
//It cuts down on having to write extra code
//Rest Controller says that the class is a rest controller
//So we expect the response body to be a JSON object

//The controllers only job is to take a request and return a response. Not handle any logic
//If we take of the @RestController annotation, the class would not be able to respond to requests
//That is exactly what @RestController does. It tells Spring that this class is a class
//that takes requests and returns responses

//@Component is what tells spring to manage the lifecycle and dependencies of a class. A parent of @RestController is @Component
//We can set a requestMapping on the class level. This will be the base path for all the methods in the class
@RequestMapping("/api/runs")
@RestController
public class RunController {

//We need to map this method to an end point.
//When somebody access a specfic end point, we tell it which method to execute

    //RequestMapping takes the path and the request method(GET)
    //We will use GetMapping instead of RequestMapping because we know we will
    //be using the GET method
    //this is a get mapping to /hello so when we run the app there will be an endpoint for /hello
//    @GetMapping("/hello")
//    String home(){
//        return "Hello, Runnerz!";
//    }
    //We no longer will be using that code. Below is what is relevant


    //As we can see we have a findAll() method in both the controller and the repository. We need an instance of the repository in the controller
    //Spring is an inversion of control framework, so we should not be manually created instances of classes. Spring should have an instance
    //of the repository and inject it into the controller. It should also run the PostConstruct method in the repository
    //If we were to make a new instance of repository then everytime that the controller gets ran, a new instance will be created
    //This is not what we want. We want one instance of the repository to be created and injected into the controller

    private final RunRepository runRepository;


    public RunController(RunRepository runRepository){
        this.runRepository = runRepository;
    }
    @GetMapping()
    List<Run> findAll(){
        return runRepository.findAll();
    }

    @GetMapping("/{id}")
    Run findById(@PathVariable Integer id){
        Optional<Run> run = runRepository.findById(id);
        if (run.isEmpty()){
            throw new RunNotFoundException();
        }
        return run.get();
    }

    //post/create
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    void create(@Valid @RequestBody Run run){
        runRepository.create(run);
    }

    //put/update
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@Valid @RequestBody Run run, @PathVariable Integer id){
        runRepository.update(run,id);
    }

    //delete
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id){
        runRepository.delete(id);
    }



}

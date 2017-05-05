package com.example.controller;

import com.example.entity.Movie;
import com.example.repositories.IMovieRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@RestController
public class MoviesController {

    private final IMovieRepository iMovieRepository;

    public MoviesController(IMovieRepository iMovieRepository) {
        this.iMovieRepository = iMovieRepository;
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable long id){
        Movie movie = iMovieRepository.findOne(id);
        movie.add(linkTo(methodOn(MoviesController.class).getMovie(id)).withSelfRel());

        return new ResponseEntity<>(movie, HttpStatus.OK);
    }
}

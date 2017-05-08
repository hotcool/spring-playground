package com.example.controller;

import com.example.entity.Movie;
import com.example.repositories.IMovieRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/movies")
public class MoviesController {

    private final IMovieRepository iMovieRepository;

    public MoviesController(IMovieRepository iMovieRepository) {
        this.iMovieRepository = iMovieRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable long id) {
        Movie movie = iMovieRepository.findOne(id);
        movie.add(linkTo(methodOn(MoviesController.class).getMovie(id)).withSelfRel());

        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Movie>> all() {
        Iterable<Movie> movieIterable = this.iMovieRepository.findAll();
        List<Movie> movies = new ArrayList<>();
        movieIterable.forEach(movies::add);
        movies.forEach(movie -> {
            movie.add(linkTo(methodOn(MoviesController.class).getMovie(movie.getMovieId())).withSelfRel());
            movie.add(linkTo(methodOn(TrailerController.class).findTrailer(movie.getMovieId())).withSelfRel());
        });
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }
}

package com.example.controller;

import com.example.entity.Trailer;
import com.example.repositories.ITrailerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trailers")
public class TrailerController {
    private final ITrailerRepository trailerRepository;

    public TrailerController(ITrailerRepository trailerRepository) {
        this.trailerRepository = trailerRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trailer> findTrailer(@PathVariable Long id) {
        Trailer trailer = this.trailerRepository.findOne(id);
        return new ResponseEntity<>(trailer, HttpStatus.OK);
    }

}
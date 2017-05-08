package com.example.controller;

import com.example.service.OmdbServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OmdbController {

    private final OmdbServices services;

    public OmdbController(OmdbServices services) {
        this.services = services;
    }

    @GetMapping("/movies")
    public String getMovies(@RequestParam(value = "q", required = false) String q) throws Exception {
        return services.getMovies(q);
    }

}

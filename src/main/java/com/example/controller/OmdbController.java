package com.example.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class OmdbController {

    private final RestTemplate restTemplate;

    private static ObjectMapper objectMapper = new ObjectMapper();

    public OmdbController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/movies")
    public String getMovies(@RequestParam("q") String q) throws Exception {
        JsonNode jsonNode = objectMapper.readTree(restTemplate.getForObject(
                "http://www.omdbapi.com/?s={q}",
                String.class,
                q
        ));

        ArrayNode search = (ArrayNode) jsonNode.get("Search");

        List<Map<String, String>> result = new ArrayList<>();

        search.forEach(value -> {
            Map<String, String> map = new HashMap<>();
            map.put("title", value.get("Title").textValue());
            map.put("imdbId", value.get("imdbID").textValue());
            map.put("year", value.get("Year").textValue());
            map.put("poster", value.get("Poster").textValue());
            result.add(map);
        });

        return objectMapper.writeValueAsString(result);
    }

}

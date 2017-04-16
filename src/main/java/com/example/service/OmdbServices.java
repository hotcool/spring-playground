package com.example.service;

import com.example.config.OmdbConfig;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OmdbServices {

    private final OmdbConfig config;
    private final RestTemplate restTemplate = new RestTemplate();
    private static ObjectMapper objectMapper = new ObjectMapper();

    public OmdbServices(OmdbConfig config) {
        this.config = config;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public String getMovies(String query) throws Exception{
        JsonNode jsonNode = objectMapper.readTree(restTemplate.getForObject(
                String.format("%s/?s={query}", config.getUrl()),
                String.class,
                query
        ));

        ArrayNode search = (ArrayNode) jsonNode.get("Search");

        List<Map<String, Object>> result = new ArrayList<>();

        search.forEach(value -> {
            Map<String, Object> map = new HashMap<>();
            map.put("title", value.get("Title").textValue());
            map.put("imdbId", value.get("imdbID").textValue());
            map.put("year", Integer.parseInt(value.get("Year").textValue()));
            map.put("poster", value.get("Poster").textValue());
            result.add(map);
        });

        return objectMapper.writeValueAsString(result);
    }
}

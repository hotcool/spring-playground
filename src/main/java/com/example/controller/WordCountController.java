package com.example.controller;

import com.example.service.WordCounter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class WordCountController {

    private final WordCounter wordCounter;

    public WordCountController(WordCounter wordCounter){
        this.wordCounter = wordCounter;
    }

    @PostMapping("/words/count")
    public Map<String, Integer> count(@RequestBody String word){
        return wordCounter.count(word);
    }

}

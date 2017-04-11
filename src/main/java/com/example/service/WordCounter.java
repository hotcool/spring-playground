package com.example.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.summingInt;

public class WordCounter {

    public Map<String, Integer> count(String word) {
        if (null == word || word.isEmpty()) {
            return null;
        }
        word = word.replaceAll("[^a-zA-Z0-9\\s]", "");
        List<String> words = Arrays.asList(word.split(" "));

        if (null == words || 0 == words.size()) {
            return null;
        }

        return words.stream().collect(Collectors.groupingBy(Function.identity(), summingInt(w -> 1)));
    }

}

package com.example.service;

import com.example.config.WordCountConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.summingInt;

public class WordCounter {

    private final WordCountConfig wordCountConfig;

    public WordCounter(WordCountConfig wordCountConfig) {
        this.wordCountConfig = wordCountConfig;
    }


    public Map<String, Integer> count(String word) {
        if (null == word || word.isEmpty()) {
            return null;
        }
        word = word.replaceAll("[^a-zA-Z0-9\\s]", "");
        if (!wordCountConfig.isCaseSensitive()) {
            word = word.toLowerCase();
        }
        List<String> words = new ArrayList<>(Arrays.asList(word.split(" ")));

        if(null != wordCountConfig.getWords() && null != wordCountConfig.getWords().getSkip() && 0 != wordCountConfig.getWords().getSkip().size()){
            words.removeAll(wordCountConfig.getWords().getSkip());
        }

        if (null == words || 0 == words.size()) {
            return null;
        }

        return words.stream().collect(Collectors.groupingBy(Function.identity(), summingInt(w -> 1)));
    }

}

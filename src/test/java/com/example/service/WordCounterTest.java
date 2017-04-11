package com.example.service;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class WordCounterTest {

    private WordCounter wordCounter = new WordCounter();

    @Test
    public void testNull() {
        assertNull(wordCounter.count(null));
    }

    @Test
    public void testEmpty() {
        assertNull(wordCounter.count(""));
    }

    @Test
    public void testSingWord() {
        Map<String, Integer> map = new HashMap<>();
        map.put("thisisawordwithoutanyspace", 1);
        assertEquals(map, wordCounter.count("this_is_a_word_without_any_space."));
    }

    @Test
    public void testMultiWords() {
        Map<String, Integer> map = new HashMap<>();
        map.put("to", 2);
        map.put("the", 2);
        map.put("moon", 2);
        assertEquals(map, wordCounter.count("to the moon, to the moon."));
    }

}

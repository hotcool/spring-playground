package com.example.service;

import com.example.config.WordCountConfig;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WordCounterTest {

    private WordCountConfig wordCountConfig;
    private WordCountConfig.Words words;

    @Before
    public void setup() {
        words = mock(WordCountConfig.Words.class);
        wordCountConfig = mock(WordCountConfig.class);
        when(wordCountConfig.isCaseSensitive()).thenReturn(false);
        when(wordCountConfig.getWords()).thenReturn(words);
    }

    @Test
    public void testNull() {
        WordCounter wordCounter = new WordCounter(wordCountConfig);
        assertNull(wordCounter.count(null));
    }

    @Test
    public void testEmpty() {
        WordCounter wordCounter = new WordCounter(wordCountConfig);
        assertNull(wordCounter.count(""));
    }

    @Test
    public void testSingWord() {
        WordCounter wordCounter = new WordCounter(wordCountConfig);

        Map<String, Integer> map = new HashMap<>();
        map.put("thisisawordwithoutanyspace", 1);
        assertEquals(map, wordCounter.count("this_is_a_word_without_any_space."));
    }

    @Test
    public void testMultiWords() {
        WordCounter wordCounter = new WordCounter(wordCountConfig);

        Map<String, Integer> map = new HashMap<>();
        map.put("to", 2);
        map.put("the", 2);
        map.put("moon", 2);
        assertEquals(map, wordCounter.count("to the moon, to the moon."));
    }

    @Test
    public void testMultiWordsCaseSensitive() {
        List<String> skipList = Arrays.asList("a","the");
        when(words.getSkip()).thenReturn(skipList);
        WordCounter wordCounter = new WordCounter(wordCountConfig);

        Map<String, Integer> map = new HashMap<>();
        map.put("brown", 2);
        map.put("cow", 1);
        map.put("jumps", 1);
        map.put("over", 1);
        map.put("fox", 1);
        assertEquals(map, wordCounter.count("The BROWN cow jumps over a brown fox"));
    }

    @Test
    public void testMultiWordsCaseInSensitive() {
        List<String> skipList = Arrays.asList("a","the");
        when(words.getSkip()).thenReturn(skipList);
        when(wordCountConfig.isCaseSensitive()).thenReturn(true);
        WordCounter wordCounter = new WordCounter(wordCountConfig);

        Map<String, Integer> map = new HashMap<>();
        map.put("brown", 1);
        map.put("BROWN", 1);
        map.put("cow", 1);
        map.put("jumps", 1);
        map.put("over", 1);
        map.put("fox", 1);
        map.put("The", 1);
        assertEquals(map, wordCounter.count("The BROWN cow jumps over a brown fox"));
    }

}

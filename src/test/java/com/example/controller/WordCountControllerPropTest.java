package com.example.controller;

import com.example.service.WordCounter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("../../../application-IOC-caseSensitive.properties")
public class WordCountControllerPropTest {

    @Autowired
    private WordCounter wordCounter;

    @Test
    public void testMultiWordsCaseSensitive() throws Exception {
        Map<String, Integer> map = new HashMap<>();
        map.put("brown", 1);
        map.put("BROWN", 1);
        map.put("cow", 1);
        map.put("jumps", 1);
        map.put("over", 1);
        map.put("fox", 1);
        map.put("The", 1);

        assertEquals(map,wordCounter.count("The BROWN cow jumps over a brown fox. an an the the "));
    }

}

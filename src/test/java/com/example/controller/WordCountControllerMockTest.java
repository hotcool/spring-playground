package com.example.controller;

import com.example.service.WordCounter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(WordCountController.class)
public class WordCountControllerMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WordCounter mockCounter;

    @Test
    public void testSingleWordMock() throws Exception{

        Map<String, Integer> mockResult = new HashMap<String, Integer>() {
            {
                put("thisisaverylongtest", 1);
            }
        };

        when(mockCounter.count("this_is_a-very.long,test!")).thenReturn(mockResult);

        mockMvc.perform(post("/words/count").content("this_is_a-very.long,test!"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.thisisaverylongtest", is(1)));
    }

    @Test
    public void testMultiMock() throws Exception{

        Map<String, Integer> mockResult = new HashMap<String, Integer>() {
            {
                put("to", 2);
                put("the", 2);
                put("moon", 2);
            }
        };

        when(mockCounter.count("to the moon, to the moon")).thenReturn(mockResult);

        mockMvc.perform(post("/words/count").content("to the moon, to the moon"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.to", is(2)))
                .andExpect(jsonPath("$.the", is(2)))
                .andExpect(jsonPath("$.moon", is(2)));



    }

}

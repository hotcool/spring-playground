package com.example.controller;

import com.example.config.WordCountConfig;
import com.google.gson.JsonObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest({WordCountConfig.class, WordCountController.class})
public class WordCountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testEmpty() throws Exception {
        mockMvc.perform(post("/words/count").content("")).andExpect(status().isBadRequest());
    }

    @Test
    public void testSingleWord() throws Exception {
        MvcResult result = mockMvc.perform(post("/words/count").content("this_is_a_word_without_any_space.")).andExpect(status().isOk()).andReturn();

        String value = result.getResponse().getContentAsString();

        JsonObject object = new JsonObject();
        object.addProperty("thisisawordwithoutanyspace", new Integer(1));

        assertEquals(object.toString(), value);
    }

    @Test
    public void testMultiWords() throws Exception {
        MvcResult result = mockMvc.perform(post("/words/count").content("How now, brown cow")).andExpect(status().isOk()).andReturn();

        String value = result.getResponse().getContentAsString();

        JsonObject object = new JsonObject();
        object.addProperty("How", new Integer(1));
        object.addProperty("now", new Integer(1));
        object.addProperty("cow", new Integer(1));
        object.addProperty("brown", new Integer(1));

        assertEquals(object.toString(), value);
    }

}

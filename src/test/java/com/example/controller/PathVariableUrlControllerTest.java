package com.example.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PathVariableUrlController.class)
@AutoConfigureMockMvc(secure=false)
public class PathVariableUrlControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testPathVariable() throws Exception {
        //Post
        this.mvc.perform(post("/math/volume/3/4/5").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("The volume of a 3x4x5 rectangle is 60"));
        //Get
        this.mvc.perform(get("/math/volume/6/7/8").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("The volume of a 6x7x8 rectangle is 336"));
    }

    @Test
    public void testPathVariableNullAndEmpty() throws Exception {
        //null
        this.mvc.perform(put("/math/volume/").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isNotFound());
        //empty
        this.mvc.perform(put("/math/volume///").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testPathVariableBadRequest() throws Exception {
        //bad request
        this.mvc.perform(put("/math/volume/a/b/c").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isBadRequest());
        //bad request
        this.mvc.perform(put("/math/volume/2.3/4.5/6.7").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testPathVariableNotFound() throws Exception {
        //incorrect number of path variables returns 404
        this.mvc.perform(put("/math/volume/2/3").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isNotFound());
    }

}

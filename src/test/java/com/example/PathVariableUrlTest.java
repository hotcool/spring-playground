package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PathVariableUrl.class)
public class PathVariableUrlTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testPathVariable() throws Exception{
        //null
        this.mvc.perform(put("/math/volume/").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isNotFound());
        //empty
        this.mvc.perform(put("/math/volume///").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isNotFound());
        //bad request
        this.mvc.perform(put("/math/volume/a/b/c").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isBadRequest());
        //bad request
        this.mvc.perform(put("/math/volume/2.3/4.5/6.7").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isBadRequest());
        //incorrect number of path variables returns 404
        this.mvc.perform(put("/math/volume/2/3").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isNotFound());

        //Post
        this.mvc.perform(post("/math/volume/3/4/5").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("The volume of a 3x4x5 rectangle is 60"));
        //Get
        this.mvc.perform(get("/math/volume/6/7/8").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("The volume of a 6x7x8 rectangle is 336"));
    }

}

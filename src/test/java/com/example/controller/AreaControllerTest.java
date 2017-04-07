package com.example.controller;

import com.example.service.MathService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest({AreaController.class, MathService.class})
public class AreaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCircle() throws Exception {
        MockHttpServletRequestBuilder request = post("/math/area")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("type", "circle")
                .param("radius", "4");

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Area of a circle with a radius of 4 is 50.26548"));

        //empty object
        MockHttpServletRequestBuilder nullObjRequest = post("/math/area")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED);

        mockMvc.perform(nullObjRequest)
                .andExpect(status().isOk())
                .andExpect(content().string("Error! Incorrect input parameters. This endpoint only takes in type(circle, rectangle), radius, width and height!"));

        //other type
        MockHttpServletRequestBuilder badTypeRequest = post("/math/area")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("type", "square")
                .param("radius", "4");

        mockMvc.perform(badTypeRequest)
                .andExpect(status().isOk())
                .andExpect(content().string("Error! Incorrect input type. This endpoint only accepts circle / rectangle."));
    }

    @Test
    public void testRec() throws Exception {
        MockHttpServletRequestBuilder request = post("/math/area")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("type", "rectangle")
                .param("width", "4")
                .param("height", "7");

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Area of a 4x7 rectangle is 28"));
    }

}

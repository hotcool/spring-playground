package com.example.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(secure = false)
public class LessonsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Before
    @Transactional
    @Rollback
    public void setup() throws Exception {
        for (int i = 0; i < 5; ++i) {
            MockHttpServletRequestBuilder postRequest = post("/lessons")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"title\": \"JPA\"}");
            this.mockMvc.perform(postRequest).andExpect(status().isOk());
        }
    }

    @Test
    @Transactional
    @Rollback
    public void testGet() throws Exception {
        MockHttpServletRequestBuilder request = get("/lessons/5")
                .contentType(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("JPA")));
    }

    @Test
    public void testGetInvalidInput() throws Exception {
        MockHttpServletRequestBuilder request = get("/lessons/a")
                .contentType(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(request)
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetNonExist() throws Exception {
        MockHttpServletRequestBuilder request = get("/lessons/54328798")
                .contentType(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    @Transactional
    @Rollback
    public void testPatch() throws Exception {
        MockHttpServletRequestBuilder request = patch("/lessons/5")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":5,\"title\":\"Spring Security\"}");

        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(5)))
                .andExpect(jsonPath("$.title", is("Spring Security")));
    }

    @Test
    public void testPatchNonExist() throws Exception {
        MockHttpServletRequestBuilder request = patch("/lessons/65432")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":6,\"title\":\"new word\"}");

        this.mockMvc.perform(request)
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDelete() throws Exception {
        MockHttpServletRequestBuilder request = delete("/lessons/5")
                .contentType(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(""));

        MockHttpServletRequestBuilder getRequest = get("/lessons/5")
                .contentType(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(getRequest)
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    public void testDeleteNonExist() throws Exception {
        MockHttpServletRequestBuilder request = delete("/lessons/65436543")
                .contentType(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(content().string("id does not exist!"));
    }

}

package com.example.controller;

import com.example.service.MathService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest({MathController.class, MathService.class})
@AutoConfigureMockMvc(secure=false)
public class MathControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testCalculateNullOperation() throws Exception {
        //null
        mvc.perform(get("/math/calculate?x=30&y=5").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("30 + 5 = 35"));
    }

    @Test
    public void testCalculateIncorrectData() throws Exception {
        //incorrect input
        mvc.perform(get("/math/calculate?x=\"30\"&y=5").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCalculateInvalidOps() throws Exception {
        //empty
        mvc.perform(get("/math/calculate?operation=&x=30&y=5").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("Error! Invalid input operation! Please use add, subtract, multiply, and divide!"));
        //other
        mvc.perform(get("/math/calculate?operation=mod&x=30&y=5").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("Error! Invalid input operation! Please use add, subtract, multiply, and divide!"));

    }

    @Test
    public void testCalculateNormalOps() throws Exception {
        //add
        mvc.perform(get("/math/calculate?operation=add&x=4&y=6").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("4 + 6 = 10"));
        //subtract
        mvc.perform(get("/math/calculate?operation=subtract&x=4&y=6").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("4 - 6 = -2"));
        //multiple
        mvc.perform(get("/math/calculate?operation=multiply&x=4&y=6").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("4 * 6 = 24"));
        //divide
        mvc.perform(get("/math/calculate?operation=divide&x=30&y=5").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("30 / 5 = 6"));
    }

    @Test
    public void testSum() throws Exception {

        //one para
        mvc.perform(post("/math/sum?n=4").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("4"));
        //multi paras
        mvc.perform(post("/math/sum?n=4&n=5").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("4 + 5 = 9"));
        //one more
        mvc.perform(post("/math/sum?n=4&n=5&n=6").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("4 + 5 + 6 = 15"));

    }

    @Test
    public void testSumInvalidPara() throws Exception {
        //invalid
        mvc.perform(post("/math/sum?n=a").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("Error! Invalid input integer!"));
    }

    @Test
    public void testSumMultipleVariables() throws Exception {
        //more variable names
        mvc.perform(post("/math/sum?n=4&p=5&q=6").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("4"));
        //no n
        mvc.perform(post("/math/sum?a=4&p=5&q=6").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("Error! Invalid input integer!"));
        //more variable names with multiple ns
        mvc.perform(post("/math/sum?n=4&n=6&p=5&q=6").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("4 + 6 = 10"));
    }

}

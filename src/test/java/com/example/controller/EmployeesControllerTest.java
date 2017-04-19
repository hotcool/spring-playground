package com.example.controller;

import com.example.config.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Import(SecurityConfig.class)
public class EmployeesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testWithEmployee() throws Exception {
        RequestBuilder request = get("/admin/employees").with(user("user").roles("EMPLOYEE"));
        mockMvc.perform(request).andExpect(status().isForbidden());
    }

    @Test
    public void testWithManager() throws Exception {
        RequestBuilder request = get("/admin/employees").with(user("user").roles("MANAGER"));
        mockMvc.perform(request).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].salary").exists());
    }

    @Test
    @WithAnonymousUser
    public void testWithAnonymous() throws Exception {
        RequestBuilder request = get("/admin/employees");
        mockMvc.perform(request).andExpect(status().isUnauthorized());
    }

}


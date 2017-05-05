package com.example.controller;

import com.example.entity.Movie;
import com.example.repositories.IMovieRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MoviesController.class)
public class MoviesControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private IMovieRepository repo;

    @Test
    public void testHateoasFindOne() throws Exception {
        Movie movie = new Movie("Logan");
        movie.setMovieId(1L);
        when(this.repo.findOne(1L)).thenReturn(movie);

        MockHttpServletRequestBuilder request = get("/movies/1");

        this.mvc.perform(request)
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._links.self.href", is("http://localhost/movies/1")));
    }
}

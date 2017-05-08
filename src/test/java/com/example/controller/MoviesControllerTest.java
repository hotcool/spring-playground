package com.example.controller;

import com.example.entity.Movie;
import com.example.repositories.IMovieRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MoviesController.class)
@AutoConfigureMockMvc(secure = false)
public class MoviesControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private IMovieRepository repo;

    private ArrayList<Movie> movies;
    private Movie movie1;
    private Movie movie2;
    private Movie movie3;

    @Before
    public void setUp() throws Exception {
        movie1 = new Movie("Boss Baby");
        movie1.setMovieId(1L);
        movie2 = new Movie("Beauty and the Beast");
        movie2.setMovieId(2L);
        movie3 = new Movie("Logan");
        movie3.setMovieId(3L);
        movies = new ArrayList<>();
        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);
    }

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

    @Test
    public void testHateoasIndexRoute() throws Exception {
        when(this.repo.findAll()).thenReturn(movies);

        MockHttpServletRequestBuilder request = get("/movies");

        this.mvc.perform(request)
                //.andDo(print())
                .andExpect(jsonPath("$[0].links[0].href", is("http://localhost/movies/1")))
                .andExpect(jsonPath("$[0].links[1].href", is("http://localhost/trailers/1")))
                .andExpect(jsonPath("$[1].links[1].href", is("http://localhost/trailers/2")))
                .andExpect(jsonPath("$[2].links[0].href", is("http://localhost/movies/3")));
    }
}

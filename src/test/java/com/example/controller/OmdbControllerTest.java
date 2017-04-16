package com.example.controller;

import com.example.config.OmdbConfig;
import com.example.service.OmdbServices;
import com.example.util.ReadFixture;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OmdbControllerTest {

    private OmdbServices services;
    private MockRestServiceServer mockServer;

    @Before
    public void setup() {
        OmdbConfig config = mock(OmdbConfig.class);
        when(config.getUrl()).thenReturn("http://www.omdbapi.com");

        services = new OmdbServices(config);
        mockServer = MockRestServiceServer.createServer(services.getRestTemplate());
    }

    @Test
    public void testGetMovies() throws Exception {
        mockServer.expect(requestTo("http://www.omdbapi.com/?s=harry"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(getJson("src/test/resources/response/RawOmdbResponse.json"), MediaType.APPLICATION_JSON));

        assertThat(services.getMovies("harry"), equalTo(getJson("src/test/resources/response/FormattedOmdbResponse.json")));
        mockServer.verify();
    }

    private String getJson(String path) throws IOException {
        return ReadFixture.readFixture(path, StandardCharsets.UTF_8);
    }
}

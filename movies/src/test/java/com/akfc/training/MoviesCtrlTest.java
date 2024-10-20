package com.akfc.training;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.akfc.training.model.Genre;
import com.akfc.training.model.Movie;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MoviesCtrlTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testMovieCreation() throws Exception {
        Movie movie = Movie.builder()
                .title("Les dents de la mer 2")
                .director("Steven Spielberg")
                .genre(Genre.Action)
                .productionDate(LocalDate.of(1977, 3, 29))
                .releaseDate(LocalDate.of(1978, 3, 17))
                .description("Very good movie")
                .rating(4.7)
                .fromBook(null)
                .actors(List.of())
                .build();
        ResultActions response =
                mockMvc.perform(post("/api/movies").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(movie)));
        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title", is(movie.getTitle())));
    }
}

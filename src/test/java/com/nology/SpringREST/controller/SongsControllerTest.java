package com.nology.SpringREST.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nology.SpringREST.entity.Song;
import com.nology.SpringREST.repository.SongsRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SongsController.class)
public class SongsControllerTest {
//    private final List<Recipe> recipeList = List.of(
//            new Recipe(1, "Macaroons", 8, 60),
//            new Recipe(2, "Tiramisu", 12, 120),
//            new Recipe(3, "Florentines", 1, 20)
//    );

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SongsRepository repo;

    @Test
    @DisplayName("Index Route should return a list of songs if they exist")
    public void indexRouteShouldReturnListOfUsers() throws Exception {
        // Mock the repository, so I have an expected result.
//        when(repo.findAll()).thenReturn(recipeList);

        // Mock the request, so I have an expected input.
        mockMvc.perform(get("/songs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3))) // 3 items in our predefined database in SongsRepository
                .andReturn();
    }

    @Test
    @DisplayName("Show route should return a selected song using its id")
    public void showRouteShouldReturnTheCorrectSong() throws Exception {
        mockMvc.perform(get("/songs/1"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.songTitle", hasToString("All You Had To Do Was Stay")))
                .andExpect((ResultMatcher) jsonPath("$.artist", hasToString("Taylor Swift")))
                .andExpect((ResultMatcher) jsonPath("$.songLyric", hasToString("All you had to do was stay")))
                .andExpect((ResultMatcher) jsonPath("$.songAlbum", hasToString("1989")))
                .andExpect((ResultMatcher) jsonPath("$.songDuration", hasToString("3:13")))
                .andExpect((ResultMatcher) jsonPath("$.albumArt", hasToString("https://lastfm.freetls.fastly.net/i/u/770x0/95f441a398374c7acc4ac72667ceb42e.jpg#95f441a398374c7acc4ac72667ceb42e")))
                .andExpect((ResultMatcher) jsonPath("$.releaseYear", is(Integer.valueOf(2014))))
                .andReturn();
    }


    @Test
    @DisplayName("Create Route should create a new song and return a success message.")
    public void createRouteShouldCreateASongAndReturnASuccessMessage() throws Exception {
        mockMvc.perform(post("/songs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(new Song(4, "Nirvana", "Smells Like Teen Spirit", "With the lights out, it's less dangerous", "Nevermind", "4:38", "https://lastfm.freetls.fastly.net/i/u/770x0/570021b68d3d9d2db08bc99a473303b0.jpg#570021b68d3d9d2db08bc99a473303b0", 1991 )))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.text", is("Successfully created a song in the database.")))
                .andReturn();
    }

    @Test
    @DisplayName("Delete Route should delete a song and return a success message.")
    public void deleteRouteShouldDeleteASongAndReturnASuccessMessage() throws Exception {
        mockMvc.perform(delete("/songs/4"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text", is("Successfully deleted song with id 4")))
                .andReturn();
    }

    @Test
    @DisplayName("Put Route should alter an existing song and return the new song.")
    public void putRouteShouldAlterASongAndReturnTheSong() throws Exception {
        mockMvc.perform(put("/songs/3")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(new Song(3, "Angel Olsen", "Heart-Shaped Face", "Heartache ends.", "My Woman", "5:33", "https://lastfm.freetls.fastly.net/i/u/770x0/534f10077f07b253b5eec4b3dcb7a44d.jpg#534f10077f07b253b5eec4b3dcb7a44d", 2016)))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.songTitle", hasToString("Heart-Shaped Face")))
                .andExpect((ResultMatcher) jsonPath("$.artist", hasToString("Angel Olsen")))
                .andExpect((ResultMatcher) jsonPath("$.songLyric", hasToString("Heartache ends.")))
                .andExpect((ResultMatcher) jsonPath("$.songAlbum", hasToString("My Woman")))
                .andExpect((ResultMatcher) jsonPath("$.songDuration", hasToString("5:33")))
                .andExpect((ResultMatcher) jsonPath("$.albumArt", hasToString("https://lastfm.freetls.fastly.net/i/u/770x0/534f10077f07b253b5eec4b3dcb7a44d.jpg#534f10077f07b253b5eec4b3dcb7a44d")))
                .andExpect((ResultMatcher) jsonPath("$.releaseYear", is(Integer.valueOf(2016))))
                .andReturn();

        mockMvc.perform(delete("/songs/3"));


        mockMvc.perform(post("/songs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(new Song(3,  "Alvvays", "Marry Me, Archie", "You've expressed explicitly your contempt for matrimony", "Alvvays", "3:14", "https://lastfm.freetls.fastly.net/i/u/770x0/63ea8d50b43146e7c64414891c20d378.jpg#63ea8d50b43146e7c64414891c20d378", 2014)))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.text", is("Successfully created a song in the database.")))
                .andReturn();
    }

    private static String toJson(Song newSong) throws JsonProcessingException {
        System.out.println(new ObjectMapper().writeValueAsString(newSong));
        return new ObjectMapper().writeValueAsString(newSong);
    }

//
//     $.title - accessing one attribute from an object
//     $.ingredients[0] - accessing the first ingredient
//     $[0].name - accessing one attribute from the first object in a list
}

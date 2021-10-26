package com.nology.SpringREST.controller;

import com.nology.SpringREST.entity.Song;
import com.nology.SpringREST.repository.SongsRepository;
import com.nology.SpringREST.entity.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class SongsController {

    private final SongsRepository repository = new SongsRepository();
    // INDEX - Find all songs
    // GET /songs
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping("/songs")
    public ResponseEntity<List<Song>> indexSongs() {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
    }

    // SHOW - Get information about one song, using its id
    // GET /songs/{id}
    @GetMapping("/songs/{id}")
    public ResponseEntity<Song> showSong(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findSongsById(id));
    }

    // CREATE - Create a new song
    // POST /songs
    @PostMapping("/songs")
    public ResponseEntity<Message> createSong(@RequestBody Song newSong) {
        repository.addSong(newSong);
        Message successMessage = new Message("Successfully created a song in the database.");
        return ResponseEntity.status(HttpStatus.CREATED).body(successMessage);
    }

    // UPDATE - Change an existing song, using its id
    // PUT or PATCH /songs/{id}
    @PutMapping("/songs/{id}")
    public ResponseEntity<Song> updateSong(@PathVariable int id, @RequestBody Song newSong) {
        Song updatedSong = repository.updateSongById(id, newSong);
        return ResponseEntity.status(HttpStatus.OK).body(updatedSong);
    }

    // DELETE - Remove a song with a specific id
    // DELETE /songs/{id}
    @DeleteMapping("/songs/{id}")
    public ResponseEntity<Message> deleteSong(@PathVariable int id) {
        repository.deleteSongById(id);
        return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new Message("Successfully deleted song with id " + id));
    }
}

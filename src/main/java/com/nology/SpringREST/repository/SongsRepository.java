package com.nology.SpringREST.repository;

import com.nology.SpringREST.entity.Song;
import com.nology.SpringREST.exceptions.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class SongsRepository {
    private List<Song> songDatabase = new ArrayList<Song>(List.of(
            new Song(1,  "Taylor Swift", "All You Had To Do Was Stay", "All you had to do was stay", "1989", "3:13", "https://lastfm.freetls.fastly.net/i/u/770x0/95f441a398374c7acc4ac72667ceb42e.jpg#95f441a398374c7acc4ac72667ceb42e", 2014),
            new Song(2,  "Angel Olsen", "Lark", "Every time I turn to you, I see the past", "All Mirrors", "6:23", "https://lastfm.freetls.fastly.net/i/u/770x0/abd51ea7d902b578144a6d71bb09e361.jpg#abd51ea7d902b578144a6d71bb09e361", 2019),
            new Song(3,  "Alvvays", "Marry Me, Archie", "You've expressed explicitly your contempt for matrimony", "Alvvays", "3:14", "https://lastfm.freetls.fastly.net/i/u/770x0/63ea8d50b43146e7c64414891c20d378.jpg#63ea8d50b43146e7c64414891c20d378", 2014)
    ));

    public List<Song> findAll() {
        return songDatabase;
    }

    public Song findSongsById(int id) {
        Song foundSong = songDatabase.stream()
                .filter((song) -> song.getId() == id)
                .findFirst()
                .orElse(null);
        if (foundSong == null) throw new ResourceNotFoundException("Couldn't find song with id: " + id); // single line if is possible without curly braces.
        return foundSong;
    }

    public void addSong(Song newSong) {
        songDatabase.add(newSong);
    }

    public Song updateSongById(int id, Song newSong) {
        Song existingSong = findSongsById(id);
        int index = songDatabase.indexOf(existingSong);
        songDatabase.set(index, newSong);
        Song updatedSong = findSongsById(id);
        return updatedSong;
    }

    public void deleteSongById(int id) {
        songDatabase.remove(findSongsById(id));
    }
}

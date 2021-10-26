package com.nology.SpringREST.entity;

import java.util.ArrayList;
import java.util.List;

public class Song {
    private int id;
    private String artist;
    private String songTitle;
    private String songLyric;
    private String songAlbum;
    private String songDuration;
    private String albumArt;
    private int releaseYear;

    public Song(int id, String artist, String songTitle, String songLyric, String songAlbum, String songDuration, String albumArt, int releaseYear) {
        this.id = id;
        this.artist = artist;
        this.songTitle = songTitle;
        this.songLyric = songLyric;
        this.songAlbum = songAlbum;
        this.songDuration = songDuration;
        this.albumArt = albumArt;
        this.releaseYear = releaseYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getSongLyric() {
        return songLyric;
    }

    public void setSongLyric(String songLyric) {
        this.songLyric = songLyric;
    }

    public String getSongAlbum() {
        return songAlbum;
    }

    public void setSongAlbum(String songAlbum) {
        this.songAlbum = songAlbum;
    }

    public String getSongDuration() {
        return songDuration;
    }

    public void setSongDuration(String songDuration) {
        this.songDuration = songDuration;
    }

    public String getAlbumArt() {
        return albumArt;
    }

    public void setAlbumArt(String albumArt) {
        this.albumArt = albumArt;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
}


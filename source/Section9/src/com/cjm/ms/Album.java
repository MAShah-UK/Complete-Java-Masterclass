package com.cjm.ms;

import java.util.ArrayList;
import java.util.List;

public class Album {
    private String band;
    private String name;
    private List<Song> songs = new ArrayList<>();

    public Album(String band, String name) {
        this.band = band;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void add(Song song) {
        song.setAlbum(name);
        songs.add(song);
    }

    // Use one-based index for trackNumber.
    public Song findSong(int trackNumber) {
        if (trackNumber > 1 || trackNumber <= songs.size()) {
            return songs.get(trackNumber - 1);
        } else {
            System.out.println("Track number out of range.");
            return null;
        }
    }

    public Song findSong(String songName) {
        for (Song song : songs) {
            if (song.getName().equals(songName)) {
                return song;
            }
        }
        return null;
    }

    public void remove(String songName) {
        Song song = findSong(songName);
        songs.remove(song);
    }

    public int numberOfSongs() {
        return songs.size();
    }

    public void printSongsList() {
        for (int i = 0; i < songs.size(); i++) {
            String name = songs.get(i).getName();
            System.out.println("Track " + i + ": " + name);
        }
    }
}

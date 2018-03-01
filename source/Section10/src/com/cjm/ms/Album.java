package com.cjm.ms;

import java.util.ArrayList;
import java.util.List;

// Outer class.
public class Album {
    private String band;
    private String name;
    // Original code that was replaced by inner class.
    // private List<Song> songs = new ArrayList<>();
    private SongList songs = new SongList();

    // Private non-static inner class.
    private class SongList {
        // 'SongList.songs' is shadowing 'Album.songs'. Not recommended.
        private List<Song> songs = new ArrayList<>();

        public void add(Song song) {
            songs.add(song);
        }

        public Song get(int index) {
            return songs.get(index);
        }

        // Use one-based index for trackNumber.
        public Song findSong(int trackNumber) {
            if (trackNumber > 1 || trackNumber <= songs.size()) {
                return get(trackNumber - 1);
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

        public int size() {
            return songs.size();
        }

        public void remove(Song song) {
            songs.remove(song);
        }
    }

    public Album(String band, String name) {
        this.band = band;
        this.name = name;
    }

    public String getBand() {
        return band;
    }

    public String getName() {
        return name;
    }

    public void add(Song song) {
        song.setAlbum(this);
        songs.add(song);
    }

    public Song findSong(int trackNumber) {
        return songs.findSong(trackNumber);
    }

    public Song findSong(String songName) {
        return songs.findSong(songName);
    }

    public void remove(String songName) {
        Song song = songs.findSong(songName);
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

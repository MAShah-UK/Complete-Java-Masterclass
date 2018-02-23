package com.cjm.ms;

import java.util.LinkedList;
import java.util.ListIterator;

public class Playlist {
    private LinkedList<Song> songs = new LinkedList<>();
    private ListIterator<Song> iter = songs.listIterator();
    private boolean wentForward = true;

    public void add(Song song) {
        boolean isValid = (song != null);

        if (isValid) {
            songs.add(song);
            System.out.println("Song added to playlist: " + song.getName());
        } else {
            System.out.println("Song doesn't exist.");
        }
    }

    public void removeSong() {
        if (songs.isEmpty()) {
            System.out.println("Playlist is empty.");
        } else {
            iter.remove();
            System.out.println("Song removed.");
        }

    }

    private void adjustDirection(boolean goingForward) {
        // Direction is the same as before.
        if (goingForward == wentForward) {
            return;
        }

        if (goingForward) {
            if (iter.hasNext()) {
                iter.next();
            }
        } else {
            if (iter.hasPrevious()) {
                iter.previous();
            }
        }
        wentForward = goingForward;
    }

    public void skipForwards() {
        adjustDirection(true);

        Song song;
        if (iter.hasNext()) {
            song = iter.next();
        } else { // Loop from beginning.
            iter = songs.listIterator();
            song = iter.next();
        }

        System.out.println("Now playing: " + song.getName());
    }

    public void skipBackwards() {
        adjustDirection(false);

        Song song;
        if (iter.hasPrevious()) {
            song = iter.previous();
        } else { // Loop from end.
            iter = songs.listIterator(songs.size()-1);
            song = iter.previous();
        }

        System.out.println("Now playing: " + song.getName());
    }

    public void replaySong() {

    }

    public void listSongs() {
        System.out.println("Songs in playlist include: ");
        int i = 0;
        for (Song song : songs) {
            i++;
            System.out.println("Track " + i + ": " + song.getName());
        }
    }
}

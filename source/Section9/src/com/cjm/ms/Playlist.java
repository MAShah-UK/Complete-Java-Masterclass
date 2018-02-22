package com.cjm.ms;

import java.util.LinkedList;
import java.util.ListIterator;

public class Playlist {
    private LinkedList<Song> songs = new LinkedList<>();
    private ListIterator<Song> iter = songs.listIterator();
    private boolean wentForward = true; // TODO: Implement.

    public void add(Song song) {
        songs.add(song);
    }

    public void removeSong() {
        if (!songs.isEmpty()) {
            iter.remove();
        }
    }

    public void adjustDirection(boolean goingForward) {
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
            System.out.println("Track " + i + ": " + song.getName()s);
        }

        System.out.println();
    }
}

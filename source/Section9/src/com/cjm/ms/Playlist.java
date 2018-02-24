package com.cjm.ms;

import java.util.LinkedList;
import java.util.ListIterator;

public class Playlist {
    private LinkedList<Song> songs = new LinkedList<>();
    private ListIterator<Song> iter;
    private boolean wentForward = true;

    public void add(Song song) {
        if (song != null) {
            songs.add(song);
            System.out.println("Song added to playlist: " + song.getName());
        } else {
            System.out.println("Song doesn't exist.");
        }

        // Iter must be rest if changes are made to original list.
        // Otherwise will get ConcurrentModificationException.
        if (songs.size()>1 && iter != null) {
            int index = (iter.hasNext()) ? iter.nextIndex() : iter.previousIndex()+1;
            iter = songs.listIterator(index);
        }
    }

    public void play() {
        if (songs.isEmpty()) {
            System.out.println("There are no songs in your playlist.");
        } else if (iter == null) {
            iter = songs.listIterator();
            Song song = iter.next();
            System.out.println("Currently playing: " + song.getName());
        } else {
            Song song = getCurrent();
            System.out.println("Currently playing: " + song.getName());
        }
    }

    public void removeSong() {
        // TODO/BUG: Can't remove two in a row.
        if (iter == null) {
            System.out.println("Play your playlist first.");
            return;
        } else {
            iter.remove();
            System.out.println("Song removed.");
        }
    }

    private Song adjustDirection(boolean goingForward) {
        Song song = null;

        // Direction is the same as before.
        if (goingForward == wentForward) {
            return song;
        }

        if (goingForward) {
            if (iter.hasNext()) {
                song = iter.next();
            }
        } else {
            if (iter.hasPrevious()) {
                song = iter.previous();
            }
        }
        wentForward = goingForward;

        return song;
    }

    private Song getCurrent() {

        if (iter == null) {
            return null;
        }

        Song song = adjustDirection(!wentForward);
        adjustDirection(!wentForward);
        return song;
    }

    public void skipForwards() {
        if (iter == null) {
            System.out.println("Play your playlist first.");
            return;
        }

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
        if (iter == null) {
            System.out.println("Play your playlist first.");
            return;
        }

        adjustDirection(false);

        Song song;
        if (iter.hasPrevious()) {
            song = iter.previous();
        } else { // Loop from end.
            iter = songs.listIterator(songs.size());
            song = iter.previous();
        }

        System.out.println("Now playing: " + song.getName());
    }

    public void replaySong() {
        Song song = getCurrent();
        System.out.println("Replaying: " + song.getName());
    }

    public void listSongs() {
        if (songs.isEmpty()) {
            System.out.println("Playlist is empty.");
            return;
        }

        System.out.println("Songs in playlist include: ");
        int i = 0;

        String currentSongName = (getCurrent() == null) ? "" : getCurrent().getName();
        for (Song song : songs) {
            i++;
            String band = song.getAlbum().getBand();
            String album = song.getAlbum().getName();
            String name = song.getName();

            if (name.equals(currentSongName)) {
                System.out.print("> ");
            }

            System.out.println("Track " + i + ": " + band + " - " + album + " - " + name);
        }
    }
}

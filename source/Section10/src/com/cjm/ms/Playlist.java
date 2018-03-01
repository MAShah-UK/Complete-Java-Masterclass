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
        play(getCurrent());
    }

    private void play(Song song) {
        if (songs.isEmpty()) {
            System.out.println("There are no songs in your playlist.");
        } else if (iter == null) {
            iter = songs.listIterator();
            song = iter.next();
            System.out.println("Currently playing: " + song.getName());
        } else {
            System.out.println("Currently playing: " + song.getName());
        }
    }

    public void removeSong() {
        if (iter == null) {
            System.out.println("Play your playlist first.");
            return;
        } else {
            iter.remove();
            System.out.println("Song removed.");
            skipForwards();
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
        if (iter == null || songs.isEmpty()) {
            System.out.println("Add songs to your playlist then play it.");
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

        play(song);
    }

    public void skipBackwards() {
        if (iter == null || songs.isEmpty()) {
            System.out.println("Add songs to your playlist then play it.");
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

        play(song);
    }

    public void replaySong() {
        Song song = getCurrent();
        play(song);
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

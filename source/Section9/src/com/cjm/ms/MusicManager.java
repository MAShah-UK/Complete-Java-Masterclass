package com.cjm.ms;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Exercise:
// Create a program that implements a playlist for songs.
// Create a Song class having Title and Duration for a song.
// The program will have an Album class containing a list of songs.
// The songs will be stored in an ArrayList.
// Songs from different albums can be added to the playlist and will appear in the list in the order
// they are added.
// Once the songs have been added to the playlist, create a menu of options to:
//    - Skip forward to the next song.
//    - Skip backwards to a previous song.
//    - Replay the current song.
//    - List the songs in the playlist
//    - Quit.
// A song must exist in an album before it can be added to the playlist (so you can only play songs that
// you own).
// Hint:  To replay a song, consider what happened when we went back and forth from a city before we
// started tracking the direction we were going.
// As an optional extra, provide an option to remove the current song from the playlist
// (hint: ListIterator.remove()).

public class MusicManager {
    private List<Album> albums = new ArrayList<>();
    private Playlist pList = new Playlist();
    private Scanner sc = new Scanner(System.in);

    public MusicManager() {
        addAlbums();
    }

    private void addAlbums() {
        Album lp_ht = new Album("Linkin Park", "Hybrid Theory");
        lp_ht.add(new Song("Papercut", 184));
        lp_ht.add(new Song("One Step Closer", 155));
        lp_ht.add(new Song("With You", 203));
        albums.add(lp_ht);

        Album e_r = new Album("Eminem", "Revival");
        e_r.add(new Song("Walk on Water", 304));
        e_r.add(new Song("Believe", 315));
        e_r.add(new Song("Chloraseptic", 301));
        albums.add(e_r);

        Album kl_d = new Album("Kendrick Lamar", "Damn");
        kl_d.add(new Song("Blood", 118));
        kl_d.add(new Song("DNA", 185));
        kl_d.add(new Song("Yah", 160));
        albums.add(kl_d);
    }

    public void startInteractiveMenu() {
        System.out.println("Welcome to the playlist manager.");
        printOptions();

        boolean quit = false;
        String arg;
        while(!quit) {
            System.out.println();
            System.out.print("Pick an option: ");
            int option = sc.nextInt();
            sc.nextLine();
            System.out.println();

            switch(option) {
                case 1: // Print options.
                    printOptions();
                    break;
                case 2: // Add song.
                    addSong();
                    break;
                case 3: // Remove song.
                    pList.removeSong();
                    break;
                case 4: // Skip forwards.
                    pList.skipForwards();
                    break;
                case 5: // Skip backwards.
                    pList.skipBackwards();
                    break;
                case 6: // Replay song.
                    pList.replaySong();
                    break;
                case 7: // List songs.
                    pList.listSongs();
                    break;
                case 8: // Quit.
                    System.out.println("Goodbye.");
                    quit = true;
                    break;
            }
        }
    }

    private void printOptions() {
        System.out.println("Your options are:");
        System.out.println("1 - Print options.");
        System.out.println("2 - Add song.");
        System.out.println("3 - Remove song.");
        System.out.println("4 - Skip forwards.");
        System.out.println("5 - Skip backwards.");
        System.out.println("6 - Replay song.");
        System.out.println("7 - List songs.");
        System.out.println("8 - Quit.");
    }

    private Song findSong(String songName) {
        Song song = null;
        for (Album album : albums) {
            song = album.findSong(songName);
            if (song != null) {
                // TODO/BUG: Assumes that song names are unique.
                return song;
            }
        }
        return song;
    }

    private void addSong() {
        System.out.print("Which song?");
        Song song = findSong(sc.nextLine());
        pList.add(song);
    }
}

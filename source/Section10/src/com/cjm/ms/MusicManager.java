package com.cjm.ms;

import javax.swing.text.View;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Exercise:
// Modify the playlist challenge so that the Album class uses an inner class.
// Instead of using an ArrayList to hold its tracks, it will use an inner class called SongList.
// The inner SongList class will use an ArrayList and will provide a method to add a song.
// It will also provide findSong() methods which will be used by the containing Album class
// to add songs to the playlist.
// Neither the Song class or the Main class should be changed.

public class MusicManager {
    private List<String> options = new ArrayList<>(9);
    private List<Album> albums = new ArrayList<>();
    private Playlist pList = new Playlist();
    private Scanner sc = new Scanner(System.in);

    public MusicManager() {
        addOptions();
        addAlbums();
    }
    
    private void addOptions() {
        options.add("1 - Print options.");
        options.add("2 - Play playlist / Current Song.");
        options.add("3 - Add song.");
        options.add("4 - Remove song.");
        options.add("5 - Skip forwards.");
        options.add("6 - Skip backwards.");
        options.add("7 - Replay song.");
        options.add("8 - List songs.");
        options.add("9 - Quit.");
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
            System.out.print("Pick an option (1 for help): ");
            int option = sc.nextInt();
            sc.nextLine();
            String confirmation = option > options.size() ? Integer.toString(option) : options.get(option-1);
            System.out.println("You chose: " + confirmation);
            System.out.println();

            switch(option) {
                case 1: // Print options.
                    printOptions();
                    break;
                case 2: // Play playlist.
                    pList.play();
                    break;
                case 3: // Add song.
                    addSong();
                    break;
                case 4: // Remove song.
                    pList.removeSong();
                    break;
                case 5: // Skip forwards.
                    pList.skipForwards();
                    break;
                case 6: // Skip backwards.
                    pList.skipBackwards();
                    break;
                case 7: // Replay song.
                    pList.replaySong();
                    break;
                case 8: // List songs.
                    pList.listSongs();
                    break;
                case 9: // Quit.
                    System.out.println("Goodbye.");
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid selection. Try again.");
                    break;
            }
        }
    }

    private void printOptions() {
        System.out.println("Your options are:");
        for (String opt : options) {
            System.out.println(opt);
        }
    }

    private Song findSong(String songName) {
        Song song = null;
        for (Album album : albums) {
            song = album.findSong(songName);
            if (song != null) {
                // TODO/Bug: Assumes that song names are unique.
                // TODO/BugFix: Could return an ArrayList of possible songs, and let user choose.
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

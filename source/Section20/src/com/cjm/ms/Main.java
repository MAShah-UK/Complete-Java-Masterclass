package com.cjm.ms;

import com.cjm.ms.model.Artist;
import com.cjm.ms.model.DataSource;
import com.cjm.ms.model.SongArtist;

import java.sql.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        createTable();
        useMVCPattern();
    }

    // Practice working with SQL via JDBC.
    public static void createTable() {
        System.out.println("BEGIN: createTable");

        // Use constants to avoid having to rename strings everywhere.
        final String DB_NAME = "testjava.db";
        final String CONNECTION_STRING = "jdbc:sqlite:data\\" + DB_NAME;
        final String TABLE_CONTACTS = "contacts";
        final String COL_NAME = "name";
        final String COL_PHONE = "phone";
        final String COL_EMAIL = "email";

        class InsertData {
            private Statement stat;
            public InsertData(Statement stat) {
                this.stat = stat;
            }
            public void insertContact(String name, int phone, String email) throws SQLException {
                String values = "VALUES('" + name + "', " + phone + ", '" + email + "')";
                stat.execute("INSERT INTO " + TABLE_CONTACTS +
                        "(" + COL_NAME + ", " +
                        COL_PHONE + ", " +
                        COL_EMAIL +
                        ") " + values);
            }
        }

        try {
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            //conn.setAutoCommit(false); // Disables automatic commitment of database changes.
            Statement statement = conn.createStatement();

            // Populate database.
            statement.execute("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS +
                                   "(" + COL_NAME + " TEXT, " +
                                         COL_PHONE + " INTEGER, " +
                                         COL_EMAIL + " TEXT" +
                                   ")");

            InsertData ID = new InsertData(statement);
            ID.insertContact("Tim", 6545678, "tim@email.com");
            ID.insertContact("Joe", 45632, "joe@anywhere.com");
            ID.insertContact("Jane", 4829484, "jane@somewhere.com");
            ID.insertContact("Fido", 9038, "dog@email.com");

            statement.execute("UPDATE " + TABLE_CONTACTS + " " +
                                   "SET " + COL_PHONE + "=5566789 " +
                                   "WHERE " + COL_NAME + "='Joe'");
            statement.execute("DELETE FROM " + TABLE_CONTACTS + " " +
                                   "WHERE " + COL_NAME + "='Joe'");

            // Query database.
//            statement.execute("SELECT * FROM " + TABLE_CONTACTS);
//            ResultSet results = statement.getResultSet(); // Results of query stored in ResultSet.
            ResultSet results = statement.executeQuery("SELECT * FROM " + TABLE_CONTACTS); // Identical.
            while(results.next()) {
                // Cast data to the correct type.
                System.out.println(results.getString(COL_NAME) + " " +
                        results.getInt(COL_PHONE) + " " +
                        results.getString(COL_EMAIL));
            }

            // Close resources when they're no longer required.
            results.close();
            statement.close();
            //conn.commit(); // Required if auto commit is disabled.
            conn.close();
        } catch(SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static void useMVCPattern() {
        System.out.println("\nBEGIN: useMVCPattern");

        DataSource dataSource = new DataSource();
        dataSource.open();

        System.out.println("List of artists: ");
        List<Artist> artists = dataSource.queryArtist(DataSource.ORDER_BY_ASC);
        for(Artist artist: artists) {
            System.out.println("\tID: " + artist.getId() + ", Name: " + artist.getName());
        }
        System.out.println();

        System.out.println("Albums by Iron Maiden: ");
        List<String> albumsForArtist = dataSource.queryAlbumsForArtist("Iron Maiden", DataSource.ORDER_BY_ASC);
        for (String album: albumsForArtist) {
            System.out.println("\t" + album);
        }
        System.out.println();

        System.out.println("The song Heartless is by: ");
        List<SongArtist> songArtists = dataSource.queryArtistsForSongs("Heartless", DataSource.ORDER_BY_ASC);
        for(SongArtist songArtist: songArtists) {
            System.out.println("\tName: " + songArtist.getArtistName() +
                    ", Album: " + songArtist.getAlbumName() +
                    ", Track: " + songArtist.getTrack());
        }
        System.out.println();

        System.out.println("Get songs table metadata: ");
        dataSource.querySongsMetadata();
        System.out.println();

        dataSource.getCount(DataSource.TABLE_SONGS);
        System.out.println();

        dataSource.createViewForSongArtists();
        System.out.println();

        System.out.println("Go Your Own Way is by:");
        songArtists = dataSource.querySongInfoView("Go Your Own Way");
        if(songArtists.isEmpty()) {
            System.out.println("Couldn't find the artist for the song.");
        } else {
            for(SongArtist artist: songArtists) {
                System.out.println("\t Artist's name: " + artist.getArtistName() +
                                " Album's name: " + artist.getAlbumName() +
                                " Track number: " + artist.getTrack());
            }
        }

        dataSource.close();
    }
}

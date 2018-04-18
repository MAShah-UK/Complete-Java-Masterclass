package com.cjm.ms.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataSource {
    public static final String DB_NAME = "music.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:Data\\" + DB_NAME;

    public static final String TABLE_ARTISTS = "artists";
    public static final String COL_ARTIST_ID = "_id";
    public static final String COL_ARTIST_NAME = "name";

    public static final String TABLE_ALBUMS = "albums";
    public static final String COL_ALBUM_ID = "_id";
    public static final String COL_ALBUM_NAME = "name";
    public static final String COL_ALBUM_ARTIST = "artist";

    public static final String TABLE_SONGS = "songs";
    public static final String COL_SONG_TRACK = "track";
    public static final String COL_SONG_TITLE = "title";
    public static final String COL_SONG_ALBUM = "album";

    private Connection conn;

    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
        } catch(SQLException e) {
            System.out.println("Couldn't connect to database: " + e.getMessage());
        }
        return conn != null;
    }

    public void close() {
        try {
            if(conn != null) {
                conn.close();
            }
        } catch(SQLException e) {
            System.out.println("Couldn't close connection: " + e.getMessage());
        }
    }

    public List<Artist> queryArtist() {
//        Statement statement = null;
//        ResultSet results = null;
//        // Messy way of dealing with resources.
//        try {
//            statement = conn.createStatement();
//            results = statement.executeQuery("SELECT * FROM " + TABLE_ARTISTS);
//
//            List<Artist> artists = new ArrayList<>();
//            while(results.next()) {
//                Artist artist = new Artist();
//                artist.setId(results.getInt(COL_ARTIST_ID));
//                artist.setName(results.getString(COL_ARTIST_NAME));
//                artists.add(artist);
//            }
//
//            return artists;
//        } catch(SQLException e) {
//            System.out.println("Query failed: " + e.getMessage());
//        } finally {
//            try {
//                if(results != null) {
//                    results.close();
//                }
//            } catch(SQLException e) {
//                System.out.println("Couldn't close results: " e.getMessage());
//            }
//            try {
//                if(statement != null) {
//                    statement.close();
//                }
//            } catch(SQLException e) {
//                System.out.println("Couldn't close statement: " e.getMessage());
//            }
//        }

        // Try-with-resources statements makes it a lot clearer and automatically closes resources.
        // Resources implement AutoCloseable.
        try(Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM " + TABLE_ARTISTS)) {

            List<Artist> artists = new ArrayList<>();
            while(results.next()) {
                Artist artist = new Artist();
                artist.setId(results.getInt(COL_ARTIST_ID));
                artist.setName(results.getString(COL_ARTIST_NAME));
                artists.add(artist);
            }

            return artists;
        } catch(SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        }

        return null;
    }
}

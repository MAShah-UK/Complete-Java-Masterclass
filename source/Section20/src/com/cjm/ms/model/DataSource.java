package com.cjm.ms.model;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataSource implements AutoCloseable {
    private static final String DB_NAME = "music.db";
    private static final String CONNECTION_STRING = "jdbc:sqlite:Data\\" + DB_NAME;

    public static final String TABLE_ARTISTS = "artists";
    public static final String COL_ARTIST_ID = "_id";
    public static final String COL_ARTIST_NAME = "name";
    public static final int INDEX_ARTIST_ID = 1;
    public static final int INDEX_ARTIST_NAME = 2;

    public static final String TABLE_ALBUMS = "albums";
    public static final String COL_ALBUM_ID = "_id";
    public static final String COL_ALBUM_NAME = "name";
    public static final String COL_ALBUM_ARTIST = "artist";
    public static final int INDEX_ALBUM_ID = 1;
    public static final int INDEX_ALBUM_NAME = 2;
    public static final int INDEX_ALBUM_ARTIST = 3;

    public static final String TABLE_SONGS = "songs";
    public static final String COL_SONG_ID = "_id";
    public static final String COL_SONG_TRACK = "track";
    public static final String COL_SONG_TITLE = "title";
    public static final String COL_SONG_ALBUM = "album";
    public static final int INDEX_SONG_ID = 1;
    public static final int INDEX_SONG_TRACK = 2;
    public static final int INDEX_SONG_TITLE = 3;
    public static final int INDEX_SONG_ALBUM = 4;

    public static final String TABLE_ARTIST_SONG_VIEW = "artist_list";

    public static final int ORDER_BY_NONE = 1;
    public static final int ORDER_BY_ASC = 2;
    public static final int ORDER_BY_DESC = 3;

    private Connection conn;
    private PreparedStatement querySongInfoView;
    private PreparedStatement insertIntoArtists;
    private PreparedStatement insertIntoAlbums;
    private PreparedStatement insertIntoSongs;
    private PreparedStatement queryArtist;
    private PreparedStatement queryAlbum;

    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);

            String queryViewSongInfoPrep = new StringBuilder()
                    .append("SELECT ").append(COL_ARTIST_NAME).append(", ").append(COL_SONG_ALBUM).append(", ")
                    .append(COL_SONG_TRACK)
                    .append(" FROM ").append(TABLE_ARTIST_SONG_VIEW)
                    .append(" WHERE ").append(" = ?").toString();
            querySongInfoView = conn.prepareStatement(queryViewSongInfoPrep);

            String insertArtist = new StringBuilder().append("INSERT INTO ")
                    .append(TABLE_ARTISTS).append('(').append(COL_ARTIST_NAME).append(')')
                    .append(" VALUES(?)")
                    .toString();
            // Returns the ID for the new entry.
            insertIntoArtists = conn.prepareStatement(insertArtist, Statement.RETURN_GENERATED_KEYS);

            String insertAlbum = new StringBuilder().append("INSERT INTO ").append(TABLE_ALBUMS)
                    .append('(').append(COL_ALBUM_NAME).append(", ").append(COL_ALBUM_ARTIST).append(") VALUES(?, ?)")
                    .toString();
            insertIntoAlbums = conn.prepareStatement(insertAlbum, Statement.RETURN_GENERATED_KEYS);

            String insertSong = new StringBuilder().append("INSERT INTO ").append(TABLE_SONGS)
                    .append('(').append(COL_SONG_TRACK).append(", ").append(COL_SONG_TITLE).append(", ")
                    .append(COL_SONG_ALBUM).append(") VALUES(?, ?, ?)").toString();
            insertIntoSongs = conn.prepareStatement(insertSong, Statement.RETURN_GENERATED_KEYS);

            String sqlQueryArtist = new StringBuilder()
                    .append("SELECT ").append(COL_ARTIST_ID)
                    .append(" FROM ").append(TABLE_ARTISTS)
                    .append(" WHERE ").append(COL_ARTIST_NAME).append(" = ?").toString();
            queryArtist = conn.prepareStatement(sqlQueryArtist);

            String sqlQueryAlbum = new StringBuilder()
                    .append("SELECT ").append(COL_ALBUM_ID)
                    .append(" FROM ").append(TABLE_ALBUMS)
                    .append(" WHERE ").append(COL_ALBUM_NAME).append(" = ?").toString();
            queryAlbum = conn.prepareStatement(sqlQueryAlbum);
        } catch(SQLException e) {
            System.out.println("Couldn't connect to database: " + e.getMessage());
        }
        return conn != null;
    }

    @Override
    public void close() {
        try {
            if(querySongInfoView != null) {
                querySongInfoView.close();
            }
            if(insertIntoArtists != null) {
                insertIntoArtists.close();
            }
            if(insertIntoAlbums != null) {
                insertIntoAlbums.close();
            }
            if(insertIntoSongs != null) {
                insertIntoSongs.close();
            }
            if(queryArtist != null) {
                queryArtist.close();
            }
            if(queryAlbum != null) {
                queryAlbum.close();
            }
            if(conn != null) {
                conn.close();
            }
        } catch(SQLException e) {
            System.out.println("Couldn't close connection: " + e.getMessage());
        }
    }

    public List<Artist> queryArtist(int sortOrder) {
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

        StringBuilder sb = new StringBuilder("SELECT * FROM ");
        sb.append(TABLE_ARTISTS);
        if(sortOrder != ORDER_BY_NONE) {
            sb.append(" ORDER BY ");
            sb.append(COL_ARTIST_NAME);
            sb.append(" COLLATE NOCASE ");
            if(sortOrder == ORDER_BY_DESC) {
                sb.append("DESC");
            } else {
                sb.append("ASC");
            }
        }

        // Try-with-resources statements makes it a lot clearer and automatically closes resources.
        // Resources implement AutoCloseable.
        try(Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery(sb.toString())) {
            List<Artist> artists = new ArrayList<>();
            while(results.next()) {
                Artist artist = new Artist();
                artist.setId(results.getInt(INDEX_ARTIST_ID));
                artist.setName(results.getString(INDEX_ARTIST_NAME));
                artists.add(artist);
            }
            return artists;
        } catch(SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    // Practice working on SQL queries with basic joins and constraints.
    public List<String> queryAlbumsForArtist(String artistName, int sortOrder) {
        // SELECT albums.name FROM albums INNER JOIN artists ON albums.artist = artists._id
        // WHERE artists.name = "Carole King" ORDER BY albums.name COLLATE NOCASE ASC
        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append(TABLE_ALBUMS).append('.').append(COL_ALBUM_NAME);
        sb.append(" FROM ");
        sb.append(TABLE_ALBUMS);
        sb.append(" INNER JOIN ");
        sb.append(TABLE_ARTISTS);
        sb.append(" ON ");
        sb.append(TABLE_ALBUMS).append('.').append(COL_ALBUM_ARTIST);
        sb.append(" = ");
        sb.append(TABLE_ARTISTS).append('.').append(COL_ARTIST_ID);
        sb.append(" WHERE ");
        sb.append(TABLE_ARTISTS).append('.').append(COL_ARTIST_NAME);
        sb.append(" = \"").append(artistName).append("\"");
        if(sortOrder != ORDER_BY_NONE) {
            sb.append(" ORDER BY ");
            sb.append(TABLE_ALBUMS).append('.').append(COL_ALBUM_NAME);
            sb.append(" COLLATE NOCASE ");
            sb.append((sortOrder == ORDER_BY_DESC) ? "DESC" : "ASC");
        }

        try(Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery(sb.toString())) {
            List<String> albums = new ArrayList<>();
            while(results.next()) {
                albums.add(results.getString(1));
            }
            return albums;
        } catch(SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    // Practice working with complex SQL queries with multi-joins, constraints, and ordering.
    public List<SongArtist> queryArtistsForSongs(String songName, int sortOrder) {
        StringBuilder query = new StringBuilder()
                .append("SELECT ").append(TABLE_ARTISTS).append('.').append(COL_ARTIST_NAME).append(", ")
                .append(TABLE_ALBUMS).append('.').append(COL_ALBUM_NAME).append(", ")
                .append(TABLE_SONGS).append('.').append(COL_SONG_TRACK)
                .append(" FROM ").append(TABLE_SONGS)
                .append(" INNER JOIN ").append(TABLE_ALBUMS).append(" ON ")
                .append(TABLE_SONGS).append('.').append(COL_SONG_ALBUM).append(" = ")
                .append(TABLE_ALBUMS).append('.').append(COL_ALBUM_ID)
                .append(" INNER JOIN ").append(TABLE_ARTISTS).append(" ON ")
                .append(TABLE_ALBUMS).append('.').append(COL_ALBUM_ARTIST).append(" = ")
                .append(TABLE_ARTISTS).append('.').append(COL_ARTIST_ID)
                .append(" WHERE ").append(TABLE_SONGS).append('.').append(COL_SONG_TITLE)
                .append(" = \"").append(songName).append("\"");
        if(sortOrder != ORDER_BY_NONE) {
            query.append(" ORDER BY ").append(TABLE_ARTISTS).append('.').append(COL_ARTIST_NAME).append(", ")
                    .append(TABLE_ALBUMS).append('.').append(COL_ALBUM_NAME)
                    .append(" COLLATE NOCASE ").append(sortOrder == ORDER_BY_DESC ? "DESC" : "ASC");
        }
        System.out.println("SQL query: " + query.toString());

        try(Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery(query.toString())) {
            List<SongArtist> songArtists = new ArrayList<>();
            while(results.next()) {
                SongArtist songArtist = new SongArtist();
                songArtist.setArtistName(results.getString(1));
                songArtist.setAlbumName(results.getString(2));
                songArtist.setTrack(results.getInt(3));
                songArtists.add(songArtist);
            }
            return songArtists;
        } catch(SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    // Practice getting column metadata.
    public void querySongsMetadata() {
        String sql = "SELECT * FROM " + TABLE_SONGS;
        try(Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery(sql)) {
            ResultSetMetaData meta = results.getMetaData();
            int numColumns = meta.getColumnCount();
            for(int i = 1; i <= numColumns; i++) {
                System.out.format("Column %d in the songs table is names %s\n",
                            i, meta.getColumnName(i));
            }
        } catch(SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        }
    }

    // Practice working with SQL functions.
    public void getCount(String table) {
        String sql = "SELECT COUNT(*) AS count, MIN(_id) AS min_id FROM " + table;
        try(Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery(sql)) {
            int count = results.getInt("count");
            int min = results.getInt("min_id");
            System.out.format("Count = %d, Min = %d.", count, min);
        } catch(SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        }
    }

    // Practice working with views.
    public boolean createViewForSongArtists() {
        String sql = new StringBuilder()
                .append("CREATE VIEW IF NOT EXISTS ").append(TABLE_ARTIST_SONG_VIEW).append(" AS ")
                .append("SELECT ").append(TABLE_ARTISTS).append('.').append(COL_ARTIST_NAME).append(", ")
                .append(TABLE_ALBUMS).append('.').append(COL_ALBUM_NAME).append(" AS ").append(COL_SONG_ALBUM).append(", ")
                .append(TABLE_SONGS).append('.').append(COL_SONG_TRACK).append(", ").append(TABLE_SONGS).append('.').append(COL_SONG_TITLE)
                .append(" FROM ").append(TABLE_SONGS)
                .append(" INNER JOIN ").append(TABLE_ALBUMS).append(" ON ")
                .append(TABLE_SONGS).append('.').append(COL_SONG_ALBUM).append(" = ")
                .append(TABLE_ALBUMS).append('.').append(COL_ALBUM_ID)
                .append(" INNER JOIN ").append(TABLE_ARTISTS).append(" ON ")
                .append(TABLE_ALBUMS).append('.').append(COL_ALBUM_ARTIST).append(" = ")
                .append(TABLE_ARTISTS).append('.').append(COL_ARTIST_ID)
                .append(" ORDER BY ").append(TABLE_ARTISTS).append('.').append(COL_ARTIST_NAME).append(", ")
                .append(TABLE_ALBUMS).append('.').append(COL_ALBUM_NAME).append(", ")
                .append(TABLE_SONGS).append('.').append(COL_SONG_TRACK).toString();

        try(Statement statement = conn.createStatement()) {
            statement.execute(sql);
            return true;
        } catch(SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return false;
        }
    }

    public List<SongArtist> querySongInfoView(String title) {
        try {
            List<SongArtist> songArtists = new ArrayList<>();
            querySongInfoView.setString(1, title);
            ResultSet results = querySongInfoView.executeQuery();
            while(results.next()) {
                SongArtist songArtist = new SongArtist();
                songArtist.setArtistName(results.getString(1));
                songArtist.setAlbumName(results.getString(2));
                songArtist.setTrack(results.getInt(3));
                songArtists.add(songArtist);
            }
            return songArtists;
        } catch(SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    private int insertArtist(String name) throws SQLException {
        queryArtist.setString(1, name);
        ResultSet results = queryArtist.executeQuery();
        if(results.next()) {
            return results.getInt(1);
        } else {
            // Insert the artist.
            insertIntoArtists.setString(1, name);
            int affectedRows = insertIntoArtists.executeUpdate();
            if(affectedRows != 1) {
                throw new SQLException("Couldn't insert artist.");
            }
            ResultSet generatedKeys = insertIntoArtists.getGeneratedKeys();
            if(generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Couldn't get _id for artist.");
            }
        }
    }

    private int insertAlbum(String name, int artistId) throws SQLException {
        queryAlbum.setString(1, name);
        ResultSet results = queryAlbum.executeQuery();
        if(results.next()) {
            return results.getInt(1);
        } else {
            // Insert the artist.
            insertIntoAlbums.setString(1, name);
            insertIntoAlbums.setInt(2, artistId);
            int affectedRows = insertIntoAlbums.executeUpdate();
            if(affectedRows != 1) {
                throw new SQLException("Couldn't insert album.");
            }
            ResultSet generatedKeys = insertIntoAlbums.getGeneratedKeys();
            if(generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Couldn't get _id for album.");
            }
        }
    }

    private int insertSong(String title, String artist, String album, int track) {
        try {
            conn.setAutoCommit(false);

            int artistId = insertArtist(artist);
            int albumId = insertAlbum(album, artistId);
            insertIntoSongs.setInt(1, track);
            insertIntoSongs.setString(2, title);
            insertIntoSongs.setInt(3, albumId);

            int affectedRows = insertIntoSongs.executeUpdate();
            if(affectedRows == 1) {
                conn.commit();
            } else {
                throw new SQLException("Song insert failed.");
            }
        } catch(SQLException e) {
            System.out.println("Insert song exception: " + e.getMessage());
            try {
                System.out.println("Performing rollback.");
                conn.rollback();
            } catch(SQLException e2) {
                System.out.println("Couldn't rollback: " + e2.getMessage());
            }
        } finally {
            try {
                System.out.println("Resetting default commit behaviour.");
                conn.setAutoCommit(true);
            } catch(SQLException e) {
                System.out.println("Couldn't reset auto-commit: " + e.getMessage());
            }
        }
    }
}

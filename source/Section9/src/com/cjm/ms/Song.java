package com.cjm.ms;

public class Song {
    private String name;
    private double duration;
    private String album;

    public Song(String name, double duration) {
        this(name, duration, "");
    }

    public Song(String name, double duration, String album) {
        this.name = name;
        this.duration = duration;
        this.album = album;
    }

    public String getName() {
        return name;
    }

    public double getDuration() {
        return duration;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }
}

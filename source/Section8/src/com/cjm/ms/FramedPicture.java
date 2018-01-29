package com.cjm.ms;

public class FramedPicture {
    private String artist;
    private Dimensions dims;

    public FramedPicture(String artist, Dimensions dims) {
        this.artist = artist;
        this.dims = dims;
    }

    public void admirePicture(int ppl) {
        String plural = (ppl > 1) ? "people are" : "person is";
        System.out.println(ppl + " " + plural + " enjoying the picture.");
    }
}

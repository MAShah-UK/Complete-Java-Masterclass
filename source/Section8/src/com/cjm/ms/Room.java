package com.cjm.ms;

// Exercise:
// Create a single room of a house using composition.
// Think about the things that should be included in the room.
// Maybe physical parts of the house but furniture as well.
// Add at least one method to access an object via a getter and
// then that objects public method as you saw in the previous video
// then add at least one method to hide the object e.g. not using a getter
// but to access the object used in composition within the main class
// like you saw in this video.

public class Room {
    private Bed bed;
    private FramedPicture picture;

    public Room(Bed bed, FramedPicture picture) {
        this.bed = bed;
        this.picture = picture;
    }

    public Bed getBed() {
        return bed;
    }

    public void admirePicture(int ppl) {
        picture.admirePicture(ppl);
    }
}

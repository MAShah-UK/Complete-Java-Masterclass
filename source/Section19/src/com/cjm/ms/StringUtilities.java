package com.cjm.ms;

public class StringUtilities {
    private StringBuilder builder = new StringBuilder();
    private int charsAdded = 0;
    public void addChar(StringBuilder builder, char c) {
        // Bug: Source StringBuilder is never modified.
        this.builder.append(c);
        charsAdded++;
    }
}

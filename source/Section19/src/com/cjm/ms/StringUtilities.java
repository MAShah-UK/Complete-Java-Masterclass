package com.cjm.ms;

public class StringUtilities {
    private StringBuilder builder = new StringBuilder();
    // Setting a field watchpoint will suspend execution anytime
    // the field is modified.
    private int charsAdded = 0;
    public void addChar(StringBuilder builder, char c) {
        // Bug: Source StringBuilder is never modified.
        // A breakpoint can be set for a specific statement, by clicking
        // on that line's gutter.
        // The debugger makes it clear that the instance variable
        // is being updated rather than the provided argument.
        // this.builder.append(c); // Bug.
        builder.append(c);
        charsAdded++;
    }
    public String upperAndPrefix(String str) {
        String upper = str.toUpperCase();
        return "Prefix_" + upper;
    }
    public String addSuffix(String str) {
        return str + "_Suffix";
    }
}

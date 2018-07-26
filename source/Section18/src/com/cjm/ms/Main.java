package com.cjm.ms;

public class Main {

    public static void main(String[] args) {
        basicRegEx();
    }
    // Practice using a string literal as a RegEx.
    public static void basicRegEx() {
        System.out.println("\nBEGIN: characterClass");

        String alphaNumeric = "abcDeeeF3D30Fkjf abcD30";
        System.out.println("Original string: " + alphaNumeric);
        System.out.println();

        // RegExs allow matching patterns with strings.
        // The simplest form of a RegEx is a string literal.
        // The matching substring is replaced with Y.
        // The replacement string can be any string.
        // Use an empty string to remove matching characters.
        String regex = alphaNumeric.replaceAll("abcD", "_");
        System.out.println("'abcD' string literal regex result: " + regex);

        // A character class/set represents a set of characters.
        // The dot character class represents all characters.
        // Hence every character is replaced with Y.
        regex = alphaNumeric.replaceAll(".", "_");
        System.out.println("'.' character class regex result: " + regex);

        // To define a custom character class use square brackets.
        // It will match to any letter defined within the brackets.
        // This will replace any F or f with a Y.
        regex = alphaNumeric.replaceAll("[Ff]", "_");
        System.out.println("'[Ff]' character class regex result: " + regex);

        // To specify a range within a character class, use hyphen.
        // a-z matches any lowercase letter, A-Z matches any uppercase letter
        // 0-9 matches any digit. a-c matches a or b or c, etc.
        regex = alphaNumeric.replaceAll("[a-z]", "_");
        System.out.println("'[a-z]' character class regex result: " + regex);

        // A boundary matcher pattern matches at specific points in a string.
        // The caret boundary matcher pattern matches at the beginning of a string.
        regex = alphaNumeric.replaceAll("^abcD", "THE START");
        System.out.println("'^abcD' boundary matcher regex result: " + regex);

        // Clearly the regex above affects the original string as a match was
        // found. However, calling String.matches() will return false.
        // This is because matches() must much the entire string, whereas
        // replaceAll() is satisfied with a substring.
        System.out.println("Does '^abcD' match the string? " +
                alphaNumeric.matches("^abcD"));

        // The following regex doesn't match so the string is unchanged.
        regex = alphaNumeric.replaceAll("^hello", "THE START");
        System.out.println("'^hello' boundary matcher regex result: " + regex);

        // The dollar boundary matcher pattern matches at the end of a string.
        regex = alphaNumeric.replaceAll("D30$", "THE END");
        System.out.println("'D30$' boundary matcher regex result: " + regex);
    }
}

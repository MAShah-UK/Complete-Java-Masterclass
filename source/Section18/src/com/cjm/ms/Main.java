package com.cjm.ms;

public class Main {
    private String string = "abcDeeeF3eD30Feef abcD30";

    public static void main(String[] args) {
        Main main = new Main();
        main.basicRegEx();
        main.basicRegEx2();
    }
    // Practice regex with string literals, character sets, and boundary matchers.
    public void basicRegEx() {
        System.out.println("\nBEGIN: characterClass");

        System.out.println("Original string: " + string + "\n");

        // RegExs allow matching patterns with strings.
        // The simplest form of a RegEx is a string literal.
        // The matching substring is replaced with Y.
        // The replacement string can be any string.
        // Use an empty string to remove matching characters.
        String regex = string.replaceAll("abcD", "_");
        System.out.println("'abcD' string literal regex result: " + regex);

        // A character class/set represents a set of characters.
        // The dot character class represents all characters.
        // Hence every character is replaced with Y.
        regex = string.replaceAll(".", "_");
        System.out.println("'.' character class regex result: " + regex);

        // To define a custom character class use square brackets.
        // It will match to any letter defined within the brackets.
        // This will replace any F or f with a Y.
        regex = string.replaceAll("[Ff]", "_");
        System.out.println("'[Ff]' character class regex result: " + regex);

        // To specify a range within a character class, use hyphen.
        // a-z matches any lowercase letter, A-Z matches any uppercase letter
        // 0-9 matches any digit. a-c matches a or b or c, etc.
        regex = string.replaceAll("[a-z]", "_");
        System.out.println("'[a-z]' character class regex result: " + regex);

        // To remove characters from a character class, use [^].
        // A caret within a character class has a special meaning.
        // It removes characters to match.
        regex = string.replaceAll("[^0-9]", "_");
        System.out.println("'[^0-9]' character class regex result: " + regex);

        // A boundary matcher pattern matches at specific points in a string.
        // The caret boundary matcher pattern matches at the beginning of a string.
        regex = string.replaceAll("^abcD", "_");
        System.out.println("'^abcD' boundary matcher regex result: " + regex);

        // Clearly the regex above affects the original string as a match was
        // found. However, calling String.matches() will return false.
        // This is because matches() must much the entire string, whereas
        // replaceAll() is satisfied with a substring.
        System.out.println("Does '^abcD' match the string? " +
                string.matches("^abcD"));

        // The following regex doesn't match so the string is unchanged.
        regex = string.replaceAll("^hello", "_");
        System.out.println("'^hello' boundary matcher regex result: " + regex);

        // The dollar boundary matcher pattern matches at the end of a string.
        regex = string.replaceAll("D30$", "_");
        System.out.println("'D30$' boundary matcher regex result: " + regex);
    }
    // Practice
    public void basicRegEx2() {
        System.out.println("\nBEGIN: basicRegEx2");

        System.out.println("Original string: " + string + "\n");

        // Quantifiers allow repeating characters in a regex.
        // * means zero or more.
        String regex = string.replaceAll("De*F", "_");
        System.out.println("'De*F' quantifier regex result: " + regex);
        // + means one or more.
        regex = string.replaceAll("e+", "_");
        System.out.println("'cDe+' quantifier regex result: " + regex);
        // To specify an exact number use {n}.
        regex = string.replaceAll("e{3}", "_");
        System.out.println("'e{3}' quantifier regex result: " + regex);
        // To specify a range use {m,n}.
        regex = string.replaceAll("e{2,3}", "_");
        System.out.println("'e{2,3}' quantifier regex result: " + regex);
        // To specify a minimum use {n,}.
        regex = string.replaceAll("e{2,}", "_");
        System.out.println("'e{2,}' quantifier regex result: " + regex);
    }
}

package com.cjm.ms;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private String string = "abcDeeeF3eD30Feef abcD30";

    public static void main(String[] args) {
        Main main = new Main();
        main.basicRegEx();
        main.quantifiers();
        main.patternMatcher();
        main.challenge1to6();
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
    // Practice regex with quantifiers.
    public void quantifiers() {
        System.out.println("\nBEGIN: quantifiers");

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
    // Practice using Pattern and Matcher.
    public void patternMatcher() {
        System.out.println("\nBEGIN: patternMatcher");

        String html = "<h1>My Heading</h1>" +
                "<h2>Sub-heading</h2>" +
                "<p>This is a paragraph about something.</p>" +
                "<p>This is another paragraph about something else.</p>" +
                "<h2>Summary</h2>" +
                "<p>Here is the summary.</p>";

        String h2Regex = "<h2>";
        // Compiles patterns to be used with various strings.
        Pattern pattern = Pattern.compile(h2Regex);
        // Stores results of pattern matching in Matcher.
        Matcher matcher = pattern.matcher(html);
        // find() stores starting and ending indices for the next match
        // every time it's called.
        while(matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            System.out.println("[start index] " + start + " [end index] " + end);
        }
        System.out.println();

        // The parenthesis denote a separate group.
        // There are three groups.
        // The first and third will always contain <h2> and </h2> respectively.
        // The second group will contain the text between those tags.
        // The ? is a lazy quantifier that stops the * from matching up to the
        // second </h2> from the first <h2>.
        String h2GroupsRegex = "(<h2>)(.*?)(</h2>)";
        pattern = Pattern.compile(h2GroupsRegex);
        matcher = pattern.matcher(html);
        while(matcher.find()) {
            // Uses 1-based index.
            String text = matcher.group(2);
            System.out.println("The second group contains: " + text);
        }
    }

    // Practice regex via challenges.
    public static void matchRegex(
            String[] strings, String regex, boolean useStringMatcher) {
        if (useStringMatcher) {
            for (String string: strings) {
                System.out.println("[string]: " + string + " [regex]: " + regex +
                        " [match]: " + string.matches(regex));
            }
        } else {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher;
            for(String string: strings) {
                matcher = pattern.matcher(string);
                System.out.println("[string]: " + string + " [regex]: " + regex +
                        " [match]: " + matcher.matches());
            }
        }
        System.out.println();
    }
    public static void applyRegex(String[] strings, String regex, String replace) {
        System.out.println("[regex]: " + regex);
        for(String string: strings) {
            System.out.println("[string]: " + string);
            String result = string.replaceAll(regex, replace);
            System.out.println("[result]: " + result);
        }
        System.out.println();
    }
    public void challenge1to6() {
        System.out.println("\nBEGIN: challenge1to6");

        String[] strings;
        String regex, replace;

        // Challenge 1: Write the regex that matches the following.
        strings = new String[]{"I want a bike."};
        regex = "I want a bike.";
        matchRegex(strings, regex, true);

        // Challenge 2: Write the regex that matches the following.
        strings = new String[]{
                "I want a bike.",
                "I want a ball."
        };
        regex = "^I want a.*";
        matchRegex(strings, regex, true);

        // Challenge 3: Call Matcher.matches().
        strings = new String[]{
                "I want a bike.",
                "I want a ball."
        };
        regex = "^I want a.*";
        matchRegex(strings, regex, false);

        // Challenge 4: Replace blanks with underscores.
        strings = new String[]{"Replace all blanks with underscores."};
        regex = " ";
        replace = "_";
        applyRegex(strings, regex, replace);

        // Challenge 5: Match the following string.
        strings = new String[]{"aaabcccccdddefffg"};
        regex = "[a-g]*"; // or {abcdefg]* or .*
        matchRegex(strings, regex, true);

        // Challenge 6: Match ONLY the string in challenge 5.
        regex =  "^a{3}bc{5}d{3}ef{3}g$"; // or aaabcccccdddefffg
        matchRegex(strings, regex, true);
    }
}

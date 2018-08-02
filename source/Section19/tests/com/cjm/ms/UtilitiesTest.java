package com.cjm.ms;

// Challenge 1: Create JUnit test class for Utilities. Don't add testing code.
// each test should fail as it's just a placeholder.

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class UtilitiesTest {
    // Challenge 9: Stop instantiating Utilities in every test case.
    private Utilities utilities;

    @BeforeEach
    public void beforeEach() {
        utilities = new Utilities();
    }

    public void everyNthChar(String input, int n, String expectedOutput) {
        char[] actualOuput = utilities.everyNthChar(input.toCharArray(), n);
        assertArrayEquals(expectedOutput.toCharArray(), actualOuput);
    }
    @Test
    public void everyNthChar_hello() {
        // Challenge 4: Write test for everyNthChar method.
        // hello > el
        everyNthChar("hello", 2, "el");
    }
    @Test
    public void everyNthChar_nOverLength() {
        // Challenge 5: Test for when source.length < n.
        everyNthChar("hello", 6, "hello");
    }

    public void removePairs(String input, String expectedOutput) {
        String actualOutput = utilities.removePairs(input);
        assertEquals(expectedOutput, actualOutput);
    }
    @Test
    public void removePairs_shortString() {
        // Challenge 3: Create more unique tests.
        // Which special value will cause a bug? null.
        removePairs("A", "A");
    }
    @Test
    public void removePairs_onePair() {
        // Challenge 3: Create more unique tests.
        // Which special value will cause a bug? null.
        removePairs("AA", "A");
    }
    @Test
    public void removePairs_uniqueSequenceEven() {
        // Challenge 3: Create more unique tests.
        // Which special value will cause a bug? null.
        removePairs("AB", "AB");
    }
    @Test
    public void removePairs_uniqueSequenceOdd() {
        // Challenge 3: Create more unique tests.
        // Which special value will cause a bug? null.
        removePairs("ABC", "ABC");
    }
    @Test
    public void removePairs_adjacentPairsOnly() {
        // Challenge 2: Add test code to test removePairs() method.
        removePairs("AABCDDEFF", "ABCDEF");
    }
    @Test
    public void removePairs_nonAdjacentPairs() {
        // Challenge 2: Add test code to test removePairs() method.
        removePairs("ABCCABDEEF", "ABCABDEF");
    }

    public static Stream<Arguments> removePairs_args() {
        return Stream.of(
                Arguments.of("ABCDEFF", "ABCDEF"),
                Arguments.of("AB88EFFG", "AB8EFG"),
                Arguments.of("112233445566", "123456"),
                Arguments.of("ZYZQQB", "ZYZQB")
        );
    }
    @ParameterizedTest
    @MethodSource("removePairs_args")
    public void converter_multi(String input, String expectedOutput) {
        removePairs(input, expectedOutput);
    }

    @Test
    public void converter_a10b5() {
        // Challenge 7: Test the converter method.
        // a = 10, b = 5, output = 300
        assertEquals(300, utilities.converter(10, 5));
    }
    @Test
    public void converter_a10b0() {
        // Challenge 8: Test the converter method.
        // a = 10, b = 0, output = ArithmeticException.
        assertThrows(ArithmeticException.class, () -> utilities.converter(10, 0));
    }


    public void nullIfOddLength(String input, String expectedOutput) {
        String actualOutput = utilities.nullIfOddLength(input);
        assertEquals(expectedOutput, actualOutput);
    }
    @Test
    public void nullIfOddLength_odd() {
        // Challenge 6: Write two tests for nullIfOddLength.
        // One for odd length string, one for even length string.
        nullIfOddLength("hello", null);
    }
    @Test
    public void nullIfOddLength_even() {
        // Challenge 6: Write two tests for nullIfOddLength.
        // One for odd length string, one for even length string.
        nullIfOddLength("hi", "hi");
    }
}

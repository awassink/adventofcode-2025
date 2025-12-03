package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day2Test {
    @Test
    void testDetermineranges() {
        var rangesInput = "11-22,95-115,998-1012,1188511880-1188511890,222220-222224," +
                "1698522-1698528,446443-446449,38593856-38593862,565653-565659," +
                "824824821-824824827,2121212118-2121212124";
        var result = Day2.determineranges(rangesInput);
        assertEquals(11,result.length);
        assertEquals(11,result[0][0]);
        assertEquals(22,result[0][1]);
        assertEquals(2121212118,result[10][0]);
        assertEquals(2121212124,result[10][1]);
    }
}
package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day2Test {

    public static final String RANGES_INPUT = "11-22,95-115,998-1012,1188511880-1188511890,222220-222224," +
            "1698522-1698528,446443-446449,38593856-38593862,565653-565659," +
            "824824821-824824827,2121212118-2121212124";
    public static final String FINAL_INPUT = "19391-47353,9354357-9434558,4646427538-4646497433,273-830,612658-674925,6639011-6699773," +
            "4426384-4463095,527495356-527575097,22323258-22422396,412175-431622,492524-611114,77-122," +
            "992964846-993029776,165081-338962,925961-994113,7967153617-7967231799,71518058-71542434," +
            "64164836-64292066,4495586-4655083,2-17,432139-454960,4645-14066,6073872-6232058,9999984021-10000017929," +
            "704216-909374,48425929-48543963,52767-94156,26-76,1252-3919,123-228";

    @Test
    void testDetermineranges() {
        var result = Day2.determineranges(RANGES_INPUT);
        assertEquals(11,result.length);
        assertEquals(11,result[0][0]);
        assertEquals(22,result[0][1]);
        assertEquals(2121212118,result[10][0]);
        assertEquals(2121212124,result[10][1]);
    }

    @Test
    void testInvalidIDsP1() {
        var result = Day2.invalidIDsP1(RANGES_INPUT);
        assertEquals("11",result[0]);
        assertEquals("22",result[1]);
        assertEquals("99",result[2]);
        assertEquals("1010",result[3]);
        assertEquals("1188511885",result[4]);
        assertEquals("222222",result[5]);
        assertEquals("446446",result[6]);
        assertEquals("38593859",result[7]);
    }

    @Test
    void testTotalInvalidIDsP2() {
        var result = Day2.totalInvalidIDsP2(RANGES_INPUT);
        assertEquals(1227775554,result);
    }

    @Test
    void testTotalFinalInput() {
        var result = Day2.totalInvalidIDsP2(FINAL_INPUT);
        assertEquals(24747430309L,result);
    }
}
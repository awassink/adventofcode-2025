package org.example;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class Day2 {
    public static long[][] determineranges(String input) {
        var result = new ArrayList<long[]>();
        var ranges = input.split(",");
        for (var range : ranges) {
            var values = range.split("-");
            result.add(new long[]{parseLong(values[0]), parseLong(values[1])});
        }
        var x = new long[result.size()][];
        return result.toArray(x);
    }
    public static String[] invalidIDsP1(String input) {
        var ranges = determineranges(input);
        var result = new ArrayList<String>();
        for (var range : ranges) {
            for (var i = range[0]; i <= range[1]; i++) {
                var s = String.valueOf(i);
                if (s.length() % 2 == 0) {
                    var s1 = s.substring(0, s.length() / 2);
                    var s2 = s.substring(s.length() / 2);
                    if (s1.equals(s2)) {
                        result.add(s);
//                        IO.println("Invalid ID: " + s);
                    }
                }
            }
        }
        return result.toArray(new String[0]);
    }
    public static long totalInvalidIDsP1(String input) {
        var invalidIDs = invalidIDsP1(input);
        long total = 0;
        for (var invalidID : invalidIDs) {
            total += parseLong(invalidID);
        }
        return total;
    }
    public static String[] invalidIDsP2(String input) {
        var ranges = determineranges(input);
        var result = new ArrayList<String>();
        for (var range : ranges) {
            for (var i = range[0]; i <= range[1]; i++) {
                var s = String.valueOf(i);
                for (var j = 1; j <= s.length()/2; j++){
                    if (s.length() % j == 0) {
                        String[] results = s.split("(?<=\\G.{" + j + "})");
                        var same = true;
                        for (var r : results) {
                            same = same && results[0].equals(r);
                        }
                        if (same) {
                            result.add(s);
                            IO.println("Invalid ID: " + s);
                            break;
                        }
                    }
                }
            }
        }
        return result.toArray(new String[0]);
    }
    public static long totalInvalidIDsP2(String input) {
        var invalidIDs = invalidIDsP2(input);
        long total = 0;
        for (var invalidID : invalidIDs) {
            total += parseLong(invalidID);
        }
        return total;
    }
}

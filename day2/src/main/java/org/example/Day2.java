package org.example;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class Day2 {
    public static int[][] determineranges(String input) {
        var result = new ArrayList<int[]>();
        var ranges = input.split(",");
        for (var range : ranges) {
            var values = range.split("-");
            result.add(new int[]{parseInt(values[0]), parseInt(values[1])});
        }
        var x = new int[result.size()][];
        return result.toArray(x);
    }
}

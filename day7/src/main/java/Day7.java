import java.util.*;
import java.util.stream.Collectors;

public class Day7 {
    public static long countBeamSplits(String input) {
        var lines = input.split("\n");
        long count = 0;
        var lastBeamIndexes = new ArrayList<Integer>();
        var newBeamIndexes = new ArrayList<Integer>();
        for (var line : lines) {
            newBeamIndexes = new ArrayList<>(lastBeamIndexes);
            for (var i = 0; i < line.length(); i++) {
                if (line.charAt(i) == 'S') {
                    newBeamIndexes.add(i);
                } else if (line.charAt(i) == '^') {
                    if (lastBeamIndexes.contains(i)) {
                        while (newBeamIndexes.contains(i)){
                            newBeamIndexes.remove(newBeamIndexes.indexOf(i));
                        }
                        newBeamIndexes.add(i-1);
                        newBeamIndexes.add(i+1);
                        count++;
                    }
                }
            }

            lastBeamIndexes = newBeamIndexes;
        }
        return count;
    }
    public static long countBeamPaths(String input) {
        var lines = input.split("\n");
        long count = 0;
        var lastBeamPaths = new long[lines[0].length()];
        var newBeamPaths = new long[lines[0].length()];
        var lastBeamIndexes = new ArrayList<Integer>();
        var newBeamIndexes = new ArrayList<Integer>();
        for (var line : lines) {
            newBeamPaths = Arrays.copyOf(lastBeamPaths, lastBeamPaths.length);
            newBeamIndexes = new ArrayList<>(lastBeamIndexes);
            for (var i = 0; i < line.length(); i++) {
                if (line.charAt(i) == 'S') {
                    newBeamIndexes.add(i);
                    newBeamPaths[i] = 1;
                } else if (line.charAt(i) == '^') {
                    if (lastBeamIndexes.contains(i)) {
                        while (newBeamIndexes.contains(i)){
                            newBeamIndexes.remove(newBeamIndexes.indexOf(i));
                        }
                        newBeamIndexes.add(i-1);
                        newBeamIndexes.add(i+1);
                        newBeamPaths[i-1] += newBeamPaths[i];
                        newBeamPaths[i+1] += newBeamPaths[i];
                        newBeamPaths[i] = 0;
                    }
                }
            }
//            IO.println(Arrays.toString(line.toCharArray()));
//            IO.println(Arrays.toString(newBeamPaths));
            lastBeamPaths = newBeamPaths;
            lastBeamIndexes = newBeamIndexes;
        }
        for (var path : newBeamPaths) {
            count += path;
        }
        return count;
    }
}

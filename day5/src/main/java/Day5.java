import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Day5 {
    public record IdRangesAndIds(long[] ids, long[][] ranges) {}
    public static IdRangesAndIds getIdRangesAndIds(String input) {
        String[] inputs = input.split("\n\n");
        String[] rangeInputs = inputs[0].split("\n");
        long[][] ranges = new long[rangeInputs.length][2];
        for (int i = 0; i < rangeInputs.length; i++) {
            String[] rangeInput = rangeInputs[i].split("-");
            long[]range = new long[]{Long.parseLong(rangeInput[0]),Long.parseLong(rangeInput[1])};
            ranges[i] = range;
        }

        String[] idInputs = inputs[1].split("\n");
        long[] ids = new long[idInputs.length];
        for (int i = 0; i < idInputs.length; i++) {
            ids[i] = Long.parseLong(idInputs[i]);
        }

        return new IdRangesAndIds(ids, ranges);
    }
    public static long[] getFreshIds(IdRangesAndIds idRangesAndIds) {
        var freshIds= new ArrayList<Long>();
        for (long id : idRangesAndIds.ids) {
            for (var range : idRangesAndIds.ranges) {
                if (id >= range[0] && id <= range[1]) {
                    freshIds.add(id);
                    break;
                }
            }
        }
        return freshIds.stream().mapToLong(l -> l).toArray();
    }
}

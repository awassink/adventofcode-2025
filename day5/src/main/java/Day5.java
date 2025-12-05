import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;

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
    public static long countAllFreshIds(IdRangesAndIds idRangesAndIds) {
        List<List<Long>> list = Arrays.stream(idRangesAndIds.ranges)
                .map(
                //Stream all the elements of each 1d array and put them into a list of Integer.
                internalArray -> Arrays.stream(internalArray).boxed().collect(Collectors.toList()
                )).collect(Collectors.toList());
        Collections.sort(list, new Comparator<List<Long>>(){
            @Override
            public int compare(List<Long> o1, List<Long> o2){
                return o1.get(0).compareTo(o2.get(0));
            }
        });
        for (var i = 1; i < list.size(); i++) {
            if (list.get(i).get(0) <= list.get(i-1).get(1)) {
                list.get(i).set(0, list.get(i-1).get(1)+1);
            }
            if (list.get(i).get(0) > list.get(i).get(1)) {
                list.remove(i);
                i--;
            }
        }
        long totalFreshIDs = 0;
        for (List<Long> l : list) {
            System.out.printf("%d-%d\n", l.get(0), l.get(1));
            totalFreshIDs += l.get(1)-l.get(0)+1;
        }
        return totalFreshIDs;
    }
}


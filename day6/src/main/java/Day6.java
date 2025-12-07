import java.math.BigInteger;

public class Day6 {
    public record MathMatrix(long[][] values, String[] operators) {}

    public static MathMatrix getMathMatrix(String input) {
        var lines = input.split("\n");
        var columns = lines[0].split("\\s+").length;
//        System.out.printf("lines %d columns %d\n", lines.length, columns);
        var values = new long[columns][lines.length-1];
        for (int i = 0; i < lines.length-1; i++) {
            var valuesInput = lines[i].trim().split("\\s+");
            for (int j = 0; j < valuesInput.length; j++) {
                values[j][i] = Long.parseLong(valuesInput[j]);
//                System.out.println(valuesInput[j] + " -> " + values[j][i]);
            }
        }
        var operatorsInput = lines[lines.length-1].split("\\s+");
        return new MathMatrix(values, operatorsInput);
    }
    public static BigInteger grandtotal(MathMatrix matrix) {
        BigInteger grandtotal = BigInteger.ZERO;
        for (var i = 0; i < matrix.values.length; i++) {
            long mathValue =0;
            if("*".equals(matrix.operators[i])){
                mathValue =1;
            }
            for (var j = 0; j < matrix.values[i].length; j++) {
                if ("+".equals(matrix.operators[i])){
                    mathValue += matrix.values[i][j];
                } else if("*".equals(matrix.operators[i])){
                    mathValue *= matrix.values[i][j];
                }
            }
//            System.out.printf("column %d value: %d\n", i,  mathValue);
            grandtotal = grandtotal.add(BigInteger.valueOf(mathValue));
        }
        return grandtotal;
    }

    public static MathMatrix getMathMatrixP2(String input) {
        var lines = input.split("\n");
        var columns = lines[0].split("\\s+").length;
        var values = new long[columns][lines.length-1];
        var operatorsInput = lines[lines.length-1].split("\\s+");
        int[] columnIndexes = new int[columns];
        var columnIndex = 0;
        for (var i = 0; i < lines[lines.length-1].length(); i++) {
            if (lines[lines.length-1].charAt(i) != ' ') {
                columnIndexes[columnIndex] = i;
                columnIndex++;
            }
        }
        for (var i = 0; i < columns; i++) {
            var startIndex = columnIndexes[i];
            var endIndex = 0;
            if (i+1 < columns) {
                endIndex = columnIndexes[i+1]-2;
            } else {
                endIndex = lines[2].length()-1;
            }
            long[] columnValues = new long[endIndex-startIndex+1];
//            System.out.println("startIndex: " + startIndex + " endIndex: " + endIndex);
            for (var j = endIndex; j >= startIndex; j--) {
                char[] value = new char[lines.length-1];
                for (int k = 0; k < lines.length-1; k++) {
                    value[k] = lines[k].charAt(j);
                }
//                System.out.println("row: " + i + " column: " + (j-startIndex) + " -> " + new String(value));
                columnValues[j-startIndex] = Long.parseLong(new String(value).trim());
            }
            values[i]=columnValues;
        }

        return new MathMatrix(values, operatorsInput);
    }
}

import java.math.BigInteger;

public class Day3 {
    public static long[][] getBatteryMatrix(String input) {
        var banks = input.split("\n");
        long[][] batteryMatrix = new long[banks.length][banks[0].length()];
        for (var i = 0; i < banks.length; i++) {
//            System.out.printf("Bank %d: %s\n", i, banks[i]);
            for (var j = 0; j < banks[i].length(); j++ ) {
                batteryMatrix[i][j] = Long.parseLong(banks[i].substring(j, j + 1));
//                System.out.printf("Battery %d: %d\n", j, batteryMatrix[i][j]);
            }
        }
        return batteryMatrix;
    }
    public static long maxBankJoltageP1(long[] batteryBank) {
        var firstMaxValueIndex = 0;
        for(int i = 1; i < batteryBank.length-1; i++){
            if(batteryBank[i] > batteryBank[firstMaxValueIndex]){
                firstMaxValueIndex = i;
            }
        }
        var secondMaxValueIndex = firstMaxValueIndex+1;
        for(int i = firstMaxValueIndex+1; i < batteryBank.length; i++){
            if(batteryBank[i] > batteryBank[secondMaxValueIndex]){
                secondMaxValueIndex = i;
            }
        }
        return batteryBank[firstMaxValueIndex]*10 + batteryBank[secondMaxValueIndex];
    }
    public static long[] maxMatrixJoltageP1(long[][] batteryMatrix) {
        var matrixJoltage = new long[batteryMatrix.length];
        for (var i  = 0; i < matrixJoltage.length; i++) {
            matrixJoltage[i] = maxBankJoltageP1(batteryMatrix[i]);
        }
        return matrixJoltage;
    }
    public static long totalMatrixJoltageP1(long[][] batteryMatrix) {
        var matrixJoltage = maxMatrixJoltageP1(batteryMatrix);
        var totalJoltage = 0;
        for (var bankJoltage : matrixJoltage) {
            totalJoltage += bankJoltage;
        }
        return totalJoltage;
    }
    public static long maxBankJoltageP2(long[] batteryBank) {
        var maxValueIndexList = new int[]{0,0,0,0,0,0,0,0,0,0,0,0};
        for (var number = 1; number <= 12; number++) {
            var startIndex = (number == 1) ? 0 : maxValueIndexList[number-2]+1;
            var stopIndex = batteryBank.length - (12 - number);
//            System.out.printf("Battery %d start: %d stop: %d\n", number, startIndex, stopIndex);
            maxValueIndexList[number-1] =  startIndex;
            for(int i = startIndex; i < stopIndex; i++){
                if(batteryBank[i] > batteryBank[maxValueIndexList[number-1]]){
                    maxValueIndexList[number-1] = i;
                }
            }
//            System.out.printf("Battery %d index: %d value: %d\n", number, maxValueIndexList[number-1], batteryBank[maxValueIndexList[number-1]]);
        }
        long maxJoltage = 0;
        for (var i = 0; i < maxValueIndexList.length; i++) {
            long multiplicator = BigInteger.TEN.pow(11-i).longValue();
            var batteryJoltage = batteryBank[maxValueIndexList[i]];
            maxJoltage += batteryJoltage * multiplicator;
        }
        return maxJoltage;
    }
    public static long[] maxMatrixJoltageP2(long[][] batteryMatrix) {
        var matrixJoltage = new long[batteryMatrix.length];
        for (var i  = 0; i < matrixJoltage.length; i++) {
            matrixJoltage[i] = maxBankJoltageP2(batteryMatrix[i]);
        }
        return matrixJoltage;
    }
    public static BigInteger totalMatrixJoltageP2(long[][] batteryMatrix) {
        var matrixJoltage = maxMatrixJoltageP2(batteryMatrix);
        BigInteger totalJoltage = BigInteger.ZERO;
        for (var bankJoltage : matrixJoltage) {
            totalJoltage = totalJoltage.add(BigInteger.valueOf(bankJoltage));
        }
        return totalJoltage;
    }
}

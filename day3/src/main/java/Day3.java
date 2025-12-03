public class Day3 {
    public static int[][] getBatteryMatrix(String input) {
        var banks = input.split("\n");
        int[][] batteryMatrix = new int[banks.length][banks[0].length()];
        for (var i = 0; i < banks.length; i++) {
//            System.out.printf("Bank %d: %s\n", i, banks[i]);
            for (var j = 0; j < banks[i].length(); j++ ) {
                batteryMatrix[i][j] = Integer.parseInt(banks[i].substring(j, j + 1));
//                System.out.printf("Battery %d: %d\n", j, batteryMatrix[i][j]);
            }
        }
        return batteryMatrix;
    }
    public static int maxBankJoltage(int[] batteryBank) {
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
    public static int[] maxMatrixJoltage(int[][] batteryMatrix) {
        var matrixJoltage = new int[batteryMatrix.length];
        for (var i  = 0; i < matrixJoltage.length; i++) {
            matrixJoltage[i] = maxBankJoltage(batteryMatrix[i]);
        }
        return matrixJoltage;
    }
    public static int totalMatrixJoltage(int[][] batteryMatrix) {
        var matrixJoltage = maxMatrixJoltage(batteryMatrix);
        var totalJoltage = 0;
        for (var bankJoltage : matrixJoltage) {
            totalJoltage += bankJoltage;
        }
        return totalJoltage;
    }
}

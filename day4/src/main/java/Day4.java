public class Day4 {
    public static char[][] getRolMatrix(String input) {
        var rows = input.split("\n");
        char[][] rolMatrix = new char[rows.length][rows[0].length()];
        for (var i = 0; i < rows.length; i++) {
            for (var j = 0; j < rows[i].length(); j++) {
                rolMatrix[i][j] = rows[i].charAt(j);
            }
        }
        return rolMatrix;
    }
    public static int countAccessibleRolls(char[][] rollMatrix) {
        int accessibleRolls = 0;
        for (var i = 0; i < rollMatrix.length; i++) {
            for (var j = 0; j < rollMatrix[i].length; j++) {
                if  (rollMatrix[i][j] == '@') {
                    int count = 0;
                    if  (i>0 && j>0 && rollMatrix[i-1][j-1] == '@') count++;
                    if  (i>0 && rollMatrix[i-1][j] == '@') count++;
                    if  (i>0 && j+1<rollMatrix[i].length && rollMatrix[i-1][j+1] == '@') count++;
                    if  (j>0 && rollMatrix[i][j-1] == '@') count++;
                    if  (j+1<rollMatrix[i].length && rollMatrix[i][j+1] == '@') count++;
                    if  (i+1<rollMatrix.length && j>0 && rollMatrix[i+1][j-1] == '@') count++;
                    if  (i+1<rollMatrix.length && rollMatrix[i+1][j] == '@') count++;
                    if  (i+1<rollMatrix.length && j+1<rollMatrix[i].length && rollMatrix[i+1][j+1] == '@') count++;
                    if (count < 4) {
                        accessibleRolls++;
//                        System.out.printf("Roll %d,%d accessible\n",i,j);
                    }
                }
            }
        }
        return accessibleRolls;
    }
}

package AI;

public class ArrayUtil {

    public static int getMax(int[][] matrix) {
        int max = 0;
        for (int[] aMatrix : matrix)
            for (int j = 0; j < matrix[0].length; j++)
                if (aMatrix[j] > max) max = aMatrix[j];
        return max;
    }

    public static void copyMatrix(int[][] srcMatrix, int[][] destMatrix, int row, int col) {
        for (int i = 0; i < row; i++) {
            System.arraycopy(srcMatrix[i], 0, destMatrix[i], 0, col);
        }
    }


    public static boolean isMatrixEquals(int[][] matrix_1, int[][] matrix_2){
        for (int i = 0; i < matrix_1.length; i++) {
            for (int j = 0; j < matrix_1[0].length; j++) {
                if (matrix_1[i][j] != matrix_2[i][j]) return false;
            }
        }
        return true;
    }

    public static void antiClockwiseRotate90(int[][] matrix, int scale) {
        int newMatrix[][] = new int[scale][scale];
        for (int p = scale - 1, i = 0; i < scale; p--, i++) {
            for (int q = 0, j = 0; j < scale; q++, j++) {
                newMatrix[p][q] = matrix[j][i];
            }
        }
        for (int i = 0; i < scale; i++) {
            System.arraycopy(newMatrix[i], 0, matrix[i], 0, scale);
        }
    }


    public static void clockwiseRotate90(int[][] matrix, int scale) {
        int newMatrix[][] = new int[scale][scale];
        for (int p = 0, i = 0; i < scale; p++, i++) {
            for (int q = scale - 1, j = 0; j < scale; q--, j++) {
                newMatrix[p][q] = matrix[j][i];
            }
        }
        for (int i = 0; i < scale; i++) {
            System.arraycopy(newMatrix[i], 0, matrix[i], 0, scale);
        }
    }
}

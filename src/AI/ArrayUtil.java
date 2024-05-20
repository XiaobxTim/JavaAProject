package AI;

public class ArrayUtil {

    public static void printMatrix(int[][] matrix) {
        System.out.print("{");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i == matrix.length - 1 && j == matrix[0].length - 1) {
                    System.out.print(" " + matrix[i][j] + "}");
                } else if (i == 0 && j == 0) {
                    System.out.print(matrix[i][j]);
                } else if (j == matrix[0].length - 1) {
                    System.out.println(" " + matrix[i][j]);
                } else {
                    System.out.print(" " + matrix[i][j]);
                }
            }
        }
        System.out.println();
    }


    public static void intiMatrixToZero(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = 0;
            }
        }
    }

    public static int getMax(int[][] matrix) {
        int max = 0;
        for (int[] aMatrix : matrix)
            for (int j = 0; j < matrix[0].length; j++)
                if (aMatrix[j] > max) max = aMatrix[j];
        return max;
    }

    public static double getMax(double[][] matrix) {
        double max = 0;
        for (double[] aMatrix : matrix)
            for (int j = 0; j < matrix[0].length; j++)
                if (aMatrix[j] > max) max = aMatrix[j];
        return max;
    }


    public static void copyMatrix(int[][] srcMatrix, int[][] destMatrix, int row, int col) {
        for (int i = 0; i < row; i++) {
            System.arraycopy(srcMatrix[i], 0, destMatrix[i], 0, col);
        }
    }


    public static boolean isEquals(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length)
            return false;
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i])
                return false;
        }
        return true;
    }


    public static boolean isMatrixEquals(int[][] matrix_1, int[][] matrix_2){
        for (int i = 0; i < matrix_1.length; i++) {
            for (int j = 0; j < matrix_1[0].length; j++) {
                if (matrix_1[i][j] != matrix_2[i][j]) return false;
            }
        }
        return true;
    }

    public static void reverseArray(int[] array) {
        int newArray[] = new int[array.length];
        int k = 0;
        for (int i = array.length - 1; i >= 0; i--) {
            newArray[k++] = array[i];
        }
        System.arraycopy(newArray, 0, array, 0, newArray.length);
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


    public static int[][] antiClockwiseRotate90(int[][] matrix, int row, int col) {
        int newMatrix[][] = new int[col][row];
        for (int p = col - 1, i = 0; i < col; p--, i++) {
            for (int q = 0, j = 0; j < row; q++, j++) {
                newMatrix[p][q] = matrix[j][i];
            }
        }
        return newMatrix;
    }


    public static int[][] clockwiseRotate90(int[][] matrix, int row, int col) {
        int newMatrix[][] = new int[col][row];
        for (int p = 0, i = 0; i < col; p++, i++) {
            for (int q = row - 1, j = 0; j < row; q--, j++) {
                newMatrix[p][q] = matrix[j][i];
            }
        }
        return newMatrix;
    }
}

package model;

import java.util.*;

public class GridNumber {
    private final int X_COUNT;

    private final int Y_COUNT;
    private boolean lock;
    public boolean getLock() {
        return lock;
    }
    public void setLock(boolean lock){
        this.lock = lock;
    }

    public int getX_COUNT() {
        return X_COUNT;
    }

    public int getY_COUNT() {
        return Y_COUNT;
    }

    private int[][] numbers;
    private int score;
    private int Coin;
    private int aim = 2048;
    private int[][] num;
    private int s;

    static Random random = new Random();

    public GridNumber(int xCount, int yCount) {
        this.X_COUNT = xCount;
        this.Y_COUNT = yCount;
        this.numbers = new int[this.X_COUNT][this.Y_COUNT];
        this.num = new int[this.X_COUNT][this.Y_COUNT];
        this.initialNumbers();
    }

    public boolean gameEnd() {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers[i].length - 1; j++) {
                if (canMergeHorizontally(i, j)) {
                    return false;
                }
            }
        }
        for (int j = 0; j < numbers[0].length; j++) {
            for (int i = 0; i < numbers.length - 1; i++) {
                if (canMergeVertically(i, j)) {
                    return false;
                }
            }
        }
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers[i].length; j++) {
                if (canSlide(i, j)) {
                    return false; // 如果可以滑动，则游戏没有结束
                }
            }
        }
        return true;
    }

    public void initialNumbers() {
        List<int[]> emptySpaces = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers[0].length; j++) {
                if (numbers[i][j] == 0) {
                    emptySpaces.add(new int[]{i, j});
                }
            }
        }
        if (emptySpaces.isEmpty()) {
            System.out.println("No empty spaces in the grid.");
        } else {
            int index1 = random.nextInt(emptySpaces.size());
            int index2 = random.nextInt(emptySpaces.size());
            while (index2 == index1) {
                index2 = random.nextInt(emptySpaces.size());
            }
            int[] randomEmptySpace1 = emptySpaces.get(index1);
            int[] randomEmptySpace2 = emptySpaces.get(index2);
            int rand = random.nextInt(2);
            if (rand == 0) {
                numbers[randomEmptySpace1[0]][randomEmptySpace1[1]] = 2;
                numbers[randomEmptySpace2[0]][randomEmptySpace2[1]] = 4;
            } else {
                numbers[randomEmptySpace1[0]][randomEmptySpace1[1]] = 4;
                numbers[randomEmptySpace2[0]][randomEmptySpace2[1]] = 2;
            }
        }
    }
    private boolean [][] merged;
    //todo: finish the method of four direction moving.
    public boolean moveRightStep(boolean isFirstStep) {
        if (isFirstStep) {
            merged = new boolean[numbers.length][numbers[0].length];
            for (int i = 0; i < numbers.length; i++) {
                for (int j = 0; j < numbers[0].length; j++) {
                    num[i][j] = numbers[i][j];
                }
            }
            s=score;
        }
        boolean res = false;
        for (int row = 0; row < numbers.length; row ++) {
            for (int i = numbers[row].length - 1; i >= 0; i--) {
                if (numbers[row][i] == 0) {
                    if (i != 0 && numbers[row][i - 1] != 0) {
                        numbers[row][i] = numbers[row][i - 1];
                        merged[row][i] = merged[row][i - 1];
                        numbers[row][i - 1] = 0;
                        res = true;
                    }
                } else {
                    if (i != 0 && numbers[row][i - 1] == numbers[row][i] && !merged[row][i] && !merged[row][i - 1]) {
                        score += numbers[row][i - 1];
                        numbers[row][i - 1] = 0;
                        numbers[row][i] *= 2;
                        merged[row][i] = true;
                        res = true;
                    }
                }
            }
        }
        return res;
    }
    public boolean moveLeftStep(boolean isFirstStep) {
        if (isFirstStep) {
            merged = new boolean[numbers.length][numbers[0].length];
            for (int i = 0; i < numbers.length; i++) {
                for (int j = 0; j < numbers[0].length; j++) {
                    num[i][j] = numbers[i][j];
                }
            }
            s=score;
        }
        boolean res = false;
        for (int row = 0; row < numbers.length; row ++) {
            for (int i = 0; i < numbers[row].length; i++) {
                if (numbers[row][i] == 0) {
                    if (i != numbers[row].length - 1 && numbers[row][i + 1] != 0) {
                        numbers[row][i] = numbers[row][i + 1];
                        merged[row][i] = merged[row][i + 1];
                        numbers[row][i + 1] = 0;
                        res = true;
                    }
                } else {
                    if (i != numbers[row].length - 1  && numbers[row][i + 1] == numbers[row][i] && !merged[row][i] && !merged[row][i + 1]) {
                        score += numbers[row][i + 1];
                        numbers[row][i + 1] = 0;
                        numbers[row][i] *= 2;
                        merged[row][i] = true;
                        res = true;
                    }
                }
            }
        }
        return res;
    }
    public boolean moveDownStep(boolean isFirstStep) {
        if (isFirstStep) {
            merged = new boolean[numbers.length][numbers[0].length];
            for (int i = 0; i < numbers.length; i++) {
                for (int j = 0; j < numbers[0].length; j++) {
                    num[i][j] = numbers[i][j];
                }
            }
            s=score;
        }
        boolean res = false;
        for (int row = 0; row < numbers.length; row ++) {
            for (int i = numbers.length - 1; i >= 0; i--) {
                if (numbers[i][row] == 0) {
                    if (i != 0 && numbers[i-1][row] != 0) {
                        numbers[i][row] = numbers[i - 1][row];
                        merged[i][row] = merged[i - 1][row];
                        numbers[i - 1][row] = 0;
                        res = true;
                    }
                } else {
                    if (i != 0 && numbers[i - 1][row] == numbers[i][row] && !merged[i][row] && !merged[i - 1][row]) {
                        score += numbers[i - 1][row];
                        numbers[i - 1][row] = 0;
                        numbers[i][row] *= 2;
                        merged[i][row] = true;
                        res = true;
                    }
                }
            }
        }
        return res;
    }
    public boolean moveUpStep(boolean isFirstStep) {
        if (isFirstStep) {
            merged = new boolean[numbers.length][numbers[0].length];
            for (int i = 0; i < numbers.length; i++) {
                for (int j = 0; j < numbers[0].length; j++) {
                    num[i][j] = numbers[i][j];
                }
            }
            s=score;
        }
        boolean res = false;
        for (int row = 0; row < numbers.length; row ++) {
            for (int i = 0; i < numbers[row].length; i++) {
                if (numbers[i][row] == 0) {
                    if (i != numbers[row].length - 1 && numbers[i + 1][row] != 0) {
                        numbers[i][row] = numbers[i + 1][row];
                        merged[i][row] = merged[i + 1][row];
                        numbers[i + 1][row] = 0;
                        res = true;
                    }
                } else {
                    if (i != numbers[row].length - 1  && numbers[i + 1][row] == numbers[i][row] && !merged[i][row] && !merged[i + 1][row]) {
                        score += numbers[i + 1][row];
                        numbers[i + 1][row] = 0;
                        numbers[i][row] *= 2;
                        merged[i][row] = true;
                        res = true;
                    }
                }
            }
        }
        return res;
    }


    public void addNewPiece(String direction) {
        List<int[]> emptySpaces = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers[0].length; j++) {
                if (numbers[i][j] == 0) {
                    emptySpaces.add(new int[]{i, j});
                }
            }
        }
        if (emptySpaces.isEmpty()) {
            System.out.println("doMove" + direction + " is unachievable.");
        } else {
            int index = random.nextInt(emptySpaces.size());
            int[] randomEmptySpace = emptySpaces.get(index);
            int rand = random.nextInt(2);
            if (rand == 0) {
                numbers[randomEmptySpace[0]][randomEmptySpace[1]] = 2;
            } else {
                numbers[randomEmptySpace[0]][randomEmptySpace[1]] = 4;
            }
//            numbers[randomEmptySpace[0]][randomEmptySpace[1]] = (rand == 0 ? 2 : 4);
        }
    }
    public void moveRight() {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers[0].length; j++) {
                num[i][j] = numbers[i][j];
            }
        }
        s=score;
        for (int i = 0; i < numbers.length; i++) {
            for (int j = numbers[0].length - 2; j >= 0; j--) {
                int k = 1;
                while ((j + k) <= (numbers[0].length - 1) && numbers[i][j + k] == 0) {
                    k++;
                }
                if (k > 1) {
                    numbers[i][j + k - 1] = numbers[i][j];
                    numbers[i][j] = 0;
                }
            }
        }
        for (int i = 0; i < numbers.length; i++) {
            for (int j = numbers[0].length - 1; j >= 1; j--) {
                if (numbers[i][j] == numbers[i][j - 1]) {
                    numbers[i][j] += numbers[i][j - 1];
                    numbers[i][j - 1] = 0;
                    score += numbers[i][j];
                }
            }
            for (int j = numbers[0].length - 2; j >= 0; j--) {
                int k = 1;
                while ((j + k) <= (numbers[0].length - 1) && numbers[i][j + k] == 0) {
                    k++;
                }
                if (k > 1) {
                    numbers[i][j + k - 1] = numbers[i][j];
                    numbers[i][j] = 0;
                }
            }
            /*for (int j = numbers[0].length - 2; j >= 0; j--) {
                if (numbers[i][j+1]!=0){
                    if (numbers[i][j]==numbers[i][j+1]){
                        numbers[i][j+1]+=numbers[i][j];
                        numbers[i][j]=0;
                        score+=numbers[i][j+1];
                    }
                }else {
                    numbers[i][j+1]=numbers[i][j];
                    numbers[i][j]=0;
                }
            }*/
        }
        addNewPiece("Right");
        gameEnd();
    }

    public void moveLeft() {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers[0].length; j++) {
                num[i][j] = numbers[i][j];
            }
        }
        s=score;
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 1; j < numbers[0].length; j++) {
                int k = 1;
                while ((j - k) >= 0 && numbers[i][j - k] == 0) {
                    k++;
                }
                if (k > 1) {
                    numbers[i][j - k + 1] = numbers[i][j];
                    numbers[i][j] = 0;
                }
            }
        }
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers[0].length - 1; j++) {
                if (numbers[i][j] == numbers[i][j + 1]) {
                    numbers[i][j] += numbers[i][j + 1];
                    numbers[i][j + 1] = 0;
                    score += numbers[i][j];
                }
            }
            for (int j = 1; j < numbers[0].length; j++) {
                int k = 1;
                while ((j - k) >= 0 && numbers[i][j - k] == 0) {
                    k++;
                }
                if (k > 1) {
                    numbers[i][j - k + 1] = numbers[i][j];
                    numbers[i][j] = 0;
                }
            }
            /*for (int j = 1; j<numbers[0].length; j++) {
                if (numbers[i][j-1]!=0){
                    if (numbers[i][j]==numbers[i][j-1]){
                        numbers[i][j-1]+=numbers[i][j];
                        numbers[i][j]=0;
                        score+=numbers[i][j-1];
                    }
                }else {
                    numbers[i][j-1]=numbers[i][j];
                    numbers[i][j]=0;
                }
            }*/
        }
        addNewPiece("Left");
    }

    public void moveUp() {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers[0].length; j++) {
                num[i][j] = numbers[i][j];
            }
        }
        s=score;
        for (int j = 0; j < numbers[0].length; j++) {
            for (int i = 1; i < numbers.length; i++) {
                int k = 1;
                while ((i - k) >= 0 && numbers[i - k][j] == 0) {
                    k++;
                }
                if (k > 1) {
                    numbers[i - k + 1][j] = numbers[i][j];
                    numbers[i][j] = 0;
                }
            }
        }
        for (int j = 0; j < numbers[0].length; j++) {
            for (int i = 0; i < numbers.length - 1; i++) {
                if (numbers[i][j] == numbers[i + 1][j]) {
                    numbers[i][j] += numbers[i + 1][j];
                    numbers[i + 1][j] = 0;
                    score += numbers[i][j];
                }
            }
            for (int i = 1; i < numbers.length; i++) {
                int k = 1;
                while ((i - k) >= 0 && numbers[i - k][j] == 0) {
                    k++;
                }
                if (k > 1) {
                    numbers[i - k + 1][j] = numbers[i][j];
                    numbers[i][j] = 0;
                }
            }
            /*for (int i =1;i<numbers.length;i++) {
                if (numbers[i-1][j]!=0){
                    if (numbers[i][j]==numbers[i-1][j]){
                        numbers[i-1][j]+=numbers[i][j];
                        numbers[i][j]=0;
                        score+=numbers[i-1][j];
                    }
                }else {
                    numbers[i-1][j]=numbers[i][j];
                    numbers[i][j]=0;
                }
            }*/
        }
        addNewPiece("Up");
        gameEnd();
    }

    public void moveDown() {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers[0].length; j++) {
                num[i][j] = numbers[i][j];
            }
        }
        s=score;
        for (int j = 0; j < numbers[0].length; j++) {
            for (int i = numbers.length - 2; i >= 0; i--) {
                int k = 1;
                while ((i + k) <= (numbers[0].length - 1) && numbers[i + k][j] == 0) {
                    k++;
                }
                if (k > 1) {
                    numbers[i + k - 1][j] = numbers[i][j];
                    numbers[i][j] = 0;
                }
            }
        }
        for (int j = 0; j < numbers[0].length; j++) {
            for (int i = numbers.length - 1; i >= 1; i--) {
                if (numbers[i][j] == numbers[i - 1][j]) {
                    numbers[i][j] += numbers[i - 1][j];
                    numbers[i - 1][j] = 0;
                    score += numbers[i][j];
                }
            }
            for (int i = numbers.length - 2; i >= 0; i--) {
                int k = 1;
                while ((i + k) <= (numbers[0].length - 1) && numbers[i + k][j] == 0) {
                    k++;
                }
                if (k > 1) {
                    numbers[i + k - 1][j] = numbers[i][j];
                    numbers[i][j] = 0;
                }
            }
            /*for (int i=numbers.length-2;i>=0;i--) {
                if (numbers[i+1][j]!=0){
                    if (numbers[i][j]==numbers[i+1][j]){
                        numbers[i+1][j]+=numbers[i][j];
                        numbers[i][j]=0;
                        score+=numbers[i+1][j];
                    }
                }else {
                    numbers[i+1][j]=numbers[i][j];
                    numbers[i][j]=0;
                }
            }*/
        }
        addNewPiece("Down");
        gameEnd();
    }

    public int getNumber(int i, int j) {
        return numbers[i][j];
    }

    public int getNum(int i, int j) {
        return num[i][j];
    }

    public void setNumber(int i, int j, int number) {
        numbers[i][j] = number;
    }

    public void printNumber() {
        for (int[] line : numbers) {
            System.out.println(Arrays.toString(line));
        }
    }

    private boolean canMergeHorizontally(int row, int col) {
        if (col < numbers[row].length - 1 && numbers[row][col] != 0 && numbers[row][col] == numbers[row][col + 1]) {
            return true;
        }
        return false;
    }

    private boolean canMergeVertically(int row, int col) {
        if (row < numbers.length - 1 && numbers[row][col] != 0 && numbers[row][col] == numbers[row + 1][col]) {
            return true;
        }
        return false;
    }

    private boolean canSlide(int row, int col) {
        if (numbers[row][col] == 0) {
            return true;
        }
        return false;
    }

    public void setScore(int score) {
        this.score = score;
    }
    public void setS(int s){this.s=s;}

    public void setCoin(int Coin) {
        this.Coin = Coin;
    }

    public void setAim(int aim) {
        this.aim = aim;
    }

    public int getAim() {
        return aim;
    }

    public int getScore() {
        return score;
    }
    public int getS(){return s;}

    public int getCoin() {
        return Coin;
    }

    public int FindMaxNumber() {
        int number = numbers[0][0];
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers[0].length; j++) {
                if (numbers[i][j] >= number) {
                    number = numbers[i][j];
                }
            }
        }
        return number;
    }
}

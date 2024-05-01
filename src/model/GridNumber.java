package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GridNumber {
    private final int X_COUNT;
    private final int Y_COUNT;

    private int[][] numbers;

    static Random random = new Random();

    public GridNumber(int xCount, int yCount) {
        this.X_COUNT = xCount;
        this.Y_COUNT = yCount;
        this.numbers = new int[this.X_COUNT][this.Y_COUNT];
        this.initialNumbers();
    }

    public void initialNumbers() {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers[i].length; j++) {
                //todo: update generate numbers method
                if (numbers[i][j]==0){
                    int randIndex=random.nextInt(3);
                    switch (randIndex){
                        case 0:
                            numbers[i][j]=0;
                            break;
                        case 1:
                            numbers[i][j]=2;
                            break;
                        default:
                            numbers[i][j]=4;
                    }
                }
            }
        }
    }
    //todo: finish the method of four direction moving.
    public void moveRight() {
        for (int i = 0; i < numbers.length; i++) {
            for (int j=numbers[0].length-2;j>=0;j--){
                int k=1;
                while ((j+k)<=(numbers[0].length-1) && numbers[i][j+k]==0){
                    k++;
                }
                if (k>1){
                    numbers[i][j+k-1]=numbers[i][j];
                    numbers[i][j]=0;
                }
            }
        }
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i][0]==numbers[i][1] && numbers[i][2]==numbers[i][3]){
                numbers[i][3]+=numbers[i][2];
                numbers[i][2]=0;
                numbers[i][1]+=numbers[i][0];
                numbers[i][0]=0;
                for (int j=numbers[0].length-2;j>=0;j--){
                    int k=1;
                    while ((j+k)<=(numbers[0].length-1) && numbers[i][j+k]==0){
                        k++;
                    }
                    if (k>1){
                        numbers[i][j+k-1]=numbers[i][j];
                        numbers[i][j]=0;
                    }
                }
            }else {
                for (int j = numbers[0].length - 2; j >= 0; j--) {
                    if (numbers[i][j+1]!=0){
                        if (numbers[i][j]==numbers[i][j+1]){
                            numbers[i][j+1]+=numbers[i][j];
                            numbers[i][j]=0;
                        }
                    }else {
                        numbers[i][j+1]=numbers[i][j];
                        numbers[i][j]=0;
                    }
                }
            }
        }
        List<int[]> emptySpaces = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            for (int j = numbers[0].length - 2; j >= 0; j--) {
                if (numbers[i][j]==0){
                    emptySpaces.add(new int[]{i,j});
                }
            }
        }
        int index=random.nextInt(emptySpaces.size());
        int[] randomEmptySpace= emptySpaces.get(index);
        int rand=random.nextInt(2);
        if (rand==0){
            numbers[randomEmptySpace[0]][randomEmptySpace[1]]=2;
        }else {
            numbers[randomEmptySpace[0]][randomEmptySpace[1]]=4;
        }
    }
    public void moveLeft() {
        for (int i = 0; i < numbers.length; i++) {
            for (int j=1;j<numbers[0].length;j++){
                int k=1;
                while ((j-k)>=0 && numbers[i][j-k]==0){
                    k++;
                }
                if (k>1){
                    numbers[i][j-k+1]=numbers[i][j];
                    numbers[i][j]=0;
                }
            }
        }
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i][0]==numbers[i][1] && numbers[i][2]==numbers[i][3]){
                numbers[i][3]+=numbers[i][2];
                numbers[i][2]=0;
                numbers[i][1]+=numbers[i][0];
                numbers[i][0]=0;
                for (int j=1;j<numbers[0].length;j++){
                    int k=1;
                    while ((j-k)>=0 && numbers[i][j-k]==0){
                        k++;
                    }
                    if (k>1){
                        numbers[i][j-k+1]=numbers[i][j];
                        numbers[i][j]=0;
                    }
                }
            }else {
                for (int j = 1; j<numbers[0].length; j++) {
                    if (numbers[i][j-1]!=0){
                        if (numbers[i][j]==numbers[i][j-1]){
                            numbers[i][j-1]+=numbers[i][j];
                            numbers[i][j]=0;
                        }
                    }else {
                        numbers[i][j-1]=numbers[i][j];
                        numbers[i][j]=0;
                    }
                }
            }
        }
        List<int[]> emptySpaces = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            for (int j = numbers[0].length - 2; j >= 0; j--) {
                if (numbers[i][j]==0){
                    emptySpaces.add(new int[]{i,j});
                }
            }
        }
        int index=random.nextInt(emptySpaces.size());
        int[] randomEmptySpace= emptySpaces.get(index);
        int rand=random.nextInt(2);
        if (rand==0){
            numbers[randomEmptySpace[0]][randomEmptySpace[1]]=2;
        }else {
            numbers[randomEmptySpace[0]][randomEmptySpace[1]]=4;
        }
    }
    public void moveUp() {
        for (int j = 0; j < numbers[0].length; j++) {
            for (int i=1;i<numbers.length;i++){
                int k=1;
                while ((i-k)>=0 && numbers[i-k][j]==0){
                    k++;
                }
                if (k>1){
                    numbers[i-k+1][j]=numbers[i][j];
                    numbers[i][j]=0;
                }
            }
        }
        for (int j = 0; j < numbers[0].length; j++) {
            if (numbers[0][j]==numbers[1][j] && numbers[2][j]==numbers[3][j]){
                numbers[3][j]+=numbers[2][j];
                numbers[2][j]=0;
                numbers[1][j]+=numbers[0][j];
                numbers[0][j]=0;
                for (int i=1;i<numbers.length;i++){
                    int k=1;
                    while ((i-k)>=0 && numbers[i-k][j]==0){
                        k++;
                    }
                    if (k>1){
                        numbers[i-k+1][j]=numbers[i][j];
                        numbers[i][j]=0;
                    }
                }
            }else {
                for (int i =1;i<numbers.length;i++) {
                    if (numbers[i-1][j]!=0){
                        if (numbers[i][j]==numbers[i-1][j]){
                            numbers[i-1][j]+=numbers[i][j];
                            numbers[i][j]=0;
                        }
                    }else {
                        numbers[i-1][j]=numbers[i][j];
                        numbers[i][j]=0;
                    }
                }
            }
        }
        List<int[]> emptySpaces = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            for (int j = numbers[0].length - 2; j >= 0; j--) {
                if (numbers[i][j]==0){
                    emptySpaces.add(new int[]{i,j});
                }
            }
        }
        int index=random.nextInt(emptySpaces.size());
        int[] randomEmptySpace= emptySpaces.get(index);
        int rand=random.nextInt(2);
        if (rand==0){
            numbers[randomEmptySpace[0]][randomEmptySpace[1]]=2;
        }else {
            numbers[randomEmptySpace[0]][randomEmptySpace[1]]=4;
        }
    }
    public void moveDown() {
        for (int j = 0; j < numbers[0].length; j++) {
            for (int i=numbers.length-2;i>=0;i--){
                int k=1;
                while ((i+k)<=(numbers[0].length-1) && numbers[i+k][j]==0){
                    k++;
                }
                if (k>1){
                    numbers[i+k-1][j]=numbers[i][j];
                    numbers[i][j]=0;
                }
            }
        }
        for (int j = 0; j < numbers[0].length; j++) {
            if (numbers[0][j]==numbers[1][j] && numbers[2][j]==numbers[3][j]){
                numbers[3][j]+=numbers[2][j];
                numbers[2][j]=0;
                numbers[1][j]+=numbers[0][j];
                numbers[0][j]=0;
                for (int i=numbers.length-2;i>=0;i--){
                    int k=1;
                    while ((i+k)<=(numbers[0].length-1) && numbers[i+k][j]==0){
                        k++;
                    }
                    if (k>1){
                        numbers[i+k-1][j]=numbers[i][j];
                        numbers[i][j]=0;
                    }
                }
            }else {
                for (int i=numbers.length-2;i>=0;i--) {
                    if (numbers[i+1][j]!=0){
                        if (numbers[i][j]==numbers[i+1][j]){
                            numbers[i+1][j]+=numbers[i][j];
                            numbers[i][j]=0;
                        }
                    }else {
                        numbers[i+1][j]=numbers[i][j];
                        numbers[i][j]=0;
                    }
                }
            }
        }
        List<int[]> emptySpaces = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            for (int j = numbers[0].length - 2; j >= 0; j--) {
                if (numbers[i][j]==0){
                    emptySpaces.add(new int[]{i,j});
                }
            }
        }
        int index=random.nextInt(emptySpaces.size());
        int[] randomEmptySpace= emptySpaces.get(index);
        int rand=random.nextInt(2);
        if (rand==0){
            numbers[randomEmptySpace[0]][randomEmptySpace[1]]=2;
        }else {
            numbers[randomEmptySpace[0]][randomEmptySpace[1]]=4;
        }
    }

    public int getNumber(int i, int j) {
        return numbers[i][j];
    }

    public void printNumber() {
        for (int[] line : numbers) {
            System.out.println(Arrays.toString(line));
        }
    }
}

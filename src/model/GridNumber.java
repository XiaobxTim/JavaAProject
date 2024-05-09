package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GridNumber {
    private final int X_COUNT;
    private final int Y_COUNT;

    private int[][] numbers;
    private int score;

    static Random random = new Random();

    public GridNumber(int xCount, int yCount) {
        this.X_COUNT = xCount;
        this.Y_COUNT = yCount;
        this.numbers = new int[this.X_COUNT][this.Y_COUNT];
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
            for (int j =0;j<numbers[0].length; j++)  {
                if (numbers[i][j]==0){
                    emptySpaces.add(new int[]{i,j});
                }
            }
        }
        if (emptySpaces.isEmpty()){
            System.out.println("No empty spaces in the grid.");
        }else {
            int index1=random.nextInt(emptySpaces.size());
            int index2=random.nextInt(emptySpaces.size());
            while (index2==index1){
                index2=random.nextInt(emptySpaces.size());
            }
            int[] randomEmptySpace1 = emptySpaces.get(index1);
            int[] randomEmptySpace2= emptySpaces.get(index2);
            int rand=random.nextInt(2);
            if (rand==0){
                numbers[randomEmptySpace1[0]][randomEmptySpace1[1]]=2;
                numbers[randomEmptySpace2[0]][randomEmptySpace2[1]]=4;
            }else {
                numbers[randomEmptySpace1[0]][randomEmptySpace1[1]]=4;
                numbers[randomEmptySpace2[0]][randomEmptySpace2[1]]=2;
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
                score+=numbers[i][3];
                numbers[i][1]+=numbers[i][0];
                numbers[i][0]=0;
                score+=numbers[i][1];
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
                            score+=numbers[i][j+1];
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
            for (int j =0;j<numbers[0].length; j++)  {
                if (numbers[i][j]==0){
                    emptySpaces.add(new int[]{i,j});
                }
            }
        }
        if (emptySpaces.isEmpty()){
            System.out.println("doMoveRight is unachievable.");
        }else {
            int index=random.nextInt(emptySpaces.size());
            int[] randomEmptySpace= emptySpaces.get(index);
            int rand=random.nextInt(2);
            if (rand==0){
                numbers[randomEmptySpace[0]][randomEmptySpace[1]]=2;
            }else {
                numbers[randomEmptySpace[0]][randomEmptySpace[1]]=4;
            }
        }
        gameEnd();
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
                score+=numbers[i][3];
                numbers[i][1]+=numbers[i][0];
                numbers[i][0]=0;
                score+=numbers[i][1];
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
                            score+=numbers[i][j-1];
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
            for (int j =0;j<numbers[0].length; j++) {
                if (numbers[i][j]==0){
                    emptySpaces.add(new int[]{i,j});
                }
            }
        }
        if (emptySpaces.isEmpty()){
            System.out.println("doMoveLeft is unachievable.");
        }else{
            int index=random.nextInt(emptySpaces.size());
            int[] randomEmptySpace= emptySpaces.get(index);
            int rand=random.nextInt(2);
            if (rand==0){
                numbers[randomEmptySpace[0]][randomEmptySpace[1]]=2;
            }else {
                numbers[randomEmptySpace[0]][randomEmptySpace[1]]=4;
            }
        }
        gameEnd();
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
                score+=numbers[3][j];
                numbers[1][j]+=numbers[0][j];
                numbers[0][j]=0;
                score+=numbers[1][j];
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
                            score+=numbers[i-1][j];
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
            for (int j =0;j<numbers[0].length; j++) {
                if (numbers[i][j]==0){
                    emptySpaces.add(new int[]{i,j});
                }
            }
        }
        if (emptySpaces.isEmpty()){
            System.out.println("doMoveUp is unachievable.");
        }else {
            int index=random.nextInt(emptySpaces.size());
            int[] randomEmptySpace= emptySpaces.get(index);
            int rand=random.nextInt(2);
            if (rand==0){
                numbers[randomEmptySpace[0]][randomEmptySpace[1]]=2;
            }else {
                numbers[randomEmptySpace[0]][randomEmptySpace[1]]=4;
            }
        }
        gameEnd();
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
                score+=numbers[3][j];
                numbers[1][j]+=numbers[0][j];
                numbers[0][j]=0;
                score+=numbers[1][j];
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
                            score+=numbers[i+1][j];
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
            for (int j =0;j<numbers[0].length; j++) {
                if (numbers[i][j]==0){
                    emptySpaces.add(new int[]{i,j});
                }
            }
        }
        if (emptySpaces.isEmpty()){
            System.out.println("doMoveDown is unachievable.");
        }else {
            int index=random.nextInt(emptySpaces.size());
            int[] randomEmptySpace= emptySpaces.get(index);
            int rand=random.nextInt(2);
            if (rand==0){
                numbers[randomEmptySpace[0]][randomEmptySpace[1]]=2;
            }else {
                numbers[randomEmptySpace[0]][randomEmptySpace[1]]=4;
            }
        }
        gameEnd();
    }

    public int getNumber(int i, int j) {
        return numbers[i][j];
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

    public int getScore(){
        return score;
    }
}

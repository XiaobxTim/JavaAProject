import java.util.Random;
public class Rando {
    public static void main(String[] args) {
        int min = 1; // 最小值
        int max = 36; // 最大值

        int randomNumber = getRandomNumber(min, max);
        System.out.println("随机抽取的数字是: " + randomNumber);
    }

    public static int getRandomNumber(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("最小值不能大于最大值");
        }
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
}
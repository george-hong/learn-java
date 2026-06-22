import java.util.Arrays;
import java.util.Scanner;

public class Random {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("你想抽几位");
        final int NUM = scanner.nextInt();
        int min = NUM + 1;
        int count = -1;
        while(count == -1 || count < min) {
            System.out.println("能抽到的最大数字是几，不能小于" + min);
            count = scanner.nextInt();
        }
        // 可用数字
        int[] avaNums = new int[count];
        for(int i = 0; i < avaNums.length; i++)
            avaNums[i] = i + 1;
        int[] result = new int[NUM];
        int curCount = 0;
        while(curCount < NUM) {
            int maxIndex = avaNums.length - curCount - 1;
            int pickedIndex = (int) (Math.random() * maxIndex);
            result[curCount] = avaNums[pickedIndex];
            avaNums[pickedIndex] = avaNums[maxIndex];
            curCount++;
        }
        // 打印数组
        System.out.println(Arrays.toString(result));
    }
}
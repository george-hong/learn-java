/**
 * page 278
 * 6.3.7 静态内部类
 *  1.有时，使用内部类只是为了把一个类影藏在另外一个类内部，并不需要内部类有外部类的引用。为此，可以将内部类声明为static。
 *  2.静态方法中只能引用静态内部类，不能引用普通内部类
 *  3.只要内部类不需要方位外部类对象，就应该使用静态内部类
 *  4.在接口中声明的内部类自动是static和public
 *  5.在类中声明的接口、记录和枚举自动是static
 */
package page278StaticInnerClass;

import java.util.Arrays;
import java.util.Random;

public class Demo {
    public static void main(String[] args) {
        double[] nums = new double[5];
        for(int i = 0; i < nums.length; i++) {
            nums[i] = new Random().nextDouble();
        }
        System.out.println("nums = " + Arrays.toString(nums));
        Calculator.Pair pair = (new Calculator()).run(nums);
        System.out.println(pair);

        new Test2().run();
    }
}

class Calculator {
    public static class Pair {
        private double min;
        private double max;

        public Pair(double min, double max) {
            this.min = min;
            this.max = max;
        }

        public double getMin() {
            return min;
        }

        public double getMax() {
            return max;
        }

        public String toString() {
            return getClass().getName() + "[min=" + min + ", max=" + max + "]";
        }
    }

    Pair run(double[] nums) {
        double min = Double.POSITIVE_INFINITY;
        double max = Double.NEGATIVE_INFINITY;
        for (double num : nums) {
            if (num < min) min = num;
            if (num > max) max = num;
        }
        return new Pair(min, max);
    }

    // 这个方法中引用的Pair只能是static，否则会报错
    public static Pair getMinMax(double[] nums) {
        double min = Double.POSITIVE_INFINITY;
        double max = Double.NEGATIVE_INFINITY;
        for (double num : nums) {
            if (num < min) min = num;
            if (num > max) max = num;
        }
        return new Pair(min, max);
    }
}

interface InterfaceWithInnerClass {
    String interfaceValue = "Interface value here";
    class InnerClass {
        static void printInterfaceValue() {
            System.out.println(interfaceValue);
        }
    }
}

class Test2 implements InterfaceWithInnerClass {
    void run() {
        InnerClass.printInterfaceValue();
    }
}
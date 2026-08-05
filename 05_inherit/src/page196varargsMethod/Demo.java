/**
 * page 196
 * 可以提供参数个数可变的方法，定义方式是 method(Object... args), 这里...是Java代码的一部分，表名这个方法可以接受任意数量的对象
 * 1.允许将数组作为最后一个参数传递给有可变参数的方法
 */
package page196varargsMethod;

public class Demo {
    public static void main(String[] args) {
        System.out.println(max(10.2, 20.2, 13.664));
        // 1.允许将数组作为最后一个参数传递给有可变参数的方法
        System.out.println(max(new double[]{ 10.2, 20.2, 13.664 }));

    }

    // 定义不定参数方法
    static double max(double... values) {
        double max = Double.NEGATIVE_INFINITY;
        for (Object value : values) {
            double cValue = Double.parseDouble(String.valueOf(value));
            if (cValue > max) max = cValue;
        }
        return max;
    }
}

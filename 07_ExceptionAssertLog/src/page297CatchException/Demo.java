/**
 * page 297
 * 7.2.1 捕获异常
 *  1.想要捕获一个异常，需要建立try/catch语句块，最简单的try语句如下所示
 *      try {
 *          code...
 *      } catch (ExceptionType e) {
 *          handler for this type
 *      }
 *  2.如果try语句块中的任何代码抛出了catch子句中指定的一个异常类，那么
 *      - 程序将跳过try语句块的其余代码
 *      - 陈旭将执行catch子句中的处理器代码
 *    如果try语句块中的代码没有抛出任何异常，那么程序将跳过catch子句。
 *    如果方法中的任何代码抛出了一个异常，但不是catch子句中指定的异常类型，那么这个方法会立即退出。
 */
package page297CatchException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Demo {
    public static void main(String[] args) {
        try {
            // 以下代码会报错
            var in = new FileInputStream("input.txt");
            // 后续代码不会执行
            System.out.println("不会执行");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

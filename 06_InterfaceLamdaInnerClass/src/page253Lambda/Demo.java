/**
 * page 253
 * 6.2.1 lambda表达式是一个可传递的代码块，可以在以后执行一次或多次
 * 6.2.2
 *  1.Java是一种强类型语言，需要指定表达式的类型 (String first, String, second) -> first.length() - second.length()
 *  2.lambda表达式就是一个代码块，以及必须传入代码的所有变量的规范
 *  3.即使lambda表达式没有参数，仍然需要提供空括号
 *  4.如果方法只有一个参数，且这个参数的类型可以退到得出，那么可以省略括号
 *  5.如果lambda在某些分支返回一个值，某些分支不返回值，这是不合法的。
 */
package page253Lambda;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;
import java.util.function.Supplier;

public class Demo {
    public static void main(String[] args) {
        // 以lambda重写之前几个使用回调的实例
        // 重写page246ComparatorInterface中的LengthComparator
        String[] strList = { "Ada", "Lucy", "An", "Nancy" };
        Arrays.sort(strList, (String str1, String str2) -> str1.length() - str2.length());
        System.out.println(Arrays.toString(strList));

        // 3.即使lambda表达式没有参数，仍然需要提供空括号
        Runnable fn = () -> System.out.println("lambda");
        // lambda表达式需要与函数式接口的签名一致，否则报错
        // 以下代码报错 java: 不兼容的类型: lambda 表达式中的参数类型不兼容
        // NotMatchInterface fn2 = () -> System.out.println("lambda");
        // 4.如果方法只有一个参数，且这个参数的类型可以退到得出，那么可以省略括号
        ActionListener listener = e -> System.out.print(e);
        // 5.如果lambda在某些分支返回一个值，某些分支不返回值，这是不合法的。
        // 以下代码报错 java: 不兼容的类型: lambda 表达式中的返回类型错误
        // Supplier<Integer> sup = () -> {
        //     if (new Random().nextDouble() > 0.5) return "success";
        // }

        Timer timer = new Timer(1000, (ActionEvent e) -> {
            System.out.println("run");
        });

        timer.start();

        JOptionPane.showMessageDialog(null, "关闭?");
        System.exit(0);
    }
}

interface NotMatchInterface {
    void run(String str);
}
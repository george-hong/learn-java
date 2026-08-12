/**
 * page 253
 * 6.2.1 lambda表达式是一个可传递的代码块，可以在以后执行一次或多次
 * 6.2.2
 *  1.Java是一种强类型语言，需要指定表达式的类型 (String first, String, second) -> first.length() - second.length()
 *  2.lambda表达式就是一个代码块，以及必须传入代码的所有变量的规范
 *  3.即使lambda表达式没有参数，仍然需要提供空括号
 *
 */
package page253Lambda;

import java.util.Arrays;

public class Demo {
    public static void main(String[] args) {
        // 以lambda重写之前几个使用回调的实例
        // 重写page246ComparatorInterface中的LengthComparator
        String[] strList = { "Ada", "Lucy", "An", "Nancy" };
        Arrays.sort(strList, (String str1, String str2) -> str1.length() - str2.length());
        System.out.println(Arrays.toString(strList));

    }
}

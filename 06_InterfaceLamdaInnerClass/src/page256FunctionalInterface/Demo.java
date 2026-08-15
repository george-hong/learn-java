/**
 * page 256
 * 6.2.3
 *  1.对于只有一个抽象方法的接口，需要这种接口的对象时，就可以提供一个lambda表达式。这种接口成为函数式接口。
 *  2.在Java中，对lambda表示能做的也只是转换为函数式接口。甚至不能吧lambda表达式赋给类型为Object的变量
 * 6.2.4 方法引用
 *  var timer = new Timer(1000, System.out::println)
 *  1.表达式System.out::println是一个方法引用，他只是编译器生成一个函数式接口实例，覆盖这个接口的抽象方法来调用给定的方法
 */
package page256FunctionalInterface;

import java.util.Arrays;

public class Demo {
    public static void main(String[] args) {
        // 为了展示如何转换为函数式接口，下面考虑Arrays.sort方法。他的第二个参数需要一个Comparator实例，Comparator就是只有一个方法的接口。
        // 所以可以一共一个lambda表达式
        // 在底层，Arrays.sort方法会接受实现了Comparator<String>的某个类的对象。在这个对象上调用compare方法会执行这个lambda表达式的体。
        // 这些对象和类的管理完全取决于具体实现，与使用传统的内联类相比，这样可能要搞笑的多。最好吧lambda表达式看做是一个具体的函数，而不是一个对象，
        // 另外要接受一个事实：lambda表达式可以传递到函数式接口
        // 实际上，在Java中，对lambda表示能做的也只是转换为函数式接口。
        String[] strList = { "France", "China", "USA", "UK" };
        Arrays.sort(strList, (first, second) -> first.length() - second.length());
    }
}

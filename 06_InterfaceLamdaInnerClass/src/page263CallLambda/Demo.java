/**
 * page 263
 * 6.2.7 处理lambda表达式
 *  1.使用lambda的重点是延迟执行
 *  2.如果自行设计函数式接口，可以使用@FunctionalInterface注解来标记这个接口。
 */
package page263CallLambda;

import java.util.function.IntConsumer;

public class Demo {
    public static void main(String[] args) {
        // 要接受这个lambda表达式，需要选择（或自行提供）一个函数式接口。
        repeat(5, () -> System.out.println("run"));
        // 可以再复杂一点，让这个lambda知道它是在哪一次迭代中被执行的
        repeatWithCount(5, (i) -> System.out.println("run in " + i));
    }

    public static void repeat(int count, Runnable runnable) {
        for (int i = 0; i < count; i++) {
            runnable.run();
        }
    }

    public static void repeatWithCount(int count, IntConsumer consumer) {
        for (int i = 0; i < count; i++) {
            consumer.accept(i);
        }
    }
}
// 2.如果自行设计函数式接口，可以使用@FunctionalInterface注解来标记这个接口。
@FunctionalInterface
interface FnInterface {
    void run();
}
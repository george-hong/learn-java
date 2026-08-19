/**
 * page 256
 * 6.2.3
 *  1.对于只有一个抽象方法的接口，需要这种接口的对象时，就可以提供一个lambda表达式。这种接口成为函数式接口。
 *  2.在Java中，对lambda表示能做的也只是转换为函数式接口。甚至不能吧lambda表达式赋给类型为Object的变量
 * 6.2.4 方法引用
 *  var timer = new Timer(1000, System.out::println)
 *  1.表达式System.out::println是一个方法引用，他只是编译器生成一个函数式接口实例，覆盖这个接口的抽象方法来调用给定的方法，在上一行代码中，
 *      会生成一个ActionListener，他的actionPerformed(ActionEvent e)发明合法app懂啊铺拍卖行System.out.println(e)
 *  2.要用::操作符分隔方法名与对象或类名，主要有三种情况
 *      Object::instanceMethod 方法引用等价于一个lambda表达式，其参数要传递到方法。等价于 x -> System.out.println(x)
 *      Class::instanceMethod 第一个参数会成为方法的隐式参数，如 String::compareToIgnoreCase等价于 (x,y) -> x.compareToIgnoreCase(y)
 *      Class::staticMethod 所有参数传递到静态方法，等同于 (x,y) -> x.compareToIgnoreCase(y)
 *  3.可以在方法引用中使用this参数。例如，this::equals等同于 x -> this.equals(x)
 *  4.使用super也是合法的
 * 6.2.5 构造器引用
 *  1. 构造器引用于方法引用很类似，只不过方法名为new。例如，Person::new是Person构造器的一个引用。使用哪个构造器取决于上下文。
 */
package page256FunctionalInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

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

        Method method = new Method();
        Method.runAdd(method::add);
        Method.runSumInst(Method::sumInInst, method);
        Method.runSum(Method::sum);

        RepeatedGreeter repeatedGreeter = new RepeatedGreeter();
        repeatedGreeter.greet();

        // 6.2.5  1. 构造器引用于方法引用很类似，只不过方法名为new。例如，Person::new是Person构造器的一个引用。使用哪个构造器取决于上下文。
        ArrayList<String> strArrayList = new ArrayList<>();
        strArrayList.add("Jack");
        strArrayList.add("Leon");
        strArrayList.add("Sami");
        Stream<Person> stream = strArrayList.stream().map(Person::new);
        List<Person> people = stream.toList();


        JOptionPane.showMessageDialog(null, "确认关闭？");
        System.exit(0);
    }
}

interface IRunAdd {
    void run(Double... values);
}

interface IRunSumInst {
    void run(Method inst, int a, int b);
}

class Method {
    static void sum(int a, int b) {
        System.out.println("static sum parameters[a=" + a + ", b=" + b + "]");
    }

    void sumInInst(int a, int b) {
        System.out.println("sumInInst parameters[a=" + a + ", b=" + b + "]");
    }

    void add (Double... values) {
        String r = "add parameters[" + System.lineSeparator();
        for (int i = 0; i < values.length; i++) {
            if (i > 0) r += "," + System.lineSeparator();
            r += values[i];
        }
        r += System.lineSeparator() + "]";
        System.out.println(r);
    }

    static void runSum(BiConsumer<Integer, Integer> fn) {
        fn.accept(1, 2);
    }

    static void runAdd(IRunAdd fn) {
        fn.run(1.0, 2.0, 3.0, 4.0, 5.0);
    }

    static void runSumInst(IRunSumInst fn, Method inst) {
        fn.run(inst, 5, 10);
    }
}

class Greeter {
    public void greet(ActionEvent event) {
        System.out.println("Hello, the time is " + Instant.ofEpochMilli(event.getWhen()));
    }
}

class RepeatedGreeter extends Greeter {
    public void greet() {
        // 4.使用super也是合法的
        Timer timer = new Timer(1000, super::greet);
        timer.start();
    }
}

class Person {
    private String name;

    Person(String name) {
        System.out.println("enter 1 parameter");
        this.name = name;
    }

    Person(String name, Integer age) {
        System.out.println("enter 2 parameter");
        this.name = name;
    }
}
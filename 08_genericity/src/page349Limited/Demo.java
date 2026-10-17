/**
 * page 349
 * 8.6.1 不能用基本类型实例化类型参数
 * 8.6.2 运行时类型查询只适用于原始类型
 * 虚拟机中的对象总是有一个特定的非泛型类型。因此，所有的类型查询只生成原始类型。
 * 8.6.3 不能创建参数化类型的数组
 * 只是不允许创建这些数组，而声明为Gen<String>[]的变量仍是合法的。不过不能用 new Gen<String>[10]初始化这个变量
 * 如果需要收集参数化类型对象，可以直接使用ArrayList: ArrayList<Pair<String>>
 * 8.6.4 Varargs警告
 * 向参数个数可变的方法传递一个泛型类型的实例
 * 可以使用@SafeVarargs注解来消除创建泛型数组的有关限制
 * 8.6.5 不能实例化类型变量
 * 8.6.6 不够构造泛型数组
 * 就像不能实例化泛型示例一样，也不能实例化数组。不过原因有所不同，毕竟数组可以填充null值，看上去好像可以安全地构造数组。不过，数组本省也带有类型，
 * 用来监控虚拟机中的数组存储。这个类型会被擦除。
 * 8.6.7 泛型类的静态上下文中类型变量无效
 * 类型擦除后，只剩下字段了，因此，带有类型变量的静态字段和方法是完全非法的
 * 8.6.8 不能抛出或捕获泛型类的实例
 * 既不能抛出也不能补货泛型类的对象。实际上，设置泛型类拓展Throwable都是不合法的。
 * 不过，在异常规范中使用类型变量时允许的
 * 8.6.9 可以取消对检查型异常的检查
 * 正常情况下，必须补货一个Runnable的run方法中的所有检查型异常，把他们“包装”到非检查型异常中，应为run方法声明为不抛出任何检查型异常。
 * 通过使用泛型类、擦除和@suppressWarnings注解，就能消除Java类型系统的一个基本限制
 * 8.6.10 注意擦除后的冲突
 * 擦除泛型类型后，不允许创建引发冲突的条件
 */
package page349Limited;

import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Demo {
    public static void main(String[] args) {
        // 8.6.1 不能用基本类型实例化类型参数
        // 没有Gen<int> g = new Gen();
        Gen<Integer> gi = new Gen<>();
        Gen<Double> gd = new Gen<>();

        // 8.6.2 运行时类型查询只适用于原始类型 虚拟机中的对象总是有一个特定的非泛型类型。因此，所有的类型查询只生成原始类型。
        // java: 不兼容的类型: page349Limited.Gen<java.lang.Integer>无法转换为page349Limited.Gen<java.lang.String>
        // if (g instanceof Gen<String>) {
        //     System.out.println("pass");
        // }
        if (gi instanceof Gen) {
            System.out.println("pass");
        }
        // 这个比较返回的结果是true,因为两个getClass调用都返回Gen.class
        if (gi.getClass() == gd.getClass()) {
            System.out.println("gi.getClass() == gd.getClass() pass");
        }

        // 不能使用泛型创建数组
        // java: 创建泛型数组
        // var table = new Gen<String>[10];
        Gen<String>[] genTable = new Gen[5];
        // 可以声明通配类型的数组，然后进行强制类型转换
        var genTable2 = (Gen<String>[]) new Gen<?>[5];
        genTable2[0] = new Gen();
        genTable2[0].getValue();
        // 如果需要收集参数化类型对象，可以直接使用ArrayList: ArrayList<Pair<String>>
        ArrayList<Gen<String>> table = new ArrayList<Gen<String>>();

    }
}

class Gen<T> {
    public int getValue() {
        return 1;
    }
}

class VarargsWarning {
    public static void main(String[] args) {
        // 8.6.4 向参数个数可变的方法传递一个泛型类型的实例
        // 为了调用addAll方法，Java虚拟机必须建立一个Pair<String>数组，这就违反了规则。不过，对于这种情况，规则有所放松，只会得到一个警告。
        // 而不是一个错误
        Collection<Pair<String>> table = new ArrayList<>();
        Pair<String> pair1 = new Pair<>();
        Pair<String> pair2 = new Pair<>();
        addAll(table, pair1, pair2);
    }

    @SafeVarargs
    public static <T> void addAll(Collection<T> coll, T... ts) {
        for (T i : ts) coll.add(i);
    }
}

class CannotInstanceTypeVariable {
    // 8.6.5 不能实例化类型变量
    // 以下代码不合法
    // public static <T> T instance(T cl) {
    //    return new T();
    // }
    public static void main(String[] args) {
        // Java8之后可以使用lambda表达式创建
        Pair<String> pair = Pair.makePair(String::new);
        // Java8之前使用反射
        Pair<String> pair2 = Pair.makePair2(String.class);
    }
}

class CannotInstanceGenericityArray {
    public static void main(String[] args) {
        System.out.print(Arrays.toString(minmax("Jay", "Ti", "An")));
        // 以下代码会报消费异常，因为minmax中return的返回值被擦除后，result真正的类型是Comparable[]，返回到调用方之后又要求是String[]
        // 因此会报消费异常
        // java.lang.ClassCastException
        String[] r = minmax("Jay", "Ti", "An");
    }

    public static <T extends Comparable> T[] minmax(T... a) {
        var result = new Comparable[2];
        T min = a[0];
        T max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i].compareTo(min) < 0) min = a[i];
            if (a[i].compareTo(max) > 0) max = a[i];

        }
        result[0] = min;
        result[1] = max;
        return (T[]) result;
    }
}

class UselessGenericityInStaticMethod<T> {
    // 8.6.7 泛型类的静态上下文中类型变量无效
    // 类型擦除后，只剩下字段了，因此，带有类型变量的静态字段和方法是完全非法的
    // java: 无法从静态上下文中引用非静态 类型变量 T
    // static T str = "test";

    // java: 无法从静态上下文中引用非静态 类型变量 T
    // public static T getStr() {
    //     return "1";
    // }

    public static void main(String[] args) {

    }
}

class CannotThrowOrCatchGenericity {
    // 8.6.8 不能抛出或捕获泛型类的实例
    // 既不能抛出也不能补货泛型类的对象。实际上，设置泛型类拓展Throwable都是不合法的。

    // 以下代码报错
    // java: 泛型类不能扩展 java.lang.Throwable
    // private class Problem <T> extends Exception {}

    public static <T extends Throwable> void doWork() {
        // java: 意外的类型
        //  需要: 类
        //  找到:    类型参数T
        // try {
        //
        // } catch(T e) {
        //
        // }
    }

    // 不过，在异常规范中使用类型变量时允许的
    public static <T extends Throwable> T doWork2(T t) throws T {
        try {
            return t;
        } catch (Exception e) {
            throw t;
        }
    }
}

class CancelTypeCheck {
    @SuppressWarnings("unchecked")
    static <T extends Throwable> void throwAs(Throwable t) throws T {
        throw (T) t;
    }

    public static void main(String[] args) {
        try {
            String fileStr = Files.readString(Paths.get("ddd.txt"));
        } catch (Throwable e) {
            // 8.6.9 可以取消对检查型异常的检查
            // 这里无法直接写丢出异常，因为没有在方法尾部声明可能抛出的异常，但是通过throwAs可以将异常放到执行过程中抛出，而不是编译阶段
            // throw(e);
            // CancelTypeCheck.<RuntimeException>throwAs(e);
        }
    }
}

interface Task {
    void run() throws Exception;

    @SuppressWarnings("unchecked")
    static <T extends Throwable> void throwAs(Throwable t) throws T {
        throw (T) t;
    }

    static Runnable asRunnable(Task task) {
        return () -> {
            try {
                task.run();
            } catch (Exception e) {
                Task.<RuntimeException>throwAs(e);
            }
        };
    }
}

class CancelTypeCheckDemo {
    public static void main(String[] args) {
        // 正常情况下，必须补货一个Runnable的run方法中的所有检查型异常，把他们“包装”到非检查型异常中，应为run方法声明为不抛出任何检查型异常。
        // 通过使用泛型类、擦除和@suppressWarnings注解，就能消除Java类型系统的一个基本限制
        // 使用asRunnable可以不用捕获InterruptedException异常
        var thread = new Thread(Task.asRunnable(() -> {
            Thread.sleep(1000);
            System.out.print("Hello, World");
            throw new Exception("Check this out!");
        }));
        thread.start();
    }
}

class Employee implements Comparable<Employee> {
    @Override
    public int compareTo(Employee o) {
        return 0;
    }
}

// 8.6.10 注意擦除后的冲突
// 擦除泛型类型后，不允许创建引发冲突的条件
// java: 无法使用以下不同的参数继承java.lang.Comparable: <page349Limited.Manager> 和 <page349Limited.Employee>
// class Manager extends Employee implements Comparable<Manager> {}
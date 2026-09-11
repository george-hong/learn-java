/**
 * page 312
 * 7.4.1 断言的概念
 *  1.假设你确信满足某个特定属性，并且代码依赖于这个属性。例如，可能需要计算
 *      double y = Math.sqrt(x);
 *      你确信这里的x是一个非负数。这个方法要求他的调用者只能提供一个正数输入。不过，你可能还是想在做一次检查，不希望计算中潜入让然困惑的NaN浮点值，
 *      当然，也可以抛出一个异常：
 *      if (x < 0) throw new IllegalArgumentException("x < 0")
 *      即使测试完成后，这个测试代码还一直保留在程序中。如果在程序中含有大量这种检查，程序运行起来比应有的速度慢一些。
 *      断言机制云溪你在测试期间在代码中Chaucer一些检查，而在生产代码中自动删除这些检查
 *
 *  2.Java语言有一个关键字assert，这个关键字有两种形式：
 *      assert condition;
 *      assert condition : expression;
 *      这两个语句都会计算条件（condition）,如果结果为false，则抛出一个AssertionError异常。
 *      在第二个语句中，表达式（expression）将传入AssertionError对象的构造器，并转换成一个消息字符串
 *
 *      表达式（expression）部分的唯一目的是生成一个消息字符串。AssertionError对象并不存储具体的表达式值，因此，以后无法得到这个表达式。
 *
 *  3.java.lang.ClassLoader
 *      void setDefaultAssertionStatus(boolean b)
 *      为通过这个类加载器加载的所有类（没有显示的类或包断言状态）启用或禁用断言。
 *
 *      void setClassAssertionStatus(String className, boolean b)
 *      为给定的类和他的内部类启用或禁用断言。
 *
 *      void setPackageAssertionStatus(String packageName, boolean b)
 *      为给定包机器子包中的所有类启用或禁用断言。
 *
 *      void clearAssertionStatus()
 *      删除所有显示的类和包断言状态设置，并禁用通过这个类加载加载的所有类断言。
 */
package page312Assert;

import java.util.Scanner;



public class Demo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入数字：");
        int n = sc.nextInt();

        assert n > 0;

        System.out.println(n);
        AssertDemo.main(new String[]{ });
    }
}

class AssertDemo {
    public static void main(String[] args) {
        System.out.println("执行AssertDemo.main");
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入数字：");
        int n = sc.nextInt();

        assert n > 0 : "测试文本";

    }
}

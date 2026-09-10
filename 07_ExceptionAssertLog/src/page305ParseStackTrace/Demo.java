/**
 * page 305
 * 7.2.6 分析栈轨迹元素
 *  1.使用StackWalker类，他会生成一个StackWalker.StackFrame实力流，其中每个实例分别描述一个栈帧。
 *
 *  java.lang.Throwable
 *      Throwable(Throwable cause)
 *      Throwable(String message, Throwable cause)
 *      用给定的cause原因构造一个Throwable对象
 *
 *      Throwable initCause(Throwable cause)
 *      为这个对象设置原因，如果这个对象已经有原因，则抛出一个异常
 *
 *      Throwable getCause()
 *      h获得设置为这个对象的原因的异常对象
 *
 *      StackTraceElement[] getStackTrace()
 *      获得构造这个对象时调用栈的轨迹
 *
 *      void addSuppressed(Throwable t)
 *      为这个异常添加一个被抑制的异常。这出现在try-with-resources语句中,其中t是close方法抛出的一个异常
 *
 *      Throwable[] getSuppressed()
 *      得到这个异常的所有被抑制的异常，一般来说，这些是try-with-resources语句中close方法抛出的异常
 *
 *  java.lang.Exception
 *      Exception(Throwable cause)
 *      Exception(String message, Throwable cause)
 *      用给定的cause构造一个Exception对象
 *
 *  java.lang.RuntimeException
 *      RuntimeException(Throwable cause)
 *      RuntimeException(String message, Throwable cause)
 *      用给定的cause构造一个RuntimeException对象
 *
 *  java.lang.StackWalker
 *      static StackWalker getInstance()
 *      static StackWalker getInstance(StackWalker.Option option)
 *      static StackWalker getInstance(Set<SrackWalker.Option> options)
 *      得到一个StackWalker实例。选项包括StackWalker.Option枚举中的RETAIN_CKASS_REFERENCE、SHOW_HIDDEN_FRAMES、SHOW_REFLECT_FRAMES
 *
 *      forEach(Consumer<? super Stream<StackWalker.StackFrame> action)
 *      在每个栈帧上完成给定的动作，从最近调用的方法开始
 *
 *      walk(Function<? super Stream<StackWalker.StackFrame>, ? extends T> function)
 *      对栈帧流应用给定的函数，返回这个函数的结果
 *
 *  java.lang.StackWalker.StackFrame
 *      String getFileName()
 *      得到包含改元素执行点的源文件的文件名，如果这个信息不可用则返回null
 *
 *      int getLineNumber()
 *      德高包含改元素执行点的源文件的行号，如果这个信息不可用则返回-1
 *
 *      String getClassName()
 *      对于包含改元素执行点的方法，得到这个方法所在的类的完全限定名
 *
 *      String getDeclaringClass()
 *      对于包含该元素执行点的方法，得到这个方法所在类的Class对象。如果这个栈遍历器不是用RETAIN_CLASS_REFERENCE选项构造的，则会抛出一个异常
 *
 *      string getMethodName()
 *      得到包含该元素执行点的方法的方法名。构造器名为<init>。静态初始化器名为<cinit>，无法区分同名的重载方法
 *
 *      boolean isNativeMethod()
 *      如果这个元素的执行点在一个原生方法中，则返回true
 *
 *      String toString()
 *      返回一个格式化字符串，包含类和方法名、文件名及行号（如果信息可用）
 *
 *  java.lang.StackTraceElement
 *      String getFileName()
 *      得到包含该元素执行点的源文件的文件名，如果这个信息不可用则返回null
 *
 *      int getLineNumber()
 *      得到包含该元素执行点的源文件的行号，如果这个信息不可用则返回-1
 *
 *      String getClassName()
 *      得到包含该元素执行点的类的完全限定名
 *
 *      String getMethodName()
 *      得到包含该元素指定点的方法的方法名。构造器名为<init>。静态初始化器名为<cinit>，无法区分同名的重载方法
 *
 *      string getMethodName()
 *      得到包含该元素执行点的方法的方法名。构造器名为<init>。静态初始化器名为<cinit>，无法区分同名的重载方法
 *
 *      boolean isNativeMethod()
 *      如果这个元素的执行点在一个原生方法中，则返回true
 */
package page305ParseStackTrace;

import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        StackTraceTest.runStackWalker();
    }
}

class StackTraceTest {
    public static void runStackWalker() {
        // 1.使用StackWalker类，他会生成一个StackWalker.StackFrame实力流，其中每个实例分别描述一个栈帧。
        StackWalker walker = StackWalker.getInstance();
        walker.forEach((frame) -> {
            String clsName = frame.getClass().getName();
            String mthName = frame.getMethodName();
            String fileName = frame.getFileName();
            int lineNumber = frame.getLineNumber();
            System.out.println("--------");
            System.out.println("file name:" + fileName);
            System.out.println("class name:" + clsName);
            System.out.println("method name:" + mthName);
            System.out.println("line number:" + lineNumber);
        });
    }
}

class StackTraceTestFactories {
    public static int factorial(int n) {
        System.out.println("factorial(" + n + ");");
        StackWalker walker = StackWalker.getInstance();
        walker.forEach(System.out::println);
        int r;
        if (n <= 1) r = 1;
        else r = n * factorial(n - 1);
        System.out.println("factorial(" + n + ") = " + r);
        return r;
    }

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            System.out.println("请输入因子：");
            int n = in.nextInt();
            factorial(n);
        }
    }
}
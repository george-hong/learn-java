/**
 * page 293
 * 7.1.2 声明检查型异常
 *  1.如果遇到了无法处理的情况，Java方法可以抛出一个异常。
 *    要在方法的首部指出这个方法可能抛出一个异常，所以要修改方法首部，以反映这个方法可能抛出的检查型异常，例如：
 *        public FileInputStream(String name) throws FileNotFoundException
 *    这个声明表示这个构造器将根据给定的String生成一个FileInputStream对象，但也有可能出错而抛出一个FileNotFoundException异常。
 *  2.编写自己的方法时，不必声明方法可能抛出的所有throwable对象，至于什么时候需要在所写的方法中用throws子句声明异常，以及要用throws子句声明
 *    哪些异常，需要记住在遇到下面4种情况时会抛出异常
 *      - 调用了一个抛出检查型异常的方法，例如：FileInputStream构造器
 *      - 检测到一个错误，并且利用throw语句抛出一个检查型异常
 *      - 程序出现错误，例如，a[-1]=0会抛出一个非检查型异常
 *      - Java虚拟机或运行时库出现内部错误
 *  3.如果一个方法有可能抛出多个检查型异常类型，那么就必须在方法的首部列出所有的异常类，每个异常类之间用逗号隔开
 *  4.但是不需要声明Java内部错误，即从Error继承的异常。任何代码都有可能抛出那些异常。类似的，也不应该声明从RuntimeException继承的那些非检查型异常
 *  5.如果在子类中覆盖了超类的一个方法，子类方法中声明的检查型异常不能比超类方法中声明的异常更通用。如果超类没有抛出任何检查型异常，子类也不能抛出任何
 *      检查型异常。
 *
 */
package page293DeclarationCheckException;

import java.io.EOFException;
import java.io.FileNotFoundException;

public class Demo {
    public static void main(String[] args) {

    }

    // 3.如果一个方法有可能抛出多个检查型异常类型，那么就必须在方法的首部列出所有的异常类，每个异常类之间用逗号隔开
    public static void maybeError() throws FileNotFoundException, EOFException {}

    // 4.但是不需要声明Java内部错误，即从Error继承的异常。任何代码都有可能抛出那些异常。类似的，也不应该声明从RuntimeException继承的那些非检查型异常
    // 以下是不好的示范，应避免出现这种异常
    public static void maybeError2() throws ArrayIndexOutOfBoundsException {}
}

class SuperError {
    public void maybeError() throws FileNotFoundException {}
}

class SubError extends SuperError {
    // 5.如果在子类中覆盖了超类的一个方法，子类方法中声明的检查型异常不能比超类方法中声明的异常更通用。如果超类没有抛出任何检查型异常，子类也不能抛出任何
    //   检查型异常。
    // java: page293DeclarationCheckException.SubError中的maybeError()无法覆盖page293DeclarationCheckException.SuperError中的maybeError()
    //  被覆盖的方法未抛出java.lang.Exception
    // public void maybeError() throws Exception {}
}
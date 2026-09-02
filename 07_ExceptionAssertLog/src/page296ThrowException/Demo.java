/**
 * page 296
 * 7.1.3 如何抛出异常
 *  在Java中，抛出异常主要依赖 throw
 * 7.1.4 创建异常类
 *  1.代码可能会遇到任何标准异常类都无法描述清楚的问题，在这种情况下，创建自己的异常类就是一件顺理成章的事情。我们要做的只是定义一个派生于Exception
 *      的类，或者派生于Exception的某个子类，如IOException。习惯做法是，自定义这个类应该包含两个构造器，一个是默认构造器，另一个是包含详细描述信息的构造器。
 *  2.java.lang.Throwable
 *      - Throwable() 构造一个新的Throwable对象，但没有详细描述信息
 *      - Throwable(String message) 构造一个新的Throwable对象，带有指定的描述信息
 *      - String getMessage() 获得Throwable对象的详细描述信息
 */
package page296ThrowException;

import java.io.FileNotFoundException;

public class Demo {
    static public void main(String[] args) throws FileNotFoundException, CustomException {
//        throwException();
        throw new CustomException();
    }

    static public void throwException() throws FileNotFoundException {
        throw new FileNotFoundException("reason");
    }
}

class CustomException extends Exception {
    public CustomException() {}
    public CustomException(String message) {
        super(message);
    }
}
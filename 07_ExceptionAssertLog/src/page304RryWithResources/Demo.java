/**
 * page 304
 *  7.2.5 try-width-resouces
 *  1.TWR 只关闭“成功初始化”的资源
 *  2.如果try块抛出异常，而且close方法也抛出一个异常。try-with-resources语句可以很好地处理这种情况。原来的异常会重新抛出，而close方法抛出的
 *      所有异常会“被抑制”。这些异常将被自动捕获，并有addSuppressed方法添加到原来的异常中。如果对这些异常感兴趣，可以调用getSuppressed方法，
 *      他会生成一个数组，其中包含close方法抛出的被抑制的异常。
 */
package page304RryWithResources;

import java.io.IOException;

public class Demo {
    public static void main(String[] args) {
        TryWithResources.simple();
        TryWithResources.simpleWithError();
    }
}

class TryWithResources {
    static void simple() {
        try (TestCloseable closable = new TestCloseable()) {
            closable.makeException();
            System.out.println("这里的代码不会执行");
        } catch (IOException e) {
            System.out.println("run catch");
        }
    }

    static void simpleWithError() {
        try (TestCloseableWithError closable = new TestCloseableWithError()) {

        } catch (IOException e) {
            var info = e.getSuppressed();
            System.out.println("info" + info);
        }
    }
}

class TestCloseable implements AutoCloseable {
    public void makeException() throws IOException {
        throw new IOException("sample error info");
    }

    public void close() {
        System.out.println("run close");
    }
}

class TestCloseableWithError implements AutoCloseable {
    public void close() throws IOException {
        throw new IOException("TestCloseableWithError error");
    }
}
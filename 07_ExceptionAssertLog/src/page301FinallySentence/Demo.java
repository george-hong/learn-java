/**
 * page 301
 * 7.2.4 finally子句
 *  1.代码抛出异常时，就会停止处理这个方法中剩余的代码，并退出这个方法啊，如果这个方法已经获得了只有他自己知道的一些本地资源，而且这些资源必须清理，
 *      这就会有问题。因为需要在try/catch中分别清理这些资源，finally子句可以解决这个问题
 *  2.不管代码是否异常，finally子句都将执行
 *  3.try语句中可以只有finally子句而没有catch子句
 *  4.当finally子句中包含return语句时，有可能产生意想不到的效果。假设由return语句从try语句块中间退出。再方法返回前，会执行finally子句块。
 *      如果finally块也有一个return语句，这个返回值将会遮蔽原来返回的值。
 */
package page301FinallySentence;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Demo {
    public static void main(String[] args) throws IOException {
//        TestFinally.run();
//        TestFinally.tryWithoutCatch();
//        TestFinally.nestTryCatch();
        String value = TestFinally.tryReturn();
        System.out.println(value); // return by finally
    }
}

class TestFinally {
    static void run() {
        try {
            String filePath = "07_ExceptionAssertLog/resource/input.txt";
            String errorFilePath = "input2.txt";
            String fileContent = Files.readString(Paths.get(errorFilePath), StandardCharsets.UTF_8);
            System.out.println("file content:" + fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 2.不管代码是否异常，finally子句都将执行
            System.out.println("执行finally");
        }
    }

    static void tryWithoutCatch() throws IOException {
        // 3.try语句中可以只有finally子句而没有catch子句
        try {
            String errorFilePath = "input2.txt";
            String fileContent = Files.readString(Paths.get(errorFilePath), StandardCharsets.UTF_8);
            System.out.println("这里的代码不会执行");
        } finally {
            System.out.println("执行tryWithoutCatch finally");
        }
        // 这里的代码也不会执行
        System.out.println("这里的代码也不会执行");
    }

    static void nestTryCatch() throws IOException {
        String errorFilePath = "input2.txt";
        try {
            String fileContent = Files.readString(Paths.get(errorFilePath), StandardCharsets.UTF_8);
            System.out.println("这里的代码不会执行");
        } catch (IOException e) {
            try {
                // catch中如果有异常会被重新抛出
                String fileContent = Files.readString(Paths.get(errorFilePath), StandardCharsets.UTF_8);
            } catch (IOException e1) {
                System.out.println("重新捕获异常");
            }
            System.out.println("执行nestTryCatch catch");
        }
    }

    static String tryReturn() {
        // 4.当finally子句中包含return语句时，有可能产生意想不到的效果。假设由return语句从try语句块中间退出。再方法返回前，会执行finally子句块。
        // 如果finally块也有一个return语句，这个返回值将会遮蔽原来返回的值。
        try {
            return "return by try";
        } finally {
            return "return by finally";
        }
    }
}

/**
 * page 299
 * 7.2.2 捕获多个异常
 *  在一个try语句块中可以捕获多个异常类型，并对不同类型的异常做出不同的处理。要为每个异常类使用一个单独的catch子句，如下所示：
 *  try {
 *      code that might throw exception
 *  } catch (FileNotFoundException e) {
 *      emergency action for missing file
 *  } catch (UnknownHostException e) {
 *      emergency action for unknown hosts
 *  } catch (IOException e) {
 *      emergency action for all other I/O problems
 *  }
 *  异常对象可能包含有关异常性质的信息，要想获得这个对象的更多茜茜，可以尝试e.getMessage() 得到详细的错误消息（如果有），或者使用
 *  e.getClass().getName()得到异常对象的实际类型
 *  捕获多个异常时，异常变量隐含为final变量
 *
 *  7.3.2 再次抛出异常与异常链
 *      1.可以在catch子句中抛出一个异常。通常，希望改变异常的类型时会这样做。
 *      2.可以把原始异常设置为新异常的原因，捕获到这个异常时，可以使用getCause()获取原始异常
 */
package page299CatchMultiException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Demo {
    public static void main(String[] args) throws Exception {
        String filePath = "07_ExceptionAssertLog/resource/input.txt";
        String errorFilePath = "input2.txt";
        try {

//            FileInputStream fis = new FileInputStream(filePath);
//            InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
//            BufferedReader br = new BufferedReader(isr);
//            String content = "";
//            String fragment = "";
//            while (fragment != null) {
//                fragment = br.readLine();
//                content += fragment;
//            }
//            System.out.println(content);

            String fileContent = Files.readString(Paths.get(errorFilePath), StandardCharsets.UTF_8);
            System.out.println(fileContent);
        } catch (FileNotFoundException e) {
            System.out.println("错误：未找到文件");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("错误：IO异常");
            e.printStackTrace();
        }

        try {
            try {
                FileInputStream errInputStream = new FileInputStream(errorFilePath);
            } catch (FileNotFoundException e) {
                // 可以在catch子句中抛出一个异常。通常，希望改变异常的类型时会这样做。
                // throw new Exception("自定义文件不存在错误");
                Exception newException = new Exception("自定义异常");
                newException.initCause(e);
                throw newException;
            }
        } catch (Exception e) {
            var cause = e.getCause();
            System.out.println(cause);
        }
    }
}

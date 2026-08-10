/**
 * page 212
 * 1.Class类提供了一个很有用的服务可以查找资源文件
 * 2.URL getResource(String name)    InputStream getResourceAsStream，找到与类位于同一位置的资源，然后返回一个URL或者输入流，可以用来加载这个资源
 */
package page212Resource;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Demo {
    public static void main(String[] args) throws IOException {
        Class cl = Demo.class;
        URL url = cl.getResource("./images.jpg");
        ImageIcon icon = new ImageIcon(url);

        InputStream textStream = cl.getResourceAsStream("test.txt");
        var txt = new String(textStream.readAllBytes(), "UTF-8");

        JOptionPane.showMessageDialog(null, txt, "标题文本", JOptionPane.INFORMATION_MESSAGE, icon);
    }
}

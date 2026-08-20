/**
 * page 261
 * 6.2.6 变量作用域
 *  1.repeatMessage方法中的lambda表达式引用了text自由变量，这些值被lambda表达式捕获。
 *  2.为了确保所捕获的值是明确定义的，这里有一个重要的限制。在lambda表达式中，只能引用值不会改变的变量。如果在lambda表达式中更改变量，并发执行多个
 *   动作是就会不安全。
 *  3.另外如果在lambda表达式中引用一个变量，而这个变量可能在外部改变，这也是不合法的
 *  4.lambda表达式中捕获的变量必须是事实最终变量，这个变量初始化之后就不会在为他赋值。
 *  5.lambda标识的体育嵌套快有相同的作用域。这里同样适用命名冲突和遮蔽有关规则。
 *  6.在一个lambda表达式中使用this关键字时，是指创建这个lambda表达式的方法的this参数
 */
package page261VariableScope;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Demo {
    public static void main(String[] args) {

        repeatMessage("Hello", 1000);

        countDown(10, 20);

        JOptionPane.showMessageDialog(null, "关闭？");
        System.exit(0);
    }

    // 1.repeatMessage方法中的lambda表达式引用了text自由变量，这些值被lambda表达式捕获。
    public static void repeatMessage(String text, int delay) {
        ActionListener al = (ActionEvent e) -> System.out.println(text);
        Timer timer = new Timer(delay, al);
        timer.start();
    }

    public static void countDown(int start, int end) {
        ActionListener al = (ActionEvent e) -> {
            // 6.2.6    2.为了确保所捕获的值是明确定义的，这里有一个重要的限制。在lambda表达式中，只能引用值不会改变的变量。
            // 以下代码报错
            // java: 从lambda 表达式引用的本地变量必须是最终变量或实际上的最终变量
            start--;
        };
    }

    public static void repeat(String text, int count) {
        for (int i = 0; i < count; i++) {
            // 6.2.6    3.另外如果在lambda表达式中引用一个变量，而这个变量可能在外部改变，这也是不合法的
            // java: 从lambda 表达式引用的本地变量必须是最终变量或实际上的最终变量
            ActionListener al = (ActionEvent e) -> System.out.println(i + ":" +text);
        }
    }

    public static void testVariable() {
        // 6.2.6    5.lambda标识的体育嵌套快有相同的作用域。这里同样适用命名冲突和遮蔽有关规则。
        String text = "Hello";
        // java: 已在方法 testVariable()中定义了变量 text
        Runnable fn = (String text) -> {
            System.out.println(text);
        };
    }

    public void useThis() {
        // 6.2.6    6.在一个lambda表达式中使用this关键字时，是指创建这个lambda表达式的方法的this参数
        ActionListener al = (ActionEvent e) -> {
            // 在这里this指向的是Demo的实例
            System.out.println(this.toString());
        };
    }
}

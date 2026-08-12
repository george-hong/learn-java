/**
 * page 244
 * 1.javax.swing.JoptionPane
 *  static void showMessageDialog(Component parent, Object message)
 *  显示一个对话框，包含一条提示小时和OK按钮。对话框位于parent中中央，如果parent为null，则位于屏幕的中央
 * 2.javax.swing.Timer
 *  Timer(int interval, ActionListener listener)
 *  构造一个定时，没经过interval毫秒通知Listener一次
 *  void start 启动定时器。
 *  void stop 停止定时器。
 */

package page244InterfaceCallback;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;

public class Demo {
    public static void main(String[] args) {
        TimePrinter timePrinter = new TimePrinter();
        Timer timer = new Timer(1000, timePrinter);
        timer.start();

        JOptionPane.showMessageDialog(null, "Quit?");
        System.exit(0);
    }
}

// 要使用Timer对象，需要实现接口ActionListener
class TimePrinter implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        System.out.println("Print timer:" + Instant.ofEpochMilli(e.getWhen()));
    }
}
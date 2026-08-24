/**
 * page 273
 * 6.3.4 局部内部类
 *  1.可以在方法中定义类
 *  2.声明局部类时不能有访问说明符（public private）
 *  3.局部类有一个很大的优势，即对外部世界完全影藏，甚至外部类中的其他代码也不能访问它。
 * 6.3.5 由外部方法访问变量
 *  1.局部类不仅能够访问外部类的字段，还能访问局部变量，不过这些变量必须是事实最终变量。
 *
 */
package page273ScopedInnerClass;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;

public class Demo {
    public static void main(String[] args) {
        TalkClock clock = new TalkClock(2000, true);
        clock.start();
        clock.start2();

        JOptionPane.showMessageDialog(null, "Confirm to exit?");
        System.exit(0);
    }
}

class TalkClock {
    private int interval;
    private boolean beep;

    TalkClock(int interval, boolean beep) {
        this.interval = interval;
        this.beep = beep;
    }

    public void start() {
        // 6.3.4    1.可以在方法中定义类
        // 6.3.4    2.声明局部类时不能有访问说明符（public private）
        // java: 非法的表达式开始
        // private class TimerPrinter implements ActionListener {
        class TimerPrinter implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                if (beep) System.out.println("Current time is " + Instant.ofEpochMilli(event.getWhen()));
            }
        }

        TimerPrinter printer = new TimerPrinter();
        Timer timer = new Timer(interval, printer);
        timer.start();
    }

    public void start2() {
        boolean beep2 = true;
        int interval2 = 1000;
        class TimerPrinter implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                if (beep2) System.out.println("Current time in start2 is " + Instant.ofEpochMilli(event.getWhen()));
            }
        }

        TimerPrinter printer = new TimerPrinter();
        Timer timer = new Timer(interval2, printer);
        timer.start();
    }
}

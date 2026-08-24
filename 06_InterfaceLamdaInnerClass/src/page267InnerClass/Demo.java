/**
 * page 267
 * 6.3 内部类
 *  1.内部类是定义在另一个类中的类。使用它主要有两个原因
 *      - 内部类可以对同一个包中的其他类隐藏
 *      - 内部类方法可以访问定义这些放的作用域中的数据，包括原本私有的数据。
 * 6.3.1 使用内部类访问对象状态
 * 6.3.2
 *  1.内部类的特殊语法规则，外部类引用的正规语法为 OutClass.this
 *  2.可以采用以下语法明确的编写内部类对象的构造器： outObject.new InnerClass(construction parameters)
 *  3.如果内部类的修饰符是public 则可以用以下语法引用内部类： OuterClass.innerClass
 *  4.内部类中声明的静态字段必须是final，并初始化为一个编译时常量。如果这个字段不是一个常量就可能不唯一；内部类不能有static方法。
 */
package page267InnerClass;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;

public class Demo {
    public static void main(String[] args) {
        TalkingClock talkingClock = new TalkingClock(2000, true);
        talkingClock.start();
        // 6.3.2    3.如果内部类的修饰符是public 则可以用以下语法引用内部类： OuterClass.innerClass
        TalkingClock.TimePrinter timePrinter = talkingClock.new TimePrinter();

        JOptionPane.showMessageDialog(null, "退出程序？");
        System.exit(0);
    }
}

class TalkingClock {
    private int interval;
    private boolean beep;
    public TalkingClock(int interval, boolean beep) {
        this.interval = interval;
        this.beep = beep;
    }

    public void start() {
        // 6.3.2    2.可以采用以下语法明确的编写内部类对象的构造器： outObject.new InnerClass(construction parameters)
        TimePrinter timePrinter = this.new TimePrinter();
        // 等同于以下代码
        // TimePrinter timePrinter = new TimePrinter();
        Timer timer = new Timer(interval, timePrinter);
        timer.start();
    }

    public class TimePrinter implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            System.out.println("The time is " + Instant.ofEpochMilli(event.getWhen()));
            // 6.3.2    1.内部类的特殊语法规则，外部类引用的正规语法为 OutClass.this
            // 以下beep引用相当于TalkingClock.this.beep
            if (beep) System.out.println("beep is true");
        }
    }

    private class Test {
        static int value;
    }
}
/**
 * page 275
 * 6.3.6 匿名内部类
 *  1.假如指向只想类的一个对象，甚至不需要为类指定名字，一般语法如下:
 *      new SuperType(construction parameters) {
 *          inner class methods and data
 *      }
 *      在这里，SuperType可以是接口，如ActionListener，如果是这样，内部类就要实现这个接口。SuperType也可以是一个类，如果是这样，内部类就要拓展这个类
 *  2.由于构造器的名字必须与类名相同，而匿名内部类没有类名，所以，匿名内部类不能有构造器。实际上，构造参数要传递给超类构造器
 *  3.尽管匿名类不能有构造器，但可以提供一个对象初始化块
 */
package page275AnonymousInnerClass;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;

public class Demo {
    public static void main(String[] args) {
        TalkingClock clock = new TalkingClock();
        clock.start();

        clock.useInnerClass();

        JOptionPane.showMessageDialog(null, "Confirm to close?");
        System.exit(0);
    }
}

class TalkingClock {

    public void start() {
        // 以下匿名内部类的含义是：创建一个类的新对象，这个类实现了ActionListener接口，需要实现的方法actionPerformed是大括号中定义的方法
        var listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Current time is " + Instant.ofEpochMilli(e.getWhen()));
            }
        };

        Timer timer = new Timer(1000, listener);
        timer.start();
    }

    public void useInnerClass() {
        // SuperType也可以是一个类，如果是这样，内部类就要拓展这个类
        var p = new Person() {
            // 6.3.6    3.尽管匿名类不能有构造器，但可以提供一个对象初始化块
            {
                System.out.println("inner class initial block");
            }
            public void sayHello() {
                System.out.println("Hello Inner class");
            }
        };

        p.sayHello();
    }
}

class Person {
    public void sayHello() {
        System.out.println("Hello Person");
    }
}
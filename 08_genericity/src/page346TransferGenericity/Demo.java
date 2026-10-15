/**
 * page 346
 * 8.5.3 转换泛型方法
 *  类型擦除也会出现在泛型方法中。
 *
 *  虚拟机中没有泛型，只有普通的类和方法
 *  所有的类型参数都会替换为他们的限定类型
 *  会合成桥方法来保持多态
 *  为保持类型安全性，必要时会插入强制类型转换
 *
 */
package page346TransferGenericity;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.Dictionary;
import java.util.Hashtable;

public class Demo {
}

class Pair<T> {
    private T first;
    private T second;

    public Pair() {
        first = null;
        second = null;
    }

    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return this.first;
    }

    public T getSecond() {
        return this.second;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public void setSecond(T second) {
        this.second = second;
    }
}

class DateInterval extends Pair<LocalDate> {
    public static void main(String[] args) {
        DateInterval interval = new DateInterval();
        Pair<LocalDate> pair = interval;
        pair.setFirst(LocalDate.now());
        pair.setSecond(LocalDate.now());
    }

    // DateInterval有自己的setSecond方法，而他的超类Pair也有setSecond方法
    // 我们希望setSecond调用具有多态性，应该调用适当的方法。因为Pair引用了一个DateInterval对象，所以应该调用DateInterval.setSecond。
    //      问题在于类型擦除与多态发生了冲突。为了解决这个问题，编译器在DateInterval类中生成了一个桥方法：
    //      public void setSecond(Object second) { setSecond(LocalDate) second; }
    public void setSecond(LocalDate second) {
        System.out.println("调用DateInterval的setSecond方法");
        if (second.compareTo(getFirst()) >= 0) {
            super.setSecond(second);
        }
    }
}


class OldCode {
    public static void main(String[] args) {
        Dictionary<Integer, Component> labelTable = new Hashtable<>();
        labelTable.put(0, new JLabel("0"));
        labelTable.put(1, new JLabel("1"));
        JSlider slider = new JSlider();
        slider.setLabelTable(labelTable);
        // 以下代码会有类型错误提示
        // Unchecked assignment: 'java.util.Dictionary' to 'java.util.Dictionary<java.lang.Integer,java.awt.Component>'
        // 添加以下注解可以移除类型错误提示
        @SuppressWarnings("unchecked")
        Dictionary<Integer, Component> label = slider.getLabelTable();
    }
}
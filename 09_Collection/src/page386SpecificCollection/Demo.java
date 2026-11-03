/**
 * 9.3.1 链表
 * 列表都有一个重大的缺陷。就是从数组中间删除一个元素开销很大，其原因是数组中位于被删除元素之后的所有元素要都向数组的开端移动。
 * 在数组中间插入一个元素也是如此
 * 链表解决了这个问题。数组是在连续的存储位置上存放对象引用，而链表则是将每个对象存放在单独的链接中。每个链接还存放着序列中的下一个链接的引用。
 * 在Java程序设计语言中，所有的链表实际上都有双向链接，
 *
 * 链表与泛型集合有一个重要的区别。链表是一个有序集合，每个对象的位置十分重要。LinkedList.add方法将对象添加到链表的尾部。
 * 但是，常常需要将元素添加到链表的中间。因为迭代器描述了集合中的位置，所以这种依赖于位置的add方法将有迭代器负责。
 * 不过，只有对自然有序的集合使用迭代器来添加元素才有意义。
 * 集合类库提供了一个子接口ListIterator，其中包含add方法。与Collection.add不同，这个方法不返回boolean类型的值，他假定add操作总会改变链表。
 * 另外，ListIterator接口有两个方法可以用来反向遍历链表
 * E previous()
 * boolean hasPrevious()
 * 与next方法一样，previous方法会返回越过的对象。
 *
 * remove操作与退格键的工作方式不太一样，在调用next之后，remove方法确实会删除迭代器左侧的元素，这与退格键一样。但是，如果调用了previous，
 * 则会删除迭代器右侧的元素。而且不能连续调用两次remove
 * add方法值依赖于迭代器的位置，而remove方法不同，它依赖于迭代器的状态
 */
package page386SpecificCollection;

import common.CollectionHelper;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class Demo {
    public static void main(String[] args) {
        // 以下示例代码先添加三个元素，然后将第二个元素删除
        var staff = new LinkedList<String>();
        staff.add("Amy");
        staff.add("Bob");
        staff.add("Carl");
        Iterator<String> it = staff.iterator();
        String first = it.next();
        String second = it.next();
        it.remove();
    }
}

class ListIteratorDemo {
    public static void main(String[] args) {
        var staff = new LinkedList<String>();
        staff.add("Amy");
        staff.add("Bob");
        staff.add("Carl");
        ListIterator<String> it = staff.listIterator();
        it.next();
        it.add("Juliet");
        it.add("Dannel");
        CollectionHelper.print(staff); // Amy Juliet Dannel Bob Carl 成功在第二个位置插入了Juliet Dannel
    }
}

class ListIteratorDemo2 {
    public static void main(String[] args) {
        var staff = new LinkedList<String>();
        staff.add("Amy");
        staff.add("Bob");
        staff.add("Carl");
        staff.add("Dannel");

        var staff2 = new LinkedList<String>();
        staff2.add("Amy");
        staff2.add("Bob");
        staff2.add("Carl");
        staff2.add("Dannel");

        ListIterator<String> it = staff.listIterator();
        ListIterator<String> it2 = staff2.listIterator();

        it.next();
        it.next();
        it.remove();
        CollectionHelper.print(staff); // Amy Carl Dannel 移除了左侧的元素

        it2.next();
        it2.next();
        it2.next();
        it2.next();
        it2.previous();
        it2.previous();
        it2.remove();
        CollectionHelper.print(staff2); // Amy Bob Dannel 移除了右侧的元素

    }
}
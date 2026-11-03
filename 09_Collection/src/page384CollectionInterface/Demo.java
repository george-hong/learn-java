/**
 * 9.2 集合框架中的接口
 * List是一个有序集合。元素会增加到容器中的特定位置。可以采用两种方式访问元素：
 * 使用迭代器访问，或者使用一个整数索引来访问。后面这种方法称为随机访问，因为这样可以按任意顺序访问元素。
 * 与之不同，迭代器必须顺序的访问元素
 *
 * List接口定义了多个用于随机访问的方法
 * void add(int index, E element)
 * void remove(int index)
 * E get(int index)
 * E set(int index, E element)
 *
 * 为了避免对链表执行随机访问操作，Java1.4引入了一个标记接口RandomAccess。
 * 这个接口不包含任何方法，不过可以用它来测试一个特定的集合是否支持高效的随机访问
 */
package page384CollectionInterface;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.RandomAccess;

public class Demo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Hello");
        list.add("World");
        // List接口定义了多个用于随机访问的方法
        // void add(int index, E element)
        // void remove(int index)
        // E get(int index)
        // E set(int index, E element)
        list.add(1, "My");
        printList(list); // Hello My World
        list.remove(1);
        printList(list); // Hello World
        System.out.println(list.get(1)); // World
        list.set(1, "System");
        printList(list); // Hello System

        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("Hello");
        linkedList.add("World");
        // 为了避免对链表执行随机访问操作，Java1.4引入了一个标记接口RandomAccess。
        // 这个接口不包含任何方法，不过可以用它来测试一个特定的集合是否支持高效的随机访问
        if (linkedList instanceof RandomAccess) {
            System.out.println("can random access");
        } else {
            System.out.println("can't random access");
        }
    }

    public static void printList(List<?> list) {
        String str = "";
        boolean isFirst = true;
        for (var item : list) {
            if (!isFirst) str += " ";
            if (isFirst) {
                isFirst = false;
            }
            str += item;
        }
        System.out.println(str);
    }
}

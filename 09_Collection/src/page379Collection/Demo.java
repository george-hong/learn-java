/**
 * 9.1.2 Collection接口
 *  在Java类库中，集合类的基本接口是Collection接口。这个接口只有两个基本方法
 *  public interface Collection<E> {
 *      boolean add(E element);
 *      Iterator<E> iterator();
 *  }
 *  add方法用于向集合中添加元素。如果添加的元素确实改变了集合就返回true，否则返回false。
 *  iterator方法用于返回一个实现了Iterator接口的对象。可以使用这个迭代器对象依次访问集合中的元素
 *
 * 9.1.3 迭代器
 *  Iterator接口包含4个方法：
 *  public interface Iterator<E> {
 *      E next();
 *      boolean hasNext();
 *      void remove();
 *      default void forEachRemaining(Consumer<? super E> action);
 *  }
 *  通过反复调用next方法，可以煮个访问集合中的每个元素。但是，如果到了集合的末尾，next方法将抛出一个NoSuchElementException。因此，在调用next
 *  之前需要调用hasNext方法。如果迭代器对象还有更多可以访问的元素，这个方法就返回true.如果想要查看结合中的所有元素，就请求一个迭代器，当hasNext
 *  返回true时就反复的调用next方法
 *
 *  Collection接口拓展了Iterable接口。因此，对于标准类库中的任何集合都可以使用 for each 循环
 *  也可以不写循环，而是调用forEachRemaining方法并一共一个lambda表达式。将对迭代器的每一个元素调用这个表达式
 *
 *  Iterator接口的remove方法会删除上次调用next方法返回的元素。在大多数情况下，这是有道理的，在决定某个元素确实是要删除的元素之前，应该先看一下
 *  这个元素。不过，如果想要删除指定位置上的元素，仍然需要越过这个元素。
 *  更重要的是，next方法和remove方法调用之间存在依赖性。调用remove之前没有调用next,将是不合法的。如果这样做，将会抛出一个IllegalStateException
 *  异常。
 *  如果想要删除两个相邻的元素，不能直接这样调用：
 *  item.remove();
 *  item.remove(); // ERROR
 *  实际上，必须要先调用next越过将要删除的元素
 */
package page379Collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Demo {
    public static void main(String[] args) {

    }


}


class IteratorDemo {
    public static void main(String[] args) {
        // 9.1.3
        // Collection接口拓展了Iterable接口。因此，对于标准类库中的任何集合都可以使用 for each 循环
        // 也可以不写循环，而是调用forEachRemaining方法并一共一个lambda表达式。将对迭代器的每一个元素调用这个表达式
        Collection<String> c = new ArrayList<>();
        c.add("A");
        c.add("B");
        c.add("C");
        Iterator<String> it = c.iterator();
        while (it.hasNext()) {
            String str = it.next();
            System.out.println(str);
        }

        Iterator<String> it2 = c.iterator();
        it2.forEachRemaining(str -> System.out.println("for each remaining:" + str));

        // Iterator接口的remove方法会删除上次调用next方法返回的元素。在大多数情况下，这是有道理的，在决定某个元素确实是要删除的元素之前，应该先看一下
        // 这个元素。不过，如果想要删除指定位置上的元素，仍然需要越过这个元素。
        Iterator<String> it3 = c.iterator();
        it3.next();
        it3.remove();
        System.out.println("after remove:" + c);

        it3.next();
        it3.remove();
        // 如果想要删除两个相邻的元素，不能直接这样调用：
        // item.remove();
        // item.remove(); // ERROR
        // 实际上，必须要先调用next越过将要删除的元素
        // Exception in thread "main" java.lang.IllegalStateException
//        it3.remove();
    }


}
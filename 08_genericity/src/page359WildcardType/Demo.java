/**
 * 8.8 通配符类型
 * 8.8.1 通配符概念
 *  在通配符类型中，运行类型参数变化。例如，通配符类型Pair<? extends Employee>表示任何泛型Pair类型，他的类型参数是Employee的子类
 *  使用通配符不会通过Pair<? extends Employee>的引用破坏Pair<Manager>
 * 8.8.2 通配符的超类型限定
 *  通配符限定与类型变量限定十分类似，但是，他们还有一个附加的能力，可以指定一个超类型限定，如下所示
 *  ? super Manager
 *  这个通配符限制为Manger的所有超类型。带有超类型限定的通配符会提供一种行为，这与通配符行为正好相反。可以为方法提供参数，但不能使用返回值。
 *  直观的讲，带有超类型限定的通配符允许写入一个泛型对象，而带有子类型限定的通配符允许读取一个泛型对象
 *
 *  超类型限定的另一个常见的用法是作为一个函数式接口的参数类型。例如Collection接口有一个方法：
 *  default boolean removeIf(Predicate<? super E> filter)
 *  这个方法会删除所有满足给定谓词条件的元素。
 *
 */
package page359WildcardType;

import common.Pair;
import common.Employee;
import common.Manager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.function.Predicate;

public class Demo {
    public static void main(String[] args) {

    }
}

class WildType {
    public static void main(String[] args) {
        Manager m1 = new Manager("Jack", 5000, 200);
        Manager m2 = new Manager("Lucy", 2000, 200);
        Employee e1 = new Employee("Candy", 800);
        var mPair = new Pair<>(m1, m2);
        // 不能将Pair<Manager>传递给Pair<Employee>，因为这两个类型没有任何关系
        // java: 不兼容的类型: common.Pair<common.Manager>无法转换为common.Pair<common.Employee>
        // printBuddiesNotCompatible(mPair);
        // 但是Pair<? extends Employee>是Pair<Employee>的子类型，因此以下方法能够正常执行
        printBuddies(mPair);

        Pair<? extends Employee> wildTypeBuddies = mPair;
        // 使用通配符不会通过Pair<? extends Employee>的引用破坏Pair<Manager>
        // java: 不兼容的类型: common.Employee无法转换为capture#1, 共 ? extends common.Employee
        // 实际上setFirst方法是不允许调用的，因为编译器不知道这个? extends Employee是哪个具体的子类，因此会拒绝所有除null的参数
        // wildTypeBuddies.setFirst(e1);

        // getFirst方法可以继续工作。因为返回值的类型一定是Employee或其子类
        System.out.println(wildTypeBuddies.getFirst());
    }

    public static void printBuddiesNotCompatible(Pair<Employee> p) {
        Employee first = p.getFirst();
        Employee second = p.getSecond();
        System.out.println(first.getName() + " and " + second.getName() + " are buddies.");
    }

    public static void printBuddies(Pair<? extends Employee> p) {
        Employee first = p.getFirst();
        Employee second = p.getSecond();
        System.out.println(first.getName() + " and " + second.getName() + " are buddies.");
    }
}

class WildTypeSuper {
    public static void main(String[] args) {
        Manager m1 = new Manager("Jack", 5000, 200);
        Manager m2 = new Manager("Lucy", 2000, 500);
        Manager m3 = new Manager("Kite", 3000, 100);
        minmaxBonus(new Manager[]{ m1, m2, m3 }, new Pair<Employee>());

    }

    public static void minmaxBonus(Manager[] a, Pair<? super Manager> result) {
        if (a.length == 0) return;
        Manager min = a[0];
        Manager max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i].getBonus() > max.getBonus()) max = a[i];
            if (a[i].getBonus() < min.getBonus()) min = a[i];
        }
        result.setFirst(min);
        result.setSecond(max);
    }
}

class ArrayAlg {
    public static void main(String[] args) {
        LocalDate[] dates = {
                LocalDate.of(2026, 10, 29),
                LocalDate.of(2026, 10, 30),
                LocalDate.of(2026, 10, 31),
        };
        var datePair = minmax(dates);
        System.out.println(datePair);
    }

//    public static <T extends Comparable<? super T>> Pair<T> minmax(T[] strList) {
    public static <T extends Comparable<T>> Pair<T> minmax(T[] strList) {
        if (strList == null || strList.length == 0) {
            return null;
        }
        T min = strList[0];
        T max = strList[0];
        for (int i = 1; i < strList.length; i++) {
            T cValue = strList[i];
            if (cValue.compareTo(min) < 0) {
                min = cValue;
            }
            if (cValue.compareTo(max) > 0) {
                max = cValue;
            }
        }
        return new Pair<>(min, max);
    }
}

class WildTypeSuperDemo {
    public static void main(String[] args) {
        ArrayList<Employee> employees = new ArrayList<>();
        Employee e1 = new Employee("Jack", 5000);
        Employee e2 = new Employee("Lucy", 2000);
        Employee e3 = new Employee("Kite", 3000);
        employees.add(e1);
        employees.add(e2);
        employees.add(e3);
        Predicate<Object> condition = (Object o) -> {
            return o.toString().contains("Jack");
        };
        // 超类型限定的另一个常见的用法是作为一个函数式接口的参数类型。例如Collection接口有一个方法：
        // default boolean removeIf(Predicate<? super E> filter)
        // 这个方法会删除所有满足给定谓词条件的元素。
        // 这个方法希望能够传入Predicate<Object>而不只是Predicate<Employee>
        employees.removeIf(condition);
        System.out.println(employees);
    }
}
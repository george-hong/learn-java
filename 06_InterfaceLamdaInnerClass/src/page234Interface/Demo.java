/**
 * page 234
 * 1.接口中的所有方法都自动是public方法，因此在接口中声明方法时，不必提供关键字public
 * 2.为了让类实现一个接口，需要完成以下两个步骤
 *  将类声明为实现给定的接口
 *  对接口中的所有方法提供定义
 * 3.java.lang.Comparable<T>
 *  int compareTo(T other)
 *  对这个对象与other进行比较，如果这个对象小于other则返回一个负整数，如何二者相等则返回0，否则返回一个正整数
 * 4.java.util.Arrays
 *  static void sort(Object[] a)
 *  对数组中的元素进行排序，要求数组中的元素必须属于实现了Comparable接口的类，并且元素之间是可比较的
 * 5.java.lang.Integer
 *  static int compare(int x, int y)
 *  如果x < y返回一个负整数,如果x、y相等返回0，如果x > y返回正整数
 * 6.java.lang.Double
 *  static int compare(double x, double y)
 *  如果x < y返回一个负整数,如果x、y相等返回0，如果x > y返回正整数
 * 7.接口不是类，不能使用new操作符来实例化，但能声明接口变量
 * 8.接口可以拓展
 * 9.接口不能包含实例字段，但是可以包含常量，接口中的字段总是 public static final,这些实例字段可以通过类直接访问
 * 10.接口可以是密封的（sealed），与密封类一样，直接子类型（可以是类或者是接口）必须在permits字句中声明，或者放在同一个文件中
 * 11.在Java8中，允许在接口中增加静态方法，这些方法只能通过接口来调用，Java9中，接口中的方法可以是private，由于private方法只能在接口本身的方法中使用，所以只能作为接口中的其他方法的辅助方法
 * 12.可以为任何接口方法提供一个默认实现，必须用default修饰符标记这样一个方法，如果实现类没有实现这个默认方法，则会使用接口上的默认方法
 *
 */

package page234Interface;

import java.util.Arrays;

public class Demo {
    public static void main(String[] args) {
        Employee[] empList = {
                new Employee("Jack", 1000.001),
                new Employee("Harry", 1000.0),
                new Employee("Li", 3000.0)
        };
        Arrays.sort(empList);
        System.out.println(Arrays.toString(empList));
        // 7.接口不是类，不能使用new操作符来实例化，但能声明接口变量
        // 以下代码报错 java: page234Interface.CustomComparable是抽象的; 无法实例化
        // var cc = new CustomComparable<String>();
        // 声明类型变量是可以的
        CustomComparable<Employee> customEmployee = empList[0];
        // 9.接口不能包含实例字段，但是可以包含常量，接口中的字段总是 public static final,这些实例字段可以通过类直接访问
        double someNumber = ClassImplementWithField.someNumber;
        // 尝试修改接口上的常量会报错 java: 无法为最终变量someNumber分配值
        // ClassImplementWithField.someNumber = 123.0;
        // 11.在Java8中，允许在接口中增加静态方法，这些方法只能通过接口来调用，Java9中，接口中的方法可以是private，由于private方法只能在接口本身的方法中使用，所以只能作为接口中的其他方法的辅助方法
        // 以下代码ide报错 Static method may only be called on its containing interface
        // System.out.println("ClassImplementWithField.getSomeNumber(): " + ClassImplementWithField.getSomeNumber());
        System.out.println("WithField.getSomeNumber(): " + WithField.getSomeNumber());
        // 12.可以为任何接口方法提供一个默认实现，必须用default修饰符标记这样一个方法，如果实现类没有实现这个默认方法，则会使用接口上的默认方法
        ClassImplementWithDefaultMethod classUseInterfaceDefaultMethod = new ClassImplementWithDefaultMethod();
        ClassImplementWithCMethodUseSelfMethod classUseSelfMethod = new ClassImplementWithCMethodUseSelfMethod();
        classUseInterfaceDefaultMethod.isEmpty();
        classUseSelfMethod.isEmpty();
    }
}

interface CustomComparable<T> {
    int compareTo(T other);
}

//  2.为了让类实现一个接口，需要完成以下两个步骤
//  将类声明为实现给定的接口
//  对接口中的所有方法提供定义
class Employee implements CustomComparable<Employee>, Comparable<Employee> {
    private String name;
    private double salary;

    Employee(String name, Double salary) {
        this.name = name;
        this.salary = salary;
    }

    public int compareTo(Employee other) {
        return Double.compare(this.salary, other.salary);
    }

    public String toString() {
        return getClass().getName() + "[name=" + name + ", salary=" + salary + "]";
    }
}

// 8.接口可以拓展
interface SecondCustomComparable<T> extends CustomComparable<T> {
    void move();
}

// 9.接口不能包含实例字段，但是可以包含常量，接口中的字段总是 public static final,这些实例字段可以通过类直接访问
interface WithField {
    double someNumber = 1000.0;
    String someString = "hello";

    static double getSomeNumber() {
        return someNumber;
    }

    static String getSomeString() {
        return getSomeStringInner();
    }

    static private String getSomeStringInner() {
        return someString;
    }
}

class ClassImplementWithField implements WithField {}

// 10.接口可以是密封的（sealed），与密封类一样，直接子类型（可以是类或者是接口）必须在permits字句中声明，或者放在同一个文件中
sealed interface SealedInterface permits interfaceChildOfSealedInterface, classChildOfSealedInterface {}

non-sealed interface interfaceChildOfSealedInterface extends SealedInterface {}

final class classChildOfSealedInterface implements SealedInterface {}

interface InterfaceWithDefaultMethod {
    int size();
    default boolean isEmpty() {
        System.out.println("run with interface default isEmpty");
        return size() == 0;
    }
}

class ClassImplementWithDefaultMethod implements InterfaceWithDefaultMethod {
    public int size() {
        return 3;
    }

    public boolean isEmpty() {
        System.out.println("run with class default isEmpty");
        return size() == 0;
    }
}

class ClassImplementWithCMethodUseSelfMethod implements InterfaceWithDefaultMethod {
    public int size() {
        return 3;
    }
}
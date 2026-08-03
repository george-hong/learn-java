/**
 * page 181
 * 1.Object中还有一个重要的方法toString，它会返回一个字符串，表示这个对象的值
 * 2.绝大多数对象的toString方法都返回,类名随后是一堆方括号括起来的字段值
 * 3.设计子类应该定义自己的toString方法，并加入子类的字段
 * 4.toString方法无处不在，这有一个重要的原因：只要对象与一个字符串通过操作符“+”拼接起来，Java编译器就会自动的调用toString方法来获得这个对象的字符串描述
 * 5.Object类定义了toString方法，会打印对象的类名和散列码
 * 6.数组继承了Object类的继承方法，采用了一种古老的格式打印，补救方法是调用静态方法Arrays.toString()
 * 7.要想正确的打印多维数组，需要调用Arrays.deepToString方法
 */
package page181ObjectToString;

import java.util.Arrays;

public class Demo {
        public static void main(String[] args) {
            Person person = new Person();
            Employee emp = new Employee("Harry", 5000);
            Manager man = new Manager("Boss", 6000, 2000);

            System.out.println("emp toString:" + emp.toString()); // emp toString:page181ObjectToString.Employee[name=Harry,salary=5000]
            System.out.println("man toString:" + man.toString()); // man toString:page181ObjectToString.Manager[name=Boss,salary=6000][bonus=2000.0]
            // 4.toString方法无处不在，这有一个重要的原因：只要对象与一个字符串通过操作符“+”拼接起来，Java编译器就会自动的调用toString方法来获得这个对象的字符串描述
            System.out.println("" + emp); // page181ObjectToString.Employee[name=Harry,salary=5000]
            // 5.Object类定义了toString方法，会打印对象的类名和散列码
            System.out.println("person toString:" + person.toString()); // person toString:page181ObjectToString.Person@119d7047
            // 6.数组继承了Object类的继承方法，采用了一种古老的格式打印，补救方法是调用静态方法Arrays.toString()
            Object[] list = { "Hello", 123, 456.789 };
            System.out.println("list toString:" + list); // list toString:[Ljava.lang.Object;@12edcd21
            System.out.println("Arrays.toString(list):" + Arrays.toString(list)); // Arrays.toString(list):[Hello, 123, 456.789]
            // 7.要想正确的打印多维数组，需要调用Arrays.deepToString方法
            Object[][] deepList = { { "Hello", "first"}, { 123, 456.789 } };
            System.out.println("Arrays.deepToString(deepList):" + Arrays.deepToString(deepList)); // Arrays.deepToString(deepList):[[Hello, first], [123, 456.789]]
        }
}

class Person {}

class Employee {
    private String name;
    private int salary;

    public Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    // 2.绝大多数对象的toString方法都返回,类名随后是一堆方括号括起来的字段值
    public String toString() {
        return getClass().getName()
                + "[name=" + name + ","
                + "salary=" + salary + "]";
    }
}

class Manager extends Employee {
    private double bonus;

    public Manager(String name, int salary, double bonus) {
        super(name, salary);
        this.bonus = bonus;
    }

    public String toString() {
        return super.toString() + "[bonus=" + bonus + "]";
    }
}

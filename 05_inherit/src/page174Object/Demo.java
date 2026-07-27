/**
 * Object是所有类的始祖，Java中的每一个类都拓展了Object，只有基本类型不是对象
 * 1.Object中的equals方法用于检测一个对象是否等于另外一个对象，Object类实现的方法将判断对象引用是否相同
 * 2.Object的getClass方法将返回一个对象所属的类
 */
package page174Object;

public class Demo {
    public static void main(String[] args) {
        // Java中的每一个类都拓展了Object
        Object obj = new Employee();

        Employee emp1 = new Employee("Jack");
        Employee emp2 = new Employee("Jack");

        System.out.println(emp1.equals(emp1)); // true
        System.out.println(emp1.equals(emp2)); // false
        System.out.println(emp1.getClass());

    }
}

class Employee {
    public String name;
    public

    Employee(String name) {
        this.name = name;
    }

    Employee() {
        this("demo");
    }
}

class Manager extends Employee {
    public double bonus;
    Manager(double bonus) {
        this.bonus = bonus;
    }
}
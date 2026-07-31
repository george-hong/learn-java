/**
 * Object是所有类的始祖，Java中的每一个类都拓展了Object，只有基本类型不是对象
 * 1.Object中的equals方法用于检测一个对象是否等于另外一个对象，Object类实现的方法将判断对象引用是否相同
 * 2.Object的getClass方法将返回一个对象所属的类
 */
package page174Object;

import java.util.Objects;

public class Demo {
    public static void main(String[] args) {
        // Java中的每一个类都拓展了Object
        Object obj = new Employee();

        Employee emp1 = new Employee("Jack", 6000);
        Employee emp2 = new Employee("Jack", 6000);
        Employee emp3 = new Employee("Jackson", 6000);

        System.out.println("emp1 == emp1:" + emp1.equals(emp1)); // true
        System.out.println("emp1 == emp2:" + emp1.equals(emp2)); // false
        System.out.println("emp1 == emp3:" + emp1.equals(emp3)); // false
        System.out.println(emp1.getClass());

    }
}

class Employee {
    public String name;
    public double salary;

    Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    Employee() {
        this("demo", 5000);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (getClass() != other.getClass()) {
            return false;
        }
        Employee obj = (Employee) other;
        return Objects.equals(name, obj.name) && salary == obj.salary;
    }
}

class Manager extends Employee {
    public double bonus;
    Manager(double bonus) {
        this.bonus = bonus;
    }
}
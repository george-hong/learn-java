/**
 * page 248
 * clone是Object的一个protected方法，代码不能直接调用这个方法。只有Employee类克隆Employee对象。这个显示是有原因的。
 * Object类如何实现clone，它对这个对象一无所知，所以只能逐字段拷贝。如果对象中的所有实例字段都是基本类型，拷贝这些字段没有问题。
 * 但是如果对象包含子对象的引用，拷贝字段就会得到相同子对象的另一个引用，这样一来，两个对象仍然会共享一些信息。
 * Clonable是Java中少数标记接口之一，标记接口不包含任何方法，他的优势是进入了Java的类型系统，允许通过 instanceof进行检查，如if (x instanceof Clonable) ...
 */
package page248ClonableInterface;

import java.time.Instant;
import java.util.Date;

public class Demo {
    public static void main(String[] args) throws CloneNotSupportedException {
        // Object中的clone方法是浅拷贝，因此修改克隆对象会影响原始对象
        Employee emp1 = new Employee("Harry", 5000, new Date());
        Employee emp1Cloned = emp1.clone();
        emp1Cloned.changeHireDate(); // 原始clone方法，修改克隆出来的对象会影响原对象
        System.out.println("emp1:      " + emp1.toString());
        System.out.println("emp1Cloned:" + emp1Cloned.toString());
        // 单独克隆hireDate之后不会影响原对象
        Employee emp2 = new Employee("Jack", 8000, new Date());
        Employee emp2Cloned = emp2.prefectClone();
        emp2Cloned.changeHireDate();
        System.out.println("emp2:      " + emp2.toString());
        System.out.println("emp2Cloned:" + emp2Cloned.toString());
    }
}

class Employee implements Cloneable {
    private String name;
    private double salary;
    private Date hireDate;
    public Employee(String name, double age, Date hireDate) {
        this.name = name;
        this.salary = age;
        this.hireDate = hireDate;
    }

    public String toString() {
        return getClass().getName() + "[name=" + name + ", salary=" + salary + ", hireDate=" + Instant.ofEpochMilli(hireDate.getTime()) + "]";
    }

    public void changeHireDate() {
        this.hireDate.setYear(0);
    }

    public Employee clone() throws CloneNotSupportedException {
        return (Employee) super.clone();
    }

    public Employee prefectClone() throws CloneNotSupportedException {
        Employee clone = (Employee) super.clone();
        clone.hireDate = (Date) this.hireDate.clone();
        return clone;
    }
}

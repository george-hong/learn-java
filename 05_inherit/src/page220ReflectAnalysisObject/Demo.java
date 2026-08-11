/**
 * page 220
 * 1.Field类中的get方法可以获取指定对象指定字段的值
 * 2.Field类中的set方法可以设置值
 * 3.setAccessible方法AccessibleObject类中的方法，它是Field、Method、Constructor类的公共超类。
 *  这个特性是为调试、持久存储和类似机制提供的。如果不允许访问，setAccessible调用会抛出一个异常，访问可能被模块系统或安全管理器拒绝。
 */
package page220ReflectAnalysisObject;

import java.lang.reflect.Field;

public class Demo {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Employee employee = new Employee("Harry", 8000);
        Field nameField = employee.getClass().getDeclaredField("name");
        // 私有字段如果不设置可访问性，会抛出IllegalAccessException异常
        nameField.setAccessible(true);
        // 1.Field类中的get方法可以获取指定对象指定字段的值
        System.out.println("employee name:" + nameField.get(employee));
        // 2.Field类中的set方法可以设置值
        nameField.set(employee, "Port");
        System.out.println("employee name after set:" + nameField.get(employee));

        System.out.println("employee:" + employee.toString());
    }
}

class Employee {
    private String name;
    private double salary;
    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public String toString() {
        try {
            return new ObjectAnalyzer().toString(this);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }
}

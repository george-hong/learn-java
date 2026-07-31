/**
 * page179
 * 1.hash code是由对象导出的一个整型值。散列码是没有规律的。如果x和y是两个不同的对象，那么x.hashCode()与y.hashCode()基本上不会相同
 * 2.由于hashCode方法定义在Object类中，因此每个对象都都有一个默认的散列码，其值由对象的存储地址得出
 * 3.字符串的散列码是由内容导出的
 * 4.hashCode方法应该返回一个整数（可以是负数）
 * 5.生成散列码时，不用对象的散列码应尽量分开，要使用null安全的Objects.hashCode方法，如果为null会返回0
 * 6.更好的做法是，需要组合多个散列值时，调用Objects.hash并提供所有这些值为参数，这个方法会对各个参数调用Objects.hashCode并组合做这些散列值
 */
package page179ObjectHashCode;

import java.util.Objects;

public class Demo {
    public static void main(String[] args) {
        // 3.字符串的散列码是由内容导出的，因此helloStr1、helloStr2的散列码是相同的
        String helloStr1 = "Hello";
        String helloStr2 = "Hello";
        // 2.helloObj1、helloObj2具有不同的散列码
        StringBuilder helloObj1 = new StringBuilder("Hello");
        StringBuilder helloObj2 = new StringBuilder("Hello");

        Employee emp = new Employee("Harry", 5000);

        System.out.println("helloStr1 hash code:" + helloStr1.hashCode());
        System.out.println("helloStr2 hash code:" + helloStr2.hashCode());
        System.out.println("helloObj1 hash code:" + helloObj1.hashCode());
        System.out.println("helloObj2 hash code:" + helloObj2.hashCode());
        System.out.println("emp hash code by hasCode2:" + emp.hashCode2());
        System.out.println("emp hash code by hasCode:" + emp.hashCode());
    }
}


class Employee {
    private String name;
    private double salary;
    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    // 5.生成散列码时，不用对象的散列码应尽量分开，要使用null安全的Objects.hashCode方法，如果为null会返回0
    public int hashCode2() {
        return Objects.hashCode(name) + Double.hashCode(salary);
    }

    // 6.更好的做法是，需要组合多个散列值时，调用Objects.hash并提供所有这些值为参数，这个方法会对各个参数调用Objects.hashCode并组合做这些散列值
    public int hashCode() {
        return Objects.hash(name, salary);
    }
}
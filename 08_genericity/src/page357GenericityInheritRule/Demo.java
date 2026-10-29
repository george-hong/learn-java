/**
 * 8.7
 * 一般来讲，无论S与T有什么关系，Pair<S>与Pair<T>都没有任何关系
 * 总是可以将参数化类型转换为一个原始类型。例如，Pair<Employee>是原始类型Pair的一个子类型
 *
 */
package page357GenericityInheritRule;

import java.util.function.Supplier;

public class Demo {
    public static void main(String[] args) {
        Manager[] managers = { new Manager(), new Manager() };
        Employee[] employees = managers;
        // 企图在Manager数组中添加Employee对象，报ArrayStoreException异常
        employees[0] = new Employee();

        Pair<Manager> managerPair = new Pair<>();
        // java: 不兼容的类型: page357GenericityInheritRule.Pair<page357GenericityInheritRule.Manager>无法转换为page357GenericityInheritRule.Pair<page357GenericityInheritRule.Employee>
        // Pair<Employee> employeePair = managerPair;
    }
}

class Employee {

}

class Manager extends Employee {

}

class Pair<T> {
    private T first;
    private T second;

    public Pair() {
        first = null;
        second = null;
    }

    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return this.first;
    }

    public T getSecond() {
        return this.second;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public void setSecond(T second) {
        this.second = second;
    }

    public static <T> page349Limited.Pair<T> makePair(Supplier<T> s) {
        return new page349Limited.Pair<>(s.get(), s.get());
    }

    public static <T> page349Limited.Pair<T> makePair2(Class<T> cl)  {
        try {
            return new page349Limited.Pair<>(cl.getConstructor().newInstance(), cl.getConstructor().newInstance());
        } catch (Exception e) {
            return new page349Limited.Pair<>();
        }
    }
}
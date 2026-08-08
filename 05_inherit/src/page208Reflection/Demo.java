/**
 * page 208
 * 反射哭提供了一个丰富且精巧的工具集，可以用来编写动态动态操纵Java代码的程序。能够分析类能力的程序称为可反射，反射可以用来
 *  - 在运行时分析类的能力
 *  - 在运行设检查对象
 *  - 实现泛型数组操作代码
 *  - 利用Method对象
 *
 *  1.在程序运行期间，Java运行时系统始终为所有对象维护一个运行时类型标识，这个信息回跟踪每个对象所属的类，Class类可以访问这些信息，Object.getClass()方法回返回一个Class类型的实例
 *  2.Class对象回描述一个特定类的属性，最常用的Class方法就是getName，这个方法返回类的名字，如果类在一个包里，包名也作为类类名的一部分
 *  3.可以使用Class.forName(className)获得类名对应的Class对象，如果className是类名或接口名，这个方法可以正常至心血管
 *  4.第三个方法是通过T.class获取（T是任意的Java类型或void关键字）
 *  5.虚拟机为每个类型管理一个唯一的Class对象，因此，可以使用==运算符比较两个类对象
 *  6.如果有一个Class类型的对象，可以用它构造类的实例，调用getConstructor方法将得到一个Constructor类型的对象，然后使用newInstance方法来构造一个实例
 *  7.Constrtctor getConstructor(Class... parameterTypes)生成一个对象，描述有指定参数类型的构造器
 *  8.Object newInstance(Object... params)将params传递到构造器，来构造这个构造器声明类的一个新实例
 */
package page208Reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public class Demo {
    public static void main(String[] args) {
        Employee employee = new Employee("Solar", 1000);
        // 1.在程序运行期间，Java运行时系统始终为所有对象维护一个运行时类型标识，这个信息回跟踪每个对象所属的类，Class类可以访问这些信息，Object.getClass()方法回返回一个Class类型的实例
        Class employeeClass = employee.getClass();
        // 2.Class对象回描述一个特定类的属性，最常用的Class方法就是getName，这个方法返回类的名字
        System.out.println("employeeClass.getName():" + employeeClass.getName()); // employeeClass.getName():page208Feflection.Employee
        // 3.可以使用Class.forName(className)获得类名对应的Class对象，如果className是类名或接口名，这个方法可以正常执行，否则将抛出一个检查型异常
        try {
            System.out.println(Class.forName("page208Reflection.Employee")); // class page208Reflection.Employee
            System.out.println(Class.forName("java.util.Random")); // class java.util.Random
            // Class.forName("not exist"); // java.lang.ClassNotFoundException: not exist
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // 4.第三个方法是通过T.class获取（T是任意的Java类型或void关键字）
        System.out.println("Random.class:" + Random.class); // Random.class:class java.util.Random
        System.out.println("int.class:" + int.class); // int.class:int
        System.out.println("Double[].class:" + Double[].class); // Double[].class:class [Ljava.lang.Double;
        // 5.虚拟机为每个类型管理一个唯一的Class对象，因此，可以使用==运算符比较两个类对象
        System.out.println("employee.getClass() == Employee.class:" +  (employee.getClass() == Employee.class)); // employee.getClass() == Employee.class:true
        // 6.如果有一个Class类型的对象，可以用它构造类的实例，调用getConstructor方法将得到一个Constructor类型的对象，然后使用newInstance方法来构造一个实例
        try {
            Class randomClass = Class.forName("java.util.Random");
            Object obj = randomClass.getConstructor().newInstance();
            System.out.println(obj);

            Class employeeClass2 = Class.forName("page208Reflection.Employee");
            Constructor employeeConstructor2 = employeeClass2.getDeclaredConstructor(String.class, double.class);
            Employee employee2 = (Employee) employeeConstructor2.newInstance("Lucky", 5000);
            System.out.println("employee2:" + employee2);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

class Employee {
    private String name;
    private double salary;

    Employee(String name, double salary) {
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
        return this.getClass().getName() + "[name=" + name + ", salary=" + salary + "]";
    }
}

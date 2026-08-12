/**
 * page 242
 * 6.1.6 Java中处理同名方法的规则比较简单，具体如下
 *  - 超类优先。如果超类提供了一个具体方法，同名且有相同参数类型的默认方法会被忽略
 *  - 接口冲突。如果一个接口提供了一个默认方法，另一个接口也提供了相同签名的同名方法，那么必须覆盖这个方法来解决冲突
 *  - 如果两个接口都没有提供默认实现，就不存在冲突，相当于这个方法是抽象的，需要实现。
 */
package page242InterfaceConflict;

public class Demo {
    public static void main(String[] args) {
        Employee employee = new Employee();
        // 超类优先。如果超类提供了一个具体方法，同名且有相同参数类型的默认方法会被忽略
        // 这里会调用超类Person上的getName方法
        System.out.println(employee.getName()); // person get name method
    }
}

interface WithGetNameMethod {
    default String getName() {
        return "interface get name method";
    }
}

interface WithGetNameMethodToo {
    default String getName() {
        return "interface get name method too";
    }
}

interface WithGetNameMethodEmpty {
    String getName();
}

class Person {
    public String getName() {
        return "person get name method";
    }
}

class Employee extends Person implements WithGetNameMethod {

}

// 接口冲突。如果一个接口提供了一个默认方法，另一个接口也提供了相同签名的同名方法，那么必须覆盖这个方法来解决冲突
// java: 类型 page242InterfaceConflict.WithGetNameMethod 和 page242InterfaceConflict.WithGetNameMethodToo 不兼容；
class Manage implements WithGetNameMethod, WithGetNameMethodToo {
    // 自行定义getName后问题解决
    public String getName() {
        return "manage get name method";
    }
}

// java: page242InterfaceConflict.Boss不是抽象的, 并且未覆盖page242InterfaceConflict.WithGetNameMethodEmpty中的抽象方法getName()
class Boss implements WithGetNameMethod, WithGetNameMethodEmpty {
    public String getName() {
        return "boss get name method";
    }
}
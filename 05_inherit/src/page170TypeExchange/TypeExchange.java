/**
 * p170 强制转换语法 Type variableName = (Type) otherVariable;
 * 1.将子类转换为父类型是允许的，也只能在继承层次结构内进行强制转换
 * 2.将父类转换为子类型是不允许的，会报ClassCastException异常
 * 3.可以使用instanceof进行继承结构检查
 */
package page170TypeExchange;

public class TypeExchange {
    public static void main(String[] args) {
        Manager manager = new Manager();
        // 1.将子类转换为父类型是允许的
        Employee employee = (Employee) manager;
        // 2.将父类转换为子类型是不允许的，会报ClassCastException异常
        // Manager manager1 = (Manager) new Employee();
        // 3.可以使用instanceof进行继承结构检查
        if (manager instanceof Employee) {
            System.out.println("manager 通过了继承结构检查");
        }
    }
}

class Employee {

}

class Manager extends Employee {

}
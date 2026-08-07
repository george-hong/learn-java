/**
 * page 197
 * 1.包含一个或多个抽象方法的类本身必须被声明为抽象的
 * 2.抽象方法相当于子类中实现的具体方法的占位符，拓展一个抽象类时，可以有两种选择，一种是在子类中保留抽两类中的部分或所有抽象方法，这样就必须将子类也标记为抽象类；
 *  另外一种中法是定义全部方法，这样依赖，子类就不再是抽象的
 * 3.抽象类不能实例化
 * 4.抽象类仍然可以创建对象变量，但是这样的变量只能引用非抽象子类对象
 */
package page197abstractClass;

public class Demo {
    public static void main(String[] args) {
        Employee employee = new Employee("Harry");
        Student student = new Student("Lora");

        System.out.println(employee.getDescription());
        System.out.println(student.getDescription());

        // 3.抽象类不能实例化
        // 直接报错  java: page197abstractClass.Person是抽象的; 无法实例化
        // Person person = new Person("Lucy"); // 'Person' is abstract; cannot be instantiated

        // 4.抽象类仍然可以创建对象变量，但是这样的变量只能引用非抽象子类对象
        Person person1 = new Employee("Lucid");
        Person person2 = new Student("Faker");

        person1.getDescription();
        person2.getDescription();
    }
}

abstract class Person {
    private String name;
    public Person(String name) {
        this.name = name;
    }
    abstract public String getDescription();
    public String getName() {
        return name;
    }
}

// 由于有没有实现的抽象方法，FilledPerson只能是抽象类
abstract class FilledPerson extends Person {
    FilledPerson(String name) {
        super(name);
    }
}

class Employee extends Person {
    Employee(String name) {
        super(name);
    }

    public String getDescription() {
        return "I‘m an employee, and name is" + super.getName() + ".";
    }
}

class Student extends Person {
    Student(String name) {
        super(name);
    }

    public String getDescription() {
        return "I'm a student, and name is" + super.getName() + ".";
    }
}
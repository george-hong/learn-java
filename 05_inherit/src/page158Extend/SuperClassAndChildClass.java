package page158Extend;

/**
 * p158 关键字extends表示继承，被继承的类成为超累、基类、父类，新的类成为子类、派生类
 */
public class SuperClassAndChildClass {
    public static void main(String[] args) {

    }
}

class Employee {
    String name;
    private double salary;

    Employee(String aName, double aSalary) {
        name = aName;
        salary = aSalary;
    }

    public String getName() {
        return name;
    }


    public double getSalary() {
        return salary;
    }
}

class Manager extends Employee {
    private int bouns;

    Manager(String aName, double aSalary, int aBouns) {
        super(aName, aSalary);
        bouns = aBouns;
    }


}

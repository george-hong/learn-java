package page158Extend;

/**
 * p158 关键字extends表示继承，被继承的类成为超累、基类、父类，新的类成为子类、派生类
 * 1.子类如果需要调用父类上的方法，需要使用super关键字
 * 2.子类数组可以转换成父类数组
 * 3.子类覆盖父类的方法时，返回值类型需要兼容父类返回值类型，只能是原本的类型或其子类
 */
public class SuperClassAndChildClass {
    public static void main(String[] args) {
        Employee employee = new Employee("Kitty", 5000);
        Manager boss = new Manager("Boss", 8000, 3000);

        Employee employee2 = new Employee("Candy", 5500);
        Employee[] employeeList = { boss, employee, employee2 };
        // 这里演示 1 通过super方法调用父类方法getSalary
        for (Employee e : employeeList) {
            // 这里的 e 能够正确调用Manager/Employee对象上正确的getSalary方法，这种特性称为多态
            System.out.println(e.getName() + "'s salary is " + e.getSalary());
        }
        boss.setBonus(4000);
        // employeeList[0]虽然和boss是同一个对象，但employeeList[0]的类型是Employee，因此调用setBonus会报错
        // employeeList[0].setBonus(5000);

        // 2. 子类数组转换为超类数组
        Manager[] mList = new Manager[2];
        Employee[] eList = mList;
        mList[0] = boss;
        // eList[1] = employee; // 这一步会引发ArrayStoreException异常
//        for (Employee e : eList) {
//            // 这里的 e 能够正确调用Manager/Employee对象上正确的getSalary方法，这种特性称为多态
//            System.out.println(e.getName() + "'s salary is " + e.getSalary());
//        }
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

    public void raiseSalary(double rate) {
        salary = salary * rate;
    }

    public Employee getBuddy() {
        return this;
    }
}

class Manager extends Employee {
    private double bonus;

    Manager(String aName, double aSalary, int aBouns) {
        super(aName, aSalary);
        bonus = aBouns;
    }

    public double getSalary() {
        // 使用super关键字访问父类方法
        double baseSalary = super.getSalary();
        return bonus + baseSalary;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double aBonus) {
        bonus = aBonus;
    }

    // 3.子类覆盖父类的方法时，返回值类型需要兼容父类返回值类型，只能是原本的类型或其子类
    public Manager getBuddy() {
        return this;
    }
}

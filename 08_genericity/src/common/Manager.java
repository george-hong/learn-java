package common;

public class Manager extends Employee {
    private double bonus;

    public Manager(String aName, double aSalary, int aBouns) {
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
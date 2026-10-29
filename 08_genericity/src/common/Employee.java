package common;

public class Employee {
    String name;
    private double salary;

    public Employee(String aName, double aSalary) {
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

    public String toString() {
        return getClass().getName() + "[name=" + name + ",salary=" + salary + "]";
    }
}
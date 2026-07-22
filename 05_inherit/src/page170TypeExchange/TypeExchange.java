package page170TypeExchange;

public class TypeExchange {
    public static void main(String[] args) {
        Manager manager = new Manager();
        Employee employee = (Employee) manager;
    }
}

class Employee {

}

class Manager extends Employee {

}
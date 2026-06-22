import java.util.*;

public class Input {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入姓名");
        String name = sc.nextLine();
        System.out.println("请输入年龄");
        int age = sc.nextInt();
        System.out.print("您的姓名为" + name + ",年龄为" + age);
    }
}
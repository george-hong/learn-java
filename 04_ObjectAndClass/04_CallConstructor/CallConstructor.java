/**
 * p 129
 * 如果构造器的第一个语句形如 this(...) 那么这个构造器将调用同一个类的另一个构造器
 */
public class CallConstructor {
    public static void main(String[] args) {

    }
}

//
class Test {
    public int value;
    public int value2;

    Test() {
        // 如果this(21);不是第一个语句，那么将会报错 Call to 'this()' must be first statement in constructor body
//         System.out.println("Test");
        this(21);
    }

    Test(int value) {
        this.value = value;
    }
}
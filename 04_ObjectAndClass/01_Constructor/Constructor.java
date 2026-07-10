/**
 * page 126
 * 1. 如果一个类没有提供任何构造参数，那么他会自动拥有一个无参构造器
 * 2. 如果提供了有参构造器，那么就不会自动拥有无参构造器，必须使用有参构造器
*/
public class Constructor {
    public static void main(String[] args) {

        // 1.
        TestConstructor test1 = new TestConstructor();

        System.out.println(test1);


        // 2.
        ParamsConstructor paramsConstructor = new ParamsConstructor(8);
        // 以下代码将报错
        // java: 无法将类 ParamsConstructor中的构造器 ParamsConstructor应用到给定类型;
        // 需要: int
        // 找到:    没有参数
        // 原因: 实际参数列表和形式参数列表长度不同
//        ParamsConstructor paramsConstructor2 = new ParamsConstructor();
    }
}

// 1. 这个类会自动生成一个无参构造器   可以通过new 创建实例
class TestConstructor {
    int testInt;
}


class ParamsConstructor {
    int testInt;
    public ParamsConstructor(int aTextInt) {
        testInt = aTextInt;
    }
}
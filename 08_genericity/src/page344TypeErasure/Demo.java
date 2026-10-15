/**
 * page 344
 * 8.5 泛型代码和虚拟机
 *  8.5.1 类型擦除
 *      无论何时定义一个泛型类型，都会自动提供一个相应的原始类型。这个原始类型的名字就是去掉类型参数后的泛型类型名。类型变量会被擦除，并替换为其限
 *          定类型（对于无限定的变量则替换为Object）
 *
 *      原始类型用第一个限定来替换类型变量，如果没有给定限定，就替换为Object
 *
 *  8.5.2 转换泛型表达式
 *      编写一个泛型方法调用时，如果擦除了返回类型，编译器会插入强制类型转换
 */
package page344TypeErasure;

import java.io.Serializable;

public class Demo {
    public static void main(String[] args) {
        Pair<String> pair = new Pair("first", "second");
        Interval<String> interval = new Interval("first", "second");
    }
}

// 1.      无论何时定义一个泛型类型，都会自动提供一个相应的原始类型。这个原始类型的名字就是去掉类型参数后的泛型类型名。类型变量会被擦除，并替换为其限
//            定类型（对于无限定的变量则替换为Object）
// 以下类的编译结果为
//class page344.Pair<T> {
//    private T first;
//
//    private T second;
//
//  public page344.Pair();
//    Code:
//    0: aload_0
//    1: invokespecial #1                  // Method java/lang/Object."<init>":()V
//    4: aload_0
//    5: aconst_null
//    6: putfield      #7                  // Field first:Ljava/lang/Object;
//    9: aload_0
//    10: aconst_null
//    11: putfield      #13                 // Field second:Ljava/lang/Object;
//    14: return
//
//  public page344.Pair(T, T);
//    Code:
//    0: aload_0
//    1: invokespecial #1                  // Method java/lang/Object."<init>":()V
//    4: aload_0
//    5: aload_1
//    6: putfield      #7                  // Field first:Ljava/lang/Object;
//    9: aload_0
//    10: aload_2
//    11: putfield      #13                 // Field second:Ljava/lang/Object;
//    14: return
//
//    public T getFirst();
//    Code:
//    0: aload_0
//    1: getfield      #7                  // Field first:Ljava/lang/Object;
//    4: areturn
//
//    public T getSecond();
//    Code:
//    0: aload_0
//    1: getfield      #13                 // Field second:Ljava/lang/Object;
//    4: areturn
//
//    public void setFirst(T);
//    Code:
//    0: aload_0
//    1: aload_1
//    2: putfield      #7                  // Field first:Ljava/lang/Object;
//    5: return
//
//    public void setSecond(T);
//    Code:
//    0: aload_0
//    1: aload_1
//    2: putfield      #13                 // Field second:Ljava/lang/Object;
//    5: return
//}
class Pair<T> {
    private T first;
    private T second;

    public Pair() {
        first = null;
        second = null;
    }

    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return this.first;
    }

    public T getSecond() {
        return this.second;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public void setSecond(T second) {
        this.second = second;
    }
}

class Interval<T extends Comparable & Serializable> implements Serializable {
    private T lower;
    private T upper;

    public Interval(T first, T second) {
        lower = first;
        upper = second;
    }
}

class Employee {
    private String name;
    Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}

//class page344TypeErasure.Transfer {
//    page344TypeErasure.Transfer();
//    Code:
//    0: aload_0
//    1: invokespecial #1                  // Method java/lang/Object."<init>":()V
//    4: return
//
//    public static void main(java.lang.String[]);
//    Code:
//    0: new           #7                  // class page344TypeErasure/Pair
//    3: dup
//    4: new           #9                  // class page344TypeErasure/Employee
//    7: dup
//    8: ldc           #11                 // String 测试
//    10: invokespecial #13                 // Method page344TypeErasure/Employee."<init>":(Ljava/lang/String;)V
//    13: new           #9                  // class page344TypeErasure/Employee
//    16: dup
//    17: ldc           #16                 // String 测试2
//    19: invokespecial #13                 // Method page344TypeErasure/Employee."<init>":(Ljava/lang/String;)V
//    22: invokespecial #18                 // Method page344TypeErasure/Pair."<init>":(Ljava/lang/Object;Ljava/lang/Object;)V
//    25: astore_1
//    26: getstatic     #21                 // Field java/lang/System.out:Ljava/io/PrintStream;
//    29: aload_1
//    30: invokevirtual #27                 // Method page344TypeErasure/Pair.getFirst:()Ljava/lang/Object;
//    33: checkcast     #9                  // class page344TypeErasure/Employee     // ← 强制转换在这里！
//    36: invokevirtual #31                 // Method page344TypeErasure/Employee.getName:()Ljava/lang/String;
//    39: invokevirtual #35                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
//    42: return
//}
class Transfer {
    public static void main(String[] args) {
        Pair<Employee> buddy =  new Pair(new Employee("测试"), new Employee("测试2"));
        // 8.5.2 编写一个泛型方法调用时，如果擦除了返回类型，编译器会插入强制类型转换
        System.out.println(buddy.getFirst().getName());
    }
}
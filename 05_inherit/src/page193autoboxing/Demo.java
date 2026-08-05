/**
 * page 193
 * 1.所有的基本类型都有一个与之对应的类，这些类称为包装器
 * 2.如果要定义整形数组列表，要使用对应的类而不是基本类型
 * 3.由于每个值分别包装在一个对象中，所以ArrayList<Integer>的效率远低于int[]数组
 * 4.当将一个Integer对象赋值给一个int值是，会自动拆箱
 * 5.自动装、拆箱也适用于算数表达式
 * 6.==运算符可以应用于包装器对象，不过检测的是对象是否有相同的内存位置，正确比较包装器的方法是调用equals方法
 * 7.自动装箱规范要求boolean、byte、char(<=127)，结余-128和127之间的short和int包装到固定的对象中
 * 8.包装器类引用可以为null，所以自动装箱可能会爆出NullPointerException
 */
package page193autoboxing;

import java.util.ArrayList;

public class Demo {
    public static void main(String[] args) {
        // 1.所有的基本类型都有一个与之对应的类，这些类称为包装器
        Integer integer1 = 1;
        Long long1 = 1L;
        Float float1 = 1.1f;
        Double double1 = 1.0;
        Short short1 = 1;
        Byte byte1 = 1;
        Character character1 = 'a';
        Boolean boolean1 = true;
        // 2.如果要定义整形数组列表，要使用对应的类而不是基本类型
        ArrayList<Integer> intList = new ArrayList<Integer>();
        intList.add(3); // 将自动转换成inList.add(Integer.valueOf(3));
        // 4.当将一个Integer对象赋值给一个int值时，会自动拆箱
        int int2 = intList.get(0); // 这里的结果就是一个字面量3，而不是包装对象
        // 5.自动装、拆箱也适用于算数表达式
        Integer int3 = 3;
        int3++;
        System.out.println("int3 increase:" + int3);
        // 6.==运算符可以应用于包装器对象，不过检测的是对象是否有相同的内存位置
        Integer int4 = 1000;
        Integer int5 = 1000;
        System.out.println("int4 == int5:" + (int4 == int5)); // int4 == int5:false
        System.out.println("int4.equals(int5):" + int4.equals(int5)); // int4.equals(int5):true
        System.out.println(intList);
        // 7.自动装箱规范要求boolean、byte、char(<=127)，介于-128和127之间的short和int包装到固定的对象中
        Integer integer100a = 100;
        Integer integer100b = 100;
        System.out.println("integer100a == integer100b:" + (integer100a == integer100b)); // integer100a == integer100b:true

        // 8.包装器类引用可以为null，所以自动装箱可能会爆出NullPointerException
        Integer integerNull = null;
        // int intErr = 2 * integerNull; // Exception in thread "main" java.lang.NullPointerException: Cannot invoke "java.lang.Integer.intValue()" because "integerNull" is null


        // java.lang.Integer中的常用方法
        // int intValue 将Integer对象的值作为int返回
        System.out.println("integer100a.intValue():" + integer100a.intValue());
        // static String toString(int i) // 返回一个新的String对象，表示指定数值的十进制表示
        System.out.println(Integer.toString(1001)); // 1001
        // static String toString(int i, int radix) // 返回一个新的String对象，表示指定数值的指定进制表示
        System.out.println(Integer.toString(1001, 2)); // 1111101001
        // static int parseInt(String s) // 返回一个整数，指定字符串必须表示一个十进制整数
        System.out.println(Integer.parseInt("1001")); // 1001
        // static int parseInt(String s, int radix) // 返回一个整数，指定字符串必须表示一个指定进制整数
        System.out.println(Integer.parseInt("1111101001", 2)); // 1001
        // static Integer valueOf(String s)
        // static Integer valueOf(String s, int radix) // 返回一个新的Integer对象，初始化为一个整数，值包含在字符串中，可以指定进制（radix参数）
        System.out.println(Integer.valueOf("27"));
    }
}



/**
 * page 225
 * 1.java.lang.reflect包中的Array类允许动态的创建数组。例如，Arrays类的CopyOf方法实现中就使用了这个类。
 * 2.对象数组不能强制转换成员工数组（Employee[]），如果这样做，Java虚拟机会再运行时生成一个ClassCastException异常
 * 3.Java数组会记住每个元素的类型，即创建数组时new表达式中使用的元素类型，将一个Employee[]临时转换成Object[]数组，然后再把它转换回来时可以的，
 *  打包一个从开始就是Object[]的数组永远也不能换换成Employee[]数组
 * 4.java.lang.Class
 *  boolean isArray() 如果这个对象标识一个数组类型，则返回true
 *  Class<?> getComponentType()
 *  Class<?> componentType()
 *  如果这个对象标识一个数组类型，则返回描述元素类型的Class，否则返回null
 *  Class<?> arrayType() 返回描述数组类型的Class
 * 5.java.lang.reflect.Array
 *  static Object get(Object array, int index)
 *  static xxx getXxx(Object array, int index)
 *  (xxx是boolean byte char double float int long short中的一种)这些方法将返回给定数组中春初在给定缩影位置上的值
 *  static void set(Object array, int index, Object newValue)
 *  static void setXxx(Object array, int index, xxx newValue)
 *  (xxx是boolean byte char double float int long short中的一种)这些方法将一个新值存储到给定数组中的给定索引位置上
 *  static int getLength(Object array) 返回给定数组的长度
 *  static Object newInstance(Class componentType, int length)
 *  static Object newInstance(Class componentType, int[] lengths)
 *  返回一个又给定元素类型、给定大小的新数组
 */
package page225ReflectCopyArray;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Demo {
    public static void main(String[] args) {
        int[] a = { 1, 2, 3 };
        a = (int[]) goodCopyOf(a, 10);
        System.out.println(Arrays.toString(a));

        String[] b = { "Tom", "Dick", "Harry" };
        b = (String[]) goodCopyOf(b, 10);
        System.out.println(Arrays.toString(b));
        // 2.对象数组不能强制转换成员工数组（Employee[]），如果这样做，Java虚拟机会再运行时生成一个ClassCastException异常
        // 以下代码报错 Exception in thread "main" java.lang.ClassCastException: class [Ljava.lang.Object; cannot be cast to class [Ljava.lang.String; ([Ljava.lang.Object; and [Ljava.lang.String; are in module java.base of loader 'bootstrap')
        // b = (String[]) badCopyOf(b, 10);

        Employee[] empList = { new Employee() };
        Object[] objList = (Object[]) empList;
        Employee[] empList2 = (Employee[]) objList;

        // Exception in thread "main" java.lang.ClassCastException: class [Ljava.lang.Object; cannot be cast to class [Lpage225ReflectCopyArray.Employee; ([Ljava.lang.Object; is in module java.base of loader 'bootstrap'; [Lpage225ReflectCopyArray.Employee; is in unnamed module of loader 'app')
        Object[] objList2 = { "Tom", "Harry" };
         Employee[] empList3 = (Employee[]) objList2;
    }

    public static Object[] badCopyOf(Object[] arr, int newLength) {
        var newArray = new Object[arr.length];
        System.arraycopy(arr, 0, newArray, 0, Math.min(arr.length, newLength));
        return newArray;
    }

    public static Object goodCopyOf(Object a, int newLength) {
        Class cl = a.getClass();
        if (!cl.isArray()) return null;
        Class componentType = cl.getComponentType();
        int length = Array.getLength(a);
        Object newArray = Array.newInstance(componentType, newLength);
        System.arraycopy(a, 0, newArray, 0, Math.min(length, newLength));
        return newArray;
    }
}

class Employee {}

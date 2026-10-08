/**
 * page 339
 * 8.2 定义简单的泛型类
 *  泛型类就是有一个或多个类型变量的类。
 *  Pair类引入一个类型变量T，用尖括号<>括起来，放在类名的后面。泛型类可以有多个类型变量。
 *  类型变量在整个类定义中用于指定方法的返回类型以及字段和局部变量的类型。
 *
 * 8.3 泛型方法
 *  类型变量放在修饰符的后面并在返回类型的前面
 *
 */
package page339DefineSimpleGenericity;

public class Demo {
    public static void main(String[] args) {
        String[] words = { "Mary", "had", "a", "little", "lamb" };
        Pair<String> mm = ArrayAlg.minmax(words);
        System.out.println("first:" + mm.getFirst());
        System.out.println("second:" + mm.getSecond());
    }
}

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

class ArrayAlg {
    public static Pair<String> minmax(String[] strList) {
        if (strList == null || strList.length == 0) {
            return null;
        }
        String min = strList[0];
        String max = strList[0];
        for (int i = 1; i < strList.length; i++) {
            String cValue = strList[i];
            if (cValue.length() < min.length()) {
                min = cValue;
            }
            if (cValue.length() > max.length()) {
                max = cValue;
            }
        }
        return new Pair<>(min, max);
    }

    public static <T> T getMiddle(T... a) {
        return a[a.length / 2];
    }
}
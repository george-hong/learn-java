/**
 * page 342
 * 8.4 类型变量的限定
 *  1.<T extends BoundingType>表示T应该是限定类型 的子类型。T和限定类型可以是类，也可以是接口。
 *  2.一个类型变量或通配符可以有多个限定，例如：
 *      T extends Comparable & Serializable
 *      限定类型用 & 分隔，而逗号用来分隔类型变量
 *      按照Java继承机制，可以根据需要拥有多个接口超类型，但最多有一个限定可以是类。
 *      如果有一个类作为限定，他必须是限定列表中的第一个限定
 */
package page343TypeVariableLimit;

public class Demo {

}

class ArrayAlg {
    public static void main(String[] args) {
        System.out.println(min(new Integer[] { 10, 20, 30, 40, 5 }));
        System.out.println(min(new String[] { "b", "a", "c", "ab", "cde" }));
    }

    public static <T extends Comparable> T min(T[] a) {
        if (a == null || a.length ==0) {
            return null;
        }
        T smallest = a[0];
        for (int i = 1; i < a.length; i++) {
            if (smallest.compareTo(a[i]) > 0) {
                smallest = a[i];
            }
        }
        return smallest;
    }
}
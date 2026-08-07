/**
 * page 202
 * 1.可以为枚举类型增加构造器、方法和字段，构造器只是在构造枚举常量的时候调用
 * 2.每个枚举类型都有一个金泰的values方法，它将返回一个包含全部枚举值的数组
 * 3.ordinal方法返回一个枚举常量在enum声明中的位置，从0开始计数
 * 4.所有的枚举类型都是抽象类Enum的子类，toString是其中最有用的方法，会返回枚举常量名；toString的逆方法是Enum.valueOf
 */
package page202Enum;

public class Demo {
    public static void main(String[] args) {
        System.out.println(Color.RED.getValue()); // red
        System.out.println(Color.GREEN.getValue()); // green

        Size[] sizes = Size.values();
        Color[] colors = Color.values();
        for(Size size : sizes) {
            System.out.println(size);
        }

        // 3.ordinal方法返回一个枚举常量在enum声明中的位置，从0开始计数
        System.out.println(Size.SMALL.ordinal()); // 0
        System.out.println(Size.MEDIUM.ordinal()); // 1
        System.out.println(Size.LARGE.ordinal()); // 2
        System.out.println(Color.RED.ordinal()); // 0

        // 4.所有的枚举类型都是抽象类Enum的子类，toString是其中最有用的方法，会返回枚举常量名；toString的逆方法是Enum.valueOf
        System.out.println(Color.GREEN.toString()); // GREEN
        System.out.println(Color.YELLOW == Enum.valueOf(Color.class, "YELLOW")); // true
    }
}

// 以下是一个普通的枚举定义，实际上这个声明定义的类型是一个类，他刚好有三个实例，不能构造新的对象
// 因此，在比较枚举类型的值是，并不需要使用equals，可以直接使用==来比较
enum Size {
    SMALL,
    MEDIUM,
    LARGE,
}

// 1.可以为枚举类型增加构造器、方法和字段，构造器只是在构造枚举常量的时候调用
enum Color {
    RED("red"),
    GREEN("green"),
    YELLOW("yellow");

    private String value;
    Color(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
/**
 * 如果在构造器中没有显式地为字段设置初始值，它就会自动设置为默认值
 * char 为 \u0000
 * 数值设置为 0
 * 小数为 0.0
 * 布尔值为 false
 * 对象引用为 null
  */
public class DefaultField {
    public static void main (String[] args) {
        Test test = new Test();

        System.out.println("char:" + test.aChar);
        System.out.println("byte:" + test.aByte);
        System.out.println("short:" + test.aShort);
        System.out.println("long:" + test.aLong);
        System.out.println("int:" + test.aInt);
        System.out.println("float:" + test.aFloat);
        System.out.println("double:" + test.aDouble);
        System.out.println("boolean:" + test.aBoolean);
        System.out.println("String:" + test.aString);
    }
}

class Test {
    char aChar;
    byte aByte;
    short aShort;
    long aLong;
    int aInt;
    float aFloat;
    double aDouble;
    boolean aBoolean;
    String aString;
}

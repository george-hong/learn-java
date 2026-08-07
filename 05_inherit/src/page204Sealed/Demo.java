/**
 * page 204
 * 1.密封类会控制哪些类可以继承它，Java15中作为预览特性，Java17中最终确定了这个特性
 * 2.一个密封类允许的子类必须是可访问的，他们不能是嵌套在另一个类中的私有类，也不能是位于另一个包中的包可见类
 * 3.对于允许的公共子类，他们必须与密封类在同一个包中，如果使用模块则必须在同一个模块中
 * 4.声明密封类可以不加permits字句，这样依赖，他的所有直接子类都必须在同一个文件中声明。
 * 5.密封类的子类必须指定他是sealed、final还是允许继续派生子类，对于云讯继续派生子类的情况，必须声明为non-sealed
 */
package page204Sealed;

public class Demo {
    public static void main(String[] args) {

    }
}

sealed class JSONValue permits JSONNumber, JSONString, JSONBoolean, JSONObject, JSONNull {
    public String toString() {
        return "JSONValue";
    }
}

final class JSONNumber extends JSONValue {}
final class JSONString extends JSONValue {}
final class JSONBoolean extends JSONValue {}
final class JSONObject extends JSONValue {}
final class JSONNull extends JSONValue {}
// 以下代码会报错'page204Sealed.JSONArray' is not allowed in the sealed hierarchy，因为JSONArray不在JSONValue的可拓展列表中
// final class JSONArray extends JSONValue {}

// 3.对于允许的公共子类，他们必须与密封类在同一个包中，如果使用模块则必须在同一个模块中
sealed class CrossFile permits CrossFileNumber, CrossFileString {}

// 4.声明密封类可以不加permits字句，这样依赖，他的所有直接子类都必须在同一个文件中声明。
sealed class SimpleValue {}

final class SimpleNumber extends SimpleValue {}

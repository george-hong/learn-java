/**
 * page 211
 * 1.如果一个方法包含一条可能抛出检查性异常的语句，则在方法名上增加一个throws字句
 * 2.调用这个方法的任何方法也都需要一个throws声明，这也包括main方法，如果一个异常确实出现，则main方法将终止并提供一个栈轨迹。
 */
package page211ThrowError;

public class Demo {
    // 2.调用这个方法的任何方法也都需要一个throws声明，这也包括main方法，如果一个异常确实出现，则main方法将终止并提供一个栈轨迹。
    public static void main(String[] args) throws ClassNotFoundException {
        MightError.getSomeClass();
    }
}

class MightError {
    // 1.如果一个方法包含一条可能抛出检查性异常的语句，则在方法名上增加一个throws字句
    static Class getSomeClass () throws ClassNotFoundException {
        return Class.forName("java.util.Random");
    }
}

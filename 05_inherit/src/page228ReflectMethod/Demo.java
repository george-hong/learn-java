/**
 * page 228
 * 表面上看Java没有提供途径将一个方法的存储地址传给另外一个方法，以便第二个方法以后调用。
 * 1.Method类有一个invoke方法，允许你调用包装在当前Method对象中的方法，invoke方法的签名为Object invoke(Object obj, Object... args)
 *  第一个参数是隐式参数this，其他是显示参数；对于静态方法第一个参数会忽略，即可以将它设置为null
 * 2.java.lang.reflect.Method
 *  public Object invoke*(Object implicitParameter, Object[] explicitParameters)
 *  调用这个对象描述的方法，传入给定参数，并返回哪个方法的返回值。对于静态方法，传入null作为隐式参数。
 */
package page228ReflectMethod;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Demo {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Employee emp = new Employee("Hack");
        Class empClass = emp.getClass();
        // 1.Method类有一个invoke方法，允许你调用包装在当前Method对象中的方法，invoke方法的签名为Object invoke(Object obj, Object... args)
        //  第一个参数是隐式参数this，其他是显示参数；对于静态方法第一个参数会忽略，即可以将它设置为null
        Method getDescMethod = empClass.getDeclaredMethod("getDesc", String.class);
        System.out.println(getDescMethod.invoke(emp, "come on"));
        Method getStaticMethod = empClass.getDeclaredMethod("staticMethod");
        getStaticMethod.invoke(null);
    }
}

class Employee {
    private String name;

    Employee(String name) {
        this.name = name;
    }

    static public void staticMethod() {
        System.out.println("run static method");
    }

    public String getDesc(String otherThing) {
        return this.name + " say " + otherThing;
    }
}


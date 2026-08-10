/**
 * page 213
 * 1.java.lang.reflect包中有三个类Field、Method、Constructor，分别用于描述类的字段、方法和构造器
 *  这三个雷都有getName方法，用于返回字段、方法和构造器的名字，Field类有一个getType方法，返回描述字段类型的一个对象。Metho和Constructor
 *  类有报告参数类型的方法，Method类还有一个报告返回类型的方法。
 * 2.Class类中的getFields、getMethods、getConstructors方法会分别返回这个类支持的公共字段、方法和构造器数组，其中包含超累的公共成员。
 *  Class类的getDeclaredFields、getDeclaredMethods、getDeclaredConstructors方法将分别返回这个类中声明的全部字段、方法和构造器组成的数组，
 *  其中包括私有成员、包成员和受保护成员以及有包访问权限的成员，但不包括超类的成员。
 */
package page213Reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

public class Demo {
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println("请输入要查看的类，例如java.util.Random");
        Scanner sc = new Scanner(System.in);
        String className = sc.next();
        Class cl = Class.forName(className);
        String classDesc = ReflectTest.getClassDescription(cl);
        System.out.println(classDesc);
    }
}

class ReflectTest {
    static String getClassDescription(Class cl) {
        String r = "";
        int modifiers = cl.getModifiers();
        String modifiersString = Modifier.toString(modifiers);
        r += modifiersString;
        // 类名前缀
        if (modifiersString.length() > 0) {
            r += " ";
        }
        if (cl.isSealed()) r += "Sealed ";
        if (cl.isEnum()) r += "enum ";
        else if (cl.isRecord()) r += "Record ";
        else if (cl.isInterface()) r += "interface ";
        else r += "class ";
        r += cl.getName();
        // 继承部分
        Class superclass = cl.getSuperclass();
        if (superclass != null && superclass != Object.class) {
            r += " extends " + superclass.getName();
        }
        // 接口部分
        r += getInterfaces(cl);
        // 密封类部分
        r += getPermittedSubClasses(cl);
        r += " {";

        // 字段部分
        r += getFields(cl);
        // 构造器部分
        r += getConstructors(cl);
        // 方法部分
        r += getMethods(cl);

        r += System.lineSeparator() + "}";
        return r;
    }

    private static String getInterfaces(Class cl) {
        String r = "";
        Class<?>[] interfaces = cl.getInterfaces();
        for (int i = 0; i < interfaces.length; i++) {
            if (i == 0) {
                r += cl.isInterface() ? " extends  " : " implements ";
            } else {
                r += ", ";
            }
            r += interfaces[i].getName();
        }
        return r;
    }

    private static String getPermittedSubClasses(Class cl) {
        String r = "";
        if (cl.isSealed()) {
            Class<?>[] permittedSubClasses = cl.getPermittedSubclasses();
            for (int i = 0; i < permittedSubClasses.length; i++) {
                if (i == 0) {
                    r += " permits ";
                } else {
                    r += ", ";
                }
                r += permittedSubClasses[i].getName();
            }
        }
        return r;
    }

    private static String getFields(Class cl) {
        String r = "";
        Field[] fields = cl.getDeclaredFields();
        for (Field field : fields) {
            Class type = field.getType();
            String name = field.getName();
            String modifiers = Modifier.toString(field.getModifiers());
            String modifiersString = modifiers.length() > 0 ? modifiers + " " : "";
            r += System.lineSeparator() + "    " + modifiersString + type.getName() + " " + name + ";";
        }
        return r;
    }

    private static String getConstructors(Class cl) {
        String r = "";
        Constructor[] constructors = cl.getDeclaredConstructors();
        for (Constructor constructor : constructors) {
            String name = constructor.getName();
            String modifiers = Modifier.toString(constructor.getModifiers());
            String modifiersString = modifiers.length() > 0 ? modifiers + " " : "";
            r += System.lineSeparator() + "    " + modifiersString + name + "(";
            // 输入参数部分
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            for (int j = 0; j < parameterTypes.length; j++) {
                if (j != 0) r += ", ";
                r += parameterTypes[j].getName();
            }
            r += ");";
        }
        return r;
    }

    private static String getMethods(Class cl) {
        String r = "";
        Method[] methods = cl.getMethods();
        for (Method method : methods) {
            String name = method.getName();
            String returnType = method.getReturnType().getName();
            String modifiers = Modifier.toString(method.getModifiers());
            String modifiersString = modifiers.length() > 0 ? modifiers + " " : "";
            r += System.lineSeparator() + "    " + modifiersString + returnType + " " + name + "(";
            // 输入参数部分
            Class<?>[] parameterTypes = method.getParameterTypes();
            for (int j = 0; j < parameterTypes.length; j++) {
                if (j != 0) r += ", ";
                r += parameterTypes[j].getName();
            }
            r += ");";
        }
        return r;
    }
}

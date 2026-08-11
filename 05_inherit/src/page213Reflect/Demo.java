/**
 * page 213
 * 1.java.lang.reflect包中有三个类Field、Method、Constructor，分别用于描述类的字段、方法和构造器
 *  这三个雷都有getName方法，用于返回字段、方法和构造器的名字，Field类有一个getType方法，返回描述字段类型的一个对象。Metho和Constructor
 *  类有报告参数类型的方法，Method类还有一个报告返回类型的方法。
 * 2.Class类中的getFields、getMethods、getConstructors方法会分别返回这个类支持的公共字段、方法和构造器数组，其中包含超累的公共成员。
 *  Class类的getDeclaredFields、getDeclaredMethods、getDeclaredConstructors方法将分别返回这个类中声明的全部字段、方法和构造器组成的数组，
 *  其中包括私有成员、包成员和受保护成员以及有包访问权限的成员，但不包括超类的成员。
 * 3.java.lang.Class
 *  Field[] getFields()
 *  Field[] getDeclaredFields()
 *  getFields方法返回一个包含Field对象的数组，这些对象对应这个类或其超类的公共字段。getDeclaredFields方法也返回一个包含Field对象的数组，
 *  这些对象对应这个类的全部字段。如果类中没有这样的字段，哲哲Class对象标识基本类型或数组类型，则这些方法返回长度为0的数组。
 *  Method[] getMethods()
 *  Method[] getDeclaredMethods()
 *  返回包含Method对象的数组，包含方法的信息
 *  Constructor[] getConstrcutors()
 *  Constructor[] getDeclaredConstructors()
 *  返回包含Constructor对象的数组，包含构造器信息
 *  boolean isInterface() 如果这个Class对象描述一个interface，则返回true
 *  boolean isEnum() 如果这个Class对象描述一个enum，则返回true
 *  boolean isRecord() 如果这个Class对象描述一个record，则返回true
 *  RecordComponent[] getRecordComponents()
 *  返回一个包含RecordComponent对象的数组，这些对象描述了记录字段，或者这个类不是一个记录，则返回null
 *  String getPackageName()
 *  得到包含这类型的包的包名，如果这个类型是一个数组类型，则返回元素类型所属的包，如果这个类型是一个基本类型，则返回java.lang
 * 4.java.lang.reflect.Field   java.lang.reflect.Method    java.lang.reflect.Constructor
 *  Class getDeclaringClass()
 *  返回一个Class对象，表示定义了这个构造器、方法或字段的类
 *  Class[]getExceptionTypes() -- in Constructor and Method Classes
 *  返回一个对象数组，其中各个对象标识这个方法所爆出异常的类型
 *  int getModifiers()
 *  返回一个整数，描述这个构造器、方法或字段名的修饰符。使用Modifier.toString方法来分析这个返回值
 *  String getName()
 *  返回一个标识构造器、方法、字段名的字符串
 *  Class[] getParameterTypes() -- in Constructor and Method Classes
 *  返回一个Class对象数组，其中各个对象标识参数的类型
 *  Class getReturnTypes() -- in Constructor and Method Classes
 *  返回一个标识返回类型的Class对象
 * 5.java.lang.reflect.RecordComponent
 *  String getName()
 *  Class<?> getType() 获得这个记录组件的名字和类型
 *  Method getAccessor()
 *  返回Method对象来访问这个记录组件
 * 6.java.lang.reflect.Modifier
 *  static String toString(int modifiers)
 *  返回一个字符串，包含modifiers中设置的二进制位所对应的修饰符
 *  static boolean isAbstract(int modifiers)
 *  static boolean isFinal(int modifiers)
 *  static boolean isInterface(int modifiers)
 *  static boolean isNative(int modifiers)
 *  static boolean isPrivate(int modifiers)
 *  static boolean isProtected(int modifiers)
 *  static boolean isPublic(int modifiers)
 *  static boolean isStrict(int modifiers)
 *  static boolean isSynchronized(int modifiers)
 *  static boolean isVolatile(int modifiers)
 *  这些方法检测modifiers值中与方法名中修饰符对应的二进制位
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

/**
 * 8.9 反射和泛型
 *
 * java.lang.Class<T>
 *     T newInstance()
 *     返回无参数构造器构造的一个新实例
 *     T cast(Object obj)
 *     如果obj为null或者可以转换成类型T，则返回obj；否则抛出一个BasCastException异常
 *     T[] getEnumConstants()
 *     如果T是枚举类型，则返回所有值组成的一个数组，否则返回null
 *     Class<? super T> getSupperClass()
 *     返回这个类的超类。如果T不是一个类或者T是Object类，则返回null
 *     Constructor<T> getConstructor(Class... parameterTypes)
 *     Constructor<T> getDeclaredConstructor(Class... parameterTypes)
 *     获得公共构造器，或者有给定参数类型的构造器
 *
 * java.lang.reflect.Constructor<T>
 *     T newInstance(Object... parameters)
 *     返回用指定参数构造的新实例
 *
 * 8.9.2 使用Class<T>参数进行类型匹配
 *
 * 8.9.3 虚拟机中的泛型类型信息
 *
 *
 *
 */
package page366WildTypeAndReflect;

import common.Pair;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        try {
            // 8.9.2 使用Class<T>参数进行类型匹配
            // Empty.class是一个Class<Empty>类型的对象。makerPair方法的类型参数T与Empty匹配，编译器可以推断出这个方法将返回一个Pair<Empty>
            Pair<Empty> ePair = makePair(Empty.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <T> Pair<T> makePair(Class<T> c) throws InstantiationException, IllegalAccessException {
        return new Pair<>(c.newInstance(), c.newInstance());
    }
}

class Empty {}

class GenericReflectionTest {
    public static void main(String[] args) {
        String name;
        if (args.length > 0) name = args[0];
        else {
            try (var in = new Scanner(System.in)) {
                System.out.println("Enter class name (e.g. java.util.Collections):");
                name = in.next();
            }
        }

        try {
            Class<?> cl = Class.forName(name);
            printClass(cl);
            for (Method m : cl.getDeclaredMethods()) {
                printMethod(m);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void printClass(Class<?> cl) {
        System.out.print(cl);
        printTypes(cl.getTypeParameters(), "<", ", ", ">", true);
        Type sc = cl.getGenericSuperclass();
        if (sc != null) {
            System.out.print(" extends ");
            printType(sc, false);
        }
        printTypes(cl.getGenericInterfaces(), " implements ", ", ", "", false);
        System.out.println();
    }

    public static void printMethod(Method m) {
        String name = m.getName();
        System.out.print(Modifier.toString(m.getModifiers()));
        System.out.print(" ");
        printTypes(m.getTypeParameters(), "<", ", ", ">", true);

        printType(m.getGenericReturnType(), false);
        System.out.print(" ");
        System.out.print(name);
        System.out.print("(");
        printTypes(m.getGenericParameterTypes(), "", ", ", "", false);
        System.out.println(")");
    }

    public static void printTypes(Type[] types, String pre, String sep, String suf, boolean isDefinition) {
        if (pre.equals(" extends ") && Arrays.equals(types, new Type[] { Object.class })) {
            return;
        }
        if (types.length > 0) System.out.print(pre);
        for (int i = 0; i < types.length; i++) {
            if (i > 0) System.out.print(sep);
            printType(types[i], isDefinition);
        }
        if (types.length > 0) System.out.print(suf);
    }

    public static void printType(Type type, boolean isDefinition) {
        if (type instanceof Class t) {
            System.out.print(t.getName());
        } else if (type instanceof TypeVariable t) {
            System.out.print(t.getName());
            if (isDefinition) printTypes(t.getBounds(), " extends ", " & ", "", false);
        } else if (type instanceof WildcardType t) {
            System.out.print("?");
            printTypes(t.getUpperBounds(), " extends ", " & ", "", false);
            printTypes(t.getLowerBounds(), " super ", " & ", "", false );
        } else if (type instanceof ParameterizedType t) {
            Type owner = t.getOwnerType();
            if (owner != null) {
                printType(owner, false);
                System.out.print(".");
            }
            printType(t.getRawType(), false);
            printTypes(t.getActualTypeArguments(), "<", ",", ">", false);
        } else if (type instanceof GenericArrayType t) {
            System.out.print("");
            printType(t.getGenericComponentType(), isDefinition);
            System.out.print("[]");
        }
    }
}
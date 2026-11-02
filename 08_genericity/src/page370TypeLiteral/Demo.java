/**
 * 8.9.4 类型字面量
 *
 * java.lang.Class<T>
 *  TypeVariable[] getTypeParameters()
 *  如果这个类型声明为泛型类型，则或者泛型类型变量，否则获得一个长度为0的数组
 *  Type getGenericSuperclass()
 *  获得这个类型所声明超类的泛型类型，如果这个类型是Object或者不是类类型，则返回null
 *  Type[] getGenericInterfaces()
 *  获得这个类型所声明接口的泛型类型（按照声明次序），否则，如果这个类型没有实现接口，则返回长度为0的数组
 *
 * java.lang.reflect.Method
 *  TypeVariable[] getTypeParameters()
 *  如果这个方法声明为一个泛型方法，则获得泛型类型变量，负责返回长度为0的数组
 *  Type getGenericReturnType()
 *  获得这个方法声明的泛型返回类型
 *  Type[] getGenericParameterTypes()
 *  获得这个方法声明的泛型类型参数。如果这个方法没有参数，则返回长度为0的数组
 *
 * java.lang.reflect.TypeVariable
 *  String getName()
 *  获得这个类型变量的名字
 *  Type[] getBounds()
 *  获得这个类型变量的子类限定，否则如果该变量无限定，则返回长度为0的数组
 *
 * java.lang.reflect.WildcardType
 *  Type[] getUpperBounds()
 *  获得这个类型变量的子类(extends)限定，否则如果该变量没有子类限定，则返回长度为0的数组
 *  Type[] getLowerBounds()
 *  获得这个类型变量的超类(super)限定，否则如果该变量没有超类限定，则返回长度为0的数组
 *
 * java.lang.reflect.ParameterizedType
 *  Type getRawType()
 *  获得这个参数化类型的原始类型
 *  Type[] getActualTypeArguments()
 *  获得这个参数化类型声明的类型参数
 *  Type getOwnerType()
 *  如果是内部类型，则返回其外部类类型，如果这是一个顶级类型，则返回null
 *
 * java.lang.reflect.GenericArrayType
 *  Type getGenericComponentType()
 *  获得这个数组类型声明的泛型元素类型
 */
package page370TypeLiteral;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Demo {
    public static void main(String[] args) {
        var type = new TypeLiteral<ArrayList<Integer>>() {};
    }
}

class TypeLiteral<T> {
    private Type type;

    public TypeLiteral() {
        Type parentType = getClass().getGenericSuperclass();
        if (parentType instanceof ParameterizedType paramType) {
            type = paramType.getActualTypeArguments()[0];
        } else {
            throw new UnsupportedOperationException("Construct as new TypeLiteral<...>(){}");
        }
    }

    private TypeLiteral(Type type) {
        this.type = type;
    }

    public static TypeLiteral<?> of(Type type) {
        return new TypeLiteral<Object>(type);
    }

    public String toString() {
        if (type instanceof Class clazz) {
            return clazz.getName();
        } else {
            return type.toString();
        }
    }

    public boolean equals(Object otherObject) {
        return otherObject instanceof TypeLiteral otherLiteral && type.equals(otherLiteral.type);
    }

    public int hashCode() {
        return type.hashCode();
    }
}

class Formatter {
    private Map<TypeLiteral<?>, Function<?, String>> rules = new HashMap<>();

    public <T> void forType(TypeLiteral<T> type, Function<T, String> formatterForType) {
        rules.put(type, formatterForType);
    }

    public String formatFields(Object obj) throws IllegalArgumentException, IllegalAccessException {
        var result = new StringBuilder();
        for (Field f : obj.getClass().getDeclaredFields()) {
            result.append(f.getName());
            result.append("=");
            f.setAccessible(true);
            Function<?, String> formatterForType = rules.get(TypeLiteral.of(f.getGenericType()));
            if (formatterForType != null) {
                @SuppressWarnings("unchecked")
                Function<Object, String> objectFormatter = (Function<Object, String>) formatterForType;
                result.append(objectFormatter.apply(f.get(obj)));
            } else {
                result.append(f.get(obj).toString());

            }
            result.append("\n");
        }
        return result.toString();
    }
}

class TypeLiterals {
    public static class Sample {
        ArrayList<Integer> nums;
        ArrayList<Character> chars;
        ArrayList<String> strings;

        public Sample() {
            nums = new ArrayList<>();
            nums.add(42);
            nums.add(1729);
            chars = new ArrayList<>();
            chars.add('H');
            chars.add('i');
            strings = new ArrayList<>();
            strings.add("Hello");
            strings.add("World");
        }
    }

    private static <T> String join(String separator, ArrayList<T> elements) {
        var result = new StringBuilder();
        for (T e : elements) {
            if (result.length() > 0) {
                result.append(separator);
            }
            result.append(e.toString());
        }
        return result.toString();
    }

    public static void main(String[] args) throws Exception {
        var formatter = new Formatter();
        formatter.forType(new TypeLiteral<ArrayList<Integer>>() {}, lst -> join(" ", lst));
        formatter.forType(new TypeLiteral<ArrayList<Character>>() {}, lst -> "\"" + join("", lst) + "\"");
        System.out.println(formatter.formatFields(new Sample()));
    }
}



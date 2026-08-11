/**
 * page 220
 * 1.java.lang.reflect.AccessibleObject
 *  void setAccessible(boolean flag) 设置或取消这个课访问对象的可访问标志，如果拒绝访问则抛出一个IllegalAccessException异常
 *  boolean trySetAccessible() 为这个可访问对象设置可访问标志，如果拒绝访问则返回false
 *  boolean canAccess(Object obj) 检查调用者是否可以他通过这个字段、方法构造器对象访问obj，对于静态字段或方法传入null，构造器也要传入null
 *  static void setAccessible(AccessibleObject[] array, boolean flag) 设置一个对象数组的可访问标志
 * 2.java.lang.Class
 *  Field getField(String name)
 *  Field[] getFields()
 *  得到指定的公共字段或字段数组
 *  Field getDeclaredField(String name)
 *  Field[] getDeclaredFields()
 *  得到类声明中的字段或字段数组
 * 3.java.lang.reflect.Field
 *  Object get(Object obj) 返回obj对象中用这个Field对象描述的字段的值
 *  void set(Object obj, Object newValue) 将obj对象中这个Field对象描述的字段设置为一个新值
 */
package page220ReflectAnalysisObject;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class ObjectAnalyzer {
    private ArrayList<Object> visited = new ArrayList<>();

    public String toString(Object obj) throws ReflectiveOperationException {
        if (obj == null) return "null";
        if (visited.contains(obj)) return "...";
        visited.add(obj);
        Class cl = obj.getClass();
        if (cl == String.class) return (String) obj;
        if (cl.isArray()) {
            String r = cl.getComponentType() + "[]{";
            for (int i = 0; i < Array.getLength(obj); i++) {
                if (i > 0) r += ",";
                Object val = Array.get(obj, i);
                if (cl.getComponentType().isPrimitive()) r += val;
                else r += toString(val);
            }
            return r += "}";
        }

        String r = cl.getName();
        do {
            r += "[";
            Field[] fields = cl.getDeclaredFields();
            AccessibleObject.setAccessible(fields, true);
            for (Field field : fields) {
                if (!Modifier.isStatic(field.getModifiers())) {
                    if (!r.endsWith("[")) r += ",";
                    r += field.getName() + "=";
                    Class t = field.getType();
                    Object val = field.get(obj);
                    if (t.isPrimitive()) r += val;
                    else r += toString(val);
                }
            }
            r += "]";
            cl = cl.getSuperclass();
        } while (cl != null);
        return r;
    }
}

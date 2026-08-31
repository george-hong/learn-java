/**
 * page 285
 * 6.5.3 代理类的特性
 *  所有的代理类都拓展Proxy雷，一个代理类只有一个实例字段--即调用处理器，它在Proxy超类中定义
 *  要调用一个目标代理的默认方法会触发调用处理器。要具体调用这个方法，可以使用InvocationHandler接口的静态方法InvokeDefault。示例如下：
 *      InvocationHandler handler = (proxy, method, args) -> {
 *          if (method.isDefault()) {
 *              return InvocationHandler.invokeDefault(proxy, method, args);
 *          } else {
 *              return method.invoke(target, args);
 *          }
 *      }
 * java.lang.reflect.InvocationHandler
 *  - Object invoke(Object proxy, Method method, Object[] args)
 *      定义这个方法包含一个动作，你希望只要在代理对象上调用一个方法就完成这个动作
 *  - static Object invokeDefault(Object proxy, Method method, Object... args)
 *      绕过调用处理器，用给定参数调用代理实例的一个默认方法
 * java.lang.reflect.Proxy
 *  - static Class<?> getProxyClass(ClassLoader loader, Class<?>... interfaces)
 *      返回实现指定接口的代理类
 *  - static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler handler)
 *      构造实现指定接口的代理类的一个新实例。所有方法都调用给定处理器对象的invoke方法
 *  - static boolean isProxyClass(Class<?> cl)
 *      如果cl是一个代理类则返回true
 */
package page285CreateProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class Demo {
    public static void main(String[] args) {
        var elements = new Object[1000];
        // 填充数组
        for (int i = 0; i < elements.length; i ++) {
            Integer value = i + 1;
            var handler = new TraceHandler(value);
            Object proxy = Proxy.newProxyInstance(
                    ClassLoader.getSystemClassLoader(),
                    new Class[] { Comparable.class },
                    handler
            );
            elements[i] = proxy;
        }
        // 构造一个随机整数
        Integer key = (int) (Math.random() * elements.length) + 1;
        // 查询key
        int result = Arrays.binarySearch(elements, key);
        // 打印找到的内容
        if (result >= 0) {
            System.out.println(elements[result]);
        }
    }
}

class TraceHandler implements InvocationHandler {
    private Object target;

    public TraceHandler(Object target) {
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String content = target + "." + method.getName() + "(";
        if (args != null && args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                if (i != 0) {
                    content += ", ";
                }
                content += args[i];
            }
        }
        content += ")";
        System.out.println(content);
        return method.invoke(target, args);
    }
}
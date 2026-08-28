/**
 * page 285
 *
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
        String content = target + "." + method.getName() + ".(";
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
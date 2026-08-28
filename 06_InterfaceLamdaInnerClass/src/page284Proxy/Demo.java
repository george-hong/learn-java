/**
 * page 284
 * 6.5.1 何时使用代理
 *  假设想构造一个累的对象，这个类实现了一个或多个接口，但是在编译时可能不知道这些接口是什么。
 *  代理类可以在运行时创建全新的类。这样一个代理类能够显示指定的接口，具体的，代理类包含以下方法：
 *      1.指定接口所需要的全部方法
 *      2.Object类中定义的全部方法（toString,equals等）
 *  不过，不能在运行时为这些方法定义新代码。实际上，必须提供一个调用处理器。调用处理器是实现了InvocationHandler接口的类的对象。
 *  这个接口只有一个方法:
 *      Object invoke(Object proxy, Method method, Object[] args)
 *  无论何时调用代理对象的方法，都会调用这个调用处理器的invoke方法，并提供Method对象和原调用的参数。
 *  之后，调用处理器必须确定如何处理这个调用。
 * 6.5.2 创建代理对象
 *  要想创建一个代理对象，需要使用Proxy类的newProxyInstance方法，这个方法一共有三个参数：
 *      - 一个类加载器（class loader）
 *      - 一个Class对象数组，每个元素对应需要实现的各个接口
 *      - 一个调用处理器
 *
 */
package page284Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class Demo {
   public static void main(String[] args) {
       Person person = new Person("Jack");
       IPerson handler = (IPerson) Proxy.newProxyInstance(
               person.getClass().getClassLoader(),
               person.getClass().getInterfaces(),
               new TraceHandler(person)
       );

       handler.getName();
       handler.sayHello("my message");
   }
}

class TraceHandler implements InvocationHandler {
    private Object target;

    public TraceHandler(Object target) {
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String content = "Method name:" + method.getName();
        if (args != null && args.length > 0) {
            content += ", and parameters:" + Arrays.toString(args);
        }
        System.out.println(content);
        return method.invoke(target, args);
    }
}

interface IPerson {
    String getName();
    String sayHello(String message);
}

class Person implements IPerson {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        System.out.println("run get name method...");
        return name;
    }

    public String sayHello(String message) {
        return message;
    }
}
/**
 * page 316
 * 7.5.1 基本日志
 *  1.对于简单的日志记录，可以使用全局日志记录器并调用其info方法
 *  2.如果在适当的地方（如main的最前面）调用Logger.getGlobal().setLevel(Level.OFF)将会抑制所有日志
 *
 * 7.5.2 高级日志
 *  1.可以调用getLogger方法创建或获取一个日志记录器：
 *      private static final Logger myLogger = Logger.getLogger("com.mycompany.myapp");
 *      未被任何变量引用的日志记录器可能会被垃圾回收。为了防止这种情况发生，要像上面一个，用静态变量存储日志记录器的一个引用
 *  2.通常，有以下7个日志级别
 *      SEVERE WARNING INFO CONFIG FINE FINER FINEST
 *      在默认情况下，实际上只记录前三个级别。也可以设置一个不同的级别，例如，
 *      logger.setLevel(LEVEL.FINE)
 *  3.如果将记录级别设置为比INFO更低的级别，还需要修改日志处理器的配置。默认的日志处理器会抑制低于INFO级别的消息。
 *  4.可以使用log方法并指定级别，例如：logger.log(Level.FINE, message)
 *  5.默认的日志记录会显示包含日志调用的类和方法的名字（根据调用栈得出）。不过，如果虚拟机对执行过程进行了优化，就可能得不到准确的调用信息。
 *      此时，可以使用logp方法获取调用类和方法的确切位置，这个方法的前敏为：
 *      void logp(Level l, String className, String methodName, String message)
 */
package page316BaseLog;

import java.util.logging.*;

public class Demo {

}

class BaseLog {
    public static void main(String[] args) {
        // 1.对于简单的日志记录，可以使用全局日志记录器并调用其info方法
        // 打印结果
        // 9月 11, 2026 2:41:15 下午 page316BaseLog.BaseLog main
        // 信息: 打印日志信息
        Logger.getGlobal().info("打印日志信息");
    }
}

class BaseLog2 {
    public static void main(String[] args) {
        // 2.如果在适当的地方（如main的最前面）调用Logger.getGlobal().setLevel(Level.OFF)将会抑制所有日志
        Logger gLogger = Logger.getGlobal();
        gLogger.setLevel(Level.OFF);
        // 以下日志将不会打印
        Logger.getGlobal().info("打印日志信息");
    }
}

class AdvancedLog {
    private static final Level nextLevel = Level.FINE;
    private static final Logger logger = Logger.getLogger("baseLog");
    static {
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new SimpleFormatter());
        // 关闭默认日志处理器
        logger.setUseParentHandlers(false);
        // 添加自定义处理器
        logger.addHandler(handler);
    }
    public static void main(String[] args) {
        logger.info("test info");
        logger.fine("这个fine日志不会输出");
        logger.setLevel(Level.FINE);
        for (Handler handler : logger.getHandlers()) {
            handler.setLevel(nextLevel);
        }
        logger.fine("这个fine日志会输出，因为调整了日志级别");
    }
}

class AdvanceLog2 {
    public static void main(String[] args) {
        // 4.可以使用log方法并指定级别，例如：logger.log(Level.FINE, message)
        Logger logger = Logger.getLogger("customLog");
        logger.log(Level.INFO, "调用log方法输入日志");
    }
}
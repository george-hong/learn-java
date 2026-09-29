/**
 * page 324
 * 7.5.6 过滤器
 *  1.在默认情况下，会根据日志记录的级别进行过滤。每个日志记录器和处理器都可以由一个可选的过滤器来完成额外的过滤。
 *      要定义一个过滤器，需要实现Filter接口并定义以下方法：
 *      boolean isLoggable(LogRecord record)
 *
 * 7.5.7 格式化器
 *  1.ConsoleHandler类和FileHandler类可以生成文本和XML格式的日志记录。不过，也可以自定义格式。
 *      这需要拓展Formatter类并覆盖下面这个方法：
 *      String format(LogRecord record)
 *
 *  java.util.logging.logger
 *      Logger getLogger(String loggerName)
 *      Logger getLogger(String loggerName, String bundleName)
 *      获得给定名字的日志记录器。如果这个日志记录器不存在，就创建一个日志记录器。
 *      void severe(String message)
 *      void warning(String message)
 *      void info(String message)
 *      void config(String message)
 *      void fine(String message)
 *      void finer(String message)
 *      void finest(String message)
 *      记录一个日志记录，包含方法名只是的级别和给定的消息
 *      void entering(String className, String methodName)
 *      void entering(String className, String methodName, Object param)
 *      void entering(String className, String methodName, Object[] param)
 *      void exiting(String className, String methodName)
 *      void exiting(String className, String methodName, Object result)
 *      记录一个日志记录，描述进入/退出一个方法（有给定的参数和返回值）
 *      void throwing(String className, String methodName, Throwable t)
 *      记录一个日志记录，描述抛出了给定的异常对象
 *      void log(Level level, String message)
 *      void log(Level level, String message, Object obj)
 *      void log(Level level, String message, Object[] objs)
 *      void log(Level level, String message, Throwable t)
 *      记录一个有给定级别和消息的日志记录，其中可以包括对象或者一个可抛出对象。要包括对象，消息中必须包含格式化占位符{0}、{1}等
 *      void logp(Level level, String className, String methodName, String message)
 *      void logp(Level level, String className, String methodName, String message, Object obj)
 *      void logp(Level level, String className, String methodName, String message, Object[] objs)
 *      void logp(Level level, String className, String methodName, String message, Throwable t)
 *      记录一个有给定级别、准确的调用者信息和消息的日志记录，其中可以包括对象或一个可抛出对象
 *      void logrb(Level level, String ClassName, String methodName, ResourceBundle bundle, String message, Object... params)
 *      void logrb(Level level, String ClassName, String methodName, ResourceBundle bundle, String message, Throwable t)
 *      记录一个有给定级别、准确调用者信息、资源包和消息的日志记录，其中可以包括对象或一个可抛出对象
 *      Level getLevel()
 *      void setLevel(Level l)
 *      获得和设置这个日志记录器的级别
 *      Logger getParent()
 *      void setParent(Logger l)
 *      获得和设置这个日志记录器的辅日志记录器
 *      Handler[] getHandlers()
 *      获得这个日志记录器的所有处理器
 *      void addHandler(Handler h)
 *      void removeHandler(Handler h)
 *      为这个日志记录器添加或删除一个处理器
 *      boolean getUserParentHandlers()
 *      void setUseParentHandlers(boolean b)
 *      获得和设置“使用辅处理器”属性。如果这个属性是true,日志记录器会讲全部日志记录转发给他的父日志记录器
 *      Filter getFilter()
 *      void setFilter()
 *      获得和设置这个日志记录器的过滤器
 *
 *  java.util.logging.Handler
 *      abstract void publish(LogRecord record)
 *      将日志记录发送到希望的目的地
 *      abstract void flush()
 *      刷新输出所有已缓冲的数据
 *      abstract void close()
 *      刷新输出所有已缓冲的数据，并释放所有相关的资源
 *      Filter getFilter()
 *      void setFilter()
 *      获得和设置这个处理器的过滤器
 *      Formatter getFormater()
 *      void setFormatter(Filter f)
 *      获得和设置这个处理器的格式化器
 *      Level getLevel()
 *      void setLevel(Level l)
 *      获得和设置这个处理器的级别
 *
 *  java.util.logging.ConsoleHandler
 *      ConsoleHandler()
 *      构造一个新的控制台处理器
 *
 *  java.util.logging.FileHandler
 *      FileHandler(String pattern)
 *      FileHandler(String pattern, boolean append)
 *      FileHandler(String pattern, int limit, int count)
 *      FileHandler(String pattern, int limit, int count, boolean append)
 *      FileHandler(String pattern, long limit, int count, boolean append)
 *      构造一个文件处理器。limit是在打开一个新日志文件之前，日志文件可以包含的近似最大字节数。count是循环序列的文件数量。
 *      如果append为true，记录则应该追加一个已存在的日志问价末尾。
 *
 *  java.util.logging.LogRecord
 *      Level.getLevel()
 *      获得这个日志记录的日志级别
 *      String getLoggerName()
 *      获得记录这个日志记录的日志记录器名字
 *      ResourceBundle getResourceBundle()
 *      String getResourceBundleName()
 *      获得用于本地化消息的资源包或资源包名。如果没有提供资源包则返回null
 *      String getMessage()
 *      获得本地化或格式化之前的“原始”消息
 *      Object[] getParameters()
 *      获得参数对象。如果没有提供，则返回null
 *      Throwable getThrown()
 *      获得所抛出的对象。如果没有提供，则返回null
 *      String getSourceClassName()
 *      String getSourceMethodName()
 *      获得记录这个日志记录的代码位置。这个信息有可能是由日志几率代码提供的，也有可能是从运行时栈自动推导得出。如果日志记录代码提供的值有误，
 *      或者运行时代码由于优化而无法推导出确切的位置，这两个方法的返回值就有可能不准确
 *      long getMillis()
 *      获得创建时间
 *      Instant getInstant()
 *      获得创建时间
 *      long getSequenceNumber()
 *      获得这个日志记录的唯一序列号
 *      long getLongThreadID()
 *      获得创建这个日志记录的线程的唯一ID
 *
 *  java.util.logging.logManager
 *      static LogManager()
 *      获得全局LogManager实例
 *      void readConfiguration()
 *      void readConfiguration(InputSteam in)
 *      从系统属性java.util.logging.config.file指定的文件或者给定的输入流读取日志配置
 *      void updateConfiguration(InputSteam in, Function<String, BiFunction<String, String, String>> mapper)
 *      void updateConfiguration(Function<String, BiFunction<String, String, String>> mapper)
 *      将日志配置与系统属性java.util.logging.config.file指定的文件或给定的输入流合并
 *
 *  java.util.logging.Filter
 *      boolean isLoggable(LogRecord record)
 *      如果给定日志记录需要记录，则返回true
 *
 *  java.util.logging.Formatter
 *      abstract String format(LogRecord record)
 *      返回格式化给定日志记录后得到的字符串
 *      String getHead(Handler h)
 *      String getTail(Handler h)
 *      返回应该出现在包含日志记录的文档开头和结尾的字符串。Formatter超累将这些方法定义为只返回空字符串。如果必要，可以覆盖这些方法
 *      String formatMessage(LogRecord record)
 *      返回日志记录的本地化和格式化消息部分
 */
package page324LogRelation;

import java.util.logging.*;

public class Demo {
}

class LogFilter {
    public static Logger logger = Logger.getLogger("LogFilter");
     //  1.在默认情况下，会根据日志记录的级别进行过滤。每个日志记录器和处理器都可以由一个可选的过滤器来完成额外的过滤。
     //      要定义一个过滤器，需要实现Filter接口并定义以下方法：
     //             boolean isLoggable(LogRecord record)
    static class CustomFilter implements Filter {
        @Override
        public boolean isLoggable(LogRecord record) {
            return !record.getMessage().startsWith("不能");
        }
    }
    static {
        logger.setFilter(new CustomFilter());
    }

    public static void main(String[] args) {
        logger.info("不能发送的测试消息");
        logger.info("能发送的消息");
    }
}

class LogFormatter {
    public static Logger logger = Logger.getLogger("LogFormatter");
    //  1.ConsoleHandler类和FileHandler类可以生成文本和XML格式的日志记录。不过，也可以自定义格式。
    //      这需要拓展Formatter类并覆盖下面这个方法：
    //      String format(LogRecord record)
    static class CustomFilter extends Formatter {
        public String format(LogRecord record) {
            System.out.println(record);
            System.out.println(record.getMessage());
            String message = "自定义Formatter添加的消息开始" + System.lineSeparator() + record.getMessage() + System.lineSeparator() + "自定义Formatter添加的消息结束";
            return message;
        }
    }
    static {
        logger.setUseParentHandlers(false);
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new CustomFilter());
        logger.addHandler(consoleHandler);
    }
    public static void main(String[] args) {
        logger.info("测试消息");
    }
}
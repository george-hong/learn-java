/**
 * page 318
 * 7.5.3 修改日志管理器配置
 *  1.可以通过编辑配置文件来修改日志系统的各个属性。默认的配置文件位于：jdk/conf/logging.properties
 *      要想使用另一个配置文件，就要将java.util.logging.config.file属性设置为哪个文件的位置，为此要用以下命令启用应用程序：
 *      java -Djava.util.logging.config.file=configFile MainClass
 *  2.日志管理器在虚拟机启动时初始化，也就是在main方法执行前。如果想要定制日志属性，但是没有用-Djava.util.logging.config.file命令行选项启动，
 *      可以在程序中调用System.setProperty("java.util.logging.conig.file", file)。不过，这样一来，还必须调用
 *      LogManager.getLogManager().readConfiguration()重新初始化日志管理器。
 *      这样就会从java.util.logging.config.file系统属性指定的位置读取一个新配置。然后应用这个映射器来解析新老配置中所有键的值。映射器是一个
 *      Function<String, BiFunction<String, String, String>>。它将心有配置中的建映射到替换函数。每个替换函数接收与键关联的老值和新值（或者，如果没有关联的值则得到null）,
 *      生成一个替换，或者如果要在更新中删除这个键则返回null
 *
 */
package page319LogConfig;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Demo {

}

class LogConfig {
    public static Logger logger = Logger.getLogger("page319LogConfig.LogConfig");
    public static void main(String[] args) {
        System.out.println("test");
        // 如需默认打印以下配置，需要使用配置文件logging.properties，在ide中若想使用，需要将添加VM Options值-Djava.util.logging.config.file=$ProjectFileDir$/07_ExceptionAssertLog/src/page319LogConfig/logging.properties
        logger.log(Level.FINE, "测试FINE日志");
    }
}

class ManualLogConfig {
    static {
        System.setProperty("java.util.logging.config.file", "D:/others/learn-java/07_ExceptionAssertLog/src/page319LogConfig/logging.properties");
        try {
            LogManager.getLogManager().readConfiguration();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Logger logger = Logger.getLogger("page319LogConfig.ManualLogConfig");
    public static void main(String[] args) {

        System.out.println("test");
        // 如需默认打印以下配置，需要使用配置文件logging.properties，在ide中若想使用，需要将添加VM Options值-Djava.util.logging.config.file=$ProjectFileDir$/07_ExceptionAssertLog/src/page319LogConfig/logging.properties
        logger.log(Level.FINE, "测试FINE日志");
    }
}
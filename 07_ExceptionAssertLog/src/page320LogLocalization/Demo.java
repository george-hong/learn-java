/**
 *  page 320
 *  7.5.4 本地化
 *   1.本地化的应用程序包含资源包中的本地特定信息。资源包包括一组映射，分别对应各个本地化环境。
 *      想要为资源包增加映射，需要对应每个本地化环境提供一个文件。英文消息映射位于com/mycompany/logmessage_en.properties文件中，德文消息
 *      映射位于com/mycompany/logmessage_de.properties文件中。（en和de是语言编码）
 *
 *      请求一个日志记录器时，可以指定一个资源包：
 *      Logger logger = Logger.getLogger(loggerName, "com.mycompany.logmessage");
 *      然后，为日志消息指定资源包的键，而不是具体的日志消息字符串：
 *      logger.log(Level.INFO, "readingFile");
 *
 *      通常需要在本地化的消息中包含一些参数，因此，消息可以包括占位符{0}、{1}等。
 *      例如，要想在日志消息中包含文件名，可以如下使用占位符：
 *      Reading file {0}.
 *
 *      然后，通过调用下面的一个方法向占位符传递具体的值：
 *      logger.log(Level.INFO, "readingFile", fileName);
 *
 *      或者，在Java9中，可以在logrb方法中指定资源包对象：
 *      logger.logrb(Level.INFO, bundle, "renamingFile", oldName, newName)
 *
 */
package page320LogLocalization;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Demo {
    static Logger logger = Logger.getLogger("LogLocalization", "page320LogLocalization.logMessage");
    public static void main(String[] args) {
        logger.log(Level.INFO, "readingFile", "测试文件名");
        logger.log(Level.INFO, "readingFileList", new String[]{ "文件A", "文件B" });
        logger.log(Level.INFO, "测试不在配置文件中的键");
        logger.logrb(Level.INFO, "Demo", "main", "page320LogLocalization.logMessage", "readingFile", "测试消息");
    }
}

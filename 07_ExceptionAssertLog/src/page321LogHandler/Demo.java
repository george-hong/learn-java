/**
 * page 321
 * 7.5.5 处理器
 *  1.在默认情况下，日志记录器将记录发送到ConsoleHandler，他会将记录输出到System.err流。与入职记录器一样，处理器也有日志级别。对于一个要记录
 *      的日志记录，他的日志级别必须高于日志记录器和处理器二者的阈值。日志管理器配置文件将默认的控制台处理器的日志级别设置为：
 *      java.util.logging.ConsoleHanlder.level=INFO
 *  2.要想记录FINE级别的日志，就必须修改配置文件中的默认日志记录器级别和处理器级别。或者，还可以绕过配置文件，安装自己的处理器
 *  3.要想将日志记录发送到其他地方，就要添加其他的处理器。日志API为此提供了两个很有用的处理器，一个是FileHandler，另一个是SocketHandler。
 *      可以如下将记录发送到默认文件处理器：
 *      FileHandler fileHandler = new FileHandler();
 *      logger.addHandler(fileHandler);
 *      这些记录被发送到用户主目录的 javan.log中，n是保证文件唯一的一个编号。
 *  4.可以通过拓展Handler类或StreamHandler类自定义处理器
 */
package page321LogHandler;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.Instant;
import java.util.logging.*;

public class Demo {
    public static void main(String[] args) {

    }
}

class LogHandler{
    static Logger logger = Logger.getLogger("LogHandler");
    static {
        // 2.要想记录FINE级别的日志，就必须修改配置文件中的默认日志记录器级别和处理器级别。或者，还可以绕过配置文件，安装自己的处理器
        Level lv = Level.FINE;
        logger.setLevel(lv);
        // 移除默认日志处理器
        logger.setUseParentHandlers(false);
        // 添加用户日志处理器
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(lv);
        logger.addHandler(consoleHandler);
    }
    public static void main(String[] args) {
        logger.fine("测试消息");
    }
}

class FileHandlerDemo {
    static Logger logger = Logger.getLogger("FileHandlerDemo");
    static {
        // 3.要想将日志记录发送到其他地方，就要添加其他的处理器。日志API为此提供了两个很有用的处理器，一个是FileHandler，另一个是SocketHandler。
        logger.setUseParentHandlers(false);
        try {
            FileHandler fileHandler = new FileHandler();
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        logger.info("测试日志内容");
    }
}

// 4.可以通过拓展Handler类或StreamHandler类自定义处理器
class CustomHandler {
    static Logger logger = Logger.getLogger("CustomHandler");
    static JTextArea textArea = new JTextArea();
    static final int maxLines = 200;
    static {
        WindowHandler winHandler = new WindowHandler();

        JFrame frame = new JFrame("日志窗口");
        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);
//        frame.pack();
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        logger.setUseParentHandlers(false);
        logger.setLevel(Level.ALL);
        logger.addHandler(winHandler);
    }
    static class WindowHandler extends Handler {
        public WindowHandler() {
            setFormatter(new SimpleFormatter());
        }
        public void publish(LogRecord record) {
            if (!isLoggable(record)) return;

            String message;
            try {
                message = getFormatter().format(record);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }

            final String text = message;

            SwingUtilities.invokeLater(() -> appendText(text));
        }

        private void appendText(String text) {
            textArea.append(text);
            // 添加换行符
            if (!text.endsWith("\n")) {
                textArea.append(System.lineSeparator());
            }
            // 限制最大行数
            int lineCount = textArea.getLineCount();
            if (lineCount > maxLines) {
                try {
                    int end = textArea.getLineEndOffset(lineCount - maxLines - 1);
                    textArea.replaceRange("", 0, end);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            // 自动滚动到底部
            textArea.setCaretPosition(textArea.getDocument().getLength());
        }

        public void flush() {

        }

        public void close() {

        }
    }

    public static void main(String[] args) {
        Timer timer = new Timer(500, (event) -> {
            Instant time = Instant.ofEpochMilli(event.getWhen());
            logger.info("测试日志文本" + time);
        });

        timer.start();
    }
}
package util;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

/**
 * Logger to write into a file.
 */
public final class Logger {

    private static final Logger LOG = new Logger();
    private final File logFile = new File("log.txt");

    private Logger() {

    }

    /**
     * Returns the instance of Logger.
     * 
     * @return Logger
     */
    public static Logger getLog() {
        return LOG;

    }

    private void createFile() {
        try {
            logFile.createNewFile();

        } catch (IOException e) {
            System.err.println(e);
        }
    }

    /**
     * Writes the given message into the Log file.
     * 
     * @param text
     *            message.
     */
    public void write(final String text) {

        createFile();

        try {

            final PrintWriter write = new PrintWriter(new FileWriter(logFile, true));
            write.println(text);
            write.close();

        } catch (IOException e) {
            System.err.println(e);
        }
    }

    /**
     * Writes the StackTrace into the Log file.
     * 
     * @param elem
     *            Exception's StackTrace
     */
    public void write(final StackTraceElement... elem) {

        createFile();

        try {

            final PrintWriter write = new PrintWriter(new FileWriter(logFile, true));
            for (final StackTraceElement elem2 : elem) {
                write.println(elem2.toString());
            }
            write.close();

        } catch (IOException e) {
            System.err.println(e);
        }
    }
}

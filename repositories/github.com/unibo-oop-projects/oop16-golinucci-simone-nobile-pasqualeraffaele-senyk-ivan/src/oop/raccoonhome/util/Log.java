package oop.raccoonhome.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 
 *
 */
public final class Log {

    private static class LogHolder {
        private static final Log SINGLETON = new Log();
    }

    private Log() {

    }

    /**
     * @return an instance of Log class
     */
    public static Log getIstance() {
        return LogHolder.SINGLETON;
    }

    /**
     * @param message
     *            to write on log file
     */
    public void write(final String message) {
        File logFile = new File("racoon.log");
        try {
            if (!logFile.exists()) {
                logFile.createNewFile();
            }
            PrintWriter write = new PrintWriter(new FileWriter(logFile, true));
            write.println(message);
            write.close();
        } catch (IOException e) {
            System.out.println("COULD NOT LOG!!");
        }
    }
}

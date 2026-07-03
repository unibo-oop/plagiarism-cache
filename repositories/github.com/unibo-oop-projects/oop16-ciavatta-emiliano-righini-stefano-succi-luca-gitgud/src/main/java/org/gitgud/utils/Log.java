package org.gitgud.utils;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * The logger utility of the application.
 */
public final class Log {

    private static final Logger LOGGER = Logger.getLogger("GUI_GUD_LOGGER");
    private static boolean isInitialized;

    private Log() {
    }

    /**
     * @return the only instance of the logger
     */
    public static Logger getLogger() {
        if (!isInitialized) {
            setupLogger();
        }

        return LOGGER;
    }

    private static void setupLogger() {
        LogManager.getLogManager().reset();

        final StdoutConsoleHandler ch = new StdoutConsoleHandler();
        ch.setFormatter(new Formatter() {

            @Override
            public String format(final LogRecord record) {
                return "[" + new SimpleDateFormat("H:mm:ss", Locale.ENGLISH).format(record.getMillis()) + "] "
                        + record.getLevel().getName() + ": " + record.getMessage() + " (" + record.getSourceClassName()
                        + "::" + record.getSourceMethodName() + ")\r\n";
            }
        });
        ch.setLevel(Level.ALL);

        LOGGER.addHandler(ch);
        LOGGER.setLevel(Level.ALL);

        isInitialized = true;
    }

    static class StdoutConsoleHandler extends ConsoleHandler {

        @Override
        protected void setOutputStream(final OutputStream out) throws SecurityException {
            super.setOutputStream(System.out); // USESYS
        }

    }

}

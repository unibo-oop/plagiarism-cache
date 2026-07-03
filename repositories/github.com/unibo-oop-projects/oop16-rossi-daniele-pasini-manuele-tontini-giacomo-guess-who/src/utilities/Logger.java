package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * Allow to write log messages along various stream and get the game event history.
*/
public final class Logger {

    private static final String LOG_PATH = "log.txt";
    private static final Logger SINGLETON = new Logger();

    private final List<String> history;
    private final Set<PrintStream> ps;

    private Logger() {
        ps = new HashSet<>();
        ps.add(System.out);
        try {
            ps.add(new PrintStream(new FileOutputStream(new File(LOG_PATH))));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        history = new ArrayList<>();
    }

    /**
     * Allow to write log messages along various stream.
     * 
     * @param msg
     *            the message that should be written
     */
    public void write(final String msg) {
        ps.stream().forEach(x -> x.println(getCurrentTime() + " " + msg));
    }

    /**
     * 
     * @return a list of events that occurred in the game
     */
    public List<String> getHistory() {
        return Collections.unmodifiableList(history);
    }

    /**
     * @return the singleton instance of this class
     */
    public static Logger getLogger() {
        return Logger.SINGLETON;
    }

    private String getCurrentTime() {
        final Calendar cal = Calendar.getInstance();
        final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.ITALIAN);
        return sdf.format(cal.getTime());
    }
}

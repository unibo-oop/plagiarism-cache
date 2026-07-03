package view.utilities;

import java.util.concurrent.TimeUnit;

/**
 * Contains static method used to convert data into preferred format.
 */
public final class DataConversions {

    private DataConversions() {
    }

    /**
     * Convert milliseconds into format mm:ss:SSS.
     * 
     * @param milliseconds
     *            value to convert
     * @return a string that represent timer
     */
    public static String getTimeFromMilliseconds(final int milliseconds) {
        final int m = (int) TimeUnit.MILLISECONDS.toMinutes(milliseconds);
        final int s = (int) TimeUnit.MILLISECONDS.toSeconds(milliseconds) - (int) TimeUnit.MINUTES.toSeconds(m);
        final int ms = (int) TimeUnit.MILLISECONDS.toMillis(milliseconds) - (int) TimeUnit.SECONDS.toMillis(s)
                - (int) TimeUnit.MINUTES.toMillis(m);
        return String.format("%d", m) + ":" + String.format("%02d", s) + ":" + String.format("%03d", ms);
    }

}

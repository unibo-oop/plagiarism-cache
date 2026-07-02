package justanotherchessgame.util;

/**
 * Class used to measure time.
 */
public final class GameTimer {

    private static long begin, end, total;

    /**
     * Private constructor for the static utility class.
     */
    private GameTimer() {
    };

    /**
     * Method which sets the starting time.
     */
    public static void start() {
        begin = System.currentTimeMillis();
    }

    /**
     * Method which sets the ending time.
     */
    public static void stop() {
        end = System.currentTimeMillis();
        total = 0;
        total += (end - begin);
    }

    /**
     * Method which returns the time measured.
     * @return the total time measured.
     */
    public static long getTimer() {
        return total;
    }

    /**
     * Method which returns the time measured as seconds.
     * @return the total time measured as seconds.
     */
    public static double getSeconds() {
        return total / 1000.0;
    }

    /**
     * Method which returns the time measured as minutes.
     * @return the total time measured as minutes.
     */
    public static double getMinutes() {
        return total / 60000.0;
    }
}

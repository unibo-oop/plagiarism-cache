package spacesurvival.utilities;

public final class ThreadUtils {

    /**
     * Min sleep thread.
     */
    public static final int MIN_SLEEP = 1;

    /**
     * Medium sleep thread.
     */
    public static final int MEDIUM_SLEEP = 5;

    /**
     * Empty constructor for ThreadUtils.
     */
    private ThreadUtils() {
    }

    /**
     * Sleep method for the thread.
     * 
     * @param milliseconds milliseconds to sleep
     */
    public static void sleep(final long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

package utility;

public final class Utilities {
	
	/**
     * 
     * @param period
     *            total time available to perform the cycle phase.
     * @param start
     *            start time of the cycle phase.
     */
	public static void waitForNextFrame(final int period, final long start) {
        final long dt = System.currentTimeMillis() - start;
        if (dt < period) {
            try {
                Thread.sleep(period - dt);
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }
    }
}

package common;

/**
 * Class to write on the "standard" output stream.
 */
public final class Log {

    private Log() {
    }

    /**
     * Manage the log of the string.
     * 
     * @param s : string to be logged
     */
    public static void add(final String s) {
        System.out.println(s);
    }
}

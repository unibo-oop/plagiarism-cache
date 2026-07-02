package it.trashwarecesena.trustalodesktopclient.model.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.util.Locale;

/**
 * A utility class holding all the values used to unit-test the system.
 * 
 * @author Manuel Bonarrigo
 *
 */
public final class TestConstants {

    /**
     * An empty String.
     */
    public static final String EMPTY_STRING = "";

    /**
     * A String only made of a single white space.
     */
    public static final String SINGLE_SPACE_STRING = " ";

    /**
     * A String made of multiple white spaces.
     */
    public static final String MULTI_SPACE_STRING = "        ";

    /**
     * A String containing the value {@code TheString}.
     */
    public static final String A_STRING = "TheString";

    /**
     * A String containing the value {@code DifferentString}.
     */
    public static final String A_DIFFERENT_STRING = "Different";

    /**
     * A String containing the value {@code TheString}, the same from the A_STRING
     * constant.
     */
    public static final String THE_SAME_STRING = "TheString";

    /**
     * The value {@code 0}, wrapped as an Integer.
     */
    public static final Integer INT_ZERO = Integer.valueOf(0);

    /**
     * An Integer containing the value of {@code 1}.
     */
    public static final Integer A_POSITIVE_INTEGER = Integer.valueOf(1);

    /**
     * An Integer containing the value of {@code 2}.
     */
    public static final Integer A_DIFFERENT_POSITIVE_INTEGER = Integer.valueOf(2);

    /**
     * An Integer containing the value of {@code 1}.
     */
    public static final Integer THE_SAME_POSITIVE_INTEGER = Integer.valueOf(1);

    /**
     * An Integer containing the value of {@code -1}.
     */
    public static final Integer A_NEGATIVE_INTEGER = Integer.valueOf(-1);

    /**
     * An Integer containing the value of {@code -2}.
     */
    public static final Integer A_DIFFERENT_NEGATIVE_INTEGER = Integer.valueOf(-2);

    /**
     * An Integer containing the value of {@code -1}.
     */
    public static final Integer THE_SAME_NEGATIVE_INTEGER = Integer.valueOf(-1);

    /**
     * The value {@code 0}, wrapped as a Float.
     */
    public static final Float FLO_ZERO = Float.valueOf(0);

    /**
     * A Float containing the value of {@code 1.00}.
     */
    public static final Float A_POSITIVE_FLOAT = Float.valueOf(1f);

    /**
     * A Float containing the value of {@code 2.00}.
     */
    public static final Float A_DIFFERENT_POSITIVE_FLOAT = Float.valueOf(2f);

    /**
     * A Float containing the value of {@code 1.00}.
     */
    public static final Float THE_SAME_POSITIVE_FLOAT = Float.valueOf(1f);

    /**
     * A Float containing the value of {@code -1.00}.
     */
    public static final Float A_NEGATIVE_FLOAT = Float.valueOf(-1f);

    /**
     * A Float containing the value of {@code -2.00}.
     */
    public static final Float A_DIFFERENT_NEGATIVE_FLOAT = Float.valueOf(-2f);

    /**
     * A Float containing the value of {@code -1.00}.
     */
    public static final Float THE_SAME_NEGATIVE_FLOAT = Float.valueOf(-1f);

    /**
     * A {@link Date} containing the date 21 August 2018.
     */
    public static final Date DATE = Date.valueOf("2018-08-21");

    /**
     * A {@link Date} containing the date 05 October 2018.
     */
    public static final Date DIFFERENT_DATE = Date.valueOf("2018-10-05");

    /**
     * A {@link Date} containing the date 21 August 2018.
     */
    public static final Date SAME_DATE = Date.valueOf("2018-08-21");

    /**
     * An {@link URL} containing the value https://www.google.com.
     */
    public static final URL U_R_L = createURL("https://www.google.com");

    /**
     * An {@link URL} containing the value https://www.google.com.
     */
    public static final URL DIFFERENT_U_R_L = createURL("https://www.google.it");

    /**
     * An {@link URL} containing the value https://www.google.com.
     */
    public static final URL SAME_U_R_L = createURL("https://www.google.com");

    /**
     * The italian Locale.
     */
    public static final Locale IT = new Locale("it");

    private static URL createURL(final String url) {
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private TestConstants() {
    }

}

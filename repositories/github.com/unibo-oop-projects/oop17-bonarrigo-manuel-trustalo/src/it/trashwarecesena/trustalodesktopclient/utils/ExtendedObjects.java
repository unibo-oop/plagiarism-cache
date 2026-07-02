package it.trashwarecesena.trustalodesktopclient.utils;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * An extended set of functionalities to use in addition to {@link Objects}.
 * 
 * @author Manuel Bonarrigo
 *
 */
public final class ExtendedObjects {

    private ExtendedObjects() { }

    /**
     * Used to assert the presence of the content in a {@link String}. A String is
     * considered empty if it is not null and constituted of no characters or only
     * spaces.
     * 
     * Notice that {@code null} is a legal parameter, and will be returned as it is.
     * 
     * <ul>
     * <li>{@code String s = ""} is an illegal value</li>
     * <li>{@code String s = " "} is an illegal value</li>
     * <li>{@code String s = "          "} is an illegal value</li>
     * <li>{@code String s = "         a"} is a legal value</li>
     * <li>{@code String s = null} is a legal value</li>
     * </ul>
     * 
     * The RegExp used is "^\\s*?$".
     * 
     * @param s
     *            the string to be checked for emptiness
     * @throws IllegalArgumentException
     *             if the string is found to be empty
     * @return the parameter s, if it had any content within, or {@code null}, if
     *         {@code null} was the value of the parameter s
     */
    public static String requireNonEmpty(final String s) {
        return requireNonEmpty(s, "");
    }

    /**
     * Used to assert the presence of the content in a {@link String}. A String is
     * considered empty if it is not null and constituted of no characters or only
     * spaces.
     * 
     * Notice that {@code null} is a legal parameter, and will be returned as it is.
     * 
     * <ul>
     * <li>{@code String s = ""} is an illegal value</li>
     * <li>{@code String s = " "} is an illegal value</li>
     * <li>{@code String s = "          "} is an illegal value</li>
     * <li>{@code String s = "         a"} is a legal value</li>
     * <li>{@code String s = null} is a legal value</li>
     * </ul>
     * 
     * The RegExp used is "^\\s*?$".
     * 
     * 
     * @param s
     *            the string to be checked for emptiness
     * @param msg
     *            detail message to be used in the event that a
     *            IllegalStateException is thrown
     * @throws IllegalArgumentException
     *             if the string is found to be empty
     * @return the parameter s, if it had any content within, or {@code null}, if
     *         {@code null} was the value of the parameter s
     */
    public static String requireNonEmpty(final String s, final String msg) {
        if (Objects.nonNull(s) && isEmpty(s)) {
            throw new IllegalArgumentException(msg);
        }
        return s;
    }

    /**
     * Used to assert the positivity of an Integer. An Integer is considered
     * positive if the value it carries is . strictly greater than 0.
     * 
     * @param i
     *            the Integer to assert the positivity of.
     * @throws IllegalArgumentException
     *             if the number is found to be 0 or negative.
     * @return the parameter i, if it was positive, or {@code null}, if {@code null}
     *         was the value of the parameter i.
     */
    public static Integer requirePositive(final Integer i) {
        return requirePositive(i, "");
    }

    /**
     * Used to assert the positivity of an Integer. An Integer is considered
     * positive if the value it carries is strictly greater than 0.
     * 
     * @param i
     *            the Integer to assert the positivity of.
     * @param msg
     *            detail message to be used in the event that a
     *            IllegalStateException is thrown
     * @throws IllegalArgumentException
     *             if the number is found to be 0 or negative.
     * @return the parameter i, if it was positive, or {@code null}, if {@code null}
     *         was the value of the parameter i.
     */
    public static Integer requirePositive(final Integer i, final String msg) {
        if (Objects.nonNull(i) && isNonPositive(i)) {
            throw new IllegalArgumentException(msg);
        }
        return i;
    }

    /**
     * Used to assert the positivity of a Float. A Float is considered positive if
     * the value it carries is strictly greater than 0.
     * 
     * @param f
     *            the Float to assert the positivity of.
     * @throws IllegalArgumentException
     *             if the number is found to be 0 or negative.
     * @return the parameter f, if it was positive, or {@code null}, if {@code null}
     *         was the value of the parameter f.
     */
    public static Float requirePositive(final Float f) {
        return requirePositive(f, "");
    }

    /**
     * Used to assert the positivity of a Float. A Float is considered positive if
     * the value it carries is strictly greater than 0.
     * 
     * @param f
     *            the Float to assert the positivity of.
     * @param msg
     *            detail message to be used in the event that a
     *            IllegalStateException is thrown
     * @throws IllegalArgumentException
     *             if the number is found to be 0 or negative.
     * @return the parameter f, if it was positive, or {@code null}, if {@code null}
     *         was the value of the parameter f.
     */
    public static Float requirePositive(final Float f, final String msg) {
        if (Objects.nonNull(f) && isNonPositive(f)) {
            throw new IllegalArgumentException(msg);
        }
        return f;
    }

    /**
     * Used to assert the non-negativity of a Float. A Float is considered
     * non-negative if the value it carries is greater or equal than 0.
     * 
     * @param f
     *            the Float to assert the non-negativity of.
     * @throws IllegalArgumentException
     *             if the number is found to be negative.
     * @return the parameter f, if it was non-negative, or {@code null}, if
     *         {@code null} was the value of the parameter f.
     */
    public static Float requireNonNegative(final Float f) {
        return requireNonNegative(f, "");
    }

    /**
     * Used to assert the positivity of a Float. A Float is considered non-negative
     * if the value it carries is greater or equal than 0.
     * 
     * @param f
     *            the Float to assert the non-negativity of.
     * @param msg
     *            detail message to be used in the event that a
     *            IllegalStateException is thrown
     * @throws IllegalArgumentException
     *             if the number is found to be negative.
     * @return the parameter f, if it was non-negative, or {@code null}, if
     *         {@code null} was the value of the parameter f.
     */
    public static Float requireNonNegative(final Float f, final String msg) {
        if (Objects.nonNull(f) && isNegative(f)) {
            throw new IllegalArgumentException(msg);
        }
        return f;
    }

    /**
     * Used to assert the positivity of an Integer. An Integer is considered non-negative
     * if the value it carries is greater or equal than 0.
     * 
     * @param i
     *            the Integer to assert the non-negativity of.
     * @throws IllegalArgumentException
     *             if the number is found to be negative.
     * @return the parameter i, if it was non-negative, or {@code null}, if
     *         {@code null} was the value of the parameter i.
     */
    public static Integer requireNonNegative(final Integer i) {
        return requireNonNegative(i, "");
    }

    /**
     * Used to assert the positivity of an Integer. An Integer is considered non-negative
     * if the value it carries is greater or equal than 0.
     * 
     * @param i
     *            the Integer to assert the non-negativity of.
     * @param msg
     *            detail message to be used in the event that a
     *            IllegalStateException is thrown
     * @throws IllegalArgumentException
     *             if the number is found to be negative.
     * @return the parameter i, if it was non-negative, or {@code null}, if
     *         {@code null} was the value of the parameter i.
     */
    public static Integer requireNonNegative(final Integer i, final String msg) {
        if (Objects.nonNull(i) && isNegative(i)) {
            throw new IllegalArgumentException(msg);
        }
        return i;
    }

    private static boolean isEmpty(final String s) {
        return Pattern.matches("^\\s*?$", s);
    }

    private static boolean isNonPositive(final Integer i) {
        return i <= 0;
    }

    private static boolean isNegative(final Integer n) {
        return n < 0;
    }

    private static boolean isNonPositive(final Float f) {
        return f <= 0;
    }

    private static boolean isNegative(final Float f) {
        return f < 0;
    }

}

package org.gitgud.utils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.gitgud.events.utils.GlobalExceptionThrowedListener;

import javafx.application.Platform;

/**
 * Utility class that provides static methods that are accessible for the whole application.
 */
public final class Utils {

    private static final Locale LOCALE = new Locale("en");
    private static final Pattern KEY_PATTERN = Pattern.compile("\\%([a-zA-Z0-9\\.]+)\\%");
    private static final Thread.UncaughtExceptionHandler DEFAULT_UNCAUGHT_EXCEPTION_HANDLER;
    private static final ExecutorService EXECUTOR;
    private static final boolean LOG_STACK_TRACE = true;
    private static final List<GlobalExceptionThrowedListener> LISTENER;

    static {
        LISTENER = new LinkedList<>();

        DEFAULT_UNCAUGHT_EXCEPTION_HANDLER = (t, e) -> {
            LISTENER.forEach(
                    gltl -> gltl.onGlobalExceptionThrowed(e.getMessage() + "\nSee the logs for more details."));

            Log.getLogger().severe("Uncaught Exception in thread '" + t.getName() // Use severe
                    + "'. Exception message: " + e.getMessage() + " (" + e.getClass() + ")");

            if (LOG_STACK_TRACE) {
                Log.getLogger().config("Uncaught Exception stack trace down here");
                Arrays.stream(e.getStackTrace()).forEach(s -> Log.getLogger().config(s.toString()));
            }
        };

        EXECUTOR = Executors.newSingleThreadExecutor(r -> {
            final Thread single = new Thread(r, "GitGud Hard Work Thread");
            Thread.setDefaultUncaughtExceptionHandler(DEFAULT_UNCAUGHT_EXCEPTION_HANDLER);
            return single;
        });
    }

    private Utils() {
    }

    /**
     * Add a global exception throwed listener.
     *
     * @param getl
     *            the GlobalExceptionThrowedListener to add
     */
    public static void addGlobalExceptionThrowedListener(final GlobalExceptionThrowedListener getl) {
        Objects.requireNonNull(getl);
        LISTENER.add(getl);
    }

    /**
     * Close all resources opened.
     */
    public static void closeResources() {
        EXECUTOR.shutdown();
    }

    /**
     * Execute the Runnable in the thread that must be used for heavy tasks. Should by called from the JavaFX
     * Application Thread.
     *
     * @param run
     *            the code to be executed
     */
    public static void doHardWork(final Runnable run) {
        EXECUTOR.execute(run);
    }

    /**
     * @return the default UncaughtExceptionHandler
     */
    public static Thread.UncaughtExceptionHandler getDefaultUncaughtExceptionHandler() {
        return DEFAULT_UNCAUGHT_EXCEPTION_HANDLER;
    }

    /**
     * @return the resource bundle that contains all translations for the labels
     */
    public static ResourceBundle getLabelsBundle() {
        return ResourceType.LABELS.getBundle(LOCALE);
    }

    /**
     * @return the application locale
     */
    public static Locale getLocale() {
        return LOCALE;
    }

    /**
     * @return the operation system path separator
     */
    public static String getSystemSeparator() {
        return System.getProperty("file.separator");
    }

    /**
     * Replace all placeholder of a string with a replacement.
     *
     * @param source
     *            the source string
     * @param placeholder
     *            the pattern of the string to replace
     * @param replacement
     *            the replacement
     * @return the string replaced with placeholder
     */
    public static String replacePattern(final String source, final String placeholder, final String replacement) {
        return source.replaceAll("\\{" + placeholder + "\\}", replacement);
    }

    /**
     * Return the resolved string from a bundle and a key. Supports nested strings.
     *
     * @param type
     *            the ResourceType of the bundle
     * @param key
     *            the key of the string to obtain
     * @return the string resolved
     */
    public static String resolveString(final ResourceType type, final String key) {
        String resolution = type.getBundle(LOCALE).getString(key);
        final Matcher m = KEY_PATTERN.matcher(resolution);

        while (m.find()) {
            resolution = resolution.replace(m.group(), resolveString(type, m.group(1)));
        }

        return resolution;
    }

    /**
     * Execute the Runnable in the thread that update the View of JavaFX Application.
     *
     * @param run
     *            the code to be executed
     */
    public static void updateView(final Runnable run) {
        Platform.runLater(run);
    }

}

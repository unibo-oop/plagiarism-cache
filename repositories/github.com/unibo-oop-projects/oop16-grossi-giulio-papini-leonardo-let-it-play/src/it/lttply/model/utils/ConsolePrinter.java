package it.lttply.model.utils;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi.Attribute;
import com.diogonunes.jcdp.color.api.Ansi.BColor;
import com.diogonunes.jcdp.color.api.Ansi.FColor;

/**
 * Provides a cross platform SINGLETON, useful to print messages to console with
 * custom formats and colors.
 */
public final class ConsolePrinter {
    private static final String SEPARATOR = " - ";
    private static final Object LOCK = new Object();
    private final ColoredPrinter cp;

    private static class ConsolePrinterHolder {
        private static final ConsolePrinter SINGLETON = new ConsolePrinter(); // NOPMD
    }

    private ConsolePrinter() {
        cp = new ColoredPrinter.Builder(1, false).foreground(FColor.WHITE).background(BColor.BLUE).build();
    }

    /**
     * @return the printer class
     */
    public static ConsolePrinter getPrinter() {
        return ConsolePrinterHolder.SINGLETON;
    }

    /**
     * Prints the date and a normal message without formatting.
     * 
     * @param message
     *            the message to be printed
     */
    public void printlnDebug(final String message) {
        synchronized (LOCK) {
            cp.print(cp.getDateFormatted(), Attribute.NONE, FColor.CYAN, BColor.NONE);
            cp.clear();
            cp.clear();
            cp.debugPrint(SEPARATOR);
            cp.clear();
            cp.clear();
            cp.debugPrintln(message);
            cp.clear();
            cp.clear();
        }
    }

    /**
     * Prints the date and the message with a cyan foreground color, you can use
     * it to warn when some procedure starts.
     * 
     * @param message
     *            the message to be printed
     */
    public void printlnProcedureStarted(final String message) {
        synchronized (LOCK) {
            cp.print(cp.getDateFormatted(), Attribute.NONE, FColor.CYAN, BColor.NONE);
            cp.clear();
            cp.clear();
            cp.debugPrint(SEPARATOR);
            cp.clear();
            cp.clear();
            cp.debugPrintln(message, Attribute.NONE, FColor.WHITE, BColor.BLUE);
            cp.clear();
            cp.clear();
        }
    }

    /**
     * Prints the date and the message with a green foreground color, you can
     * use it to warn when some procedure finished without errors.
     * 
     * @param message
     *            the message to be printed
     */
    public void printlnSuccess(final String message) {
        synchronized (LOCK) {
            cp.print(cp.getDateFormatted(), Attribute.NONE, FColor.CYAN, BColor.NONE);
            cp.clear();
            cp.clear();
            cp.debugPrint(SEPARATOR);
            cp.clear();
            cp.clear();
            cp.print(message + "\n", Attribute.BOLD, FColor.YELLOW, BColor.GREEN);
            cp.clear();
            cp.clear();
        }
    }

    /**
     * Prints the date and the message with a red foreground color, you can use
     * it to warn when some procedure finished with errors.
     * 
     * @param message
     *            the message to be printed
     */
    public void printlnError(final String message) {
        synchronized (LOCK) {
            cp.print(cp.getDateFormatted(), Attribute.NONE, FColor.CYAN, BColor.NONE);
            cp.clear();
            cp.clear();
            cp.debugPrint(SEPARATOR);
            cp.clear();
            cp.clear();
            cp.print(message + "\n", Attribute.BOLD, FColor.YELLOW, BColor.RED);
            cp.clear();
            cp.clear();
        }
    }
}

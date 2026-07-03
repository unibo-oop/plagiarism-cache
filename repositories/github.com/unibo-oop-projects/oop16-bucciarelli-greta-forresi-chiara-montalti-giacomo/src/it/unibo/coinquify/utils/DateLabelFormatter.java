package it.unibo.coinquify.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFormattedTextField.AbstractFormatter;

/**
 * Parser for Date-> String & String-> Date.
 */
public class DateLabelFormatter extends AbstractFormatter {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final String PATTERN = "dd-MMMM-yyyy";
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat(PATTERN, Messages.getMessages().getCurrentLocale());

    @Override
    public Object stringToValue(final String text) throws ParseException {
        return dateFormatter.parseObject(text);
    }

    @Override
    public String valueToString(final Object value) throws ParseException {
        if (value != null) {
            final Calendar cal = (Calendar) value;
            return dateFormatter.format(cal.getTime());
        }

        return "";
    }

    /**
     * 
     * @return a parser for date with a specific PATTERN (dd-MMMM-yyyy)
     */
    public SimpleDateFormat getDateFormatter() {
        return this.dateFormatter;
    }
}

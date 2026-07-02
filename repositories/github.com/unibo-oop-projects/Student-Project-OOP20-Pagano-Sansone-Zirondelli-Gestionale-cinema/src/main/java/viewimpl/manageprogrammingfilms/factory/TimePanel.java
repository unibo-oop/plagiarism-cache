package viewimpl.manageprogrammingfilms.factory;

import java.awt.GridLayout;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TimePanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private static final int TOP_EMPTY_BORDER = 10;
    private static final int LEFT_EMPTY_BORDER = 10;
    private static final int BOTTOM_EMPTY_BORDER = 10;
    private static final int RIGHT_EMPTY_BORDER = 10;

    private static final int ROWS_GRID = 2;
    private static final int COLS_GRID = 0;
    private static final int HGAP_GRID = 5;
    private static final int VGAP_GRID = 5;

    private static final int MAX_MIN = 59;
    private static final int MAX_HOUR = 23;

    private final JTextField hourTextField;
    private final JTextField minTextField;

    TimePanel() {
        final LocalTime localTime = LocalTime.now();
        final JLabel hourLabel;
        final JLabel minLabel;
        setLayout(new GridLayout(ROWS_GRID, COLS_GRID, HGAP_GRID, VGAP_GRID));
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Enter Time"),
                BorderFactory.createEmptyBorder(TOP_EMPTY_BORDER, LEFT_EMPTY_BORDER, BOTTOM_EMPTY_BORDER, RIGHT_EMPTY_BORDER)));
        hourTextField = new JTextField(String.valueOf(localTime.getHour()));
        hourTextField.setHorizontalAlignment(JTextField.RIGHT);
        minTextField = new JTextField(String.valueOf(localTime.getMinute()));
        minTextField.setHorizontalAlignment(JTextField.RIGHT);

        hourLabel = new JLabel("Hour", JLabel.RIGHT);
        minLabel = new JLabel("MIN", JLabel.RIGHT);

        add(hourLabel);
        add(minLabel);
        add(hourTextField);
        add(minTextField);
    }

    private int getHour() {
        int hour;
        try {
            hour = Integer.parseInt(hourTextField.getText());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Hour must be number!");
        }

        if (hour < 0 || hour > MAX_HOUR) {
            throw new IllegalArgumentException("Hours must be between 0 to 23!");
        }
        return hour;
    }

    private int getMinutes() {
        int min;
        try {
            min = Integer.parseInt(minTextField.getText());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Minutes must be number!");
        }

        if (min < 0 || min > MAX_MIN) {
            throw new IllegalArgumentException("Minutes must be between 0 and 59!");
        }
        return min;
    }

    private void setHourTextField(final int hour) {
        final int maxHour = 23;
        if (hour < 0 || hour > maxHour) {
            throw new IllegalArgumentException("Hours must be between 0 to 23!");
        }
        hourTextField.setText(String.valueOf(hour));
    }

    private void setMinTextField(final int min) {
        if (min < 0 || min > MAX_MIN) {
            throw new IllegalArgumentException("Minutes must be between 0 and 59!");
        }
        minTextField.setText(String.valueOf(min));
    }
    /** 
     * Set time.
     * @param time time to set
     * */
    public void setTime(final LocalTime time) {
        setHourTextField(time.getHour());
        setMinTextField(time.getMinute());
    }
    /** 
     * Get time.
     * @param date date selected
     * @return  inserted time 
     * */
    public LocalTime getTime(final LocalDate date) {
        return LocalTime.of(this.getHour(), this.getMinutes());
    }
    /** 
     * Reset time.
     * */
    public void reset() {
        this.setTime(LocalTime.now());
    }
}

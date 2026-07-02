package viewimpl.manageprogrammingfilms.factory;

import static javax.swing.BorderFactory.*;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.text.DateFormatSymbols;
import java.time.LocalDate;
/** 
 * Describe panel with date.
 * */
public final class DatePanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private final JComboBox<String> monthNamesComboBox;
    private final JTextField dayTextField;
    private final JTextField yearTextField;

    private static final int TOP_EMPTY_BORDER = 10;
    private static final int LEFT_EMPTY_BORDER = 10;
    private static final int BOTTOM_EMPTY_BORDER = 10;
    private static final int RIGHT_EMPTY_BORDER = 10;
    private static final int TOP_PANEL_BORDER = 0;
    private static final int LEFT_PANEL_BORDER = 5;
    private static final int BOTTOM_PANEL_BORDER = 0;
    private static final int RIGHT_PANEL_BORDER = 0;
    private static final int ROWS_GRID = 2;
    private static final int COLS_GRID = 0;
    private static final int HGAP_GRID = 5;
    private static final int VGAP_GRID = 5;

    private static final int INDEX_TO_REMOVE = 12;

    DatePanel() {
        setLayout(new BorderLayout());
        setBorder(createCompoundBorder(createTitledBorder("Enter Date"),
                createEmptyBorder(TOP_EMPTY_BORDER, LEFT_EMPTY_BORDER, BOTTOM_EMPTY_BORDER, RIGHT_EMPTY_BORDER)));

        final JPanel monthPanel = new JPanel(new GridLayout(ROWS_GRID, COLS_GRID, HGAP_GRID, VGAP_GRID));
        final DateFormatSymbols dateFormatter = DateFormatSymbols.getInstance(); // to retrieve months name
        final String [] months = dateFormatter.getMonths();
        monthNamesComboBox = new JComboBox<>(months);
        monthNamesComboBox.removeItemAt(INDEX_TO_REMOVE);
        monthPanel.add(new JLabel("Month", JLabel.CENTER));
        monthPanel.add(monthNamesComboBox);

        final JPanel dayYearPanel = new JPanel(new GridLayout(ROWS_GRID, COLS_GRID, HGAP_GRID, VGAP_GRID));
        dayYearPanel.setBorder(createEmptyBorder(TOP_PANEL_BORDER, LEFT_PANEL_BORDER, BOTTOM_PANEL_BORDER, RIGHT_PANEL_BORDER));
        dayTextField = new JTextField();
        dayTextField.setHorizontalAlignment(JTextField.RIGHT);
        yearTextField = new JTextField();
        yearTextField.setHorizontalAlignment(JTextField.RIGHT);

        // set date values
        final LocalDate todayDate = LocalDate.now();

        monthNamesComboBox.setSelectedIndex(todayDate.getMonthValue() - 1);
        dayTextField.setText(Integer.toString(todayDate.getDayOfMonth()));
        yearTextField.setText(Integer.toString(todayDate.getYear()));

        dayYearPanel.add(new JLabel("Day", JLabel.RIGHT));
        dayYearPanel.add(new JLabel("Year", JLabel.RIGHT));
        dayYearPanel.add(dayTextField);
        dayYearPanel.add(yearTextField);

        add(monthPanel, BorderLayout.WEST);
        add(dayYearPanel, BorderLayout.EAST);

    }

    public LocalDate getDate()  {
        if (yearTextField.getText().length() != 4) {
            throw new NumberFormatException("You must enter a 4-digit year number!");
        }

        // parseInt may throw a NumberFormatException (i.e. run time exception)
        final int year = Integer.parseInt(yearTextField.getText());
        final int day = Integer.parseInt(dayTextField.getText());

        final int month = monthNamesComboBox.getSelectedIndex() + 1;
        final LocalDate requestedDate = LocalDate.of(year, month, day);
        // validate date

        final LocalDate start = requestedDate.withDayOfMonth(1);
        final LocalDate lastDayOfMonth = start.withDayOfMonth(start.lengthOfMonth());

        final int max = lastDayOfMonth.getDayOfMonth(); // get day of this specific month and year
        if (day < 1 || day > max) {
            throw new NumberFormatException("Day must be between 1 and " + max + "!");
        }

        if (requestedDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("You cannot schedule in the past. Please change date");
        }

        return requestedDate;
    }

    private void setDate(final LocalDate date) {
        monthNamesComboBox.setSelectedIndex(date.getMonthValue() - 1);
        dayTextField.setText(String.valueOf(date.getDayOfMonth()));
        yearTextField.setText(String.valueOf(date.getYear()));
    }

    public void reset() {
        this.setDate(LocalDate.now());
    }

}

package viewimpl.manageprogrammingfilms.factory;

import java.awt.LayoutManager;
import java.util.EventObject;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.mindfusion.common.ChangeListener;
import com.mindfusion.common.DateTime;
import com.mindfusion.scheduling.Calendar;
import com.mindfusion.scheduling.CalendarView;
import com.mindfusion.scheduling.ThemeType;

import view.manageprogrammingfilms.factory.ProgrammingFilmsGUIfactory;

public final class ProgrammingFilmsViewFactoryImpl implements ProgrammingFilmsGUIfactory {

    /** 
     * {@inheritDoc}
     * */
    @Override
    public JPanel createPanel(final LayoutManager layout) {
        return new JPanel(layout);
    }
    /** 
     * {@inheritDoc}
     * */
    @Override
    public JButton createButton(final String text) {
        return new JButton(text);
    }
    /** 
     * {@inheritDoc}
     * */
    @Override
    public Calendar createCalendar() {
        final Calendar calendar = new Calendar();
        calendar.setCurrentTime(DateTime.now());
        calendar.setDate(DateTime.today());
        // Calendar initialization start
        calendar.beginInit();
        calendar.setCurrentView(CalendarView.SingleMonth);
        calendar.setTheme(ThemeType.Silver);
        calendar.getMonthRangeSettings().setMonthsPerRow(1);
        calendar.getMonthRangeSettings().setNumberOfMonths(1);
        calendar.getSelection().setAllowMultiple(false);
        calendar.getMonthSettings().getDaySettings().setHeaderSize(0);
        calendar.endInit();
        // Calendar initialization end
        return calendar;
    }
    /** 
     * {@inheritDoc}
     * */
    @Override
    public JTable createTable(final String[] columnNames, final Object[][] data) {
        final DefaultTableModel model = new DefaultTableModel(data, columnNames);
        final JTable table = new JTable(model) {
                private static final long serialVersionUID = 1L;
                public boolean isCellEditable(final int row, final int column) {
                    return false;
                }
        };
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        return table;
    }

}

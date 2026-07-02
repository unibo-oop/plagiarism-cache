package view.manageprogrammingfilms.factory;

import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.mindfusion.scheduling.Calendar;
/** 
 * Factory to create components of programming films gui.
 * */
public interface ProgrammingFilmsGUIfactory {
    /** 
     * Creates a panel with specific layout.
     * @param layout layout to use
     * @return created panel
     * */
    JPanel createPanel(LayoutManager layout);
    /** 
     * Creates a button with specific text.
     * @param text text to use
     * @return created button
     * */
    JButton createButton(String text);
    /** 
     * Creates a calendar.
     * @return created calendar
     * */
    Calendar createCalendar();
    /** 
     * Creates a table with specific columns names adn data.
     * @param columnNames names of columns
     * @param data date to insert
     * @return created table
     * */
    JTable createTable(String [] columnNames, Object[][] data);
}

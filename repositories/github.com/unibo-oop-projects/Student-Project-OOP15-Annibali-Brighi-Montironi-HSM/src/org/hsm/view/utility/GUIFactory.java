package org.hsm.view.utility;

import java.util.List;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;

import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 * The interface for GUI Factory.
 *
 */
public interface GUIFactory {

    /**
     * Create a button.
     * 
     * @param name
     *            the name of the button.
     * @param image
     *            the image of the button.
     * @return the new JButton.
     */
    JButton createButton(final String name, final Icon image);

    /**
     * Create a new label with a specific size of font.
     * 
     * @param text
     *            the text to write in the label
     * @return a new JLabel.
     */
    JLabel createLabel(final String text);

    /**
     * Create a new JSpinner with the specific size.
     * 
     * @param size
     *            the size of the spinner.
     * @param model
     *            the spinner model.
     * @return a new JSpinner
     */
    JSpinner createSpinner(final int size, final SpinnerModel model);

    /**
     * Create a new JTextField with the specific font size.
     * 
     * @param size
     *            the size of the font.
     * @return a new JTextField
     */
    JTextField createTextField(final int size);

    /**
     * Create a new JTable with the specified columns and ready to be filtered.
     * 
     * @param columns
     *            the columns of the table
     * @return a new JTable with the specified columns
     */
    JTable createTable(final Object... columns);

    /**
     * Create a bar chart with the legend placed on the left.
     * 
     * @param dataset
     *            the dataset for the chart
     * @return a new chart with the specific dataset
     */
    JFreeChart createPieChart(final DefaultPieDataset dataset);

    /**
     * Create the XY Line chart with 2 lines for comparing them through time.
     * 
     * @param firstLine
     *            the list of elements of the first line
     * @param firstLineName
     *            the first line name
     * @param secondLine
     *            the list of elements of the second line
     * @param secondLineName
     *            the second line name
     * @param unitOfMeasure
     *            the unit of Measure
     * @return the two line chart
     */
    JFreeChart createXYTwoLineChart(final List<? extends Number> firstLine, final String firstLineName,
            final List<? extends Number> secondLine, final String secondLineName, final String unitOfMeasure);

}

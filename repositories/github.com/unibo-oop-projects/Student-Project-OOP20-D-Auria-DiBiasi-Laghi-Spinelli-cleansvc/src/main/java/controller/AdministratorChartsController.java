package controller;
import javax.swing.JPanel;

import org.knowm.xchart.XYChart;
import com.github.lgooddatepicker.components.DatePicker;
import model.ChartException;
import model.DateException;
/**
 * 
 * @author Nico Nize
 *
 */

public interface AdministratorChartsController {
    /**
     * Calls buildChartsFromData method from DataChartImpl, if some error in date params is detected a DateException is thrown.
     * So the methods adds Lines to its XYChart
     * @param dateStart
     * @param dateEnd
     * @param choice
     * @param panel
     * @param chart
     * @throws DateException
     */

   void addLine(DatePicker dateStart, DatePicker dateEnd, int choice, JPanel panel, XYChart chart) throws DateException;

   /**
     * Clean the XYChart Panel, a ChartException is thrown if the XYChart is empty.
     * @param chart
     * @param panel
     * @throws ChartException
     */
   void resetChart(XYChart chart, JPanel panel) throws ChartException;

   /**
     * Delete the last line added to the XYchart, it calls deleteLast method from DataChart.
     * @param chart
     * @param panel
     * @throws ChartException
     */
   void deleteLast(XYChart chart, JPanel panel) throws ChartException;
}

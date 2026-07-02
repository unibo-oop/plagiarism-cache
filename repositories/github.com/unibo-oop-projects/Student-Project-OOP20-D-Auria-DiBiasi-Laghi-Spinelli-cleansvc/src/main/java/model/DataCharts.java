package model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.knowm.xchart.XYChart;

/**
 * 
 * @author Nico Nize
 *
 */

public interface DataCharts {
    
    /**
     * Given a choice, a date as startDate and another date as endDate,or each choice the corresponding 
     * method will be performed
     * @param dateStart
     * @param dateEnd
     * @param choose
     * @return
     */
     List<Double> buildChartsFromData(LocalDate dateStart, LocalDate dateEnd, Integer choose);
     
     /**
      *  Given a date as dateStart and a date as dateEnd,reading from file the method will calculate and sketch 
      * the gain chart for the interval of days given, more values for a date will be added.
      * @param dateStart
      * @param dateEnd
      * @return
      */
     List<Double> getEntrate(LocalDate dateStart, LocalDate dateEnd);
     
     /**
      * Given a date as dateStart and a date as dateEnd,reading from file the method will calculate and sketch 
      * the work time chart for the interval of days given, more values for a date will be added.
      * @param dateStart
      * @param dateEnd
      * @return
      */
     List<Double> getTempoLavoro(LocalDate dateStart, LocalDate dateEnd);
     
     /**
      * Return a List of Date converted form LocalDate for the XYChart Y axes
      * @return
      */
      List<Date> getDateList();
      
     /**
      *  Delete the last item added in the XYChart.
      * @param chart
      */
     void deleteLastItem(XYChart chart);
     
}

package controller;

import org.jfree.chart.JFreeChart;

public interface AnalysisController {

    /**
     * Method aimed at generating the profit chart showing profit
     * data collected in the simulation.
     * @return the profit chart 
     */
    JFreeChart getProfitChart();

    /**
     * Method aimed at generating the fair chart showing fair liking
     * data collected in the simulation.
     * @return the fair chart 
     */
    JFreeChart getFairChart();

}

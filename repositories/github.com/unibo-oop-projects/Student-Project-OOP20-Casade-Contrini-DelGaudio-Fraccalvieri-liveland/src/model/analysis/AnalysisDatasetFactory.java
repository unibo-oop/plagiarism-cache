package model.analysis;

import org.jfree.chart.JFreeChart;
import org.jfree.data.general.Dataset;

public abstract class AnalysisDatasetFactory {

    /**
     * @return A dataset containing analysis data
     */
    public abstract Dataset createDataset();

    /**
     * @return A chart produced with values in the dataset 
     * created with previous method
     */
    public abstract JFreeChart createChart();

}

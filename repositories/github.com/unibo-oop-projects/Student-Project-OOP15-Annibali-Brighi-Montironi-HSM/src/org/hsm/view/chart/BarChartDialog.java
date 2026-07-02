package org.hsm.view.chart;

import java.awt.BorderLayout;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * The dialog that contains the bar chart to compare the plant with the model.
 *
 */
public class BarChartDialog extends AbstractChartDialog {

    private static final double BAR_WIDTH_FACTOR = 0.2;

    /**
     * Create the bar chart for comparing values.
     * 
     * @param characteristic
     *            the name of the characteristic to compare
     * @param unitsOfMeasure
     *            the unit of measure to use
     * @param optimalValue
     *            the optimal value
     * @param currentValue
     *            the current value
     * @param traditionalValue
     *            the value in a traditional culture
     */
    public BarChartDialog(final String characteristic, final String unitsOfMeasure, final double optimalValue,
            final double currentValue, final double traditionalValue) {
        super(characteristic);
        // chart
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(currentValue, "Current", characteristic);
        dataset.addValue(optimalValue, "Optimal", characteristic);
        dataset.addValue(traditionalValue, "Traditional Culture", characteristic);
        final JFreeChart chart = ChartFactory.createBarChart3D(characteristic + " Comparing Chart", "", unitsOfMeasure,
                dataset, PlotOrientation.VERTICAL, true, true, false);
        final ChartPanel panel = new ChartPanel(chart, false);
        final CategoryPlot categoryPlot = chart.getCategoryPlot();
        final BarRenderer br = (BarRenderer) categoryPlot.getRenderer();
        br.setMaxBarWidth(BAR_WIDTH_FACTOR);
        super.getJDialog().add(panel, BorderLayout.CENTER);
    }

}

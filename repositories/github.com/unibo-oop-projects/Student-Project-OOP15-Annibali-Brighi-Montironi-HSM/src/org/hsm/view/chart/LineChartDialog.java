package org.hsm.view.chart;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;

import org.hsm.view.utility.GUIFactory;
import org.hsm.view.utility.MyGUIFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

/**
 * The dialog that contains the line chart to see the trend of values.
 *
 */
public class LineChartDialog extends AbstractChartDialog {

    /**
     * Create the line chart to see the value trend.
     * 
     * @param characteristic
     *            the name of the characteristic to measure
     * @param unitsOfMeasure
     *            the unit of measure to use
     * @param valueList
     *            the list of values
     * @param tradList
     *            the list of values in a traditional culture
     */
    public LineChartDialog(final String characteristic, final String unitsOfMeasure, final List<Double> valueList,
            final List<Double> tradList) {
        super(characteristic);
        // chart
        final GUIFactory factory = new MyGUIFactory();
        final JFreeChart chart = factory.createXYTwoLineChart(valueList, "Current", tradList, "Traditional Culture",
                unitsOfMeasure);
        chart.setTitle(characteristic + " Line Chart");
        final XYPlot plot = chart.getXYPlot();
        plot.setRenderer(new XYLineAndShapeRenderer());
        plot.setBackgroundPaint(Color.DARK_GRAY);
        final ChartPanel panel = new ChartPanel(chart, false);
        super.getJDialog().add(panel, BorderLayout.CENTER);
    }

}

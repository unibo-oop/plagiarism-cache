package org.hsm.view.tab;

import java.awt.BorderLayout;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.hsm.controller.ControllerImpl;
import org.hsm.view.gui.Resettable;
import org.hsm.view.utility.GUIFactory;
import org.hsm.view.utility.MyGUIFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.data.general.DefaultPieDataset;

/**
 * This tab is used to show charts about the greenhouse.
 *
 */
public class GreenhouseChartTab implements Resettable {

    private final JPanel panel;
    private final ChartPanel chartPanel;

    /**
     * Create the tab for greenhouse composition charts.
     */
    public GreenhouseChartTab() {
        this.panel = new JPanel(new BorderLayout());
        final GUIFactory factory = new MyGUIFactory();
        final JPanel buttonPanel = new JPanel();
        // buttons
        final JButton numPlantChart = new JButton("Composition by Number");
        final JButton spacePlantChart = new JButton("Composition by Occupied Space");
        final JButton waterConsuption = new JButton("Water Consuption");
        final JButton growthTime = new JButton("Plants Growth Time");
        buttonPanel.add(numPlantChart);
        buttonPanel.add(spacePlantChart);
        buttonPanel.add(waterConsuption);
        buttonPanel.add(growthTime);
        // chart
        this.chartPanel = new ChartPanel(null);
        final JScrollPane scrollPane = new JScrollPane(chartPanel);
        scrollPane.setBorder(BorderFactory.createEtchedBorder());
        // button listeners
        numPlantChart.addActionListener(e -> {
            final DefaultPieDataset dataset = new DefaultPieDataset();
            for (final Map.Entry<String, Integer> elem : ControllerImpl.getController().getGreenhouse()
                    .getCompositionByNumber().entrySet()) {
                dataset.setValue(elem.getKey(), elem.getValue());
            }
            this.chartPanel.setChart(factory.createPieChart(dataset));
        });
        spacePlantChart.addActionListener(e -> {
            final DefaultPieDataset dataset = new DefaultPieDataset();
            for (final Map.Entry<String, Double> elem : ControllerImpl.getController().getGreenhouse()
                    .getCompositionByOccupiedSpace().entrySet()) {
                dataset.setValue(elem.getKey(), elem.getValue());
            }
            this.chartPanel.setChart(factory.createPieChart(dataset));
        });
        waterConsuption.addActionListener(e -> {
            this.chartPanel.setChart(factory.createXYTwoLineChart(
                    ControllerImpl.getController().getGreenhouse().getSimulatedWaterConsuption(),
                    "Current Water Consuption", ControllerImpl.getController().getGreenhouse().getRealWaterConsuption(),
                    "Traditional Water Consuption", "ml"));
        });
        growthTime.addActionListener(e -> {
            this.chartPanel.setChart(
                    factory.createXYTwoLineChart(ControllerImpl.getController().getGreenhouse().getSimulatedPlantGrow(),
                            "Current Plant Growth", ControllerImpl.getController().getGreenhouse().getRealPlantGrow(),
                            "Traditional Plant Growth", "Growth %"));
        });
        this.panel.add(buttonPanel, BorderLayout.NORTH);
        this.panel.add(scrollPane, BorderLayout.CENTER);
    }

    @Override
    public void clean() {
        this.chartPanel.setChart(null);
    }

    @Override
    public JComponent getComponent() {
        return this.panel;
    }

}

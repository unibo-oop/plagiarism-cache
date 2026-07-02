package model.analysis;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import controller.EnvironmentControllerImpl;

public class ProfitDatasetFactory extends AnalysisDatasetFactory {

    private final EnvironmentControllerImpl controller;
    private static final String TICK = "TICKETS";

    public  ProfitDatasetFactory(final EnvironmentControllerImpl controller) {
        this.controller = controller;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final DefaultCategoryDataset createDataset() {
        final DefaultCategoryDataset profitDataset = new DefaultCategoryDataset();
        this.controller.getProfitList().forEach(f -> {
            profitDataset.addValue(f.getProfit(), f.getName(), f.getActivityType()); });
        profitDataset.addValue(this.controller.getEntranceProfit().get(0), "Adult", TICK);
        profitDataset.addValue(this.controller.getEntranceProfit().get(1), "Reduced", TICK);
        profitDataset.addValue(this.controller.getEntranceProfit().get(2), "Pass", TICK);
        return profitDataset;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final JFreeChart createChart() {
        return ChartFactory.createBarChart(
                "DAILY PROFIT STATISTICS",
                "Category", "Income (€)", 
                this.createDataset(),
                PlotOrientation.VERTICAL, 
                true, true, false);
    }

    /**
     * This is a method introduced to make debugging and testing easier.
     * @return  A phony CategoryDataset showing fixed data about profits
     */
    public DefaultCategoryDataset createPhonyDataset() {
        final int rest = 300;
        final int rest2 = 100;
        final int souv = 150;
        final DefaultCategoryDataset profitDataset = new DefaultCategoryDataset();
        profitDataset.addValue(rest, "Pizza Pazza", "RESTAURANT");
        profitDataset.addValue(rest2, "Burger King", "RESTAURANT");
        profitDataset.addValue(souv, "Souvenirs", "SHOP");
        profitDataset.addValue(rest + souv, "Intero", TICK);
        profitDataset.addValue(souv, "Ridotto", TICK);
        profitDataset.addValue(rest2, "Abbonamento", TICK);
        return profitDataset;
    }

    /**
     * This is a method introduced to make debugging and testing easier.
     * @return A phony chart based on the phony dataset above
     */
    public JFreeChart createPhonyChart() {
        return ChartFactory.createBarChart(
                    "DAILY PROFIT STATISTICS", 
                    "Category", "Income (€)", 
                    this.createPhonyDataset(), PlotOrientation.VERTICAL,
                    true, true, false);
    }
}

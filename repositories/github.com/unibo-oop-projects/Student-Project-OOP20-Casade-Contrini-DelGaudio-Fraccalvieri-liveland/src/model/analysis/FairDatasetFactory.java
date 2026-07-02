package model.analysis;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import controller.EnvironmentControllerImpl;

public class FairDatasetFactory extends AnalysisDatasetFactory {

    private final EnvironmentControllerImpl controller;
    public FairDatasetFactory(final EnvironmentControllerImpl controller) {
        this.controller = controller;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final PieDataset createDataset() {
        final DefaultPieDataset dataset = new DefaultPieDataset();
        this.controller.getFairList().forEach(f -> {
            dataset.setValue(f.getName(), f.getTotPeople()); });
        return dataset;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final JFreeChart createChart() {
        if (this.controller.getFairList().size() == 0) {
            return null;
        }
        return ChartFactory.createPieChart(
                "FAIR LIKING STATISTICS",
                this.createDataset(),
                true,
                true, 
                false);
    }

    /**
     * This is a method introduced to make debugging and testing easier.
     * @return  A phony PieDataset showing fixed data about fair liking
     */
    public PieDataset createPhonyDataset() {
        final int katun = 45;
        final int bruco = 38;
        final int raptor = 60;
        final DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("katun", katun);
        dataset.setValue("bruco mela", bruco);
        dataset.setValue("raptor", raptor);
        return dataset;
    }

    /**
     * This is a method introduced to make debugging and testing easier.
     * @return A phony chart based on the phony dataset above
     */
    public JFreeChart createPhonyChart() {
        return ChartFactory.createPieChart(
                "FAIR LIKING STATISTICS",
                this.createPhonyDataset(),
                true,
                true,
                false);
    }

}

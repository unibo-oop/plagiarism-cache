package model.analysis.save;

import java.io.File;
import java.io.IOException;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

import controller.AnalysisControllerImpl;

public final class ChartImgBuilder implements ChartImg {

    private static final String HOME = System.getProperty("user.home");
    private static final String SEPARATOR = System.getProperty("file.separator");
    private static final int WIDTH = 640;
    private static final int HEIGHT = 480;
    private final JFreeChart fairChart;
    private final JFreeChart profitChart;

    public ChartImgBuilder(final AnalysisControllerImpl controller) {
        this.fairChart = controller.getFairChart();
        this.profitChart = controller.getProfitChart();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public File profitChartImg() throws IOException {
        final File profitBarChart = new File(HOME + SEPARATOR + "LiveLand_ProfitBarChart.jpeg");
        ChartUtilities.saveChartAsJPEG(profitBarChart, this.profitChart, WIDTH, HEIGHT);
        return profitBarChart;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public File fairChartImg() throws IOException {
        final File fairPieChart = new File(HOME + SEPARATOR + "LiveLand_FairPieChart.jpeg"); 
        ChartUtilities.saveChartAsJPEG(fairPieChart, this.fairChart, WIDTH, HEIGHT);
        return fairPieChart;
    }


}

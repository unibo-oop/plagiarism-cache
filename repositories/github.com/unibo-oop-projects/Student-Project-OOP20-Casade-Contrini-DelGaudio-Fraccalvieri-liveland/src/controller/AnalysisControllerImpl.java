package controller;

import org.jfree.chart.JFreeChart;

import model.analysis.FairDatasetFactory;
import model.analysis.ProfitDatasetFactory;
import view.analysis.AnalysisDialog;

public class AnalysisControllerImpl implements AnalysisController {

    private final FairDatasetFactory fairModel;
    private final ProfitDatasetFactory profitModel;

    public AnalysisControllerImpl(final EnvironmentControllerImpl envController) {
        this.fairModel = new FairDatasetFactory(envController);
        this.profitModel = new ProfitDatasetFactory(envController);
        new AnalysisDialog(this, envController);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final JFreeChart getFairChart() {
        return this.fairModel.createChart();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final JFreeChart getProfitChart() {
        return this.profitModel.createChart();
    }

    /**
     * @return a phony fair chart built to make debug and testing during
     *  development easier
     */
    public final JFreeChart getPhonyFairChart() {
        return this.fairModel.createPhonyChart();
    }

    /**
     * @return a phony profit chart built to make debug and testing during
     *  development easier
     */
    public final JFreeChart getPhonyProfitChart() {
        return this.profitModel.createPhonyChart();
    }

}

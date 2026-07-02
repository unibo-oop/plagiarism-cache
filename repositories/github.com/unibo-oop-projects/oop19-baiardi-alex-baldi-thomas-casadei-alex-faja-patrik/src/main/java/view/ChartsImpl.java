package view;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import controller.DataGetter;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart.Series;

/**
 *
 */
public class ChartsImpl implements Initializable, Charts {

    @FXML
    private LineChart<String, Number> general;

    @FXML
    private LineChart<String, Number> daily;

    @FXML
    private LineChart<String, Number> rate;

    @FXML
    private LineChart<String, Number> birthDeath;

    /**
     * Method to initialize charts.
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        addSeries(general, List.of("Infected", "Virus Death", "Healthy", "Recovered"));
        addSeries(daily, List.of("Infected", "Virus Death", "Recovered"));
        addSeries(rate, List.of("Infectivity"));
        addSeries(birthDeath, List.of("Birth", "Death"));
        general.setCreateSymbols(false);
        daily.setCreateSymbols(false);
        rate.setCreateSymbols(false);
        birthDeath.setCreateSymbols(false);
    }

    /**
     * Private method to add the series to the charts.
     * @param linecharts
     * @param series
     */
    private void addSeries(final LineChart<String, Number> linecharts, final List<String> series) {
        for (int i = 0; i < series.size(); i++) {
            final Series<String, Number> serie = new Series<String, Number>();
            serie.setName(series.get(i));
            linecharts.getData().add(serie);
        }
    }

    /**
     * Method for update the charts.
     * @param updater
     */
    public void update(final DataGetter updater) {
        Platform.runLater(() -> {
            general.getData().get(0).getData().add(updater.getTotalInfect());
            general.getData().get(1).getData().add(updater.getTotalVirusDeath());
            general.getData().get(2).getData().add(updater.getHealthy());
            general.getData().get(3).getData().add(updater.getTotalRecovered());
            if (updater.drawDailyChart()) {
                 daily.getData().get(0).getData().add(updater.getDailyInfect());
                 daily.getData().get(1).getData().add(updater.getDailyVirusDeath());
                 daily.getData().get(2).getData().add(updater.getDailyRecovered());
            }

            rate.getData().get(0).getData().add(updater.getRateOfInfectivity());
            if (updater.birthDeathFlag()) {
                birthDeath.getData().get(0).getData().add(updater.getBirth());
                birthDeath.getData().get(1).getData().add(updater.getTotalDeath());
            }
        });
    }
}

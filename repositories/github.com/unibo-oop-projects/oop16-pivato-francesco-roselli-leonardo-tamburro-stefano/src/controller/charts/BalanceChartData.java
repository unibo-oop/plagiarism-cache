package controller.charts;

import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;

public class BalanceChartData extends GeneralChart {

        private final static String TITLE = "Balance Chart";

	public BalanceChartData() {
		this.title = TITLE;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Series<String, Double> getSeries(int year) {

	    Series<String, Double> s = new Series<>();
	    s.setName("Balance of " + year);

	    this.model.retreiveBalance()
                                        .getProfitByYear(year)
                                        .keySet()
                                        .stream()
                                        .sorted((m1, m2) -> m1.compareTo(m2))
                                        .forEach(k -> s.getData()
                                                                .add(new Data<>(
                                                                                k.toString(),
                                                                                this.model.retreiveBalance().getProfitByYear(year).get(k))));
	    return s;
	}
}

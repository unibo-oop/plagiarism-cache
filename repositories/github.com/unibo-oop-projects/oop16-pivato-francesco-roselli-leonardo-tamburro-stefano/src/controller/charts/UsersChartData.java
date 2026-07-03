package controller.charts;

import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;

public class UsersChartData extends GeneralChart {

    private final static String TITLE = "Users Chart";

	public UsersChartData() {
		this.title = TITLE;
	}

    @Override
    @SuppressWarnings("unchecked")
    public Series<String, Integer> getSeries(int year) {

        Series<String, Integer> s = new Series<>();
        s.setName("User number of " + year);

        this.model.retreiveBalance()
                                    .getInscriptionsByYear(year)
                                    .keySet()
                                    .stream()
                                    .sorted((m1, m2) -> m1.compareTo(m2))
                                    .forEach(k -> s.getData()
                                                            .add(new Data<>(
                                                                            k.toString(),
                                                                            this.model.retreiveBalance().getInscriptionsByYear(year).get(k))));
        return s;
   }
}

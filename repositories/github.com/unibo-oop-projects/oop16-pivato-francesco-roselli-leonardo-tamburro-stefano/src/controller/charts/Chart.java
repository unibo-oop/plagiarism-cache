package controller.charts;

import java.util.List;

import javafx.scene.chart.XYChart.Series;

public interface Chart {

	String getTitle();

	List<Integer> getAvailablePeriods();

	<X, Y> Series<X, Y> getSeries(int year);
}

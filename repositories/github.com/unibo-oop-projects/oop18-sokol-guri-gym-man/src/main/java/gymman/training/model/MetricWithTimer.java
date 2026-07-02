package gymman.training.model;

public class MetricWithTimer implements Metric{

	private double minutes;

	MetricWithTimer(double min){
		this.minutes = min;
	}

	public double getMinutes() {
		return minutes;
	}

	public void setMinutes(double minutes) {
		this.minutes = minutes;
	}

}


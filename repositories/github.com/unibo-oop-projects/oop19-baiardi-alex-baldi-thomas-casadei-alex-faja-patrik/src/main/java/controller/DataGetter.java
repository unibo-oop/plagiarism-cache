package controller;

import javafx.scene.chart.XYChart.Data;

/**
 * Class that contains data to add to the charts.
 */
public class DataGetter {

    private static final int DAY = 24;
    private static final int BD = 5;
    private final Data<Number, Number> totalInfect;
    private final Data<Number, Number> totalDeath;
    private final Data<Number, Number> totalBirth;
    private final Data<Number, Number> totalVirusDeath;
    private final Data<Number, Number> totalRecovered;
    private final Data<Number, Number> totalHealthy;
    private final Data<Number, Number> rate;
    private final Data<Number, Number> dailyInfect;
    private final Data<Number, Number> dailyVirusDeath;
    private final Data<Number, Number> dailyRecovered;

    /**
     * Constructor of the class.
     * 
     * @param tInfect
     * @param tDeath
     * @param tBirth
     * @param tVisurDeath
     * @param tRecovered
     * @param tHealthy
     * @param dInfect
     * @param dVirusDeath
     * @param dRecovered
     * @param trate
     */
    public DataGetter(final Data<Number, Number> tInfect, final Data<Number, Number> tDeath,
            final Data<Number, Number> tBirth, final Data<Number, Number> trate, final Data<Number, Number> tVisurDeath,
            final Data<Number, Number> tRecovered, final Data<Number, Number> tHealthy,
            final Data<Number, Number> dInfect, final Data<Number, Number> dVirusDeath,
            final Data<Number, Number> dRecovered) {
        this.totalInfect = tInfect;
        this.totalDeath = tDeath;
        this.totalBirth = tBirth;
        this.totalVirusDeath = tVisurDeath;
        this.totalRecovered = tRecovered;
        this.totalHealthy = tHealthy;
        this.dailyInfect = dInfect;
        this.dailyVirusDeath = dVirusDeath;
        this.dailyRecovered = dRecovered;
        this.rate = trate;
    }

    /**
     * Method that returns the data to be added to the series of the chart formed by
     * totalBirth and time.
     * 
     * @return totalBirth
     */
    public Data<String, Number> getBirth() {
        return new Data<String, Number>(this.totalBirth.getXValue().toString(), this.totalBirth.getYValue().intValue());
    }

    /**
     * Method that returns the data to be added to the series of the chart formed by
     * rate of infectivity and time.
     * 
     * @return rateOfInfectivity
     */
    public Data<String, Number> getRateOfInfectivity() {
        return new Data<String, Number>(this.rate.getXValue().toString(), this.rate.getYValue());
    }

    /**
     * Method that returns the data to be added to the series of the chart formed by
     * totalinfect and time.
     * 
     * @return totalInfect
     */
    public Data<String, Number> getTotalInfect() {
        return new Data<String, Number>(this.totalInfect.getXValue().toString(), this.totalInfect.getYValue());
    }

    /**
     * Method that returns the data to be added to the series of the chart formed by
     * DailyInfect and time.
     * 
     * @return dailyInfect
     */
    public Data<String, Number> getDailyInfect() {
        return new Data<String, Number>(this.dailyInfect.getXValue().toString(), this.dailyInfect.getYValue());
    }

    /**
     * Method that returns the data to be added to the series of the chart formed by
     * totalDeath and time.
     * 
     * @return totalDeath
     */
    public Data<String, Number> getTotalDeath() {
        return new Data<String, Number>(this.totalDeath.getXValue().toString(), this.totalDeath.getYValue().intValue());
    }

    /**
     * Method that returns the data to be added to the series of the chart formed by
     * totalVirusDeath and time.
     * 
     * @return totalVirusDeath
     */
    public Data<String, Number> getTotalVirusDeath() {
        return new Data<String, Number>(this.totalVirusDeath.getXValue().toString(), this.totalVirusDeath.getYValue());
    }

    /**
     * Method that returns the data to be added to the series of the chart formed by
     * dailyVirusDeath and time.
     * 
     * @return dailyVirusDeath
     */
    public Data<String, Number> getDailyVirusDeath() {
        return new Data<String, Number>(this.dailyVirusDeath.getXValue().toString(), this.dailyVirusDeath.getYValue());
    }

    /**
     * Method that returns the data to be added to the series of the chart formed by
     * totalrecovered and time.
     * 
     * @return totalRecovered
     */
    public Data<String, Number> getTotalRecovered() {
        return new Data<String, Number>(this.totalRecovered.getXValue().toString(), this.totalRecovered.getYValue());
    }

    /**
     * Method that returns the data to be added to the series of the chart formed by
     * dailyRecovered and time.
     * 
     * @return dailyrecovered
     */
    public Data<String, Number> getDailyRecovered() {
        return new Data<String, Number>(this.dailyRecovered.getXValue().toString(), this.dailyRecovered.getYValue());
    }

    /**
     * Method that returns the data to be added to the series of the chart formed by
     * dailyRecovered and time.
     * 
     * @return totalhealty
     */
    public Data<String, Number> getHealthy() {
        return new Data<String, Number>(this.totalHealthy.getXValue().toString(), this.totalHealthy.getYValue());
    }

    /**
     * Method to updating the chart correctly.
     * 
     * @return true if the chart needs updating. false otherwise.
     */
    public boolean birthDeathFlag() {
        return this.dailyInfect.getXValue().intValue() % BD == 0;
    }

    /**
     * Method to updating the chart correctly.
     * 
     * @return true if the chart needs updating. false otherwise.
     */
    public boolean drawDailyChart() {
        return this.dailyInfect.getXValue().intValue() % DAY == 0;
    }
}

package controller;

import javafx.scene.chart.XYChart.Data;

/**
 * 
 */
public class ChartsManagerImpl implements ChartsManager {

    private static final int DAILY = 24;
    private static final int PERCENTUAL = 100;
    private int totalHealthyPerson;
    private final Data<Number, Number> totalInfect = new Data<Number, Number>(0, 0);
    private final Data<Number, Number> totalDeath = new Data<Number, Number>(0, 0);
    private final Data<Number, Number> totalBirth = new Data<Number, Number>(0, 0);
    private final Data<Number, Number> totalVirusDeath = new Data<Number, Number>(0, 0);
    private final Data<Number, Number> totalRecovered = new Data<Number, Number>(0, 0);
    private final Data<Number, Number> totalHealthy = new Data<Number, Number>(0, 0);
    private final Data<Number, Number> rateOfInfectivity = new Data<Number, Number>(0, 0);

    private final Data<Number, Number> dailyInfect = new Data<Number, Number>(0, 0);
    private final Data<Number, Number> dailyVirusDeath = new Data<Number, Number>(0, 0);
    private final Data<Number, Number> dailyRecovered = new Data<Number, Number>(0, 0);

    /**
     * Constructor method.
     * 
     * @param initalHealthy initial number of people
     */
    public ChartsManagerImpl(final int initalHealthy) {
        this.totalHealthyPerson = initalHealthy;
    }

    /**
     *
     */
    @Override
    public void addInfect(final int infect, final int time) {
        reset(this.dailyInfect);
        this.dailyInfect.setXValue(time);
        this.dailyInfect.setYValue(this.dailyInfect.getYValue().intValue() + infect);
        this.totalInfect.setXValue(time);
        this.totalInfect.setYValue(this.totalInfect.getYValue().intValue() + infect);
        this.totalHealthyPerson = this.totalHealthyPerson - infect;
    }

    /**
     *
     */
    @Override
    public void addDeath(final int death, final int time) {
        this.totalDeath.setXValue(time);
        this.totalDeath.setYValue(this.totalDeath.getYValue().intValue() + death);
        this.totalHealthyPerson = this.totalHealthyPerson - death;
    }

    /**
     *
     */
    @Override
    public void addBirth(final int birth, final int time) {
        this.totalBirth.setXValue(time);
        this.totalBirth.setYValue(this.totalBirth.getYValue().intValue() + birth);
        this.totalHealthyPerson = this.totalHealthyPerson + birth;
    }

    /**
     *
     */
    @Override
    public void addVirusDeath(final int virusDeath, final int time) {
        reset(this.dailyVirusDeath);
        this.dailyVirusDeath.setXValue(time);
        this.dailyVirusDeath.setYValue(this.dailyVirusDeath.getYValue().intValue() + virusDeath);
        this.totalVirusDeath.setXValue(time);
        this.totalVirusDeath.setYValue(this.totalVirusDeath.getYValue().intValue() + virusDeath);
        this.totalInfect.setYValue(this.totalInfect.getYValue().intValue() - virusDeath);
    }

    /**
     *
     */
    @Override
    public void addRecovered(final int recovered, final int time) {
        reset(this.dailyRecovered);
        this.dailyRecovered.setXValue(time);
        this.dailyRecovered.setYValue(this.dailyRecovered.getYValue().intValue() + recovered);
        this.totalRecovered.setXValue(time);
        this.totalRecovered.setYValue(this.totalRecovered.getYValue().intValue() + recovered);
        this.totalHealthyPerson = this.totalHealthyPerson + recovered;
        this.totalInfect.setYValue(this.totalInfect.getYValue().intValue() - recovered);
    }

    /**
     * 
     */
    @Override
    public void addHealthy(final int time) {
        this.totalHealthy.setXValue(time);
        this.totalHealthy.setYValue(totalHealthyPerson);
    }

    /**
     *
     */
    @Override
    public void addRateInfectivity(final int people, final int time) {
        float rate = this.totalInfect.getYValue().floatValue() / people;
        rate = rate * PERCENTUAL;
        this.rateOfInfectivity.setYValue(rate);
        this.rateOfInfectivity.setXValue(time);
    }

    /**
     * 
     * @param data
     */
    private void reset(final Data<Number, Number> data) {
        if (data.getXValue().intValue() % DAILY == 1) {
           data.setYValue(0);
        }
    }

    /**
     * @return DataGetter
     */
    public DataGetter getData() {
        return new DataGetter(this.totalInfect, this.totalDeath, this.totalBirth, this.rateOfInfectivity,
                this.totalVirusDeath, this.totalRecovered, this.totalHealthy, this.dailyInfect, this.dailyVirusDeath,
                this.dailyRecovered);
    }

}

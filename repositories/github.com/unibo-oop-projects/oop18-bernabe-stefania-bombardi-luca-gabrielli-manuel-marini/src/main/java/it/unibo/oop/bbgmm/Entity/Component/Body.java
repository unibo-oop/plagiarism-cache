package it.unibo.oop.bbgmm.Entity.Component;

import it.unibo.oop.bbgmm.Utilities.Pair;

public class Body extends AbstractEntityComponent implements EntityBody{

    private Pair<Integer, Integer> position;
    private Pair<Double, Double> dimension;
    private double velocity;

    Body(Pair<Integer, Integer> position, Pair<Double, Double> dimension, double velocity) {
        super();
        this.position = position;
        this.dimension = dimension;
        this.velocity = velocity;
    }

    @Override
    public void update(double delta) {

    }

    @Override
    public Pair<Integer, Integer> getPosition() {
        return this.position;
    }

    @Override
    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    @Override
    public double getVelocity() {
        return this.velocity;
    }

    @Override
    public Pair<Double, Double> getDimension() {
        return this.dimension;
    }
}

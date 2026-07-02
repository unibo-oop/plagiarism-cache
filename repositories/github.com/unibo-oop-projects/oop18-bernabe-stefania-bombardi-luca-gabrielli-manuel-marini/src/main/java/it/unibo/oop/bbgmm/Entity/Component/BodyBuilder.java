package it.unibo.oop.bbgmm.Entity.Component;

import it.unibo.oop.bbgmm.Utilities.Pair;

public class BodyBuilder {
    private double velocity;
    private Pair<Double, Double> dimension;
    private Pair<Integer, Integer> position;

    public BodyBuilder setVelocity(final double velocity) {
        this.velocity = velocity;
        return this;
    }

    public BodyBuilder setDimension(final Pair<Double, Double> dimension) {
        this.dimension = dimension;
        return this;
    }

    public BodyBuilder setPosition(final Pair<Integer, Integer> position) {
        this.position = position;
        return this;
    }

    public Body build() {
        Body body = new Body(this.position, this.dimension, this.velocity);
        return body;
    }

}

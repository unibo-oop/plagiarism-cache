package model.armory.munition;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import model.bounding_box.RectBoundingBox;

/**
 * {@inheritDoc}
 */
public class FactoryMunition {

    /**
     * {@inheritDoc}
     */
    public Munition createParabellum(final Pair<Double, Double> initPos) {
        final int damage = 400;
        final int width = 3;
        final int velocity = 900;
        return new Parabellum(damage,
                velocity,
                width,
                new MutablePair<>(initPos.getLeft(), initPos.getRight()),
                new RectBoundingBox(Pair.of(initPos.getLeft(), initPos.getRight() + width),
                        Pair.of(initPos.getLeft() + width, initPos.getRight())));
    }

}

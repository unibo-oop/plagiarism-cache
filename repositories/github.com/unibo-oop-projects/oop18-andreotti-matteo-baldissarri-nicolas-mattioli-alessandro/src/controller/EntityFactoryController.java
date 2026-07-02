package controller;

import java.util.List;

import model.bonus.Bonus;
import model.bonus.LifeBonus;
import model.bonus.ScoreBonus;
import model.enemy.Hawk;
import model.enemy.Vase;
import model.entities.Character;
import model.entities.Environment;
import model.entities.Position;
import model.entities.Stuntman;
import model.palace.Palace;
import model.palace.PalaceImpl;
import utils.Pair;

/**
 * Create the game entities.
 */
public final class EntityFactoryController implements EntityFactoryObserver {

    @Override
    public Character createStuntman(final Position initPos, final Pair<Double, Double> shift,
            final Environment environment) {
        return new Stuntman(initPos, shift, environment);
    }

    @Override
    public Palace createPalace(final List<List<Position>> positions) {
        return new PalaceImpl(positions);
    }

    @Override
    public Hawk createHawk(final Pair<Double, Double> dimension, final Environment environment) {
        return new Hawk(dimension, environment);
    }

    @Override
    public Vase createVase(final Pair<Double, Double> dimension, final Environment environment) {
        return new Vase(dimension, environment);
    }

    @Override
    public Bonus createLifeBonus(final Pair<Double, Double> size, final Environment environment) {
        return new LifeBonus(size, environment);
    }

    @Override
    public Bonus createScoreBonus(final Pair<Double, Double> size, final Environment environment) {
        return new ScoreBonus(size, environment);
    }
}

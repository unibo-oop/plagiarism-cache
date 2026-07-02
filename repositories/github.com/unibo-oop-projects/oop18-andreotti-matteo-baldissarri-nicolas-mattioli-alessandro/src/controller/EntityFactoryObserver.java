package controller;

import java.util.List;

import model.bonus.Bonus;
import model.enemy.Hawk;
import model.enemy.Vase;
import model.entities.Environment;
import model.entities.Position;
import model.entities.Character;
import model.palace.Palace;
import utils.Pair;

/**
 * Models the entities creation.
 */
public interface EntityFactoryObserver {

    /**
     * Create the Climber.
     * 
     * @param initPos     The initial position of the Stuntman on the game map
     * @param shift       The displacement size of the Stuntman
     * @param environment The stuntman's environment
     * @return The Stuntman
     */
    Character createStuntman(Position initPos, Pair<Double, Double> shift, Environment environment);

    /**
     * Create the Palace.
     * 
     * @param positions The list of window positions
     * @return The Palace
     */
    Palace createPalace(List<List<Position>> positions);

    /**
     * Create the Hawk.
     * 
     * @param dimension   The dimension of Hawk.
     * @param environment The hawk's environment
     * @return The Hawk
     */
    Hawk createHawk(Pair<Double, Double> dimension, Environment environment);

    /**
     * Create the Vase.
     * 
     * @param dimension   The dimension of Vase.
     * @param environment The vase's environment
     * @return The Vase
     */
    Vase createVase(Pair<Double, Double> dimension, Environment environment);

    /**
     * Create the LifeBonus.
     * 
     * @param size The size of the LifeBonus.
     * @return The LifeBonus
     * @param environment The environment of LifeBonus
     */
    Bonus createLifeBonus(Pair<Double, Double> size, Environment environment);

    /**
     * Create the ScoreBonus.
     * 
     * @param size The size of the ScoreBonus.
     * @return The ScoreBonus
     * @param environment The environment of ScoreBonus
     */
    Bonus createScoreBonus(Pair<Double, Double> size, Environment environment);

}

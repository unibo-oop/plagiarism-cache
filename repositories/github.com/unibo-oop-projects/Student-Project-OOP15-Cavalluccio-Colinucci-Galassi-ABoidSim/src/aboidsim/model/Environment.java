package aboidsim.model;

import java.util.Set;

import aboidsim.util.Pair;
import aboidsim.util.Vector;

/**
 * Interface of the whole simulation.
 *
 */
public interface Environment {

    /**
     * Add a boid to the environment.
     *
     * @param pos
     *            New boid position.
     * @param liv
     *            New boid level.
     */
    void createBoid(final Vector pos, final int liv);

    /**
     * Destroy a boid in this position (if present).
     *
     * @param pos
     *            of designated boid.
     */
    void destroyBoid(final Vector pos);

    /**
     * check all the near boids in influence radius.
     */
    void checkNearBoids();

    /**
     * Rule management.
     *
     * @param ruleId
     *            id rule.
     */
    void toggleRule(final int ruleId);
    
    /**
     * 
     * @return active rules
     */
    RuleSet getActiveRuleSet();
    
    /**
     * Set simulation dimension.
     *
     * @param dimension
     *            simulation dimension.
     */
    void setScreenDimension(final Pair<Integer, Integer> dimension);

    /**
     *
     * @return all the entities of the environment with position and image path.
     */
    Set<Pair<Pair<Vector, Double>, Integer>> getSimulationComponents();

    /**
     * Getter for the environment.
     *
     * @return The whole environment
     */
    Set<Boid> getEnvironment();
    
    /**
     * Set a new environment.
     * 
     * @param idEnv
     * 		Environment id
     */
    void loadDefaultEnvironment(final int idEnv);

    /**
     * This method updates the state of the simulation.
     */
    void updateEnvironment();
}

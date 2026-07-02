package it.unibo.model.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.scene.input.KeyCode;
import it.unibo.common.Pair;
import it.unibo.controller.impl.GameController;
import it.unibo.model.api.Entity;
import it.unibo.model.api.GamePerformance;
import it.unibo.utilities.EntityType;
import java.util.stream.Collectors;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Class that manages the game performance of the play.
 */
public class GamePerformanceImpl implements GamePerformance {
    private final GameController gameController;
    private final Set<Entity> entities = new HashSet<>();
    private final List<KeyCode> inputs = new ArrayList<>();

    /**
     * Constructor for the GamePerformanceImpl.
     * 
     * @param gameController the game controller.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "We need the original object")
    public GamePerformanceImpl(final GameController gameController) {
        this.gameController = gameController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize() {
        entities.add(this.gameController.getRalphController().getRalph());
        entities.add(this.gameController.getFelixController().getFelix());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Entity> getEntity() {
        return new HashSet<>(this.entities);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<KeyCode> getInputs() {
        return new ArrayList<>(this.inputs);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeEntity(final Entity e) {
        this.entities.remove(e);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addKey(final KeyCode keyCode) {
        this.inputs.add(keyCode);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeBrick(final Pair<Double, Double> pos) {
        entities.remove(entities.stream()
                .filter(e -> e.getEntityType() == EntityType.BRICK && e.getPosition().equals(pos))
                .findFirst()
                .orElse(null));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addEntity(final Entity e) {
        this.entities.add(e);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Entity> getPowerUpsPresent() {
        return this.entities.stream()
                .filter(entity -> entity.getEntityType() == EntityType.BIRD
                        || entity.getEntityType() == EntityType.CAKE)
                .toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Entity> getWindows() {
        return this.entities.stream()
                .filter(entity -> entity.getEntityType() == EntityType.WINDOW).toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLevel() {
        return this.gameController.getLevel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Entity> getBricks() {
        return this.entities.stream()
                .filter(entity -> entity.getEntityType() == EntityType.BRICK).collect(Collectors.toSet());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Entity> getBirds() {
        return this.entities.stream()
                .filter(entity -> entity.getEntityType() == EntityType.BIRD)
                .collect(Collectors.toSet());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeBirds(final Pair<Double, Double> pos) {
        entities.remove(entities.stream()
                .filter(e -> e.getEntityType() == EntityType.BIRD && e.getPosition().equals(pos))
                .findFirst()
                .orElse(null));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Entity> getCakes() {
        return this.entities.stream()
                .filter(entity -> entity.getEntityType() == EntityType.CAKE)
                .collect(Collectors.toSet());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeCakes(final Pair<Double, Double> pos) {
        entities.remove(entities.stream()
                .filter(e -> e.getEntityType() == EntityType.CAKE && e.getPosition().equals(pos))
                .findFirst()
                .orElse(null));
    }

}

package it.tbt.controller.modelmanager;

import java.util.List;

import it.tbt.model.entities.characters.Ally;
import it.tbt.model.entities.characters.Enemy;
import it.tbt.model.fight.api.FightModel;

/**
 * Implementation of the {@link FightState} interface.
 * This class provides the implementation for managing the state of a fight in
 * the game.
 */
public final class FightStateImpl implements FightState {

    private final FightModel model;

    /**
     * Constructs a new instance of the {@link FightStateImpl} class with the
     * specified {@link FightModel}.
     *
     * @param model the {@link FightModel} representing the fight
     */
    public FightStateImpl(final FightModel model) {
        if (model == null) {
            throw new IllegalArgumentException(
                    "è stato passato un argomento non lecito alla creazione di FightStateImpl");
        }
        this.model = model;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSelectedTargetIndex() {
        return model.getSelectedTargetIndex();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handlePreviousTarget() {
        model.selectPreviousTarget();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleNextTarget() {
        model.selectNextTarget();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handlePerformAction() {
        model.performSelectedAction();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleCycleAction(final boolean cycleUp) {
        if (cycleUp) {
            if (model.isUsingSkill()) {
                model.selectAction(false, false, true);
            } else if (model.isUsingPotion()) {
                model.selectAction(false, false, false);
            } else if (model.isUsingAntidote()) {
                model.selectAction(false, true, false);
            } else {
                model.selectAction(true, false, false);
            }
        } else {
            if (model.isUsingSkill()) {
                model.selectAction(false, false, false);
            } else if (model.isUsingPotion()) {
                model.selectAction(false, false, true);
            } else if (model.isUsingAntidote()) {
                model.selectAction(true, false, false);
            } else {
                model.selectAction(false, true, false);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Ally> getAllies() {
        return model.getAllies();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Enemy> getEnemies() {
        return model.getEnemies();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isUsingSkill() {
        return model.isUsingSkill();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isUsingAntidote() {
        return model.isUsingAntidote();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isUsingPotion() {
        return model.isUsingPotion();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Ally getCurrentAlly() {
        return model.getCurrentAlly();
    }

}

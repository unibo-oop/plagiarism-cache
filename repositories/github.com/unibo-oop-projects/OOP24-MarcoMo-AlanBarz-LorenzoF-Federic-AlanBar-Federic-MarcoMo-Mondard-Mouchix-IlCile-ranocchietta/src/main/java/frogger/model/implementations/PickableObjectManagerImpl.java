package frogger.model.implementations;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import frogger.controller.Controller;
import frogger.controller.GameControllerImpl;
import frogger.model.interfaces.PickableObjectManager;
import frogger.model.interfaces.PowerUp;

/**
 * Implementation of the {@link PickableObjectManager} interface responsible for managing pickable objects 
 * and power-ups in the game.
 * <p>
 * This class maintains a collection of active power-ups, handles their activation and deactivation,
 * and manages dependencies required by pickable objects. It also provides methods to check the status
 * of power-ups and to retrieve the currently active power-ups.
 * </p>
 *
 * <p>
 * Key responsibilities:
 * <ul>
 *   <li>Adding pickable objects and injecting their required dependencies.</li>
 *   <li>Activating and deactivating power-ups, ensuring only one power-up of each type is active at a time.</li>
 *   <li>Updating power-up fields when a new power-up replaces an old one of the same type.</li>
 *   <li>Providing access to the list of currently active power-ups.</li>
 * </ul>
 * </p>
 *
 * @see frogger.model.interfaces.PickableObjectManager
 * @see frogger.model.interfaces.PowerUp
 * @see frogger.model.implementations.PickableObjectImpl
 * @see frogger.model.implementations.GameImpl
 */
public class PickableObjectManagerImpl implements PickableObjectManager {

    private final Map<PowerUpType, PowerUp> activePowerUps = new EnumMap<>(PowerUpType.class);
    private final GameImpl game;
    private Controller controller;

    /**
     * Constructs a new PickableObjectManagerImpl with the specified game instance.
     *
     * @param game the game instance to which this manager belongs
     */
    public PickableObjectManagerImpl(final GameImpl game) {
        this.game = game;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void checkPowerUps() {
        final var keysToRemove = new ArrayList<PowerUpType>();
        for (final var entry : activePowerUps.entrySet()) {
            final PowerUp powerUp = entry.getValue();
            if (powerUp != null && !powerUp.isActive()) {
                keysToRemove.add(entry.getKey());
            }
        }
        for (final PowerUpType key : keysToRemove) {
            activePowerUps.remove(key);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPickableObject(final PickableObjectImpl x) {
        if (x != null) {
            switch (x.getRequiredDependencies()) {
                case PLAYER -> x.setRelatedEntity(game.getPlayer());
                case OBSTACLE -> x.setRelatedEntity(game.getObstacles());
                case GAME_CONTROLLER -> x.setRelatedEntity(controller);
                default -> throw new IllegalArgumentException("Unsupported dependency type: " + x.getRequiredDependencies());
            }

            if (x instanceof PowerUp powerUp) {
                final PowerUpType type = powerUp.getPowerUpType();
                final PowerUp previous = activePowerUps.get(type);
                if (previous != null) {
                    previous.deactivate();
                }
                activePowerUps.put(type, powerUp);
                powerUp.onPick(); 
                updatefieldsPowerUp(powerUp, previous);
            } else {
                x.onPick();
            }
        }
    }

    /**
     * Updates the fields of the current power-up based on the last power-up.
     * This method is called when a new power-up replaces an existing one of the same type.
     *
     * @param currentPowerUp the newly activated power-up
     * @param lastPowerUp    the previously active power-up of the same type
     */
    private void updatefieldsPowerUp(final PowerUp currentPowerUp, final PowerUp lastPowerUp) {
        if (currentPowerUp != null && lastPowerUp != null
         && currentPowerUp instanceof FreezePowerUp cPowerUp && lastPowerUp instanceof FreezePowerUp lPowerUp) {
                cPowerUp.setEntitiesSpeed(lPowerUp.getEntitiesSpeed());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PowerUp> getActivePowerUps() {
        return List.copyOf(activePowerUps.values());
    }

    /**
     * Sets the controller for this manager.
     * This method is used to inject the game controller into the manager,
     * allowing it to interact with the game state and respond to player actions.
     *
     * @param controller the controller to be set
     */
    public void setController(final Controller controller) {
        if (controller instanceof GameControllerImpl gameController) {
            this.controller = gameController;
        }
    }
}

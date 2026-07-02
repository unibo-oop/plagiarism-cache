package frogger.model.implementations;

import java.util.Random;

import frogger.common.Constants;
import frogger.common.Pair;
import frogger.common.Position;
import frogger.controller.GameControllerImpl;

/**
 * Represents a collectible coin object in the game.
 * <p>
 * A Coin has a randomly assigned value (either high or low) and displays a different image
 * depending on its value. When picked up, it increases the player's coin count in the game controller.
 * </p>
 * <ul>
 *   <li>If the coin value is {@link Constants#COIN_HIGH_VALUE}, a "big" coin image is used.</li>
 *   <li>If the coin value is {@link Constants#COIN_LOW_VALUE}, a "small" coin image is used.</li>
 * </ul>
 * <p>
 * The Coin requires a {@link PickableObjectDependency#GAME_CONTROLLER} dependency to update the coin count.
 * </p>
 */
public class Coin extends PickableObjectImpl {

    private final int coinValue;
    private final Random random = new Random();

    /**
     * Constructs a new Coin at the specified position with the given dimensions.
     * The coin value is randomly determined, and the image is set based on the value.
     *
     * @param pos        the position of the coin
     * @param dimension  the dimensions of the coin
     */
    public Coin(final Position pos, final Pair dimension) {
        super(pos, dimension);
        coinValue = randomCoinValue();
        if (coinValue == Constants.COIN_HIGH_VALUE) {
            super.setImage("coinPowerUpBig.png");
        } else {
            super.setImage("coinPowerUpSmall.png");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onPick() {
        if (super.getRelatedEntity() instanceof GameControllerImpl game) {
            game.setCoins(game.getCoins() + coinValue);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PickableObjectDependency getRequiredDependencies() {
        return PickableObjectDependency.GAME_CONTROLLER;
    }

    /**
     * Generates and return a random value for the coin.
     *
     * @return the coin value
     */
    private int randomCoinValue() {
        return random.nextInt(4) == 0 ? Constants.COIN_HIGH_VALUE : Constants.COIN_LOW_VALUE;
    }
}

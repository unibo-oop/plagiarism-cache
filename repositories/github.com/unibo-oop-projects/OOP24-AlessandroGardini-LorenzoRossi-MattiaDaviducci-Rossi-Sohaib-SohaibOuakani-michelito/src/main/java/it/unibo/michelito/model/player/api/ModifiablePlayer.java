package it.unibo.michelito.model.player.api;

import it.unibo.michelito.model.bomb.api.BombType;

/**
 * Interface that allows modification of the {@link Player} without giving the ability to update the {@link Player} in the Model.
 * This interface provides methods to modify player attributes such as bomb limit, speed, and bomb type.
 */
public interface ModifiablePlayer {
    /**
     * Increases the number of bombs that can be placed by the {@link ModifiablePlayer}.
     *
     * @param amount the number of additional bombs the {@link ModifiablePlayer} can place
     */
    void setBombLimit(int amount);

    /**
     * Increases the speed of the {@link ModifiablePlayer}.
     *
     * @param speedIncrease the amount by which the {@link ModifiablePlayer}'s speed will be increased
     */
    void setSpeed(double speedIncrease);

    /**
     * Returns the current speed of the {@link ModifiablePlayer}.
     *
     * @return the current speed of the {@link ModifiablePlayer}
     */
    double getSpeed();

    /**
     * Returns the current limit on the number of bombs that the {@link ModifiablePlayer} can place.
     *
     * @return the bomb placement limit of the {@link ModifiablePlayer}
     */
    int getBombLimit();

    /**
     * Changes the {@link BombType} of the bombs deployed by the {@link Player}.
     *
     * @param type the new {@link BombType} to be used by the {@link Player}
     */
    void setBombType(BombType type);

    /**
     * Returns the {@link BombType} of the next bomb to be placed by the {@link ModifiablePlayer}.
     *
     * @return the current {@link BombType} of the next bomb
     */
    BombType getBombType();
}

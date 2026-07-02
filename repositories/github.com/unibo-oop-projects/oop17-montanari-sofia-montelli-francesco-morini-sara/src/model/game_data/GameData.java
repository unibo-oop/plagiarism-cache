package model.game_data;

import model.battleground.BattleGround;
import model.player.Player;

/**
 * Add interaction method to {@link ImmutableGameData}.
 */
public interface GameData extends ImmutableGameData {
    /**
     * Sets the player.
     * @param player is the {@link Player} to set.
     * @throws IllegalStateException if the player has already been set.
     */
    void setPlayer(Player player) throws IllegalStateException;
    /**
     * Sets the player's password.
     * @param password is the password to set.
     * @throws IllegalStateException if the password has already been set.
     */
    void setPassword(String password) throws IllegalStateException;

    /**
     * Sets the player's {@link BattleGround}.
     * @param battleGround is the {@link BattleGround} to set.
     * @throws IllegalStateException if the battleground has already been set.
     */
    void setBattleGround(BattleGround battleGround) throws IllegalStateException;
}

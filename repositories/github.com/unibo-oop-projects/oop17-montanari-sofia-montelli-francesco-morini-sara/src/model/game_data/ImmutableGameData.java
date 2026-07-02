package model.game_data;

import java.util.Optional;

import model.battleground.BattleGround;
import model.player.Player;

/**
 * Getter for the {@link GameData}.
 */

public interface ImmutableGameData {
    /**
     * @return the {@link Player} data.
     */
    Optional<Player> getPlayer();
    /**
     * @return the {@link Player} password.
     */
    Optional<String> getPassword();
    /**
     * @return the {@link Player} battleground.
     */
    Optional<BattleGround> getBattleGround();
}

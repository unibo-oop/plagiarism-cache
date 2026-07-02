package model.game_data;

import java.util.Optional;

import model.battleground.BattleGround;
import model.player.Player;

/**
 * Implementation of {@link GameData}.
 */
public final class GameDataImpl implements GameData {

    /**
     * player of the game data.
     */
    private Optional<Player> player = Optional.empty();
    /**
     * password that the player set for the change of the view.
     */
    private Optional<String> passowrd = Optional.empty();
    /**
     * battle ground with the navy set by the player.
     */
    private Optional<BattleGround> battleGround = Optional.empty();
    /**
     * constructor.
     */
    public GameDataImpl() {
        player = Optional.empty();
        passowrd = Optional.empty();
        battleGround = Optional.empty();
    }
    @Override
    public void setPlayer(final Player player) {
        if (!this.player.isPresent()) {
            this.player = Optional.of(player);
        }
    }

    @Override
    public void setPassword(final String password) {
        if (!this.passowrd.isPresent()) {
            this.passowrd = Optional.of(password);
        }
    }

    @Override
    public void setBattleGround(final BattleGround battleGround) {
        if (!this.battleGround.isPresent()) {
            this.battleGround = Optional.of(battleGround);
        }
    }

    @Override
    public Optional<Player> getPlayer() {
        return player;
    }

    @Override
    public Optional<String> getPassword() {
        return passowrd;
    }

    @Override
    public Optional<BattleGround> getBattleGround() {
        return battleGround;
    }
}

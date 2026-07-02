package it.unibo.biscia.view.actors;

import it.unibo.biscia.core.Player;
import it.unibo.biscia.events.StateObserver;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * This Actor takes care of displaying {@link Player} informations. Infos are
 * displayed by 3 labels containing respectively {@link Player#getName()}
 * {@link Player#getLives()} and {@link Player#getPoints()}.
 *
 */
public class PlayerInfo extends Table {

    private final Label playerName;
    private final Label playerLives;
    private final Label playerPoints;

    public PlayerInfo(final Player player, final Skin skin) {
        this.playerName = new Label(player.getName(), skin);
        this.playerLives = new Label(String.valueOf(player.getLives()), skin);
        this.playerPoints = new Label(String.valueOf(player.getPoints()), skin);
        this.add(playerName);
        this.add(playerLives).expandX();
        this.add(playerPoints);
        this.debugAll();
        this.pack();
    }

    /**
     * Update labels info by the given {@link Player}. This method is usually called
     * by a {@link StateObserver#updatePlayer(Player)}
     * 
     * @see StateObserver
     * 
     * @param player The {@link Player} to update.
     */
    public final void reset(final Player player) {
        this.playerName.setText(player.getName());
        this.playerLives.setText(player.getLives());
        this.playerPoints.setText(player.getPoints());
    }

}

package powpaw.player.controller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.geometry.Point2D;
import powpaw.player.controller.api.PlayerController;
import powpaw.player.controller.api.PlayerObservable;
import powpaw.player.model.api.Player;
import powpaw.player.model.impl.PlayerImpl;
import powpaw.player.view.api.PlayerRender;
import powpaw.player.view.impl.PlayerRenderImpl;
import powpaw.world.controller.ScreenController;

/**
 * The {@code PlayerControllerImpl} class implements the
 * {@code PlayerController} interface and
 * manages the players in the game.
 * 
 * @author Alessia Carfì
 */
public final class PlayerControllerImpl implements PlayerController {

    private static final Point2D POSITION_ONE = new Point2D(ScreenController.SIZE_HD_W / 3,
            ScreenController.SIZE_HD_H / 2.5);
    private static final Point2D POSITION_TWO = new Point2D(ScreenController.SIZE_HD_W / 1.5,
            ScreenController.SIZE_HD_H / 2.5);
    private static final List<Point2D> POSITIONS = List.of(POSITION_ONE, POSITION_TWO);

    private final PlayerObservable playerObservable;
    private final List<PlayerRender> playersRender = new ArrayList<>();
    private final List<Player> players = new ArrayList<>();

    /**
     * Constructs a new {@code PlayerControllerImpl} object, initializes the players
     * and their respective PlayerRender objects, and initializes the
     * PlayerObservable object with the list of players.
     */
    public PlayerControllerImpl() {
        final AtomicInteger index = new AtomicInteger();
        POSITIONS.forEach(point -> {
            final Player player = new PlayerImpl(point, index.incrementAndGet());
            players.add(player);
            playersRender.add(new PlayerRenderImpl(player));
        });
        playerObservable = new PlayerObservableImpl(players);

    }

    @Override
    public List<PlayerRender> getRender() {
        return this.playersRender;
    }

    @Override
    public PlayerObservable getPlayerObservable() {
        return this.playerObservable;
    }

    @Override
    public List<Player> getPlayers() {
        return this.players;
    }
}

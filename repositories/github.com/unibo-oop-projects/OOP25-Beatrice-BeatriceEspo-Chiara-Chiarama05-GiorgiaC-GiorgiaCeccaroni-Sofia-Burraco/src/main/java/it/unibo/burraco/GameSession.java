package it.unibo.burraco;

import it.unibo.burraco.controller.distribution.DistributionManagerImpl;
import it.unibo.burraco.controller.distribution.DistributionController;
import it.unibo.burraco.controller.game.GameController;
import it.unibo.burraco.controller.game.GameLoopController;
import it.unibo.burraco.controller.pot.PotManager;
import it.unibo.burraco.controller.round.ResetManagerImpl;
import it.unibo.burraco.controller.round.RoundControllerImpl;
import it.unibo.burraco.controller.round.RoundEndHandler;
import it.unibo.burraco.controller.score.ScoreController;
import it.unibo.burraco.model.GameModel;
import it.unibo.burraco.model.GameModelImpl;
import it.unibo.burraco.model.player.Player;
import it.unibo.burraco.model.score.Score;
import it.unibo.burraco.model.score.ScoreImpl;
import it.unibo.burraco.view.components.sound.SoundView;
import it.unibo.burraco.view.components.sound.SoundViewImpl;
import it.unibo.burraco.view.scenes.ScoreViewImpl; 
import it.unibo.burraco.view.table.BurracoView;
import it.unibo.burraco.view.table.DistributionView;
import it.unibo.burraco.view.table.SwingTableAccess;
import it.unibo.burraco.view.table.TableViewImpl;

/**
 * Composition root for a single game session.
 * Wires all model, view, and controller components together
 * and exposes a single entry point to start the game loop.
 */
public final class GameSession {

    private final GameController loop;
    private final RoundControllerImpl roundCtrl;
    private final ScoreController scoreController;

    /**
     * Constructs and initializes a complete GameSession by wiring Model, View, 
     * and Controller components together.
     *
     * @param nameP1      the name of the first player
     * @param nameP2      the name of the second player
     * @param targetScore the score threshold required to win the entire game session
     */
    public GameSession(final String nameP1,
                       final String nameP2,
                       final int targetScore) {

        final SoundView sound = new SoundViewImpl();
        final TableViewImpl tableView = new TableViewImpl(nameP1, nameP2);
        final GameModel model = new GameModelImpl(nameP1, nameP2);

        final Player p1 = model.getPlayer1();
        final Player p2 = model.getPlayer2();

        final BurracoView burracoView = tableView;
        final SwingTableAccess swingAccess = tableView;
        final DistributionView distributionView = tableView.getInitDist();

        final PotManager potManager = new PotManager(model.getTurn(), burracoView);
        final Score score = new ScoreImpl();

        final RoundEndHandler roundEndHandler = new RoundEndHandler(
                score, p1, p2, nameP1, nameP2,
                burracoView, swingAccess, sound, targetScore,
                (snap1, snap2, target, sa, over) ->
                        new ScoreViewImpl(snap1, snap2, target, sa, over));

        this.scoreController = new ScoreController(score, p1, p2, roundEndHandler);

        this.loop = new GameLoopController(
                model, burracoView, sound, potManager, scoreController);

        this.roundCtrl = new RoundControllerImpl(
                burracoView,
                new ResetManagerImpl(),
                model,
                new DistributionController(new DistributionManagerImpl()),
                distributionView);

        this.scoreController.setOnNewRound(() -> {
            roundCtrl.processNewRound();
            loop.start();
        });
    }

    /**
     * Starts the first round and the game loop.
     */
    public void start() {
        this.roundCtrl.processNewRound();
        this.loop.start();
    }
}

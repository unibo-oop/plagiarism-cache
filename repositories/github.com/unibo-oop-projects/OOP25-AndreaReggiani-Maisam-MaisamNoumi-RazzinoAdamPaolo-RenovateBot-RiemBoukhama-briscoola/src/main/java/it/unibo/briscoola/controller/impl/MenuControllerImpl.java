package it.unibo.briscoola.controller.impl;

import java.util.List;

import it.unibo.briscoola.controller.api.GameController;
import it.unibo.briscoola.controller.api.MenuController;
import it.unibo.briscoola.controller.impl.utils.Pair;
import it.unibo.briscoola.model.api.attributes.Difficulty;
import it.unibo.briscoola.model.api.card.Card;
import it.unibo.briscoola.model.api.game.GameModel;
import it.unibo.briscoola.model.api.leaderboard.Leaderboard;
import it.unibo.briscoola.model.api.player.Player;
import it.unibo.briscoola.model.impl.game.GameBuilderImpl;
import it.unibo.briscoola.model.impl.leaderboard.LeaderboardImpl;
import it.unibo.briscoola.view.api.View;
import it.unibo.briscoola.view.impl.GameViewImpl;
 
/**
 * implementation of {@link MenuController}
 * This class has the role to handle the initial setUp
 * and starts the match.
 *
 * @author Andrea Reggiani
 */
public final class MenuControllerImpl implements MenuController {
 
    private static final int MAX_PLAYERS = 2;
 
    /*
     * private final View view;
     */
 
    /**
     * Constructs a new {@code MenuControllerImpl}.
     */
    public MenuControllerImpl() {
        super();
    }
 
    /**
     * {@inheritDoc}
     */
    @Override
    public void startGame(final String namePlayer, final Difficulty difficulty, final View view) {
        if (namePlayer == null) {
            throw new IllegalArgumentException("Il nome non puo essere vuoto");
        }
        if (difficulty == null) {
            throw new IllegalArgumentException("La difficolta non puo essere nulla");
        }
 
        final GameBuilderImpl builder = new GameBuilderImpl(namePlayer);
        builder.changeDifficulty(difficulty);
 
        for (int i = 1; i < MAX_PLAYERS; i++) {
            builder.addPlayer();
        }
        final GameModel model = builder.build();
        model.startMatch();
 
        view.initGame();
 
        final Player human = model.getCurrentPlayer();
        view.updateHand(0, convertCards(human.getHand()));
 
        if (model.getBriscolaSeed().isPresent()) {
            final String briscolaSeedStr = model.getBriscolaSeed().get().getCardSeed().name();
            final String briscolaValueStr = model.getBriscolaSeed().get().getCardValue().name();
 
            if (view instanceof GameViewImpl gameView) {
                gameView.updateBriscola(briscolaSeedStr, briscolaValueStr);
            }
        }
 
        final GameController gameController = new GameControllerImpl(model, view);
        view.setOnCardPlayedListener(gameController::handlesHumanCardSelection);
        gameController.startGame();
    }
 
    private List<Pair<String, String>> convertCards(final List<Card> cards) {
    return cards.stream()
        .map(card -> new Pair<>(
            card.getCardSeed().name(),
            card.getCardValue().name()
        ))
        .toList();
    }
 
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Pair<String, String>> getLeaderboardData() {
        final String leaderboardFile = "leaderboard.json";
        final Leaderboard leaderboard = new LeaderboardImpl(leaderboardFile);
        return leaderboard.getEntries().stream().map(a -> new Pair<>(a.getName(), String.valueOf(a.getScore()))).toList();
    }
}

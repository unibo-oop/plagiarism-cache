package migglione.controller.impl;

import java.util.List;
import java.util.Optional;

import migglione.controller.api.Controller;
import migglione.model.api.Game;
import migglione.model.api.Player;
import migglione.model.impl.Card;
import migglione.model.impl.GameImpl;
import migglione.persistence.api.ScoreRepository;
import migglione.persistence.api.TutorialRepository;
import migglione.persistence.impl.ScoreRepositoryImpl;
import migglione.persistence.impl.TutorialRepositoryImpl;
import migglione.view.api.SwingView;
import migglione.view.impl.SwingViewImpl;

/**
 * Implementation of the Controller class.
 * 
 * <p>
 * It is responsible for storing the player's name,
 * to allow either the player or the CPU to play a card,
 * to decide if it's time to go to the next round or
 * to end the match and declaring the winner
 */
public final class ControllerImpl implements Controller {

    private final SwingView view;
    private final ScoreRepository sRep;
    private final TutorialRepository tRep;
    private Game model;
    private String playerName;

    /**
     * Simple constructor of the Controller.
     * 
     * <p>
     * It initializes the main class of the GUI,
     * so that the menu is seen by the player but also
     * the Repositories useful for writing and reading
     * from files
     */
    public ControllerImpl() {
        this.view = new SwingViewImpl(this);
        this.sRep = new ScoreRepositoryImpl();
        this.tRep = new TutorialRepositoryImpl();

        checkFirstTime();
    }

    /**
     * Constructor used for tests.
     *
     * <p>
     * The reason this other constructor exists is to make sure that
     * the controller can be tested. I personally wasn't sure if
     * this is good practice in a code, but after some research
     * it seems that this is the only way to guarantee that a Controller
     * using new can be tested.
     *
     * @param view is the view of the application
     * @param sRep is the ScoreRepository to save scores in files
     * @param tRep is the TutorialRepository to check if the tutorial has been viewed
     * @param model is the model of the application
     */
    ControllerImpl(final SwingView view, final ScoreRepository sRep, final TutorialRepository tRep, final Game model) {
        this.model = model;
        this.view = view;
        this.sRep = sRep;
        this.tRep = tRep;
        checkFirstTime();
    }

    /**
     * Necessary method for the tests.
     *
     * <p>
     * Since calling startSession would change the
     * mock value with a real one, it will compromise
     * the effectivness of the when methods, so
     * this way we can test other parts of the Controller
     *
     * @param name is the name of the player
     */
    void setPlayerMockName(final String name) {
        this.playerName = name;
    }

    private void checkFirstTime() {
        if (!this.tRep.haveTutorialBeenSeen()) {
            this.tRep.writeOnTutorial();
            this.view.showTutorialPrompt();
        }
    }

    @Override
    public void startSession(final String name) {
        this.playerName = name;
        this.model = new GameImpl(playerName);
    }

    @Override
    public void checkSession() {
        if (this.model.matchEnded()) {
            endSession();
        }
    }

    @Override
    public List<Player> getPlayers() {
        return this.model.getPlayers();
    }

    @Override
    public void playTurnLead(final String attr, final Card played) {
        this.model.playTurnLead(attr, played);
    }

    @Override
    public void playTurnTail(final Card played) {
        this.model.playTurnTail(played);
    }

    @Override
    public boolean playTurnStored() {
        return this.model.playTurnStored();
    }

    @Override
    public String getCurrAttr() {
        return this.model.getCurrAttr();
    }

    @Override
    public Player getTurnLeader() {
        return this.model.getTurnLeader();
    }

    @Override
    public int getScore(final Player player) {
        return this.model.getScore(player);
    }

    @Override
    public void endSession() {
        final Optional<String> winner = this.model.getWinner();
        final Optional<Integer> pScoreOpt = this.model.getPlayerScore();
        final Optional<Integer> cScoreOpt = this.model.getCPUScore();
        if (winner.isEmpty() || pScoreOpt.isEmpty() || cScoreOpt.isEmpty()) {
            return;
        }

        if (winner.get().equals(playerName)) {
            this.sRep.writeWinner(playerName, pScoreOpt.get());
        }

        this.view.endMessage(winner.get(), playerName, pScoreOpt.get(), cScoreOpt.get());
    }
}

package controller.playercontroller;

import static utilities.Messages.*;
import model.character.Character;
import java.util.Optional;
import java.util.stream.Collectors;

import controller.command.Action;
import controller.command.GuessingAction;
import controller.gamecontroller.Controller;
import controller.gameoptions.Difficulty;
import controller.resources.Resources;
import model.player.*;
import utilities.*;
import view.playerview.*;

/**
 * Implementation of a Human Player Controller.
 */
public class HumanController extends AbstractController implements PlayerController, PlayerViewObserver {

    private final PlayerView view;
    private final Difficulty difficulty;
    private final Controller controller;

    /**
     * Constructor.
     * @param difficulty 
     *              the game's difficulty
     * @param controller 
     *              the main controller of the game
     */
    public HumanController(final Difficulty difficulty, final Controller controller) {
        super(new PlayerBuilder().setAttempts(difficulty.getAttempts()).setCharacters(Resources.getCharacters()).build());
        Utilities.requireNonNull(difficulty, controller);
        this.difficulty = difficulty;
        this.controller = controller;
        view = new PlayerViewImpl(this, difficulty.getAttempts(), getPlayer().availableQuestions().stream().collect(Collectors.toSet()));
    }

    /**
     * @iheritDoc
     */
    @Override
    public void play() {
        view.setEnabled(true);
    }

    /**
     * @iheritDoc
     */
    @Override
    public boolean askPlayer(final Action action) throws InterruptedException {
        Utilities.requireNonNull(action);
        final boolean correctAnswer = super.askPlayer(action);
        if (view.showQuestion(action.getQuestion()) != correctAnswer) {
            view.showMessage(HUMAN_LIED);
        }
        return correctAnswer;
    }

    /**
     * @iheritDoc
     */
    @Override
    public void registerAnswer(final Action action, final boolean answer) throws InterruptedException {
        super.registerAnswer(action, answer);
        this.updateView(answer);
        if (action instanceof GuessingAction && !answer) {
            view.decreaseAttempts();
        }
    }

    /**
     * @iheritDoc
     */
    @Override
    public void endGame(final Status status, final Optional<Character> opponentCharacter) {
        Utilities.requireNonNull(status, opponentCharacter);
        if (status == Status.OPPONENT_QUIT) {
            view.showErrorMessage(OPPONENT_QUIT);
        } else {
            view.close(status == Status.WON ? PLAYER_WON : PLAYER_LOST 
                    + (opponentCharacter.isPresent() ? OPPONENT_CHARACTER_WAS + opponentCharacter.get().getName() : ""));
        }
    }

    /**
     * @iheritDoc
     */
    @Override
    public void select(final String name) {
        Utilities.requireNonNull(name);
        view.setEnabled(false);
        getPlayer().select(name);
        controller.selected(this, name);
    }

    /**
     * @iheritDoc
     */
    @Override
    public void askOpponent(final Action action) {
        Utilities.requireNonNull(action);
        view.setEnabled(false);
        controller.askOpponent(action);
    }

    /**
     * @iheritDoc
     */
    @Override
    public void exit() {
        controller.endGame(this);
    }

    private void updateView(final boolean answer) throws InterruptedException {
        view.showMessage(answer ? YES : NO);
        view.updateCharacters(getPlayer().getAvailables());
        if (difficulty != Difficulty.HARD) {
            view.updateQuestions(getPlayer().availableQuestions().stream().collect(Collectors.toSet()));
        }
    }

}

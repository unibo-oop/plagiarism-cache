package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.swing.ImageIcon;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Model;
import model.ModelImpl;
import model.Question;
import model.exception.AttemptException;
import model.exception.NoMoreQuestionAvailableException;
import model.exception.PendingAIGuessingCharacter;
import model.exception.UserLiedException;
import utilities.CharacterDeserializer;
import utilities.ResourceLoader;
import view.MenuView;
import view.GameView;
import view.GameViewImpl;
import model.Character;

/**
 * This class manages the game and interactions between model and view.
 *
 */
public final class ControllerImpl implements Controller {

    private static final String JSONS_PATH = "/json";
    private static final int JSON_EXT_LENGTH = 5;
    private static final Controller SINGLETON = new ControllerImpl();
    private Model myModel;
    private GameView myView;

    private ControllerImpl() {
    }

    /**
     * 
     * @return an istance of controller
     */
    public static Controller getController() {
        return ControllerImpl.SINGLETON;
    }

    @Override
    public Map<Character, Boolean> getHumanCharactersLeft() {
        return this.myModel.getHumanCharacters();
    }

    @Override
    public Map<Character, Boolean> getAICharactersLeft() {
        return this.myModel.getAICharacters();
    }

    @Override
    public Set<Question> getAvailableQuestions() {
        return this.myModel.getHumanQuestions();
    }

    @Override
    public void setHumanCharacter(final Character c) {
        this.myModel.humanHasChosenCharacter(c);
        this.turnManager();

    }

    @Override
    public void askQuestion(final Question q) {
        if (this.myModel.askToAI(q)) {
            this.myView.showMessage(q.getText() + " SI");
        } else {
            this.myView.showMessage(q.getText() + " NO");
        }
        this.turnManager();
    }

    @Override
    public void humanAnswered(final Question question, final boolean answer) {
        try {
            this.myModel.humanAnswered(question, answer);
            this.turnManager();

        } catch (UserLiedException e) {
            this.myView.showMessage("Hai mentito!");
            this.myView.showQuestion(question);
        }
    }

    @Override
    public List<String> getPackList() {
        List<String> filenames = new ArrayList<>();
        filenames = ResourceLoader.listResourceFiles(JSONS_PATH).stream()
                .map(x -> x.substring(0, x.length() - JSON_EXT_LENGTH)).collect(Collectors.toList());
        return filenames;
    }

    @Override
    public void tryToGuess(final Character choosen) {
        try {
            this.myModel.humanTryToGuess(choosen);
            if (!this.myModel.humanWon()) {
                this.myView.showMessage("Peccato, il personaggio dell'avversario non e' " + choosen.getName());
            }
        } catch (AttemptException e) {
            this.myView.showMessage("Non hai piu' tentativi!");
        }
        this.turnManager();
    }

    @Override
    public void aiGuessed(final Character c, final boolean answer) {
        try {
            this.myModel.humanAnswered(c, answer);
            this.turnManager();
        } catch (UserLiedException e) {
            this.myView.showMessage("Hai mentito!");
            this.myView.showQuestion(c);
        }

    }

    @Override
    public void loadPack(final String s) {
        this.myModel = new ModelImpl(this.getStartingCharacters(JSONS_PATH + "/" + s + ".json"));
        this.myView = new GameViewImpl();
        this.myView.showView();
    }

    @Override
    public void restartGame() {
        this.myModel.resetGame();
    }

    /* Utility Methods */

    /**
     * Officially starts the new game launching a new view.
     */
    public void startGame() {
        new MenuView();
    }

    /**
     * Turn's handler, understand whose turn is and lets him play. Basically the
     * heart of this class.
     */
    private void turnManager() {
        if (!this.checkWin()) {
            if (this.myModel.isHumanTurn()) {
                this.myView.showMessage("E' il tuo turno, puoi porre una domanda oppure provare ad indovinare");

            } else {
                try {
                    final Question q = this.myModel.getAInextQuestion();
                    this.myView.showQuestion(q);
                } catch (NoMoreQuestionAvailableException e) {
                    e.printStackTrace();
                } catch (PendingAIGuessingCharacter e) {
                    this.myView.showQuestion(this.myModel.getAIcharacterGuess().get());
                }
            }
        }
    }

    /**
     * Checks if someone won the game
     * 
     * @return true if someone won, false otherwise
     */

    private boolean checkWin() {
        if (this.myModel.humanWon()) {
            this.myView.showWinMessage("<html> Hai vinto!<br></br> Il personaggio dell'avversario era: </html>",
                    this.getAIPic());
            return true;
        } else if (this.myModel.aiWon()) {
            this.myView.showWinMessage("<html>Hai perso...<br></br> Il personaggio dell'avversario era: </html>",
                    this.getAIPic());
            return true;
        }
        return false;
    }

    /**
     * 
     * @return the image of AI's choosen character to be shown when someone wins
     *         the game
     */
    private ImageIcon getAIPic() {
        return this.myModel.getAIchosenCharacter().getPic();
    }

    /**
     * Loads a list of characters from a pack specified by the player.
     * 
     * @return List of characters
     */

    private Set<Character> getStartingCharacters(final String path) {
        final Character[] characters;
        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Character.class, new CharacterDeserializer());
        final Gson gson = gsonBuilder.create();
        characters = gson.fromJson(ResourceLoader.loadFile(path), Character[].class);
        return new LinkedHashSet<>(Arrays.asList(characters));
    }

}

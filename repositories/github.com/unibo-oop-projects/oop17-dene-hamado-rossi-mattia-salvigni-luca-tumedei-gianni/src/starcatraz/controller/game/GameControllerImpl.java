package starcatraz.controller.game;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import starcatraz.controller.StarcatrazController;
import starcatraz.model.Statistics;
import starcatraz.model.cards.Card;
import starcatraz.model.cards.CardType;
import starcatraz.model.game.Game;
import starcatraz.model.game.GameImpl;
import starcatraz.model.game.GameStatus;
import starcatraz.util.AppFXML;
import starcatraz.util.AppSound;
import starcatraz.util.FileReadWrite;
import starcatraz.view.cards.CardStackView;
import starcatraz.view.cards.CardView;

/**
 * The controller for Game.
 */
public class GameControllerImpl extends StarcatrazController implements Initializable, GameController {

    // Constants used for game time operations
    private static final int SECONDOS_IN_HOUR = 3600;
    private static final int MILLIS_IN_SECOND = 1000;
    private static final int SECONDS_IN_MINUTE = 60;
    private static final int HOURS_IN_DAY = 24;

    @FXML
    private CardStackView deckStack;
    @FXML
    private CardStackView playerHandStack;
    @FXML
    private CardStackView playedCardsStack;
    @FXML
    private CardStackView discardStack;
    @FXML
    private CardStackView rocketStore;
    @FXML
    private Button backButton;
    @FXML
    private Button restartButton;
    @FXML
    private Button pauseButton;
    @FXML
    private Label robotCounterLabel;
    @FXML
    private Label cardCounterLabel;
    @FXML
    private ScrollPane playedCardsScroll;
    @FXML
    private AnchorPane playedCardsAnchor;

    /**
     * The Game object.
     */
    private final Game game = new GameImpl();

    /**
     * The popup stage used to show dialogs.
     */
    private Stage popupStage;

    /**
     * The popup stage controller.
     */
    private GamePopupController popupController;
    
    /**
     * The Statistics object.
     */
    private Statistics statistics;

    /**
     * Count of defeated robots in the current game.
     */
    private int defeatedRobots = 0;

    /**
     * Game start time.
     */
    private long startTime;

    /**
     * Amount of time in pause since the start of the match.
     */
    private long pauseTime;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        AppSound.GAME_MUSIC.play();
        // deckStack has only facedown cards, so order doesn't matter
        addMirrorListener(this.game.getDeck(), deckStack);
        // playerHand must always be sorted, so setupPermutationListener is used
        addMirrorListener(this.game.getPlayerHand(), playerHandStack);
        // There is no need to sort playedCardsStack and discardStack
        addMirrorListener(this.game.getPlayedCards(), playedCardsStack);
        addMirrorListener(this.game.getDiscardPile(), discardStack);
        // rocketStore must always be sorted, so setupPermutationListener is used
        addMirrorListener(this.game.getRockets(), rocketStore);
        resetGame();
    }
    
    @Override
    public Game getGame() {
        return this.game;
    }
    
    @Override
    public void setStatistics(final Statistics statistics) {
        this.statistics = statistics;
    }
    
    @Override
    public void incrementDefeatedRobots() {
        this.defeatedRobots++;
    }
    
    @Override
    public long getPauseTime() {
        return this.pauseTime;
    }
    
    @Override
    public void setPauseTime(final long pauseTime) {
        this.pauseTime = pauseTime;
    }
    
    /**
     * Restart the game
     */
    private void resetGame() {
        removeDeckCounterListener();
        this.game.reset();
        addDeckCounterListener();
        updateDeckCounters();
        this.game.drawHand();
        this.defeatedRobots=0;
        this.startTime = System.currentTimeMillis();
    }
    
    /**
     * Sets up listener to mirror any addition or removal from list to stack
     * @param list
     * @param stack
     */
    private void addMirrorListener(final ObservableList<Card> list, final CardStackView stack) {
        list.addListener(new ListChangeListener<Card>() {
            @Override
            public void onChanged(Change<? extends Card> c) {
                while (c.next()) {
                    if (c.wasAdded()) {
                        for (Card card : c.getAddedSubList()) {
                            CardView cardView = new CardView(card);
                            // If a card is added to the deck it must be face down
                            if (list.equals(game.getDeck())) {
                                cardView.setFaceDown(true);
                            }
                            // If a card is added to the player hand it must have a mouse click event to be played
                            if (list.equals(game.getPlayerHand())) {
                                cardView.setOnMouseClicked(e -> useCard(e, cardView));
                            }
                            stack.addCard(cardView);
                        }
                    } else if (c.wasRemoved()) {
                        for (Card card : c.getRemoved()) {
                            stack.removeCard(card);
                        }
                        if (list.equals(game.getPlayerHand())) {
                            AppSound.PLAY_CARD_SOUND.play();
                        }
                    }
                }
            }
        });
    }
    
    /**
     * Listener to update deck counters
     */
    private ListChangeListener<Card> deckCounterListener = new ListChangeListener<Card>() {
        @Override
        public void onChanged(Change<? extends Card> c) {
            //System.out.println("deckCounterListener");
            while (c.next()) {
                if (c.wasAdded() || c.wasRemoved()) {
                    updateDeckCounters();
                }
            }
        }
    };
    
    /**
     * Adds the counter listener to the deck
     */
    private void addDeckCounterListener() {
        this.game.getDeck().addListener(deckCounterListener);
    }
    
    /**
     * Removes the counter listener from the deck
     */
    private void removeDeckCounterListener() {
        this.game.getDeck().removeListener(deckCounterListener);
    }
    
    /**
     * Updates the deck counters
     */
    private void updateDeckCounters() {
        robotCounterLabel.setText(Integer.toString(this.game.getDeckRobotCount()));
        cardCounterLabel.setText(Integer.toString(this.game.getDeckSize()));
    }
    
    /**
     * Use the given card
     * @param e
     * @param cardView
     */
    private void useCard(MouseEvent e, CardView cardView) {
        switch (e.getButton()) {
        case PRIMARY:
            game.playCard(cardView.getCard());
            this.playedCardsScroll.hvalueProperty().bind(playedCardsAnchor.widthProperty());
            break;
        case SECONDARY:
            if (cardView.getCard().getType() == CardType.CHIP) {
                game.useChip(cardView.getCard().getColor());
            } else {
                game.discardCard(cardView.getCard());
            }
            break;
        default:
            break;
        }
        checkGameStatus();
    }
    
    /**
     * Check the status of Game and act correspondingly
     */
    private void checkGameStatus() {
        int cardStreak;
        int usedCards;
        String matchTime;
        long actualRecord;
        switch (this.game.getStatus()) {
        case WON:
            this.startTime = System.currentTimeMillis() - this.startTime - this.pauseTime;
            matchTime = formatTime(this.startTime);
            actualRecord = (statistics.getGameTimeRecord().getHour() * SECONDOS_IN_HOUR)
                         + (statistics.getGameTimeRecord().getMinute() * SECONDS_IN_MINUTE * MILLIS_IN_SECOND)
                         + (statistics.getGameTimeRecord().getSecond() * MILLIS_IN_SECOND);
            statistics.incrementVictories();
            statistics.incrementPlayedGames();
            cardStreak = getLongestCardStreak();
            usedCards = getUsedCards();
            if (statistics.getDefeatedRobotsCount() < this.defeatedRobots) {
                statistics.setDefeatedRobotsCount(this.defeatedRobots);
            }
            if (statistics.getCardStreak() < cardStreak) {
                statistics.setCardStreak(cardStreak);
            }
            if (statistics.getVictoryWithFewestCards() > usedCards) {
                statistics.setVictoryWithFewestCards(usedCards);
            }
            if (actualRecord > this.startTime) {
                statistics.setGameTimeRecord(matchTime);
            }
            FileReadWrite.writeStatistics(statistics);
            showPopupStage(AppFXML.VICTORY_VIEW);
            AppSound.GAME_MUSIC.stop();
            AppSound.VICTORY_MUSIC.play();
            resetGame();
            break;
        case LOST:
            statistics.incrementDefeats();
            statistics.incrementPlayedGames();
            FileReadWrite.writeStatistics(statistics);
            showPopupStage(AppFXML.DEFEAT_VIEW);
            AppSound.GAME_MUSIC.stop();
            AppSound.DEFEAT_MUSIC.play();
            resetGame();
            break;
        case ACTIVATING_ROCKET:
            showPopupStage(AppFXML.ROCKET_ACTIVATION_VIEW);
            AppSound.ROCKET_SOUND.play();
            ((RocketActivationController) this.popupController).setRocket(this.game.getLimboCards().get(0));
            break;
        case UNDER_ATTACK:
            showPopupStage(AppFXML.ROBOT_ATTACK_VIEW);
            AppSound.ROBOT_SOUND.play();
            ((RobotAttackController) this.popupController).disableInappropriateButtons();
            break;
        case USING_CHIP:
            showPopupStage(AppFXML.CHIP_USAGE_VIEW);
            AppSound.TERMINAL_SOUND.play();
            this.game.getChipCards().forEach(c -> ((ChipUsageController) this.popupController).addCard(new CardView(c)));
            break;
        default:
            break;
        }
    }
    
    /**
     * @return the longest streak of cards of the same color in the current match
     */
    private int getLongestCardStreak() {
        List<Card> l = this.game.getPlayedCards();
        int max = 0;
        int count = 0;
        for(int i=0; i<l.size(); i++) {
            if(count==0) {
                count = 1;
            } else if(l.get(i).getColor() != l.get(i-1).getColor()) {
                if(count > max) {
                    max = count;
                }
                count = 1;
            } else {
                count++;
            }
        }
        return max;
    }
    
    /**
     * @return the number of cards used in the current match
     */
    private int getUsedCards() {
        return this.game.getDiscardPile().size()
             + this.game.getPlayerHand().size()
             + this.game.getRockets().size()
             + this.game.getPlayedCards().size();
    }
    
    /**
     * Formats the given number in HH:MM:SS String
     * @param time in milliseconds
     * @return String containing the formatted time
     */
    private String formatTime(long time) {
        long second = (time / MILLIS_IN_SECOND) % SECONDS_IN_MINUTE;
        long minute = (time / (MILLIS_IN_SECOND * SECONDS_IN_MINUTE)) % SECONDS_IN_MINUTE;
        long hour = (time / (MILLIS_IN_SECOND * SECONDS_IN_MINUTE * SECONDS_IN_MINUTE)) % HOURS_IN_DAY;
        String formattedTime = String.format("%02d:%02d:%02d", hour, minute, second);
        return formattedTime;
    }

    @Override
    public void backButtonClick(ActionEvent event) {
        AppSound.GAME_MUSIC.stop();
        getStarcatrazApp().showView(AppFXML.MENU_VIEW);
    }
    
    @Override
    public void restartButtonClick(ActionEvent event) {
        resetGame();
    }
    
    @Override
    public void pauseButtonClick(ActionEvent event) {
        AppSound.GAME_MUSIC.stop();
        showPopupStage(AppFXML.PAUSE_VIEW);
    }
    
    /**
     * Method used to load popup windows
     * @param path of the fxml file
     * @return AnchorPane containing the loaded file
     */
    private AnchorPane createWindow(final String path) {
        AnchorPane pane = new AnchorPane();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(path));
            pane = (AnchorPane) loader.load();
            this.popupController = (GamePopupController) loader.getController();
            this.popupController.setGameController(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pane;
    }
    
    /**
     * Initialized the popup stage
     */
    private void initPopupStage() {
        this.popupStage = new Stage(StageStyle.TRANSPARENT);
        this.popupStage.initOwner(getStarcatrazApp().getPrimaryStage());
        this.popupStage.initModality(Modality.APPLICATION_MODAL);
        this.popupStage.setWidth(getStarcatrazApp().getSettings().getResolutionWidth());
        this.popupStage.setWidth(getStarcatrazApp().getSettings().getResolutionHeight());
    }
    
    @Override
    public void showPopupStage(String fxmlPath) {
        if (this.popupStage == null) {
            initPopupStage();
        }
        final AnchorPane content = createWindow(fxmlPath);
        this.popupStage.setScene(new Scene(content, Color.TRANSPARENT));
        this.popupStage.show();
        if(this.game.getStatus() == GameStatus.WON) {
            AppSound.VICTORY_MUSIC.stop();
        } else {
            AppSound.DEFEAT_MUSIC.stop();
        }
    }
    
    @Override
    public void hidePopupStage() {
        this.popupStage.hide();
        checkGameStatus();
    }
    
    @Override
    public GamePopupController getPopupStageController() {
        return this.popupController;
    }
}

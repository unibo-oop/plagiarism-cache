package view.gamescreen;

import java.util.List;

import com.jfoenix.controls.JFXButton;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import utils.enumerations.Status;
import view.ViewImpl;

/**
 * 
 * This class represent bottom row of gameScreen view. It contains:
 * <ul>
 * <li>Attacking state
 * <li>Attacking dice
 * <li>Battle confirmation button
 * <li>Attacking dice
 * <li>Defending state
 * </ul>
 */
public class FooterBattleInfo extends HBox {

    private static final double WIDTH = Screen.getPrimary().getBounds().getWidth();
    private static final double HEIGHT = Screen.getPrimary().getBounds().getHeight() / 16;

    private static final double FONT_SIZE = 32;
    private static final String BATTLE_ICON_PATH = "/images/battle.png";
    private static final String TICK_ICON_PATH = "/images/green-tick.png";

    private final Label atkStateName = new Label("");
    private final Label defStateName = new Label("");
    private static final TilePane ATK_DICE = new TilePane();
    private static final TilePane DEF_DICE = new TilePane();
    private final JFXButton battle = new JFXButton();
    private final JFXButton move = new JFXButton();

    /**
     * 
     * Class constructor.
     * 
     */
    public FooterBattleInfo() {
        ATK_DICE.getChildren().clear();
        DEF_DICE.getChildren().clear();
        ATK_DICE.getChildren().addAll(new Dice(0), new Dice(0), new Dice(0));
        DEF_DICE.getChildren().addAll(new Dice(0), new Dice(0), new Dice(0));

        battle.setGraphic(new ImageView(getClass().getResource(BATTLE_ICON_PATH).toExternalForm()));
        move.setGraphic(new ImageView(getClass().getResource(TICK_ICON_PATH).toExternalForm()));

        final StackPane btnContainer = new StackPane(battle, move);
        move.setVisible(false);

        atkStateName.setPrefSize(WIDTH / 3, HEIGHT);
        defStateName.setPrefSize(WIDTH / 3, HEIGHT);
        ATK_DICE.setPrefHeight(HEIGHT);
        ATK_DICE.setAlignment(Pos.CENTER);
        ATK_DICE.setHgap(10);
        DEF_DICE.setPrefHeight(HEIGHT);
        DEF_DICE.setAlignment(Pos.CENTER);
        DEF_DICE.setHgap(10);
        battle.setPrefSize(battle.getWidth(), HEIGHT);
        move.setPrefSize(move.getWidth(), HEIGHT);

        HBox.setHgrow(atkStateName, Priority.ALWAYS);
        HBox.setHgrow(ATK_DICE, Priority.ALWAYS);
        HBox.setHgrow(battle, Priority.ALWAYS);
        HBox.setHgrow(move, Priority.ALWAYS);
        HBox.setHgrow(DEF_DICE, Priority.ALWAYS);
        HBox.setHgrow(defStateName, Priority.ALWAYS);

        atkStateName.setFont(new Font(FONT_SIZE));
        atkStateName.setAlignment(Pos.CENTER);
        defStateName.setFont(new Font(FONT_SIZE));
        defStateName.setAlignment(Pos.CENTER);

        this.getChildren().addAll(atkStateName, ATK_DICE, btnContainer, DEF_DICE, defStateName);
    }

    /**
     * Attacker dice value updater.
     * 
     * @param valueList
     *                  List of dice values to display.
     * 
     */
    public static synchronized void updateAtkDice(final List<Integer> valueList) {
        Platform.runLater(() -> {
            ATK_DICE.getChildren().clear();
            valueList.iterator().forEachRemaining(v -> {
            ATK_DICE.getChildren().add(new Dice(v.intValue()));
            });
        });
    }

    /**
     * Defender dice value updater.
     * 
     * @param valueList
     *                  list of dice values to display.
     * 
     */
    public static synchronized void updateDefDice(final List<Integer> valueList) {
        Platform.runLater(() -> {
            DEF_DICE.getChildren().clear();
            valueList.iterator().forEachRemaining(v -> {
                DEF_DICE.getChildren().add(new Dice(v.intValue()));
            });
        });
    }

    /**
     * Update attacking player name.
     * 
     * @param name
     *              Actual name of the player.
     * 
     */
    public void updateAtkStateName(final String name) {
        this.atkStateName.setText(name);
    }

    /**
     * Update defending player name.
     * 
     * @param name
     *              Actual name of the player.
     * 
     */
    public void updateDefStateName(final String name) {
        this.defStateName.setText(name);
    }

    /**
     * 
     * Register battle button listener.
     * 
     * @param tankChooserSlider
     *                  Slider responsible of attack phase. 
     * @param tankMoverSlider 
     *                  Slider responsible of moving tanks.
     * 
     */
    public void registerButtonsListener(final TankChooserSlider tankChooserSlider, final TankMoverSlider tankMoverSlider) {
        battle.setOnMouseClicked(e -> {
            if (ViewImpl.getIstance().getAtkState().isPresent() && ViewImpl.getIstance().getDefState().isPresent() && ViewImpl.getIstance().getController().getGameStatus().equals(Status.ATTACK)) {
                ViewImpl.getIstance().getController().checkStates(ViewImpl.getIstance().getAtkState().get(), ViewImpl.getIstance().getDefState().get(), tankChooserSlider.getIntValue());
            }
        });

        move.setOnMouseClicked(e -> {
            if (ViewImpl.getIstance().getOriginState().isPresent() && ViewImpl.getIstance().getDestState().isPresent() && ViewImpl.getIstance().getController().getGameStatus().equals(Status.ATTACK)) {
                ViewImpl.getIstance().getController().tanksToMove(ViewImpl.getIstance().getOriginState().get(), ViewImpl.getIstance().getDestState().get(), tankMoverSlider.getIntValue());
                ViewImpl.getIstance().disableAllStates(true);
                tankMoverSlider.setVisible(false);
                this.setMoveButton(false);
            }

        });
    }

    /**
     * 
     * Reset the two string that indicate defending and/or attacking states.
     * 
     */
    public void resetSelections() {
        updateAtkStateName(" ");
        updateDefStateName(" ");
    }

    /**
     * 
     * Getter for move tanks button.
     * 
     * @return
     *          The actual button.
     * 
     */
    public JFXButton getMove() {
        return move;
    }

    /**
     * 
     * Change active button.
     * 
     * @param bool
     *                  True to show move button, false show battle button.
     * 
     */
    public void setMoveButton(final boolean bool) {
        move.setVisible(bool);
        battle.setVisible(!bool);
    }
}

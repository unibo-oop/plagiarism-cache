package uicontrollers;

import java.util.List;
import java.util.Map;

import controller.ControllerImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.util.Pair;

/**
 * 
 * Controller dedicated to the Battle Scene.
 *
 */
public final class BattleController {
    private static final int DEFAULT_DIMENSION = 40;
    private static final int PERC_COL_GRID = 15;
    private static final int PERC_ROW_GRID = 15;
    private static final int MIN_HEIGHT_BUTTON = 20;
    private static final int MIN_WIDTH_BUTTON = 30;

    @FXML
    private GridPane battlegrid;

    private BattleController bc;

    @FXML
    private Button btnBackMain;

    @FXML
    private Button btnQuit;

    @FXML
    private Button btnRematch;

    @FXML
    private Button endTurn;

    @FXML
    private Label srcMaxHp;

    @FXML
    private Label srcMov;

    @FXML
    private Label srcName;

    @FXML
    private Label srcHp;

    @FXML
    private Label srcAtk;

    @FXML
    private Label trgtMaxHp;

    @FXML
    private Label trgtDef;

    @FXML
    private Label trgtHp;

    @FXML
    private Label trgtName;

    @FXML
    private Label winner;

    @FXML
    private Label srcAtkType;

    @FXML
    private Label srcDef;

    @FXML
    private Label trgtAtk;

    @FXML
    private Label trgtMov;

    @FXML
    private Label trgtAtkType;

    @FXML
    private Label trgtMovStats;

    @FXML
    private Label trgtAtkStats;

    @FXML
    private Label srcMovStats;

    @FXML
    private Label srcAtkStats;

    @FXML
    private Label playerTurn;

    /**
     * 
     * @param bc
     *            the controller of the scene
     * @param p
     *            the pair containing the maximum numbers of columns and rows of the
     *            arena
     */
    public void initData(final BattleController bc, final Pair<Integer, Integer> p) {
        if (this.bc == null) {
            this.bc = bc;
        }
        battlegrid.getColumnConstraints().clear();
        battlegrid.getRowConstraints().clear();
        for (int irows = 0; irows <= p.getKey(); irows++) {
            battlegrid.getRowConstraints().add(new RowConstraints());
            battlegrid.getRowConstraints().get(irows).setPercentHeight(PERC_ROW_GRID);
            for (int icols = 0; icols <= p.getValue(); icols++) {
                final Button btn = new Button();
                btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                btn.setOnAction(a -> {
                    this.cellClick(a);
                });
                btn.hoverProperty().addListener((obs, oldVal, newVal) -> {
                    this.cellHover(btn);
                });
                btn.focusedProperty().addListener((obs, oldVal, newVal) -> {
                    this.cellFocusChanged(btn);
                });
                if (irows == 0) {
                    battlegrid.getColumnConstraints().add(new ColumnConstraints());
                    battlegrid.getColumnConstraints().get(icols).setPercentWidth(PERC_COL_GRID);
                }
                btn.setId(Integer.toString(irows) + Integer.toString(icols));
                GridPane.setConstraints(btn, icols, irows);
                btn.setMinSize(MIN_HEIGHT_BUTTON, MIN_WIDTH_BUTTON);
                this.battlegrid.add(btn, icols, irows);
                GridPane.setHgrow(btn, Priority.ALWAYS);
                GridPane.setVgrow(btn, Priority.ALWAYS);
            }
        }
        ControllerImpl.getInstance().loadBattleMap(this.bc);
    }

    /**
     * 
     * @param arenaMap
     *            map containing coordinates of the terrain and terrain type of the
     *            arena
     * 
     */
    public void drawTerrain(final Map<Pair<Integer, Integer>, String> arenaMap) {
        for (final Map.Entry<Pair<Integer, Integer>, String> e : arenaMap.entrySet()) {
            final Image terrainImg = new Image(getClass().getResource(e.getValue().toString()).toExternalForm());
            final Button btn = getButton(battlegrid, e.getKey().getValue(), e.getKey().getKey());
            final BackgroundImage bImage = new BackgroundImage(terrainImg, BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                    new BackgroundSize(btn.getWidth(), btn.getHeight(), false, false, true, true));
            final Background backGround = new Background(bImage);
            btn.setBackground(backGround);
        }
    }

    /**
     * 
     * @param aliveEntities
     *            map containing coordinates and the names of the alive entities in
     *            the arena
     */
    public void drawAlive(final Map<Pair<Integer, Integer>, String> aliveEntities) {
        for (final Node cell : this.battlegrid.getChildren()) {
            ((Button) cell).setGraphic(null);
        }
        for (final Map.Entry<Pair<Integer, Integer>, String> a : aliveEntities.entrySet()) {
            final Button btn = getButton(battlegrid, a.getKey().getValue(), a.getKey().getKey());
            final Image heroImage = new Image(a.getValue());
            final ImageView iv = new ImageView();
            iv.setImage(heroImage);
            iv.setFitWidth(DEFAULT_DIMENSION);
            iv.setPreserveRatio(true);
            iv.setSmooth(true);
            iv.setCache(true);
            btn.setGraphic(iv);
        }

    }

    private Button getButton(final GridPane battlegrid, final int col, final int row) { // NOPMD
        for (final Node node : battlegrid.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return (Button) node;
            }
        }
        return null;
    }

    private void cellClick(final ActionEvent e) {
        final Button btnSource = (Button) e.getSource();
        ControllerImpl.getInstance().gameSelectionAction(bc, btnSource.getId());

    }

    private void cellFocusChanged(final Button btnSource) {
        if (btnSource.isFocused()) {
            btnSource.setStyle("-fx-border-color: #ff0000;-fx-border-width: 1px;");
            this.resetSelectionMovementCandidates();
            ControllerImpl.getInstance().changeGameCursorPosition(GridPane.getRowIndex(btnSource),
                    GridPane.getColumnIndex(btnSource), this.bc);
        } else {
            btnSource.setStyle("-fx-border-color: transparent;");
        }
    }

    private void cellHover(final Button btnSource) { // NOPMD
        if (btnSource.isHover()) {
            btnSource.requestFocus();
            ControllerImpl.getInstance().battleCursorInfo(this.bc);
            btnSource.setStyle("-fx-border-color: #ff0000; -fx-border-width: 1px;");
            ControllerImpl.getInstance().changeGameCursorPosition(GridPane.getRowIndex(btnSource),
                    GridPane.getColumnIndex(btnSource), this.bc);
        } else {
            btnSource.setStyle("-fx-border-color: transparent;");
        }
    }

    /**
     * Show the info of the selected entity.
     * 
     * @param name
     *            name of the hero
     * @param maxHP
     *            number of maximuim health points of the hero
     * @param currentHP
     *            number of current health points of the hero
     * @param attack
     *            attack points of the hero
     * @param defence
     *            defence points of the hero
     * @param movementType
     *            movement type of the hero
     * @param attackType
     *            attack type of the hero
     * @param attackStatus
     *            status of the attack of the hero
     * @param movementStatus
     *            status of the movement of the hero
     */
    public void showSelectionInfo(final String name, final String maxHP, final String currentHP, final String attack,
            final String defence, final String movementType, final String attackType, final String attackStatus,
            final String movementStatus) {
        this.srcName.setText(name);
        this.srcMaxHp.setText(maxHP);
        this.srcHp.setText(currentHP);
        this.srcAtk.setText(attack);
        this.srcDef.setText(defence);
        this.srcMov.setText(movementType);
        this.srcAtkType.setText(attackType);
        this.srcAtkStats.setText(attackStatus);
        this.srcMovStats.setText(movementStatus);
    }

    /**
     * Show the info of the hovered entity.
     * 
     * @param name
     *            name of the hero
     * @param maxHP
     *            number of maximuim health points of the hero
     * @param currentHP
     *            number of current health points of the hero
     * @param attack
     *            attack points of the hero
     * @param defence
     *            defence points of the hero
     * @param movementType
     *            movement type of the hero
     * @param attackType
     *            attack type of the hero
     * @param attackStatus
     *            status of the attack of the hero
     * @param movementStatus
     *            status of the movement of the hero
     */
    public void showCursorInfo(final String name, final String maxHP, final String currentHP, final String attack,
            final String defence, final String movementType, final String attackType, final String attackStatus,
            final String movementStatus) {
        this.trgtName.setText(name);
        this.trgtMaxHp.setText(maxHP);
        this.trgtHp.setText(currentHP);
        this.trgtAtk.setText(attack);
        this.trgtDef.setText(defence);
        this.trgtMov.setText(movementType);
        this.trgtAtkType.setText(attackType);
        this.trgtAtkStats.setText(attackStatus);
        this.trgtMovStats.setText(movementStatus);
    }

    /**
    * 
    */
    public void changeTurn() {
        ControllerImpl.getInstance().changeGameTurn(bc);
    }

    /**
     * 
     * @param turn
     *            String rappresenting the turn of the next player
     */
    public void updateTurn(final String turn) {
        this.playerTurn.setText(turn);
    }

    /**
     * Shows the cells where the hovered entity can be moved.
     * 
     * @param positions
     *            List containing all the cells coordinates avable for the hero to
     *            move in
     */
    public void cursorSelectionMovementCandidates(final List<Pair<Integer, Integer>> positions) {
        if (positions.isEmpty()) {
            this.resetSelectionMovementCandidates();
        }
        for (final Pair<Integer, Integer> p : positions) {
            final Button btn = getButton(battlegrid, p.getValue(), p.getKey());
            btn.setStyle("-fx-color : #0099cc");
        }
    }

    /**
     * Shows the cells where the hovered entity can attack.
     * 
     * @param positions
     *            List containing all the cells coordinates rappresenting the hero
     *            attack range
     */
    public void cursorSelectionAttackCandidates(final List<Pair<Integer, Integer>> positions) {
        if (positions.isEmpty()) {
            this.resetSelectionMovementCandidates();
        }
        for (final Pair<Integer, Integer> p : positions) {
            final Button btn = getButton(battlegrid, p.getValue(), p.getKey());
            btn.setStyle("-fx-color : #990000");
        }

    }

    /**
     * Shows the cells where the selected entity can attack.
     * 
     * @param positions
     *            List containing all the cells coordinates rappresenting the hero
     *            attack range
     */
    public void selectionAttackCandidates(final List<Pair<Integer, Integer>> positions) {
        if (positions.isEmpty()) {
            this.resetSelectionMovementCandidates();
        }
        for (final Pair<Integer, Integer> p : positions) {
            final Button btn = getButton(battlegrid, p.getValue(), p.getKey());
            btn.setStyle("-fx-color : #990000");
        }

    }

    private void resetSelectionMovementCandidates() {
        for (final Node node : this.battlegrid.getChildren()) {
            node.setStyle(null);
        }
    }

    /**
     * Shows the cells where the selected entity can move.
     * 
     * @param positions
     *            List containing all the cell coordinates avable for the hero to
     *            move in
     */
    public void selectionMovementCandidates(final List<Pair<Integer, Integer>> positions) {
        if (positions.isEmpty()) {
            this.resetSelectionMovementCandidates();
        }
        for (final Pair<Integer, Integer> p : positions) {
            final Button btn = getButton(battlegrid, p.getValue(), p.getKey());
            btn.setStyle("-fx-color : #0099cc");
        }
    }

    /**
     * 
     * @param newPos
     *            the new position of the moved hero
     * @param oldPos
     *            the old position of the moved hero
     */
    public void updatePosition(final Pair<Integer, Integer> newPos, final Pair<Integer, Integer> oldPos) {
        final Button newBtn = getButton(battlegrid, newPos.getValue(), newPos.getKey());
        final Button oldBtn = getButton(battlegrid, oldPos.getValue(), oldPos.getKey());
        newBtn.setGraphic(oldBtn.getGraphic());
        oldBtn.setGraphic(null);
    }

    /**
     * 
     * @param victoryMessage
     *            String containing the victory message
     */
    public void victoryMessage(final String victoryMessage) {
        this.winner.setText(victoryMessage);
        this.endTurn.setDisable(true);
    }

    /**
     * exit game function for quit button.
     */
    public void quit() {
        Runtime.getRuntime().exit(0);
    }

}

package view.match;

import java.util.List;
import java.util.Map;

import application.Battleships;
import controller.game.MatchController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.enums.PlayerNumber;
import model.util.Pair;
import view.dialog.DialogType;
import view.scene.SceneName;
import java.util.ArrayList;
import java.util.HashMap;

import static java.util.stream.Collectors.joining;

/**
 * 
 * Implementation of battle's view dynamics.
 *
 */
public class BattleViewImpl implements BattleView {

//    private static final String SEPARATOR = System.getProperty("file.separator");
    private static final String HIT_IMAGE_URL =  "/images/shot/explosion.png";
    private static final String MISS_IMAGE_URL = "/images/shot/miss.png";
    private static final String RIP_IMAGE_URL =  "/images/shot/sunkShip.png";
    @FXML
    private GridPane playerOneGrid, playerTwoGrid;

    @FXML
    private TextField pointsPLOne, pointsPLTwo, shotAvailablePLOne, shotAvailablePLTwo;

    @FXML
    private Label nameOne, nameTwo;

    private GridPane currentVillainGridPane;
    private TextField currentPointsPL, currentShotAvailable;
    private Map<PlayerNumber, Map<Pair<Integer, Integer>, Pane>> panes;
    private Map<PlayerNumber, List<Pair<Integer, Integer>>> hittedCells;

    private MatchController controller;

    private void initGridPane(final GridPane gridPane) {
        for (int col = 0; col < gridPane.getColumnCount(); col++) {
            for (int row = 0; row < gridPane.getRowCount(); row++) {
                final Pane pane = new Pane();
                final int finalRow = row, finalCol = col;
                pane.setStyle("-fx-background-color: #FFFFFF");
                pane.setOnMouseClicked(e -> {
                    if (pane.getParent().equals(this.currentVillainGridPane)) {
                        new Thread(() -> {
                            this.controller.shot(finalRow, finalCol);
                        }).start();
                    }
                });
                gridPane.add(pane, col, row);

                if (gridPane == this.playerOneGrid) {
                    this.panes.get(PlayerNumber.PLAYER_ONE).put(new Pair<Integer, Integer>(row, col), pane);
                } else {
                    this.panes.get(PlayerNumber.PLAYER_TWO).put(new Pair<Integer, Integer>(row, col), pane);
                }
            }
        }
        gridPane.setStyle("-fx-background-color: #000000");
    }

    private void initTextField() {
        this.pointsPLOne.setText("0");
        this.pointsPLOne.setEditable(false);

        this.pointsPLTwo.setText("0");
        this.pointsPLTwo.setEditable(false);

        this.shotAvailablePLOne.setText(Integer.toString(Battleships.getController().getMatchInfo().get().getShipsNumber()));
        this.shotAvailablePLOne.setEditable(false);

        this.shotAvailablePLTwo.setText(Integer.toString(Battleships.getController().getMatchInfo().get().getShipsNumber()));
        this.shotAvailablePLTwo.setEditable(false);
    }

    private void initMaps() {
        this.panes = new HashMap<>();
        this.panes.put(PlayerNumber.PLAYER_ONE, new HashMap<>());
        this.panes.put(PlayerNumber.PLAYER_TWO, new HashMap<>());

        this.hittedCells = new HashMap<>();
        this.hittedCells.put(PlayerNumber.PLAYER_ONE, new ArrayList<>());
        this.hittedCells.put(PlayerNumber.PLAYER_TWO, new ArrayList<>());
    }

    /**
     * Initialize components of view and fields.
     */
    @FXML
    public void initialize() {
        this.initMaps();
        this.initGridPane(this.playerOneGrid);
        this.initGridPane(this.playerTwoGrid);
        this.initTextField();
        this.nameOne
                .setText(Battleships.getController().getMatchInfo().get().getPlayerInfo(PlayerNumber.PLAYER_ONE).getUsername());
        this.nameTwo
                .setText(Battleships.getController().getMatchInfo().get().getPlayerInfo(PlayerNumber.PLAYER_TWO).getUsername());
        this.currentVillainGridPane = this.playerTwoGrid;
        this.currentShotAvailable = this.shotAvailablePLTwo;
        this.currentPointsPL = this.pointsPLOne;
        this.controller = Battleships.getController().getMatchController();
        this.controller.setView(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showCellAlreadyShottedAlert(final Pair<Integer, Integer> cell) {
        final String description = "Cell [line, column]: [" + (cell.getX() + 1) + "," + (cell.getY() + 1) + "] is already shotted.\n"
                + "Select another cell, please.";
        Platform.runLater(() -> {
            Battleships.getController().launchDialog(DialogType.WARNING, "Choiche not valid", "Cell choiced is already shotted!",
                    description);
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showCellAlreadyUsedAlert(final List<Pair<Integer, Integer>> cell) {
        final String description = "Cell [line, column]: "
                + cell.stream().map(e -> "[" + e.getX() + "," + e.getY() + "]").collect(joining(",")) + " already used." + "\n"
                + "Select a different place, please.";
        Platform.runLater(() -> {
            Battleships.getController().launchDialog(DialogType.WARNING, "Choiche not valid", "Position choiced is already used!",
                    description);
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showWinDialog() {
        final String winner = Battleships.getController().getMatchInfo().get()
                .getPlayerInfo(Battleships.getController().getCurrentPlayer().get()).getUsername();

        final String description = "Il giocatore " + winner + " vince la partita!";
        Platform.runLater(() -> {
            Battleships.getController().launchDialog(DialogType.INFORMATION, "Fine partita", "Vittoria!", description);
            Battleships.getController().changeScene(SceneName.MAIN);
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawHit(final Pair<Integer, Integer> cell, final PlayerNumber playerNumber) {
        this.hittedCells.get(playerNumber).add(cell);

        Platform.runLater(() -> {
            final ImageView imageView = new ImageView();
            imageView.setImage(new Image(getClass().getResource(HIT_IMAGE_URL).toExternalForm()));
            imageView.fitWidthProperty().bind(this.getNodeByRowColumnIndex(cell, playerNumber).widthProperty());
            imageView.fitHeightProperty().bind(this.getNodeByRowColumnIndex(cell, playerNumber).heightProperty());

            this.getNodeByRowColumnIndex(cell, playerNumber).getChildren().add(imageView);
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawShip(final List<Pair<Integer, Integer>> cells, final PlayerNumber playerNumber) {

        Platform.runLater(() -> {
            for (final Pair<Integer, Integer> cell : cells) {
                this.getNodeByRowColumnIndex(cell, playerNumber).setStyle("-fx-background-color: #444444");
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawSunkShip(final List<Pair<Integer, Integer>> cells, final PlayerNumber playerNumber) {
        for (final Pair<Integer, Integer> cell : cells) {
            Platform.runLater(() -> {
                final ImageView imageView = new ImageView();
                imageView.setImage(new Image(getClass().getResource(RIP_IMAGE_URL).toExternalForm()));
                imageView.fitWidthProperty().bind(this.getNodeByRowColumnIndex(cell, playerNumber).widthProperty());
                imageView.fitHeightProperty().bind(this.getNodeByRowColumnIndex(cell, playerNumber).heightProperty());

                this.getNodeByRowColumnIndex(cell, playerNumber).getChildren().add(imageView);
            });
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawMissed(final Pair<Integer, Integer> cell, final PlayerNumber playerNumber) {
        Platform.runLater(() -> {
            final ImageView imageView = new ImageView();
            imageView.setImage(new Image(getClass().getResource(MISS_IMAGE_URL).toExternalForm()));
            imageView.fitWidthProperty().bind(this.getNodeByRowColumnIndex(cell, playerNumber).widthProperty());
            imageView.fitHeightProperty().bind(this.getNodeByRowColumnIndex(cell, playerNumber).heightProperty());

            this.getNodeByRowColumnIndex(cell, playerNumber).getChildren().add(imageView);
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void changePlayer() {

        this.showChangePlayerDialog();

        if (Battleships.getController().getCurrentPlayer().get().equals(PlayerNumber.PLAYER_TWO)) {
            this.currentPointsPL = this.pointsPLOne;
            this.currentShotAvailable = this.shotAvailablePLTwo;
            this.currentVillainGridPane = playerTwoGrid;
        } else {
            this.currentPointsPL = this.pointsPLTwo;
            this.currentShotAvailable = this.shotAvailablePLOne;
            this.currentVillainGridPane = playerOneGrid;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPoints(final int point) {
        this.currentPointsPL.setText(Integer.toString(point));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setShotAvailable(final int shotAvailable) {
        this.currentShotAvailable.setText(Integer.toString(shotAvailable));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showChangePlayerDialog() {
        Platform.runLater(() -> {
            Battleships.getController().launchDialog(DialogType.INFORMATION, "Cambio giocatore!", "Cambio giocatore!",
                    "Cambio giocatore!");
            this.controller.showShip();
        });
    }

    private Pane getNodeByRowColumnIndex(final Pair<Integer, Integer> cell, final PlayerNumber playerNumber) {
        return this.panes.get(playerNumber).get(cell);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hideShip(final List<Pair<Integer, Integer>> cells, final PlayerNumber playerNumber) {
        for (final Pair<Integer, Integer> cell : cells) {
            if (!this.hittedCells.get(playerNumber).contains(cell)) {
                Platform.runLater(() -> {
                    this.getNodeByRowColumnIndex(cell, playerNumber).setStyle("-fx-background-color: #FFFFFF");
                });
            }
        }
    }
}

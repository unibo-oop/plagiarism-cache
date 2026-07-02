package view.gameplay;

import java.util.Map;

import controller.gameplay.GameplayController;
import controller.gameplay.GameplayControllerImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import model.basic_component.Cell;
import model.basic_component.StaticPoint2D;
import view.ButtonGridManager;
import view.PasswordInputDialog;

/**
 * Implementation of {@link GameplayUI} using the javafx library.
 */
public final class GameplayUIImpl implements GameplayUI {

    /**
     * The on-screen player grid.
     */
    @FXML
    private GridPane playerGrid;

    /**
     * The on-screen enemy grid.
     */
    @FXML
    private GridPane enemyGrid;

    /**
     * The on-screen player name.
     */
    @FXML
    private Label playerName;

    /**
     * The on-screen enemy name.
     */
    @FXML
    private Label enemyName;

    /**
     * A map representing the association between a button and its position.
     */
    private Map<StaticPoint2D, Button> playerGridLogic;

    /**
     * Same as above but for the enemy.
     */
    private Map<StaticPoint2D, Button> enemyGridLogic;

    /**
     * The associated controller.
     */
    private final GameplayController controller = new GameplayControllerImpl();

    @Override
    public void initialize() {
        enemyGridLogic = ButtonGridManager.buttonMap(controller.getGridSize(), controller.getGridSize());
        ButtonGridManager.associateWithUIGrid(enemyGridLogic, enemyGrid);
        enemyGridLogic.entrySet().forEach(playerGridElem -> playerGridElem.getValue().setOnAction(action -> interact(playerGridElem.getKey())));

        playerGridLogic = ButtonGridManager.buttonMap(controller.getGridSize(), controller.getGridSize());
        ButtonGridManager.associateWithUIGrid(playerGridLogic, playerGrid);
        playerGridLogic.entrySet().forEach(enemyGridElem -> enemyGridElem.getValue().setDisable(true));

        playerName.setText(controller.getPlayerName());
        enemyName.setText(controller.getEnemyName());

        String pin;
        do {
            pin = PasswordInputDialog.createPasswordInputDialog(controller.getPlayerName(), "Provide your password below");
        } while (!pin.equals(controller.getPlayerPassword()));

        updatetEnemyGrid();
        updatePlayerGrid();
    }

    @Override
    public void update() {
        updatePlayerGrid();
        updatetEnemyGrid();
    }

    private void setCellColorStatus(final Button button, final Cell.Status status) {
        button.setStyle(status.equals(Cell.Status.OCCUPIED_AND_TARGETED) ? "-fx-background-color: red;" : "-fx-background-color: blue;"); 
    }

    /**
     * Asks the controller for information about 
     *          the enemy's grid in order to update the UI.
     */
    private void updatetEnemyGrid() {
        controller.getEnemyHittedSpotSet().forEach(cell -> {
            setCellColorStatus(enemyGridLogic.get(cell.getCoordinate()), cell.getStatus());
            enemyGridLogic.get(cell.getCoordinate()).setStyle(cell.getStatus().equals(Cell.Status.OCCUPIED_AND_TARGETED) ? "-fx-background-color: red;" : "-fx-background-color: blue;"); 
            enemyGridLogic.get(cell.getCoordinate()).setDisable(true);
        });
        controller.getEnemeySunkenShip().stream().flatMap(ship -> ship.getCellSet().stream())
                    .forEach(cell -> enemyGridLogic.get(cell.getCoordinate()).setStyle("-fx-background-color: green;"));
    }

    /**
     * Asks the controller for information 
     *          about the player's grid in order to update the UI.
     */
    private void updatePlayerGrid() {
       controller.getPlayerNavy().getShips().forEach(ship -> {
           ship.getCellSet().forEach(cell -> {
               playerGridLogic.get(cell.getCoordinate()).setStyle("-fx-background-color: black;"); 
           });
       });
       controller.getPlayerHittedSpotSet()
                 .forEach(cell -> setCellColorStatus(playerGridLogic.get(cell.getCoordinate()), cell.getStatus()));
    }

    @Override
    public void reset() {
        playerGridLogic.values().forEach(button -> button.setStyle(""));
        enemyGridLogic.values().forEach(button -> button.setStyle(""));
    }

    /**
     * The controller will be notified about the interaction with the grid.
     */
    @Override
    public void interact(final StaticPoint2D location) {
        controller.shootEnemy(location);
    }
}

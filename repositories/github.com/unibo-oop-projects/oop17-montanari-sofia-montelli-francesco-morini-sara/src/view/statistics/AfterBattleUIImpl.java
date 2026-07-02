package view.statistics;

import controller.after_battle.AfterBattleUIController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.statistic.Statistic;
/**
 * 
 * Class representing the after battle scene.
 *
 */
public final class AfterBattleUIImpl implements  AfterBattleUI<AfterBattleUIController>  {

    @FXML
    private TableView<Statistic<?>> playerOneTable;

    @FXML
    private TableView<Statistic<?>> playerTwoTable;

    @FXML
    private TableColumn<Statistic<?>, String> nameColumnOne;

    @FXML
    private TableColumn<Statistic<?>, String> nameColumnTwo;

    @FXML
    private TableColumn<Statistic<?>, String> valueColumnOne;

    @FXML
    private TableColumn<Statistic<?>, String> valueColumnTwo;

    @FXML
    private Label playerOneLabel;

    @FXML
    private Label playerTwoLabel;

    private AfterBattleUIController controller;

    @Override
    public void exit() {
        System.exit(0);
    }

    @Override
    public void setController(final AfterBattleUIController controller) {
       this.controller = controller;
    }

    @Override
    public AfterBattleUIController getController() {
        return controller;
    }

    @Override
    public void initialize() {
        if (controller == null) {
            throw new RuntimeException("Uninitialized controller");
        }
        showPlayerOne();
        showPlayerTwo();
    }

    @Override
    public void showPlayerOne() {
        playerOneTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        playerOneLabel.setText(controller.getPlayerOneName());
        nameColumnOne.setCellValueFactory((s) -> new SimpleStringProperty(s.getValue().getName()));
        valueColumnOne.setCellValueFactory((s) -> new SimpleStringProperty(s.getValue().getValue().toString()));
        playerOneTable.setItems(FXCollections.observableArrayList(controller.getPlayerOneStatistics()));

    }

    @Override
    public void showPlayerTwo() {

        playerTwoTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        playerTwoLabel.setText(controller.getPlayerTwoName());

        nameColumnTwo.setCellValueFactory((s) -> new SimpleStringProperty(s.getValue().getName()));

        valueColumnTwo.setCellValueFactory((s) -> new SimpleStringProperty(s.getValue().getValue().toString()));


        playerTwoTable.setItems(FXCollections.observableArrayList(controller.getPlayerTwoStatistics()));

    }

}

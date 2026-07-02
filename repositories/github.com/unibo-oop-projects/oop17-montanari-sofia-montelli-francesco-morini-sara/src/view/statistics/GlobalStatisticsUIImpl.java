package view.statistics;

import controller.global_statistics.GlobalStatisticsUIController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.statistic.Statistic;
import view.MasterViewImpl;
/**
 * 
 * Class representing the global statistic scene.
 *
 */
public final class GlobalStatisticsUIImpl implements GlobalStatisticsUI<GlobalStatisticsUIController> {

    @FXML
    private TextField nameTextField;

    @FXML
    private TableView<Statistic<?>> table;

    private GlobalStatisticsUIController controller;

    @FXML
    private TableColumn<Statistic<?>, String> nameColumn;

    @FXML
    private TableColumn<Statistic<?>, String> valueColumn;

    @Override
    public GlobalStatisticsUIController getController() {
        return controller;
    }

    @Override
    public void setController(final GlobalStatisticsUIController controller) {
        this.controller = controller;
    }

    @Override
    public void initialize() {
        if (controller == null) {
            throw new RuntimeException("Uninitialized controller");
        }
        nameTextField.textProperty().addListener((obj, oldValue, newValue) -> {
            if (newValue.length() > 0) {
                showPlayerStatistics(newValue);
            } else {
                showGlobalStatistics();
            }
        });
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        nameColumn.setCellValueFactory((s) -> new SimpleStringProperty(s.getValue().getName()));
        valueColumn.setCellValueFactory((s) -> new SimpleStringProperty(s.getValue().getValue().toString()));
        showGlobalStatistics();
    }

    @Override
    public void exit() {
        MasterViewImpl.getInstance().showMainMenu();
    }

    @Override
    public void showGlobalStatistics() {
         table.setItems(FXCollections.observableArrayList(controller.getGlobalStatistics()));
    }

    @Override
    public void showPlayerStatistics(final String player) {
         table.setItems(FXCollections.observableArrayList(controller.getPlayerGlobalStatistics(player)));
    } 

}

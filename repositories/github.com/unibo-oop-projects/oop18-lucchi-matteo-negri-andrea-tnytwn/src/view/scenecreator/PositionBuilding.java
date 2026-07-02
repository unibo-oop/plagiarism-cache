package view.scenecreator;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.construction.ConstructionType;
import model.game.GameImpl;
import utility.PositionControllerHelper;
import utility.SystemPropertiesHelper;

/**
 * 
 */
public class PositionBuilding {

    /**
     * 
     * @param type
     *          type of construction.
     */
    public PositionBuilding(final ConstructionType type) {
        try {
            final BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("/view/scenes/PositionBuilding.fxml"));
            final Label labelType = (Label) ((HBox) ((VBox) root.getChildren().get(1)).getChildren().get(0)).getChildren().get(1);
            final Label labelCost = (Label) ((HBox) ((VBox) root.getChildren().get(1)).getChildren().get(1)).getChildren().get(1);
            final Label labelInfo = (Label) root.getChildren().get(2);
            labelType.setText(" " + type.getType());
            labelCost.setText(" " + type.getCost().toString() + " (" + GameImpl.getGameImpl().getResources().get(0).getValue() + ")");
            labelInfo.setText("Produzione giornaliera: " + type.getMoneyProduction().toString() + " - Consumo energetico: " + type.getEnergyInfluence().toString()
                    + "\nConsumo idrico: " + type.getWaterInfluence().toString() + " - Popolazione: " + type.getPopulationHousingCapacity().toString());
            final Scene scene = new Scene(root, (double) SystemPropertiesHelper.PREFERRED_WIDTH_RESOLUTION / 3, (double) SystemPropertiesHelper.PREFERRED_HEIGHT_RESOLUTION / 2);
            final Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setOnCloseRequest(e -> {
                PositionControllerHelper.setDefault();
            });
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

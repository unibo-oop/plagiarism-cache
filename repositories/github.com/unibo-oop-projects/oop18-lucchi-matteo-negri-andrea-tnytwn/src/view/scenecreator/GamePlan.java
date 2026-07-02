package view.scenecreator;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.construction.ConstructionType;
import model.game.GameImpl;
import utility.SystemPropertiesHelper;

/**
 * This class creates the view of the Game Map.
 */
public class GamePlan {


    /**
     * @param event
     *          the event that started the creation of GamePlan
     */
    public GamePlan(final ActionEvent event) {
        try {
            final BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("/view/scenes/GameMap.fxml"));
            root.setPrefWidth((double) SystemPropertiesHelper.PREFERRED_WIDTH_RESOLUTION);
            root.setPrefHeight((double) SystemPropertiesHelper.PREFERRED_HEIGHT_RESOLUTION);
            final GridPane buildingsMenu = (GridPane) root.getChildren().get(1);
            final VBox buildings = (VBox) buildingsMenu.getChildren().get(0);
            Button btn = (Button) buildings.getChildren().get(0);
            buildingsMenu.getColumnConstraints().get(0).setPrefWidth(btn.getPrefWidth());
            buildingsMenu.getColumnConstraints().get(0).setPercentWidth(90);
            buildings.setPrefHeight((double) SystemPropertiesHelper.PREFERRED_HEIGHT_RESOLUTION);
            buildings.setPrefWidth((double) btn.getPrefWidth());
            buildings.setSpacing((double) SystemPropertiesHelper.DEFAULT_SPACING / ConstructionType.values().length);
            final ScrollBar scrollBar = (ScrollBar) buildingsMenu.getChildren().get(1);
            scrollBar.setPrefHeight((double) SystemPropertiesHelper.PREFERRED_HEIGHT_RESOLUTION);
            buildingsMenu.getColumnConstraints().get(1).setPercentWidth(10);
            final VBox menu = (VBox) root.getChildren().get(2);
            btn = (Button) menu.getChildren().get(0);
            menu.setPrefHeight((double) SystemPropertiesHelper.PREFERRED_HEIGHT_RESOLUTION);
            menu.setPrefWidth((double) btn.getPrefWidth());
            menu.setSpacing((double) SystemPropertiesHelper.DEFAULT_SPACING);
            final Scene scene = new Scene(root, (double) SystemPropertiesHelper.PREFERRED_WIDTH_RESOLUTION, (double) SystemPropertiesHelper.PREFERRED_HEIGHT_RESOLUTION);
            scrollBar.setMin(10);
            scrollBar.setMax(100);
            scrollBar.setValue(scrollBar.getMax() / 2);
            buildings.setTranslateY(scrollBar.getValue() / 2);
            scrollBar.setVisibleAmount(scrollBar.getVisibleAmount() * 2);
            scrollBar.valueProperty().addListener(e -> {
                buildings.setTranslateY(scrollBar.getValue());
            });
            final Stage gamePlan = new Stage();
            gamePlan.setTitle("Tiny Town");
            gamePlan.setScene(scene);
            gamePlan.setResizable(false);
            gamePlan.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
            gamePlan.setOnCloseRequest(e -> {
                GameImpl.getGameImpl().getTime().cancel();
            });
        } catch (Exception e) {
                e.printStackTrace();
        }
    }
}

package it.unibo.oop.bbgmm.Boundary;

import it.unibo.oop.bbgmm.Utilities.Resolution;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameOver extends Scene {

    private static Stage gameOverStage;
    private final AnchorPane pane;

    private int currentItem = 0;
    private VBox menuBox;
    private final MenuItem itemRestart = new MenuItem("RESTART");
    private final MenuItem itemExit = new MenuItem("EXIT");

    public GameOver(){
        super(new AnchorPane(), Resolution.getSmallWidth(), Resolution.getSmallHeight());

        //button pressed, click with mouse or both of them?
        this.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.UP) {
                if(currentItem > 0) {
                    getMenuItem(currentItem).setActive(false);
                    getMenuItem(--currentItem).setActive(true);
                }
            }

            if(event.getCode() == KeyCode.DOWN) {
                if(currentItem < menuBox.getChildren().size() - 1 ){
                    getMenuItem(currentItem).setActive(false);
                    getMenuItem(++currentItem).setActive(true);
                }
            }

            if(event.getCode() == KeyCode.ENTER){
                getMenuItem(currentItem).activate();
            }
        });

        pane = new AnchorPane();
        menuBox = new VBox(25, itemRestart, itemExit);

        buttonActions();

        menuBox.setAlignment(Pos.TOP_CENTER);

        menuBox.setTranslateX(365);
        menuBox.setTranslateY(350);

        getMenuItem(0).setActive(true);

        pane.getChildren().add(menuBox);

        pane.setId("-- Game Over --");
        this.getStylesheets().add("Style.css");

        this.setRoot(pane);
    }

    private MenuItem getMenuItem(int index){
        return (MenuItem)menuBox.getChildren().get(index);
    }

    private void buttonActions(){
        /*itemRestart.setOnActivate(() -> {
            this.gameOverStage.setScene(GameFieldView.getGameFiledView(this.gameOverStage));
        });*/

        itemExit.setOnActivate(() -> System.exit(0));
    }

    /**
     * Getter for the scene
     * @param stage
     * @return GameOver
     */

    public static GameOver getGameOver(Stage stage){
        gameOverStage = stage;
        gameOverStage.setHeight(Resolution.getSmallHeight());
        gameOverStage.setWidth(Resolution.getSmallWidth());
        return new GameOver();
    }
}

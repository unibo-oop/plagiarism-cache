package it.unibo.oop.bbgmm.Boundary;

import it.unibo.oop.bbgmm.Utilities.Resolution;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author Manuel
 * Scene for the settings Menu used to set the Resolution
 */

public class SettingsMenu extends Scene {

    private static Stage primaryStage;
    private final AnchorPane pane;

    private VBox menuBox;
    private int currentItem = 0;
    private final MenuItem itemSmallScreen = new MenuItem("Small Screen");
    private final MenuItem itemFullScreen = new MenuItem("Full Screen");
    private final MenuItem itemBack = new MenuItem("BACK");

    public SettingsMenu() {
        super(new AnchorPane(), Resolution.getSmallWidth(), Resolution.getSmallHeight());

        //it intercepts the button presses
        this.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP) {
                if (currentItem > 0) {
                    getMenuItem(currentItem).setActive(false);
                    getMenuItem(--currentItem).setActive(true);
                }
            }

            if (event.getCode() == KeyCode.DOWN) {
                if (currentItem < menuBox.getChildren().size() - 1) {
                    getMenuItem(currentItem).setActive(false);
                    getMenuItem(++currentItem).setActive(true);
                }
            }

            if (event.getCode() == KeyCode.ENTER) {
                getMenuItem(currentItem).activate();
            }
        });

        pane = new AnchorPane();

        menuBox = new VBox(40,
                itemSmallScreen,
                itemFullScreen,
                itemBack);

        buttonActions();

        if(Resolution.isFullScreen()){
            itemSmallScreen.setUnderline(false);
            itemFullScreen.setUnderline(true);
        }
        else{
            itemSmallScreen.setUnderline(true);
            itemFullScreen.setUnderline(false);
        }

        menuBox.setAlignment(Pos.TOP_CENTER);

        menuBox.setTranslateX(315);
        menuBox.setTranslateY(300);

        getMenuItem(0).setActive(true);

        pane.getChildren().add(menuBox);

        pane.setId("settingsMenu");
        this.getStylesheets().add("Style.css");

        this.setRoot(pane);
    }

    /**
     * Method used to get the requested element of the buttons' box
     */
    private MenuItem getMenuItem(int index) {
        return (MenuItem)menuBox.getChildren().get(index);
    }

    /**
     * Method used to set the action for each button
     */
    private void buttonActions(){
        itemBack.setOnActivate(() -> this.primaryStage.setScene(MainMenu.getMainMenu(this.primaryStage)));
        itemSmallScreen.setOnActivate(() -> {
            Resolution.setSmallResolution();
            itemSmallScreen.setUnderline(true);
            itemFullScreen.setUnderline(false);
        });
        itemFullScreen.setOnActivate(() -> {
            Resolution.setFullResolution();
            itemSmallScreen.setUnderline(false);
            itemFullScreen.setUnderline(true);
        });
    }

    /**
     * Getter for the Scene
     * @param stage
     * @return SettingsMenu
     */
    public static SettingsMenu getSettingsMenu(Stage stage) {
        primaryStage = stage;
        primaryStage.setHeight(Resolution.getSmallHeight());
        primaryStage.setWidth(Resolution.getSmallWidth());
        return new SettingsMenu();
    }
}
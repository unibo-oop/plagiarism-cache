package it.dpg.maingame.launcher;

import it.dpg.maingame.view.menu.MenuGUI;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage pStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        setPrimaryStage(stage);
        MenuGUI m = new MenuGUI();
        m.initializeGUI(stage);
    }

    public static Stage getPrimaryStage() {
        return pStage;
    }

    private void setPrimaryStage(Stage pStage) {
        Main.pStage = pStage;
    }
}

package view.frames;

import controller.ViewManager;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.View;
import view.ViewImpl;

/**
 * Abstract class for a generic view frame.
 */
public abstract class AbstractViewFrame implements ViewFrame {
    static final int TAMAGOTCHIFONTSIZE = 6;
    static final int TUTORIALBUTTONFONTSIZE = 2;
    static final int TUTORIALBUTTONINSETS = 30;
    static final int LOGINFONTSIZE = 5;
    static final int DIFFICULTYFONTSIZE = 2;
    static final int DIFFICULTYSPACING = 110;
    static final int LOGINBUTTONFONTSIZE = 3;
    static final int DIFFICULTYBUTTONFONTSIZE = 2;
    static final int LOGOLOGINSIZE = 100;
    static final int CURRENTLOGOSIZE = 200;
    static final int SCREEN_HEIGHT = 730;
    static final int SCREEN_WIDTH = 1366;
    static final int PANEGAP = 10;
    static final int RANKINGHGAP = 70;
    static final int RANKINGCOLUMNSFONTSIZE = 4;
    static final double RANKINGCELLSFONTSIZE = 2.2;
    static final int TUTORIALIMGHEIGHT = 500;
    static final int TUTORIALIMGWIDTH = 700;
    static final int TUTORIALSHOPINVIMGHEIGHT = 650;
    static final int TUTORIALSHOPINVIMGWIDTH = 500;
    private Scene scene;
    private ViewManager controller;
    private View view;
    private Stage stage;

    @Override
    public abstract void start(Stage primaryStage) throws Exception;

    @Override
    public abstract void setFrame();

    @Override
    public void setStage(final Stage newStage) {
        this.stage = newStage;
    };

    @Override
    public void setScene(final Parent pane, final double width, final double height) {
        if (getScene() == null) {
            this.scene = new Scene(pane, width, height);
            getStage().setScene(getScene());
        } else {
            getStage().setScene(this.getScene());
        }
        getStage().setResizable(false);
        getStage().show();
    };

    @Override
    public void setView(final ViewImpl newManager) {
        this.view = newManager;
    };

    @Override
    public void setController(final ViewManager newController) {
        this.controller = newController;
    }

    @Override
    public void setExitOperation() {
        getStage().setOnCloseRequest(e -> {
            clearStage();
            System.exit(0);
        });
    }

    @Override
    public abstract void clearStage();

    /**
     * @return scene
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * @return controller
     */
    public ViewManager getController() {
        return controller;
    }

    /**
     * @return View
     */
    public View getView() {
        return view;
    }

    /**
     * @return Stage
     */
    public Stage getStage() {
        return stage;
    }
}

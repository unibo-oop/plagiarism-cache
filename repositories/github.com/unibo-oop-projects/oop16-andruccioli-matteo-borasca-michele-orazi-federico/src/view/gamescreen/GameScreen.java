package view.gamescreen;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.ViewImpl;

/**
 * 
 * Class representation of the main program's View. It's the gameplay view, responsible of all user interaction 
 * on world map or related view components. It contains:
 * <ul>
 * <li> {@link WorldMap}, displays the map
 * <li> {@link Toolbar}, sideBar with info about players and states
 * <li> {@link FooterBattleInfo}, footer that contains all info regarding a battle
 * </ul>
 * <p>
 *
 */
public class GameScreen extends Application {

    private final WorldMap worldMap = new WorldMap(ViewImpl.getIstance().getGameMap().getFilePath());
    private final Toolbar toolbar = new Toolbar();
    private final FooterBattleInfo footerBattleInfo = new FooterBattleInfo();
    private final FooterPlayerInfo footerPlayerInfo = new FooterPlayerInfo();

    private static final double WIDTH = Screen.getPrimary().getBounds().getWidth();
    private static final double HEIGHT = Screen.getPrimary().getBounds().getHeight();

    @Override
    public void start(final Stage stage) {
        /* Register GameScreen in ViewImpl */
        ViewImpl.getIstance().registerGameScreen(this);

        final BorderPane mainPane = new BorderPane();
        final Scene scene = new Scene(mainPane, WIDTH, HEIGHT);
        scene.getStylesheets().add("/Style.css");


        toolbar.registerComponents(worldMap);
        worldMap.registerStateListener(footerPlayerInfo, footerBattleInfo);
        footerBattleInfo.registerButtonsListener(worldMap.getChooserSlider(), worldMap.getMoverSlider());

        mainPane.setCenter(worldMap);
        mainPane.setTop(toolbar);
        mainPane.setBottom(new VBox(footerPlayerInfo, footerBattleInfo));
        stage.setScene(scene);
        stage.setResizable(false);
        //stage.setFullScreen(true);
        stage.setMaximized(true);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.centerOnScreen();
        //stage.fullScreenExitKeyProperty().set(KeyCombination.NO_MATCH);
        stage.show();

        /* Start game! */
        ViewImpl.getIstance().getController().initializeMap();

        mainPane.requestFocus();
    }

    /**
     * 
     * WorldMap getter.
     * 
     * @return
     *          The object worldMap.
     * 
     */
    public WorldMap getWorldMap() {
        return worldMap;
    }

    /**
     * 
     * Toolbar getter.
     * 
     * @return
     *          The object toolbar.
     * 
     */
    public Toolbar getToolbar() {
        return toolbar;
    }

    /**
     * 
     * FooterBattleInfo getter.
     * 
     * @return
     *          The object FooterInfoBox.
     * 
     */
    public FooterBattleInfo getFooterBattleInfo() {
        return footerBattleInfo;
    }

    /**
     * 
     * FooterPlayerInfo getter.
     * 
     * @return
     *          The object FooterInfoBox.
     * 
     */
    public FooterPlayerInfo getFooterPlayerInfo() {
        return footerPlayerInfo;
    }
}

package view;

import java.awt.Toolkit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.application.Application;
import model.player.PlayerInfo;
import utilities.enumerations.IconType;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import view.dialogs.MoveDialog;
import view.dialogs.Notification;
import view.scenes.BoardScene;
import view.scenes.MenuScene;
import view.scenes.SetUpGameScene;

/**
 * Class which implements the View Interface.
 */
public class ViewImpl extends Application implements View {
    private static final String ICON = "/images/icon.png";
    private static final double SCREEN_H = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    private static final double SCREEN_W = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private static Stage primaryStage;

    private Map<String, ImageView> playersImage;

    private static void setPrimaryStage(final Stage stage) {
        ViewImpl.primaryStage = stage;
    }

    /**
     * Utility method to get the Stage.
     * 
     * @return stage stage to get.
     */
    public static Stage getPrimaryStage() {
        return ViewImpl.primaryStage;
    }

    @Override
    public void init(final String... args) {
        Application.launch(ViewImpl.class);
    }

    @Override
    public void start(final Stage arg0) {
        ViewImpl.setPrimaryStage(arg0);
        final Scene scene = new Scene(MenuScene.createPane());
        scene.getStylesheets().add("/style/style.css");
        getPrimaryStage().setHeight(SCREEN_H);
        getPrimaryStage().setWidth(SCREEN_W);
        getPrimaryStage().getIcons().add(new Image(ICON));
        getPrimaryStage().setFullScreen(true);
        getPrimaryStage().setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        getPrimaryStage().centerOnScreen();
        getPrimaryStage().setOnCloseRequest(evt -> {
            evt.consume();
        });
        getPrimaryStage().setScene(scene);
        getPrimaryStage().show();
        getPrimaryStage().setResizable(false);
    }

    @Override
    public void switchScene(final String newScene) {
        Parent scene = null;
        switch (newScene) {
        case "MENUSCENE":
            try {
                scene = MenuScene.createPane();
                getPrimaryStage().getScene().setRoot(scene);
            } catch (Exception e) {
                e.printStackTrace();
            }
            break;
        case "SETUPGAME":
            scene = SetUpGameScene.createPane();
            getPrimaryStage().getScene().setRoot(scene);
            break;
        default:
            break;
        }
    }

    @Override
    public void switchBoardScene(final List<PlayerInfo> l) {
        Parent scene = null;
        playersImage = new HashMap<>();
        scene = BoardScene.createPane(l, playersImage);
        getPrimaryStage().getScene().setRoot(scene);
    }

    @Override
    public void drawNotification(final String str, final IconType icon) {
        Notification.getNotification().drawNotification(getPrimaryStage(), str, icon);
    }

    @Override
    public void updatePlayerPosition(final PlayerInfo p) {
        if (playersImage.containsKey(p.getName().toString())) {
            BoardScene.updatePlayerPos(playersImage.get(p.getName().toString()), p.getPosition());
        }
    }

    @Override
    public void removePawnPlayer(final PlayerInfo p) {
        if (playersImage.containsKey(p.getName().toString())) {
            BoardScene.removePlayerPos(playersImage.get(p.getName().toString()));
            playersImage.remove(p.getName().toString());
        }
    }

    @Override
    public void showRooms(final int d) {
        MoveDialog.getDialog().createChooseDialog(d);
    }

    @Override
    public void updateNextPlayerView(final PlayerInfo pAttual) {
        BoardScene.updateCurrentAvatar(pAttual.getName().getImagePath());
        BoardScene.updateCurrentName(pAttual.getName());
        BoardScene.refreshPlayerHistory(pAttual.getHistory());
        BoardScene.updateNote(pAttual.getClues());
        BoardScene.refreshTextArea(pAttual.getNotes());
    }

    @Override
    public void updateHistoryCurrentPlayer(final String s) {
        BoardScene.updatePlayerHistory(s);
    }
}
package application;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.SoundManager;
import model.SoundManagerImpl;
import view.SceneManager;
import view.Scenes;

/**
 * This class represent the Main class of the JavaFX-based application.
 */
public final class Main extends Application {

    private static SoundManager backgroundMusic = new SoundManagerImpl("music/Funny-background-music-for-games.wav");

    /**
     * Returns the background music.
     * 
     * @return the background music
     */
    public static SoundManager getBackgroundMusic() {
        return backgroundMusic;
    }

    @Override
    public void start(final Stage stage) throws Exception {
        stage.setResizable(false);
        stage.setTitle("UNItype - Typing and Spelling");
        stage.getIcons().add(new Image(Main.class.getResourceAsStream("/images/unicornIcon.png")));
        stage.initStyle(StageStyle.UNDECORATED);
        SceneManager.setPrimaryStage(stage);
        SceneManager.showScene(Scenes.MENU.getNewScene());
        stage.show();
        backgroundMusic.loop();
    }

    /**
     * Starts the application.
     * 
     * @param args unused
     */
    public static void main(final String[] args) {
        launch();
    }

}

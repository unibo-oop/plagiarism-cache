package view.image;

import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

import application.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utilities.exception.IllegalInitializationException;
import view.display.ScreenUtilities;

/**
 * Provides methods for loading or reloading objects.
 */
public final class Loader {

    private static final Map<ImageID, Image> IMAGE_TABLE = new EnumMap<>(ImageID.class);

    static {
        initializeData();
    }

    private Loader() {
    }

    /**
     * Loads the FXML file and sets it in the root of the scene.
     * @param path The path of the FXML file
     * @return The fxmlLoader used to load the FXML file
     * @throws IOException If an error occurs during loading
     */
    public static FXMLLoader loadFXML(final String path) throws IOException {
        final FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemResource(path));
        Main.getScene().setRoot(fxmlLoader.load());
        return fxmlLoader;
    }

    /**
     * Loads and returns the Image specified in the path.
     * @param path The path of the image
     * @return the Image specified in the path
     */
    public static Image loadImage(final String path) {
        return new Image(ClassLoader.getSystemResource(path).toString());
    }

    /**
     * Returns the Image specified by the image ID.
     * @param imageID The imageID
     * @return The Image specified in the path
     */
    public static Image loadImage(final ImageID imageID) {
        //return new Image(new File(path).toURI().toString());
        return IMAGE_TABLE.get(imageID);
    }

    /**
     * Loads and returns the ImageView specified in the path.
     * @param imageID The imageID
     * @return the ImageView specified in the path
     */
    public static ImageView loadImageView(final ImageID imageID) {
        final Image image = loadImage(imageID);
        final ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(image.getWidth());
        imageView.setFitHeight(image.getHeight());
        return imageView;
    }

    /**
     * Loads and returns the ImageView specified in the path but already resized to the correct scale.
     * @param imageID The imageID
     * @return the ImageView specified in the path
     */
    public static ImageView loadResizeImageView(final ImageID imageID) {
        final ImageView imageView = loadImageView(imageID);
        imageView.setScaleX(ScreenUtilities.getCurrentScaleFactor());
        imageView.setScaleY(ScreenUtilities.getCurrentScaleFactor());
        imageView.setFitWidth(imageView.getFitWidth() * ScreenUtilities.getCurrentScaleFactor());
        imageView.setFitHeight(imageView.getFitHeight() * ScreenUtilities.getCurrentScaleFactor());
        return imageView;
    }

    /**
     * Changes the image in the imageView with the image specified in the path and sets 
     * fitWidth and fitHeight respectively with the width and height of the loaded image.
     * @param imageID The imageID
     * @param imageView The ImageView in which the image needs to be changed
     */
    public static void changeImageView(final ImageID imageID, final ImageView imageView) {
        final Image image = loadImage(imageID);
        imageView.setImage(image);
        imageView.setFitWidth(image.getWidth());
        imageView.setFitHeight(image.getHeight());
    }

    /**
     * Loads all the images.
     */
    private static void initializeData() {
        IMAGE_TABLE.putAll(Map.ofEntries(
                Map.entry(ImageID.DEFAULT, Loader.loadImage("images/spaceships/reference.png")),

                Map.entry(ImageID.BACKGROUND_INITIAL_MENU, Loader.loadImage("images/initialMenuBackground.png")),
                Map.entry(ImageID.BACKGROUND_MAIN_MENU, Loader.loadImage("images/mainMenuBackground.png")),
                Map.entry(ImageID.BACKGROUND_MENU, Loader.loadImage("images/menuBackground.png")),

                Map.entry(ImageID.BUTTON_START, Loader.loadImage("images/buttons/startButton.png")),
                Map.entry(ImageID.BUTTON_LEADERBOARD, Loader.loadImage("images/buttons/leaderboardButton.png")),
                Map.entry(ImageID.BUTTON_OPTIONS, Loader.loadImage("images/buttons/optionsButton.png")),
                Map.entry(ImageID.BUTTON_QUIT, Loader.loadImage("images/buttons/quitButton.png")),
                Map.entry(ImageID.BUTTON_YES, Loader.loadImage("images/buttons/yesButton.png")),
                Map.entry(ImageID.BUTTON_NO, Loader.loadImage("images/buttons/noButton.png")),
                Map.entry(ImageID.BUTTON_OK, Loader.loadImage("images/buttons/okButton.png")),
                Map.entry(ImageID.BUTTON_BACK, Loader.loadImage("images/buttons/backButton.png")),
                Map.entry(ImageID.BUTTON_RADIO, Loader.loadImage("images/buttons/radioButton.png")),
                Map.entry(ImageID.BUTTON_PLUS, Loader.loadImage("images/buttons/plusButton.png")),
                Map.entry(ImageID.BUTTON_MINUS, Loader.loadImage("images/buttons/minusButton.png")),
                Map.entry(ImageID.BUTTON_RIGHTARROW, Loader.loadImage("images/buttons/rightArrowButton.png")),
                Map.entry(ImageID.BUTTON_LEFTARROW, Loader.loadImage("images/buttons/leftArrowButton.png")),
                Map.entry(ImageID.SPACESHIP_1_BUTTON, Loader.loadImage("images/buttons/playerSpaceship1Button.png")),
                Map.entry(ImageID.SPACESHIP_2_BUTTON, Loader.loadImage("images/buttons/playerSpaceship2Button.png")),
                Map.entry(ImageID.SPACESHIP_3_BUTTON, Loader.loadImage("images/buttons/playerSpaceship3Button.png")),

                Map.entry(ImageID.MOUSE_POINTER, Loader.loadImage("images/mousePointer.png")),
                Map.entry(ImageID.MOUSE_OVER, Loader.loadImage("images/mouseOver.png")),

                Map.entry(ImageID.TEXT_1280X720, Loader.loadImage("images/texts/1280x720Text.png")),
                Map.entry(ImageID.TEXT_1600X900, Loader.loadImage("images/texts/1600x900Text.png")),
                Map.entry(ImageID.TEXT_1920X1080, Loader.loadImage("images/texts/1920x1080Text.png")),
                Map.entry(ImageID.TEXT_2560X1440, Loader.loadImage("images/texts/2560x1440Text.png")),
                Map.entry(ImageID.TEXT_3840X2160, Loader.loadImage("images/texts/3840x2160Text.png")),
                Map.entry(ImageID.TEXT_MUSIC, Loader.loadImage("images/texts/musicText.png")),
                Map.entry(ImageID.TEXT_AUDIOVOLUME, Loader.loadImage("images/texts/audioVolText.png")),
                Map.entry(ImageID.TEXT_FULLSCREEN, Loader.loadImage("images/texts/fullscreenText.png")),
                Map.entry(ImageID.TEXT_WINDOWED, Loader.loadImage("images/texts/windowedText.png")),
                Map.entry(ImageID.TEXT_QUIT, Loader.loadImage("images/texts/quitText.png")),

                Map.entry(ImageID.OBJECT_BONUS, Loader.loadImage("images/objects/bonus.png")),
                Map.entry(ImageID.OBJECT_CROSSHAIR, Loader.loadImage("images/objects/crosshair.png")),
                Map.entry(ImageID.OBJECT_ENEMY_THRUSTER, Loader.loadImage("images/objects/enemy_thruster.png")),
                Map.entry(ImageID.OBJECT_PLAYER_THRUSTER, Loader.loadImage("images/objects/player_thruster.png")),

                Map.entry(ImageID.SPACESHIP_ENEMY_FIGHTER, Loader.loadImage("images/spaceships/enemy_spaceship_1.png")),
                Map.entry(ImageID.SPACESHIP_ENEMY_JUGGERNAUT, Loader.loadImage("images/spaceships/enemy_spaceship_2.png")),
                Map.entry(ImageID.SPACESHIP_ENEMY_CUTTER, Loader.loadImage("images/spaceships/enemy_spaceship_3.png")),
                Map.entry(ImageID.SPACESHIP_PLAYER_FIGHTER, Loader.loadImage("images/spaceships/player_spaceship_1.png")),
                Map.entry(ImageID.SPACESHIP_PLAYER_JUGGERNAUT, Loader.loadImage("images/spaceships/player_spaceship_2.png")),
                Map.entry(ImageID.SPACESHIP_PLAYER_CUTTER, Loader.loadImage("images/spaceships/player_spaceship_3.png")),
                Map.entry(ImageID.SPACESHIP_EASTER_EGG, Loader.loadImage("images/spaceships/easter_egg_ship.png")),

                Map.entry(ImageID.PROJECTILE_ENEMY_1, Loader.loadImage("images/objects/enemy_projectile.png")),
                Map.entry(ImageID.PROJECTILE_PLAYER_1, Loader.loadImage("images/objects/player_projectile.png"))
        ));
        if (!IMAGE_TABLE.keySet().containsAll(Set.of(ImageID.values()))) {
            throw new IllegalInitializationException("Loader.initializeData: the image table doesn't have a key for each possible ImageID.");
        }
    }

    /**
     * Enum of all the images IDs.
     */
    public enum ImageID {

        /** ID relative to the default image of a spaceship. */
        DEFAULT,

        /** ID relative to the initial menu background image. */
        BACKGROUND_INITIAL_MENU,
        /** ID relative to the main menu background image. */
        BACKGROUND_MAIN_MENU,
        /** ID relative to the menu background image. */
        BACKGROUND_MENU,

        /** ID relative to the image of the start button. */
        BUTTON_START,
        /** ID relative to the image of the leaderboard button. */
        BUTTON_LEADERBOARD,
        /** ID relative to the image of the options button. */
        BUTTON_OPTIONS,
        /** ID relative to the image of the quit button. */
        BUTTON_QUIT,
        /** ID relative to the image of the yes button. */
        BUTTON_YES,
        /** ID relative to the image of the no button. */
        BUTTON_NO,
        /** ID relative to the image of the ok button. */
        BUTTON_OK,
        /** ID relative to the image of the back button. */
        BUTTON_BACK,
        /** ID relative to the image of the radio button. */
        BUTTON_RADIO,
        /** ID relative to the image of the plus button. */
        BUTTON_PLUS,
        /** ID relative to the image of the minus button. */
        BUTTON_MINUS,
        /** ID relative to the image of the right arrow button. */
        BUTTON_RIGHTARROW,
        /** ID relative to the image of the left arrow button. */
        BUTTON_LEFTARROW,
        /** ID relative to the image of the spaceship 1 selection button. */
        SPACESHIP_1_BUTTON,
        /** ID relative to the image of the spaceship 2 selection button. */
        SPACESHIP_2_BUTTON,
        /** ID relative to the image of the spaceship 3 selection button. */
        SPACESHIP_3_BUTTON,

        /** ID relative to the image of the default mouse pointer. */
        MOUSE_POINTER,
        /** ID relative to the image of the over mouse pointer. */
        MOUSE_OVER,

        /** ID relative to the image of the text 1280x720. */
        TEXT_1280X720,
        /** ID relative to the image of the text 1600x900. */
        TEXT_1600X900,
        /** ID relative to the image of the text 1920x1080. */
        TEXT_1920X1080,
        /** ID relative to the image of the text 2560x1440. */
        TEXT_2560X1440,
        /** ID relative to the image of the text 3840x2160. */
        TEXT_3840X2160,
        /** ID relative to the image of the text music enabled. */
        TEXT_MUSIC,
        /** ID relative to the image of the text audio volume. */
        TEXT_AUDIOVOLUME,
        /** ID relative to the image of the text fullscreen mode. */
        TEXT_FULLSCREEN,
        /** ID relative to the image of the text windowed mode. */
        TEXT_WINDOWED,
        /** ID relative to the image of the text in the quit menu. */
        TEXT_QUIT,

        /** ID relative to the bonus image. */
        OBJECT_BONUS,
        /** ID relative to the crosshair image. */
        OBJECT_CROSSHAIR,
        /** ID relative to the enemy thruster image. */
        OBJECT_ENEMY_THRUSTER,
        /** ID relative to the player thruster image. */
        OBJECT_PLAYER_THRUSTER,

        /** ID relative to the enemy projectile image. */
        PROJECTILE_ENEMY_1,
        /** ID relative to the player projectile image. */
        PROJECTILE_PLAYER_1,

        /** ID relative to the image of an enemy fighter spaceship. */
        SPACESHIP_ENEMY_FIGHTER,
        /** ID relative to the image of an enemy juggernaut spaceship. */
        SPACESHIP_ENEMY_JUGGERNAUT,
        /** ID relative to the image of an enemy cutter spaceship. */
        SPACESHIP_ENEMY_CUTTER,
        /** ID relative to the image of a player fighter spaceship. */
        SPACESHIP_PLAYER_FIGHTER,
        /** ID relative to the image of player juggernaut spaceship. */
        SPACESHIP_PLAYER_JUGGERNAUT,
        /** ID relative to the image of a player cutter spaceship. */
        SPACESHIP_PLAYER_CUTTER,
        /** ID relative to the image of an easter egg spaceship. */
        SPACESHIP_EASTER_EGG,

    }

}

package unibo.exiled.utilities;

import java.net.URL;

import javax.swing.ImageIcon;

/**
 * The end state of the game.
 */
public enum EndState {
    /**
     * Represents the victory state.
     */
    VICTORY("victory.png"),

    /**
     * Represents the game over state.
     */
    GAME_OVER("gameover.png");

    private final String fileImageName;

    /**
     * Constructs an EndState.
     *
     * @param fileImageName the name of the file which contains the image of the game state.
     */
    EndState(final String fileImageName) {
        this.fileImageName = fileImageName;
    }

    /**
     * Gets the image associated with the end state.
     *
     * @return The ImageIcon representing the view to be displayed in the EndGameView.
     */
    public ImageIcon getEndStateImage() {
        final String imagePath = ConstantsAndResourceLoader.IMAGES_PATH + "/interface/" + this.fileImageName;
        final URL imageURL = ClassLoader.getSystemResource(imagePath);
        return new ImageIcon(imageURL);
    }
}

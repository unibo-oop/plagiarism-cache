package breakout.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import breakout.controller.levels.LevelManager;
import breakout.model.levels.DecoratedLevel;
import javafx.scene.image.Image;

/**
 * 
 */
public final class Controller {

    private static final Controller SINGLETON = new Controller();

    private final LevelManager levelManager;
    private final List<DecoratedLevel> toPlay = new ArrayList<>();
    private Image ballImage;
    private Image paddleImage;

    private Controller() {
        this.levelManager = new LevelManager();
    }

    /**
     * Getter for the singleton.
     * 
     * @return the Controller
     */
    public static Controller get() {
        return SINGLETON;
    }

    /**
     * Clears the list of levels that has to be played.
     */
    public void clearToPlayList() {
        this.toPlay.clear();
    }

    /**
     * Takes a list of level names in input and, if they are available, adds
     * them to the levels to play.
     * 
     * @param list
     *            the list of level names
     */
    public void addLevelsToPlay(final List<String> list) {
        for (final String name : list) {
            this.toPlay.add(
                    this.getAvailableLevels().stream().filter(level -> level.getName().equals(name)).findFirst().get());
        }
    }

    /**
     * @return The list of levels that the user has decided to play
     */

    public List<DecoratedLevel> getLevelsToPlay() {
        return Collections.unmodifiableList(this.toPlay);
    }

    /**
     * Set the images that the ball and the paddle will have during the game.
     * 
     * @param ballImage
     *            image of the ball
     * @param paddleImage
     *            imgae of the paddle
     */
    public void setPlayer(final Image ballImage, final Image paddleImage) {
        this.ballImage = ballImage;
        this.paddleImage = paddleImage;
    }

    /**
     * @return the ball image choosen by the user.
     */
    public Image getBallImage() {
        return this.ballImage;
    }

    /**
     * @return the paddle image choosen by the user.
     */
    public Image getPaddleImage() {
        return this.paddleImage;
    }

    /**
     * @return the list of available levels to play
     */
    public List<DecoratedLevel> getAvailableLevels() {
        return Collections.unmodifiableList(this.levelManager.loadAllLevels());
    }

}

package javagotchi.view.minigame.mainview;

import java.util.List;

import javagotchi.view.minigame.FrameDefault;
import javagotchi.view.minigame.component.GameButton;

/**
 * 
 * @author marica
 *
 */
public interface MiniGameView extends FrameDefault {

    /**
     * Repaints the game view.
     */
    void repaintGameView();

    /**
     * Gets the {@link GameButton} list of game view.
     * 
     * @return list of button
     */
    List<GameButton> getButtons();

    /**
     * Sets the time progress.
     * 
     * @param sec
     *            new seconds
     */
    void setTime(int sec);

    /**
     * Sets the score text.
     * 
     * @param score
     *            new score text
     *
     */
    void setScore(String score);

    /**
     * Active the game view.
     * 
     * @param cond
     *            if it is true, the game view is active, otherwise it is disabled.
     */
    void reActive(boolean cond);
}

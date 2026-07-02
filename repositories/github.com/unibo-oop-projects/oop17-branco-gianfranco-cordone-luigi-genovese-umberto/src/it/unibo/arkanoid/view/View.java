package it.unibo.arkanoid.view;

import java.util.List;

import it.unibo.arkanoid.audio.SongLoop;
import it.unibo.arkanoid.controller.GameStats;
import it.unibo.arkanoid.subject.Subject;

/**
 * The interface for the visual representation for {@link Subject} and world.
 *
 */
public interface View {

    /**
     * Update the visual representation for all {@link Subject} in world.
     * 
     * @param subjectList
     *            The list of Subject to draw.
     */
    void render(List<Subject> subjectList);

    /**
     * 
     * @param subview
     *            the {@link SubView} to show on the screen.
     */
    void setSubView(SubView subview);

    /**
     * Display the updated {@link GameStats}.
     * 
     * @param gameStats
     *            the Game Stats to show.
     */
    void displayGameStats(GameStats gameStats);

    /**
     * Getter of {@link SongLoop}.
     * 
     * @return songLoop.
     */
    SongLoop getSongLoop();

}

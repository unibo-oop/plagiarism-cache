package it.unibo.arkanoid.view;

import java.util.List;

import it.unibo.arkanoid.subject.Subject;

/**
 * Interface to draw {@link Subject} in the game screen.
 */
public interface Drawer {

    /**
     * Draw {@link Subject} in the game screen.
     * 
     * @param subjects
     *               The subjects to draw.
     */
     void render(List<Subject> subjects);

    /**
     * Clean all the {@link Subject} from game screen. 
     */
     void clear();

}

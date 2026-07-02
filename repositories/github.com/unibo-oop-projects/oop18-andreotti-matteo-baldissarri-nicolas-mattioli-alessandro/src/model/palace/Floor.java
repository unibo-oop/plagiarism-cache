package model.palace;

import java.util.List;

/**
 * 
 * Model a Floor of a Palace.
 *
 */
public interface Floor {

    /**
     * 
     * @return A random Window.
     * 
     */
    Window getRandomWindow();

    /**
     * 
     * @return A random Window.
     * @param maxWindow The maximum window to end random.
     * 
     */
    Window getRandomWindow(int maxWindow);

    /**
     * 
     * @return All the windows of this Floor.
     * 
     */
    List<Window> getWindows();
}

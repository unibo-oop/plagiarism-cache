package it.unibo.pacman.view.entities;
/**
 * An interface which allows to creates a views for each entities in the game.
 */
public interface ViewFactory {
    /**
     * Get a new Blinky view.
     * 
     * @return a new {@link MovableView} for Blinky.
     */
    MovableView getBlinkyView();

    /**
     * Get a new Inky view.
     * 
     * @return a new {@link MovableView} for Inky.
     */
    MovableView getInkyView();

    /**
     * Get a new Pinky view.
     * 
     * @return a new {@link MovableView} for Pinky.
     */
    MovableView getPinkyView();

    /**
     * Get a new Clyde view.
     * 
     * @return a new {@link MovableView} for Clyde.
     */
    MovableView getClydeView();

    /**
     * Get a pacman view.
     * 
     * @return a new {@link MovableView} for PacMan.
     */
    MovableView getPacManView();

    /**
     * Get a wall view.
     * 
     * @return a new {@link EntityView} for Wall.
     */
    EntityView getWallView();

    /**
     * Get a pill view.
     * 
     * @return a new {@link EntityView} for Pill.
     */
    EntityView getPillView();

    /**
     * Get a PowerPill view.
     * 
     * @return a new {@link EntityView} for PowerPill.
     */
    EntityView getPowerPillView();
}

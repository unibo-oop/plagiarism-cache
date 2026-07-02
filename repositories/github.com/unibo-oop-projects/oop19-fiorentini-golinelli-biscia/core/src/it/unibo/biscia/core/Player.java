package it.unibo.biscia.core;

/**
 * is a player of game.
 *
 */
public interface Player {
    /**
     * the initial number of lives of a player.
     */
    int INITIAL_LIVES = 5;
    /**
     * point lost any dead.
     */
    int POINTS_FOR_DEAD = -1000;
    /**
     * point added for any energy point of food.
     */
    int POINTS_FOR_FOOD_ENERGY = 100;

    /**
     * the name.
     * 
     * @return the name of player
     */
    String getName();

    /**
     * actual points.
     * 
     * @return value of actual points
     */
    int getPoints();

    /**
     * remaining lives of player.
     * 
     * @return number of lives
     */
    int getLives();

    /**
     * get the entity represent snake of player.
     * 
     * @return the snake associated to a player
     */
    Entity getEntity();

    /**
     * extend player with methods for setting points and lives.
     *
     */
    interface PlayerManaged extends Player {
        /**
         * decrease lives and remove snake, is possible only if snake is set, also throw
         * IllegalStateException.
         */
        void dead();

        /**
         * increment points.
         * 
         * @param points points to add
         */
        void addPoints(int points);

        /**
         * set the snake to player. Is possible only if snake is not set and lives not
         * zero, also throw IllegalStateException.
         * 
         * @param directable the snake
         */
        void setDirectable(Entity.EntityManaged.Movable.Directable directable);

        /**
         * return the snake assigned to player.
         * 
         * @return the snake
         */
        Entity.EntityManaged.Movable.Directable getDirectable();

        /**
         * remove a directable inside the player.
         */
        void removeDirectable();
    }
}

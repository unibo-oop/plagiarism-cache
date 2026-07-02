package it.unibo.isaccoop.model.player;

/**
 * Player statistic.
 * */
public interface PlayerStat {

    /**
     * Get player hearts.
     * @return the number of remaining hearts
     * */
    int getHeart();

    /**
     * Get player max hearts.
     * @return the max number of available hearts
     * */
    int getMaxHeart();

    /**
     * Get player coins.
     * @return the number of coins collected during the game
     * */
    int getCoin();

    /**
     * Get player speed.
     * @return the speed of the player
     * */
    Double getSpeed();

    /**
     * Get player damage.
     * @return the damage that the player can do
     * */
    Double getDamage();

    /**
     * Get player tears.
     * @return the time between two hit
     * */
    Double getTears();

    /**
     * Set player hearts.
     * @param heart the new number of heart of the player
     * set the player's current hearts as a parameter
     * */
    void setHeart(int heart);

    /**
     * Set player max hearts.
     * @param maxHeart the new number of available heart of the player
     * set the max available heart for the player
     * */
    void setMaxHeart(int maxHeart);

    /**
     * Set player coins.
     * @param coin the coin collected during the game or used in the shop
     * set the coin of the player
     * */
    void setCoin(int coin);

    /**
     * Set player speed.
     * @param speed the new speed
     * set the new speed of the player
     * */
    void setSpeed(Double speed);

    /**
     * Set player tears.
     * @param tears
     * set the new time between two hit
     * */
    void setTears(Double tears);

    /**
     * Set player damage.
     * @param damage
     * set the new damage that player have
     * */
    void setDamage(Double damage);

    /**
     * Get if the player is dead or not.
     * @return if the player is dead
     * */
    boolean isDead();
}

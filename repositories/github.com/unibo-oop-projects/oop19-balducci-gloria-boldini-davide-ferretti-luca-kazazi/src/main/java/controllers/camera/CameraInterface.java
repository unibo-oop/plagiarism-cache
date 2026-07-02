package controllers.camera;

import model.player.Player;

public interface CameraInterface {

    /**
     * @return X
     * 
     * method to get camera X coordinate
     * 
     */
    float getX();

    /**
     * @return Y
     * 
     * method to get camera Y coordinate
     * 
     */
    float getY();

    /**
     * @param player
     * 
     * method to refresh real-time camera coordinates using player coordinates
     *
     */
    void tick(Player player);

}

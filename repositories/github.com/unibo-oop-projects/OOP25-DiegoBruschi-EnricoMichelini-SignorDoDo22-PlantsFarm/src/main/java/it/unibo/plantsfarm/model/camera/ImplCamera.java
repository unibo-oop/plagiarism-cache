package it.unibo.plantsfarm.model.camera;

import it.unibo.plantsfarm.model.camera.api.Camera;
import it.unibo.plantsfarm.view.gamepanel.ImplViewGamePanel;

/**
 * Represents the game camera.
 * The camera defines the portion of the game world currently visible on screen
 * and follows the player within the world boundaries.
 *
 */
public final class ImplCamera implements Camera {

    private int posX;
    private int posY;
    private final int screenWidth;
    private final int screenHeight;

    /**
     * Constructs a new Camera instance using the given screen dimensions.
     * The screen width and height are used to calculate the visible
     * area of the game world and to correctly center the camera
     * on the player during the game loop.
     *
     * @param screenWidth  the width of the game screen in pixels
     * @param screenHeight the height of the game screen in pixels
     */
    public ImplCamera(final int screenWidth, final int screenHeight) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
}

    @Override
    public void followPlayer(final int playerPosX, final int playerPosY) {

        posX = playerPosX - ImplViewGamePanel.SCREEN_WIDTH / 2;
        posY = playerPosY - ImplViewGamePanel.SCREEN_HEIGHT / 2;

        if (posX < 0) {
            posX = 0;
        }
        if (posY < 0) {
            posY = 0;
        }
        if (posX > ImplViewGamePanel.WORLD_WIDTH - screenWidth) {
            posX = ImplViewGamePanel.WORLD_WIDTH - ImplViewGamePanel.SCREEN_WIDTH;
        }
        if (posY > ImplViewGamePanel.WORLD_HEIGHT - screenHeight) {
            posY = ImplViewGamePanel.WORLD_HEIGHT - ImplViewGamePanel.SCREEN_HEIGHT;
        }
    }

    @Override
    public int getCameraPosX() {
        return this.posX;
    }

    @Override
    public int getCameraPosY() {
        return this.posY;
    }
}

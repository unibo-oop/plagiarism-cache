package controllers.camera;

import java.awt.Toolkit;

import java.util.Objects;

import model.player.Player;

public class Camera implements CameraInterface {

    private static final int LEVEL_WIDTH = 64;
    private static final int LEVEL_HEIGHT = 64;
    private static final int PIXEL_MULTIPLICATIVE = 64;
    private static final int VIEWSIZE_X = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private static final int VIEWSIZE_Y = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    private static final int OFFSETMAX_X = (int) (LEVEL_WIDTH * PIXEL_MULTIPLICATIVE
            - Toolkit.getDefaultToolkit().getScreenSize().getWidth());
    private static final int OFFSETMAX_Y = (int) (LEVEL_HEIGHT * PIXEL_MULTIPLICATIVE
            - Toolkit.getDefaultToolkit().getScreenSize().getHeight());
    private static final int OFFSETMIN_X = 0;
    private static final int OFFSETMIN_Y = 0;

    private float x;
    private float y;

    /**
     * Constructor for Camera.
     * 
     * @param x initial x coordinate
     * @param y initial y coordinate
     */
    public Camera(final float x, final float y) {
        this.x = Objects.requireNonNull(x);
        this.y = Objects.requireNonNull(y);
    }

    @Override
    public float getX() {
        return this.x;
    }

    @Override
    public float getY() {
        return this.y;
    }

    @Override
    public void tick(final Player player) {
        Objects.requireNonNull(player);
        this.x = player.getCoord().getX() - VIEWSIZE_X / 2;
        this.y = player.getCoord().getY() - VIEWSIZE_Y / 2;

        if (this.x > (OFFSETMAX_X)) {
            this.x = OFFSETMAX_X;
        } else if (this.x < OFFSETMIN_X) {
            this.x = OFFSETMIN_X;
        }
        if (this.y > (OFFSETMAX_Y)) {
            this.y = OFFSETMAX_Y;
        } else if (this.y < OFFSETMIN_Y) {
            this.y = OFFSETMIN_Y;
        }
    }
}

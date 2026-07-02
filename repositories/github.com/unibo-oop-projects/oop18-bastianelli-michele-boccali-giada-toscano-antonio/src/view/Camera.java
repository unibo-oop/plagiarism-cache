package view;

import common.CommonStrings;
import common.EventBusConnection;
import controller.GameController;
import javafx.geometry.Point2D;

/**
 * Handles the movement of the camera in the game. It also handles conversion
 * between JavaFX coordinates and JBox2D coordinates.
 */
public class Camera extends EventBusConnection {

    private static Point2D cameraPosition;

    private static final double CAMERA_THRESHOLD = CommonStrings.WINDOW_HEIGHT / 2;

    private double yDifference = 0;
    private double lastY = 0;
    private double lastMaxHeight = 0;

    /**
     * Constructor for the camera.
     */
    public Camera() {
        super();
        Camera.cameraPosition = new Point2D(0, 0);
    }

	/**
	 * Returns the position in the camera from the view position.
	 * @param viewPoint - point in view
	 * @return point in view
	 */
    public static Point2D viewPointToCamera(final Point2D viewPoint) {
        return new Point2D(viewPoint.getX() + cameraPosition.getX(), viewPoint.getY() + cameraPosition.getY());
    }

    /**
     * Returns the view position from the camera position.
     * 
     * @param cameraPoint - point in view
     * @return point in view
     */
    public static Point2D cameraPointToView(final Point2D cameraPoint) {
        return new Point2D(cameraPoint.getX() - cameraPosition.getX(), cameraPoint.getY() - cameraPosition.getY());
    }

    /**
	 * Updates the camera.
	 */
	public void update() {
		final double y = GameController.getInstance().getGameModel().getPlayer().getViewPosition().y;
		if (y < CAMERA_THRESHOLD) {	
			cameraPosition = cameraPosition.add(0, CAMERA_THRESHOLD - y);
		}
	}

    /**
     * Returns the actual camera position.
     * 
     * @return camera position
     */
    public Point2D getCameraPosition() {
        return cameraPosition;
    }

    /**
     * Checks if a y is not visible yet on camera. Used to generate entities outside
     * of the screen.
     * 
     * @param y of the point
     * @return <code>true</code> if the Y is not showed yet on camera
     */
    public static boolean isYNotShowingYet(final float y) {
        return ((double) -y < (double) cameraPosition.getY());
    }

    /**
     * Checks if a y is past the player and the screen.
     * 
     * @param y of the point
     * @return <code>true</code> if the Y is past the screen and the player
     */
    public static boolean isYPastPlayer(final float y) {
        return ((double) -y < (double) cameraPosition.getY() - (double) CommonStrings.WINDOW_HEIGHT);
    }

}

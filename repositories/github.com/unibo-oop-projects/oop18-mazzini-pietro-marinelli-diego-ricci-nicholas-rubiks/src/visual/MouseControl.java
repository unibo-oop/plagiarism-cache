package visual;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.transform.Rotate;

/**
 * Class used to attach to a cube Mouse Controls (rotation around default axis with mouse).
 * Thanks to <a href="https://www.genuinecoder.com/">GenuineCoder</a> for the main concept of this class.
 */
public class MouseControl {

    private double anchorX, anchorY;
    private double anchorAngleX, anchorAngleY;
    private final DoubleProperty angleX = new SimpleDoubleProperty(0);
    private final DoubleProperty angleY = new SimpleDoubleProperty(0);

    private final Group group;
    private final Scene scene;

    /**
     * Constructor that defines local variables.
     * @param group - Group to be controlled.
     * @param scene - Scene containing the Group.
     */
    public MouseControl(final Group group, final Scene scene) {
        this.group = group;
        this.scene = scene;
    }

    /**
     * Initialize Mouse Control with the previously selected (with constructor) parameters.
     */
    public void initMouseControl() {
        final Rotate xRotate = new Rotate(0, Rotate.X_AXIS);
        final Rotate yRotate = new Rotate(0, Rotate.Y_AXIS);
        group.getTransforms().addAll(xRotate, yRotate);
        xRotate.angleProperty().bind(angleX);
        yRotate.angleProperty().bind(angleY);

        scene.setOnMousePressed(event -> {
            anchorX = event.getSceneX();
            anchorY = event.getSceneY();
            anchorAngleX = angleX.get();
            anchorAngleY = angleY.get();
        });

        scene.setOnMouseDragged(event -> {
            angleX.set(anchorAngleX - (anchorY - event.getSceneY()));
            angleY.set(anchorAngleY + anchorX - event.getSceneX());
        });
    }
}

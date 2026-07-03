package oop.lit.view;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.DoublePropertyBase;
import javafx.beans.property.ReadOnlyDoubleProperty;

/**
 * A class used to change the viewpoint on the boardView.
 */
public class Camera {
    private final DoubleProperty posX = new CameraDoubleProperty("posX");
    private final DoubleProperty posY = new CameraDoubleProperty("posY");

    /**
     * Moves the camera along the y axis, by the specified quantity.
     *
     * @param quantity
     *      how much the camera will be moved. Positive value will move the camera upwards.
     */
    public void moveVertical(final double quantity) {
        this.posY.set(this.posY.get() - quantity);
    }

    /**
     * Moves the camera along the x axis, by the specified quantity.
     *
     * @param quantity
     *      how much the camera will be moved. Positive value will move the camera to the right.
     */
    public void moveHorizontal(final double quantity) {
        this.posX.set(this.posX.get() - quantity);
    }

    /**
     * Returns the double property representing the vertical offset elements of the board view should have to be displayed correctly according to the camera offset.
     * @return
     *      the double property.
     */
    public ReadOnlyDoubleProperty getNodeOffsetXproperty() {
        return this.posX;
    }

    /**
     * Returns the double property representing the horizontal offset elements of the board view should have to be displayed correctly according to the camera offset.
     * @return
     *      the double property.
     */
    public ReadOnlyDoubleProperty getNodeOffsetYproperty() {
        return this.posY;
    }

    /**
     * Resets the camera position.
     */
    public void resetCamera() {
        this.posX.set(0);
        this.posY.set(0);
    }

    private class CameraDoubleProperty extends DoublePropertyBase {
        private final String name;
        protected CameraDoubleProperty(final String propertyName) {
            this.name = "Camera-" + propertyName;
        }
        @Override
        public String getName() {
            return name;
        }
        @Override
        public Object getBean() {
            return Camera.this;
        }
    };
}

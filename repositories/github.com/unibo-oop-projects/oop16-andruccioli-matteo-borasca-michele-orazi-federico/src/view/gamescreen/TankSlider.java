package view.gamescreen;

import com.jfoenix.controls.JFXSlider;

/**
 * 
 * Abstract class representing a generic slider.
 *
 */
public abstract class TankSlider extends JFXSlider {

    /**
     * 
     * Class constructor.
     * 
     * @param width
     *              Node width.
     * 
     */
    public TankSlider(final double width) {
        this.setPrefWidth(width);
        this.setMaxWidth(width);
        this.setVisible(false);
    }

    /**
     * @return Integer round up value of double obtained using getValue() method.
     */
    public int getIntValue() {
        return (int) Math.round(this.getValue());
    }
    /**
     * 
     * Set max value on slider.
     * 
     */
    public abstract void setMax();
}

package tmw.controller.menu;

/**
 * Class that stores options.
 *
 */
public class OptionsSettings {

    private double volume;
    private boolean isMuted;
    private boolean isDisabled;
    private double width;
    private double height;

    /**
     * Public constructor.
     * 
     * @param volume      volume
     * @param isMute      true if is mute, false otherwise
     * @param isDisable   true if volume slider is disable, false otherwise
     * @param stageWidth  stage width
     * @param stageHeight stage height
     */
    public OptionsSettings(final double volume, final boolean isMute, final boolean isDisable, final double stageWidth,
            final double stageHeight) {
        this.volume = volume;
        this.isMuted = isMute;
        this.isDisabled = isDisable;
        this.width = stageWidth;
        this.height = stageHeight;

    }

    /**
     * Getter for mute state.
     * 
     * @return true if is mute
     */
    public boolean isMute() {
        return this.isMuted;
    }

    /**
     * Getter for the slider's state.
     * 
     * @return true if the slider is disabled
     */
    public boolean isDisable() {
        return this.isDisabled;
    }

    /**
     * Getter for the volume value.
     * 
     * @return volume
     */
    public double getVolume() {
        return this.volume;
    }

    /**
     * Getter for the resolution.
     * 
     * @return resolution in the format "width + x + height"
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Getter for the height.
     * 
     * @return height
     */
    public double getHeigth() {
        return this.height;
    }

    /**
     * Setter for game volume.
     * 
     * @param volume the volume
     */
    public void setVolume(final double volume) {
        this.volume = volume;
    }

    /**
     * Setter for mute Option.
     * 
     * @param value true if mute, false otherwise
     */
    public void setMute(final boolean value) {
        this.isMuted = value;
    }

    /**
     * Setter for disable the slider.
     * 
     * @param value true if is disable, false otherwise
     */
    public void setDisable(final boolean value) {
        this.isDisabled = value;
    }

    /**
     * Setter for the resolution.
     * 
     * @param width  stage width
     * @param height stage height
     */
    public void setResolution(final double width, final double height) {
        this.width = width;
        this.height = height;
    }
}

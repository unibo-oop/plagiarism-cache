package model.mario;

/**
 * This interface creates the Model of Mario.
 */
public interface MarioModel {

    /**
     * Set the type of Mario in that moment
     * @param type
     */
    void setType(MarioType type);

    /**
     *
     * @return the type of Mario.
     */
    MarioType getType();

    /**
     * Method to get the path of "Run" Image of Mario.
     * @return the image path of Mario while he is running
     */
    String getMarioRunImgPath();

    /**
     * Method to get the path of "Jump" Image of Mario.
     * @return the image path of Mario while he is jumping
     */
    String getMarioJumpImgPath();

    /**
     * Method to get the path of the Power-Up mushroom Image.
     * @return the Path of the Image
     */
    String getMarioPowerImgPath();

    /**
     * Method to get the path of the "Jump" Image of Mario WHEN in POWER-UP.
     * @return the Path of the Image
     */
    String getMarioPowerJumpImgPath();

    /**
     * Method to get Mario's Width.
     * @return Mario's width
     */
    double getMarioWidth();

    /**
     * Method to get Mario's Height.
     * @return Mario's height
     */
    double getMarioHeight();

    /**
     * Method to get the Position on the Y Axis of Mario.
     * @return current y coordinate of Mario
     */
    double getMarioPosY();

    /**
     * Method to get the Position on the X Axis of Mario.
     * @return current x coordinate of Mario
     */
    double getMarioPosX();

    /**
     * Set the new Mario's position with parameter n.
     * @param n is a Constant MOVEMENT defined in{@link controllers.GameLoopImpl} it's referring at the number of pixels added at the current Y, at every loop.
     */
    void marioUpdate(double n);

    /**
     * Set y coordinate of Mario.
     * @param posY current y coordinate
     */
    void setPosY(double posY);
}

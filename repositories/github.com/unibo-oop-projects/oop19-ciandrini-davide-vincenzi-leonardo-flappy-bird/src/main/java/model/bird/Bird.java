package model.bird;

/**
 * Bird's Model Interface.
 */
public interface Bird {
    /**
     * Set the new bird position adding a constant "n".
     * @param n Constant defined in {@link controllers.GameLoopImpl}, which is the number of pixels that must be added at the current Y coordinates each loop
     */
    void birdUpdate(double n);

    /**
     *
     * @return the image path of bird
     */
    String getBirdImagePath();

    /**
     *
     * @return the width of bird
     */
    int getWidthBird();

    /**
     *
     * @return the height of bird
     */
    int getHeightBird();

    /**
     *
     * @return the X coordinate of bird
     */
    double getPosX();

    /**
     *
     * @return the Y coordinate of bird
     */
    double getPosY();

    /**
     * Set Y coordinate of bird.
     * @param posY current Y coordinate
     */
    void setPosY(double posY);

    /**
     * Set birdInstance null for create new bird Object when game restart.
     */
    //void setBirdInstance();

}

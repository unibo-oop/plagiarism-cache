package maingame.input;

import util.Vector2;

/**
 * Interfaccia di un model per l'input di gioco.
 *
 */
public interface ModelInput {
    /**
     * @return se è premuto freccia in alto o W
     */
    boolean isPressedUp();

    /**
     * @return se è premuto freccia in basso o S
     */
    boolean isPressedDown();

    /**
     * @return se è premuto freccia destra o D
     */
    boolean isPressedRight();

    /**
     * @return se è premuto freccia sinistra o A
     */
    boolean isPressedLeft();

    /**
     * @return se è premuto ESC
     */
    boolean isPressedEsc();

    /**
     * @return se è premuto INVIO
     */
    boolean isPressedEnter();

    /**
     * @return se è premuto SPAZIO
     */
    boolean isPressedSpace();

    /**
     * Setta tasto premuto up.
     * 
     * @param pressedUp
     *            true se è premuto
     */
    void isUp(boolean pressedUp);

    /**
     * Setta tasto premuto down.
     * 
     * @param pressedDown
     *            true se è premuto
     */
    void setDown(boolean pressedDown);

    /**
     * Setta tasto premuto right.
     * 
     * @param pressedRight
     *            true se è premuto
     */
    void setRight(boolean pressedRight);

    /**
     * Setta tasto premuto left.
     * 
     * @param pressedLeft
     *            true se è premuto
     */
    void setLeft(boolean pressedLeft);

    /**
     * Setta tasto premuto esc.
     * 
     * @param pressedEsc
     *            true se è premuto
     */
    void setEsc(boolean pressedEsc);

    /**
     * Setta tasto premuto enter.
     * 
     * @param pressedEnter
     *            true se è premuto
     */
    void setEnter(boolean pressedEnter);

    /**
     * Setta tasto premuto space.
     * 
     * @param pressedSpace
     *            true se è premuto
     */
    void setSpace(boolean pressedSpace);

    /**
     * resetta i valori letti da tastiera.
     */
    void resetKeyBoard();

    /**
     * @return Coordinate del mouse
     */
    Vector2<Integer> getMouseCoordinate();

    /**
     * @return valore del tasto del mouse premuto
     */
    int getButton();

    /**
     * Setta le coordinate del mouse.
     * 
     * @param coordinate
     *            coordinate del mouse
     */
    void setMouseCoordinate(Vector2<Integer> coordinate);

    /**
     * Setta tasto premuto del mouse.
     * 
     * @param button
     *            tasto premuto
     */
    void setMouseButton(int button);
}

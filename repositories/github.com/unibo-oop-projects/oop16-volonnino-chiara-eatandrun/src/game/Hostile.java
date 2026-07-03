package game;

/**
 * Implementation of a hostile
 */

import java.awt.Graphics;

/**
 * 
 * @author Chiara
 *
 */

public class Hostile extends Element {
    
    private static final int POINT_X = 750;
    private static final int POINT_Y = 550;
    
    /**
     * 
     * @param resource 
     *        path to load 
     * @param x
     *        point x
     * @param y
     *        point y
     * @param len
     *        length width and height
     * @param speedX
     *        speed point x
     * @param speedY
     *        speed point y
     */
    public Hostile(final String resource, final int x, final int y, final int len, final int speedX, final int speedY) {
        super(resource, x, y, len, speedX, speedY);
    }
	
    @Override 
    public void draw(Graphics graphic) {
        graphic.drawImage(imagen, getX(), getY(), getLen(), getLen(), null);
    }
	
    @Override 
    public void animation() {
        setX(getX() + getSpeedX());
        setY(getY() + getSpeedY());
        if (getX() < 0 ) {
            setSpeedX(- getSpeedX());
        }
        if (getY() < 0 ) {
            setSpeedY(- getSpeedY());
        }
        if (getX() > POINT_X) {
            setSpeedX(-getSpeedX());
        }
        if (getY() > POINT_Y) {
            setSpeedY (-getSpeedY());
        }
    }

}

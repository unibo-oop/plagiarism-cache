package game;

 /**
  * Implementation of a player 
  */
 
import java.awt.Graphics;
import javax.swing.ImageIcon;

/**
 * 
 * @author Chiara
 *
 */

public class Players extends Element {
    
    private static final int POINT_X = 750;
    private static final int POINT_Y = 550;
    private boolean invulnerable;
    
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
    public Players(final String resource, final int x, final int y, final int len, final int speedX, final int speedY) {
        super(resource, x, y, len, speedX, speedY);
        invulnerable = false;
    }
    
    @Override
    public void draw(Graphics graphic) {
        
        if(isCrash()) {
            imagen = new ImageIcon(this.getClass().getResource("/res/muerte.png")).getImage();
            graphic.drawImage(imagen, getX(), getY(), getLen(), getLen(), null);
        } else {
            if(isInvulnerable()) {
                imagen = new ImageIcon(this.getClass().getResource("/res/pac.PNG")).getImage();
		graphic.drawImage(imagen, getX(), getY(), getLen(), getLen(), null);
            } else {
                imagen = new ImageIcon(this.getClass().getResource("/res/pacV.png")).getImage();
		graphic.drawImage(imagen, getX(), getY(), getLen(), getLen(), null);
            }
	}
    }

    /**
     * 
     * @return
     *     status of player
     */
    public boolean isInvulnerable() {
        return invulnerable;
    }
    
    @Override
    public void animation() {
        setX(getX()+getSpeedX());
        setY(getY()+getSpeedY());
        if (getX()<0 || getY()<0 || getX()>750 || getY()>550) {
            if(isInvulnerable()) {
                if(getX() < 0) {
                    setX(POINT_X);
                }
		if(getX() > POINT_X) {
		    setX(0);
		}
		if(getY() < 0) {
		    setY(POINT_Y);
		}
		if(getY() > POINT_Y) {
		    setY(0);
		}
            } else {
                setCrash(true);
            }
	}
    }
	
    public void setInvulnerable(final boolean invulnerable) {
        this.invulnerable = invulnerable;
    }
}

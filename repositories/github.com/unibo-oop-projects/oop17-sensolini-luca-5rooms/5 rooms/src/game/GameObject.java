package game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public abstract class GameObject {

	protected int x, y;
	protected ID id;
	protected double velX, velY;
	
	public GameObject(int x, int y, ID id){
		this.x = x;
		this.y = y;
		this.id = id;
	}
    /**
	 * computing to be done at each model loop
	 */
	public abstract void tick();
    /**
	 * rendering to be done at each view loop
	 * @param graphics object used to draw
	 */
	public abstract void render(Graphics g);
    /**
	 * Observer for keyPressed.
	 * @param key
	 */
	public void keyPressed(int k){	
	}
    /**
	 * Observer for keyReleased.
	 * @param key
	 */
	public void keyReleased(int k){	
	}
    /**
	 * Observer for mouseDragged.
	 * @param mouse event
	 */
	public void mouseDragged(MouseEvent e){
		
	}
    /**
	 * Observer for mouseMoved.
	 * @param mouse event
	 */
	public void mouseMoved(MouseEvent e){
		
	}
    /**
	 * Observer for mouseClicked.
	 * @param mouse event
	 */
	public void mouseClicked(MouseEvent e){
		
	}
    /**
	 * get the boundary box.
	 * @return boundary rectangle
	 */
	public abstract Rectangle getBounds();
    /**
	 * set x coordinate
	 * @param x
	 */
	public void setX(int x){
		this.x = x;
	}
    /**
	 * get x coordinate
	 * @return x
	 */
	public int getX(){
		return x;
	}
    /**
	 * set y coordinate
	 * @param y
	 */
	public void setY(int y){
		this.y = y;
	}
    /**
	 * get y coordinate
	 * @return y
	 */
	public int getY(){
		return y;
	}
    /**
	 * set the id
	 * @param id
	 */
	public void setId(ID id){
		this.id = id;
	}
    /**
	 * get the id
	 * @return id
	 */
	public ID getId(){
		return id;
	}
    /**
	 * set the x velocity
	 * @param velX
	 */
	public void setVelocityX(int velX){
		this.velX = velX;
	}
    /**
	 * get the x velocity
	 * @return VelX
	 */
	public double getVelX(){
		return velX;
	}
    /**
	 * set the y velocity
	 * @param velY
	 */
	public void setVelocityY(int velY){
		this.velY = velY;
	}
    /**
	 * get the y velocity
	 * @return velY
	 */
	public double getVelY(){
		return velY;
	}
    /**
	 * removes this item from the controller's list, won't be rendered or computed
	 */
	public void die(){
		Controller.getInstance().removeObject(this);
	}
}

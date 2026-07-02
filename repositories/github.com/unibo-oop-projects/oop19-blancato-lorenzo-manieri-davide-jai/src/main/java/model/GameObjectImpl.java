package model;


import controller.ScreenManager;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class GameObjectImpl extends EntityImpl implements Movable, GameObject {
	
	protected final static int DELTA_X = 1;
	protected final static int DELTA_Y = 1;
	
	private boolean visibile;
	private Image img;


	public GameObjectImpl(double width, double height, double positionX, double positionY) {
		super(ScreenManager.getScaleX(width), ScreenManager.getScaleY(height), positionX, positionY);
		this.setVisible(true);
	}
	

	public boolean isVisible() {
		return this.visibile; 
	}
	
	public void setVisible(boolean visible) {
		this.visibile = visible; 
	}
	public Rectangle2D getBounds() {
		return new Rectangle2D(this.x, this.y, this.width, this.height );
	}
	
	
	public boolean checkCollision(Rectangle2D r) {
		return this.getBounds().intersects(r);
		
	}
	
	public void setImage(Image img) {
		this.img = img; 
	
	}
	
	public void	draw(GraphicsContext gc) {
		if (this.visibile) {
			gc.drawImage(this.img, this.x, this.y, this.width, this.height);
		}
				
	}
	
	public void move(double deltaX, double deltaY) {
		this.moveX(deltaX);
		this.moveY(deltaY);
	}
	
	public void moveX(double deltaX) {
		this.setPositionX(this.getPositionX() + ScreenManager.getScaleX(deltaX));
	}
	
	public void moveY(double deltaY) {
		this.setPositionY(this.getPositionY() + ScreenManager.getScaleY(deltaY));
	}
	
	
	
	
	
}

package model;

import controller.ScreenManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class HealthBarImpl implements HealthBar {
	
	private Rectangle maxHealthRect;
	private Rectangle currentHealthRect;
	private final double width = 15;
	private final double height = 5;


	/**
	 * Constructor for enemy health bar
	 * 
	 */
	public HealthBarImpl() {
		maxHealthRect = new Rectangle(ScreenManager.getScaleX(width), height);
		currentHealthRect = new Rectangle(ScreenManager.getScaleX(width), height);
	}
	
	/**
	 * Constructor for player health bar
	 * @param x coordinate x for setting health bar
	 * @param y coordinate y for setting health bar
	 * @param playerWidth width of player health bar
	 * @param playerHeight height of player health bar
	 * 
	 */
	public HealthBarImpl(double x, double y, double playerWidth, double playerHeight) {
		maxHealthRect = new Rectangle(x, y, 
				ScreenManager.getScaleX(playerWidth), ScreenManager.getScaleY(playerHeight));
		currentHealthRect = new Rectangle(x, y,
				ScreenManager.getScaleX(playerWidth), ScreenManager.getScaleY(playerHeight));
	}	

	public double getHealthHeight(){
		return this.maxHealthRect.getHeight();
	}

	public void setCurrentHealth(double value) {
		currentHealthRect.setWidth( maxHealthRect.getWidth() * value);
	}
	
	public void drawRectangle(GraphicsContext gc, double x, double y ) {
		gc.setFill(Color.RED);
		gc.fillRect(x, 
					y,
					maxHealthRect.getWidth(),
					maxHealthRect.getHeight());
		
		gc.setFill(Color.LIMEGREEN);
		gc.fillRect(x,
					y,
					currentHealthRect.getWidth(),
					currentHealthRect.getHeight());
	}

}

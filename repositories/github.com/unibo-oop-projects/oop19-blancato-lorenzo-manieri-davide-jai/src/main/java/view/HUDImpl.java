package view;

import controller.ScoreManager;
import controller.ScoreManagerImpl;
import controller.ScreenManager;
import javafx.scene.canvas.GraphicsContext;
import model.HealthBarImpl;
import model.PlayerImpl;

public class HUDImpl implements HUD {
	
	private final static double HEALTH_BAR_X = ScreenManager.getScaleX(5);
	private final static double HEALTH_BAR_Y = ScreenManager.getScaleY(ScreenManager.getMaxScaleY() - 10);
	private HealthBarImpl playerHealthBar;
	private ScoreManager scoreManager = null;
	
	
	public HUDImpl() {
		this.scoreManager = ScoreManagerImpl.getInstance();
		playerHealthBar = new HealthBarImpl(HEALTH_BAR_X, HEALTH_BAR_Y, 
				ScreenManager.getMaxScaleX()-10, 5);
	}
	
	/**
	 * 
	 */
	public void update(GraphicsContext gc) {
		playerHealthBar.setCurrentHealth(PlayerImpl.getInstance().getRelativeHealth());
		playerHealthBar.drawRectangle(gc, HEALTH_BAR_X, HEALTH_BAR_Y);
		gc.fillText("SCORE: " +Integer.toString(this.scoreManager.getCurrentScore().getScore()),
				ScreenManager.getScaleX(ScreenManager.getMaxScaleX()-30),
				ScreenManager.getScaleY(10));
		gc.fillText("KILLS: " +Integer.toString(this.scoreManager.getCurrentScore().getEnemyKilled()),
				ScreenManager.getScaleX(ScreenManager.getMaxScaleX()-30),
				ScreenManager.getScaleY(15));
	}
}

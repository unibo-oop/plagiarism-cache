package view;



import controller.BulletManager;
import controller.EnemyManager;
import controller.PowerUpManager;
import controller.PowerUpManagerImpl;
import controller.ScreenManager;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import model.Player;
import model.PlayerImpl;

public class AnimatorImpl extends AnimationTimer implements Animator{
	
	private GraphicsContext gc; 
	private EnemyManager enemyManager;
	private BulletManager bulletManager;
	private PowerUpManager powerUpManager;
	private HUD hud;
	private Player player = PlayerImpl.getInstance();

	/**
	 * 
	 * @param gc
	 */
	public AnimatorImpl(GraphicsContext gc) {
		this.gc = gc;
		this.enemyManager = new EnemyManager();
		this.bulletManager = new BulletManager();
		this.powerUpManager = new PowerUpManagerImpl();
		this.hud = new HUDImpl();
	}
	
	
	/**
	 * 
	 */
	@Override
	public void handle(long now) {

		this.gc.clearRect(0, 0, ScreenManager.widthScreen, ScreenManager.heightScreen);
		this.hud.update(gc);
		this.enemyManager.update(gc);
		this.bulletManager.update(gc);
		this.powerUpManager.update(gc);
		this.player.draw(gc);
	}
	
	@Override
	public void stop() {
		super.stop();
		this.gc.fillText("PAUSE ",
				ScreenManager.getScaleX((ScreenManager.getMaxScaleX()/2)-3),
				ScreenManager.getScaleY(ScreenManager.getMaxScaleY()/2),
				400);
	}


}

package model;



import java.io.IOException;
import controller.BulletManager;
import controller.InputController.Direction;
import controller.ScoreManager;
import controller.ScoreManagerImpl;
import controller.ScreenManager;
import controller.TimeManager;
import controller.TimeManagerImpl;
import javafx.scene.canvas.GraphicsContext;
import model.BulletImpl.Target;
import program.Game;
import program.GameOver;



public class PlayerImpl extends LivingEntityImpl implements Player{


	private final static String PLAYER_BASIC_IMAGE = "Player.png";
	private final static String PLAYER_SHIELD_IMAGE = "PlayerWithShield.png";
	
	private final static double WIDTH = 15;
	private final static double HEIGHT = 15;
	private final static double POSITION_X = ScreenManager.getScaleX(ScreenManager.getMaxScaleX()/2);
	private final static double POSITION_Y = ScreenManager.getScaleY(ScreenManager.getMaxScaleY()-30);
	private final static double SPEED = 1;
	private final static double INITIAL_DAMAGE = 50;
	private final static double PLAYER_COLLISION_DAMAGE = 100;
	private final static double INITIAL_HEALTH = 100;
	

	
	private TimeManager playerManager; 
	private boolean blinking = false;

	private boolean shield = false;
	private boolean canFire = true;
	
	private boolean up = false; 
	private boolean down = false; 
	private boolean right = false; 
	private boolean left = false; 
	private boolean fire = false;


	private static PlayerImpl player;
	
	public static synchronized PlayerImpl getInstance() {
		if (PlayerImpl.player== null) {
			PlayerImpl.player = new PlayerImpl(WIDTH, HEIGHT, POSITION_X, POSITION_Y);
			return PlayerImpl.player;
		}
		return PlayerImpl.player;
	}
	
	private PlayerImpl(double width, double height, double positionX, double positionY)  {
		super(width, height, positionX, positionY);
		this.setHealth(INITIAL_HEALTH);
		this.setDamage(INITIAL_DAMAGE);
		try {
			this.setImage(ScreenManager.getImage(PLAYER_BASIC_IMAGE));
		}catch(Exception e ) {
			this.setVisible(true);
			System.out.println("errore immagine");
		}

		this.playerManager = TimeManagerImpl.getInstance();
		this.playerManager.setFireTimer(this.getFireSpeed());
	}
	
	public void setCanFire(boolean flag) {
		this.canFire = flag;
	}
	
	public boolean canFire() {
		return this.canFire;
	}
	
	public double getCollisionDamage() {
		return PLAYER_COLLISION_DAMAGE;
	}
	
	public void setBlinking(boolean blink) {
		this.blinking = blink;
	}
	
	public boolean isBlinking() {
		return blinking;
	}

	

	@Override
	public void draw(GraphicsContext gc) {
		if (!this.getRemovable()) {
			this.updateDirection();
			super.draw(gc);
		}else {
			ScoreManager sm = ScoreManagerImpl.getInstance();
			ScoreManagerImpl.getListFromFile();
			sm.addPersonalScore(sm.getCurrentScore());
			ScoreManagerImpl.sortList();
			ScoreManagerImpl.writeListToFile();
			GameOver.gameOver(Game.gc);
		}
	}


	public void setShield(boolean s) {
		this.shield = s;
		if (s) {
			try {
				this.setImage(ScreenManager.getImage(PLAYER_SHIELD_IMAGE));
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.playerManager.setShieldTimer();
		}else {
			try {
				this.setImage(ScreenManager.getImage(PLAYER_BASIC_IMAGE));
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.playerManager.resetShield();
		}
		
	}

	public boolean getShield() {
		return this.shield; 
	}
	
	@Override	
	public void setFireSpeed(long newSpeed) {
		super.setFireSpeed(newSpeed);
		playerManager.setFireTimer(this.getFireSpeed());
	}
	

	
	


	@Override
	public void move(double deltaX, double deltaY) {
		checkMoveX(deltaX * SPEED);
		checkMoveY(deltaY * SPEED);
	}
	
	@Override
	public void fire() {
		if (this.canFire) {
			super.fire();
			RedBullet b = new RedBullet(1,3, 
					this.getPositionX()+(this.width/2), this.getPositionY(), 
					Target.ENEMY, this.getDamage());
			BulletManager.addBullet(b);
			this.setCanFire(false);
		}
	}
	
	
	
	@Override
	public void hit(double damage) {
		super.hit(damage);
		if (!this.isExploding()) {
			this.setBlinking(true);
			playerManager.setBlinkingTimer();
		}
		
			
	}



	@Override
	public void moveUp() {
		this.move(0, -DELTA_Y);
	}



	@Override
	public void moveDown() {
		this.move(0, DELTA_Y);
	}



	@Override
	public void moveLeft() {
		this.move(-DELTA_X, 0);
	}



	@Override
	public void moveRight() {
		this.move(DELTA_X, 0);
	}
	
	public void setDirection(Direction d) {
		switch (d) {
		case UP: this.up = true; 
				break;
		case DOWN: this.down = true; 
				break; 
		case LEFT: this.left = true; 
				break;
		case RIGHT: this.right = true; 
				break;
		case SPACE: this.fire = true;
		default:
			break;
		}
	}
	
	public void deleteDirection(Direction d) {
		switch (d) {
		case UP: this.up = false; 
				break;
		case DOWN: this.down = false; 
				break; 
		case LEFT: this.left = false; 
				break;
		case RIGHT: this.right = false; 
				break;
		case SPACE: this.fire = false;
		default:
			break;
		}
	}
	
	//Metodo per effettuare i movimenti in base alle variabili di direzione impostate
	private void updateDirection() {
		if (up) 
			player.moveUp();
		if (down)
			player.moveDown();
		if (left)
			player.moveLeft();
		if (right)
			player.moveRight();
		if (fire) 
			player.fire();
	}
	

	public static void resetPlayer() {
		
		PlayerImpl.player = null;
		PlayerImpl.getInstance();
	}

	

}

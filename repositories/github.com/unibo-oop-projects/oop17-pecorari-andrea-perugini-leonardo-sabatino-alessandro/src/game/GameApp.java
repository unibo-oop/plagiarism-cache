package game;


import java.io.IOException;
import java.util.*;
import characters.*;
import dodge.*;
import enemies.*;
import powerups.*;
import powerups.PowerUp;
import powerups.WumpaFruit;
import reset.ResetPopupViewImpl;
import urls.CharacterURLImage;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class GameApp implements GameViewObserver {

	//game's environment's dimensions
	public static double SCENE_WIDTH = 1444;
	public static double SCENE_HEIGHT = 768;

	//spawn rate for enemies and powerups (how frequently spawn)
	public static int ENEMY_SPAWN_RANDOMNESS = 110;
	public static int POWERUPS_SPAWN_RANDOMNESS = 380;

	//private GameView gameView;
	private Input input;

	private Random rnd = new Random();
	private List<Image> images = new ArrayList<>();
	private Image HenchmanImage;
	private Image MeteoriteImage;
	private Image NitroImage;
	private Image TNTImage;
	private Image RockballImage;
	private Image wumpaFruitImage;
	private Image akuakuImage;
	private Image lifeImage;
	private Image dodgeImage;
	private Image shieldImage;

	private DodgeImpl dodge;
	private List<String> URLImage = new ArrayList<>();
	private List<Enemy> enemies = new ArrayList<>();
	private List<PowerUp> powerUps = new ArrayList<>();

	private AnimationTimer gameLoop;

	public GameApp() {
		
	}

	public void start() {
		try {

			
			
			GameViewImpl.getGameView().start(new Stage());

			addURL();
			loadGame();
			loadEnemies();
			loadPowerUps();
			loadImages();
			createDodge();
			
			this.gameLoop = new AnimationTimer() {

				@Override
				public void handle(long now) {

					GameViewImpl.getGameView().updateLabel(dodge.getLife(), dodge.getWumpas());

					try {
						spawnEnemies(true);
						spawnPowerUps(true);
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					enemies.forEach(enemy -> enemy.move());
					powerUps.forEach(powerup -> powerup.move());

					dodge.processInput(input);
					dodge.move();

					checkCollisions();

					enemies.forEach(enemy-> enemy.updateUI());
					powerUps.forEach(powerup -> powerup.updateUI());
					dodge.updateUI();
					
					removeCharacters(powerUps);
					removeCharacters(enemies);
					checkLife(GameViewImpl.getGameView().getStage());

					dodge.updateUI();
				}
			};
			
			this.gameLoop.start();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * add images' URLs
	 */
	private void addURL() {
		URLImage.add(CharacterURLImage.HENCHMAN.getURL());
		URLImage.add(CharacterURLImage.METEORITE.getURL());
		URLImage.add(CharacterURLImage.NITRO.getURL());
		URLImage.add(CharacterURLImage.TNT.getURL());
		URLImage.add(CharacterURLImage.ROCKBALL.getURL());
		URLImage.add(CharacterURLImage.WUMPAFRUIT.getURL());
		URLImage.add(CharacterURLImage.AKUAKU.getURL());
		URLImage.add(CharacterURLImage.LIFE.getURL());		
	}

	/**
	 * add Dodge's image
	 */
	private void loadGame() {
		this.dodgeImage = new Image(getClass().getClassLoader().getResource("res/images/DodgeMed.gif").toExternalForm());
		this.shieldImage = new Image(getClass().getClassLoader().getResource("res/images/ShieldedDodgeMed.gif").toExternalForm());
	}

	/**
	 * add power ups
	 */
	private void loadPowerUps() {
		this.wumpaFruitImage = new Image(getClass().getClassLoader().getResource(URLImage.get(5)).toExternalForm());
		this.akuakuImage = new Image(getClass().getClassLoader().getResource(URLImage.get(6)).toExternalForm());
		this.lifeImage = new Image(getClass().getClassLoader().getResource(URLImage.get(7)).toExternalForm());

	}

	/**
	 * add enemies
	 */
	private void loadEnemies() {
		HenchmanImage = new Image(getClass().getClassLoader().getResource(URLImage.get(0)).toExternalForm());
		MeteoriteImage = new Image(getClass().getClassLoader().getResource(URLImage.get(1)).toExternalForm());
		NitroImage = new Image(getClass().getClassLoader().getResource(URLImage.get(2)).toExternalForm());
		TNTImage = new Image(getClass().getClassLoader().getResource(URLImage.get(3)).toExternalForm());
		RockballImage = new Image(getClass().getClassLoader().getResource(URLImage.get(4)).toExternalForm());
		
	}

	/**
	 * load characters' images
	 */
	private void loadImages() {
		images.add(HenchmanImage);
		images.add(MeteoriteImage);
		images.add(NitroImage);
		images.add(TNTImage);
		images.add(RockballImage);
		images.add(wumpaFruitImage);
		images.add(akuakuImage);
		images.add(lifeImage);
		
	}

	/**
	 * It creates Dodge with relative input for movement, and It sets the initial position and image
	 */
	public void createDodge() {

		input = new InputImpl(GameViewImpl.getGameView().getScene());
		
		//add listeners for Dodge's movement
		input.addListeners();

		//set Dodge's position in the scene's center 
		double x = (SCENE_WIDTH - this.dodgeImage.getWidth()) / 2.0;
		
		double y = SCENE_HEIGHT * 0.48;

		Position position = new PositionImpl(x, y);
		
		this.dodge = new DodgeImpl(position);
		dodge.setInput(input);
		this.dodge.setImage(this.dodgeImage, this.shieldImage);
		

		GameViewImpl.getGameView().addToLayer(this.dodge.getImageView());
	}

	/**
	 * 
	 * @param random
	 * @throws IOException
	 * 
	 * It manages the power ups spawn
	 */
	private void spawnPowerUps(final boolean random) throws IOException {
		PowerUp powerup = null;
		Random myrnd = new Random();
		int pu_type = myrnd.nextInt(7);

		if (random && rnd.nextInt(POWERUPS_SPAWN_RANDOMNESS) != 0) {
			return;
		}

		if (pu_type < 4) {
			powerup = new WumpaFruit(calculatePosition(images.get(5)));
			powerup.setImage(images.get(5));
		} else if (pu_type >= 4 & pu_type < 6) {
			powerup = new AkuAku(calculatePosition(images.get(6)));
			powerup.setImage(images.get(6));
		} else if (pu_type >= 6 & pu_type < 7) {
			powerup = new Life(calculatePosition(images.get(7)));
			powerup.setImage(images.get(7));
		}

		this.powerUps.add(powerup);
		GameViewImpl.getGameView().addToLayer(powerup.getImageView());
	}

	private Position calculatePosition(Image image) {
		double val = rnd.nextDouble();

		//It imposes that the characters' movement happens within the limits (bounds)
		while ((val * (SCENE_HEIGHT - image.getWidth())) < dodge.getBoundUp()
				|| (val * (SCENE_HEIGHT - image.getWidth())) > dodge.getBoundDown()) {
			val = rnd.nextDouble();
		}
		return new PositionImpl((SCENE_WIDTH + image.getWidth()), (val * (SCENE_HEIGHT - image.getWidth())));
	}

	/**
	 * 
	 * @param random
	 * @throws IOException
	 * 
	 * It manages the enemies' spawn
	 * It calculates the enemy's position with relative image. So, the enemy will appear at a random position in the game scene
	 */
	private void spawnEnemies(final boolean random) throws IOException {
		Enemy enemy = null;
		if (random && rnd.nextInt(ENEMY_SPAWN_RANDOMNESS) != 0) {
			return;
		}
		
		int enemyType = rnd.nextInt(5);

		if (enemyType == 0) {
			enemy = new Henchman(calculatePosition(images.get(0)));
			enemy.setImage(images.get(0));
		} else if (enemyType == 1) {
			enemy = new Meteorite(calculatePosition(images.get(1)));
			enemy.setImage(images.get(1));
		} else if (enemyType == 2) {
			enemy = new Nitro(calculatePosition(images.get(2)));
			enemy.setImage(images.get(2));
		} else if (enemyType == 3) {
			enemy = new TNT(calculatePosition(images.get(3)));
			enemy.setImage(images.get(3));
		} else if (enemyType == 4) {
			enemy = new RockBall(calculatePosition(images.get(4)));
			enemy.setImage(images.get(4));
		}

		enemies.add(enemy);
		GameViewImpl.getGameView().addToLayer(enemy.getImageView());

	}

	/**
	 * It controls if Dodge is dead. In this case It will appear a reset view (game over) to restart the game
	 */
	public void checkLife(final Stage primaryStage) {
		if (this.dodge.getLife() <= 0) {
			try {
				this.gameLoop.stop();
				primaryStage.setFullScreen(false);
				ResetPopupViewImpl resetView = new ResetPopupViewImpl();
				resetView.start(new Stage());
				primaryStage.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * for enemies and powerups: if an enemy or a powerup collides with Dodge, It will be removed from the scene and Dodge will "suffer" a damage or It will get a benefit
	 * 
	 */
	public void checkCollisions() {

		for (PowerUp powerup : powerUps) {
			if (powerup.collidesWith(dodge)) {
				powerup.collides(dodge);
				//if there is a collision the powerup will disappear from the layer
				powerup.setRemovable(true);
			}
		}
		for (Enemy enemy : enemies) {
			if (enemy.collidesWith(dodge)) {
				//if there is a collision the enemy will disappear from the layer
				enemy.collides(dodge);
				enemy.setRemovable(true);
			}
		}

	}

	/**
	 * @param characterList
	 * It verifies if a character is removable or not. In the first case, It will remove iteratively the removable character
	 */
	public void removeCharacters(final List<? extends CharacterImpl> characterList) {
		///with an iterator for any "CharacterImpl" specialization, the list will be scrolled and it will be removed if it has been set to be removed
		Iterator<? extends CharacterImpl> iter = characterList.iterator();
		while (iter.hasNext()) {
			CrossingCharacter character = (CrossingCharacter) iter.next();

			if (character.isRemovable()) {
				GameViewImpl.getGameView().removeFromLayer(character.getImageView());
				iter.remove();
			}
		}

	}

}
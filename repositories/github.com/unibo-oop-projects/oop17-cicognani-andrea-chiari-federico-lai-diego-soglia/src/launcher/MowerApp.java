package launcher;

import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.Level;

import com.almasb.fxgl.extra.ai.pathfinding.AStarGrid;
import com.almasb.fxgl.extra.ai.pathfinding.NodeState;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.parser.text.TextLevelParser;

import com.almasb.fxgl.settings.GameSettings;
import com.almasb.fxgl.texture.Texture;
import com.almasb.fxgl.ui.UI;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import collision.ColPlayerBlock;
import collision.ColPlayerFlower;
import collision.ColPlayerFuel;
import collision.ColPlayerGravel;
import collision.ColPlayerRock;
import collision.ColPlayerSgravel;
import collision.ColPlayerWater;
import collision.ColPlayerWeed;

import com.almasb.fxgl.app.FXGL;

import controller.PlayerControl;
import controller.MowerUiController;
import factory.MenuFactory;
import factory.MowerFactory;
import factory.MowerType;
import gamemode.RandomMap;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import scoreboard.Scoreboard;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import static com.almasb.fxgl.app.DSLKt.inc;
import static com.almasb.fxgl.app.DSLKt.geti;

/**
 *
 * @author Daniele
 * @author Nicola
 * @author Federico
 * @author Andrea
 * @author Diego
 *
 * This class initialize the whole game
 */
public class MowerApp extends GameApplication {

	public static final int BLOCK_SIZE = 40;
	public static final int MAP_SIZE_X = 21;
	public static final int MAP_SIZE_Y = 11;
	public static final int XRANDPOS = 0;
	public static final int YRANDPOS = 0;
	// seconds
	private static final int TIME_PER_LEVEL = 20;
	private static final int FUEL_SPAWN_TIME = 5;

	private static final int MIN = 2;
	private static final int MAX = 10;
	private static final int NUMBER_OF_LIVES = 3;

	// private Scoreboard savedData = null;
	private ArrayList<Scoreboard> scorelistClassic = new ArrayList<>();
	private ArrayList<Scoreboard> scorelistHard = new ArrayList<>();

	private AStarGrid grid;

	// variabili per provare gli spawn della tanica
	private int index=0;
	private int[] xArray = new int[200];
	private int[] yArray = new int[200];

	// variabili per gestione dei livelli
	private String[] CMmapSelection = { "map1.txt", "map2.txt", "map3.txt", "map4.txt", "map5.txt", "map6.txt", "map7.txt", "map8.txt", "map9.txt", "map10.txt" };
	private int gameLevel = 0;
	public static boolean gameMode = false;
	private boolean gameOver = false;
	private int lvlscore;

	/**
	 * Method to set gameOver variable
	 * @param gameOver
	 */
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	/**
	 *
	 * @return   player entity
	 *
	 */
	public Entity getPlayer() {
		return getGameWorld().getSingleton(MowerType.PLAYER).get();
	}

	/**
	 *
	 * @return   playerControl class
	 *
	 */

	public PlayerControl getPlayerControl() {
		return getPlayer().getComponent(PlayerControl.class);
	}

	/**
	 *
	 * @return   grid game
	 *
	 */

	public AStarGrid getGrid() {
		return grid;
	}

	/**
	 *
	 * @return   TIME_PER_LEVEL variable
	 *
	 */

	public static int getTimePerLevel() {
		return TIME_PER_LEVEL;
	}

	/**
	 *
	 * @return   FUEL_SPAWN_TIME variable
	 *
	 */

	public static int getFuelSpawnTime() {
		return FUEL_SPAWN_TIME;
	}

	/**
	 *
	 * @return   MIN variable for setting min Y coordinate
	 *
	 */

	public static int getMin() {
		return MIN;
	}

	/**
	 *
	 * @return   MAX variable for setting max Y coordinate
	 *
	 */

	public static int getMax() {
		return MAX;
	}

	/**
	 *
	 * method that initializes global audio settings and reads scoreboard
	 *
	 *
	 */

	// background music (menu and in game)
	@Override
	protected void preInit() {
		getAudioPlayer().setGlobalSoundVolume(0.3);
		getAudioPlayer().setGlobalMusicVolume(0.1);

		FXGL.getAudioPlayer().loopBGM("bgm.mp3");

		Gson gson = new Gson();

		try {

			BufferedReader br = new BufferedReader(new FileReader("score.json"));
			// convert the json string back to object

			Type ScoreboardListType = new TypeToken<ArrayList<Scoreboard>>() {
			}.getType();
			List<Scoreboard> scoreboardlist = gson.fromJson(br, ScoreboardListType);
			this.scorelistClassic = (ArrayList<Scoreboard>) scoreboardlist;
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {

			BufferedReader br = new BufferedReader(new FileReader("scoreHard.json"));
			// convert the json string back to object

			Type ScoreboardListType = new TypeToken<ArrayList<Scoreboard>>() {
			}.getType();
			List<Scoreboard> scoreboardlist = gson.fromJson(br, ScoreboardListType);
			this.scorelistHard = (ArrayList<Scoreboard>) scoreboardlist;
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 *
	 * method that initializes game window's size, title, menu and scenefactory
	 *
	 *
	 */

	@Override
	protected void initSettings(GameSettings settings) {

		settings.setWidth(MAP_SIZE_X * BLOCK_SIZE);
		settings.setHeight(MAP_SIZE_Y * BLOCK_SIZE);
		settings.setTitle("Java Mower");
		settings.setCloseConfirmation(true);
		settings.setApplicationMode(ApplicationMode.RELEASE);
		settings.setIntroEnabled(false);
		settings.setMenuEnabled(true);
		settings.setSceneFactory(new MenuFactory());
	}

	private MowerUiController uiController;

	/**
	 *
	 * method that initializes UI
	 *
	 *
	 */

	protected void initUI() {
		uiController = new MowerUiController();
		getStateMachine().getPlayState().addStateListener(uiController);

		UI ui = getAssetLoader().loadUI("mower_ui.fxml", uiController);
		ui.getRoot().setTranslateX(MAP_SIZE_X);
		if(!gameMode){
			Texture brickTexture = getAssetLoader().loadTexture("lifeimage.png");
			brickTexture.setTranslateX(808);
			brickTexture.setTranslateY(7);

			getGameScene().addUINode(brickTexture);
		}
		uiController.getLabelWeed().textProperty().set("weed\nleft");
		uiController.getLabelLives().textProperty().bind(getGameState().intProperty("lives").asString("%d"));
		uiController.getLabelScore().textProperty().bind(getGameState().intProperty("score").asString("Score:\n%d"));
		uiController.getPercentBar().maxValueProperty().bind(getGameState().intProperty("total"));
		getGameScene().addUI(ui);
	}

	/**
	 *
	 * method that initializes inputs
	 *
	 *
	 */

	@Override
	protected void initInput() {
		getInput().addAction(new UserAction("Up") {
			@Override
			protected void onAction() {
				getPlayerControl().up();
			}
		}, KeyCode.UP);

		getInput().addAction(new UserAction("Down") {
			@Override
			protected void onAction() {
				getPlayerControl().down();
			}
		}, KeyCode.DOWN);

		getInput().addAction(new UserAction("Left") {
			@Override
			protected void onAction() {
				getPlayerControl().left();
			}
		}, KeyCode.LEFT);

		getInput().addAction(new UserAction("Right") {
			@Override
			protected void onAction() {
				getPlayerControl().right();
			}
		}, KeyCode.RIGHT);

		getInput().addAction(new UserAction("Turbo") {
			@Override
			protected void onAction() {
				getPlayerControl().turbo();
			}
		}, KeyCode.SPACE);

	}

	/**
	 *
	 * method that initializes games properties
	 *
	 *
	 */

	@Override
	protected void initGameVars(Map<String, Object> vars) {
		vars.put("score", 0);
		vars.put("weedtiles", 0);
		vars.put("gas", TIME_PER_LEVEL);
		vars.put("total", 0);
		vars.put("lives", NUMBER_OF_LIVES);
	}

	/**
	 *
	 * this method reads a map
	 *
	 * @param map name of a map
	 *
	 */

	private void mapSelector(String map) {
		MowerFactory factory = new MowerFactory();
		getGameWorld().addEntityFactory(factory);
		TextLevelParser parser = new TextLevelParser(factory);
		Level level = parser.parse(map);
		getGameWorld().setLevel(level);
	}

	/**
	 *
	 * this method selects random position for fuel's spawn
	 *
	 *
	 */

	public void fuelSpawnPosition() {
		getGameWorld().getEntitiesByType(MowerType.WEED).stream().map(Entity::getPosition).forEach(point -> {
			xArray[index]= (int) point.getX();
			yArray[index]= (int) point.getY();
			index++;
		});
		if(!gameMode){
			getGameWorld().getEntitiesByType(MowerType.GRASS).stream().map(Entity::getPosition).forEach(point -> {
				xArray[index]= (int) point.getX();
				yArray[index]= (int) point.getY();
				index++;
			});
		}

		int spPosition= ThreadLocalRandom.current().nextInt(0, index);
		getGameWorld().spawn("fuel", xArray[spPosition], yArray[spPosition]);
		index=0;
	}



	private void timerStart() {
		getMasterTimer().runAtInterval(() -> {
			inc("gas", -1);

			getGameState().<Integer>addListener("gas", (old, now) -> {

				if (now <=5) {
					FXGL.getAudioPlayer().playSound("lowfuel.wav");
				}

				if (now <= 0) {
					gameOver = true;
					FXGL.getAudioPlayer().playSound("lose.wav");
				}
			});
			if (gameOver == true) {
				onPlayerKilled();
			}

		}, Duration.seconds(1));

		// spawn iniziale della tanica
		getMasterTimer().runOnceAfter(() -> {
			fuelSpawnPosition();
		}, Duration.seconds(FUEL_SPAWN_TIME));

		getGameWorld().spawn("grass", getPlayer().getPosition());
	}

	/**
	 *
	 * this method initializes game's grid, game properties and starts the timer
	 *
	 *
	 */

	protected void initGame() {

		mapSelector(getMapByMode());
		if(gameMode){
			getRandomMap();
		}

		grid = new AStarGrid(MAP_SIZE_X, MAP_SIZE_Y);

		getGameWorld().getEntitiesByType(MowerType.BLOCK).stream().map(Entity::getPosition).forEach(point -> {
			int x = (int) point.getX() / BLOCK_SIZE;
			int y = (int) point.getY() / BLOCK_SIZE;

			grid.setNodeState(x, y, NodeState.NOT_WALKABLE);
		});

		getGameState().setValue("weedtiles", getGameWorld().getEntitiesByType(MowerType.WEED).size());
		getGameState().setValue("total", getGameWorld().getEntitiesByType(MowerType.WEED).size());
		timerStart();
	}

	/**
	 *
	 * method that initializes collisions classes
	 *
	 *
	 */

	@Override
	protected void initPhysics() {

		getPhysicsWorld().addCollisionHandler(new ColPlayerWeed());
		getPhysicsWorld().addCollisionHandler(new ColPlayerRock());
		getPhysicsWorld().addCollisionHandler(new ColPlayerFlower());
		getPhysicsWorld().addCollisionHandler(new ColPlayerFuel());
		getPhysicsWorld().addCollisionHandler(new ColPlayerBlock());
		getPhysicsWorld().addCollisionHandler(new ColPlayerGravel());
		getPhysicsWorld().addCollisionHandler(new ColPlayerSgravel());
		getPhysicsWorld().addCollisionHandler(new ColPlayerWater());
	}

	private String getMapByMode() {
		if (gameMode) {
			return "RandomMap.txt";
		} else {
			return CMmapSelection[gameLevel];
		}
	}

	// resets all paramethers required and loads new levels
	private void nextMap() {
		if (gameMode == false) {
			if (gameLevel >= CMmapSelection.length) {
				gameLevel = 0;
				getDisplay().showMessageBox("Classic mode completed!", () -> {
					showGameOver();
				});
			}
		}
		getGameWorld().clear();
		mapSelector(getMapByMode());
		if (gameMode) {
			getRandomMap();
		}
		gameOver = false;
		getMasterTimer().clear();
		timerStart();
		lvlscore=geti("score");
		getGameState().setValue("gas", TIME_PER_LEVEL);
		getGameState().setValue("weedtiles", getGameWorld().getEntitiesByType(MowerType.WEED).size());
		getGameState().setValue("total", getGameWorld().getEntitiesByType(MowerType.WEED).size());

	}

	/**
	 *
	 * this method show levelcompleted box and start next map
	 *
	 *
	 */

	public void gameOver() {
		FXGL.getAudioPlayer().playSound("winner.wav");
		getDisplay().showMessageBox("Level completed, well done!", () -> {
			gameLevel++;
			nextMap();
		});

	}

	/**
	 *
	 * method that decreases lifes in classic mode and show game over message in both modes
	 *
	 *
	 */

	public void onPlayerKilled() {
		if(!gameMode && geti("lives")>1){
			gameOver = false;
			getDisplay().showMessageBox("Game over! Try again", () -> {
				inc("lives",-1);
				getGameState().setValue("score", lvlscore);
				nextMap();
			});

		}else{
			getDisplay().showMessageBox("Game over!", () -> {
				showGameOver();
			});
		}
	}

	/**
	 *
	 * method that saves final score in scoreboard and shows play again message
	 *
	 *
	 */

	public void showGameOver() {

		if (!gameMode) {


			scorelistClassic.ensureCapacity(10);
			int score = getGameState().getInt("score");
			Scoreboard newscore = new Scoreboard(Menu.getProfileName(), score);

			if (scorelistClassic.size() == 10) {
				if (scorelistClassic.get(9).getHighScore() < newscore.getHighScore()) {
					scorelistClassic.remove(9);
					scorelistClassic.add(newscore);
				}
			} else {
				scorelistClassic.add(newscore);
			}

			Collections.sort(scorelistClassic, new Comparator<Scoreboard>() {
				@Override
				public int compare(Scoreboard s1, Scoreboard s2) {
					return s2.getHighScore() - s1.getHighScore();
				}
			});

			Gson gson = new Gson();
			String json = gson.toJson(scorelistClassic);
			try {
				FileWriter writer = new FileWriter("score.json");
				writer.write(json);
				writer.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			scorelistHard.ensureCapacity(10);
			int score = getGameState().getInt("score");
			Scoreboard newscore = new Scoreboard(Menu.getProfileName(), score);

			if (scorelistHard.size() == 10) {
				if (scorelistHard.get(9).getHighScore() < newscore.getHighScore()) {
					scorelistHard.remove(9);
					scorelistHard.add(newscore);
				}
			} else {
				scorelistHard.add(newscore);
			}

			Collections.sort(scorelistHard, new Comparator<Scoreboard>() {
				@Override
				public int compare(Scoreboard s1, Scoreboard s2) {
					return s2.getHighScore() - s1.getHighScore();
				}
			});

			Gson gson = new Gson();
			String json = gson.toJson(scorelistHard);
			try {

				FileWriter writer = new FileWriter("scoreHard.json");
				writer.write(json);
				writer.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		getDisplay().showConfirmationBox("Play Again?", yes -> {
			if (yes) {
				getGameWorld().getEntitiesCopy().forEach(Entity::removeFromWorld);
				gameOver = false;
				gameLevel = 0;
				startNewGame();
			} else {
				exit();
			}
		});
	}

	/**
	 *
	 * this method places entities in hard mode
	 *
	 *
	 */

	private void getRandomMap() {
		int posX=0;
		int posY=0;
		RandomMap RM = new RandomMap();
		RM.mapCreation();
		while (!RM.getReady()) {
			posX = RM.getXPosition();
			posY = RM.getYPosition();
			String spawnEntity = RM.getSpawnEntity();

			if((posX==1) && (posY==1)) {
				 spawnEntity="R";
			}


			switch (spawnEntity) {
			case "0":
				getGameWorld().spawn("weed", posX * BLOCK_SIZE, posY * BLOCK_SIZE);
				break;
			case "F":
				getGameWorld().spawn("flower", posX * BLOCK_SIZE, posY * BLOCK_SIZE);
				break;
			case "R":
				getGameWorld().spawn("rock", posX * BLOCK_SIZE, posY * BLOCK_SIZE);
				break;
			case "G":
				getGameWorld().spawn("gravel", posX * BLOCK_SIZE, posY * BLOCK_SIZE);
				break;
			case "W":
				getGameWorld().spawn("water", posX * BLOCK_SIZE, posY * BLOCK_SIZE);
				break;
			}
		}

		RM.setNotReady();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
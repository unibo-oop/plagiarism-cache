package pvz.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.text.View;

import pvz.controller.data.DataManager;
import pvz.controller.data.DataManagerInterface;
import pvz.controller.data.Player;
import pvz.controller.data.Score;
import pvz.model.ModelInterface;
import pvz.model.Model;
import pvz.model.entity.plant.PlantType;
import pvz.view.*;

/**
 * 
 * Controller
 *
 */
public class Controller implements ControllerInterface {
	
	private final static int FPS_25 = 25;
	private final static int FPS_60 = 60;
	private final static int FPS_120 = 120;
	
	private final List<Integer> availableFPS = new ArrayList<>(Arrays.asList(FPS_25,FPS_60,FPS_120));

	private Optional<GameLoop> gameLoop;
	private ViewInterface view ;
	private DataManagerInterface dataManager;
	private Optional<String> currentPlayerName;
	private Model model;
	private Integer fps;
	private Optional<Mode> currentMode;

	public Controller() throws Exception {
		this.fps = FPS_60;
		this.dataManager = new DataManager();
		this.gameLoop = Optional.empty();
		this.currentPlayerName= Optional.empty();
		this.currentMode = Optional.empty();		
	}

	@Override
	public void quit() {
		
	}

	@Override
	public void abortGameLoop() {
		if (this.gameLoop.get().isInPause()) {
			throw new IllegalStateException();
		} else {
			this.gameLoop.get().abort();
		}
	}

	@Override
	public void resumeGameLoop() {
		if (this.gameLoop.get().isInPause()) {
			throw new IllegalStateException();
		} else {
			this.gameLoop.get().resumeGame();
		}
	}

	@Override
	public void pauseGameLoop() {
		if (this.gameLoop.get().isRunning()) {
			throw new IllegalStateException();
		} else {
			this.gameLoop.get().abort();
		}
	}

	@Override
	public boolean isRunning() {
			return this.gameLoop.get().isRunning();
	}

	@Override
	public boolean isInPause() {
			return this.gameLoop.get().isInPause();
	}

	@Override
	public boolean registerPlayer(String playerName) {
		return this.dataManager.registerAndSetPlayer(playerName);
	}

	@Override
	public void loadPlayer(String playerName) {
		this.dataManager.setPlayer(playerName);
	}

	@Override
	public void savePlayerInfo() {
		this.dataManager.saveData();
	}

	@Override
	public List<String> getRegisteredPlayers() {
		return this.dataManager.registeredPlayers();
	}

	@Override
	public Map<Mode, List<Score>> getHighScores() {
		return this.dataManager.getAllPlayersHighScores();
	}

	@Override
	public List<Score> getPlayerHighScores(String playerName, Mode mode) {

		return this.dataManager.getPlayerHighScores(playerName).get(mode);
	}

	@Override
	public int getCurrentEnergy() {
		return this.model.getCurrentEnergy();
	}

	@Override
	public List<PlantType> getPlantsUnlocked() {

		if (this.dataManager.getCurrentPlayer().isPresent()) {
			final int level = this.dataManager.getCurrentPlayer().get().getLevelProgress();
			List<PlantType> list = Arrays.asList(PlantType.values()).stream().limit(3 + level)
					.collect(Collectors.toList());
			return list;
		} else {
			throw new IllegalStateException();
		}

	}

	@Override
	public int getLevelsUnlocked() {
		if(this.dataManager.getCurrentPlayer().isPresent()){
			return this.dataManager.getCurrentPlayer().get().getLevelProgress();
		}else{
			throw new IllegalStateException();
		}
	}

	@Override
	public int getTotLevels() {
		return Mode.HISTORY.getTotLevels();
	}

	@Override
	public void startGameLoop(Optional<Integer> level, Set<PlantType> plants) {
		if (this.gameLoop.isPresent() || !this.currentMode.isPresent()) {
			throw new IllegalStateException();
		}
		final GameLoop gameLoop = new GameLoop(this, this.view, level, this.dataManager, this.currentMode.get(),
				this.fps,plants);
		this.gameLoop = Optional.of(gameLoop);
		this.gameLoop.get().run();

	}

	@Override
	public List<String> getCredits() {
		// Not necessary now
		return null;
	}

	@Override
	public void setFPS(int fps) {
		this.fps= fps;
	}

	@Override
	public void setMode(Mode mode) {
		this.currentMode = Optional.of(mode);
	}

	@Override
	public List<String> getPlantInfo(PlantType plant) {
		//   Not necessary now
		return null;
	}

	

	@Override
	public String currentPlayer(){
		Optional<Player> player = this.dataManager.getCurrentPlayer();
		if(player.isPresent()){
			return player.get().getName();
		}else{
			throw new IllegalStateException();
		}
	}
	

	@Override
	public List<Integer> availableFPS() {
		return this.availableFPS;
	}

	
	@Override
	public int getFPS() {
		return this.fps;
	}
	
	protected void setView(final ViewInterface view ){
		this.view = view;
	}
	
}

package controller;

import model.exceptions.InvalidConfigException;
import model.games.Game;
import model.games.GameConfig;
import model.players.Decoder;
import model.players.PlayerFactory;
import view.interfaces.ConfigView;

public class ConfigControllerImpl implements ConfigController {
	private final Game game;
	private final ConfigView view;
	
	public ConfigControllerImpl (Game game, ConfigView view) {
		this.game= game;
		this.view = view;
		
		game.loadDefaults();
		this.view.setController(this);

		game.addObserver(view);
		this.game.notifyObservers();
	}
	
	/**
	 * adds a new decoder to list with default values
	 */
	public void addDecoder() {
		if(this.game.getDecoders().size() < GameConfig.MAX_NO_DECODERS) {
			
			view.fillModel(this.game);
			
			Decoder decoder = PlayerFactory.createDecoder();
			this.game.getDecoders().add(decoder);
			this.game.notifyObservers();
		}
	}

	/**
	 * removes an existing decoder from list
	 * @param decoder Decoder to remove
	 */
	public void removeDecoder(Decoder decoder) {
		if(this.game.getDecoders().size() > GameConfig.MIN_NO_DECODERS) {
			
			view.fillModel(this.game);
			
			this.game.getDecoders().remove(decoder);
			this.game.notifyObservers();
		}
	}
	
	/**
	 * Save and Validate Game Configuration
	 * @throws InvalidConfigException thrown if Validation fails
	 */
	public void saveSettings() throws InvalidConfigException {
		view.fillModel(this.game);
		this.game.validateConfig();
	}
}

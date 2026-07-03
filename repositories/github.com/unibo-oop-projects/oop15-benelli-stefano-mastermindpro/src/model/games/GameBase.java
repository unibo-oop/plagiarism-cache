package model.games;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.games.Choice;
import model.observers.Observer;
import model.players.Decoder;
import model.players.Encoder;

/**
 * 
 * @author Stefano Benelli
 *
 * This class represents a basic implementation of Game
 */
public abstract class GameBase implements Game, Serializable {

	private static final long serialVersionUID = 9001009762943659279L;

	private Encoder encoder;
	private transient List<Observer<Game>> observers;
	private final List<Decoder> decoders;
	private final GameConfig gameConfig;

	protected GameBase() {
		
		observers = new ArrayList<Observer<Game>>();
		
		this.gameConfig = new GameConfig();
		this.encoder = null;
		this.decoders = new ArrayList<Decoder>();
	}

	@Override
	/**
	 * Returns the Game Configuration
	 * @return Game Configuration parameters
	 */
	public GameConfig getGameConfig() {
		return this.gameConfig;
	}

	@Override
	/**
	 * Set the Encoder object
	 * @param encoder Encoder object
	 */
	public void setEncoder(Encoder encoder) {
		this.encoder = encoder;
	}
	
	@Override
	/**
	 * Returns the Encoder object
	 * @return Encoder object
	 */
	public Encoder getEncoder() {
		return encoder;
	}

	@Override
	/**
	 * Returns the List of Decoders
	 * @return List of Decoders
	 */
	public List<Decoder> getDecoders() {
		return decoders;
	}
	
	@Override
	public void addObserver(Observer<Game> component) {
		observers.add(component);
	}
	
	@Override
	public void removeObserver(final Observer<Game> component) {
		observers.remove(component);
	}
	
	@Override
	public void notifyObservers() {
		for(Observer<Game> o : observers) {
			o.update(this);
		}
	}

	/**
	 * Generates a new random Code
	 * @param codeLength Length of the Secret Code
	 * @param noChoices Number of Colors allowed
	 * @return the new Secret Code
	 */
	protected Choice[] generateSecretCode(int codeLength, int noChoices) {

		List<Choice> code = new ArrayList<Choice>(codeLength);

		boolean valueFound = false;
		Random randomGenerator = new Random();
		
		for (int i=0; i<codeLength; i++) {

			valueFound=false;

			if (code.size() < codeLength) {
				while(!valueFound) {    
					int random = randomGenerator.nextInt(codeLength)+1;
					Choice randomChoice = Choice.values()[random];
					if (!code.contains(randomChoice)) {
						code.add(randomChoice);
						valueFound=true;
					}
				}
			}
		}

		Choice[] ret = new Choice[codeLength];
		code.toArray(ret);
		return ret;
	}


    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
		this.observers = new ArrayList<Observer<Game>>();
	}
}
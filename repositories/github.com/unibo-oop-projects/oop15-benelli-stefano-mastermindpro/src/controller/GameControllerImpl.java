package controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import controller.data.Persistent;
import controller.data.PersistentException;
import controller.data.PersistentFileSystem;
import model.games.Choice;
import model.games.Game;
import model.games.Hint;
import model.games.Round;
import model.players.Decoder;
import view.images.ImageSetFactory;
import view.interfaces.GameView;

public class GameControllerImpl implements GameController {

	private final Game game;
	private final GameView view;
	
	public GameControllerImpl (Game game, GameView view) {
		this.game = game;
		this.view = view;

		this.game.initializeGame();
		this.view.setController(this);

		this.view.initialize(game);

		game.addObserver(view);
		this.game.notifyObservers();
	}

	/**
	 * Toggles the Debug Mode for the current Game
	 */
	public void toggleDebugMode() {
		if(!this.game.getGameCompleted()) {
			this.game.getEncoder().setCodeHidden(!this.game.getEncoder().getCodeHidden());
			this.game.notifyObservers();
		}
	}

	/**
	 * Submits the round identified by DecoderId / RoundId
	 * @param decoderId Id of Decoder
	 * @param roundId Id of Round
	 */
	public void submitRound(int decoderId, int roundId) {
		
		Optional<Round> round = getActiveRound(decoderId, roundId);
		if(round.isPresent()) {	
			
			//calcolo degli hints
			computeHints(round.get(), this.game.getEncoder().getSecretCode());
			
			round.get().setSubmitted(true);
			
			//calcolo dello stato del gioco
			if(this.game.getGameCompleted()) {
				this.game.getEncoder().setCodeHidden(false);
			}
			
			this.game.notifyObservers();
		}
	}
	
	/**
	 * Increment the Choice value identified by DecoderId / RoundId / ChoiceId
	 * @param decoderId Id of Decoder
	 * @param roundId Id of Round
	 * @param choiceId Id of Choice
	 */
	public void nextChoice(int decoderId, int roundId, int choiceId) {

		Optional<Round> round = getActiveRound(decoderId, roundId);
		if(round.isPresent()) {
			round.get().getChoices()[choiceId] = getNextChoice(round.get(), choiceId);
			this.game.notifyObservers();
		}
	}
	
	/**
	 * Determines the active round and checks if it corresponds to the round identified by DecoderId / RoundId
	 * @param decoderId Id of Decoder
	 * @param roundId Id of Round
	 * @return return the Active Round if exists
	 */
	private Optional<Round> getActiveRound(int decoderId, int roundId) {
		Decoder decoder = this.game.getDecoders().get(decoderId);

		if(decoder.getActiveRound().isPresent()) {
			
			int idx = Arrays.asList(decoder.getRounds()).indexOf(decoder.getActiveRound().get());

			if (idx == roundId) {
				return Optional.of(decoder.getRounds()[idx]);
			}
		}
		
		return Optional.empty();
	}
	
	private void computeHints(Round round, Choice[] secretCode) {
		
		Choice[] roundChoices = round.getChoices();
		
		int countHit=0, countWp=0;
		
		for(int i=0; i<roundChoices.length; i++) {
			for(int j=0; j< secretCode.length; j++) {
				if(roundChoices[i] == secretCode[j]) {
					if(i==j) {
						countHit++;
					} else {
						countWp++;
					}
				}
			}
		}

		//dopo aver conteggiato le occorrenze aggiorno la lista degli Hints
		for(int i=0; i<round.getHints().length; i++) {
			if(countHit>0) {
				round.getHints()[i] = Hint.HIT;
				countHit--;
			} else if (countWp>0) {
				round.getHints()[i] = Hint.WRONGPOS;
				countWp--;
			} else {
				round.getHints()[i] = Hint.EMPTY;
			}
		}
	}

	/**
	 * Changes the Image Set used by the View
	 * @param setGroup Image Set Group
	 */
	public void changeImageSet(ImageSetFactory.SetGroup setGroup) {
		ImageSetFactory.setCurrentGroup(setGroup);
		this.game.notifyObservers();
	}
	
	/**
	 * Saves the current Game
	 * @throws PersistentException thrown if an error occurs during Save process
	 */
	public void saveGame() throws PersistentException {
		Persistent p = new PersistentFileSystem();
		if(this.game.getGameCompleted()) {
			//gioco completato, lo salvo nell'archivio
			p.clearPendingGame();
			p.archiveGame(this.game);
		} else {
			//gioco ancora da completare, lo salvo come pending
			p.savePendingGame(game);
		}
	}

	private Choice getNextChoice(Round round, int choiceId) {
		
		Choice currentChoice = round.getChoices()[choiceId];
		
		List<Choice> choiceValues = Arrays.asList(Choice.values());
		int idx = choiceValues.indexOf(currentChoice);

		int next = idx;
		boolean found = false;
		
		do {
			if(next == this.game.getGameConfig().getNoOfChoices()) {
				next = 0;
				found=true;
			} else {
				next++;
				if(!Arrays.asList(round.getChoices()).contains(choiceValues.get(next))) {
					found=true;
				}
			}
		}
		while(!found);
			
		return choiceValues.get(next);
	}
}

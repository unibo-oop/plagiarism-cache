package view;

import java.util.List;

import libs.observe.*;

/**
 * A class containing useful observers to allow the view to exchange information
 * with the controller.
 * 
 * @author Ryan Perrina
 *
 */
public class GameViewObservables {

	private ObservableElement<CurrentPlayerInfo> currentPlayer = new ObservableElement<>();
	private ObservableElement<List<PlayerInfo>> otherPlayers = new ObservableElement<>();
	private ObservableElement<List<String>> targets = new ObservableElement<>();
	private ObservableElement<String> action = new ObservableElement<>();
	private String chosenCard = "";
	private ObservableElement<String> chosenPlayer = new ObservableElement<>();

	public ObservableElement<CurrentPlayerInfo> getCurrentPlayer() {
		return this.currentPlayer;
	}

	public void setCurrentPlayer(final CurrentPlayerInfo currentPlayerInfo) {
		this.currentPlayer.set(currentPlayerInfo);
	}

	public ObservableElement<List<PlayerInfo>> getOtherPlayers() {
		return this.otherPlayers;
	}

	public void setOtherPlayers(final List<PlayerInfo> otherPlayers) {
		this.otherPlayers.set(otherPlayers);
	}

	public ObservableElement<String> getAction() {
		return this.action;
	}

	public void setAction(final ObservableElement<String> action) {
		this.action = action;
	}

	public ObservableElement<List<String>> getTargets() {
		return this.targets;
	}

	public void setTargets(final ObservableElement<List<String>> targets) {
		this.targets = targets;
	}

	public String getChosenCard() {
		return this.chosenCard;
	}

	public void setChosenCard(String chosenCard) {
		this.chosenCard = chosenCard;
	}

	public ObservableElement<String> getChosenPlayer() {
		return this.chosenPlayer;
	}

}
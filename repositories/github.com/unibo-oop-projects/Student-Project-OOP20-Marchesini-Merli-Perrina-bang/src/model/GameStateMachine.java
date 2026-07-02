package model;

import libs.observe.ObservableElement;
import model.states.State;

/**
 * A class which handles the changes of states of the game.
 * 
 * @author Davide Merli
 *
 */
public class GameStateMachine {

	private State currentState;
	private Table table;
	private ObservableElement<String> messageObs = new ObservableElement<>();

	public GameStateMachine(final Table table) {
		this.table = table;
	}

	/**
	 * Handles current state
	 */
	public void go() {
		this.currentState.handle(this);
	}

	/**
	 * Returns the message observable.
	 * 
	 * @return messageObs
	 */
	public ObservableElement<String> getMessageObservable() {
		return this.messageObs;
	}

	/**
	 * Sets the message of the observable.
	 * 
	 * @param message the message to set
	 */
	public void setMessage(final String message) {
		this.messageObs.set(message);
	}

	/**
	 * Returns the current state.
	 * 
	 * @return
	 */
	public State getCurrentState() {
		return this.currentState;
	}

	/**
	 * Sets the current state.
	 * 
	 * @param state
	 */
	public void setCurrentState(final State state) {
		this.currentState = state;
	}

	/*
	 * Returns the table.
	 */
	public Table getTable() {
		return this.table;
	}
}

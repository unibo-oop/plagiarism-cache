package dev.spaccabolle.states;

import java.awt.Graphics;
import java.io.File;

import dev.spaccabolle.Handler;

/**
 * The Class State.
 */
public abstract class State {
	
	/** The current state. */
	private static State currentState = null;
	
	/** The level. */
	public static File level;
	
	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	public static void setState(State state){
		currentState = state;
	}
	
	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public static State getState(){
		return currentState;
	}

	/** The handler. */
	protected Handler handler;
	
	/**
	 * Instantiates a new state.
	 *
	 * @param handler the handler
	 */
	public State(Handler handler){
		this.handler = handler;
	}
	
	/**
	 * Tick.
	 */
	public abstract void tick();
	
	/**
	 * Render.
	 *
	 * @param g the g
	 */
	public abstract void render(Graphics g);
	
}

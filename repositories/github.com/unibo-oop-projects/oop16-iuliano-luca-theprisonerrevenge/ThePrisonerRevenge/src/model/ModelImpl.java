package model;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import states.*;

/**
 * This Class implements {@link Model}. This is the MODEL of MVC PATTERN, It
 * encapsulates the state of the application. This Class is also based on the
 * STATE PATTERN, specifically this represent the CONTEXT. it deals with
 * creating a Map of {@link states.State} and handle the switch between them.
 * 
 * @author Luca
 */
public class ModelImpl implements Model {

	private Map<StateEnum,State> stateMap;
	private State currentState;

	/**
	 * Initialize the Map of states with all the possible {@link StateEnum} that can be
	 * the application. After the initialization, the "stateMap" will be set to
	 * unmodifiable to make sure that can't be added other {@link State}.
	 */
	public ModelImpl() {
		this.stateMap = new HashMap<>();
		this.stateMap.put(StateEnum.MENU_STATE,new StateMenu(this));
		this.stateMap.put(StateEnum.GAME_STATE, new StateGame(this));
		this.stateMap.put(StateEnum.OPTION_STATE, new StateOption(this));
		this.stateMap.put(StateEnum.WIN_STATE, new StateGameWin(this));
		this.stateMap.put(StateEnum.GAMEOVER_STATE, new StateGameOver(this));
		this.stateMap = Collections.unmodifiableMap(this.stateMap); 
		this.setState(StateEnum.MENU_STATE);
	}
	
	@Override
	public void setState(final StateEnum state) {
		this.currentState = this.stateMap.get(state);
	}
	
	@Override
	public State getState() throws NullPointerException {
		if (this.currentState != null) {
			return this.currentState;
		} else {
			throw new NullPointerException("ERROR! current state not set.");
		}
	}
	
	@Override
	public Map<StateEnum,State> getStateMap() throws NullPointerException {
		if(this.stateMap != null){
			return this.stateMap;
		}else {
			throw new NullPointerException("ERROR! state Map not initialized.");
		}	
	}
	
	@Override
	public void getGraphics(final Graphics2D graphics){
		this.getState().print(graphics);
	}
	
	@Override
	public void updateModel(){
		this.getState().update();
	}

	@Override
	public void resetGame() {
		((StateGame) this.stateMap.get(StateEnum.GAME_STATE)).resetGame();
	}
	
	@Override
	public void keyPressed(KeyEvent key) {
		this.getState().keyPressed(key);
	}

	@Override
	public void keyReleased(KeyEvent key) {
		this.getState().keyReleased(key);
	}
}

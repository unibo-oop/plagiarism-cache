package view.game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import controller.GameStatus;
import controller.ViewGameController;
import controller.gameStatusManager.ViewGameStatusManager;

import menu.Board;
import menu.StateGame;
import menu.StateGameOver;
import menu.StateMenu;
import menu.StateWin;
import menu.gameview.StateInGameMenu;

import model.entitiesutil.MappedEntity;

/**
 * implementation of {@link GameView}. Manage all the graphics aspects
 *
 */
public class GameViewImpl extends KeyAdapter implements GameView {
    private final ViewGameStatusManager flag;
    private final ViewGameController controller;
    private final List<Set<MappedEntity>> guiUpdateSet;
    private final Map<GameEvent,Boolean> keyPressed;
    private final Board board;

    /**
     * implementation of {@link GameView}. Manage all the graphics aspects
     *
     */
    public GameViewImpl(final ViewGameController controller) {
    	this.controller = controller;
        this.flag = controller.getViewStatusManager();
        this.guiUpdateSet = Collections.synchronizedList(new LinkedList<>());
        this.keyPressed = Collections.synchronizedMap(new HashMap<>());
        this.board = new Board(controller);
    }
    
   /**
 	* {@inheritDoc}
 	*/
    @Override
	public List<GameEvent> getEvents(){
    	return this.keyPressed.entrySet().stream()
    			.filter(e -> e.getValue() == true)
    			.map(e-> e.getKey())
    			.collect(Collectors.toList());
    }

     /**
 	 * {@inheritDoc}
 	 */
     @Override
     public void updateGui(Set<MappedEntity> updates){
        this.guiUpdateSet.add(updates);
        this.board.render(this.guiUpdateSet.get(0));
        this.guiUpdateSet.remove(0);
    }
    
    /**
	 * {@inheritDoc}
	 */
    @Override
    public void keyPressed(KeyEvent e) {
    	this.setKeyValue(e.getKeyCode(), true);
    }

	/**
	 * {@inheritDoc}
	 */
    @Override
    public void keyReleased(KeyEvent e){
    	this.setKeyValue(e.getKeyCode(), false);
    }
    
   /**
 	* {@inheritDoc}
 	*/
    @Override
	public void openGameOver(){
    	this.board.getMenuController().changeState(new StateGameOver(this.board));
    }
    
    /**
 	 * {@inheritDoc}
 	 */
     @Override
     public void openVictoryScene(){
    	this.board.getMenuController().changeState(new StateWin(board, this.controller.getScore()));
    }
    
    /**
     * method for set the pause
     */
    private void setPause(){
    	this.board.getMenuController().changeState(new StateInGameMenu(this.board));
        this.flag.pause();
    }
    
    /**
 	* {@inheritDoc}
 	*/
    @Override
    public void clearKeyMap() {
    	this.keyPressed.clear();
    }

    /**
     * method for resume the game
     */
    private void resume(){
    	this.keyPressed.clear();
    	this.board.getMenuController().changeState(new StateGame(this.board));
        this.flag.resume();
    }

    /**
     * method for stop the game
     */
    private void stop() {
    	this.keyPressed.clear();
    	this.board.setCurrentState(new StateMenu(this.board));
    	this.flag.stop();
    }

    /**
     * method for restart the game
     */
    private void restart() {    
    	this.keyPressed.clear();
    	this.flag.restart();
    	this.board.getMenuController().changeState(new StateGame(this.board));
    }
    
    /**
     * method that handles keyboard inputs 
     * @param code
     * @param value
     */
    private void setKeyValue(int code, boolean value) {
    	switch (code) {
    	case KeyEvent.VK_ESCAPE:
    		if(this.flag.getGameStatus().equals(GameStatus.PAUSED) && value) {
    			this.resume();
    		}
 
    		if(this.flag.getGameStatus().equals(GameStatus.RUNNING) && value) {
    			 this.setPause(); 
    		}
    		break;
    	case KeyEvent.VK_R:
    		if(this.flag.getGameStatus().equals(GameStatus.PAUSED) && value) {
    			this.restart();
    		}
    		break;
    	case KeyEvent.VK_S:
    		if(this.flag.getGameStatus().equals(GameStatus.PAUSED) && value) {
    			this.stop();
    		}
    		break;
    	case KeyEvent.VK_LEFT:
    	case KeyEvent.VK_RIGHT:
    	case KeyEvent.VK_SPACE:
    		this.keyPressed.put(this.toGameEvent(code), value);
    	}
    }
    
    /**
     * method that manages possible game events
     * @param code
     * @return
     */
    private GameEvent toGameEvent(int code) {
    	switch (code) {
		case KeyEvent.VK_LEFT:
			return GameEvent.LEFT;

		case KeyEvent.VK_RIGHT:
			return GameEvent.RIGHT;

		case KeyEvent.VK_SPACE:
			return GameEvent.FIRE;

		default:
			throw new IllegalArgumentException("Wrong typing");
		}
    }

    /**
 	 * {@inheritDoc}
 	 */
     @Override
     public int getWidth() {
		return this.board.getWidth();
	}

     /**
 	 * {@inheritDoc}
 	 */
     @Override
     public int getHeight() {
		return this.board.getHeight();
	}
}
package menu;


import java.awt.Color;
import java.util.Set;

import javax.swing.BoxLayout;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import controller.ViewGameController;
import menuController.menuController;
import menuController.menuControllerImpl;
import model.entitiesutil.MappedEntity;
import util.Constants;

/**
 * class that manages the change and creation of all the various states.
 */
public class Board {

	private JFrame frame = new JFrame();
	private State currentState;
	private menuController menuController = new menuControllerImpl(this); 
	private ViewGameController controller;
	private String playerSkin;
	
	/**
	 * the constructor of the first state in the project, it contains all the frame characteristics.
	 */
	public Board(ViewGameController viewGameController) {
		this.controller = viewGameController;
		this.frame.setTitle("Space Invaders Remake");
		this.frame.setPreferredSize(Constants.ObjectDimension.preferDimension);
		this.frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		this.frame.pack();
		this.frame.setLocationRelativeTo(null);
		this.frame.setResizable(false);
		this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.menuController.changeState(new StateMenu(this));
		this.frame.setVisible(true);
	}
	
	/**
	 * it take the new state in input and it shows the state on the frame repainting it and removing all the object of the last state.
	 * @param newState
	 */
	public void setCurrentState(State newState){
		this.frame.getContentPane().removeAll();
		this.currentState = newState;
		this.frame.getContentPane().add(currentState.getMainPanel());
		this.frame.getContentPane().setBackground(Color.black);
		this.currentState.getMainPanel().requestFocusInWindow();
		this.frame.pack();
	}
	
	/** a method to get the game view controller.
	 * 
	 * @return the GameViewController
	 */
	public ViewGameController getController() {
		return this.controller;
	}
	
	/** a method to get the menu controller.
	 * 
	 * @return the menuController
	 */
	public menuController getMenuController() {
		return this.menuController;
	}
	/** a method to get the mainFrame.
	 * 
	 * @return the mainFrame
	 */
	public JFrame getFrame() {
		return this.frame;
	}
	
	/**
	 * A method to get the screen width 
	 * @return the screen width
	 */
	public int getWidth() {
		return this.currentState.getMainPanel().getWidth();
	}

	/**
	 * A method to get the screen height 
	 * @return the screen height
	 */
	public int getHeight() {
		return this.currentState.getMainPanel().getHeight();
	}

	/**
	 * A method to get the state of the game
	 * @return the state of the game
	 */
	private StateGame getStateGame(){
		return (StateGame)this.currentState;
	}

	/**
	 * A method to refresh the game panel
	 */
	public void render(Set<MappedEntity> set) {
		if(this.currentState.getClass().getSimpleName().equals("StateGame")) {
			this.getStateGame().refresh(set);
		}
	}

	/**
	 * A method to set the player skin
	 * @param skinUri
	 */
	public void setPlayerSkin(String skinUri) {
		if(this.currentState.getClass().getSimpleName().equals("StateSelectSkin")) {
			this.playerSkin = skinUri;
		}
	}

	/**
	 * A method that return the player skin
	 * @return the player skin
	 */
	public String getPlayerSkin() {
		return this.playerSkin;
	}
}

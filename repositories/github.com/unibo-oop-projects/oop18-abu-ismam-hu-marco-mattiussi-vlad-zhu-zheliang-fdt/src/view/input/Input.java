package view.input;

import model.tower.TowerType;

/**
 * Public Classfor inputs
 */
public interface Input {
	
	/**
     * Returns the horizontal position of the player's click in the window.
     * 
     * @return x position
     */
    int getX();

    /**
     * Returns the vertical position of the player's click in the window.
     * 
     * @return y position
     */
    int getY();

    /**
     * Method to return the type of the Input.
     * 
     * @return InputType (ADD,REMOVE)
     */
    InputType getInputType();
    
    /**
     * Method to return the type of Tower
     * @return the TowerType
     */
    
    TowerType getTowerType();

}

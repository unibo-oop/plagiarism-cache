package view;

import java.util.List;

import model.Entity;

public interface View {
	
	/**
	 * 	The method that start the main Window class
	 */
	 public void startView();
	 
	 /**
	  * 
	  * @return the SceneManager
	  */
	 public SceneManager getSceneManager();
	 /**
	  * Setter of road 
	  * @param r Road
	  */
	 public void setScene(Road r);
	 
	 /**
	  * The method called when the game is over
	  */
	 public void gameOver();
	 
	 /**
	  * Initialize the road class in the view 
	  */
	 public void initializeRoad();
	 
	 /**
	  * Pass the stats in the main stage of the view 
	  * 
	  * @param health Player's health
	  * @param shield Player's shield
	  * @param score Game's score
	  */
	 public void updateText(final double health, final int shield, final int score);
	 
	 
	 /**
	  * Call the draw entities method
	  * @param listEntities
	  */
	 public void drawEntities(final List<Entity> listEntities);
}

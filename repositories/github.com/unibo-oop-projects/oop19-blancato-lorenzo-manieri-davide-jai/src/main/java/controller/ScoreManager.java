package controller;


import javafx.collections.ObservableList;
import model.PersonalScore;


public interface ScoreManager {

	/**
	 * 
	 * @return
	 * 		Return the current game session score
	 */
	public PersonalScore getCurrentScore();
	/**
	 * 
	 * @return
	 * 		Return the observable score list
	 */
	public ObservableList<PersonalScore> getScoreList();
	
	
	/**
	 * Add a personal score to score list
	 * @param ps
	 */
	public void addPersonalScore(PersonalScore ps);


	
	
}

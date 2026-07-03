package model;

import java.io.File;
import java.util.ArrayList;

public interface Fight {
	
	/**
	 * 
	 * @param punti
	 * @return paths of the JPG blue-score
	 */
	public String[] getScoreBlue(Integer punti);
	/**
	 * 
	 * @param punti
	 * @return  paths of the JPG redore
	 */
	public String[] getScoreRed(Integer punti);
	/**
	 * 
	 * @param warnings
	 * @return  paths of the JPG red-warning
	 */
	public String getWarningRed(Integer warnings);
	/**
	 * 
	 * @param warnings
	 * @return  paths of the JPG blue-warning
	 */
	public String getWarningBlue(Integer warnings);
	/**
	 * Create a Clip object and play it
	 * @param Sound
	 * 
	 */
	public void playSound(File Sound);
	/**
	 * Insert in a List<Match> the match concluded
	 * @param atleta1
	 * @param atleta2
	 * @param risultato
	 */
	public void insertListaMatch(String atleta1, String atleta2, String risultato);
	/**
	 * Set the matches list
	 * @param lista
	 */
	public void setListaMatch(ArrayList<Match> lista);
	/**
	 * Get the matches list
	 * @return
	 */
	public ArrayList<Match> getListaMatch();
	/**
	 * Adds the fights fought in the file(data backup)
	 */
	public void insertListaMatchFile();
	/**
	 * 
	 * @return Get the fights saved in the file .dat
	 */
	public ArrayList<Match> getListaMatchFile();
}

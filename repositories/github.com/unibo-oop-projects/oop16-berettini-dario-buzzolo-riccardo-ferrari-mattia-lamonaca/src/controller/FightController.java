package controller;

import java.io.File;
/**
 * 
 * @author Berettini, Buzzolo, Ferrari, Lamonaca
 *
 */
public interface FightController {
	/**
	 * Open score machine JFrame, where the fight is ready to start
	 * @param cognome1 Surname of the first athlete
	 * @param cognome2 Surname of the second athlete
	 */
	public void startFight(String cognome1, String cognome2);
	/**
	 * Open the historical JFrame with all the fights fought
	 */
	public void stampaAtleti();
	/**
	 * Get  an Array of String containing the path of the blue points as JPG
	 * @param punti -actual blue points
	 * @return the paths of the JPG files
	 */
	public String[] getScoreBlue(Integer punti);
	/**
	 * Get an Array of String containing the path of the red points as JPG
	 * @param punti
	 * @return the paths of the JPG
	 */
	public String[] getScoreRed(Integer punti);
	/**
	 * Get an Array of String containing the path of the red warnings as JPG
	 * @param warnings
	 * @return the paths of the warnings
	 */
	public String getWarningRed(Integer warnings);
	/**
	 * Get an Array of String containing the path of the blue warnings as JPG
	 * @param warnings
	 * @return the paths of the warnings
	 */
	public String getWarningBlue(Integer warnings);
	/**
	 * 
	 * @param file
	 */
	public void playSound(File file);
	/**
	 * 
	 */
	public void insertListaMatchFile();
	/**
	 * 
	 */
	public void getListaMatchFile();
	/**
	 * 
	 * @param atleta1
	 * @param atleta2
	 * @param risultato
	 */
	public void insertListaMatch(String atleta1, String atleta2, String risultato);
}

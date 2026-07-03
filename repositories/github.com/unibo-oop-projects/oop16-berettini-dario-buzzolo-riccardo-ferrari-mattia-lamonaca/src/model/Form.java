package model;

import java.util.ArrayList;
import java.util.List;

public interface Form {
	/**
	 * 
	 * @param punti
	 * @return the paths of the JPG file containing the image of the score
	 */
	public String[] getScoreRed(int punti);
	/**
	 * 
	 * @return the List<Athlete> that performed the taegeuk
	 */
	public List<Athlete> getListaAtletiForma();
	/**
	 * 
	 * @return an ArrayList<String> to print the athletes in the list
	 */
	public ArrayList<String> stampaAtleti();
	/**
	 * Adds the athlete in the listAthlete
	 * @param atleta
	 */
	public void addAtletiForma(Athlete atleta);
	/**
	 * Adds the list of the athletes in file .dat (data backup)
	 */
	public void insertListaAtletiFormaFile();
	/**
	 * 
	 * @return the list of athletes saved in the file .dat
	 */
	public ArrayList<Athlete> getListaAtletiFormaFile();
}

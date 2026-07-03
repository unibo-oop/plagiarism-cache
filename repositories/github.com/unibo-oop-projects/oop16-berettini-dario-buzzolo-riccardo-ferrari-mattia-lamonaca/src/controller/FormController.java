package controller;

import java.util.ArrayList;
import java.util.List;
import model.Athlete;
import model.Belt;
/**
 * 
 * @author Berettini, Buzzolo, Ferrari, Lamonaca
 *
 */
public interface FormController {
	/**
	 * Open the JFrame to start the form score-machine	 
	 * @param nome
	 * @param cognome
	 * @param cintura
	 * @param forma
	 */
	public void startForm(String nome, String cognome, Belt cintura, String forma);
	/**
	 * Open the JFrame to see the historical of the athletes who have executed a form
	 */
	public void stampaAtleti();
	/**
	 * Get  an Array of String containing the path of the red points as JPG
	 * @param punti - actual points
	 * @return the paths of the JPG files
	 */
	public String[] getScoreRed(int punti);
	/**
	 * Get the list of the athletes who did the taegeuk
	 * @return a List<Athete> 
	 */
	public List<Athlete> getListaAtletiForma();
	/**
	 * Add the list in the model a new athlete with a new score
	 * @param atleta new Athlete
	 */
	public void addAtleta(Athlete atleta);
	/**
	 * Add the entire list of the athletes in the file (data backup)
	 */
	public void addListaAtletiFile();
	/**
	 * Get all the athletes saved in the file 
	 * @return an arrayList from the file
	 */
	public ArrayList<Athlete> getListaAtletiFile();
}

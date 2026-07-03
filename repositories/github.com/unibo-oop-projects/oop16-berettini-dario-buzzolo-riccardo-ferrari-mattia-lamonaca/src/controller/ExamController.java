package controller;


import java.util.List;

import model.Athlete;
import model.Belt;
import model.Master;

/**
 * 
 * @author Berettini, Buzzolo, Ferrari, Lamonaca
 *
 */

public interface ExamController {

	/**
	 * Insert a new Master in a specific list
	 * @param name
	 * @param surname
	 * @param dan
	 */
	public void insertMaster(String name , String surname, Integer dan);
	/**
	 * Insert a new Athlete in a specific list
	 * @param name
	 * @param surname
	 * @param belt
	 */
	public void insertAthlete(String name, String surname, Belt belt);
	/**
	 * Open the JFrame where insert info for a new Master 
	 */
	public void insertMasterView();
	/**
	 * Open the JFrame where insert info for a new Athlete 
	 */
	public void insertAthleteView();
	/**
	 * Open the JFrame to start the exam
	 */
	public void startExamView();
	/**
	 * Open the
	 * @param atleta
	 */
	public void votesAthleteView(Athlete atleta);
	/**
	 * 
	 * @return the list of athletes that must take the exam
	 */
	public List<Athlete> getAthletes();
	/**
	 * 
	 * @return the list of master that are part of the commission
	 */
	public List<Master> getMasters();

	/**
	 * 
	 * @return as a String all the Masters 
	 */
	public String getMastersString();

	/**
	 *  Add the exam in the List<ExamDone>
	 */	
	//public void addExameDone();
	/**
	 * 
	 * @return all the exams done 
	 */
	//public ArrayList<ExamDoneImpl> getListaEsami();
	/**
	 * add all the exams in the Esami.dat file
	 */
	//public void addExamsFile();
	/**
	 * 
	 * @return all the exams saved in the Esami.dat file
	 */
	//public ArrayList<ExamDoneImpl> getEsamiFile();
	/**
	 * 
	 * @return a String with all the Atlhetes
	 */
	public String printAthletes(); 
	/**
	 * 
	 * @return a String with the date of the exam
	 */
	public String getDate();
	/**
	 * Open the respective View with the specific exercise for the given Belt
	 * @param prova
	 * @param valueBelt
	 * @return a String with the path of the specific exercise
	 */
	public String openGuidaTecnica(String prova, int valueBelt);
	/**
	 * 
	 * @return last Master added
	 */
	public Master getLastMaster();
	/**
	 * 
	 * @return last Athlete added
	 */
	public Athlete getLastAthlete();
	/**
	 * reload the table of the Athletes
	 */
	public void updateMaster();
	/**
	 * reload the table of the Masters
	 */
	public void updateAthlete();
	/**
	 * Delete the corresponding in the listMaster
	 * @param nMaster as index
	 * 
	 */
	public void deleteMaster(int nMaster);
	/**
	 * Delete the corresponding in the listAthlete
	 * @param nAthlete as index
	 */
	public void deleteAthlete(int nAthlete);
}

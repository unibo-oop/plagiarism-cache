package model;

import java.util.List;

public interface Exam {
	/**
	 * Adds a master in the commission
	 * @param m (Master)
	 */
	void addMaster(Master m);
	/**
	 * Adds an athlete who must take an exam

	 * @param a
	 */
	void addAthlete(Athlete a);
	/**
	 * 
	 * @return a list of the masters in the commission
	 */
	List<Master> getListMaster();
	/**
	 *  
	 * @return a list of athletes that take part in the exam
	 */
	List<Athlete> getListAthlete();
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
	 * 
	 * @param atleta
	 * @param index
	 * @return true if the athlete has complete the part(index) of the exam
	 */
	public int checkDone(Athlete atleta, Integer index);
	/**
	 * 
	 * @param atleta
	 * @return true if the athlete has completed and passed the exam
	 */
	public boolean checkPassed(Athlete atleta);
	
	/*public void addExamDone();
	
	public ArrayList<ExamDoneImpl> getListaEsami();
	
	public void insertEsameFile();
	
	public ArrayList<ExamDoneImpl> getEsamiFile();*/
	/**
	 * 
	 * @param prova
	 * @param valueBelt
	 * @return a String with the path of the file with the corresponding image of exercise
	 */
	public String openGuidaTecnica(String prova,int valueBelt);
	/**
	 * 
	 * @return a string format of the current day
	 */
	public String setDate();
	/**
	 * 
	 * @return 	
	 */
	public String getDate();
	/**
	 * Delete from the list the master at the given index
	 * @param nMaster
	 */
	public void deleteMaster(int nMaster);
	/**
	 * Delete from the list the athlete at the given index
	 * @param nAthlete
	 */
	public void deleteAthlete(int nAthlete);
}

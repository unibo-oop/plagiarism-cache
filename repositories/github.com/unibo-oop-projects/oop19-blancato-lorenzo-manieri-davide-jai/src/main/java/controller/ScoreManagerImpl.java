package controller;

import java.io.BufferedReader;



import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.PersonalScore;
import model.PersonalScoreImpl;


/**
 *
 */
public class ScoreManagerImpl implements ScoreManager {
	
	private static ScoreManagerImpl scoreManager = null;
	
	private PersonalScore currentScore;
	private ObservableList<PersonalScore> scoreList;
	
	/**
	 * 
	 * @return
	 * 		Return the singleton instance of Score Manager
	 */
	public synchronized static ScoreManagerImpl getInstance() {
		if(scoreManager == null) {
			scoreManager = new ScoreManagerImpl();
		}
		return scoreManager;
	}
	
	private ScoreManagerImpl() {
		this.currentScore = new PersonalScoreImpl();
		this.scoreList = FXCollections.observableArrayList();
	}
	
	/**
	 * 
	 */
	public PersonalScore getCurrentScore() {
		return this.currentScore;
	}
	
	/**
	 *
	 */
	public ObservableList<PersonalScore> getScoreList(){
		return this.scoreList;
	}
	
	
	/**
	 * 
	 */
	public void addPersonalScore(PersonalScore ps) {
		this.scoreList.add(ps);
	}
	
	/**
	 * Get score list form specified file
	 */
	public static void getListFromFile() {
		ScoreManagerImpl.getInstance().scoreList = FXCollections.observableArrayList();
		try {
			File file = new File("./Storico.txt");
			
			if (file.exists()) {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String tmp = reader.readLine();
				while(tmp != null) {
					if (PersonalScoreImpl.getPersonalScoreFromString(tmp) != null) {
						ScoreManagerImpl.getInstance().getScoreList().add(PersonalScoreImpl.getPersonalScoreFromString(tmp));
					}
					tmp = reader.readLine();
				}
				reader.close();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Sort the list and rewrite scores position in order
	 */
	public static void sortList() {
		List<PersonalScore> lista = ScoreManagerImpl.getInstance().getScoreList();
		Collections.sort(lista);
		Collections.reverse(lista);
		int i = 1;
		for (PersonalScore p : lista) {
			p.setPosition(i);
			i++;
		}
	}

	/**
	 * Write the score list in its specified file
	 */
	public static void writeListToFile() {
		File file = new File("./Storico.txt");
		PrintWriter writer;
		boolean check = false;
		try {
			if (file.exists()) {
				file.delete();
			}
			check = file.createNewFile();
			if (check) {
				
				writer = new PrintWriter(new FileWriter(file));
				for (PersonalScore p : ScoreManagerImpl.getInstance().getScoreList()) {
					writer.println(p.toString());
				}
				writer.close();		
				
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Set the score manager to null 
	 */
	public static void resetScoreManager() {
		ScoreManagerImpl.scoreManager = null;
	}
	

	
	
}

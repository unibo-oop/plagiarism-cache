package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The Class ScoreController manages the writing on file of the best scores
 */
public class ScoreController {

	/** The index score. */
	private final int INDEX_SCORE = 9;
	
	/** The Constant HOME. */
	private static final String HOME = System.getProperty("user.home");
	
	/** The Constant SEPARATOR. */
	private static final String SEPARATOR = File.separator;
	
	/** The Constant FILE. */
	private static final String FILE = HOME + SEPARATOR + "Score.txt";
	
	/** The highscore. */
	private final List<Integer> highscore = new ArrayList<Integer>();

	/**
	 * Read file.
	 */
	private void readFile() {
		BufferedReader bufferedReader = null;
		String s;
		highscore.clear();
		try {
			bufferedReader = new BufferedReader(new FileReader(new File(FILE)));
			s = bufferedReader.readLine();
			while(s != null) {
				highscore.add(Integer.parseInt(s));
				s = bufferedReader.readLine();
			}
			if(bufferedReader != null) {
				bufferedReader.close();
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Check score.
	 *
	 * @param score the score
	 */
	public void checkScore(final int score) {
		BufferedWriter bufferedWriter = null; 
		readFile();
		final File file = new File (FILE);
		boolean Highscoretrue = false;
		for(int i=0; i<highscore.size(); i++) {
			if(score >highscore.get(i)) {
				Highscoretrue = true;
				break;
			}
		}
		if(Highscoretrue) {
			highscore.add(INDEX_SCORE, score);
			Collections.sort(highscore);
			Collections.reverse(highscore);
//			for (final int i : highscore) {
//               System.out.println(i);
//             }
			if(!file.exists()) {
				try {
					file.createNewFile();
				}catch(IOException e) {
					System.out.println(e.getMessage());
				}
			}
			try {
				bufferedWriter = new BufferedWriter(new FileWriter(new File(FILE)));
				
				for(int i=0; i <= INDEX_SCORE; i++) {
					bufferedWriter.write(Integer.toString(highscore.get(i)));
					bufferedWriter.newLine();
				}
				bufferedWriter.close();
			}catch (IOException e) {
				System.out.printf("Errore di scrittura del file: %s>\n", e);
			}
		}
	}
	
	/**
	 * Gets the score.
	 *
	 * @return the score
	 */
	public List<String> getScore(){
		final List<String> getScore = new ArrayList<>();
		final File file = new File(FILE);
		try {
			if(file.createNewFile()) {
				initializeHighscore();
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		try {
			highscore.clear();
			readFile();
			for(final int i : highscore) {
				getScore.add(String.valueOf(i));
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return getScore;
		
	}

	/**
	 * Initialize highscore.
	 */
	private void initializeHighscore() {
		BufferedWriter bufferedWrite = null;
		try {
			bufferedWrite = new BufferedWriter(new FileWriter(new File(FILE)));
			for(int i = 0; i< 10; i++) {
				bufferedWrite.write(Integer.toString(0));
				bufferedWrite.newLine();
			}
			bufferedWrite.close();
		}catch (IOException e) {
			System.out.printf("ERROR writing score to file: %s\n", e);
			
		}
	}
}

package tetris.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This class create the "games.txt" file if it not exists, and update the file
 * with the score of current player or append new data.
 * 
 * @author Carlo
 *
 */
public class SaveRecord {

	/**
	 * class constructor
	 * 
	 * @param score
	 *            score of game
	 * @param name
	 *            name of player
	 * @throws IOException
	 */
	public SaveRecord(int score, String name) throws IOException {

		String path = "games.txt";
		String game;
		int min_pos = 0, min_value = 9999;
		try {
			File file = new File(path);

			/**
			 * Check if file doesn't exist, if it's true will create it
			 */
			if (file.exists()) {
				FileInputStream fstream = new FileInputStream("games.txt");
				BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
				/**
				 * declare players' arrays
				 */
				String[] players = new String[20];
				int[] scores = new int[20];

				int i = 0;
				int n;
				int saved_score;
				/**
				 * Read File Line By Line
				 */
				while ((game = br.readLine()) != null) {
					String[] record = game.split(",");
					saved_score = Integer.parseInt(record[1]);
					if (saved_score == 0) {
						/**
						 * end of file
						 */
					} else {
						players[i] = record[0];
						scores[i] = Integer.parseInt(record[1]);
						/**
						 * store the smaller score position
						 */
						if (scores[i] < min_value) {
							min_value = scores[i];
							min_pos = i;
						}
						i++;
					}
				}
				n = i;
				/**
				 * Close the input stream
				 */
				fstream.close();
				int exists = 0;
				i = 0;
				while ((i < n) && exists == 0) {
					if (players[i].equals(name)) {
						exists = 1;
						if (scores[i] < score) {
							scores[i] = score;
						}
					}
					i++;
				}
				if (exists == 0) {
					/**
					 * arrays will contain one more element
					 */
					if (n < 20) {
						players[i] = name;
						scores[i] = score;
						n = i + 1;
					} else if (score > min_value) { 
						/**
						 * store player's score if it's bigger than last one
						 */
						players[min_pos] = name;
						scores[min_pos] = score;
					}
				}
				/**
				 * after storing data in players and scores arrays, delete the old file version
				 */
				file.delete();

				/**
				 * now store data in games file
				 */
				try {
					File newTextFile = new File("games.txt");
					FileWriter fw = new FileWriter(newTextFile);
					for (i = 0; i < n; i++) {
						game = players[i] + "," + Integer.toString(scores[i]);
						game = game + "\n";
						fw.write(game);
					}
					fw.close();
				} catch (IOException iox) {
					iox.printStackTrace();
				}
			} else if (file.createNewFile()) {
				game = name + "," + Integer.toString(score) + "\n";
				File newTextFile = new File("games.txt");
				FileWriter fw = new FileWriter(newTextFile);
				fw.write(game);
				fw.close();
			} else
				System.out.println("Error during creation players' data");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

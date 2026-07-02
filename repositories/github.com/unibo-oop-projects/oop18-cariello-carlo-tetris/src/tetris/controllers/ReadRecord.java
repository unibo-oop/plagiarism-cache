package tetris.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import tetris.views.TetrisFrameImpl;

/**
 * This class checks if exists a record for the current player. If it's true, shows it.
 * @author Carlo
 * 
 */
public class ReadRecord {

	/**
	 * class constructor
	 */
	public ReadRecord() {    
	 	String path = "games.txt";
		String game;
		int find=0;		
		int record=0;
		String name=TetrisFrameImpl.name;
		try {
			File file = new File(path);

			/**
			 * Check if file doesn't exist, if it's true make it
			 */
			if (file.exists()) {
				FileInputStream fstream = new FileInputStream("games.txt");
				@SuppressWarnings("resource")
				BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
				@SuppressWarnings("unused")
				String[] players = new String[20];
				/**
				 * Read File Line By Line
				 */
				while ((game = br.readLine()) != null){
					String[] player_score = game.split(",");
							if(player_score[0].equals(name)) {
							record=Integer.parseInt(player_score[1]); 
							find=1;
						}			 
				}
			} 
		}
		catch (IOException iox) {
            iox.printStackTrace();
        }
		
		if(find==0){			
			record=0;
		}
		TetrisFrameImpl.lastScore=record;
	} 
}

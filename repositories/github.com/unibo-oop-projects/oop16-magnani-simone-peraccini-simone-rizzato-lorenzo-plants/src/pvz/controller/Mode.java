package pvz.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pvz.controller.data.Path;

/**
	 * This enumeration is created to  
	 *
	 */

public enum Mode {

	/**
	 * In this mode the player must overcome some levels, each of them will
	 * allow you to unlock new plants.
	 */
	HISTORY(5,Path.HISTORY_HIGHSCORES_FILE.getPath()),

	/**
	 * In this mode the player has battle through a series of hordes.The purpose
	 * of this level is to defeat more hordes possible.
	 */
	ARCADE(Integer.MAX_VALUE,Path.ARCADE_HISHSCORES_FILE.getPath());
	

	
	
	
	
	private int nLevels;
	private String  backUpsPath; 
	
	private Mode(final int nLevels,final String backUpsPath){
		this.nLevels = nLevels;
		this.backUpsPath = backUpsPath;
	}
	
	public int getTotLevels(){
		return this.nLevels;
	}
	
	public String getModePath(){
		return this.backUpsPath;
	}
	
	public static List<Mode> getAvailableModes(){
		return new ArrayList<Mode>(Arrays.asList(ARCADE));
	}
}

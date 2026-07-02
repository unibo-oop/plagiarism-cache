package model;

import java.io.File;

public class FileCreation {
	/**
	 * This class is used to create the hihgScore file in the Desktop direcotry of the user
	 */
	String homeDirectory=System.getProperty("user.home");
	String separator=System.getProperty("file.separator");
	String dir=homeDirectory+separator+"Desktop"+separator+"highScore.txt";
	
	public void createFile() {
		@SuppressWarnings("unused")
		File f= new File(dir);	
	}
	
}

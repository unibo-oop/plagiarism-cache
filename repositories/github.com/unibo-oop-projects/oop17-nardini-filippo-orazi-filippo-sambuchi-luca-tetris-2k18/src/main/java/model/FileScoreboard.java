package main.java.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileScoreboard {

	private  String path;
	private final String namePlayer;
	private List<String> list ;
	
	
	public FileScoreboard(String Path,String namePlayer) {
		this.path = path;
		this.namePlayer = namePlayer;
		list = new ArrayList<>(Arrays.asList(namePlayer , path));
	}
	
	public void writeFile() {
		File f = new File(path);
		try(PrintStream ps = new PrintStream(f)){
			this.list.forEach(ps::println);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.list.clear();
	}
}

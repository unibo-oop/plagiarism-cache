package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class UtilityReadWriteCatena {
	
	private static final String SEP = File.separator;
	private static final String USER_HOME = System.getProperty("user.home");
	private static final String DIR_NAME = "MyWarehouse";
	private static final String DIR = USER_HOME + SEP + DIR_NAME;
	private static final String FILE_NAME = "catena.bin";
	private static final String FILE = DIR + SEP + FILE_NAME;
	private static final File f = new File(FILE);
	private static final File dir = new File(DIR);
	
	public static Catena getCatena() throws FileNotFoundException, IOException, ClassNotFoundException {
		if (!f.exists()) {
			setCatena(new Catena());
		}
		ObjectInputStream i = new ObjectInputStream(new FileInputStream(f));
		Catena c = (Catena) i.readObject();
		i.close();
		return c;
	}
	public static void setCatena(Catena c) throws FileNotFoundException, IOException {
		if(!dir.exists()) {
			dir.mkdir();
		}
		ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(f));
		o.writeObject(c);
		o.close();
	}
	
}

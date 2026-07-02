package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;


public class UtilityBackupAndRestore {
	private static final String SEP = File.separator;
	private static final String USER_HOME = System.getProperty("user.home");
	private static final String DIR_NAME = "MyWarehouse";
	private static final String DIR_BCK_NAME = "Backup";
	private static final String DIR = USER_HOME + SEP + DIR_NAME + SEP + DIR_BCK_NAME;
	
	private static final File dir = new File(DIR);
	
	private static File f;
	
	public static void makeBackup(Catena c) throws FileNotFoundException, IOException {
		if(!dir.exists()) {
			dir.mkdir();
		}
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss");
		f = new File(DIR + SEP + dtf.format(LocalDateTime.now()).toString() + ".bin");
		ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(f));
		o.writeObject(c);
		o.close();
	}
	public static Catena restoreBackup() throws FileNotFoundException, IOException, ClassNotFoundException {
		SortedMap<Date, File> map = getAllFiles();
		f = map.get(map.lastKey());
		ObjectInputStream i = new ObjectInputStream(new FileInputStream(f));
		Catena c = (Catena) i.readObject();
		i.close();
		return c;
	}
	private static Date parseDate(String date) {
		try {
			return new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
	private static SortedMap<Date, File> getAllFiles() {
		SortedMap<Date, File> map = new TreeMap<>();
		Date tmp;
		File folder = new File(DIR);
		for(File f: folder.listFiles()) {
			tmp = parseDate(f.getName());
			if(tmp != null) {
				map.put(tmp, f);
			}
		}
		return map;
	}
}

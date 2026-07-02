package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is the class that is used to write and read a particular file.dat
 * this class contain 2 method,read and write.
 * 
 * @author Mattia.Rovinelli
 *
 * @param <T>
 * @param <X>
 */

public class StreamImpl<T, X> implements StreamModel<T, X> {

	@SuppressWarnings("unchecked")
	public Map<T, X> readFile(String path) {

		Map<T, X> map = new HashMap<T, X>();

		FileInputStream fis;
		try {
			fis = new FileInputStream(path);
			ObjectInputStream ois = new ObjectInputStream(fis);

			map = (Map<T, X>) ois.readObject();

			ois.close();
			fis.close();
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		} catch (ClassNotFoundException e) {

		}
		return map;
	}

	@Override
	public void writeFile(String path, Map<T, X> map) {
		try {
			FileOutputStream fos = new FileOutputStream(path);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(map);

			oos.close();
			fos.close();
		} catch (IOException i) {
			i.printStackTrace();
		}

	}
}

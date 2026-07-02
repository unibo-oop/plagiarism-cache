package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Utility class necessary to save and load on file every object that implements the interface 
 * {@link java.io.Serializable}.
 * 
 * @author Lorenzo Cottignoli
 *
 */
public final class SaveLoad {
	
	private SaveLoad() {
		super();
	}
	
	/**
	 * Method to save an object on a file.
	 * 
	 * @param fileName The system-dependent filename.
	 * @param obj Object to be saved on file.
	 * @throws IOException {@link ObjectOutputStream#writeObject(Object)}.
	 */
	public static void commandSave(final String fileName, final Object obj) throws IOException {
		final ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
		oos.writeObject(obj);
		oos.close();
	}
	
	/**
	 * Method to load an object on a file.
	 * 
	 * @param fileName The system-dependent filename.
	 * @return Object loaded from a file.
	 * @throws IOException construct method of {@link ObjectIntputStream}.
	 * @throws ClassNotFoundException {@link ObjectInputStream#readObject()}.
	 */
	public static Object commandLoad(final String fileName) throws IOException, ClassNotFoundException {
		final ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
		final Object o = ois.readObject();
		ois.close();
		return o;
	}
}

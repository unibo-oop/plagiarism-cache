package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Set;

public class FileManagerImpl<X> implements FileManager<X> {

	public Set<X> load(final String fileName) {
		try {
			final FileInputStream stream = new FileInputStream(fileName);
			final ObjectInputStream osStream = new ObjectInputStream(stream);

			@SuppressWarnings("unchecked")
			final Set<X> set =  (Set<X>) osStream.readObject();

			osStream.close();

			return set;

		} catch (IOException | ClassNotFoundException e) { }
		final Set<X> setEmpty = new HashSet<>();
		return setEmpty;
	}

	public void save(final String fileName, final Set<X> set) {
		try {
			final FileOutputStream stream = new FileOutputStream(fileName);
			final ObjectOutputStream osStream = new ObjectOutputStream(stream);
			osStream.writeObject(set);
			osStream.flush();
			osStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

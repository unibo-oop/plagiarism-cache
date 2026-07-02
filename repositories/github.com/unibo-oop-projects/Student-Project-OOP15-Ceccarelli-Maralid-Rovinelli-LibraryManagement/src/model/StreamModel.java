package model;

import java.io.IOException;
import java.util.Map;

public interface StreamModel<T, X> {
	/**
	 * method for read a file returns a map with numbered within the appropriate
	 * class It takes as input the destination file.dat
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws Exception
	 */
	public Map<T, X> readFile(String path);

	/**
	 * method to write to a file given the file and the class you want to write
	 * to the file
	 *
	 * @param path
	 * @param map
	 *            for the type
	 */
	public void writeFile(String path, Map<T, X> map);

}

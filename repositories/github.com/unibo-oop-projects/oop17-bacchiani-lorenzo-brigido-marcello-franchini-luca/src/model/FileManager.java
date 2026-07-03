package model;

import java.util.Set;

public interface FileManager<X> {

	/**
	 * 
	 * @param fileName
	 * @return a Set of X from a specific file.
	 */
	Set<X> load(final String fileName);
	/**
	 * Save all the element of set in a specific file.
	 * @param fileName
	 * @param set
	 */
	void save(final String fileName, final Set<X> set);




}

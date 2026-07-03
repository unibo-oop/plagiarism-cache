package model.core;

public interface StreamModel <K>  {
	
	/**
	 * this method is used to save an object on the given path
	 * @param path
	 * @param file
	 */
	
	public void saveFile(String path, K file);
	
	/**
	 * this method is used to read a file.
	 * @param path
	 * @return the object contained in the file
	 */
	
	public K readFile(String path);
	
}

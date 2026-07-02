package model.staticobj;

public interface IStaticObj {
	
	/**
	 * 
	 * @return coordinata x oggetto statico
	 */
	int getX();
	
	/**
	 * 
	 * @return coordinata y oggetto statico
	 */
	int getY();
	
	/**
	 * settaggio x coordinata oggetto
	 * @param x
	 */
	void setX(int x);
	
	/**
	 * settaggio y coordinata oggetto
	 * @param y
	 */
	void setY(int y);
	
	/**
	 * 
	 * @return Stringa contenente path immagine
	 */
	String getImgPath();
	
	/**
	 * settaggio stringa contente il path immagine
	 * @param imgPath
	 */
	void setImgPath(String imgPath);
	
	/**
	 * 
	 * @return larghezza oggetto statico
	 */
	int getWidth();
	
	/**
	 * 
	 * @return altezza oggetto statico
	 */
	int getHeight();

}

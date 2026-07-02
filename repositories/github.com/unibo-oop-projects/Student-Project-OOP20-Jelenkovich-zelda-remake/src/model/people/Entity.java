package model.people;

public interface Entity {
	/**
	 * 
	 * @return coordinata y dell'entità
	 */
	int getY();
	
	/**
	 * 
	 * @return coordinata x dell'entità
	 */
	int getX();
	
	/**
	 * settaggio coordinata x dell'entità
	 * @param x
	 */
	void setX(int x);
	
	/**
	 * settaggio coordinata y dell'entità
	 * @param y
	 */
	void setY(int y);
	
	/**
	 * 
	 * @return Stringa con path immagine
	 */
	String getImgPath();
	
}

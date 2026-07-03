package model.Interfaces;

/**
 * interfaccia di una cella
 * @author Utente
 *
 */
public interface Cell {

	/**
	 * metodo che ritorna l'id i una cella
	 * @return
	 */
	public int getId();
	
	/**
	 * metodo che setta l'id di una cella
	 * @param id id cella
	 */
	public void setId(int id);
	
	/**
	 * metodo che ritorna la posizione di una cella
	 * @return posizione cella
	 */
	public String getPosition();
	
	/**
	 * metodo che setta la posizione della cella
	 * @param position posizione cella
	 */
	public void setPosition(String position);
	
	/**
	 * metodo che ritorna la capacità di una cella
	 * @return capacità massima di una cella
	 */
	public int getCapacity();
	
	/**
	 * metodo che setta la capacità di una cella
	 * @param capacity capacità cella
	 */
	public void setCapacity(int capacity);
	
	/**
	 * metodo che ritorna i prigionieri correnti di una cella
	 * @return prigionieri di una cella
	 */
	public int getCurrentPrisoners();

	/**
	 * metodo che setta i prigionieri di una cella
	 * @param currentPrisoners numero prigionieri di una cella
	 */
	public void setCurrentPrisoners(int currentPrisoners);
}

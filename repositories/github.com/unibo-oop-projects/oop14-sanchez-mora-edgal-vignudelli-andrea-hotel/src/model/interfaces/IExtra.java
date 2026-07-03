package model.interfaces;

public interface IExtra {

	/**
	 * Metodo che ritorna il costo relativo a questo extra.
	 * 
	 * @return
	 */

	public Double getCost();

	/**
	 * Metodo che ritorna il nome di questo specifico extra.
	 * 
	 * @return
	 */

	public String getName();

	/**
	 * Metodo per settare il costo di questo extra.
	 * 
	 * @param cost
	 *            il valore dell'extra
	 */

	public void setCost(final double cost);

	/**
	 * Metodo per settare il nome di questo extra.
	 * 
	 * @param cost
	 *            il nome dell'extra
	 */

	public void setName(final String name);

	public String toString();

}

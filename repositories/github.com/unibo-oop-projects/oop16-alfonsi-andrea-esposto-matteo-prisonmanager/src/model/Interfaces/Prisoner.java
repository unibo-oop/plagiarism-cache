package model.Interfaces;

import java.util.Date;
import java.util.List;

/**
 * interfaccia che rappresenta un prigioniero
 */
public interface Prisoner extends Person{

	/**
	 * Metodo che aggiunge un crimine alla lista dei crimini
	 * @return
	*/
	public void addCrime(String crimine);

	/**
	 * Metodo che ritrna la lista dei crimini commessi da un criminale
	 * @return la lista dei crimini
	*/
	public List<String> getCrimini();
	
	/**
	 * Metodo che ritorna l'id del prigioniero
	 * @return id del prigioniero
	*/
	public int getIdPrigioniero();

	/**
	 * Metodo che setta l'id del prigioniero
	 * @return
	*/
	public void setIdPrigioniero(int idPrigioniero);

	/**
	 * Metodo che ritorna la data di inizio della reclusione
	 * @return data di inizio della reclusione
	*/
	public Date getInizio();

	/**
	 * Metodo che setta la data di inizio della reclusione
	 * @return
	*/
	public void setInizio(Date inizio);

	/**
	 * Metodo che ritorna la data di fine della reclusione
	 * @return data di fine della reclusione
	*/
	public Date getFine();

	/**
	 * Metodo che setta la data di fine della reclusione
	 * @return
	*/
	public void setFine(Date fine);
	
	/**
	 * Metodo che ritorna l'id della cella 
	 * @return id della cella
	 */
	public int getCellID();
	
	/**
	 * metodo che setta l'id della cella
	 * @param cellID id della cella
	 */
	public void setCellID(int cellID);
}
package model.Implementations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Interfaces.Prisoner;

/**
 * implementazione di un prigioniero
 */
public class PrisonerImpl extends PersonImpl implements Prisoner{

	private static final long serialVersionUID = -3204660779285410481L;
	
	private int idPrigioniero;
	
	private Date inizio;
	
	private Date fine;
	
	private List <String> crimini = new ArrayList<>();
	
	private int idCell;

	/**
	 * Instantiates a new prisoner.
	 *
	 * @param name the name
	 * @param surname the surname
	 * @param birthDate the birth date
	 * @param idPrigioniero the id
	 * @param inizio inizio della reclusione
	 * @param fine fine della reclusione
	 */
	public PrisonerImpl(String name, String surname, Date birthDate, int idPrigioniero, Date inizio, Date fine,List<String>list, int idCell) {
		super(name, surname, birthDate);
		this.idPrigioniero=idPrigioniero;
		this.inizio=inizio;
		this.fine=fine;
		this.crimini=list;
		this.idCell=idCell;
	}

	@Override
	public void addCrime(String crimine) {
		crimini.add(crimine);
	}

	@Override
	public List<String> getCrimini(){
		return this.crimini;
	}

	public int getIdPrigioniero() {
		return this.idPrigioniero;
	}

	public void setIdPrigioniero(int idPrigioniero) {
		this.idPrigioniero = idPrigioniero;
	}

	public Date getInizio() {
		return this.inizio;
	}

	public void setInizio(Date inizio) {
		this.inizio = inizio;
	}

	public Date getFine() {
		return fine;
	}

	public void setFine(Date fine) {
		this.fine = fine;
	}

	public int getCellID() {
		return idCell;
	}

	public void setCellID(int cellID) {
		this.idCell = cellID;
	}

	@Override
	public String toString() {
		return "PrisonerImpl [idPrigioniero=" + idPrigioniero + ", inizio=" + inizio + ", fine=" + fine + ", crimini="
				+ crimini + "]";
	}

}

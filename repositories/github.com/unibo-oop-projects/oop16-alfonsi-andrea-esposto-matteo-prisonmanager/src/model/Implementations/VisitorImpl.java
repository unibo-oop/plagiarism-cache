package model.Implementations;

import java.util.Date;

import model.Interfaces.Visitor;

/**
 * Implementazione di un visitatore
 */
public class VisitorImpl extends PersonImpl implements Visitor{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5306827736761721189L;

	/**
	 * id del prigioniero
	 */
	private int prisonerID;
	
	/**
	 * 
	 * @param name nome
	 * @param surname cognome
	 * @param birthDate data di nascita
	 * @param prisonerID id del prigioniero andato a trovare
	 */
	public VisitorImpl(String name, String surname, Date birthDate, int prisonerID) {
		super(name, surname, birthDate);
		this.prisonerID=prisonerID;
	}

	@Override
	public int getPrisonerID() {
		return this.prisonerID;
	}

	@Override
	public void setIdPrisoner(int prisonerID) {
		this.prisonerID=prisonerID;
	}
	
	@Override
	public String toString() {
		return "VisitorImpl getName()=" + getName() + ", getSurname()="+ getSurname() + ", getBirthDate()=" + getBirthDate() + "[getIdPrisoner()=" + getPrisonerID() + "]";
	}


	

}

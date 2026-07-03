package model.Implementations;

import java.util.Date;

import model.Interfaces.Guard;

/**
 * implementazione di una guardia
 */
public class GuardImpl extends PersonImpl implements Guard{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3975704240795357459L;

	private int rank;
	private String telephoneNumber;
	private int idGuardia;
	private String password;
	
	/**
	 * costruttore di una guardia
	 * @param name nome
	 * @param surname cognome
	 * @param birthDate data di nascita
	 * @param rank grado
	 * @param telephoneNumber numero di telefono
	 * @param idGuardia id guardia
	 * @param password password
	 */
	public GuardImpl(String name, String surname, Date birthDate, int rank, String telephoneNumber, int idGuardia,
			String password) {
		super(name, surname, birthDate);
		this.setRank(rank);
		this.setTelephoneNumber(telephoneNumber);
		this.idGuardia = idGuardia;
		this.password = password;
	}

	@Override
	public void setPassword(String newPass) {
		this.password=newPass;
		
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public int getUsername() {
		return this.idGuardia;
	}

	@Override
	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	@Override
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	@Override
	public int getRank() {
		return rank;
	}
	
	@Override
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	@Override
	public int getID() {
		return idGuardia;
	}
	
	@Override
	public void setID(int idGuardia) {
		this.idGuardia = idGuardia;
	}


	@Override
	public String toString() {
		return "GuardImpl [rank=" + rank + ", telephoneNumber=" + telephoneNumber + ", idGuardia=" + idGuardia
				+ "]";
	}

}

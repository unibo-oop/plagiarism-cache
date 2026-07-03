package model.Interfaces;

/**
 * questa interfaccia rappresenta un visitatore
 */
public interface Visitor extends Person{

	/**
	 * metodo che ritorna l'id del prigioniero che il visitatore è andato a trovare
	 * @return id del prigioniero visitato
	 */
	public int getPrisonerID();
	
	/**
	 * metodo che imposta l'id del prigioniero
	 * @param id id del prigioniero
	 */
	public void setIdPrisoner(int prisonerID);
}

package model.Interfaces;


/**
 * Interfaccia navi
 * @author Helena Zaccarelli
 */

public interface Nave {

	/**
	 * Metodo che ritorna il codice univoco di una nave
	 * @return
	 */
	public int getCod();
	
	/**
	 * Metodo che setta il codice univoco di una nave
	 * @param codice
	 */
	public void setCod(int codice);
	
	/**
	 * Metodo che ritorna il nome nave
	 * @return
	 */
	public String getNome();
	
	/**
	 * Metodo che setta il nome nave
	 * @param nome 
	 */
	public void setNome(String nome);
	
	/**
	 * Metodo che ritorna la portata di una nave (valore in tonnellate)
	 * @return
	 */
	public int getPortata();
	
	/**
	 * Metodo che ritorna gli spazi presenti sulla nave per stivare le merci 
	 * @return
	 */
	public int getSpazi();
	
	/**
	 * Metodo che ritorna la bandiera della nave
	 * @return
	 */
	public String getBandiera();
	
	/**
	 * Metodo che setta la bandiera della nave
	 * @param bandiera 
	 */
	public void setBandiera(String bandiera);
	
	/**
	 * Metodo che ritorna il tipo di carico previsto dalla nave (containers, rotabili, etc.)
	 * @return
	 */
	public String getTipo();
	
	/**
	 * Metodo che setta il tipo di carico previsto dalla neve
	 * @param tipo 
	 */
	public void setTipo(String tipo);
}

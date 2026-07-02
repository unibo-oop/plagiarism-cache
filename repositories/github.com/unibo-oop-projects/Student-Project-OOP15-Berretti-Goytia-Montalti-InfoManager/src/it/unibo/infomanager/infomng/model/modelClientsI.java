package it.unibo.infomanager.infomng.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import it.unibo.infomanager.infomng.controller.DataBaseSearch;
/***
 * Interfaccia per gestire i clienti
 * @author Juan Goytia
 *
 */
public interface modelClientsI {
	/***
	 * setta il nome del cliente
	 * @param name
	 * string con il nome del cliente
	 */
	void setName(String name);
	/***
	 * setta il cognome del cliente
	 * @param cognome
	 * string con il cognome del cliente
	 */
	void setLastName(String cognome);
	/***
	 * setta la mail del cliente
	 * @param mail
	 * string con l'email del cliente
	 */
	void setMail(String mail);
	/***
	 * setta il numero del telefono
	 * @param telefono
	 * string con il numero del telefono
	 */
	void setPhone(String telefono);
	/***
	 * setta il nome del negozio
	 * @param nomeNegozio
	 * string con il nome del negozio
	 */
	void setShopName(String nomeNegozio);
	
	/***
	 * ottiene ID del record corrente
	 * @return
	 * un integer che rapparesenta il record
	 */
	Integer getID();
	
	/***
	 * ottiene il nome del cliente 
	 * @return
	 * una string con il nome del cliente
	 */
	String getName();
	
	/***
	 * ottiene il cognome del cliente
	 * @return
	 * una string con il cognome del cliente
	 */
	String getLastName();
	
	/***
	 * ottiene la mail del cliente
	 * @return
	 * una string con la mail del cliente
	 */
	String getMail();
	
	/***
	 * Ottiene il telefono del cliente
	 * @return
	 * una string con il telefono del cliente
	 */
	String getPhone();
	
	/***
	 * ottiene il nome del negozio a cui appartiene il cliente
	 * @return
	 * una stringa contenente il nome del negozio
	 */
	String getShopName();
	/***
	 * realizza l'update(salvataggio o modifica) di un record
	 * @return
	 * true se e andato a buon fine, altrimenti false
	 */
	boolean update();
	
	/***
	 * eliminazione del cliente corrente
	 * @return
	 * true se va a buon fine
	 */
	boolean deleteClient();

	/***
	 * ricerca di un/i clienti tramite uno o piu parametri
	 * passare come stringa vuota se non si vuole usare come parametro di ricerca
	 * @param nome
	 * nome del cliente
	 * @param cognome
	 * cognome del cliente
	 * @param mail
	 * mail del cliente
	 * @param telefono
	 * telefono del cliente
	 * @param nomeNegozio
	 * nome del negoizio a cui appartiene il cliente
	 * @return
	 * tutti i clienti trovati tramite i parametri forniti, si segue una lgica or! oppure una lista vuota
	 */
	public static List<modelClientsI> searchClients(String nome, String cognome, String mail, String telefono, String nomeNegozio){
		if(modelUsersI.isLogged()){
			return modelClientsI.clientsList().stream()
					.filter(cliente -> cliente.getName().contains(nome) || cliente.getLastName().contains(cognome)
					    || cliente.getMail().contains(mail) || cliente.getShopName().contains(nomeNegozio)
					    || cliente.getPhone().contains(telefono))
					.collect(Collectors.toList());
		}
		else
			return new ArrayList<modelClientsI>();
	}
	
	/***
	 * elenco di tutti i clienti esisitenti
	 * @return
	 * una lista contenente tutti i clienti
	 */
	public static List<modelClientsI> clientsList(){
		if(modelUsersI.isLogged()){
			DataBaseSearch query = DataBaseSearch.queryDaTabella("Clienti");
			try {
				return query.find().stream()
						.map(e -> new modelClients(e))
						.collect(Collectors.toList());
			} catch (SQLException e) {
				return new ArrayList<modelClientsI>();
			}
		}
		else
			return new ArrayList<modelClientsI>();
	}
	/***
	 * creazione di un nuovo cliente
	 * @param nome
	 * nome del cliente
	 * @param cognome
	 * cognome del cliente
	 * @param mail
	 * mail del cliente
	 * @param telefono
	 * teleono del cliente
	 * @param nomeNegozio
	 * nome del negozio cliente
	 * @return
	 * true se e andato a buon fine altrimenti false
	 */
	public static boolean builder(String nome, String cognome, String mail, String telefono, String nomeNegozio){
		if(modelUsersI.isLogged()){
			modelClientsI temp = new modelClients();
			temp.setName(nome);
			temp.setLastName(cognome);
			temp.setMail(mail);
			temp.setPhone(telefono);
			temp.setShopName(nomeNegozio);
			return temp.update();
		}else
			return false;
	}
	
}
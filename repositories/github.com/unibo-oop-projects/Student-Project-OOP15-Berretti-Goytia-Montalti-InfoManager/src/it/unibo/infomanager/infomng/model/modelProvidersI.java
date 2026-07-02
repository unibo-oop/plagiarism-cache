package it.unibo.infomanager.infomng.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import it.unibo.infomanager.infomng.controller.DataBaseSearch;
/***
 * interfaccia per gestire i fornitori
 * @author Juan Goytia
 *
 */
public interface modelProvidersI {
	/***
	 * setta il nome del fornitore
	 * @param name
	 * string con il nome del cliente
	 */
	void setName(String name);
	/***
	 * setta il cognome del fornitore
	 * @param cognome
	 * string con il congnome del fornitore
	 */
	void setLastName(String cognome);
	/***
	 * setta la mail del fornitore
	 * @param mail
	 * string con la mail del fornitore
	 */
	void setMail(String mail);
	/***
	 * setta il telefono del fornitore
	 * @param telefono
	 * string con il telefono del fornitore
	 */
	void setPhone(String telefono);
	
	/***
	 * ottiene ID del record corrente
	 * @return
	 * un integer contenente l'ID
	 */
	Integer getID();
	
	/***
	 * ottiene l'id del fornitore sottoforma di stringa
	 * @return
	 * una stringa contenente l'ID del fornitore
	 */
	String getIDFornitore();
	/***
	 * ottiene il nome del fornitore
	 * @return
	 * una string che contiene il nome
	 */
	String getName();
	/***
	 * ottiene il cognome de fornitore
	 * @return
	 * una stringa contenente il cognome
	 */
	String getLastName();
	/***
	 * ottiene la mail del fornitore
	 * @return
	 * una stringa contenente la mail
	 */
	String getMail();
	/***
	 * ottiene il recapito telefonico del fornitore
	 * @return
	 * una stringa contenente il recapito telfonico
	 */
	String getPhone();	
	/***
	 * eliminazione del fornitore corrente
	 * @return
	 * true se e andato a buon fine
	 */
	boolean deleteProvider();
	/***
	 * realizza l'update(salvataggio o modifica) di un record
	 * @return
	 * true se e andato a buon fine, altrimenti false
	 */
	boolean update();
	
	
	/***
	 * ricerca di fornitore tramite uno o piu paramentri
	 * si chiede di passare come "" i parametri da non considerare
	 * @param nome
	 * nome del fornitre
	 * @param cognome
	 * cognome del fornitore
	 * @param mail
	 * mail del fornitore
	 * @param telefono
	 * recapito telefonico del fornitore
	 * @return
	 * una lista contenente tutti i fornitori, tramite i parametri forniti o una lista vuota
	 */
	public static List<modelProvidersI> searchProviders(String nome, String cognome, String mail, String telefono){
		if(modelUsersI.isLogged()){
		 return modelProvidersI.providersList().stream()
					.filter(f -> f.getName().contains(nome) || f.getLastName().contains(cognome)
								|| f.getMail().contains(mail) || f.getPhone().contains(telefono))
					.collect(Collectors.toList());
		}
		else
			return new ArrayList<modelProvidersI>();
	}
	
	/***
	 * cerca dei fornitori attraverso i prodotti presente nel magazzino
	 * @param nomeProdotto
	 * noeme del prodotto
	 * @return
	 * una lista con tutti i fornitori che forniscono il prodotto specificato altrimenti una lista vuota
	 */
	public static List<modelProvidersI> searchProvidersByProduct(String nomeProdotto){
		if(modelUsersI.isLogged()){
			List<Integer> listTemp = modelStoreI.productsList().stream()
					.filter(e-> e.getName().contains(nomeProdotto))
					.map(e -> e.getProvider().getID())
					.collect(Collectors.toList());
			if(!listTemp.isEmpty()){
			return modelProvidersI.providersList().stream()
					.filter(e ->{
						for(Integer a : listTemp){
							if(e.getID().equals(a))
								return true;
						}return false;	
					}).collect(Collectors.toList());
			}
			else
				return new ArrayList<modelProvidersI>();
		}
		else
			return new ArrayList<modelProvidersI>();
	}
	
	/***
	 * ottiene un elenco di tutti i fornitori
	 * @return
	 * una lista contenente tutti i fornitori
	 */
	public static List<modelProvidersI> providersList(){
		if(modelUsersI.isLogged()){
			DataBaseSearch query = DataBaseSearch.queryDaTabella("Fornitori");
			try {
				return query.find().stream()
						.map(e -> new modelProviders(e))
						.collect(Collectors.toList());
			} catch (SQLException e) {
				return new ArrayList<modelProvidersI>();
			}
		}
		else
			return new ArrayList<modelProvidersI>();
	}
	/***
	 * creazione di un nuovo cliente
	 * @param nome
	 * nome del fornitore
	 * @param cognome
	 * cognome de forntore
	 * @param mail
	 * email del fornitore
	 * @param telefono
	 * telefono del fornitore
	 * @return
	 * true se e andato a buon fine altrimenti false
	 */
	public static boolean builder(String nome, String cognome, String mail, String telefono){
		if(modelUsersI.isLogged()){
			modelProvidersI temp = new modelProviders();
			temp.setName(nome);
			temp.setLastName(cognome);
			temp.setPhone(telefono);
			temp.setMail(mail);
			return temp.update();
		}
		else
			return false;
	}
}
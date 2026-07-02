package it.unibo.infomanager.infomng.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import it.unibo.infomanager.infomng.controller.DataBaseSearch;
/***
 * interfaccia per la gestione del magazzino
 * @author Juan
 *	
 */
public interface modelStoreI {
	/***
	 * ottengo l'id di questo record
	 * @return
	 */
	Integer getID();
	/**
	 * ottiene il nome del prodotto
	 * @return
	 */
	String getName();

	/***
	 * metodo che calola la quantita dell'oggetto
	 * @return
	 * quantita del prodotto nel magazzzino
	 */
	int getQuantity();
	/***
	 * ottiene il provider di questo prodotto
	 * @return
	 * il fornitore altrimenti null
	 */
	modelProvidersI getProvider();
	/***
	 * ottiene i dettagli di questo prodotto
	 * @return
	 * un string on i dettagli del prodotto
	 */
	String getProductDetails();
	/***
	 * metodo per l'eliminazione di questo prodotto nel magazzino
	 * @return
	 * true se andato a buon fine altrimenti false
	 */
	boolean deleteProduct();
	/***
	 * settaggio del fornitore di questo prodotto
	 * @param provider
	 * il fornitore del prodotto
	 */
	void setProvider(modelProvidersI provider);
	/***
	 * settaggio del nome del prodotto
	 * @param nome
	 */
	void setName(String nome);
	/***
	 * metodo per l'aggiornamento(salvataggio o modifica) del prodotto
	 * @return
	 * true se andato a buon fine altrimenti false
	 */
	boolean update();
	/***
	 * settaggio dei dettagli del prodotto
	 * @param details
	 * string con i dettagli
	 */
	void setProductDeatils(String details);
	/***
	 * ricerca di prodotti di un determinato fornitore
	 * @param IDFornitore
	 * @return
	 * i prodotti trovati ltrimenti una lista vuota
	 */
	public static List<modelStoreI> serachProductsByProvider(modelProvidersI fornitore){
		if(modelUsersI.isLogged()){
		return modelStoreI.productsList().stream()
				.filter(e-> {
					try{
						return e.getProvider().getID().equals(fornitore.getID());
					}catch (Exception ex){
						return false;
					}
				})
				.collect(Collectors.toList());
		}
		else
			return new ArrayList<modelStoreI>();
	}
	
	/***
	 * ricerca dei prodotti con una quantita minima
	 * @param quantitaMinima
	 * @return
	 * una lista che contiene i prodotti trovati altrimenti una lista vuota
	 */
	public static List<modelStoreI> searchProductsByQuantity(int quantitaMinima){
		if(modelUsersI.isLogged()){
			return modelStoreI.productsList().stream()
					.filter(e-> e.getQuantity() >= quantitaMinima)
					.collect(Collectors.toList());
		}
		else
			return new ArrayList<modelStoreI>();
	}
	
	/***
	 * cerca un prodotto tramine il nome
	 * @param nome
	 * nome del prodotto
	 * @return
	 * il/i prodotti trovati altrimenti una lista vuota
	 */
	public static List<modelStoreI> serachProductsByName(String nome){
		if(modelUsersI.isLogged()){
			return modelStoreI.productsList().stream()
					.filter(e-> e.getName().contains(nome))
					.collect(Collectors.toList());
		}
		else
			return new ArrayList<modelStoreI>();
	}

	/***
	 * elenco di tutti i prodotti presenti nel magazzino
	 * @return
	 * una lista con tutti i prodotti
	 */
	public static List<modelStoreI> productsList(){
		if(modelUsersI.isLogged()){
			DataBaseSearch query = DataBaseSearch.queryDaTabella("Magazzino");
			try {
				return query.find().stream()
						.map(e -> new modelStore(e))
						.collect(Collectors.toList());
			} catch (SQLException e) {
				return new ArrayList<modelStoreI>();
			}
		}
		else
			return new ArrayList<modelStoreI>();
	}
	
	/***
	 * creazione di un nuovo prodotto
	 * @param nome
	 * nome del prodotto
	 * @param fornitore
	 * ID del fornitore del quale si � aquisito il prodotto
	 * @param descrizione
	 * descrizione del prodotto
	 * @return
	 */
	public static boolean builder(String nome, modelProvidersI fornitore, String descrizione) {
		if(modelUsersI.isLogged()){
			modelStoreI temp = new modelStore();
			temp.setName(nome);
			temp.setProductDeatils(descrizione);
			temp.setProvider(fornitore);
			return temp.update();
		}
		else
			return false;
	}
}
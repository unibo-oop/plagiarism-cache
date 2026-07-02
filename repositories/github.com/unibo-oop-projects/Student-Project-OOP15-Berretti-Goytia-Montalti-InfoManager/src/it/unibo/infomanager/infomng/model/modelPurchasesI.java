package it.unibo.infomanager.infomng.model;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import it.unibo.infomanager.infomng.controller.DataBaseSearch;
/***
 * interfaccia per gestire gli acquisti 
 * @author Juan
 *
 */
public interface modelPurchasesI {
	/***
	 * ottiene l'id del record corrente
	 * @return
	 * un integer che contiene l'id
	 */
	Integer getID();
	/***
	 * salva la data dell'acquisto
	 * @param data
	 * data di tipo Object sql.date
	 */
    void setDate(Date data);
	/***
	 * settaggio dello sconto per questo acquisto
	 * @param sconto
	 * lo sconto passato come float
	 */
	void setDiscount(float sconto);
	/***
	 * settaggio del provider di questo acquisto
	 * @param IDProvuder
	 * ID del provider passato come Integer
	 */
	void setProvider(modelProvidersI IDProvider);
	/***
	 * settaggio del numero della recivuta per questo acquisto(lo stesso usato nello scontrino di questo acquisto)
	 * @param nRicevuta
	 * numero della recivuto passato come integer
	 */
	void setNumberPaymentReceipt(int nRicevuta);
	/***
	 * settaggio dell'iva per questo acquisto
	 * @param iva
	 * iva passato come float
	 */
	void setIVA(float iva);
	
	/***
	 * ottiene il numero della ricevuta dell'acquisto( lo stesso usato nello scontrino per questo acquisto)
	 * @return
	 * un int con il numero della ricevuta
	 */
	int getNumberPaymentReceipt();
	/***
	 * ottiene il fornitore di questo acquisto
	 * @return
	 * il fornitore
	 */
	modelProvidersI getProvider();
	/***
	 * ottiene l'iva per questo acquisto
	 * @return
	 * l'iva
	 */
	float getIva();
	/***
	 * ottiene la data dell'acquisto
	 * @return
	 * una object sql.date che contiene la data dell'acquisto
	 */
	Date getDate();
	/***
	 * ottiene lo sconto per l'acquisto
	 * @return
	 * un float con lo sconto dell'acquisto
	 */
	float getDiscount();
	/***
	 * ottiene una lista con tutti i prodotti acquistati
	 * @return
	 * una lista con i prodotti acquistati altrimenti una lista vuota
	 */
	List<transactionsProducts> purchasedProducts();
	/***
	 * ottiene il totale speso per questo acquisto
	 * @return
	 * un double con il totale speso
	 */
	Double getTotalSpent();
	
	/***
	 * metodo che salva ogni modifica/creazione di un acquisto. 
	 * @return
	 * true se e andato a buon fine
	 */
	boolean update();
	
	/***
	 * eliminazione dell'accquisto corrente
	 * @return
	 * true se e andato a buon fine
	 */
	boolean deletePurchase();

	/***
	 * elenco di tutti gli acquisti realizzati
	 * @return
	 * una lista contenente tutti gli acquisti fatti
	 */
	public static List<modelPurchasesI> purchasesList(){
		if(modelUsersI.isLogged()){
			DataBaseSearch query = DataBaseSearch.queryDaTabella("Acquisti");
			try {
				return query.find().stream()
						.map(e -> new modelPurchases(e))
						.collect(Collectors.toList());
			} catch (SQLException e) {
				return new ArrayList<modelPurchasesI>();
			}
		}
		else
			return new ArrayList<modelPurchasesI>();
	}
	/***
	 * ricerca di una ricevuta di acquisto tramite il numero
	 * @param nRicevuta
	 * numero della ricevuta passato come int
	 * @return
	 * l'acquisto realizzato altrimenti null
	 */
	public static modelPurchasesI searchPurchase(int nRicevuta){
		if(modelUsersI.isLogged()){
			return modelPurchasesI.purchasesList().stream()
				.filter(p -> p.getNumberPaymentReceipt() == nRicevuta)
				.findFirst()
				.orElse(null);
		}
		else
			return null;
	}
	/***
	 * ottiene il report degli acquisti
	 * @return
	 * una lista contenente tutti gli acquisti ordinati in maniera decrescente in base alle spese altrimenti una lista vuota
	 */
	public static List<modelPurchasesI> reportPurchases(){
		if(modelUsersI.isLogged()){
			Comparator<modelPurchasesI> sort = (primo, secondo) -> Double.compare(primo.getTotalSpent(), secondo.getTotalSpent());
			
			return modelPurchasesI.purchasesList().stream()
					.sorted(sort)
					.collect(Collectors.toList());
		}else
			return new ArrayList<modelPurchasesI>();
	}
	/***
	 * creazione di un acquisto
	 * @param data
	 * data dell'acquisto
	 * @param sconto
	 * socnto dell'acquisto
	 * @param iva
	 * iva dell'acquisto
	 * @param nRicevuta
	 * numero della ricevuta
	 * @param fornitore
	 * fornitore dell'acquisto
	 * @param prodotti
	 * prodotti dell'acquisto
	 * @return
	 * true se creato altrimenti false
	 */
	public static boolean builder(Date data, float sconto, float iva, int nRicevuta, modelProvidersI fornitore,  List<transactionsProductsI> prodotti){
		if(modelUsersI.isLogged())
		{
			modelPurchasesI temp = new modelPurchases();
			temp.setProvider(fornitore);
			temp.setDate(data);
			temp.setDiscount(sconto);
			temp.setIVA(iva);
			//salvataggio dei prodotti acquistati
			if(modelTransactionsI.transactionsProducts(nRicevuta, prodotti, false))
				return temp.update();
			else
				return false;
		}
		else 
			return false;
	}
	
}
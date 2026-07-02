package it.unibo.infomanager.infomng.model;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import it.unibo.infomanager.infomng.controller.DataBaseSearch;
/***
 * interfaccia per la gestione della vendita
 * @author Juan GOytia
 *
 */
public interface modelSalesI {
	/***
	 * ottiene l'ID del record
	 * @return
	 * un integer con l'id 
	 */
	Integer getID();
	/***
	 * ottiene il numero della ricevuta di 	quella vendita( lo stesso dello scontrino per questa vendita)
	 * @return
	 * un in con il n di recivuta
	 */
	int getNumberPaymentReceipt();
	/***
	 * ottiene il cliente a chi è stato effettuato la vendita
	 * @return
	 * il cliente altrimenti null
	 */
	modelClientsI getClient();
	/***
	 * ottiene l'iva della vendita
	 * @return
	 * un float con l'iva
	 */
	float getIva();
	/***
	 * ottiene la data della vendita
	 * @return
	 * un object sql.date con la data della vendita
	 */
	Date getDate();
	/***
	 * ottiene lo sconto della vendita
	 * @return
	 * un float con lo sconto
	 */
	float getDiscount();
	/***
	 * setto la data della vendita
	 * @param data
	 * data della vendita come object sql.Date
	 */
	void setDate(Date data);
	/***
	 * setto lo scono per questa vendita
	 * @param sconto
	 * sconto della vendita
	 */
	void setDiscount(float sconto);
	/***
	 * setta il cliente della vendita
	 * @param cliente
	 * il cliente passato ome modelClientsI
	 */
	void setClient(modelClientsI cliente);
	/***
	 * setto l'iva per questa vendita
	 * @param iva
	 * iva passato come float
	 */
	void setIVA(float iva);
	/***
	 * setto il numero della ricevuta per questa vendita( lo stesso creato per lo scontrino di questa vendita)
	 * @param nRicevuta
	 * int con il numero della ricevuta
	 */
	void setNumberPaymentReceipt(int nRicevuta);
	/***
	 * ottiene il totale di incasso per quella vendita
	 * @return
	 * un double con l'incasso 
	 */
	double getTotalColleactions();
	
	
	/***
	 * metodo che elimina la vendita corrente e i suoi relativi prodotti
	 * @return
	 * true o false a seconda del esito
	 */
	boolean deleteSale();
	/***
	 * ottiene la lista dei prodotti venduti
	 * @return
	 * una lista tipo transactionsProductsI altrimenti una lista vuota
	 */
	List<transactionsProductsI> soldProducts();
	
	/***
	 * modifica di una vendita
	 * @param newIDCliente
	 * id del cliente
	 * @param newNRicevuta
	 * numer della ricevuta
	 * @param newIva
	 * iva corrispondente
	 * @param newSconto
	 * nuovo sconto
	 * @param newData
	 * nuova data 
	 * @return
	 * true se  e andato a buon fine
	 */
	boolean update();
	
	/***
	 * elenco di tutte le vendite realizzate anche quelle che non possiedono uno scrontrino
	 * @return
	 * una lista contenente tutte le vendite
	 */
	public static List<modelSalesI> salesList(){
		if(modelUsersI.isLogged()){
			DataBaseSearch query = DataBaseSearch.queryDaTabella("Acquisti");
			try {
				return query.find().stream()
						.map(e -> new modelSales(e))
						.collect(Collectors.toList());
			} catch (SQLException e) {
				return new ArrayList<modelSalesI>();
			}
		}else
			return new ArrayList<modelSalesI>();
		
	}
	/***
	 * creazione di una nuova vendita
	 * @param cliente
	 * cliente a cui e stato effettuto la vendita
	 * @param nRicevuta
	 * numero della ricevuta
	 * @param iva
	 * iva per questa vendita
	 * @param sconto
	 * sconto di questa vendita
	 * @param data
	 * data della vendita
	 * @param prodotti
	 * lista di prodotti venduti
	 * @return
	 * true se andato a buon fine altrimenti false
	 */
	public static boolean builder(modelClientsI cliente, int nRicevuta, float iva, float sconto, Date data, List<transactionsProductsI> prodotti){
		if(modelUsersI.isLogged()){
			modelSalesI temp = new modelSales();
			temp.setClient(cliente);
			temp.setDiscount(sconto);
			temp.setDate(data);
			temp.setIVA(iva);
			temp.setNumberPaymentReceipt(nRicevuta);
			//salvo i prodotti della vendita
			if(modelTransactionsI.transactionsProducts(nRicevuta, prodotti, true))
				return temp.update();
			else
				return false;
		}
		return false;
	}
	/***
	 * ottiene un report con le vendite ordinate in modo decrescente
	 * @return
	 * una lsita con tutte le vendite altrimenti una lista vuota
	 */
	public static List<modelSalesI> reportSales(){
		if(modelUsersI.isLogged()){
			Comparator<modelSalesI> sort = (primo, secondo) -> Double.compare(primo.getTotalColleactions(), secondo.getTotalColleactions());
			
			return modelSalesI.salesList().stream()
					.sorted(sort)
					.collect(Collectors.toList());
		}
		else
			return new ArrayList<modelSalesI>();
	}

}
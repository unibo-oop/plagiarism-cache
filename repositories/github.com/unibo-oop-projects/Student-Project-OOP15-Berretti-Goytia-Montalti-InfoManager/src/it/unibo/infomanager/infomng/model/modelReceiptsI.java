package it.unibo.infomanager.infomng.model;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import it.unibo.infomanager.infomng.controller.DataBaseSearch;
/***
 * interfaccia per la gestione degli sontrini
 * @author Juan Goytia
 *
 */
public interface modelReceiptsI {
	
	void setNumberReceipt(int nScontrino);
	/***
	 * setto nello scontrino l'eventuale cliente se si tratta di una vendita
	 * @param cliente
	 * cliente passato come modelClientsI
	 */
	void setClient(modelClientsI cliente);
	/***
	 * setto il providers se si tratta di un acquisto
	 * @param fornitore
	 * fornitore passato come modelProvidersI
	 */
	void setProvider(modelProvidersI fornitore);
	/***
	 * setto la data dello scontrino
	 * @param data
	 * data passato come sql.Date
	 */
	void setDate(Date data);
	/***
	 * setta l'iva per questo scontrino
	 * @param iva
	 */
	void setIva(float iva);
	/***
	 * setto lo sconto per questo scontrino
	 * @param sconto
	 * lo sconto passato come un float
	 */
	void setDiscount(float sconto);
	
	/***
	 * ottiene l'ID del record
	 * @return
	 */
	Integer getID();
	
	/***
	 * ottiene il numero dello scontrino
	 * @return
	 * un int con il numero dello scontrino
	 */
	int getNumberReceipt();
	/***
	 * ottiene l'iva dello scontrino
	 * @return
	 * un float contenente l'IVA
	 */
	float getIVA();
	/***
	 * ottiene l'eventuale sconto in questo scontrino
	 * @return
	 */
	float getDiscount();
	
	/***
	 *ottiene la data di emissione dello scontrino 
	 * @return
	 * un object sql.Date con la data
	 */
	Date getDate();

	/***
	 * ottiene il cliente se si tratta di una vendita altrimenti ottine un cliente null
	 * @return
	 * un obtject tipo modelCLientsI altrimenti null
	 */
	modelClientsI getClient();

	/***
	 * lista dei prodotti venduti o acquistati in questo scontrino
	 * @return
	 * una lista tipo transactionsProductsI alrimenti una lista vuota
	 */
	List<transactionsProductsI> listTransactionsProducts();
	/***
	 * ottiene il fornitore se si tratta di un acquisto altrimenti un fornitore null
	 * @return
	 * una lista con i prodotti vendutiAcquistati altrimenti null
	 */
	modelProvidersI getProvider();
	
	/***
	 * elimnazione dello scontrino corrente
	 * @return
	 * true se e andato  a buon fine
	 */
	boolean deleteReceipt();
	
	/***
	 * aggiornamento(modifica salvataggio) di un record
	 * @return
	 * true se andato a buon fine altrimenti false
	 */
	boolean update();
	

	/***
	 * ottiene una lista con tutti gli scontrini emmessi
	 * @return
	 * un lista di tipo modelReceiptsI
	 */
	public static List<modelReceiptsI> receiptsList(){
		if(modelUsersI.isLogged()){
			DataBaseSearch query = DataBaseSearch.queryDaTabella("Scontrini");
			try {
				return query.find().stream()
						.map(e -> new modelReceipts(e))
						.collect(Collectors.toList());
			} catch (SQLException e) {
				return new ArrayList<modelReceiptsI>();
			}
		}
		else
			return new ArrayList<modelReceiptsI>(); 
	}
	
	/***
	 * creazione di uno scontrino
	 * @param cliente
	 * passare il cliente se si tratta di una vendita altrimenti passare l'optional vuoto
	 * @param fornitore
	 * passare il fornitore se si tratta di un acquisto altrimenti passare l'optional vuoto
	 * @param data
	 * data dello scontrino
	 * @param iva
	 * iva dello scontrino
	 * @param sconto
	 * sconto per questo scontrino
	 * @param prodotti
	 * lista di prodotti sottforma di transactionsProductsI
	 * @return
	 * true se andato a buon fine altrimenti false
	 */
	public static boolean builder(Optional<modelClientsI> cliente, Optional<modelProvidersI> fornitore, Date data, float iva,float sconto, List<transactionsProductsI> prodotti){
		if(modelUsersI.isLogged()){
			//variabile che mi salva il numero dello sconrino
			int nScontrino;
			//controllo se si tratto del primo scontrino 
			if(modelReceiptsI.receiptsList().size() == 0)
				nScontrino = 1000;
			else
				nScontrino = modelReceiptsI.receiptsList().get(modelReceiptsI.receiptsList().size()-1).getNumberReceipt()+1;
			
			modelReceiptsI temp = new modelReceipts();
			if(cliente.isPresent()){
				temp.setClient(cliente.get());
				temp.setNumberReceipt(nScontrino);
				temp.setDate(data);
				temp.setIva(iva);
				temp.setDiscount(sconto);
				modelSalesI.builder(cliente.get(), nScontrino, iva, sconto, data, prodotti);
				return temp.update();
			}
			else{
				temp.setProvider(fornitore.get());
				temp.setNumberReceipt(nScontrino);
				temp.setDate(data);
				temp.setIva(iva);
				temp.setDiscount(sconto);
				modelPurchasesI.builder(data, sconto, iva, nScontrino, fornitore.get(), prodotti);
				return temp.update();
			}
		}
		return false;
	}
	
	/***
	 * ricerca di uno specifico scontrino
	 * @param nScontrino
	 * numero dello scontrino da ricercare
	 * @return
	 * lo scontrino altrimenti null
	 */
	public static modelReceiptsI searchReceiptByNumber(int nScontrino){
		if(modelUsersI.isLogged()){
			return modelReceiptsI.receiptsList().stream()
					.filter(s -> s.getNumberReceipt() == nScontrino)
					.findFirst()
					.orElse(null);
		}
		return null;
	}
	
}
package it.unibo.infomanager.infomng.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import it.unibo.infomanager.infomng.controller.DataBaseSearch;
import it.unibo.infomanager.infomng.controller.TableRow;
/***
 * interfaccia per la gestione di ogni movimento realizzato( vendita o aquisto )
 * @author Juan Goytia
 *
 */
public interface modelTransactionsI {
	/***
	 * ottiene l'id del record
	 * @return
	 * un integer con l'id del record
	 */
	Integer getID();
	/***
	 * ottiene prodotto movimentato
	 * @return
	 * il prodotto venduto o acquistato altrimenti null
	 */
	modelStoreI getProduct();
	/***
	 * ottiene il numero della ricevuta vendita o acquisto
	 * @return
	 * int con il numero della ricevuta
	 */
	int getNumberPaymentRicevuta();
	/***
	 * ottiene la quantita acquistata o venduta del prodotto
	 * @return
	 * un intero con la quantita
	 */
	int getQuantity();
	/***
	 * ottiene il perzzo unitario del prodotto
	 * @return
	 * un double con il prezzo
	 */
	double getPrice();
	/***
	 * eliminazine del movimento corrente
	 * @return
	 * true se andato a buon fine altrimenti false
	 */
	boolean deleteTransactions();
	/***
	 * elenco con tutti i movimenti fatti
	 * @return
	 * una lista che contiene tutti i movimenti. ACquisto e vendite si diferenzano per la quantita in ogni movimento
	 * vendita -> quantita negativa acquisto->quantita positiva
	 */
	public static List<modelTransactionsI> transactionsList(){
		if(modelUsersI.isLogged()){
			DataBaseSearch query = DataBaseSearch.queryDaTabella("Movimenti");
			try {
				return query.find().stream()
						.map(e -> new modelTransactions(e))
						.collect(Collectors.toList());
			} catch (SQLException e) {
				return new ArrayList<modelTransactionsI>();
			}
		}
		else
			return new ArrayList<modelTransactionsI>();
	}
	/***
	 * nuovo movimento effettuato
	 * @param nRicevuta
	 * @param lista
	 * lista tipo prodottoVenduto contenente tutti i prodotti coinvolti nel movimento
	 * @param ctrlVendita
	 * boolean che controlla se si tratta di una vendita(true) o acquisto(false)
	 * @return
	 * true o false a seconda dell'esito
	 */
	public static boolean transactionsProducts(int nRicevuta, List<transactionsProductsI> lista, boolean ctrlVendita){
		if(modelUsersI.isLogged()){
			boolean ctrl= true;
			for(transactionsProductsI p : lista){
				modelTransactions temp = new modelTransactions(TableRow.oggettoDaTabella("Movimenti"));
				temp.oggetto.setObjectValue("nRicevuta", nRicevuta );
				temp.oggetto.setObjectValue("IDProdotto", p.getProductInvolved().getID());
				if(ctrlVendita)
					temp.oggetto.setObjectValue("Quantita", -1 * p.getQuantity());
				else
					temp.oggetto.setObjectValue("Quantita", p.getQuantity());
				temp.oggetto.setObjectValue("Prezzo", p.getPrice());
				ctrl=temp.oggetto.salva();
			}
			return ctrl;
		}
		else
			return false;
	}
	/***
	 * metodo per eliminaare tutti i prodotti di una vendita o aquisto sbagliti
	 * @param nRicevuta
	 * nRicevuta, neceessario per cancellare la vendita
	 * @param ctrlVendita
	 * ture se si tratta di una vendita, false se si tratta di un acquisto
	 * @return
	 * true o false a secoda del esito
	 */
	public static boolean deleteTransactionsProducts(int nRicevuta, boolean ctrlVendita){
		if(modelUsersI.isLogged()){
			List<modelTransactionsI> temp;
			boolean ctrl = true;
			if(ctrlVendita){
				temp = modelTransactionsI.transactionsList().stream()
				.filter(e -> e.getNumberPaymentRicevuta()==nRicevuta)
				.filter(e -> e.getQuantity() < 0)
				.collect(Collectors.toList());
				if(!temp.isEmpty())
					for(modelTransactionsI a : temp){ ctrl = a.deleteTransactions(); }
				return ctrl;
			}
			else
			{
				temp = modelTransactionsI.transactionsList().stream()
				.filter(e -> e.getNumberPaymentRicevuta()==nRicevuta)
				.filter(e -> e.getQuantity() > 0)
				.collect(Collectors.toList());
				if(!temp.isEmpty())	
					for(modelTransactionsI a : temp){ ctrl = a.deleteTransactions(); }
						return ctrl;
			}
		}
		else
			return false;
	}
}
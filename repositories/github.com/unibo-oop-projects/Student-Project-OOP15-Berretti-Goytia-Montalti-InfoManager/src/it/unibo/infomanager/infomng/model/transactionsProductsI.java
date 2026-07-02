package it.unibo.infomanager.infomng.model;
/***
 * interfaccia per il tipo di dato di un prodotto movimentato
 * @author Juan
 *	
 */
public interface transactionsProductsI {
	/***
	 * ottiene il prodotto salvato nella classe
	 * @return
	 * il prodotto coinvolto nel movimento
	 */
	modelStoreI getProductInvolved();
	/***
	 * ottiene la quantita del prodotto
	 * @return
	 * un int con la quantita del prodotto
	 */
	int getQuantity();
	/**
	 * ottiene il prezzo unitario del prodotto
	 * @return
	 * ottiene il prezzo del prodotto coinvolto nel movimento
	 */
	double getPrice();
	
}
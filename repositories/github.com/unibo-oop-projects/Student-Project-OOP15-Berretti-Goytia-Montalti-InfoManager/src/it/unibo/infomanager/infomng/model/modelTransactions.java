package it.unibo.infomanager.infomng.model;

import it.unibo.infomanager.infomng.controller.TableRow;
/***
 * 
 * @author Juan Goytia
 *
 */
public class modelTransactions implements modelTransactionsI{
	
	TableRow oggetto;
	
	protected modelTransactions(TableRow temp){
		this.oggetto = temp;
	}
	
	public modelTransactions(){
		this.oggetto = TableRow.oggettoDaTabella("Movimenti");
	}
	@Override
	public Integer getID(){
		return this.oggetto.objectId();
	}

	private Integer getIDProduct(){
		return (Integer) this.oggetto.getObject("IDProdotto");
	}
	
	@Override
	public int getNumberPaymentRicevuta(){
		return (int)this.oggetto.getObject("nRicevuta");
	}
	@Override
	public int getQuantity(){
		return (int)this.oggetto.getObject("Quantita");
	}
	@Override
	public double getPrice(){
		return (double)this.oggetto.getObject("Prezzo");
	}
	@Override
	public boolean deleteTransactions(){
		return this.oggetto.elimina();
	}

	@Override
	public modelStoreI getProduct() {
		return modelStoreI.productsList().stream()
				.filter( p -> p.getID().equals(this.getIDProduct()))
				.findFirst()
				.orElse(null);
	}

}

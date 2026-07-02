package it.unibo.infomanager.infomng.model;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import it.unibo.infomanager.infomng.controller.TableRow;
/***
 * 
 * @author Juan Goytia
 *
 */
class modelPurchases implements modelPurchasesI{
	
	TableRow oggetto;
	
	private Integer getIDProvider(){
		return (Integer)this.oggetto.getObject("IDFornitore");
	}
	protected modelPurchases(TableRow temp){
		this.oggetto = temp;
	}
	
	public modelPurchases(){
		this.oggetto = TableRow.oggettoDaTabella("Acquisti");
	}
	@Override
	public void setNumberPaymentReceipt(int nRicevuta){
		this.oggetto.setObjectValue("nRicevuta", nRicevuta);
	}
	@Override
	public void setIVA(float iva){
		this.oggetto.setObjectValue("IVA", iva);
	}
	@Override
	public Integer getID(){
		return this.oggetto.objectId();
	}
	@Override
	public void setProvider(modelProvidersI provider){
		this.oggetto.setObjectValue("IDFornitore", provider.getID());
	}
	@Override
	public void setDate(Date data){
		this.oggetto.setObjectValue("Data", data);
	}
	@Override
	public void setDiscount(float sconto){
		this.oggetto.setObjectValue("Sconto", sconto);
	}
	@Override
	public int getNumberPaymentReceipt(){
		return (int)this.oggetto.getObject("nRicevuta");
	}
	@Override
	public modelProvidersI getProvider(){
		return modelProvidersI.providersList().stream()
				.filter(p -> p.getID().equals(this.getIDProvider()))
				.findFirst()
				.get();
	}
	@Override
	public float getIva(){
		return (float)this.oggetto.getObject("IVA");
	}
	@Override
	public Date getDate(){
		return (Date)this.oggetto.getObject("Data");
	}
	@Override
	public float getDiscount(){
		return (float)this.oggetto.getObject("Sconto");
	}
	@Override
	public List<transactionsProducts> purchasedProducts(){
		return modelTransactionsI.transactionsList().stream()
				.filter( m -> m.getNumberPaymentRicevuta() == this.getNumberPaymentReceipt())
				.map( p -> {
					return new transactionsProducts(p.getProduct(), Math.abs(p.getQuantity()), p.getPrice());
				})
				.collect(Collectors.toList());
				
	}
	
	/***
	 * eliminazione dell'accquisto corrente
	 * @return
	 * true o false a seconda del esito
	 */
	@Override
	public boolean deletePurchase(){
		
		if(modelTransactionsI.deleteTransactionsProducts(this.getNumberPaymentReceipt(), false))
			return this.oggetto.elimina();
		else 
			return false;
	}
	
	/*
	 * calcolo del totale speso in un acquisto
	 */
	@Override
	public Double getTotalSpent(){
		return purchasedProducts().stream()
		.mapToDouble(e -> e.getPrice() * e.getQuantity())
		.sum();
	}

	@Override
	public boolean update(){
		return this.oggetto.salva();
	}

}

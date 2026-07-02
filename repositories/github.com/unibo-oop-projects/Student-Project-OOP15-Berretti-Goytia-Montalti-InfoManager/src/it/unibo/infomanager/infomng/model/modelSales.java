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
public class modelSales implements modelSalesI {
	
	TableRow oggetto;
	//List<transactionsProducts> prodottiVenduti;
	
	private Integer getIDClient(){
		return (Integer) this.oggetto.getObject("IDCliente");
	}
	protected modelSales(TableRow temp){
		this.oggetto = temp;
	}
	
	public modelSales(){
		this.oggetto = TableRow.oggettoDaTabella("Vendite");
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
	public void setClient(modelClientsI cliente){
		this.oggetto.setObjectValue("IDCliente", cliente.getID());
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
	public Integer getID(){
		return this.oggetto.objectId();
	}
	@Override
	public int getNumberPaymentReceipt(){
		return (int)this.oggetto.getObject("nRicevuta");
	}
	@Override
	public modelClientsI getClient(){
		return modelClientsI.clientsList().stream()
				.filter(c -> c.getID().equals(this.getIDClient()))
				.findFirst()
				.orElse(null);
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
	
	/***
	 * metodo per il salvataggio dei produtti venduti
	 * @param nRicevuta
	 * @param lista
	 * @return
	 * true o false a seconda dell'esito
	 */
	protected boolean builderProductsSale(int nRicevuta, List<transactionsProductsI> lista){
		
		return modelTransactionsI.transactionsProducts(nRicevuta, lista, true);
	}
	@Override
	public boolean deleteSale(){

		if(modelTransactionsI.deleteTransactionsProducts(this.getNumberPaymentReceipt(), true))
			return this.oggetto.elimina();
		else 
			return false;
	}
	@Override
	public List<transactionsProductsI> soldProducts(){
		return modelTransactionsI.transactionsList().stream()
				.filter( m -> m.getNumberPaymentRicevuta() == this.getNumberPaymentReceipt())
				.map( p -> {
					transactionsProducts prod = new transactionsProducts(p.getProduct(), Math.abs(p.getQuantity()), p.getPrice());
					return prod;
				})
				.collect(Collectors.toList());
	}
	@Override
	public boolean update(){
		return this.oggetto.salva();
	}
	@Override
	public double getTotalColleactions(){
		
		return this.soldProducts().stream()
				.mapToDouble(e -> e.getPrice() * e.getQuantity())
				.sum();
	}
	
}

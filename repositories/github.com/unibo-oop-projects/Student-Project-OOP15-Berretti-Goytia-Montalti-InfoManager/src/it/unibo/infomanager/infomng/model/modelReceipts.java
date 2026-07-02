package it.unibo.infomanager.infomng.model;

import java.util.List;
import java.util.stream.Collectors;

import it.unibo.infomanager.infomng.controller.TableRow;

import java.sql.Date;

/***
 * 
 * @author Juan Goytia
 *
 */
public class modelReceipts implements modelReceiptsI{
	
	TableRow oggetto;
	
	protected modelReceipts(TableRow temp){
		this.oggetto=temp;
	}
	private Integer getIDCliente(){
		return (Integer)this.oggetto.getObject("IDCliente");
	}
	private Integer getIDFornitore(){
		return (Integer)this.oggetto.getObject("IDFornitore");
	}
	/***
	 * Ottengo un record nuovo dalla tabella Scontrini
	 */
	public modelReceipts(){
		this.oggetto = TableRow.oggettoDaTabella("Scontrini");
	}
	@Override
	public Integer getID(){
		return this.oggetto.objectId();
	}
	@Override
	public void setDate(Date data){
		this.oggetto.setObjectValue("Data", data);
	}
	@Override
	public void setIva(float iva){
		this.oggetto.setObjectValue("IVA", iva);
	}
	@Override
	public int getNumberReceipt(){
		return (int)this.oggetto.getObject("NumeroScontrino");
	}

	@Override
	public modelClientsI getClient(){

			return modelClientsI.clientsList().stream()
					.filter(c ->{
						try{
						return c.getID().equals(this.getIDCliente());
						}catch(NullPointerException e){
							return false;
						}
					})
					.findFirst()
					.orElse(null);
	}
	@Override
	public modelProvidersI getProvider(){
		return modelProvidersI.providersList().stream()
				.filter(p -> {
					try{
						return p.getID().equals(this.getIDFornitore());}
					catch(Exception e){return false;}
				})
				.findFirst()
				.orElse(null);
	}
	@Override
	public float getIVA(){
		return (float) this.oggetto.getObject("IVA");
	}
	@Override
	public Date getDate(){
		return (Date)this.oggetto.getObject("Data");
	}
	@Override
	public List<transactionsProductsI> listTransactionsProducts(){
		return modelTransactionsI.transactionsList().stream()
				.filter( m -> m.getNumberPaymentRicevuta() == this.getNumberReceipt())
				.map( p -> {
					return new transactionsProducts(p.getProduct(), Math.abs(p.getQuantity()), p.getPrice());
				})
				.collect(Collectors.toList());
				
	}
	@Override
	public boolean update(){
		return this.oggetto.salva();
	}
	@Override
	public boolean deleteReceipt(){
		return this.oggetto.elimina();
	}
	@Override
	public void setNumberReceipt(int nScontrino) {
		this.oggetto.setObjectValue("NumeroScontrino", nScontrino);
	}
	@Override
	public void setProvider(modelProvidersI fornitore) {
		// TODO Auto-generated method stub
		this.oggetto.setObjectValue("IDFornitore", fornitore.getID());
	}
	@Override
	public void setDiscount(float sconto) {
		// TODO Auto-generated method stub
		this.oggetto.setObjectValue("Sconto", sconto);
	}
	@Override
	public void setClient(modelClientsI cliente) {
		// TODO Auto-generated method stub
		this.oggetto.setObjectValue("IDCliente", cliente.getID());
	}
	@Override
	public float getDiscount() {
		return (float)this.oggetto.getObject("Sconto");
	}
}

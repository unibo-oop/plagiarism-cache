package it.unibo.infomanager.infomng.model;

import it.unibo.infomanager.infomng.controller.TableRow;
/***
 * 
 * @author Juan goytia
 *
 */
	class modelStore implements modelStoreI {
	
	TableRow oggetto;
	
	private Integer getIDProvider(){
		return (Integer)this.oggetto.getObject("IDFornitore");
	}
	
	protected modelStore(TableRow temp){
		this.oggetto=temp;
	}
	
	protected modelStore(){
		this.oggetto = TableRow.oggettoDaTabella("Magazzino");
	}
	@Override
	public void setName(String nome){
		this.oggetto.setObjectValue("Nome", nome);
	}
	@Override
	public void setProductDeatils(String descrizione){
		this.oggetto.setObjectValue("Descrizione", descrizione);
	}
	@Override
	public Integer getID(){
		return this.oggetto.objectId();
	}
	@Override
	public String getName(){
		return (String) this.oggetto.getObject("Nome");
	}
	/***
	 * metodo che calola la quantita di un oggetto tramite un suo ID
	 * @param IDProdotto
	 * ID del prodotto da ottenere la quantita
	 * @return
	 * quantita del prodotto nel magazzzino
	 */
	@Override
	public int getQuantity(){
		
		return 	modelTransactionsI.transactionsList().stream()
				.filter(p -> p.getProduct().getID().equals(this.getID()))
				.mapToInt( m -> m.getQuantity())
				.sum();
	}
	@Override
	public String getProductDetails(){
		return (String)this.oggetto.getObject("Descrizione");
	}

	@Override
	public boolean deleteProduct(){
		return this.oggetto.elimina();
	}

	@Override
	public void setProvider(modelProvidersI provider) {
		this.oggetto.setObjectValue("IDFornitori", provider.getID());
	}

	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		return this.oggetto.salva();
	}

	@Override
	public modelProvidersI getProvider() {
		return modelProvidersI.providersList().stream()
				.filter(p -> p.getID().equals(this.getIDProvider()))
				.findFirst()
				.orElse(null);
	}
	
}

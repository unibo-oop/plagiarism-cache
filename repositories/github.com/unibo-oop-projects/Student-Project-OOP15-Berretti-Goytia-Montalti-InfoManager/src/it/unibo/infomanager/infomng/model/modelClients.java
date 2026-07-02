package it.unibo.infomanager.infomng.model;

import it.unibo.infomanager.infomng.controller.TableRow;
/***
 * 
 * @author Juan Goytia
 *
 */
class modelClients implements modelClientsI{
	
	TableRow oggetto;
	
	public modelClients(){
		this.oggetto= TableRow.oggettoDaTabella("Clienti");
	}
	
	protected modelClients(TableRow temp){
		this.oggetto=temp;
	}
	@Override
	public Integer getID(){
		return this.oggetto.objectId();
	}
	@Override
	public String getName(){
		return (String)this.oggetto.getObject("Nome");
	}
	@Override
	public String getLastName(){
		return (String)this.oggetto.getObject("Cognome");
	}
	@Override
	public String getMail(){
		return (String)this.oggetto.getObject("Mail");
	}
	@Override
	public String getPhone(){
		return (String)this.oggetto.getObject("Telefono");
	}
	@Override
	public String getShopName(){
		return (String)this.oggetto.getObject("Negozio");
	}
	@Override
	public void setName(String nome){
		this.oggetto.setObjectValue("Nome", nome);
	}
	@Override
	public void setLastName(String cognome){
		this.oggetto.setObjectValue("Cognome", cognome);
	}
	@Override
	public void setMail(String mail){
		this.oggetto.setObjectValue("Mail", mail);
	}
	@Override
	public void setPhone(String telf){
		this.oggetto.setObjectValue("Telefono", telf);
	}
	@Override
	public void setShopName(String negozio){
		this.oggetto.setObjectValue("Negozio", negozio);
	}
	@Override
	public boolean deleteClient(){
		return this.oggetto.elimina();
	}
	@Override
	public boolean update(){
		return this.oggetto.salva();
	}
	
}

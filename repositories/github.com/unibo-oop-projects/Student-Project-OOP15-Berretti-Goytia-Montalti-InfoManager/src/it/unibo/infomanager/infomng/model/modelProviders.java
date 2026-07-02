package it.unibo.infomanager.infomng.model;

import it.unibo.infomanager.infomng.controller.TableRow;
/***
 * 
 * @author Juan Goytia
 *
 */
class modelProviders implements modelProvidersI{
	
	TableRow oggetto;
	
	protected modelProviders(TableRow temp){
		this.oggetto=temp;
	}
	/***
	 * ottieni un nuovo record della tabella Fornitori
	 */
	public modelProviders(){
		this.oggetto = TableRow.oggettoDaTabella("Fornitori");
	}
	@Override
	public Integer getID(){
		return this.oggetto.objectId();
	}
	@Override
	public String getIDFornitore(){
		return String.format("%i", this.oggetto.objectId());
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
	public boolean deleteProvider(){
		return this.oggetto.elimina();
	}
	@Override
	public boolean update(){
		return this.oggetto.salva();
	}
}
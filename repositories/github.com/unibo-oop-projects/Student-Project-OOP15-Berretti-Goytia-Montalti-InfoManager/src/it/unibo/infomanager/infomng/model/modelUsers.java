package it.unibo.infomanager.infomng.model;

import it.unibo.infomanager.infomng.controller.TableRow;
/***
 * 
 * @author Juan Goytia
 *
 */
public class modelUsers implements modelUsersI {

	TableRow oggetto;
	/***
	 * Ottengo un record nuovo dalla tabella Scontrini
	 */
	public modelUsers(){
		this.oggetto= TableRow.oggettoDaTabella("Utenti");
	}
	
	protected modelUsers(TableRow temp){
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
	public String getUsername(){
		return (String)this.oggetto.getObject("Username");
	}
	@Override
	public String getPassword(){
		return (String)this.oggetto.getObject("Password");
	}
	@Override
	public String getMail(){
		return (String)this.oggetto.getObject("Mail");
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
	public void setUsername(String username){
		this.oggetto.setObjectValue("Username", ctrlString(username));
	}
	@Override
	public void setPassword(String password){
		this.oggetto.setObjectValue("Password", ctrlString(password));
	}
	
	protected static String ctrlString(String str){
		return str != "" ? str : null;
	}
	
	@Override
	public boolean deleteUser(String nome, String cognome, String mail, String username, String password){
		//mi accerto che si tratti dello stesso cliente richiedendo i dati
		if(this.getName().equals(nome) && this.getLastName().equals(cognome) && this.getUsername().equals(username) && this.getMail().equals(mail) && this.getPassword().equals(password))
			return this.oggetto.elimina();
		else 
			return false;
	}

	@Override
	public boolean update() {
		return this.oggetto.salva();
	}
	
	
}

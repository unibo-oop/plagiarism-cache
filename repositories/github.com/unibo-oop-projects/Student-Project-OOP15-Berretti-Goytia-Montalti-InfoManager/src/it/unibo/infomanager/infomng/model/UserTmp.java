package it.unibo.infomanager.infomng.model;

import java.util.Optional;

class UserTmp {
	
	protected static UserTmp CurrentUser = new UserTmp();
	
	private Optional<modelUsersI> utente;
	
	protected UserTmp(){
		this.utente = Optional.empty();
	}
	
	public boolean isLogged(){
		return this.utente != null && this.utente.isPresent();
	}
	
	public modelUsersI getUtente(){
		return this.utente.get();
	}
	
	public void setUtente(modelUsersI utente){
		this.utente = Optional.ofNullable(utente);
	}
}

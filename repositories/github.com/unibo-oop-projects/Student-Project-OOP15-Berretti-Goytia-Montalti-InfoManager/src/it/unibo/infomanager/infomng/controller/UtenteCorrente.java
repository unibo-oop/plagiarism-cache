package it.unibo.infomanager.infomng.controller;

import java.util.Optional;

class UtenteCorrente {
	private Optional<tmpUser> utente;
	
	public UtenteCorrente() {
		this.utente = Optional.empty();
	}
	
	public void setUtente(tmpUser nuovo){
		this.utente = Optional.ofNullable(nuovo);
	}
	
	public Optional<tmpUser> getUtente(){
		return this.utente;
	}
	
	public static class tmpUser {
		public String nome;
		public String cognome;
	}
}

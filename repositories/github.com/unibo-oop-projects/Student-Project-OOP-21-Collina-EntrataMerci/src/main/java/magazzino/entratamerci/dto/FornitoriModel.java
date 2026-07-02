package magazzino.entratamerci.dto;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FornitoriModel{
	private SimpleStringProperty codice;
	private SimpleStringProperty desc;
	private SimpleStringProperty nome;
	private SimpleStringProperty cognome;
	
	
	public FornitoriModel(SimpleStringProperty codice, SimpleStringProperty desc, SimpleStringProperty nome,
			SimpleStringProperty cognome) {
		super();
		this.codice = codice;
		this.desc = desc;
		this.nome = nome;
		this.cognome = cognome;
	}
	
	public FornitoriModel(String codice2, String descrizione, String nome, String cognome) {
		this.codice = new SimpleStringProperty(codice2);
		this.desc = new SimpleStringProperty(descrizione);
		this.nome = new SimpleStringProperty(nome);
		this.cognome = new SimpleStringProperty(cognome);
		
	}
	
	public SimpleStringProperty getCodice() {
		return this.codice;
	}
	public void setCodice(SimpleStringProperty codice) {
		this.codice = codice;
	}
	public SimpleStringProperty getDesc() {
		return this.desc;
	}
	public void setDesc(SimpleStringProperty desc) {
		this.desc = desc;
	}
	public SimpleStringProperty getNome() {
		return nome;
	}
	public void setNome(SimpleStringProperty nome) {
		this.nome = nome;
	}
	public SimpleStringProperty getCognome() {
		return cognome;
	}
	public void setCognome(SimpleStringProperty cognome) {
		this.cognome = cognome;
	}

	public StringProperty codiceProperty() {
	    return codice;
	}
	public StringProperty descProperty() {
	    return desc;
	}
	public StringProperty nomeProperty() {
	    return nome;
	}
	public StringProperty cognomeProperty() {
	    return cognome;
	}
}

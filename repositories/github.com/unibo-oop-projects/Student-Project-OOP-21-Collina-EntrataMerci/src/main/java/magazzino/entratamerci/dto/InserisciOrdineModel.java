package magazzino.entratamerci.dto;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class InserisciOrdineModel{
	private SimpleStringProperty riga;
	private SimpleStringProperty codice;
	private SimpleStringProperty descr;
	private SimpleIntegerProperty qta;
	
	
	public InserisciOrdineModel(SimpleStringProperty codice, SimpleStringProperty descr, SimpleIntegerProperty qta) {
		this.codice = codice;
		this.descr = descr;
		this.qta = qta;
	}
	
	public InserisciOrdineModel(String codice, String descr, Integer qta) {
		this.codice = new SimpleStringProperty(codice);
		this.descr = new SimpleStringProperty(descr);
		this.qta = new SimpleIntegerProperty(qta);
		
	}
	

	public SimpleStringProperty getCodice() {
		return codice;
	}

	public void setCodice(SimpleStringProperty codice) {
		this.codice = codice;
	}

	public SimpleIntegerProperty getQta() {
		return qta;
	}

	public void setQta(SimpleIntegerProperty qta) {
		this.qta = qta;
	}

	public StringProperty codiceProperty() {
	    return codice;
	}
	public StringProperty descrProperty() {
	    return descr;
	}
	public SimpleStringProperty getDescr() {
		return descr;
	}

	public void setDescr(SimpleStringProperty descr) {
		this.descr = descr;
	}
	public IntegerProperty qtaProperty() {
	    return qta;
	}
}

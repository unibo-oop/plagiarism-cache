package magazzino.entratamerci.dto;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AreaModel{
	private SimpleStringProperty codice;
	private SimpleStringProperty desc;
	
	
	public AreaModel(SimpleStringProperty codice, SimpleStringProperty desc) {
		super();
		this.codice = codice;
		this.desc = desc;
	}
	
	public AreaModel(String codice2, String descrizione) {
		this.codice = new SimpleStringProperty(codice2);
		this.desc = new SimpleStringProperty(descrizione);
		
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

	public StringProperty codiceProperty() {
	    return codice;
	}
	public StringProperty descProperty() {
	    return desc;
	}
}

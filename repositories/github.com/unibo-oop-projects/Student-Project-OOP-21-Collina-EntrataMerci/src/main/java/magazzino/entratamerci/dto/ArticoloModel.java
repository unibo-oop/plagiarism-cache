package magazzino.entratamerci.dto;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ArticoloModel{
	private SimpleStringProperty codice;
	private SimpleStringProperty desc;
	private SimpleBooleanProperty isObsolete;
	private SimpleStringProperty note;
	
	
	
	public ArticoloModel(SimpleStringProperty codice, SimpleStringProperty desc, SimpleBooleanProperty isObsolete,
			SimpleStringProperty note) {
		super();
		this.codice = codice;
		this.desc = desc;
		this.isObsolete = isObsolete;
		this.note = note;
	}
	
	public ArticoloModel(String codice, String descrizione, boolean obsoleto, String note) {
		this.codice = new SimpleStringProperty(codice);
		this.desc = new SimpleStringProperty(descrizione);
		this.isObsolete = new SimpleBooleanProperty(obsoleto);
		this.note = new SimpleStringProperty(note);
		
	}
	
	public String getCodice() {
		return this.codice.get();
	}
	public String getDesc() {
		return this.desc.get();
	}
	public Boolean getIsObsolete() {
		return this.isObsolete.get();
	}
	public String getNote() {
		return this.note.get();
	}
	public StringProperty codiceProperty() {
	    return codice;
	}
	public StringProperty descProperty() {
	    return desc;
	}
	public BooleanProperty isObsoleteProperty() {
	    return isObsolete;
	}
	public StringProperty noteProperty() {
	    return note;
	}
	
}

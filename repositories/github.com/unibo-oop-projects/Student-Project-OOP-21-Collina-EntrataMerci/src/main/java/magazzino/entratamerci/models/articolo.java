package magazzino.entratamerci.models;

import java.util.Date;
import java.util.Objects;

public class articolo {
	private String codice;
	private String descrizione;
	private String descrizioneBreve;
	private String note;
	private Date dataInizioValidita;
	private Date dataFineValidita;
	private boolean obsoleto;
	
	
	public articolo(String codice, String descrizione) {
		super();
		this.codice = codice;
		this.descrizione = descrizione;
	}

	public articolo(String codice, String descrizione, boolean obsoleto) {
		super();
		this.codice = codice;
		this.descrizione = descrizione;
		this.obsoleto = obsoleto;
	}

	public articolo(String codice, String descrizione, String descrizioneBreve, String note, Date dataInizioValidita,
			Date dataFineValidita, boolean obsoleto) {
		super();
		this.codice = codice;
		this.descrizione = descrizione;
		this.descrizioneBreve = descrizioneBreve;
		this.note = note;
		this.dataInizioValidita = dataInizioValidita;
		this.dataFineValidita = dataFineValidita;
		this.obsoleto = obsoleto; 
	}

	public String getCodice() {
		return codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getDescrizioneBreve() {
		return descrizioneBreve;
	}

	public void setDescrizioneBreve(String descrizioneBreve) {
		this.descrizioneBreve = descrizioneBreve;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getDataInizioValidita() {
		return dataInizioValidita;
	}

	public void setDataInizioValidita(Date dataInizioValidita) {
		this.dataInizioValidita = dataInizioValidita;
	}

	public Date getDataFineValidita() {
		return dataFineValidita;
	}

	public void setDataFineValidita(Date dataFineValidita) {
		this.dataFineValidita = dataFineValidita;
	}

	public boolean isObsoleto() {
		return obsoleto;
	}

	public void setObsoleto(boolean obsoleto) {
		this.obsoleto = obsoleto;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		articolo articolo = (articolo) o;
		return codice.equals(articolo.codice);
	}

	@Override
	public int hashCode() {
		return Objects.hash(codice);
	}
}

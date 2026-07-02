package magazzino.entratamerci.models;

import java.util.Objects;

public abstract class posizione {
	private String codice;
	private String descrizione;
	
	public posizione(String codice, String descrizione) {
		this.codice = codice;
		this.descrizione = descrizione;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		posizione posizione = (posizione) o;
		return codice.equals(posizione.codice) && Objects.equals(descrizione, posizione.descrizione);
	}

	@Override
	public int hashCode() {
		return Objects.hash(codice, descrizione);
	}
}

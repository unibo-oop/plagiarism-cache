package magazzino.entratamerci.models;

import java.util.Objects;

public class fornitore {
	private String codice;
	private String descrizione;
	private String nome;
	private String cognome;

	

	public fornitore(String codice, String descrizione, String nome, String cognome) {
		this.codice = codice;
		this.descrizione = descrizione;
		this.nome = nome;
		this.cognome = cognome;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNomeCognome() {
		return  String.format("%s %s",this.nome,this.cognome);
	}
	public String getCodiceDescrizione(){
		return  String.format("[%s] - %s",this.codice,this.descrizione);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		fornitore fornitore = (fornitore) o;
		return Objects.equals(codice, fornitore.codice);
	}

	@Override
	public int hashCode() {
		return Objects.hash(codice);
	}
}

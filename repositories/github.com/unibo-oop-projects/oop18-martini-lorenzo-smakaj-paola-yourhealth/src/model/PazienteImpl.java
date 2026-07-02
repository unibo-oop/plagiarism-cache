package model;

import java.io.Serializable;
import java.time.LocalDate;

import util.Enums.Sesso;

public final class PazienteImpl extends PersonaImpl.Builder implements Paziente, Serializable {

	private static final long serialVersionUID = 5390998140886300918L;
	private final String nome;
	private final String cognome;
	private final Sesso sesso;
	private final String luogonascita;
	private final LocalDate datanascita;
	private final String codicefiscale;
	private final String residenza;

	public PazienteImpl(final String nome, final String cognome, final Sesso sesso, final String luogonascita,
			final LocalDate datanascita, final String codicefiscale, final String residenza) {
		this.nome = nome;
		this.cognome = cognome;
		this.sesso = sesso;
		this.luogonascita = luogonascita;
		this.datanascita = datanascita;
		this.codicefiscale = codicefiscale;
		this.residenza = residenza;
	}

	public static class Builder extends PersonaImpl.Builder {
		private String nome1;
		private String cognome1;
		private Sesso sesso1;
		private String luogonascita1;
		private LocalDate datanascita1;
		private String codicefiscale1;
		private String residenza1;

		/**
		 * adds the fiscal code.
		 * 
		 * @param cf fiscal code
		 * @return patient
		 */
		public Builder codicefiscale(final String cf) {
			this.codicefiscale1 = cf;
			return this;
		}

		/**
		 * adds the address.
		 * 
		 * @param res address
		 * @return patient
		 */
		public Builder residenza(final String res) {
			this.residenza1 = res;
			return this;
		}

		public Paziente build() throws IllegalArgumentException {
			if (this.codicefiscale1.isEmpty() || this.residenza1.isEmpty()) {
				throw new IllegalArgumentException("Invalid Input");
			}
			return new PazienteImpl(this.nome1, this.cognome1, this.sesso1, this.luogonascita1, this.datanascita1,
					this.codicefiscale1, this.residenza1);
		}
	}

	@Override
	public String getCodicefiscale() {
		return this.codicefiscale;
	}

	@Override
	public String getResidenza() {
		return this.residenza;
	}

	@Override
	public String getNome() {
		return this.nome;
	}

	@Override
	public String getCognome() {
		return this.cognome;
	}

	@Override
	public String getSesso() {
		return this.sesso.getSesso();
	}

	@Override
	public String getLuogoNascita() {
		return this.luogonascita;
	}

	@Override
	public LocalDate getDataNascita() {
		return this.datanascita;
	}

}

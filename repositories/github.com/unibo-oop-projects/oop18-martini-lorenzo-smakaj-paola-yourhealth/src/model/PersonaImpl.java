package model;

import java.io.Serializable;
import java.time.LocalDate;

import util.Enums.Sesso;

public class PersonaImpl implements Persona, Serializable {

	private static final long serialVersionUID = -5773439756675582771L;
	private final String nome;
	private final String cognome;
	private final Sesso sesso;
	private final String luogonascita;
	private final LocalDate datanascita;

	protected PersonaImpl(final String nome, final String cognome, final Sesso sesso, final String luogonascita,
			final LocalDate datanascita) {
		this.nome = nome;
		this.cognome = cognome;
		this.sesso = sesso;
		this.luogonascita = luogonascita;
		this.datanascita = datanascita;
	}

	public static class Builder {
		private String nome1;
		private String cognome1;
		private Sesso sesso1;
		private String luogonascita1;
		private LocalDate datanascita1;

		/**
		 * adds the name.
		 * 
		 * @param n name
		 * @return person
		 */
		public Builder nome(final String n) {
			this.nome1 = n;
			return this;
		}

		/**
		 * adds the surname.
		 * 
		 * @param sn surname
		 * @return person
		 */
		public Builder cognome(final String sn) {
			this.cognome1 = sn;
			return this;
		}

		/**
		 * adds the sex.
		 * 
		 * @param s sex
		 * @return person
		 */
		public Builder sesso(final Sesso s) {
			this.sesso1 = s;
			return this;
		}

		/**
		 * adds the birthplace.
		 * 
		 * @param ln birthplace
		 * @return person
		 */
		public Builder luogonascita(final String ln) {
			this.luogonascita1 = ln;
			return this;
		}

		/**
		 * adds the birth date.
		 * 
		 * @param dn birth date
		 * @return person
		 */
		public Builder datanascita(final LocalDate dn) {
			this.datanascita1 = dn;
			return this;
		}

		public Persona build() throws IllegalArgumentException {
			if (this.nome1.isEmpty() || this.cognome1.isEmpty() || this.sesso1.toString().isEmpty()
					|| this.luogonascita1.isEmpty() || this.datanascita1 == null) {
				throw new IllegalArgumentException("Invalid Input");
			}
			return new PersonaImpl(this.nome1, this.cognome1, this.sesso1, this.luogonascita1, this.datanascita1);
		}
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

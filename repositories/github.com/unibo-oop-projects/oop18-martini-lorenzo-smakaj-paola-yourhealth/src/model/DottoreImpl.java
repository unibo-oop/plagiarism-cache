package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import util.Enums.Ruolo;
import util.Enums.Sesso;

public class DottoreImpl extends PersonaImpl.Builder implements Dottore, Serializable {

	private static final long serialVersionUID = 1259675923576837038L;
	private final String nome;
	private final String cognome;
	private final Sesso sesso;
	private final String luogonascita;
	private final LocalDate datanascita;
	private final int tesserino;
	private final Ruolo ruolo;
	private final LocalTime orarioinizio;
	private final LocalTime orariofine;

	public DottoreImpl(final String nome, final String cognome, final Sesso sesso, final String luogonascita,
			final LocalDate datanascita, final int tesserino, final Ruolo ruolo, final LocalTime orarioinizio,
			final LocalTime orariofine) {
		this.nome = nome;
		this.cognome = cognome;
		this.sesso = sesso;
		this.luogonascita = luogonascita;
		this.datanascita = datanascita;
		this.tesserino = tesserino;
		this.ruolo = ruolo;
		this.orarioinizio = orarioinizio;
		this.orariofine = orariofine;
	}

	public static class Builder extends PersonaImpl.Builder {

		private int tesserino1;
		private String nome1;
		private String cognome1;
		private Sesso sesso1;
		private String luogonascita1;
		private LocalDate datanascita1;
		private Ruolo ruolo1;
		private LocalTime orarioinizio1;
		private LocalTime orariofine1;

		/**
		 * adds the id code.
		 * 
		 * @param id id code
		 * @return doctor
		 */
		public Builder tesserino(final int id) {
			this.tesserino1 = id;
			return this;
		}

		/**
		 * adds the role.
		 * 
		 * @param r role
		 * @return doctor
		 */
		public Builder ruolo(final Ruolo r) {
			this.ruolo1 = r;
			return this;
		}

		/**
		 * adds the shedule's begin.
		 * 
		 * @param oi schedule
		 * @return doctor
		 */
		public Builder orarioinizio(final LocalTime oi) {
			this.orarioinizio1 = oi;
			return this;
		}

		/**
		 * adds the shedule's end.
		 * 
		 * @param of schedule
		 * @return doctor
		 */
		public Builder orariofine(final LocalTime of) {
			this.orariofine1 = of;
			return this;
		}

		public Dottore build() throws IllegalArgumentException {
			if (this.tesserino1 != 0 || this.orarioinizio1 == null || this.orariofine1 == null) {
				throw new IllegalArgumentException("Invalid Input");
			}
			return new DottoreImpl(this.nome1, this.cognome1, this.sesso1, this.luogonascita1, this.datanascita1,
					this.tesserino1, this.ruolo1, this.orarioinizio1, this.orariofine1);
		}
	}

	@Override
	public int getTesserino() {
		return this.tesserino;
	}

	@Override
	public String getRuolo() {
		return this.ruolo.getRuolo();
	}

	@Override
	public LocalTime getOrarioInizio() {
		return this.orarioinizio;
	}

	@Override
	public LocalTime getOrarioFine() {
		return this.orariofine;
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

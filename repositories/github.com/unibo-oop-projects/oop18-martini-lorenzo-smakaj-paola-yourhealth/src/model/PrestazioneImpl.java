package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import util.Enums.Stato;
import util.Enums.TipoPrestazione;

public class PrestazioneImpl implements Prestazione, Serializable {

	private static final long serialVersionUID = -5932677735628055713L;
	private final String cfPaziente;
	private final int tesserinoDottore;
	private final TipoPrestazione tipo;
	private final LocalDate data;
	private final LocalTime ora;
	private final Stato stato;
	private final String macchinario;
	private final String ambulatorio;

	public PrestazioneImpl(final String paziente, final int dottore, final TipoPrestazione tipo, final LocalDate data,
			final LocalTime ora, final Stato stato, final String macchinario, final String ambulatorio) {
		this.cfPaziente = paziente;
		this.tesserinoDottore = dottore;
		this.tipo = tipo;
		this.data = data;
		this.ora = ora;
		this.stato = stato;
		this.macchinario = macchinario;
		this.ambulatorio = ambulatorio;
	}

	public static class Builder {
		private String paziente1;
		private int dottore1;
		private TipoPrestazione tipo1;
		private LocalDate data1;
		private LocalTime ora1;
		private Stato stato1;
		private String macchinario1;
		private String ambulatorio1;

		/**
		 * adds the patient.
		 * 
		 * @param p patient
		 * @return performance
		 */
		public Builder paziente(final String p) {
			this.paziente1 = p;
			return this;
		}

		/**
		 * adds the doctor.
		 * 
		 * @param doc doctor
		 * @return performance
		 */
		public Builder dottore(final int doc) {
			this.dottore1 = doc;
			return this;
		}

		/**
		 * adds the type of performance.
		 * 
		 * @param t type
		 * @return performance
		 */
		public Builder tipoprestazione(final TipoPrestazione t) {
			this.tipo1 = t;
			return this;
		}

		/**
		 * adds the date.
		 * 
		 * @param d performance's date
		 * @return performance
		 */
		public Builder data(final LocalDate d) {
			this.data1 = d;
			return this;
		}

		/**
		 * adds the hour.
		 * 
		 * @param d performance's hour
		 * @return performance
		 */
		public Builder ora(final LocalTime h) {
			this.ora1 = h;
			return this;
		}

		/**
		 * adds the status.
		 * 
		 * @param d performance's status
		 * @return performance
		 */
		public Builder stato(final Stato s) {
			this.stato1 = s;
			return this;
		}

		/**
		 * adds the machine.
		 * 
		 * @param c performance's machine
		 * @return performance
		 */
		public Builder macchinario(final String m) {
			this.macchinario1 = m;
			return this;
		}

		/**
		 * adds the treatment room.
		 * 
		 * @param amb performance's treatment room
		 * @return performance
		 */
		public Builder ambulatorio(final String amb) {
			this.ambulatorio1 = amb;
			return this;
		}

		public Prestazione build() throws IllegalArgumentException {
			if (this.paziente1.isEmpty() || this.dottore1 == 0 || this.tipo1.getTipo().isEmpty() || this.data1 == null
					|| this.ora1 == null || this.stato1.getStato().isEmpty() || this.macchinario1.isEmpty()
					|| this.ambulatorio1.isEmpty()) {
				throw new IllegalArgumentException("Invalid Input");
			}
			return new PrestazioneImpl(this.paziente1, this.dottore1, this.tipo1, this.data1, this.ora1, this.stato1,
					this.macchinario1, this.ambulatorio1);
		}
	}

	@Override
	public String getPaziente() {
		return this.cfPaziente;
	}

	@Override
	public int getDottore() {
		return this.tesserinoDottore;
	}

	@Override
	public String getTipo() {
		return this.tipo.getTipo();
	}

	@Override
	public LocalDate getData() {
		return this.data;
	}

	@Override
	public LocalTime getOra() {
		return this.ora;
	}

	@Override
	public String getStato() {
		return this.stato.getStato();
	}

	@Override
	public String getMacchinario() {
		return this.macchinario;
	}

	@Override
	public String getAmbulatorio() {
		return this.ambulatorio;
	}

}

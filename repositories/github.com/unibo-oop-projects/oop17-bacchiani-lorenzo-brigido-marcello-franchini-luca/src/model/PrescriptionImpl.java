package model;

import java.io.Serializable;
import java.time.LocalDate;

import utilities.Medicine;

public class PrescriptionImpl implements Prescription, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Person paziente;
	private final Worker personale;
	private final Medicine farmaco;
	private final LocalDate date;
	private final String cod;

	private PrescriptionImpl(final String cod, final Person paziente, final Worker personale, 
			final Medicine farmaco, final LocalDate date) {
		this.cod = cod;
		this.paziente = paziente;
		this.personale = personale;
		this.farmaco = farmaco;
		this.date = date;
	}

	public String getCode() {
		return this.cod;
	}
	public Person getPatient() {
		return this.paziente;
	}

	public Worker getStaff() {
		return this.personale;
	}

	public Medicine getMedicine() {
		return this.farmaco;
	}

	public LocalDate getDate() {
		return this.date;
	}


	public static class Builder {

		private String bCod;
		private Person bPaz;
		private Worker bPers;
		private Medicine bFarm;
		private LocalDate bDate;

		public Builder code(final String cod) {
			this.bCod = cod;
			return this;
		}
		public Builder patient(final Person paz) {
			this.bPaz = paz;
			return this;
		}

		public Builder staff(final Worker pers) {
			this.bPers = pers;
			return this;
		}

		public Builder medicine(final Medicine farm) {
			this.bFarm = farm;
			return this;
		}

		public Builder date(final LocalDate date) {
			this.bDate = date;
			return this;
		}

		public Prescription build() throws IllegalArgumentException {
			if (this.bCod.equals("") || this.bPaz == null || this.bPers == null || this.bFarm == null || this.bDate == null) {
				throw new IllegalArgumentException("Complete all fields");
			} else {
				return new PrescriptionImpl(this.bCod, this.bPaz, this.bPers, this.bFarm, this.bDate);
			}
		}
	}
}

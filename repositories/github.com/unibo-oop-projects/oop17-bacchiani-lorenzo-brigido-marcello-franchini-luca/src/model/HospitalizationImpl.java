package model;

import java.io.Serializable;
import java.time.LocalDate;

import utilities.Room;
import utilities.Bed;
import utilities.Department;

public class HospitalizationImpl implements Hospitalization, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final LocalDate startDate;
	private LocalDate endDate;
	private String state;
	private final Person p;
	private final Bed l;
	private final String cause;
	private final Worker pe;
	private final String cod;
	private String note;

	private HospitalizationImpl(final String cod, final Person p,
			final Bed l, final String cause, final Worker pe)  {
		this.startDate = LocalDate.now();
		this.p = p;
		this.l = l;
		this.state = "Ricoverato";
		this.cause = cause;
		this.pe = pe;
		this.cod = cod;
	}

	public String getCode() {
		return this.cod;
	}

	public LocalDate getInitDate() {
		return this.startDate;
	}


	public void setEndDate(final LocalDate Date) {
		this.endDate = Date;
	}

	public LocalDate getEndDate() {
		return this.endDate;
	}

	public void setState(final String state) {
		this.state = state;
	}

	public String getState() {
		return this.state;
	}

	public Person getPatient() {
		return this.p;
	}

	public Department getDepartment() {
		return l.getDepartment();
	}

	public Room getRoom() {
		return l.getRoom();
	}

	public Bed getBed() {
		return l;
	}

	public String getCause() {
		return this.cause;
	}

	public Worker getStaff() {
		return this.pe;
	}

	public void setNote(final String note) {
		this.note = note;
	}

	public String getNote() {
		return this.note;
	}

	public static class Builder {
		private String bCode;
		private Person bPerson;
		private Bed bBed;
		private String bCause;
		private Worker bStaff;

		public Builder code(final String code) {
			this.bCode = code;
			return this;
		}

		public Builder patient(final Person p) {
			this.bPerson = p;
			return this;
		}

		public Builder letto(final Bed l) {
			this.bBed = l;
			return this;
		}

		public Builder cause(final String cause) {
			this.bCause = cause;
			return this;
		}

		public Builder staff(final Worker pe) {
			this.bStaff = pe;
			return this;
		}

		public Hospitalization build() throws IllegalStateException {
			if (this.bCode.equals("") || this.bPerson == null || this.bBed == null || this.bCause.equals("") || this.bStaff == null) {
				throw new IllegalStateException("Complete all fields");
			} else {
				return new HospitalizationImpl(this.bCode, this.bPerson, this.bBed, this.bCause, this.bStaff);
			}
		}
	}
}

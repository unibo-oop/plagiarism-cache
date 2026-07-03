package model;

import java.io.Serializable;
import java.time.LocalDate;

import utilities.Role;

public class WorkerImpl extends PersonImpl implements Worker, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Role o;

	private WorkerImpl(final String name, final String surname, final String fiscalCode, final LocalDate date, 
			final String place, final String residency, final Role o, final String sex) {
		super(name, surname, fiscalCode, date, place, residency, sex);
		this.o = o;

	}

	public Role getRole() {
		return o;
	}	

	public static class Builder {
		private String bName;
		private String bSurname;
		private String bFiscalCode;
		private LocalDate bDate;
		private String bBirthPlace;
		private String bResidencyPlace;
		private Role bRole;
		private String bSex;

		public Builder name(final String name) {
			this.bName = name;
			return this;
		}

		public Builder surname(final String surname) {
			this.bSurname = surname;
			return this;
		}

		public Builder fiscalCode(final String fiscalCode) {
			this.bFiscalCode = fiscalCode;
			return this;
		}

		public Builder date(final LocalDate date) {
			this.bDate = date;
			return this;
		}

		public Builder birthPlace(final String placeOfBirth) {
			this.bBirthPlace = placeOfBirth;
			return this;
		}

		public Builder residency(final String residencyPlace) {
			this.bResidencyPlace = residencyPlace;
			return this;
		}

		public Builder role(final Role o) {
			this.bRole = o;
			return this;
		}

		public Builder sex(final String sex) {
			this.bSex = sex;
			return this;
		}

		public Worker build() throws IllegalStateException {
			if (this.bName.equals("") || this.bSurname.equals("") || this.bDate == null || this.bResidencyPlace.equals("")
					|| this.bBirthPlace.equals("") || this.bFiscalCode.equals("") || this.bRole == null || this.bSex == null) {
				throw new IllegalStateException("Complete all fields");
			} else {
				return new WorkerImpl(this.bName, this.bSurname, this.bFiscalCode, this.bDate, this.bBirthPlace, this.bResidencyPlace, this.bRole, this.bSex);
			}
		}
	}
}

package model;


import java.io.Serializable;
import java.time.LocalDate;

public class PersonImpl implements Person, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private final String name;
	private final LocalDate date;
	private final String surname;
	private final String fiscalCode;
	private final String place;
	private final String residency;
	private final String sex;
	protected PersonImpl(final String name, final String surname, final String fiscalCode,
			final LocalDate date, final String place, final String residency, final String sex) {
		this.name = name;
		this.surname = surname;
		this.fiscalCode = fiscalCode;
		this.date = date;
		this.place = place;
		this.residency = residency;
		this.sex = sex;
	}

	public String getName() {
		return this.name;
	}

	public String getSurname() {
		return this.surname;
	}

	public String getFiscalCode() {
		return this.fiscalCode;
	}

	public LocalDate getBirthdayDate() {
		return this.date;
	}

	public String getResidency() {
		return this.residency;
	}

	public String getBirthPlace() {
		return this.place;
	}

	public String getSex() {
		return this.sex;
	}

	public static class Builder {
		private String bName;
		private String bSurname;
		private String bFiscalCode;
		private LocalDate bDate;
		private String bSex;
		private String bBirthPlace;
		private String bResidencyPlace;

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

		public Builder birthPlace(final String birthPlace) {
			this.bBirthPlace = birthPlace;
			return this;
		}

		public Builder residency(final String residencyPlace) {
			this.bResidencyPlace = residencyPlace;
			return this;
		}

		public Builder sex(final String sex) {
			this.bSex = sex;
			return this;
		}

		public Person build() throws IllegalStateException {
			if (this.bName.equals("") || this.bSurname.equals("") || this.bDate == null || this.bResidencyPlace.equals("")
					|| this.bBirthPlace.equals("") || this.bFiscalCode.equals("") || this.bSex.equals("")) {
				throw new IllegalStateException("Complete all fields");
			} else {
				return new PersonImpl(this.bName, this.bSurname, this.bFiscalCode, this.bDate, this.bBirthPlace, this.bResidencyPlace, this.bSex);
			}
		}
	}


}

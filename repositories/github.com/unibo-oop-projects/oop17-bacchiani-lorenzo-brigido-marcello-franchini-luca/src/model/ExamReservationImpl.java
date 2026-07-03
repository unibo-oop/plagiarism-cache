package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import utilities.Exam;

public class ExamReservationImpl implements ExamReservation, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final LocalDate date;
	private final LocalTime time;
	private final Person p;
	private final Exam e;
	private final Worker pe;

	private ExamReservationImpl(final LocalDate date, final LocalTime time, final Person p, final Exam e, final Worker pe) {
		this.date = date;
		this.time = time;
		this.p = p;
		this.e = e;
		this.pe = pe;
	}

	public LocalDate getDate() {
		return this.date;
	}

	public LocalTime getHour() {
		return this.time;
	}

	public Person getPatient() {
		return this.p;
	}

	public Exam getExam() {
		return this.e;
	}

	public Worker getStaff() {
		return this.pe;
	}

	public static class Builder {
		private LocalDate bDate;
		private LocalTime bTime;
		private Person bPersona;
		private Worker bPersonale;
		private Exam bExam;

		public Builder date(final LocalDate date) {
			this.bDate = date;
			return this;
		}

		public Builder time(final LocalTime time) {
			this.bTime = time;
			return this;
		}


		public Builder patient(final Person persona) {
			this.bPersona = persona;
			return this;
		}

		public Builder staff(final Worker personale) {
			this.bPersonale = personale;
			return this;
		}

		public Builder exam(final Exam e) {
			this.bExam = e;
			return this;
		}

		public ExamReservation build() throws IllegalStateException {
			if (this.bDate == null || this.bTime == null || this.bPersona == null || this.bPersonale == null || this.bExam == null) {
				throw new IllegalStateException("Complete all fields");
			} else {
				return new ExamReservationImpl(this.bDate, this.bTime, this.bPersona, this.bExam, this.bPersonale);
			}
		}
	}
}

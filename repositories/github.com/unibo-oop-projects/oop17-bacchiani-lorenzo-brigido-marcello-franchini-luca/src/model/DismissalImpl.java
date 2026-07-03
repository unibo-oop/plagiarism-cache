package model;

import java.io.Serializable;
import java.time.LocalDate;

public class DismissalImpl implements Dismissal, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final LocalDate date;
	private final String reason;
	private final Worker p;

	private DismissalImpl(final LocalDate date, final String reason, final Worker p) {
		this.date = date;
		this.reason = reason;
		this.p = p;
	}
	public LocalDate getDate() {
		return this.date;
	}

	public String getReason() {
		return this.reason;
	}

	public Worker getStaff() {
		return this.p;
	}

	public static class Builder {

		private LocalDate bDate;
		private String bReason;
		private Worker bStaff;

		public Builder date(final LocalDate date) {
			this.bDate = date;
			return this;
		}

		public Builder reason(final String reason) {
			this.bReason = reason;
			return this;
		}

		public Builder staff(final Worker p) {
			this.bStaff = p;
			return this;
		}

		public DismissalImpl build() {
			if (this.bDate == null || this.bReason.equals("") || this.bStaff == null) {
				throw new IllegalStateException("Complete all fields");
			} else {
				return new DismissalImpl(this.bDate, this.bReason, this.bStaff);
			}
		}
	}

}

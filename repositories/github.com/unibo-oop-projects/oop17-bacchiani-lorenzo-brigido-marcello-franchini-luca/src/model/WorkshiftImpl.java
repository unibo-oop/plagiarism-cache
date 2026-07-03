package model;

import java.io.Serializable;
import java.time.LocalTime;

import utilities.Department;


public class WorkshiftImpl implements Workshift, Serializable {

     /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private final Department r;
    private final LocalTime startingTime;
    private final LocalTime endTime;
    private String state;
    private final Worker p;
    private WorkshiftImpl(final Department r, final LocalTime start, final LocalTime end, final Worker p) {
        this.r = r;
        this.p = p;
        this.startingTime = start;
        this.endTime = end;
        this.state = "Attivo";
    }

    public Department getDepartment() {
      return this.r;
	}

	public LocalTime getStartingTime() {
		return this.startingTime;
	}

	public void changeState() {
		this.state = "Disattivo";
	}

	public String getState() {
		return this.state;
	}

	public LocalTime getEndTime() {
		return this.endTime;
	}

	public Worker getWorker() {
		return this.p;
	}

	public static class Builder {
		private Department bRep;
		private LocalTime bStart;
		private LocalTime bEnd;
		private Worker bStaff;

		public Builder department(final Department r) {
			this.bRep = r;
			return this;
		}

		public Builder start(final LocalTime start) {
			this.bStart = start;
			return this;
		}

		public Builder end(final LocalTime end) {
			this.bEnd = end;
			return this;
		}

		public Builder staff(final Worker p) {
			this.bStaff = p;
			return this;
		}

		public Workshift build() {
			if (this.bRep == null || this.bStart == null || this.bEnd == null || this.bStaff == null) {
				throw new IllegalStateException("Complete all fields");
			} else {
				return new WorkshiftImpl(this.bRep, this.bStart, this.bEnd, this.bStaff);
			}
		}
	}
}

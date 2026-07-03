package model.Implementations;

import java.io.Serializable;
import java.time.Duration;
import java.util.Calendar;

import model.Interfaces.EmployeeModel;
import model.Interfaces.ShiftModel;

public class ShiftImplementation implements ShiftModel, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8216980089517499852L;
	private Calendar data;
	private Calendar start;
	private Calendar end;
	private String description;
	private EmployeeModel employee;

	public ShiftImplementation(Calendar data, String description, EmployeeModel employee) {
		this.data = data;
		this.description = description;
		this.employee = employee;

	}

	public ShiftImplementation(Calendar data, Calendar start, Calendar end, String description,
			EmployeeModel employee) {
		this(data, description, employee);
		this.start = start;
		this.end = end;
	}

	@Override
	public void setData(Calendar data) {
		this.data = data;
	}

	@Override
	public void setStartHour(Calendar start) {
		this.start = start;
	}

	@Override
	public void setEndHour(Calendar end) {
		this.end = end;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public void setEmployee(EmployeeModel employee) {
		this.employee = employee;
	}

	@Override
	public Calendar getData() {
		return this.data;
	}

	@Override
	public String getStartHour() {
		if (this.start == null) {
			return "NOT YET SETTED";
		}
		return this.start.get(Calendar.HOUR_OF_DAY) + ":" + this.start.get(Calendar.MINUTE);
	}

	@Override
	public String getEndHour() {
		if (this.end == null) {
			return "NOT YET SETTED";
		}
		return this.end.get(Calendar.HOUR_OF_DAY) + ":" + this.end.get(Calendar.MINUTE);
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public EmployeeModel getEmployee() {
		return this.employee;
	}

	@Override
	public int getMinutes() {
		if (this.end == null || this.start == null) {
			throw new NullPointerException("end or start not setted yet");
		}
		Long result = Duration.between(this.start.toInstant(), this.end.toInstant()).toMinutes();
		return result.intValue();
	}

	/**
	 * this method return true if given employee cf is equals to this cf
	 * employee AND there is already another shift in the same time
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShiftImplementation other = (ShiftImplementation) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!(data.getTime().compareTo(other.getData().getTime())==0))
			return false;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.getCF().equals(other.getEmployee().getCF()))
			return false;
		if (start == null) {
			if (other.end != null)
				return false;
		} else if (!(other.start.getTime().compareTo(this.start.getTime()) >= 0
				&& other.start.getTime().compareTo(this.end.getTime()) <= 0))
			if (end == null) {
				if (other.end != null)
					return false;
			} else if (!(other.end.getTime().compareTo(this.start.getTime()) >= 0
					&& other.end.getTime().compareTo(this.end.getTime()) <= 0))
				if (!(this.start.getTime().after(other.start.getTime())
						&& this.start.getTime().before(other.end.getTime())))
					return false;
		return true;
	}

}

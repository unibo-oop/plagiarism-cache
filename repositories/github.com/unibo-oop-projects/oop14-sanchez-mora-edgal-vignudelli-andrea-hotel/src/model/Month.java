package model;

import java.io.Serializable;

public enum Month implements Serializable {
	JANUARY(31), FEBRUARY(-1), MARCH(31), APRIL(30), MAY(31), JUNE(30), JULY(31), AUGUST(31), SEPTEMBER(30), OCTOBER(
			31), NOVEMBER(30), DECEMBER(31);

	private int days;

	private Month(int days) {
		this.days = days;
	}

	public int getDays(int year) {
		switch (this) {
		case FEBRUARY:
			return year % 400 == 0 || (year % 100 != 0 && year % 4 == 0) ? 29 : 28;
		default:
			return this.days;
		}
	}

}

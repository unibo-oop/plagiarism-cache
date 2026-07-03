package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import model.interfaces.ICatalog;

public class Catalog implements ICatalog, Serializable {
	private static final long serialVersionUID = -4573369305348309035L;
	private double day;
	private double BBOverPrice;
	private double halfBoardOverPrice;
	private double fullBoardOverPrice;
	private double childPercentage;
	private double premiumPercentage;
	private double suitePercentage;
	private double midSeasonOverPrice;
	private double highSeasonOverPrice;
	private int childAge;
	private int babyAge;

	private HashMap<Pair<LocalDate, LocalDate>, SeasonType> seasonMap;

	public Catalog() {
		seasonMap = new HashMap<>();
	}

	public double getDay() {
		return this.day;
	}

	public double getMidSeasonOverPrice() {
		return this.midSeasonOverPrice;
	}

	public double getHighSeasonOverPrice() {
		return this.highSeasonOverPrice;
	}

	public double getBBOverPrice() {
		return this.BBOverPrice;
	}

	public double getHalfBoardOverPrice() {
		return this.halfBoardOverPrice;
	}

	public double getFullBoardOverPrice() {
		return this.fullBoardOverPrice;
	}

	public int getChildAge() {
		return this.childAge;
	}

	public int getBabyAge() {
		return this.babyAge;
	}

	public double getChildPercentage() {
		return this.childPercentage;
	}

	public double getPremiumPercentage() {
		return this.premiumPercentage;
	}

	public double getSuitePercentage() {
		return this.suitePercentage;
	}

	public HashMap<Pair<LocalDate, LocalDate>, SeasonType> getSeasonMap() {
		return this.seasonMap;
	}

	public void setDay(final double day) {
		this.day = day;
	}

	public void setBBOverPrice(final double BBOverPrice) {
		this.BBOverPrice = 1 + (BBOverPrice / 100);
	}

	public void setHalfBoardOverPrice(final double halfBoardOverPrice) {
		this.halfBoardOverPrice = 1 + (halfBoardOverPrice / 100);
	}

	public void setFullBoardOverPrice(final double fullBoardOverPrice) {
		this.fullBoardOverPrice = 1 + (fullBoardOverPrice / 100);
	}

	public void setPremiumPercentage(final double premiumPercentage) {
		this.premiumPercentage = 1 + (premiumPercentage / 100);
	}

	public void setSuitePercentage(final double suitePercentage) {
		this.suitePercentage = 1 + (suitePercentage / 100);
	}

	public void setChildAge(final int childAge) {
		this.childAge = childAge;
	}

	public void setChildPercentage(final double childPercentage) {
		this.childPercentage = 1 - (childPercentage / 100);
	}

	public void setBabyAge(final int babyAge) {
		this.babyAge = babyAge;
	}

	public void setMidSeason(final double midPercentage) {
		this.midSeasonOverPrice = 1 + (midPercentage / 100);
	}

	public void setHighSeason(final double highPercentage) {
		this.highSeasonOverPrice = 1 + (highPercentage / 100);
	}

	public void setAll(final double highPercentage, final double midPercentage, final double day,
			final double BBOverPrice, final double halfBoardOverPrice, final double fullBoardOverPrice,
			final double premiumOverPrice, final double suiteOverPrice, final double childPercentage,
			final int childAge, final int babyAge) {
		this.setBabyAge(babyAge);
		this.setBBOverPrice(BBOverPrice);
		this.setChildAge(childAge);
		this.setChildPercentage(childPercentage);
		this.setFullBoardOverPrice(fullBoardOverPrice);
		this.setHalfBoardOverPrice(halfBoardOverPrice);
		this.setDay(day);
		this.setPremiumPercentage(premiumOverPrice);
		this.setSuitePercentage(suiteOverPrice);
		this.setMidSeason(midPercentage);
		this.setHighSeason(highPercentage);
	}

	public boolean checkConflict(LocalDate start, LocalDate end) {
		Set<Pair<LocalDate, LocalDate>> allList = seasonMap.keySet();
		for (Pair<LocalDate, LocalDate> pair : allList) {
			if ((start.isBefore(pair.getKey()) && end.isAfter(pair.getKey()))
					|| (start.isBefore(pair.getValue()) && end.isAfter(pair.getValue()))
					|| (start.isBefore(pair.getValue()) && end.isBefore(pair.getValue()))
					|| (start.isEqual(pair.getKey()) || start.isEqual(pair.getValue()) || end.isEqual(pair.getKey())
							|| end.isEqual(pair.getValue()))) {
				return false;
			}
		}
		return true;
	}

	public SeasonType getSeason(LocalDate date) {
		for (Entry<Pair<LocalDate, LocalDate>, SeasonType> et : seasonMap.entrySet()) {
			if ((date.isAfter(et.getKey().getKey()) && date.isBefore(et.getKey().getValue())
					|| (date.isEqual(et.getKey().getKey()) || date.isEqual(et.getKey().getValue())))) {
				return et.getValue();
			}
		}
		return null;
	}

}

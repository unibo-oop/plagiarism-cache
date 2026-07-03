package model.Implementations;

import java.io.Serializable;

import model.Interfaces.PlantModel;

public class PlantImplementation implements PlantModel, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7779863525087200048L;
	private String name;
	private String description;
	private int nDays;
	
	public PlantImplementation(String name, String description, int days) {
		this.name = name;
		this.description = description;
		this.nDays=days;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public void setNDays(int days) {
		this.setNDays(days);
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public int getNDays() {
		return this.nDays;
	}

}

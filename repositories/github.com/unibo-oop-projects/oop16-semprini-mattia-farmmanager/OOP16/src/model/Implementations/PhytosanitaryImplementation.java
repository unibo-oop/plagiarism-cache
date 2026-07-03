package model.Implementations;

import java.io.Serializable;

import model.Interfaces.PhytosanitaryModel;

public class PhytosanitaryImplementation implements PhytosanitaryModel, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4576774199359924235L;
	private String name;
	private String type;
	private String description;
	private int days;

	public PhytosanitaryImplementation(String name, String type, String description, int days) {
		this.name = name;
		this.type = type;
		this.description = description;
		this.days = days;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setType(String type) {
		this.type=type;
	}

	@Override
	public void setDescription(String descritpion) {
		this.description=descritpion;
	}

	@Override
	public void setExpire(int days) {
		this.days=days;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getType() {
		return this.type;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public int getExpire() {
		return this.days;
	}

}

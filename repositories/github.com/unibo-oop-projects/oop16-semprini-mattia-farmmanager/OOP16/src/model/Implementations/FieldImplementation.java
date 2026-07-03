package model.Implementations;

import java.io.Serializable;

import model.Interfaces.FieldModel;

public class FieldImplementation implements FieldModel, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5764613263748993307L;
	private String id;
	private int height;
	private int width;


	public FieldImplementation(String id, int height, int width) {
		this.id = id;
		this.height = height;
		this.width = width;
	}

	@Override
	public void setFieldID(String id) {
		this.id = id;

	}


	@Override
	public void setSize(int height, int width) {
		this.height = height;
		this.width = width;

	}

	@Override
	public String getFieldID() {
		return this.id;
	}

	@Override
	public Pair<Integer, Integer> getSize() {
		return new Pair<Integer, Integer>(this.height, this.width);
	}


}

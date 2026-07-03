package model.Interfaces;

import model.Implementations.Pair;

public interface FieldModel {
	
	/**
	 * this method is used to set the id of the Field
	 * @param id
	 */
	public void setFieldID(String id);
	
	/**
	 * this method is used to set the size of the field in height and width
	 * @param height
	 * @param width
	 */
	
	public void setSize(int height, int width);
	
	/**
	 * This method is used to get the id of field
	 */
	public String getFieldID();
	
	/**
	 * This method is used to get the size in n. of plants of the field
	 */
	public Pair<Integer, Integer> getSize();
}

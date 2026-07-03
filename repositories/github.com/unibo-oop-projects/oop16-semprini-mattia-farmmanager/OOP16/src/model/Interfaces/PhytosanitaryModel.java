package model.Interfaces;

public interface PhytosanitaryModel {
	
	/**
	 * this method is used to set the name of this Phytosanitary product
	 * @param name
	 */
	
	public void setName(String name);
	
	/**
	 * this method is used to set the type of this Phytosanitary product
	 * @param type
	 */
	
	public void setType(String type);
	
	/**
	 * this method is used to set the description of this Phytosanitary product
	 * @param descritpion
	 */
	
	public void setDescription(String descritpion);
	
	/**
	 * this method is used to set the time in day after which is possible to harvest the cultivation
	 * @param days
	 */
	
	public void setExpire(int days);
	
	/**
	 * this method is used to get the name of the product
	 * @return name
	 */
	
	public String getName();
	
	/**
	 * this method is used to get the type of the product
	 * @return type
	 */
	
	public String getType();
	
	/**
	 * this method is used to get the description of the product
	 * @return description
	 */
	
	public String getDescription();
	
	/**
	 * this method is used to get the time in day after which is possible to harvest the cultivation
	 * @param days
	 */
	
	public int getExpire();
}

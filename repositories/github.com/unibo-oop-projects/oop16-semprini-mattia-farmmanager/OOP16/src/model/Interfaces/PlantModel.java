package model.Interfaces;

public interface PlantModel {
	
	
	/**
	 * this method is used to set the name of the plant
	 * @param name
	 */
	
	public void setName(String name);
	
	/**
	 * this method is used to set the description of the plant
	 * @param description
	 */
		
	public void setDescription(String description);
	
	/**
	 * this method is used to set the nofdays after which the plant is fully grown
	 * @param days
	 */
	
	public void setNDays(int days);
	
	/**
	 * this method is used to get the name of the plant
	 * @return name
	 */
	
	public String getName();
	
	/**
	 * this method is used to get the description of the plant
	 * @return name
	 */
	
	public String getDescription();
	
	/**
	 * this method is used to get the nofdays after which the plant is fully grown
	 * @return name
	 */
	
	public int getNDays();
	
}

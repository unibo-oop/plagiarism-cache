package model.Interfaces;

public interface SupplierModel {
	
	/**
	 * this method is used to set the name of the supplier
	 *
	 * @param name
	 */
	
	public void setName(String name);
	
	/**
	 * this method is used to set the address of the supplier
	 *
	 * @param address
	 */
	
	public void setAddress(String address);
	
	/**
	 * this method is used to set the telephone of the supplier
	 *
	 * @param telephone
	 */
	
	public void setTelephone(String telephone);
	
	/**
	 * this method is used to get the name of the supplier
	 * @return String
	 */
	
	public String getName();
	
	/**
	 * this method is used to get the address of the supplier
	 * @return String
	 */
	
	public String getAddress();
	
	/**
	 * this method is used to get the telephone of the supplier
	 * @return int
	 */
	
	public String getTelephone();
}

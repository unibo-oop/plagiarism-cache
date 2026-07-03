package model.Interfaces;

public interface CustomerModel {
	/**
	 * this method is used to set the name of the customer
	 * @param name
	 */

	public void setName(String name);
	/**
	 * this method is used to set the address of the customer
	 * @param address
	 */
	
	public void setAddress(String address);
	
	/**
	 * this method is used to set the telephone of the customer
	 * @param telehone
	 */
	
	public void setTelephone(String telehone);
	
	/**
	 * this method is used to get the name of the customer
	 * @return String name
	 */
	
	public String getName();
	
	/**
	 * this method is used to get the address of the customer
	 * @return string address
	 */
	
	public String getAddress();
	
	/**
	 * this method is used to get the telephone of the customer
	 * @return
	 */
	
	public String getTelephone();
	
}

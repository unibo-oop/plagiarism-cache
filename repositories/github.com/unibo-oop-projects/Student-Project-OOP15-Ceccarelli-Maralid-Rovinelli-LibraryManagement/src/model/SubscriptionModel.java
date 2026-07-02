package model;

public interface SubscriptionModel {
	

	/**
	 * this method return a name in subscription
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * this method return the surname in subscription
	 * 
	 * @return
	 */
	public String getSurname();

	/**
	 * this method return the type
	 * 
	 * @return
	 */
	public String getType();

	/**
	 * This method returns how many books he bought that person
	 * 
	 * @return
	 */
	public int getBook();

	/**
	 * this method set a name for the subscription
	 * 
	 * @param name
	 */
	public void setName(String name);

	/**
	 * this method set a surname in subscription
	 * 
	 * @param surname
	 */
	public void setSurname(String surname);

	/**
	 * this method set a type of the subscription
	 * 
	 * @param type
	 */
	public void setType(String type);

	/**
	 * how many books he bought
	 * 
	 * @param book
	 */
	public void addBook(int book);
}

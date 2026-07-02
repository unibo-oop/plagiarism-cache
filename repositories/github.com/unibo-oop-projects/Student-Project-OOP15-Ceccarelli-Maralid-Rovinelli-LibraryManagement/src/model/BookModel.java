/**
 * 
 */
package model;

/**
 * @author Rovinelli
 *
 */
public interface BookModel {
	/**
	 * this method return the name of book
	 * 
	 * @return String
	 */
	public String getTitle();

	/**
	 * this method return the name of Author
	 * 
	 * @return String
	 */
	public String getAuthor();

	/**
	 * this method return the genre of library
	 * 
	 * @return
	 */
	public String getLiteraryGenre();

	/**
	 * This method returns the year of publication
	 * 
	 * @return String
	 */
	public int getyearOfPublication();

	/**
	 * this method return the price of book
	 * 
	 * @return
	 */
	public double getPrice();

	/**
	 * this method set a new title
	 * 
	 * @param title
	 */
	public void setTitle(String title);

	/**
	 * this method set a new author
	 * 
	 * @param author
	 */
	public void setAuthor(String author);

	/**
	 * this method set a new genre
	 * 
	 * @param genre
	 */
	public void setLiteraryGenre(String genre);

	/**
	 * this method set a new year of pubblication
	 * 
	 * @param YOP
	 */
	public void setYearOfPublication(int YOP);

	/**
	 * this method set a new price for the book
	 * 
	 * @param price
	 */
	public void setPrice(double price);

}

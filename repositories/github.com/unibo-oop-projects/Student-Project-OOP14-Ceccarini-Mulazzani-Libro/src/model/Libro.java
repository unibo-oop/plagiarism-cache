package model;

import java.io.Serializable;
/**
 * 
 * @author Chiara Ceccarini
 *
 */
public class Libro implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5192478245223549155L;
	private String title;
	private String author;
	private int year;
	private String editor;
	private String isbn;
	private double price;
	private int sold;
	private int copy;
	/**
	 * 
	 * @param fields are the fields of the Book to add
	 */
	public Libro(final String... fields) {
		this.sold = 0;
		for (int i = 0; i < fields.length; i++) {
			switch (i) {
				case 0: 
					this.title = fields[i]; 
					break;
				case 1: 
					this.author = fields[i]; 
					break;
				case 2: 
					this.year = Integer.parseInt(fields[i]); 
					break;
				case 3: 
					this.editor = fields[i]; 
					break;
				case 4: 
					this.isbn = fields[i]; 
					break;
				case 5: 
					this.price = Double.parseDouble(fields[i]); 
					break;
				case 6: 
					this.copy = Integer.parseInt(fields[i]);
					break;
				default:
					break;		
					}									
			}
	}	
	/**
	 * 
	 * @return the Title of the book
	 */
	public String getTitle() {
		return this.title;
	}
	/**
	 * 
	 * @return the author of the book 
	 */
	public String getAuthor() {
		return this.author;
	}
	/**
	 * 
	 * @return the year of the book
	 */
	public int getYear() {
		return this.year;
	}
	/**
	 * 
	 * @return the Editor of the book
	 */
	public String getEditor() {
		return this.editor;
	}
	/**
	 * 
	 * @return the ISBN of the book
	 */
	public String getISBN() {
		return this.isbn;
	}
	/**
	 * 
	 * @return the price of the book
	 */
	public double getPrice() {
		return this.price;
	}
	/**
	 * 
	 * @return the number of sales
	 */
	public int getNSales() {
		return this.sold;
	}
	/**
	 * 
	 * @return the number of copies
	 */
	public int getNCopy() {
		return this.copy;
	}
	/**
	 * 
	 * @param ntitle is the title to set
	 */
	public void setTitle(final String ntitle) {
		this.title = ntitle;	
	}
	/**
	 * 
	 * @param nauthor is the author to set
	 */
	public void setAuthor(final String nauthor) {
		this.author = nauthor;
	}
	/**
	 * 
	 * @param nyear is the new year to set
	 */
	public void setYear(final int nyear) {
		this.year = nyear;
	}
	/**
	 * 
	 * @param neditor is the new name of the editor
	 */
	public void setEditor(final String neditor) {
		this.editor = neditor;
	}
	/**
	 * 
	 * @param nisbn is the isbn to set
	 */
	public void setISBN(final String nisbn) {
		this.isbn = nisbn;
	}
	/**
	 * 
	 * @param nprice is the price to set
	 */
	public void setPrice(final double nprice) {
		this.price = nprice;
	}
	/**
	 * 
	 * @param ncopy are the starting copies of the book
	 */
	public void setNCopy(final int ncopy) {
		this.copy = ncopy;
	}
	/**
	 * 
	 * @param ncopy is the number of copies to add
	 */
	public void addCopy(final int ncopy) {
		this.copy += ncopy;
	}
	/**
	 * 
	 * @param ncopy is the number of copies to sell
	 */
	public void removeCopy(final int ncopy) {
		this.copy -= ncopy;
		this.sold += ncopy;
	}
	/**
	 * 
	 * @return the fields of the book
	 */
	public String[] getFields() {
		String[] str = new String[8];
		str[0] = this.title;
		str[1] = this.author;
		str[2] = "" + this.year;
		str[3] = this.editor;
		str[4] = this.isbn;
		str[5] = "" + this.price;
		str[6] = "" + this.copy;
		str[7] = "" + this.sold;		
		return str;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + copy;
		result = prime * result + ((editor == null) ? 0 : editor.hashCode());
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + sold;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + year;
		return result;
	} 

	
	@Override
	public boolean equals(final Object b) {
		if (this == b) {
			return true;
		}
		
		if (b == null) {
			return false;
		}
		
		final Libro lib = (Libro) b;
		final String[] fields = lib.getFields();

		for (int i = 0; i < fields.length; i++) {
			if (!fields[i].equals(this.getFields()[i])) {
				return false;
			}
		}
		return true;
	}
}

package model;

import java.io.Serializable;

/**
 * this class represented a book from the library The book is composed of the
 * title, author, year of publication,genre,and price.
 * 
 * @author rovi9
 *
 */

public class BookImpl implements BookModel, Serializable {

	private static final long serialVersionUID = 1L;
	private String title;
	private String author;
	private String literaryGenre;
	private int yearOfPublication;
	private double price;

	public BookImpl() {

	}

	public BookImpl(String title, String author, String literaryGenre, int yearOfPublication, double price) {
		this.title = title;
		this.author = author;
		this.literaryGenre = literaryGenre;
		this.yearOfPublication = yearOfPublication;
		this.price = price;

	}

	@Override
	public String getTitle() {
		return this.title;
	}

	@Override
	public String getAuthor() {
		return this.author;
	}

	@Override
	public String getLiteraryGenre() {
		return this.literaryGenre;
	}

	@Override
	public int getyearOfPublication() {
		return this.yearOfPublication;
	}

	@Override
	public void setTitle(String title) {
		this.title = title;

	}

	@Override
	public void setAuthor(String author) {
		this.author = author;

	}

	@Override
	public void setLiteraryGenre(String genre) {
		this.literaryGenre = genre;

	}

	@Override
	public void setYearOfPublication(int YOP) {
		this.yearOfPublication = YOP;

	}

	@Override
	public double getPrice() {
		return this.price;
	}

	@Override
	public void setPrice(double price) {
		this.price = price;
	}

}

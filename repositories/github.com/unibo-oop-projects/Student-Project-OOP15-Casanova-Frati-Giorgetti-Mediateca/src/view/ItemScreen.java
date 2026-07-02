package view;

import utils.ItemGenre;
import utils.Language;
import utils.TypeColor;
import utils.TypeItemInfo;
import view.ViewImpl.OtherItemFilter;

/**
 * inteface for item screen.
 *
 * @author Luca Giorgetti
 *
 */
public interface ItemScreen {
	/**
	 * Allows to get item info by passing the info type you want.
	 *
	 * @param info
	 *            info type you want
	 * @return information requested
	 */
	String getItemInfo(TypeItemInfo info);

	/**
	 * Sets all film field.
	 *
	 * @param title
	 *            the title of movie
	 * @param author
	 *            the author of movie
	 * @param manifacturer
	 *            the producer of movie
	 * @param year
	 *            the year of release of movie
	 * @param genre
	 *            the genre of movie
	 * @param duration
	 *            the duration of movie
	 * @param color
	 *            the color type of movie
	 * @param language
	 *            the language of movie
	 * @param copies
	 *            the number of copies of movie
	 */

	void setFilmField(String title, String author, String manifacturer,
			String year, ItemGenre genre, String duration, TypeColor color,
			Language language, int copies);

	/**
	 * Sets all book fields.
	 *
	 * @param title
	 *            the title of book
	 * @param author
	 *            the author of book
	 * @param manifacturer
	 *            the producer of book
	 * @param year
	 *            the year of release of book
	 * @param genre
	 *            the genre of book
	 * @param isbn
	 *            the isbn code of book
	 * @param language
	 *            the language of book
	 * @param release
	 *            the number of release of book
	 * @param copies
	 *            the number of copies of book
	 */
	void setBookField(String title, String author, String manifacturer,
			String year, ItemGenre genre, String isbn, Language language,
			int copies, int release);

	/**
	 * Return information about release and copies number.
	 *
	 * @param info2
	 *            the info type you want
	 * @return information requested
	 */
	String getItemInfo(OtherItemFilter info2);

}

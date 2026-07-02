package view;


/**
 * interface for showing item.
 *
 * @author Luca Giorgetti
 *
 */
public interface ItemShow {

	/**
	 * Sets all field for film item.
	 *
	 * @param titleS
	 *            the title of movie
	 * @param authorS
	 *            the author of movie
	 * @param manifacturerS
	 *            the producer of movie
	 * @param yearS
	 *            the year of release of movie
	 * @param genreS
	 *            the genre of movie
	 * @param reviewAvarageS
	 *            the avarage review of movie
	 * @param availabilityS
	 *            the availability of movie
	 * @param durationS
	 *            the duration of movie
	 * @param colorS
	 *            the color type of movie
	 * @param languageS
	 *            the language of movie
	 */
	void setFilmField(String titleS, String authorS, String manifacturerS,
			String yearS, String genreS, String reviewAvarageS,
			String availabilityS, String durationS, String colorS,
			String languageS);

	/**
	 * Sets all field for book item.
	 *
	 * @param titleS
	 *            the title of book
	 * @param authorS
	 *            the author of book
	 * @param manifacturerS
	 *            the producer of book
	 * @param yearS
	 *            the year of release of book
	 * @param genreS
	 *            the genre of book
	 * @param reviewAvarageS
	 *            the avarage review of book
	 * @param availabilityS
	 *            the availability of book
	 * @param isbnS
	 *            the isbn code of book
	 * @param languageS
	 *            the language of movie
	 */
	void setBookField(String titleS, String authorS, String manifacturerS,
			String yearS, String genreS, String reviewAvarageS,
			String availabilityS, String isbnS, String languageS);

	/**
	 * Starts item show.
	 *
	 * @param v
	 *            the name of the calling View
	 */
	void startItemShow(View v);

}

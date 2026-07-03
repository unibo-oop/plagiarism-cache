package it.lttply.view;

import java.util.List;

import javafx.util.Pair;

/**
 * The basic structure for a single movie view.
 */
public interface SingleMovieView {

    /**
     * Sets and shows the movie backdrop.
     *
     * @param backdropMovie
     *            Movie backdrop path
     */
    void setBackdrop(String backdropMovie);

    /**
     *
     *
     * @param posterMovie
     *            Movie poster path
     */
    void setPoster(String posterMovie);

    /**
     * Sets and shows the movie title.
     *
     * @param titleMovie
     *            title of the movie
     */
    void setTitle(String titleMovie);

    /**
     * Sets and shows the movie plot.
     *
     * @param plotMovie
     *            plot of the movie
     */
    void setPlot(String plotMovie);

    /**
     * Sets and shows the scene's title.
     *
     * @param titleMovie
     *            title of the movie
     */
    void setSceneTitle(String titleMovie);

    /**
     * Sets and shows the movie countries.
     *
     * @param countriesMovie
     *            the movie countries
     */
    void setCountries(String countriesMovie);

    /**
     * Sets and shows the movie tags.
     *
     * @param tagsMovie
     *            the tags of the movie
     */
    void setTags(String tagsMovie);

    /**
     * Sets and shows the movie size.
     *
     * @param sizeMovie
     *            size of the movie
     */
    void setSize(String sizeMovie);

    /**
     * Sets and shows the movie release date.
     *
     * @param dateMovie
     *            release date of the movie
     */
    void setReleaseDate(String dateMovie);

    /**
     * Sets and shows the movie rating.
     *
     * @param ratingMovie
     *            rating of the movie
     */
    void setRating(double ratingMovie);

    /**
     * Sets and shows the movie duration.
     *
     * @param duration
     *            duration of the movie
     */
    void setDuration(String duration);

    /**
     * Sets and show the movie phisical location.
     *
     * @param locationMovie
     *            the phisical location of the movie
     */
    void setPhisicalLocation(String locationMovie);

    /**
     * Sets and shows the movie producer.
     *
     * @param imagePath
     *            the producer image path
     * @param name
     *            the producer name
     */
    void setProducer(String imagePath, String name);

    /**
     * Sets and shows the cast.
     *
     * @param actorsMovie
     *            the name and the image path for each person
     */
    void setCast(List<Pair<String, String>> actorsMovie);

    /**
     * Shows errors with a notification.
     *
     * @param errorMessage
     *            text that must be shown
     */
    void showError(String errorMessage);

}
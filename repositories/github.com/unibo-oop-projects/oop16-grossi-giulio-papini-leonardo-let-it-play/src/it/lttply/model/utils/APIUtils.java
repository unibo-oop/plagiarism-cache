package it.lttply.model.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.NoRouteToHostException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

import org.apache.commons.io.IOUtils;
import it.lttply.model.domain.PictureFormat;

/**
 * Provides a singleton useful to query informations from
 * <a href="http://tmdb.org">TheMovieDB</a>.
 * <p>
 * This singleton is <i>lazy-initialized</i> and <i>thread-safe</i>.
 */
public final class APIUtils {
    private static final String ERR_NAME = "The name/format cannot be empty";
    private static final String ERR_QUERY = "The query cannot be empty";
    private static final String ERR_ID = "The ID must be greater than zero";
    private static final String ERR_NOTCOMPATIBLE = "The format and type specified are not compatible";
    private static final int STRLNGTH_DEF = 200;
    private static final int MAX_ERR = 15;

    private static final int MUST_WAIT_ERR = 429; // The 429 error is returned
                                                  // by the server
                                                  // when you make too many
                                                  // requests in a certain
                                                  // number of seconds

    private static final int NOT_FOUND_ERR = 404;

    private static final int WAITING_TIME_MS = 500;

    // BASE URLs
    private static final String IMG_BASE_URL = "http://image.tmdb.org/t/p/";
    private static final String MOVIE_SEARCH_BASE_URL = "https://api.themoviedb.org/3/search/movie?";
    private static final String MOVIE_DETAILS_BASE_URL = "https://api.themoviedb.org/3/movie/";

    private static final String TVSERIE_SEARCH_BASE_URL = "https://api.themoviedb.org/3/search/tv?";
    private static final String TVSERIE_DETAILS_BASE_URL = "https://api.themoviedb.org/3/tv/";

    // SYMBOLS and COMMON STRINGS
    private static final String API_KEY = "api_key";
    private static final String LANGUAGE_STR = "language";
    private static final String SEASON_STR = "season";
    private static final String EPISODE_STR = "episode";
    private static final String QUERY_STR = "query";
    private static final String CREDITS_STR = "credits";
    private static final String YEAR_STR_MOVIE = "primary_release_year";
    private static final String YEAR_STR_TV = "first_air_date_year";
    private static final String EQUALSTO_SYMBOL = "=";
    private static final String AMPERSAND = "&";
    private static final String ASCII_SPACE = "%20";
    private static final String QUESTION_MARK = "?";
    private static final String SLASH = "/";

    // UTILS
    /**
     * The default date pattern for this application.
     */
    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";

    /**
     * The default date for this application. Useful if you don't know what
     * {@link LocalDate} you can use as default.
     */
    public static final LocalDate DEFAULT_DATE = LocalDate.parse("1970-01-01",
            DateTimeFormatter.ofPattern(DEFAULT_DATE_PATTERN));

    /**
     * This enumerators provides references to the languages of the contents
     * offered in <a href="http://tmdb.org">TheMovieDB</a>.
     */
    public enum Languages {
        /**
         * The languages.
         */
        ENGLISH_US, ITALIAN_IT
    }

    private final Map<Languages, String> languages;
    private final List<String> apiKeys;

    private static class APIUtilsHolder {
        private static final APIUtils SINGLETON = new APIUtils(); // NOPMD
    }

    private APIUtils() {
        this.apiKeys = Collections.unmodifiableList(new LinkedList<>(
                Arrays.asList("30acda80a2099a558cbf40a5e453bdea", "e9489c540e9d7d603a7a72e8da0dcef9")));

        this.languages = new HashMap<>();
        this.languages.put(Languages.ENGLISH_US, "en-US");
    };

    /**
     * @return a instance of the singleton
     */
    public static APIUtils getInstance() {
        return APIUtilsHolder.SINGLETON;

    }

    private String getKey() {
        return this.apiKeys.get(new Random().nextInt(apiKeys.size()));
    }

    private String getResponse(final String query) throws InterruptedException, InternetConnectionException {
        Objects.requireNonNull(query);
        if (query.isEmpty()) {
            throw new IllegalArgumentException(ERR_QUERY);
        }
        URL url;
        try {
            url = new URL(query);
        } catch (MalformedURLException e1) {
            throw new InternetConnectionException(e1);
        }

        boolean ok = false;
        int errCount = 0;
        InputStream in = null;
        HttpURLConnection con = null;
        int res = 0;
        while (!ok) {
            try {
                con = (HttpURLConnection) url.openConnection();
                // con.setUseCaches(false);
                in = con.getInputStream();
                ok = true;
                errCount = 0;
            } catch (UnknownHostException | SocketTimeoutException | NoRouteToHostException err) {
                errCount++;
                if (errCount == MAX_ERR) {
                    throw new InternetConnectionException(err);
                }

                // Necessary to wait the server, useless for threads
                Thread.sleep(WAITING_TIME_MS);
            } catch (IOException e) {
                try {
                    if (con.getResponseCode() != MUST_WAIT_ERR) {
                        ok = true;
                        res = con.getResponseCode();
                    }
                } catch (IOException err) {
                    errCount++;
                    if (errCount == MAX_ERR) {
                        throw new InternetConnectionException(err);
                    }
                }
            }
        }

        String encoding = con.getContentEncoding();
        encoding = encoding == null ? "UTF-8" : encoding;
        String body = null;

        if (res != NOT_FOUND_ERR) {
            try {
                body = IOUtils.toString(in, encoding);
            } catch (IOException e) {
                body = null;
            }
        }

        return body;
    }

    /**
     * Searches for a movie basing on name and, optionally, the year.
     * 
     * @param name
     *            the movie's name
     * @param year
     *            an {@link Optional} {@link Integer} representing when the
     *            movie was shot
     * @return a {@link String} which represents the response in JSON format
     * @throws InternetConnectionException
     *             see the corresponding doc for details
     * @throws InterruptedException
     *             thrown when the waiting time has been altered in
     *             {@link APIUtils#getResponse(String)}
     * 
     */
    public Optional<String> getJSONMovieSearch(final String name, final Optional<Integer> year)
            throws InternetConnectionException, InterruptedException {
        Objects.requireNonNull(name);
        if (name.isEmpty()) {
            throw new IllegalArgumentException(ERR_NAME);
        }

        final StringBuilder builder = new StringBuilder(STRLNGTH_DEF);
        builder.append(MOVIE_SEARCH_BASE_URL).append(API_KEY).append(EQUALSTO_SYMBOL).append(this.getKey())
                .append(AMPERSAND).append(LANGUAGE_STR).append(EQUALSTO_SYMBOL)
                .append(this.languages.get(Languages.ENGLISH_US)).append(AMPERSAND).append(QUERY_STR)
                .append(EQUALSTO_SYMBOL).append(name.replace(" ", ASCII_SPACE));
        if (year.isPresent()) {
            builder.append(AMPERSAND).append(YEAR_STR_MOVIE).append(EQUALSTO_SYMBOL).append(year.get());
        }

        return Optional.ofNullable(getResponse(builder.toString()));

    }

    /**
     * Searches for a tv serie basing on name and, optionally, the year.
     * 
     * @param name
     *            the tv serie's name
     * @param year
     *            an {@link Optional} {@link Integer} representing the first air
     *            date of the tv serie
     * @return a {@link String} which represents the response in JSON format
     * @throws InternetConnectionException
     *             see the corresponding doc for details
     * @throws InterruptedException
     *             thrown when the waiting time has been altered in
     *             {@link APIUtils#getResponse(String)}
     * 
     * 
     */
    public Optional<String> getJSONTVSerieSearch(final String name, final Optional<Integer> year)
            throws InterruptedException, InternetConnectionException {
        Objects.requireNonNull(name);
        if (name.isEmpty()) {
            throw new IllegalArgumentException(ERR_NAME);
        }

        final StringBuilder builder = new StringBuilder(STRLNGTH_DEF);

        builder.append(TVSERIE_SEARCH_BASE_URL).append(API_KEY).append(EQUALSTO_SYMBOL).append(this.getKey())
                .append(AMPERSAND).append(LANGUAGE_STR).append(EQUALSTO_SYMBOL)
                .append(this.languages.get(Languages.ENGLISH_US)).append(AMPERSAND).append(QUERY_STR)
                .append(EQUALSTO_SYMBOL).append(name.replace(" ", ASCII_SPACE));
        if (year.isPresent()) {
            builder.append(AMPERSAND).append(YEAR_STR_TV).append(EQUALSTO_SYMBOL).append(year.get());
        }

        return Optional.ofNullable(getResponse(builder.toString()));
    }

    /**
     * Searches for a single movie basing on its identifier.
     * 
     * @param movieID
     *            an id which uniquely represents a movie
     * @return a {@link String} which represents the response in JSON format
     * @throws InternetConnectionException
     *             see the corresponding doc for details
     * @throws InterruptedException
     *             thrown when the waiting time has been altered in
     *             {@link APIUtils#getResponse(String)}
     * 
     */
    public Optional<String> getJSONMovieDetails(final String movieID)
            throws InterruptedException, InternetConnectionException {
        Objects.requireNonNull(movieID);
        if (movieID.isEmpty()) {
            throw new IllegalArgumentException(ERR_ID);
        }

        final StringBuilder builder = new StringBuilder(STRLNGTH_DEF);
        builder.append(MOVIE_DETAILS_BASE_URL).append(movieID).append(QUESTION_MARK).append(API_KEY)
                .append(EQUALSTO_SYMBOL).append(this.getKey()).append(AMPERSAND).append(LANGUAGE_STR)
                .append(EQUALSTO_SYMBOL).append(this.languages.get(Languages.ENGLISH_US));
        return Optional.ofNullable(getResponse(builder.toString()));
    }

    /**
     * Searches for a single tv serie basing on its identifier.
     * 
     * @param tvSerieID
     *            an id which uniquely represents a tv serie
     * @return a {@link String} which represents the response in JSON format
     * @throws InternetConnectionException
     *             see the corresponding doc for details
     * @throws InterruptedException
     *             thrown when the waiting time has been altered in
     *             {@link APIUtils#getResponse(String)}
     */
    public Optional<String> getJSONTVSerieDetails(final String tvSerieID)
            throws InterruptedException, InternetConnectionException {
        Objects.requireNonNull(tvSerieID);
        if (tvSerieID.isEmpty()) {
            throw new IllegalArgumentException(ERR_ID);
        }

        final StringBuilder builder = new StringBuilder(STRLNGTH_DEF);
        builder.append(TVSERIE_DETAILS_BASE_URL).append(tvSerieID).append(QUESTION_MARK).append(API_KEY)
                .append(EQUALSTO_SYMBOL).append(this.getKey()).append(AMPERSAND).append(LANGUAGE_STR)
                .append(EQUALSTO_SYMBOL).append(this.languages.get(Languages.ENGLISH_US));

        System.out.println(builder.toString());

        return Optional.ofNullable(getResponse(builder.toString()));
    }

    /**
     * Searches for a single season basing on its progressive number and on the
     * season to which it belongs.
     * 
     * @param tvSerieID
     *            an id which uniquely represents a tv serie
     * @param seasonNumber
     *            an id which uniquely represents the progressive number of that
     *            season
     * @return a {@link String} which represents the response in JSON format
     * @throws InternetConnectionException
     *             see the corresponding doc for details
     * @throws InterruptedException
     *             thrown when the waiting time has been altered in
     *             {@link APIUtils#getResponse(String)}
     * 
     */
    public Optional<String> getJSONTVSerieSeasonDetails(final String tvSerieID, final int seasonNumber)
            throws InterruptedException, InternetConnectionException {
        Objects.requireNonNull(tvSerieID);
        if (tvSerieID.isEmpty() || seasonNumber < 0) {
            throw new IllegalArgumentException(ERR_ID);
        }

        final StringBuilder builder = new StringBuilder(STRLNGTH_DEF);
        builder.append(TVSERIE_DETAILS_BASE_URL).append(tvSerieID).append(SLASH).append(SEASON_STR).append(SLASH)
                .append(seasonNumber).append(QUESTION_MARK).append(API_KEY).append(EQUALSTO_SYMBOL)
                .append(this.getKey()).append(AMPERSAND).append(LANGUAGE_STR).append(EQUALSTO_SYMBOL)
                .append(this.languages.get(Languages.ENGLISH_US));

        System.out.println(builder.toString());
        return Optional.ofNullable(getResponse(builder.toString()));
    }

    /**
     * Searches for a single episode basing on its progressive number, on the tv
     * serie and season to which it belongs.
     * 
     * @param tvSerieID
     *            an id which uniquely represents a tv serie
     * @param seasonNumber
     *            an id which uniquely represents the progressive number of that
     *            season contained into the tv serie
     * @param episodeNumber
     *            an id which uniquely represents the progressive number of that
     *            episode contained into the season
     * @return a {@link String} which represents the response in JSON format
     * @throws InternetConnectionException
     *             see the corresponding doc for details
     * @throws InterruptedException
     *             thrown when the waiting time has been altered in
     *             {@link APIUtils#getResponse(String)}
     * 
     */
    public Optional<String> getJSONTVSerieEpisodeDetails(final String tvSerieID, final int seasonNumber,
            final int episodeNumber) throws InterruptedException, InternetConnectionException {
        Objects.requireNonNull(tvSerieID);
        if (tvSerieID.isEmpty() || seasonNumber < 0 || episodeNumber < 0) {
            throw new IllegalArgumentException(ERR_ID);
        }
        final StringBuilder builder = new StringBuilder(STRLNGTH_DEF);
        builder.append(TVSERIE_DETAILS_BASE_URL).append(tvSerieID).append(SLASH).append(SEASON_STR).append(SLASH)
                .append(seasonNumber).append(SLASH).append(EPISODE_STR).append(SLASH).append(episodeNumber)
                .append(QUESTION_MARK).append(API_KEY).append(EQUALSTO_SYMBOL).append(this.getKey()).append(AMPERSAND)
                .append(LANGUAGE_STR).append(EQUALSTO_SYMBOL).append(this.languages.get(Languages.ENGLISH_US));
        System.out.println(builder.toString());
        return Optional.ofNullable(getResponse(builder.toString()));

    }

    /**
     * Searches for the credits of the specified movie.
     * 
     * @param movieID
     *            and id which uniquely represents the movie
     * @return a {@link String} which represents the response in JSON format
     * @throws InternetConnectionException
     *             thrown if an error occurs while querying the online database
     * @throws InterruptedException
     *             thrown if the thread is interrupted
     */
    public Optional<String> getJSONMovieCredits(final String movieID)
            throws InterruptedException, InternetConnectionException {
        Objects.requireNonNull(movieID);
        if (movieID.isEmpty()) {
            throw new IllegalArgumentException(ERR_NAME);
        }

        final StringBuilder builder = new StringBuilder(STRLNGTH_DEF);
        builder.append(MOVIE_DETAILS_BASE_URL).append(movieID).append(SLASH).append(CREDITS_STR).append(QUESTION_MARK)
                .append(API_KEY).append(EQUALSTO_SYMBOL).append(this.getKey());
        return Optional.ofNullable(getResponse(builder.toString()));
    }

    /**
     * Searches for the credits of the specified movie.
     * 
     * @param tvSerieID
     *            and id which uniquely represents the movie
     * @return a {@link String} which represents the response in JSON format
     * @throws InternetConnectionException
     *             thrown if an error occurs while querying the online database
     * @throws InterruptedException
     *             thrown if the thread is interrupted
     */
    public Optional<String> getJSONTVSerieCredits(final String tvSerieID)
            throws InterruptedException, InternetConnectionException {
        Objects.requireNonNull(tvSerieID);
        if (tvSerieID.isEmpty()) {
            throw new IllegalArgumentException(ERR_NAME);
        }

        final StringBuilder builder = new StringBuilder(STRLNGTH_DEF);
        builder.append(TVSERIE_DETAILS_BASE_URL).append(tvSerieID).append(SLASH).append(CREDITS_STR)
                .append(QUESTION_MARK).append(API_KEY).append(EQUALSTO_SYMBOL).append(this.getKey());

        return Optional.ofNullable(getResponse(builder.toString()));
    }

    /**
     * Composes an {@link URL} from which is it possible to download the image.
     * 
     * @param name
     *            the name of the image retrieved from the JSON parsing (with
     *            the final extension)
     * @param format
     *            the {@link PictureFormat} which represents the resolution of
     *            the image
     * @param type
     *            the type of the media that needs the full {@link URL} of its
     *            image. This is useful to check if the specified
     *            {@link PictureFormat} is compatible.
     * @return the final {@link URL} which represents the remote location of
     *         that image
     * @throws MalformedURLException
     *             see the inherited doc for details
     */
    public URL getURLImage(final String name, final PictureFormat format, final PictureType type)
            throws MalformedURLException {
        if (name.isEmpty() || format == null || type == null) {
            throw new IllegalArgumentException(ERR_NAME);
        }

        switch (type) {
        case IMAGE_BACKDROP:
            if (!format.equals(PictureFormat.W_300) && !format.equals(PictureFormat.W_780)
                    && !format.equals(PictureFormat.W_HD_READY) && !format.equals(PictureFormat.ORIGINAL)) {
                throw new IllegalArgumentException(ERR_NOTCOMPATIBLE);
            }
            break;

        case IMAGE_POSTER:
            if (!format.equals(PictureFormat.W_45) && !format.equals(PictureFormat.W_92)
                    && !format.equals(PictureFormat.W_154) && !format.equals(PictureFormat.W_185)
                    && !format.equals(PictureFormat.W_342) && !format.equals(PictureFormat.W_500)
                    && !format.equals(PictureFormat.W_780) && !format.equals(PictureFormat.ORIGINAL)) {
                throw new IllegalArgumentException(ERR_NOTCOMPATIBLE);
            }
            break;
        case IMAGE_PROFILE_PHOTO:
            if (!format.equals(PictureFormat.W_45) && !format.equals(PictureFormat.W_185)
                    && !format.equals(PictureFormat.H_632) && !format.equals(PictureFormat.ORIGINAL)) {
                throw new IllegalArgumentException(ERR_NOTCOMPATIBLE);
            }
            break;
        default:
            throw new IllegalArgumentException();
        }

        return new URL(new StringBuilder().append(IMG_BASE_URL).append(format.getValue()).append(SLASH).append(name)
                .toString());
    }

}

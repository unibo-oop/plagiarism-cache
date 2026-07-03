package it.lttply.model;

import static it.lttply.model.domain.managers.SQLiteAdvanced.getDBManager;

import java.sql.SQLException;

import it.lttply.model.domain.Movie;
import it.lttply.model.domain.MovieConcrete.MovieBuilderConcrete;
import it.lttply.model.domain.Source;

/**
 * This class permits to manage a single {@link Movie.
 */
public final class SingleMovieModel implements SingleMediaModel<Movie> {

    private static final String ERR_NOT_SUPPORTED = "This feature isn't still supported yet!";
    private Movie movie;
    private final String idMovie;

    /**
     * Constructs a new instance of {@link SingleMovieModel}.
     * 
     * @param idMovie
     *            the id of the movie to be retrieved
     */
    public SingleMovieModel(final String idMovie) {
        this.idMovie = idMovie;
        this.movie = null;
    }

    @Override
    public void reload(final Source source, final boolean overwrite) throws SQLException {
        if (source.equals(Source.SECONDARY)) {
            throw new UnsupportedOperationException(ERR_NOT_SUPPORTED);
        }

        this.movie = getDBManager().getMovie(this.idMovie);

    }

    @Override
    public Movie get() {
        if (movie == null) {
            throw new IllegalStateException();
        }
        return new MovieBuilderConcrete().buildFrom(this.movie);
    }
}

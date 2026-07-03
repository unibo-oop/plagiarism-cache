package it.lttply.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import it.lttply.model.HomeMediaModel;
import it.lttply.model.HomeMediaModelConcrete;
import it.lttply.model.domain.Source;
import it.lttply.model.utils.ThreadException;
import it.lttply.view.View;
import it.lttply.view.ViewApp;
import javafx.application.Platform;

/**
 * The main controller, which loads everything is needed to start the
 * application.
 */
public class HomeControllerConcrete implements HomeController {

    private static final String NOT_SUPPORTED = "Not supported";
    private static final String MOVIEFOLDERNOTSET = "You must set the movie tracking folder. Click on Settings.";

    private static final String MOVIESPATHNOTVALID = "The Movies path specified is not valid, try again!";
    private static final String ERR_OCCURRED = "An error occurred: ";

    private View view;
    private HomeMediaModel model;

    /**
     * Creates a new {@link HomeControllerConcrete} which is able to load the
     * main application components.
     */
    public HomeControllerConcrete() {
        try {
            this.model = new HomeMediaModelConcrete();
            this.view = new ViewApp();
            this.view.setObserver(this);
            this.view.initView();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void refresh(final RefreshType type) {
        new Thread(() -> {
            final Thread t1 = new Thread(() -> {
                refreshMovies(type);
                Thread.currentThread().interrupt();
            });

            /*
             * final Thread t2 = new Thread(() -> { // refreshTvSeries(type);
             * Thread.currentThread().interrupt(); });
             */

            try {
                t1.start();
                // t2.start();
                t1.join();
                // t2.join();
            } catch (final InterruptedException e) {
                Platform.runLater(() -> this.view.showError(ERR_OCCURRED + e.getMessage()));
            }
        }).start();
    }

    @Override
    public void refreshMovies(final RefreshType type) {
        new Thread(() -> {
            Platform.runLater(() -> {
                this.view.clearMovies();
                this.view.blockView();
            });

            try {
                if (type.equals(RefreshType.MINIMAL)) {
                    this.model.reloadMoviesOnly(Source.DATABASE, true);
                } else if (type.equals(RefreshType.SHALLOW)) {
                    this.model.reloadMoviesOnly(Source.SECONDARY, false);
                } else {
                    this.model.reloadMoviesOnly(Source.SECONDARY, true);
                }
            } catch (SQLException | InterruptedException | ThreadException | IOException e) {
                Platform.runLater(() -> {
                    this.view.unlockView();
                    this.view.showError(ERR_OCCURRED + e.getMessage());
                });
                return;
            } catch (IllegalStateException | IllegalArgumentException e) {
                Platform.runLater(() -> {
                    this.view.unlockView();
                    this.view.showError(MOVIEFOLDERNOTSET);
                });
                return;
            }

            this.model.getMovies(null).forEach(t -> {
                Platform.runLater(() -> {
                    if (t.getPoster().isPresent()) {
                        this.view.loadMoviesCover(t.getPoster().get().getPhysicalLocation().toString(),
                                t.getTitle() + " (" + t.getReleaseDate().getYear() + ")", t.getID());
                    } else {
                        this.view.loadMoviesCover(this.model.getDefaultPictureLocation(),
                                t.getTitle() + " (" + t.getReleaseDate().getYear() + ")", t.getID());
                    }
                });
            });

            Platform.runLater(() -> this.view.unlockView());

            Thread.currentThread().interrupt();
        }).start();

    }

    @Override
    public void refreshTvSeries(final RefreshType type) {
        // view.loadTvSeriesCover(null, "TESTO DI TEST");
        throw new UnsupportedOperationException(NOT_SUPPORTED);

    }

    @Override
    public String getTVSeriePath() {
        // return this.model.getTVSeriesTrackingFolder().equals("0") ? null :
        // this.model.getTVSeriesTrackingFolder();
        throw new UnsupportedOperationException(NOT_SUPPORTED);
    }

    @Override
    public String getMoviePath() {
        if (this.model.getMoviesTrackingFolder() == null || this.model.getMoviesTrackingFolder().equals("0")) {
            return "";
        } else {
            return this.model.getMoviesTrackingFolder();
        }
    }

    @Override
    public void setTVSeriePath(final String tvSeriesPath) {
        throw new UnsupportedOperationException(NOT_SUPPORTED);
        /*
         * try { if (tvSeriesPath == null || tvSeriesPath.isEmpty() ||
         * tvSeriesPath.trim().isEmpty()) {
         * this.model.setTVSeriesTrackingFolder(null); } else {
         * this.model.setTVSeriesTrackingFolder(new File(tvSeriesPath)); }
         * 
         * } catch (IOException e) { Platform.runLater(() ->
         * this.view.showError(TVSERIESPATHNOTVALID)); } catch (SQLException e)
         * { Platform.runLater(() -> this.view.showError(ERR_OCCURRED +
         * e.getMessage())); }
         */

    }

    @Override
    public void setMoviePath(final String moviesPath) {
        final Thread t = new Thread(() -> {
            Platform.runLater(() -> {
                this.view.clearMovies();
                this.view.blockView();
            });
            try {
                if (moviesPath == null || moviesPath.isEmpty() || moviesPath.trim().isEmpty()) {
                    this.model.setMovieTrackingFolder(null);
                } else {
                    this.model.setMovieTrackingFolder(new File(moviesPath));
                }

            } catch (IOException e) {
                Platform.runLater(() -> {
                    this.view.unlockView();
                    this.view.showError(MOVIESPATHNOTVALID);
                });
            } catch (SQLException e) {
                Platform.runLater(() -> {
                    this.view.unlockView();
                    this.view.showError(ERR_OCCURRED + e.getMessage());
                });
            }
            Platform.runLater(() -> this.view.unlockView());
        });

        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            Platform.runLater(() -> {
                this.view.unlockView();
                this.view.showError(ERR_OCCURRED + e.getMessage());
            });
        }
    }

    @Override
    public void searchMovie(final String filter) {
        Platform.runLater(() -> {
            this.view.blockView();
            this.view.clearMovies();
        });

        new Thread(() -> {
            this.model.getMovies(filter).forEach(movie -> {
                Platform.runLater(() -> {
                    if (movie.getPoster().isPresent()) {
                        this.view.loadMoviesCover(movie.getPoster().get().getPhysicalLocation().toString(),
                                movie.getTitle() + " (" + movie.getReleaseDate().getYear() + ")", movie.getID());
                    } else {
                        this.view.loadMoviesCover(this.model.getDefaultPictureLocation(),
                                movie.getTitle() + " (" + movie.getReleaseDate().getYear() + ")", movie.getID());
                    }
                });
            });
            Platform.runLater(() -> this.view.unlockView());
            Thread.currentThread().interrupt();
        }).start();
    }

    @Override
    public void searchTVSeries(final String filter) {
        throw new UnsupportedOperationException(NOT_SUPPORTED);

    }

}

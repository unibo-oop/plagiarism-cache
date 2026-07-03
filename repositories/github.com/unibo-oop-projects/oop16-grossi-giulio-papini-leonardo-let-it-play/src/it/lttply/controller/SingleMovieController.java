package it.lttply.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.swing.SwingUtilities;

import org.apache.commons.io.FileUtils;

import it.lttply.model.SingleMediaModel;
import it.lttply.model.SingleMovieModel;
import it.lttply.model.domain.Movie;
import it.lttply.model.domain.Source;
import it.lttply.model.domain.managers.EnvironmentStandard;
import it.lttply.model.utils.ThreadException;
import it.lttply.view.PlayerMedia;
import it.lttply.view.SingleMovieView;
import javafx.util.Pair;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;

/**
 * Manager for a single movie instance.
 */
public class SingleMovieController implements SingleMediaController {

    private static final int WAITING_TIME = 500;
    private final SingleMediaModel<Movie> model;
    private final SingleMovieView view;
    private static final String ERR_OCCURRED = "An error occurred: ";

    /**
     * Creates a new instance of {@link SingleMovieController}.
     *
     * @param idMovie
     *            the id of the movie to be managed
     * @param movieView
     *            the physical location of the movie
     */
    public SingleMovieController(final String idMovie, final SingleMovieView movieView) {
        model = new SingleMovieModel(idMovie);
        view = movieView;
    }

    @Override
    public void refresh(final RefreshType type) {
        try {
            model.reload(Source.DATABASE, false);
            final Movie tmp = model.get();

            view.setSceneTitle(tmp.getTitle().toUpperCase(Locale.ENGLISH));

            if (tmp.getBackdrop().isPresent()) {
                view.setBackdrop(tmp.getBackdrop().get().getPhysicalLocation().toString());
            }

            view.setTitle(tmp.getTitle());

            view.setReleaseDate(tmp.getReleaseDate().toString());
            if (tmp.getTags().isPresent()) {
                view.setTags(tmp.getTags().get().entrySet().stream().map(entry -> entry.getValue())
                        .collect(Collectors.joining(", ")));
            }
            view.setSize(FileUtils.byteCountToDisplaySize(tmp.getSize()));

            if (tmp.getCountries().isPresent()) {
                view.setCountries(
                        tmp.getCountries().get().stream().map(String::toString).collect(Collectors.joining(", ")));
            }

            view.setPhisicalLocation(tmp.getPhysicalLocation().toString());

            if (tmp.getPoster().isPresent()) {
                view.setPoster(tmp.getPoster().get().getPhysicalLocation().toString());
            }

            if (tmp.getCredits().isPresent()) {
                if (tmp.getCredits().get().getCreator().getPicture().isPresent()) {
                    view.setProducer(
                            tmp.getCredits().get().getCreator().getPicture().get().getPhysicalLocation().toString(),
                            tmp.getCredits().get().getCreator().getName());
                } else {
                    view.setProducer(null, tmp.getCredits().get().getCreator().getName());
                }

            }

            if (tmp.getRating().isPresent()) {
                view.setRating(tmp.getRating().get());
            }

            if (tmp.getCredits().isPresent()) {
                final List<Pair<String, String>> cast = new LinkedList<>();

                tmp.getCredits().get().getCast().forEach(t -> {

                    if (t.getPicture().isPresent()) {
                        cast.add(new Pair<String, String>(t.getPicture().get().getPhysicalLocation().toString(),
                                t.getName()));
                    } else {
                        cast.add(new Pair<String, String>(EnvironmentStandard.getDefaultPictureLocation().toString(),
                                t.getName()));
                    }

                });
                Collections.reverse(cast);
                view.setCast(cast);

                if (tmp.getOverview().isPresent()) {
                    view.setPlot(tmp.getOverview().get());
                } else {
                    view.setPlot("-");
                }
            }

        } catch (SQLException | InterruptedException | ThreadException | IOException e) {
            view.showError(ERR_OCCURRED + e.getMessage());
        }
    }

    @Override
    public void play() {
        new NativeDiscovery().discover();

        SwingUtilities.invokeLater(() -> {
            final PlayerMedia player = new PlayerMedia(model.get().getTitle(),
                    model.get().getPhysicalLocation().toString());

            player.play();

            new Thread(() -> {
                while (!player.isPlaying()) {
                    try {
                        Thread.sleep(WAITING_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                player.startRefresher();
            }).start();
        });

    }

}

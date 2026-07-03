package it.lttply.model.domain;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * It provides an interface who represents a container for a multimedial file.
 */
public interface MediaContainer extends FileContainer {
    /**
     * @return the univocal identification of the multimedial file
     */
    String getID();

    /**
     * @return an {@link Optional} {@link List} of tags which describe what are
     *         the covered topics of the multimedial file. For example: for a
     *         {@link Movie} or {@link TVSerie} they can be Adventure, Comedy,
     *         Drammatic, etc.
     * 
     */
    Optional<Map<String, String>> getTags();

    /**
     * @return an {@link Optional} overview (for instance a plot) of the
     *         multimedial file.
     */
    Optional<String> getOverview();

    /**
     * @return an {@link Optional} {@link Picture} which represents the Poster
     *         of the multimedial file.
     */
    Optional<Picture> getPoster();
}

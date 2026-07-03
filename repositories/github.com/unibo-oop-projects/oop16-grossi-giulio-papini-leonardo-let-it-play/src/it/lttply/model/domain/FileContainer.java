package it.lttply.model.domain;

import java.nio.file.Path;
import java.time.LocalDate;

/**
 * This interface is the base of the whole architecture. It provides the base to
 * build a simple container for a file independently from his nature.
 */
public interface FileContainer {
    /**
     * @return the title (or name) of the file.
     */
    String getTitle();

    /**
     * @return the size in bytes of the multimedial file.
     */
    int getSize();

    /**
     * @return the {@link LocalDate} that denotes when the file has been
     *         created.
     */
    LocalDate getReleaseDate();

    /**
     * @return the {@link Path} of the file on secondary memory.
     */
    Path getPhysicalLocation();
}

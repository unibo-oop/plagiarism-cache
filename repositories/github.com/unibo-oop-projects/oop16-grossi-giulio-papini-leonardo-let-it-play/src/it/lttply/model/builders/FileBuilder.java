package it.lttply.model.builders;

import java.nio.file.Path;
import java.time.LocalDate;
import it.lttply.model.domain.FileContainer;

/**
 * It provides an interface to create a builder of generic
 * {@link FileContainer}.
 * 
 * @param <T>
 *            the builder
 * @param <X>
 *            the type to build
 */
public interface FileBuilder<T, X> extends Builder<X> {
    /**
     * Sets the title of the file.
     * 
     * @param title
     *            the file's title
     * @return the <T> builder
     */
    T title(String title);

    /**
     * Sets the date of the file.
     * 
     * @param date
     *            the file's release date
     * @return the <T> builder
     */
    T releaseDate(LocalDate date);

    /**
     * Sets the physical location of the file.
     * 
     * @param path
     *            the file's location in secondary memory
     * @return the <T> builder
     */
    T physicalLocation(Path path);

    /**
     * Sets the size of the file.
     * 
     * @param size
     *            the file's size
     * @return the <T> builder
     */
    T size(int size);
}

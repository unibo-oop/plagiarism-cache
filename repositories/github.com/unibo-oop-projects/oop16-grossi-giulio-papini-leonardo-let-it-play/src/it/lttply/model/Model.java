package it.lttply.model;

import java.io.IOException;
import java.sql.SQLException;

import it.lttply.model.domain.Source;
import it.lttply.model.utils.ThreadException;

/**
 * Provides a structure to build a simple and basic model. All the models should
 * use a "caching strategy", it means that the informations (when possible) are
 * stored into the RAM instead of secondary memory (files on hard disk,
 * database, etc.), in order to speedup the retrieving operations.
 */
public interface Model {
    /**
     * Retrieves the informations from a {@link Source}.
     * 
     * @param source
     *            the {@link Source} from which the data must be retrieved
     * @param overwrite
     *            if this flag is <code>true</code> indicates that the
     *            informations retrieved must be overwritten to the informations
     *            already present or not if <code>false</code>
     * @throws SQLException
     *             thrown if an exception occurs while querying the database
     * @throws InterruptedException
     *             thrown if a {@link Thread} gets interrupted
     * @throws ThreadException
     *             thrown if an exception occurs when the thread/s is/are
     *             processing data
     * @throws IOException thrown if an exception occurs while reading/writing in seconday memory
     */
    void reload(Source source, boolean overwrite) throws SQLException, InterruptedException, ThreadException, IOException;
}

package it.trashwarecesena.trustalodesktopclient.repository.mapper.jooq;

import java.sql.Connection;
import java.util.Objects;

import org.jooq.SQLDialect;

import it.trashwarecesena.trustalodesktopclient.repository.Repository;
import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;

/**
 * A static factory to prevent any
 * {@link it.trashwarecesena.trustalodesktopclient.repository.adapter.PersistenceAdapter
 * PersistenceAdapter} from knowing details of JOOQ necessary to construct a
 * functional mapper.
 * <p>
 * Given the capabilities of the JOOQ library, it is necessary to just add a
 * factory method with the specific SQLDialect to instantiate a functioning
 * mapper.
 * 
 * @author Manuel Bonarrigo
 */
public final class JooqMapperFactory {

    private JooqMapperFactory() {
    }

    /**
     * Returns an instance of {@link PeopleJooqMapper} initiated to work over a
     * MySql database.
     * 
     * @param connection
     *            a {@link Connection} to such a database.
     * @return a {@link PeopleJooqMapper} primed to work over a MySql database.
     */
    public static PeopleJooqMapper createMySqlPeopleJooqMapper(final Connection connection) {
        return new PeopleJooqMapper(Objects.requireNonNull(connection, "The connection" + ErrorString.CUSTOM_NULL),
                                    SQLDialect.MYSQL);
    }

    /**
     * Returns an instance of {@link RequestsJooqMapper} initiated to work over a
     * MySql database.
     * 
     * @param connection
     *            a {@link Connection} to such a database.
     * @param repository
     *            a reference to dispatch the instance request over the whole system
     * @return a {@link RequestsJooqMapper} primed to work over a MySql database.
     */
    public static RequestsJooqMapper createMySqlRequestsJooqMapper(final Connection connection,
                                                                   final Repository repository) {
        return new RequestsJooqMapper(Objects.requireNonNull(connection, "The connection" + ErrorString.CUSTOM_NULL),
                SQLDialect.MYSQL, repository);
    }

    /**
     * Returns an instance of {@link DevicesJooqMapper} initiated to work over a
     * MySql database.
     * 
     * @param connection
     *            a {@link Connection} to such a database.
     * @param repository
     *            a reference to dispatch the instance request over the whole system
     * @return a {@link DevicesJooqMapper} primed to work over a MySql database.
     */
    public static DevicesJooqMapper createMySqlDevicesJooqMapper(final Connection connection,
                                                                 final Repository repository) {
        return new DevicesJooqMapper(Objects.requireNonNull(connection, "The connection" + ErrorString.CUSTOM_NULL),
                SQLDialect.MYSQL, repository);
    }
}

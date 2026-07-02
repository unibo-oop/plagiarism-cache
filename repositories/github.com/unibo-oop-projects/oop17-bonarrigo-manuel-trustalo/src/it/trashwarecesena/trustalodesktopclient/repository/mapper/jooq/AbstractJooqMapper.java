package it.trashwarecesena.trustalodesktopclient.repository.mapper.jooq;

import java.sql.Connection;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;

import it.trashwarecesena.trustalodesktopclient.repository.exception.BoundedReferenceException;
import it.trashwarecesena.trustalodesktopclient.repository.exception.DuplicateKeyValueException;
import it.trashwarecesena.trustalodesktopclient.repository.exception.NonExistentReferenceException;
import it.trashwarecesena.trustalodesktopclient.repository.query.criteria.QueryObject;
import it.trashwarecesena.trustalodesktopclient.repository.query.interpreter.Interpreter;
import it.trashwarecesena.trustalodesktopclient.repository.query.interpreter.MariaDbSqlInterpreter;
import it.trashwarecesena.trustalodesktopclient.utils.ErrorString;

/**
 * This class provides all the utility methods needed by every JOOQ mapper. The
 * mapper themselves are not in any way interchangeable, so this kind of
 * abstraction can be seen as a dynamic utility class.
 * 
 * @author Manuel Bonarrigo
 *
 */
public class AbstractJooqMapper {

    private final DSLContext context;

    /**
     * The instance of {@link Interpreter} shared by all JOOQ mappers, which enables
     * the classes to translate from a {@link QueryObject} to the appropriate query
     * language.
     */
    private final Interpreter sqlInterpreter;

    /**
     * Instantiate the resources needed by any kinf of JOOQ mapper.
     * 
     * @param connection
     *            the standard library Java {@link Connection}.
     * @param dialect
     *            member of a JOOQ enumeration which knows all the possible dialects
     *            for SQL.
     */
    protected AbstractJooqMapper(final Connection connection, final SQLDialect dialect) {
        this.context = DSL.using(connection, dialect);
        this.sqlInterpreter = new MariaDbSqlInterpreter();
    }

    /**
     * Retrieve the resource needed by the JOOQ library to pursue any single
     * operation upon the database.
     * 
     * @return a reference to a DSLContext.
     */
    protected DSLContext getContext() {
        return this.context;
    }

    /**
     * Fetches a JOOQ related Collection with all the Records which matched against
     * the filtering of the {@link QueryObject}.
     * 
     * @param filter
     *            a QueryObject with indications of what to retrieve from the
     *            database.
     * @return a Result{@literal <}Record{@literal >} containing all the records
     *         which matched the filtering of the {@link QueryObject}, all wrapped
     *         inside their own Record, all of which wrapped in a Result, which is a
     *         Collection implementation developed by JOOQ.
     */
    protected final Result<Record> executeSqlRetrieveStatement(final QueryObject filter) {
        return context.fetch(translateQueryObject(filter));
    }

    private String translateQueryObject(final QueryObject filter) {
        return sqlInterpreter.translate(filter);
    }

    private String extractFallaciousValue(final String complexMessage) {
        return complexMessage;
    }

    /**
     * Intercept a DataAccessException from JOOQ, throwing a
     * {@link DuplicateKeyValueException} instead.
     * 
     * @param klass
     *            the class mapped to the table which made the problem arise.
     * @param dax
     *            the original exception thrown by JOOQ.
     */
    protected final void manageDataAccessException(final Class<?> klass, final DataAccessException dax) {
        throw new DuplicateKeyValueException(
                ErrorString.buildDuplicateKeyValueExceptionMessage(klass, extractFallaciousValue(dax.getMessage())),
                dax);
    }

    /**
     * Throws a {@link NonExistentReferenceException} containing a message composed
     * by the given informations.
     * 
     * @param klass
     *            the class mapped to the table which made the problem arise.
     * @param missing
     *            the name of the field which made the problem arise.
     */
    protected final void manageMissingReferenceException(final Class<?> klass, final String missing) {
        throw new NonExistentReferenceException(ErrorString.buildMissingReferenceExceptionMessage(klass, missing));
    }

    /**
     * Throws a {@link BoundedReferenceException} containing a message composed
     * by the given informations.
     * 
     * @param klass
     *            the class mapped to the table which made the problem arise.

     */
    protected final void manageBoundedReferenceOnDeletionException(final Class<?> klass) {
        throw new BoundedReferenceException(klass.getSimpleName() + " can not be deleted since it is referenced by "
                + "other entities");
    }

    /**
     * Translate a boolean in a byte, due to the absence of such a primitive in some
     * kind of relational databases.
     * 
     * @param bool
     *            the boolean to translate
     * @return {@code 1} if the boolean is {@code true}, {@code 0} otherwise.
     */
    protected final byte translateBooleanToByte(final boolean bool) {
        return bool ? (byte) 1 : (byte) 0;
    }

    /**
     * Translate a byte in a boolean.
     * 
     * @param b
     *            the byte to translate
     * @return {@code true} if the byte is {@code 1}, {@code false} otherwise.
     */
    protected boolean translateByteToBoolean(final byte b) {
        return b == 0 ? false : true;
    }
}

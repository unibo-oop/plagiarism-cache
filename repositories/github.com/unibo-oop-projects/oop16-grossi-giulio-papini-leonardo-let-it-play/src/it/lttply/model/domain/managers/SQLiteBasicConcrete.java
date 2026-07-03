package it.lttply.model.domain.managers;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

import org.apache.commons.io.FilenameUtils;

import it.lttply.model.utils.ConsolePrinter;

/**
 * Provides a wrapper for the common {@link java.sql} operations (insert, edit,
 * remove, etc), and can be used to manage every SQLite database instance.
 */
public class SQLiteBasicConcrete implements SQLiteBasic {

    private static final String DB_FINAL_EXT = "db";
    private static final String ERR_DB_EMPTY_NOTVALID = "The specified database path is empty or not valid!";
    private static final String ERR_DB_NOTINIT = "The environment has not been initialized yet!";
    private static final String ERR_DB_ALREADYINIT = "The environment has already been initialized!";

    private final File databaseFullPath;
    private boolean initialized;

    /**
     * Constructs a new {@link SQLiteBasicConcrete} instance. Only
     * {@link #DB_FINAL_EXT} database file are supported.
     * 
     * @param databaseFullPath
     *            the database to be managed
     */
    public SQLiteBasicConcrete(final String databaseFullPath) {
        Objects.requireNonNull(databaseFullPath);
        if (databaseFullPath.isEmpty()) {
            throw new IllegalStateException(ERR_DB_EMPTY_NOTVALID);
        }

        this.initialized = false;
        this.databaseFullPath = new File(databaseFullPath);

    }

    private boolean dbExistsAndIsValidFile() {
        return this.databaseFullPath.exists() && this.databaseFullPath.isFile()
                && FilenameUtils.getExtension(databaseFullPath.toString()).equals(DB_FINAL_EXT);
    }

    private void checkInitialization() {
        if (!initialized) {
            throw new IllegalStateException(ERR_DB_NOTINIT);
        }
    }

    @Override
    public Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:" + this.getDBPath());
    }

    @Override
    public void initialize() {
        ConsolePrinter.getPrinter().printlnProcedureStarted("Initializing database...");
        if (initialized) {
            throw new IllegalStateException(ERR_DB_ALREADYINIT);
        }

        if (!this.dbExistsAndIsValidFile()) {
            throw new IllegalStateException(ERR_DB_EMPTY_NOTVALID);
        }

        initialized = true;

        ConsolePrinter.getPrinter().printlnSuccess("Database initialized correctly.");
    }

    @Override
    public void setInsertRemove(final String query) throws SQLException {
        this.checkInitialization();

        try (Connection conn = this.connect(); PreparedStatement smt = conn.prepareStatement(query)) {
            smt.executeUpdate();
        }
    }

    @Override
    public String getDBPath() {
        return this.databaseFullPath.getPath();
    }

}

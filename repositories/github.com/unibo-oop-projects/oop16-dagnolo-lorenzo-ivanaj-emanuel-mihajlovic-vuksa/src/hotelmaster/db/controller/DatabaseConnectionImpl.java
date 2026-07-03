package hotelmaster.db.controller;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.sqlite.SQLiteConfig;

/**
 * Manages the connectivity with the database.
 */
public final class DatabaseConnectionImpl implements DatabaseConnection {

    private static DatabaseConnectionImpl db;
    private Connection conn = null;
    private static boolean isCreatingDB = false;
    private final String url = "jdbc:sqlite:";
    private final String dbName = "hotelmaster.db";
    private final String driver = "org.sqlite.JDBC";
    private final String dbPath = System.getProperty("user.home") + File.separator + dbName;
    private final File f = new File(dbPath);

    /**
     * Solution to enable foreign keys for the dbms that we are going to
     * use: code taken by
     * http://code-know-how.blogspot.it/2011/10/how-to-enable-foreign-keys-in-sqlite3.html
     */
    private DatabaseConnectionImpl() {
        if (!f.exists() && !isCreatingDB) {
            System.out.println("Database " + this.dbName + " doesn't exist, it will be created in" + this.dbPath);
            isCreatingDB = true;
            new DatabaseBuilder().createDatabase();
        }
        try {
            Class.forName(driver).newInstance();
            SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            this.conn = DriverManager.getConnection(url + dbPath, config.toProperties());
            System.out.println("Connection to the database " + dbName + " established");

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * @return the Singleton of this class.
     */
    public static DatabaseConnectionImpl getSingleton() {
        if (db == null) {
            db = new DatabaseConnectionImpl();
        }
        return db;
    }

    @Override
    public Connection getConnection() {
        return this.conn;
    }

    @Override
    public void closeConnection() {
        try {
            if (conn != null && !this.conn.isClosed()) {
                try {
                    this.conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

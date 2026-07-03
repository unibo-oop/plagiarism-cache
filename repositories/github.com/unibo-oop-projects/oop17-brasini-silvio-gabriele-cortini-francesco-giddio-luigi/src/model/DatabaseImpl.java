package model;
/**
 * Implementazione dell'interfaccia IDatabase.
 * @author silviobrasini
 *
 */
public class DatabaseImpl implements Database {

    private String name;
    private String path;
    private String type;
    private String password;
    private String user;

    /**
     * Costruttore di Database.
     * @param name Nome del database
     * @param path Indirizzo del database
     * @param type Tipo di database
     */
    public DatabaseImpl(final String name, final String path, final String type) {
        this.name = name;
        this.path = path;
        this.type = type;
        this.password = password;
        this.user = user;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getPath() {
        return this.path;
    }

    @Override
    public String getType() {
        return this.type;
    }
}

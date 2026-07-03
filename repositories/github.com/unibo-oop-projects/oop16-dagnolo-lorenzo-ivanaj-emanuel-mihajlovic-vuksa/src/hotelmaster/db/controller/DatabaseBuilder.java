package hotelmaster.db.controller;
/**
 * Create the database.
 */
public class DatabaseBuilder {

    private final QueryManager manager;

    DatabaseBuilder() {
        this.manager = new QueryManagerImpl();
    }

    private void createAccountTable() {
        String account = "CREATE TABLE Account (idAccount INTEGER PRIMARY KEY, " 
                                            + "tipo BOOLEAN NOT NULL, "
                                            + "username TEXT UNIQUE NOT NULL, " 
                                            + "password  TEXT NOT NULL)";
        this.create(account);
    }

    private void createRoomTable() {
        final String room = "CREATE TABLE Camera (numero  INTEGER NOT NULL,"
                                               + "piano   INTEGER NOT NULL," 
                                               + "codTipo INTEGER NOT NULL,"
                                               + "FOREIGN KEY (codTipo) REFERENCES TipoCamera (idTipo) ON DELETE NO ACTION," 
                                               + "PRIMARY KEY (numero, piano))";
        this.create(room);
    }

    private void createStayTypeTable() {
        final String stay = "CREATE TABLE Pensione (idPensione  INTEGER PRIMARY KEY, "
                                                + "descrizione TEXT NOT NULL UNIQUE, "
                                                + "prezzo REAL NOT NULL)";
        this.create(stay);
    }

    private void createRoomExtraTable() {
        final String roomExtra = "CREATE TABLE ExtraCamera (idExtra INTEGER PRIMARY KEY, "
                                                         + "descrizione TEXT NOT NULL UNIQUE, "
                                                         + "prezzo  REAL NOT NULL)";
        this.create(roomExtra);
    }

    private void createStayExtraTable() {
        final String stayExtra = "CREATE TABLE Supplemento (idSupplemento INTEGER PRIMARY KEY, "
                                                         + "descrizione TEXT UNIQUE NOT NULL, "
                                                         + "prezzo REAL NOT NULL,"
                                                         + "aPersona BOOLEAN NOT NULL)";
        this.create(stayExtra);
    }

    private void create(final String query) {
        manager.createQuery().createTable(query);
    }

    private void createRoomTypeTable() {
        final String roomType = "CREATE TABLE TipoCamera (idTipo  INTEGER NOT NULL, "
                                                      + "prezzo  REAL NOT NULL, "
                                                      + "descrizione TEXT NOT NULL UNIQUE, "
                                                      + "maxPosti INTEGER NOT NULL, "
                                                      + "prezzoAggiuntivo REAL, "
                                                      + "PRIMARY KEY (idTipo))";
        this.create(roomType);
    }

    private void createPersonPriceTable() {
        final String personPrice = "CREATE TABLE TipoPersona (idTipoPersona INTEGER PRIMARY KEY, "
                                                          + "descrizione TEXT UNIQUE NOT NULL, "
                                                          + "prezzo REAL NOT NULL)";
        this.create(personPrice);
    }

    private void createDocumentTypeTable() {
        final String document = "CREATE TABLE TipoDocumento (idTipoDoc INTEGER, "
                                                         + "descrizione TEXT NOT NULL UNIQUE, "
                                                         + "numeroCaratteri INTEGER NOT NULL, "
                                                         + "PRIMARY KEY (idTipoDoc))";
        this.create(document);
    }

    private void createSeasonTable() {
        final String season = "CREATE TABLE Stagione (idStagione  INTEGER PRIMARY KEY, "
                                                   + "dataInizio  TEXT NOT NULL, "
                                                   + "dataFine TEXT NOT NULL, "
                                                   + "prezzo REAL NOT NULL, "
                                                   + "descrizione TEXT UNIQUE NOT NULL)";
        this.create(season);
    }

    private void createOldStaysTable() {
        final String stays = "CREATE TABLE Storico (idStorico INTEGER PRIMARY KEY,"
                                                 + "dataInizio TEXT,"
                                                 + "dataFine TEXT,"
                                                 + "ricavo REAL,"
                                                 + "numeroPersone INTEGER)";
        this.create(stays);
    }

    private void createStayTable() {
        final  String stay = "CREATE TABLE Soggiorno (codCliente TEXT PRIMARY KEY REFERENCES Cliente (idDocumento) ON DELETE CASCADE,"
                                                   + "dataArrivo TEXT NOT NULL,"
                                                   + "dataPartenza TEXT NOT NULL,"
                                                   + "stato BOOLEAN NOT NULL, "
                                                   + "codPensione INTEGER REFERENCES Pensione (idPensione) ON DELETE NO ACTION)";
        this.create(stay);
    }

    private void createGuestTable() {
        final String guest = "CREATE TABLE Cliente (idDocumento  TEXT PRIMARY KEY, "
                                                 + "nominativo  TEXT  NOT NULL,"
                                                 + "residenza TEXT,"
                                                 + "codTipoDocumento INTEGER REFERENCES TipoDocumento (idTipoDoc) NOT NULL, "
                                                 + "telefono TEXT)";
        this.create(guest);
    }

    private void createGuestStayTable() {
        final String guestStay = "CREATE TABLE SoggiornoSupplemento (codCliente TEXT REFERENCES Cliente (idDocumento) ON DELETE CASCADE, "
                                                                  + "codSupplemento INTEGER REFERENCES Supplemento (idSupplemento) ON DELETE NO ACTION, "
                                                                  + "PRIMARY KEY (codCliente,codSupplemento))";
        this.create(guestStay);
    }

    private void createReservedRoomTable() {
        final String reservedRoom = "CREATE TABLE CameraPrenotata (codCamera INTEGER, codPiano INTEGER,"
                                                                + "codTipoPersona INTEGER,"
                                                                + "codCliente TEXT,"
                                                                + "numeroPersone INTEGER NOT NULL,"
                                                                + "FOREIGN KEY (codCamera, codPiano) REFERENCES Camera (numero, piano) ON DELETE NO ACTION,"
                                                                + "FOREIGN KEY (codTipoPersona) REFERENCES TipoPersona (idTipoPersona) ON DELETE NO ACTION, "
                                                                + "FOREIGN KEY (codCliente) REFERENCES Soggiorno (codCliente) ON DELETE CASCADE, "
                                                                + "PRIMARY KEY (codCamera,codPiano,codTipoPersona,codCliente))";
        this.create(reservedRoom);
    } 

    private void createRoomWithExtraTable() {
        final String extra = "CREATE TABLE CameraSupplemento (codExtra  INTEGER,"
                                                           + "codPiano  INTEGER,"
                                                           + "codCamera INTEGER,"
                                                           + "PRIMARY KEY (codCamera,codExtra,codPiano), "
                                                           + "FOREIGN KEY (codExtra) REFERENCES ExtraCamera (idExtra) ON DELETE CASCADE,"
                                                           + "FOREIGN KEY (codPiano,codCamera)REFERENCES Camera (piano,numero) ON DELETE CASCADE)";
        this.create(extra);

    }
    /**
     * Performs all the queries to create the tables.
     */
    protected void createDatabase() {
        this.createAccountTable();
        this.createRoomExtraTable();
        this.createStayTypeTable(); 
        this.createStayExtraTable();
        this.createRoomTypeTable();
        this.createPersonPriceTable();
        this.createDocumentTypeTable();
        this.createSeasonTable();
        this.createOldStaysTable();
        this.createRoomTable();
        this.createGuestTable();
        this.createStayTable();
        this.createGuestStayTable();
        this.createReservedRoomTable();
        this.createRoomWithExtraTable();
    }
}

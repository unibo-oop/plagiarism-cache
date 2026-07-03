package hotelmaster.database.utilities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import hotelmaster.db.controller.QueryManager;
import hotelmaster.db.controller.QueryManagerImpl;
import hotelmaster.reservations.DocumentType;

/**
 * Utilities for document types.
 */
public class DocumentUtilities extends Utility<DocumentType> {

    private final QueryManager manager;

    /**
     * 
     */
    public DocumentUtilities() {
        this.manager = new QueryManagerImpl();

    }

    @Override
    public Set<DocumentType> getAll() {
        Set<DocumentType> documents = new HashSet<>();
        final String query = "SELECT descrizione, numeroCaratteri FROM TipoDocumento";
        ResultSet rs;
        try {
            rs = manager.createQuery().selectNotPrepared(query);
            while (rs.next()) {
                documents.add(new DocumentType(rs.getString(1), rs.getInt(2)));
            }
            rs.close();
            manager.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (documents.isEmpty() ? Collections.emptySet() : Collections.unmodifiableSet(documents));
    }

}

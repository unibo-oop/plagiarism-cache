package hotelmaster.database.admin;

import java.sql.SQLException;

import hotelmaster.db.controller.QueryManagerImpl;
import hotelmaster.exceptions.PriceDescriberRemovalException;
import hotelmaster.exceptions.UnmodifiablePriceDescriberException;
import hotelmaster.pricing.PriceDescriber;

/**
 * Manages the operations with person prices (administrator level).
 */
public class PersonPrice extends BasicPriceDescriberOperation {

    private final QueryManagerImpl manager;

    /**
     * 
     */
    public PersonPrice() {
        this.manager = new QueryManagerImpl();
    }

    @Override
    public void create(final PriceDescriber person) throws IllegalArgumentException {
        final String query = "INSERT INTO TipoPersona(descrizione, prezzo) VALUES (?,?)";
        try {
            manager.prepareQuery(query).string(1, person.getDescription()).price(2, person.getPrice()).update();
        } catch (SQLException e) {
            throw new IllegalArgumentException("This person price already exists", e);
        }
    }

    @Override
    public void modifyPrice(final PriceDescriber person) throws UnmodifiablePriceDescriberException {
        this.modify(person, "TipoPersona");
    }

    @Override
    public void removeType(final PriceDescriber person) throws PriceDescriberRemovalException {
        this.remove(person, "TipoPersona");

    }
}

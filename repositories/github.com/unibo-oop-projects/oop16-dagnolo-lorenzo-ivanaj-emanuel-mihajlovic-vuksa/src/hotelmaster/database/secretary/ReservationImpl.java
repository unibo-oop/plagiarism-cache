package hotelmaster.database.secretary;

import java.sql.SQLException;
import java.util.Map.Entry;

import hotelmaster.database.admin.OldStayManager;
import hotelmaster.database.utilities.PersonPriceUtilities;
import hotelmaster.database.utilities.StayExtraUtilities;
import hotelmaster.database.utilities.StayTypeUtilities;
import hotelmaster.db.controller.QueryManager;
import hotelmaster.db.controller.QueryManagerImpl;
import hotelmaster.exceptions.GuestException;
import hotelmaster.pricing.PersonPriceDescriber;
import hotelmaster.pricing.StayExtraPriceDescriber;
import hotelmaster.reservations.ModifiableStay;
import hotelmaster.reservations.Occupation;
/**
 * A stay that cannot be modified.
 */
public class ReservationImpl implements Reservation {

    private final QueryManager manager;

    /**
     * 
     */
    public ReservationImpl() {
        this.manager = new QueryManagerImpl();
    }

    @Override
    public void registerStay(final ModifiableStay stay) throws GuestException {
        final String cliente = "INSERT INTO Cliente(idDocumento, nominativo, residenza, "
                + "codTipoDocumento, telefono) VALUES (?,?,?, (SELECT idTipoDoc " + "FROM TipoDocumento "
                + "WHERE descrizione = ?), ?)";
        final String soggiorno = "INSERT INTO Soggiorno (codCliente, dataArrivo, dataPartenza, stato, codPensione) "
                + "VALUES(?,?,?,0,?)";
        try {
            manager.prepareQuery(cliente).string(1, stay.getClient().getDocument())
            .string(2, stay.getClient().getNominative()).string(3, stay.getClient().getCountry())
            .string(4, stay.getClient().getDocumentType().getDescription())
            .string(5, stay.getClient().getPhoneNumber())
            .update();

            manager.prepareQuery(soggiorno).string(1, stay.getClient().getDocument())
            .date(2, stay.getDates().getBeginning())
            .date(3, stay.getDates().getEnd())
            .integer(4, new StayTypeUtilities().getId(stay.getType()))
            .update();
        } catch (SQLException e) {
            throw new GuestException("This guest already exists", e);
        }

        this.setExtras(stay);
        this.setRooms(stay);
    }

    @Override
    public void confirmStay(final ModifiableStay stay) {
        final String query = "UPDATE Soggiorno SET stato = 1 WHERE codCliente = ?";
        try {
            manager.prepareQuery(query).string(1, stay.getClient().getDocument());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closeStay(final ModifiableStay stay) {
        new ModifiableReservationImpl().deleteReservation(stay);
        new OldStayManager().saveClosedStay(stay);
    }
    /**
     * Set the stay extras to a stay.
     * @param stay the stay to be created.
     */
    protected void setExtras(final ModifiableStay stay) {
        final String query = "INSERT INTO SoggiornoSupplemento (codCliente, codSupplemento) " + "VALUES (?,?)";
        for (StayExtraPriceDescriber extras : stay.getExtras()) {
            try {
                manager.prepareQuery(query).string(1, stay.getClient().getDocument())
                        .integer(2, new StayExtraUtilities().getId(extras)).update();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * Set a room occupation to a stay.
     * @param stay the stay to be created.
     */
    protected void setRooms(final ModifiableStay stay) {
        final String query = "INSERT INTO CameraPrenotata (codCamera,codPiano,codTipoPersona,codCliente,numeroPersone) "
                + "VALUES (?,?,?,?,?)";
        for (Occupation occ : stay.getOccupationsView()) {
            for (Entry<PersonPriceDescriber, Integer> people : occ.getPeopleView().entrySet()) {
                try {
                    manager.prepareQuery(query).integer(1, occ.getRoom().getID().getNumberOnFloor())
                            .integer(2, occ.getRoom().getID().getFloor())
                            .integer(3, new PersonPriceUtilities().getId(people.getKey()))
                            .string(4, stay.getClient().getDocument()).integer(5, people.getValue()).update();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

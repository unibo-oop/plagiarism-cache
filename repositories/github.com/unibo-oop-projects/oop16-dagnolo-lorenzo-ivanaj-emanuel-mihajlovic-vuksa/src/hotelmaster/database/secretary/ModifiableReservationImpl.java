package hotelmaster.database.secretary;

import java.sql.SQLException;

import hotelmaster.database.utilities.StayExtraUtilities;
import hotelmaster.database.utilities.StayTypeUtilities;
import hotelmaster.db.controller.QueryManager;
import hotelmaster.db.controller.QueryManagerImpl;
import hotelmaster.pricing.StayExtraPriceDescriber;
import hotelmaster.reservations.ModifiableOccupation;
import hotelmaster.reservations.ModifiableStay;

/**
 * A reservation that can be modified.
 */
public class ModifiableReservationImpl implements ModifiableReservation {
    private final QueryManager manager;

    /**
     * 
     */
    public ModifiableReservationImpl() {
        this.manager = new QueryManagerImpl();
    }

    @Override
    public void addExtra(final ModifiableStay stay, final StayExtraPriceDescriber extra) {
        ModifiableStay temp = ModifiableStay.create();
        temp.getExtras().add(extra);
        temp.setClient(stay.getClient());
        new ReservationImpl().setExtras(temp);
    }

    @Override
    public void removeExtra(final ModifiableStay stay, final StayExtraPriceDescriber extra) {
        final String query = "DELETE FROM SoggiornoSupplemento WHERE codCliente = ? AND codSupplemento = ?";
        try {
            manager.prepareQuery(query).string(1, stay.getClient().getDocument())
                    .integer(2, new StayExtraUtilities().getId(extra)).delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modifyStayType(final ModifiableStay stay)  {
        final String query = "UPDATE Soggiorno SET codPensione = ? WHERE codCliente = ?";
        try {
            manager.prepareQuery(query).integer(1, new StayTypeUtilities().getId(stay.getType()))
                                       .string(2, stay.getClient().getDocument()).update();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteReservation(final ModifiableStay stay) {
        final String query = "DELETE FROM Cliente WHERE idDocumento = ?";
            try {
                manager.prepareQuery(query).string(1, stay.getClient().getDocument()).delete();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void modifyDates(final ModifiableStay stay) {
        final String query = "UPDATE Soggiorno SET dataArrivo  = ?, dataPartenza = ? WHERE codCliente = ?";
        try {
            manager.prepareQuery(query).date(1, stay.getDates().getBeginning()).date(2, stay.getDates().getEnd())
                    .string(3, stay.getClient().getDocument()).update();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addRoomOccupation(final ModifiableStay stay, final ModifiableOccupation occ)  {
        ModifiableStay temp = ModifiableStay.create();
        temp.setClient(stay.getClient());
        temp.getOccupations().add(occ);
        new ReservationImpl().setRooms(temp);
    }

    @Override
    public void removeRoomOccupation(final ModifiableStay stay, final ModifiableOccupation occ)  {
        final String query = "DELETE FROM CameraPrenotata WHERE codCamera = ? AND codPiano = ? AND codCliente = ?";
        try {
            manager.prepareQuery(query).integer(1, occ.getRoom().getID().getNumberOnFloor())
                                       .integer(2, occ.getRoom().getID().getFloor())
                                       .string(3, stay.getClient().getDocument())
                                       .delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modifyPeople(final ModifiableStay stay, final ModifiableOccupation newOccupation) {
        this.removeRoomOccupation(stay, newOccupation);
        this.addRoomOccupation(stay, newOccupation);
    }
}

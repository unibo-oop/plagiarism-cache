package hotelmaster.database.admin;

import java.sql.SQLException;

import hotelmaster.database.utilities.RoomExtraUtilities;
import hotelmaster.database.utilities.RoomTypeUtilities;
import hotelmaster.db.controller.QueryManager;
import hotelmaster.db.controller.QueryManagerImpl;
import hotelmaster.exceptions.RoomRemovalException;
import hotelmaster.structure.Room;
import hotelmaster.pricing.RoomExtraPriceDescriber;

/**
 * Manages the operations on the rooms (administrator level).
 */
public class RoomsImpl implements Rooms {

    private final QueryManager manager;

    /**
     * 
     */
    public RoomsImpl() {
        this.manager = new QueryManagerImpl();
    }

    @Override
    public void createRoom(final Room r) throws IllegalArgumentException {
        String query = "INSERT INTO Camera (numero, piano, codTipo) VALUES (?,?,?)";
        try {
            manager.prepareQuery(query).integer(1, r.getID().getNumberOnFloor())
                                       .integer(2, r.getID().getFloor())
                                       .integer(3, new RoomTypeUtilities().getId(r.getType()))
                                       .update();
        } catch (SQLException e) {
            throw new IllegalArgumentException("The room already exixsts", e);
        }

        for (RoomExtraPriceDescriber tp : r.getExtrasView()) {
            this.addExtra(r, tp);
        }
    }

    @Override
    public void modifyTypeOfRoom(final Room r) throws IllegalArgumentException {
        String query = "UPDATE Camera SET codTipo = ? WHERE numero = ? AND piano = ?";
        try {
            manager.prepareQuery(query).integer(1, new RoomTypeUtilities().getId(r.getType()))
                                       .integer(2, r.getID().getNumberOnFloor())
                                       .integer(3, r.getID().getFloor()).update();
        } catch (SQLException e) {
            throw new IllegalArgumentException("This room cannot be modified", e);
        }
    }

    @Override
    public void addExtra(final Room r, final RoomExtraPriceDescriber extra) {
        final String query = "INSERT INTO CameraSupplemento (codCamera, codPiano, codExtra) VALUES (?,?,?)";
        try {
            manager.prepareQuery(query).integer(1, r.getID().getNumberOnFloor())
                                       .integer(2, r.getID().getFloor())
                                       .integer(3, new RoomExtraUtilities().getId(extra));
        } catch (SQLException e) {
            throw new IllegalArgumentException("This extra is already associated with the room", e);
        }
    }

    @Override
    public void removeExtra(final Room r, final RoomExtraPriceDescriber extra) throws RoomRemovalException {
        String query = "DELETE FROM CameraSupplemento WHERE codCamera = ? AND codPiano = ? AND codExtra = ?";
        try {
            manager.prepareQuery(query).integer(1, r.getID().getNumberOnFloor())
                                       .integer(2, r.getID().getFloor())
                                       .integer(3, new RoomExtraUtilities().getId(extra));
        } catch (SQLException e) {
            throw new RoomRemovalException("This extra cannot be removed from the room", e);
        }
    }

    @Override
    public void removeRoom(final Room r) throws RoomRemovalException {
        String query = "DELETE FROM Camera WHERE numero = ? AND piano = ?";
        try {
            manager.prepareQuery(query).integer(1, r.getID().getNumberOnFloor()).integer(2, r.getID().getFloor())
                    .delete();
        } catch (SQLException e) {
            throw new RoomRemovalException("This room cannot be removed", e);
        }
    }

}

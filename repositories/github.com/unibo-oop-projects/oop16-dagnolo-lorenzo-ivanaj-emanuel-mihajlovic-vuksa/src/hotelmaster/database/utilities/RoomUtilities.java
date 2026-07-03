package hotelmaster.database.utilities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import hotelmaster.db.controller.QueryManager;
import hotelmaster.db.controller.QueryManagerImpl;
import hotelmaster.pricing.RoomExtraPriceDescriber;
import hotelmaster.pricing.RoomTypePriceDescriber;
import hotelmaster.structure.ModifiableRoom;

/**
 * Utilities for the rooms.
 */
public class RoomUtilities extends Utility<ModifiableRoom> {

    private final QueryManager manager;

    /**
     * 
     */
    public RoomUtilities() {
        this.manager = new QueryManagerImpl();
    }

    @Override
    public Set<ModifiableRoom> getAll() {
        final String query = "SELECT numero, piano, TipoCamera.descrizione, TipoCamera.prezzo, "
                + "TipoCamera.maxPosti, TipoCamera.prezzoAggiuntivo " 
                + "FROM Camera, TipoCamera "
                + "WHERE Camera.codTipo = TipoCamera.idTipo";

        ResultSet rs;
        Set<ModifiableRoom> rooms = new HashSet<>();
        Set<RoomExtraPriceDescriber> extras = null;
        try {
            rs = manager.createQuery().selectNotPrepared(query);

            while (rs.next()) {
                ModifiableRoom newRoom = ModifiableRoom.create(rs.getInt(2), rs.getInt(1));
                newRoom.setType(
                        new RoomTypePriceDescriber(rs.getString(3), rs.getDouble(4), rs.getDouble(6), rs.getInt(5)));
                extras = this.getExtras(newRoom.getID().getNumberOnFloor(), newRoom.getID().getFloor());
                for (RoomExtraPriceDescriber room : extras) {
                    newRoom.getExtras().add(room);
                }
                rooms.add(newRoom);
            }
            rs.close();
            manager.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (rooms.isEmpty() ? Collections.emptySet() : Collections.unmodifiableSet(rooms));

    }

    /**
     * Get all the extras associated with a certain room.
     * @param number the number of the room on its floor
     * @param floor the number of the floor 
     * @return a {@link Set} of rooms
     */
    public Set<RoomExtraPriceDescriber> getExtras(final int number, final int floor) {

        final String query = "SELECT ExtraCamera.descrizione, ExtraCamera.prezzo "
                + "FROM Camera, ExtraCamera, CameraSupplemento "
                + "WHERE ExtraCamera.idExtra = CameraSupplemento.codExtra "
                + "AND Camera.numero = CameraSupplemento.codCamera " + "AND Camera.piano = CameraSupplemento.codPiano "
                + "AND numero = ? " + "AND piano = ?";
        Set<RoomExtraPriceDescriber> extras = new HashSet<>();
        ResultSet rs;
        try {
            rs = manager.prepareQuery(query).integer(1, number).integer(2, floor).selectPrepared();
            while (rs.next()) {
                extras.add(new RoomExtraPriceDescriber(rs.getString(1), rs.getDouble(2)));
            }
            rs.close();
            manager.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (extras.isEmpty() ? Collections.emptySet() : Collections.unmodifiableSet(extras));
    }
}

package hotelmaster.database.utilities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import hotelmaster.db.controller.QueryManager;
import hotelmaster.db.controller.QueryManagerImpl;
import hotelmaster.pricing.PersonPriceDescriber;
import hotelmaster.pricing.RoomExtraPriceDescriber;
import hotelmaster.pricing.RoomTypePriceDescriber;
import hotelmaster.pricing.StayExtraPriceDescriber;
import hotelmaster.pricing.StayTypePriceDescriber;
import hotelmaster.reservations.Client;
import hotelmaster.reservations.DocumentType;
import hotelmaster.reservations.UnboundStayBuilder;
import hotelmaster.structure.ModifiableRoom;
import hotelmaster.structure.Room;
import hotelmaster.utility.time.FixedPeriod;

/**
 * Utilities for the reservations (secretary level).
 *
 */
public class ReservationUtilities implements BasicReservationUtilities {

    private final QueryManager manager;

    /**
     * 
     */
    public ReservationUtilities() {
        this.manager = new QueryManagerImpl();
    }

    @Override
    public void loadStays() {
        this.getClients().stream().forEach(client -> {
            UnboundStayBuilder stay = UnboundStayBuilder.create();
            stay.setClient(client);
            stay.setType(this.getType(client));
            this.getExtras(client).stream().forEach(t -> stay.addExtra(t));
            stay.setDates(this.getDates(client));
            this.getRooms(client).stream().forEach(room -> {
                room.getExtras().addAll(this.getRoomExtras(room));
                stay.addRoom(room, this.getPeople(client, room));
            });
            stay.complete();
        });
    }

    @Override
    public Set<Client> getClients() {
        Set<Client> clients = new HashSet<>();

        final String query = "SELECT nominativo, idDocumento, TipoDocumento.descrizione, "
                + "TipoDocumento.numeroCaratteri,residenza,telefono " 
                + "FROM Cliente, TipoDocumento "
                + "WHERE Cliente.codTipoDocumento = TipoDocumento.idTipoDoc";

        try {
            ResultSet rs = manager.createQuery().selectNotPrepared(query);
            while (rs.next()) {
                clients.add(Client.create(rs.getString(1), rs.getString(5),
                        new DocumentType(rs.getString(3), rs.getInt(4)), rs.getString(2), rs.getString(6)));
            }
            rs.close();
            manager.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (clients.isEmpty() ? Collections.emptySet() : Collections.unmodifiableSet(clients));

    }

    @Override
    public Map<PersonPriceDescriber, Integer> getPeople(final Client c, final Room r) {
        Map<PersonPriceDescriber, Integer> people = new HashMap<>();

        final String query = "SELECT TipoPersona.descrizione, TipoPersona.prezzo, CameraPrenotata.numeroPersone "
                + "FROM CameraPrenotata, TipoPersona " 
                + "WHERE CameraPrenotata.codCliente = ? "
                + "AND CameraPrenotata.codCamera = ? AND CameraPrenotata.codPiano = ? "
                + "AND TipoPersona.idTipoPersona = CameraPrenotata.codTipoPersona";
        try {
            final ResultSet rs = manager.prepareQuery(query).string(1, c.getDocument())
                    .integer(2, r.getID().getNumberOnFloor()).integer(3, r.getID().getFloor()).selectPrepared();

            while (rs.next()) {
                people.put(new PersonPriceDescriber(rs.getString(1), rs.getDouble(2)), rs.getInt(3));
            }
            rs.close();
            manager.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return people;
    }

    @Override
    public Set<ModifiableRoom> getRooms(final Client c) {
        Set<ModifiableRoom> rooms = new HashSet<>();

        final String query = "SELECT Camera.numero, Camera.piano, TipoCamera.descrizione, "
                + "TipoCamera.prezzo, TipoCamera.maxPosti, TipoCamera.prezzoAggiuntivo "
                + "FROM Camera, CameraPrenotata,TipoCamera " 
                + "WHERE CameraPrenotata.codCliente = ? "
                + "AND Camera.codTipo = TipoCamera.idTipo " 
                + "AND CameraPrenotata.codCamera = Camera.numero "
                + "AND CameraPrenotata.codPiano = Camera.piano " 
                + "GROUP BY numero, piano";
        try {
            final ResultSet rs = manager.prepareQuery(query).string(1, c.getDocument()).selectPrepared();
            while (rs.next()) {
                ModifiableRoom r = ModifiableRoom.create(rs.getInt(2), rs.getInt(1));
                r.setType(new RoomTypePriceDescriber(rs.getString(3), rs.getDouble(4), rs.getDouble(6), rs.getInt(5)));
                rooms.add(r);
            }
            rs.close();
            manager.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    @Override
    public Set<RoomExtraPriceDescriber> getRoomExtras(final Room r) {
        Set<RoomExtraPriceDescriber> extras = new HashSet<>();
        final String query = "SELECT DISTINCT ExtraCamera.descrizione, ExtraCamera.prezzo "
                + "FROM ExtraCamera, CameraSupplemento,CameraPrenotata "
                + "WHERE CameraSupplemento.codCamera = ? AND CameraSupplemento.codPiano = ? "
                + "AND CameraSupplemento.codExtra = ExtraCamera.idExtra";
        try {
            final ResultSet rs = manager.prepareQuery(query).integer(1, r.getID().getNumberOnFloor())
                    .integer(2, r.getID().getFloor()).selectPrepared();
            while (rs.next()) {
                extras.add(new RoomExtraPriceDescriber(rs.getString(1), rs.getDouble(2)));
            }
            rs.close();
            manager.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return extras;

    }

    @Override
    public FixedPeriod getDates(final Client c) {
        FixedPeriod period = null;
        final String query = "SELECT Soggiorno.dataPartenza, Soggiorno.dataArrivo, Soggiorno.codStato "
                + "FROM Soggiorno  " 
                + "WHERE codCliente = ?";
        try {
            final ResultSet rs = manager.prepareQuery(query).string(1, c.getDocument()).selectPrepared();
            if (rs.next()) {
                period = FixedPeriod.of(Utility.toLocalDate(rs.getString(2)), Utility.toLocalDate(rs.getString(1)));
            }
            rs.close();
            manager.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return period;

    }

    @Override
    public Set<StayExtraPriceDescriber> getExtras(final Client c) {
        Set<StayExtraPriceDescriber> extras = new HashSet<>();
        final String query = "SELECT Supplemento.descrizione, Supplemento.aPersona, Supplemento.prezzo "
                + "FROM Supplemento, SoggiornoSupplemento " 
                + "WHERE SoggiornoSupplemento.codCliente = ? "
                + "AND Supplemento.idSupplemento = SoggiornoSupplemento.codSupplemento";

        try {
            final ResultSet rs = manager.prepareQuery(query).string(1, c.getDocument()).selectPrepared();
            while (rs.next()) {
                extras.add(new StayExtraPriceDescriber(rs.getString(1), rs.getDouble(3), rs.getBoolean(2)));
            }
            rs.close();
            manager.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (extras.isEmpty() ? Collections.emptySet() : Collections.unmodifiableSet(extras));

    }

    @Override
    public StayTypePriceDescriber getType(final Client c) {
        StayTypePriceDescriber type = null;
        final String query = "SELECT Pensione.descrizione, Pensione.prezzo " 
                + "FROM Pensione, Soggiorno "
                + "WHERE Soggiorno.codCliente = ? " 
                + "AND Soggiorno.codPensione = Pensione.idPensione";
        try {
            ResultSet rs = manager.prepareQuery(query).string(1, c.getDocument()).selectPrepared();
            if (rs.next()) {
                type = new StayTypePriceDescriber(rs.getString(1), rs.getInt(2));
            }
            rs.close();
            manager.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return type;

    }

}

package hotelmaster.database.admin;

import java.sql.SQLException;

import hotelmaster.db.controller.QueryManager;
import hotelmaster.db.controller.QueryManagerImpl;
import hotelmaster.reservations.ModifiableStay;
/**
 * Manager for closed stays.
 */
public class OldStayManager {

    private final QueryManager manager;
    /**
     * 
     */
    public OldStayManager() {
        this.manager = new QueryManagerImpl();
    }

    /**
     * Save the main data of a closed stay.
     * @param stay the stay
     */
    public void saveClosedStay(final ModifiableStay stay) {

        final String query = "INSERT INTO Storico (dataInzio, dataFine, ricavo, numeroPersone) VALUES " + "(?,?,?,?)";
        try {
            manager.prepareQuery(query).date(1, stay.getDates().getBeginning()).date(2, stay.getDates().getEnd())
                    .price(3, stay.getTotalPrice()).integer(4,
                            stay.getOccupationsView().stream().mapToInt(
                                    occ -> occ.getPeopleView().values().stream().mapToInt(Integer::intValue).sum())
                                    .sum());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

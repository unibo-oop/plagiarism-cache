package view.HomeHotelManage;

import java.awt.Color;
import java.util.Date;

public interface HomeHotelManageViewLogic {

    /**
     * This method check the room's status in a specific date. 
     * Free: Green
     * Reserved: Red
     * Checkout-date: Orange.
     * @param currentDate
     * @param roomNumber
     * @return Color
     */
    Color statusOnDate(Date currentDate, int roomNumber);
}

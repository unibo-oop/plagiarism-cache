package controller.implementations;

import java.text.DateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import controller.interfaces.MonthlyIncomeController;
import model.implementations.AirportImpl;
import model.interfaces.Airport;
import model.interfaces.Booking;

/**
 * Monthly Income Controller implementation.
 */
public class MonthlyIncomeControllerImpl implements MonthlyIncomeController {

    private static final String[] MONTHS = {"Gen", "Feb", "Mar", "Apr", "Mag", "Giu", "Lug", "Ago",
            "Set", "Ott", "Nov", "Dic"};

    private final Airport airport;

    /**
     * Monthly Income Controller constructor.
     */
    public MonthlyIncomeControllerImpl() {
        this.airport = AirportImpl.getAirport();
    }

    @Override
    public Map<String, Double> createMap() {
        final Map<String, Double> map = new HashMap<>();
        final DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);

        for (int i = 0; i < MONTHS.length; i++) {
            map.put(MONTHS[i], 0.0);
        }

        for (final Booking b : this.airport.getReservations()) {
            final String month = format.format(b.getFlight().getDepartureDate()).substring(3, 5);
            map.merge(MONTHS[Integer.parseInt(month) - 1], 0.0, (k, v) -> v + b.getFinalPrice());
        }
        return map;
    }

}

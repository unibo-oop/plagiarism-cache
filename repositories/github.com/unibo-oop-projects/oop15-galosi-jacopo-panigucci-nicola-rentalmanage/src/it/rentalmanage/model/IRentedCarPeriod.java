package it.rentalmanage.model;

import java.util.Date;

/**
 * Created by utente on 23/02/2016.
 */
public interface IRentedCarPeriod {

    /**
     *
     * @return l'auto noleggiata
     */
    String getNumberPlate();

    /**
     *
     * @return l'assegnazione dell'auto noleggiata
     * @param numberPlate
     */
    void setNumberPlate(String numberPlate);

    /**
     *
     * @return la data di inizio noleggio
     */
    Date getStartDate();

    /**
     *
     * @return l'assegnazione della data di inizio noleggio
     * @param startDate
     */
    void setStartDate(Date startDate);

    /**
     *
     * @return la data di fine noleggio
     */
    Date getEndDate();

    /**
     *
     * @return l'assegnazione della data di fine noleggio, può essere null
     * @param endDate
     */
    void setEndDate(Date endDate);

    /**
     *
     * @return il costo per noleggiare l'auto durante il periodo di date preso in esame
     */
    int getRentedPrice();


}

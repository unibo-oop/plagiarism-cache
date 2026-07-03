package it.rentalmanage.model;

import java.util.Date;

/**
 * Created by utente on 23/02/2016.
 */
public interface IRentalPeriod {

    /**
     *
     * @return chi ha noleggiato l'auto
     */
    String getFCode();

    /**
     *
     * @return la data di inizio noleggio
     */
    Date getStartDate();

    /**
     * Imposta la data di inizio noleggio
     * @param startDate  data di inizio nolettio
     */
    void setStartDate(Date startDate);

    /**
     * Ritorna la data di fine noleggio
     * @return data di fine noleggio
     */
    Date getEndDate();

    /**
     * Imposta la data di fine noleggio, può essere null
     * @param endDate  la data di fine noleggio
     */
    void setEndDate(Date endDate);


}

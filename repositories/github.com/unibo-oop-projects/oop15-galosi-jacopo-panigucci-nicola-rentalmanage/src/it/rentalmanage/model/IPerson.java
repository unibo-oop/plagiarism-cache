package it.rentalmanage.model;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by utente on 23/02/2016.
 */
public interface IPerson {

    /**
     * Imposta il nome della persona
     * @param name  nome della persona
     */
    void setName(String name);

    /**
     * Imposta il cognome della persona
     * @param surname  cognome della persona
     */
    void setSurname(String surname);

    /**
     *
     * @return il nome della persona
     */
    String getName();

    /**
     *
     * @return il cognome della persona
     */
    String getSurname();

    /**
     *
     * @param phoneNumber numero di telefono della persona
     * imposta il numero di telefono della persona
     */
    void setPhoneNumber(String phoneNumber);

    /**
     *
     * @return il numero di telefono della persona
     */
    String getPhoneNumber();


    /**
     *
     * @return il codice fiscale della persona
     */
    String getFiscalCode();

    /**
     *
     * @return l'indirizzo in cui vive la persona
     */
    String getAddress();

    /**
     * Imposta l'indirizzo in cui vive la persona
     * @param address l'indirizzo in cui vive la persona
     */
    void setAddress(String address);

    /**
     *
     * @return Tutte le patenti possedute dalla persona
     */
    List<DrivingLicence> getDrivingLicense();

    /**
     *
     * @return La data di nascita della persona
     */
    Date getBirthDate();


    /**
     *
     * @return una collection con lo storico di tutte le auto noleggiate con le rispettive date
     */
    Collection<IRentedCarPeriod> getAllRentedCar();

    /**
     * aggiunge un auto a quelle prenotate
     * @param carPeriod periodo per il quale si è prenotata una determinata auto
     */
    void addCar(IRentedCarPeriod carPeriod);

}

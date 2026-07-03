package it.rentalmanage.model;

import java.util.Collection;
import java.util.Date;

/**
 * Created by utente on 23/02/2016.
 */
public interface ICar {

    /**
     * @param manufactorer produttore dell'auto
     * assegna la marca dell'auto
     */
    void setManufactorer(String manufactorer);

    /**
     *
     * @return la marca dell'auto
     */
    String getManufactorer();

    /**
     *
     * @param model modello dell'auto
     *  assegna la marca dell'auto
     */
    void setModel(String model);

    /**
     *
     * @return il modello dell'auto
     */
    String getModel();

    /**
     *
     * @return se l'auto è noleggiabile o meno
     */
    boolean isRentable();

    /**
     *
     * @param rentable booleano che ci dice se l'auto è noleggiabile o meno
     * imposta il valore che ci dice se l'auto può essere noleggiata o meno
     */
    void  setRentability(boolean rentable);

    /**
     * imposta il costo di noleggio
     * @param rentPrice costo di nolegio
     */

    void setRentPrice(int rentPrice);

    /**
     *
     * @return il costo di noleggio
     */
    int getRentPrice();

    /**
     *
     * @return la targa dell'auto
     */
    String getNumberPlate();

    /**
     *
     * @return il costo dell'assicurazione
     */
    int getInsuranceCost();

    /**
     * @param insuranceCost costo dell'asicurazione
     * imposta il costo dell'assicurazione
     */
    void setInsuranceCost(int insuranceCost);

    /**
     *
     * @return la data di scadenza dell'assicurazione
     */
    Date getInsuranceExpiring();

    /**
     * @param insuranceExpiring data di scadenza dell'assicurazione
     * imposta la scadenza dell'assicurazione
     */
    void setInsuranceExpiring(Date insuranceExpiring);



    /**
     *
     * @return il numero di posti a sedere nell'auto
     */
    int getCarSeats();

    /**
     *
     * @return la patente richiesta per guidare l'auto
     */
    DrivingLicence getRequestedLicense();

    void setRequestedLicence(DrivingLicence drivingLicence);

    /**
     *
     * @return il costo del bollo
     */
    int getCarTaxCost();

    /**
     * @param carTaxCost costo del bollo
     * imposta il costo del bollo
     */
    void setCarTaxCost(int carTaxCost);

    /**
     *
     * @return la data di scadenza del bollo
     */
    Date getCarTaxDate();

    /**
     * @param carTaxDate data di scadenza del bollo
     * imposta la data di scadenza del bollo
     */
    void setCarTaxDate(Date carTaxDate);

    /**
     *
     * @return il costo della revisione
     */
    int getMotTestCost();

    /**
     * @param motTestCost costo della revisione
     * imposta il costo della revisione
     */
    void setMotTestCost(int motTestCost);

    /**
     *
     * @return la data di scadenza della revisione
     */
    Date getMotTestDate();

    /**
     * @param motTestDate data di scadenza della revisione
     * imposta la data di scadenza della revisione
     */
    void setMotTestDate(Date motTestDate);

    /**
     *
     * @return una collection con lo storico di tutte le persone che hanno noleggiato l'auto
     */
    Collection<IRentalPeriod> getAllTenant();

    /**
     *
     * aggiunge a un set la persona che ha prenotato l'auto
     * @param personPeriod persona che noleggia l'auto e per quale periodo l'ha noleggiata
     *
     */
    void addTenant(IRentalPeriod personPeriod);

    /**
     *
     * @param personPeriod persona che noleggia l'auto e per quale periodo l'ha noleggiata
     * rimuove da un set la persona che ha prenotato l'auto
     */
}

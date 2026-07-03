package it.rentalmanage.model;

import java.util.Map;

/**
 * Created by utente on 23/02/2016.
 */
public interface IModel {

    /**
     *
     * @return una mappa<CodiceFiscale,Persona> con tutte le persone
     */
    Map<String, IPerson> getAllPersons();

    /**
     *
     * @return una mappa<Targa,Macchina> con tutte le auto
     */
    Map<String, ICar> getAllCar();

    /**
     *
     * @return una mappa<CodiceFiscale,Persona> con lo storico di tutte le persone che hanno almeno un noleggio
     */
    Map<String, IPerson> getAllPersonsHistory();

    /**
     *
     * @param fCode codice fiscale della persona da cercare nello storico
     * @return la persona che ha il codice fiscale preso in input
     */
    IPerson getPersonFromFCodeHistory(String fCode);

    /**
     *
     * @param numberPlate targa della macchina da cercare nello storico
     * @return la macchina che ha la targa presa in input
     */
    ICar getCarFromNumPlateHistory(String numberPlate);

    /**
     *
     * @return una mappa<Targa,Macchina> con tutte le auto che sono state noleggiate almeno una volta
     */
    Map<String,ICar> getAllCarsHistory();

    /**
     *
     * @param person un oggetto di tipo persona che va aggiunto alla mappa di tutte le persone
     */
    void addPerson(IPerson person);

    /**
     *
     * @param person un oggetto di tipo persona che va aggiunto alla mappa-storico di tutte le persone,
     *               questo metodo viene chiamato solo in fase di noleggio
     */
    void addPersonToHistory(IPerson person);

    /**
     *
     * @param person persona da rimuovere dalla mappa di tutte le persone
     */

    void removePerson(IPerson person);

    /**
     *
     * @param car un oggetto di tipo car che va aggiunto alla mappa di tutte le auto
     */
    void addCar(ICar car);

    /**
     *
     * @param car un oggetto di tipo car che va aggiunto alla mappa-storico di tutte le auto,questo metodo viene chiamato
     *            solo in fase di noleggio
     */
    void addCarToHistory(ICar car);

    /**
     *
     * @param car auto da rimuovere dalla mappa di tutte le auto
     */
    void removeCar(ICar car);



}

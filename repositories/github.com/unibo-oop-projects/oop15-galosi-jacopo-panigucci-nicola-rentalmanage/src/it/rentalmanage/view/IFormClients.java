package it.rentalmanage.view;

import it.rentalmanage.model.DrivingLicence;

import java.util.List;

/**
 * Created by nicolapanigucci on 08/03/16.
 */
public interface IFormClients {

    /**
     * Aggiunge una patente alla lista di patenti possedute
     */
    void addDriveLicenseToList();

    /**
     * Rimuove una patente dalla lista delle patenti possedute
     */
    void removeDriveLicenseFromList();

    /**
     * Visualizza la lista nella JLabel
     */
    void setLabelDLicense();

    /**
     * Ritorna la lista delle patenti possedute dal cliente
     * @return lista delle patenti possedute dal cliente
     */
    List<DrivingLicence> getAllDrivingLicense();

}

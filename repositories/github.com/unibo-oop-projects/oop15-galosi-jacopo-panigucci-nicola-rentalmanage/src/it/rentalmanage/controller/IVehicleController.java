package it.rentalmanage.controller;

import it.rentalmanage.model.DrivingLicence;
import it.rentalmanage.model.IRentalPeriod;

import java.util.Collection;
import java.util.Date;

/**
 * Created by nicolapanigucci on 27/04/16.
 */
public interface IVehicleController {

    /**
     * Aggiorna i dati di un veicolo e li salva nei file
     * @param rentPrice
     * @param requestedLicence
     * @param insuranceCost
     * @param insuranceExpiring
     * @param carTaxCost
     * @param carTaxExpiring
     * @param MOOTestCost
     * @param MOOTestExpiring
     */
    void updateVehicle(int rentPrice, DrivingLicence requestedLicence, int insuranceCost, Date insuranceExpiring,
                       int carTaxCost, Date carTaxExpiring, int MOOTestCost, Date MOOTestExpiring);

    /**
     * Elimina il veicolo dai file
     */
    void deleteVehicle();

}

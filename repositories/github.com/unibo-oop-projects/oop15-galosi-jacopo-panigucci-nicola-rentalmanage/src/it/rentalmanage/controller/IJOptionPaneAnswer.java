package it.rentalmanage.controller;

import it.rentalmanage.model.ICar;
import it.rentalmanage.model.IModel;
import it.rentalmanage.model.IPerson;
import it.rentalmanage.view.MainFrame;

/**
 * Created by nicolapanigucci on 10/03/16.
 */
public interface IJOptionPaneAnswer {

    /**
     * Gestisce la scelta dell'utente. Eliminazione cliente
     * @param choice
     * @param prevPanel
     * @param delPerson
     * @param iModel
     */
    void delPerson(int choice, final MainFrame prevPanel, final IPerson delPerson, final IModel iModel);

    /**
     * Gestisce la scelta dell'utente. Eliminazione veicolo
     * @param choice
     * @param prevPanel
     * @param delCar
     * @param iModel
     */
    void delVehicle(int choice, final MainFrame prevPanel, final ICar delCar, final IModel iModel);
}

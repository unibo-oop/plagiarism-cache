package it.rentalmanage.controller;

import it.rentalmanage.model.ICar;
import it.rentalmanage.model.IModel;
import it.rentalmanage.model.IPerson;
import it.rentalmanage.model.filemanager.IFileManager;
import it.rentalmanage.model.filemanager.JSonFileManager;
import it.rentalmanage.view.MainFrame;
import it.rentalmanage.view.StoragePerson;
import it.rentalmanage.view.StorageVehicle;

import javax.swing.*;

/**
 * Created by nicolapanigucci on 10/03/16.
 */
public class JOptionePaneAnswer implements IJOptionPaneAnswer {

    private IFileManager manager = new JSonFileManager();

    @Override
    public void delPerson(int choice, final MainFrame prevPanel, final IPerson delPerson, final IModel iModel) {

        if(choice == JOptionPane.YES_OPTION){
            IPersonController personController = new PersonController(iModel, delPerson);
            personController.deletePerson();

            prevPanel.setPanel(new StoragePerson(prevPanel, iModel));
        }
    }

    @Override
    public void delVehicle(int choice, final MainFrame prevPanel, final ICar delCar, final IModel iModel) {
        if (choice == JOptionPane.YES_OPTION){
            IVehicleController vehicleController = new VehicleController(iModel, delCar);
            vehicleController.deleteVehicle();

            prevPanel.setPanel(new StorageVehicle(prevPanel, iModel));
        }
    }
}

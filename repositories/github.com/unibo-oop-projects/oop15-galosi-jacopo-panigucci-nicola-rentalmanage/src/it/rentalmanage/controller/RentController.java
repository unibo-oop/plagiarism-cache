package it.rentalmanage.controller;

import it.rentalmanage.model.*;
import it.rentalmanage.model.filemanager.IFileManager;
import it.rentalmanage.model.filemanager.JSonFileManager;

import java.util.Date;

/**
 * Created by nicolapanigucci on 09/04/16.
 */
public class RentController implements IRentController{

    private IRentedCarPeriod iRentedCarPeriod;
    private IRentalPeriod iRentalPeriod;
    private IModel iModel;

    public RentController(final IModel iModel){
        this.iModel = iModel;
    }

    @Override
    public void rentCar(ICar iCar, IPerson iPerson, Date start, Date end){

        IFileManager fileManager = new JSonFileManager();

        iRentedCarPeriod = new RentedCarPeriod(iCar.getNumberPlate(),start,end,iCar.getRentPrice());
        this.iRentalPeriod = new RentalPeriod(iPerson.getFiscalCode(), start, end);

        iModel.removePerson(iPerson);

        /**
         * Assegno l'auto alla persona
         */
        iPerson.addCar(iRentedCarPeriod);

        /**
         * Assegno la persona all'auto
         */
        iModel.removeCar(iCar);
        iCar.setRentability(false);
        iCar.addTenant(iRentalPeriod);
        iModel.addPerson(iPerson);
        iModel.addCar(iCar);
        iModel.addCarToHistory(iCar);
        iModel.addPersonToHistory(iPerson);

        fileManager.writeList(fileManager.writePersonToJArray(iModel.getAllPersons().values()), "Person");
        fileManager.writeList(fileManager.writeCarToJArray(iModel.getAllCar().values()), "Car");
        fileManager.writeList(fileManager.writePersonToJArray(iModel.getAllPersonsHistory().values()), "PersonHistory");
        fileManager.writeList(fileManager.writeCarToJArray(iModel.getAllCarsHistory().values()), "CarHistory");
    }
}

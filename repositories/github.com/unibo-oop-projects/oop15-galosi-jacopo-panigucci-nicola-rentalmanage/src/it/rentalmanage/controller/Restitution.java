package it.rentalmanage.controller;

import it.rentalmanage.model.IModel;
import it.rentalmanage.model.IPerson;
import it.rentalmanage.model.IRentalPeriod;
import it.rentalmanage.model.IRentedCarPeriod;
import it.rentalmanage.model.filemanager.IFileManager;
import it.rentalmanage.model.filemanager.JSonFileManager;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by nicolapanigucci on 27/04/16.
 */
public class Restitution implements IRestitution {

    private IModel model;
    private IPerson person;
    private IRentedCarPeriod rentedCarPeriod;
    private Calendar calendar;

    public Restitution (final IModel iModel, final IPerson iPerson, final IRentedCarPeriod iRentedCarPeriod){
        this.model = iModel;
        this.person = iPerson;
        this.rentedCarPeriod = iRentedCarPeriod;
        this.calendar = Calendar.getInstance();
    }

    @Override
    public void restitutionCar() {

        this.calendar.setTime(new Date());
        this.calendar.add(Calendar.MONTH, -1);

        this.model.getAllCar().get(this.rentedCarPeriod.getNumberPlate()).setRentability(true);
        this.model.getAllCarsHistory().get(this.rentedCarPeriod.getNumberPlate()).setRentability(true);

        for (IRentalPeriod rentalPeriod : this.model.getAllCar().get(this.rentedCarPeriod.getNumberPlate()).getAllTenant()){
            if (rentalPeriod.getFCode().equals(this.person.getFiscalCode())){
                rentalPeriod.setEndDate(this.calendar.getTime());
            }
        }
        this.rentedCarPeriod.setEndDate(this.calendar.getTime());

        System.out.println(this.model.getAllCar().get(this.rentedCarPeriod.getNumberPlate()).isRentable());

        IFileManager fileManager = new JSonFileManager();
        fileManager.writeList(fileManager.writeCarToJArray(model.getAllCar().values()), "Car");
        fileManager.writeList(fileManager.writePersonToJArray(model.getAllPersons().values()), "Person");
        fileManager.writeList(fileManager.writePersonToJArray(model.getAllPersonsHistory().values()), "PersonHistory");
        fileManager.writeList(fileManager.writeCarToJArray(model.getAllCarsHistory().values()), "CarHistory");
    }
}

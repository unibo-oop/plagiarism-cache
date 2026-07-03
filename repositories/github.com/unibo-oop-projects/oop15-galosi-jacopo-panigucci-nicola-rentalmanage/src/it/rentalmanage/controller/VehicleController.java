package it.rentalmanage.controller;

import it.rentalmanage.model.DrivingLicence;
import it.rentalmanage.model.ICar;
import it.rentalmanage.model.IModel;
import it.rentalmanage.model.IRentalPeriod;
import it.rentalmanage.model.filemanager.IFileManager;
import it.rentalmanage.model.filemanager.JSonFileManager;

import java.util.Date;

/**
 * Created by nicolapanigucci on 27/04/16.
 */
public class VehicleController implements IVehicleController {

    private IFileManager manager;
    private ICar car;
    private IModel model;

    public VehicleController(final IModel iModel, final ICar iCar){
        this.manager = new JSonFileManager();
        this.model = iModel;
        this.car = iCar;
    }

    @Override
    public void updateVehicle(int rentPrice, DrivingLicence requestedLicence, int insuranceCost, Date insuranceExpiring, int carTaxCost, Date carTaxExpiring, int MOOTestCost, Date MOOTestExpiring) {
        this.car.setRentPrice(rentPrice);
        this.car.setRequestedLicence(requestedLicence);
        this.car.setInsuranceCost(insuranceCost);
        this.car.setInsuranceExpiring(insuranceExpiring);
        this.car.setCarTaxCost(carTaxCost);
        this.car.setCarTaxDate(carTaxExpiring);
        this.car.setMotTestCost(MOOTestCost);
        this.car.setMotTestDate(MOOTestExpiring);

        this.model.removeCar(car);
        this.model.addCar(car);
        manager.writeList(manager.writeCarToJArray(model.getAllCar().values()), "Car");

    }

    @Override
    public void deleteVehicle(){
        this.model.removeCar(car);
        manager.writeList(manager.writeCarToJArray(model.getAllCar().values()), "Car");
    }

}

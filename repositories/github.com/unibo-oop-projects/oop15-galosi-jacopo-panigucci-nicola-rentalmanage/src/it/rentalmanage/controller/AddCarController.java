package it.rentalmanage.controller;

import it.rentalmanage.model.ICar;
import it.rentalmanage.model.IModel;
import it.rentalmanage.model.Model;
import it.rentalmanage.model.filemanager.IFileManager;
import it.rentalmanage.model.filemanager.JSonFileManager;

/**
 * Created by utente on 09/03/2016.
 */
public class AddCarController implements IAddCarController {

    private IFileManager model ;
    private IModel iModel;

    public AddCarController(final IFileManager model, final IModel iModel) {
        this.model = model;
        this.iModel = iModel;
    }

    @Override
    public void writeCar(ICar car) {

        iModel.addCar(car);
        model.writeList(model.writeCarToJArray(iModel.getAllCar().values()), "Car");
    }
}

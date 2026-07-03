package it.rentalmanage.controller;

import it.rentalmanage.model.ICar;
import it.rentalmanage.model.IModel;
import it.rentalmanage.view.ICustomTableModelVehicle;

import java.util.Map;

/**
 * Created by nicolapanigucci on 17/03/16.
 */
public class TableRentableController implements ITableRantableController {

    private IModel model;
    private ICustomTableModelVehicle customTableModelVehicle;

    public TableRentableController(final IModel model, final ICustomTableModelVehicle viewModel){
        this.model = model;
        this.customTableModelVehicle = viewModel;
    }

    @Override
    public void showRentableCar() {
        Map<String,ICar> carMap = model.getAllCar();
        customTableModelVehicle.setElement(carMap);
        //customTableModelVehicle.fireTableDataChanged();
    }
}

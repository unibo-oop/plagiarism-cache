package it.rentalmanage.controller;

import it.rentalmanage.model.ICar;
import it.rentalmanage.model.IModel;
import it.rentalmanage.view.ICustomTableModelVehicle;

import java.util.Map;

/**
 * Created by utente on 09/03/2016.
 */
public class TableVehicleController implements ITableVehicleController {

    private IModel model;
    private ICustomTableModelVehicle customTableModelVehicle;

    public TableVehicleController(final IModel model, final ICustomTableModelVehicle viewModel) {
        this.model = model;
        this.customTableModelVehicle = viewModel;
    }

    @Override
    public void showCar() {
        Map<String,ICar> carMap = model.getAllCar();
        customTableModelVehicle.setElement(carMap);
        //customTableModelVehicle.fireTableDataChanged();
    }
}

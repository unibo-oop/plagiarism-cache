package it.rentalmanage.controller;

import it.rentalmanage.model.ICar;
import it.rentalmanage.model.IModel;
import it.rentalmanage.view.ICTMHistorianVehicle;

import java.util.Map;

/**
 * Created by nicolapanigucci on 03/05/16.
 */
public class TableHistorianVehicleController implements ITableHistorianVehicleController {

    private IModel model;
    private ICTMHistorianVehicle modelHistorianVehicle;

    public TableHistorianVehicleController(final IModel iModel, final ICTMHistorianVehicle ctmHistorianVehicle){
        this.model = iModel;
        this.modelHistorianVehicle = ctmHistorianVehicle;
    }

    @Override
    public void showHistorianVehicle(ICar car) {
        Map<String, ICar> historianVehicle = this.model.getAllCarsHistory();
        this.modelHistorianVehicle.setElement(historianVehicle, car);
        //this.modelHistorianVehicle.fireTableDataChanged();
    }
}

package it.rentalmanage.view;

import it.rentalmanage.controller.ITableHistorianVehicleController;
import it.rentalmanage.controller.TableHistorianVehicleController;
import it.rentalmanage.model.ICar;
import it.rentalmanage.model.IModel;

import javax.swing.*;

/**
 * Created by nicolapanigucci on 14/03/16.
 */
public class TableHistorianVehicle extends JScrollPane {

    private JTable tbHistorian;
    private ITableHistorianVehicleController tableHistorianVehicleController;
    private ICTMHistorianVehicle customTableModelHistorian;

    public TableHistorianVehicle(final MainFrame prevPanel, final IModel iModel, final ICar iCar) {

        customTableModelHistorian = new CTMHistorianVehicle(iModel);
        this.tbHistorian = new JTable(customTableModelHistorian);
        this.tableHistorianVehicleController = new TableHistorianVehicleController(iModel, customTableModelHistorian);
        tableHistorianVehicleController.showHistorianVehicle(iCar);
        tbHistorian.setFillsViewportHeight(true);

        this.setViewportView(tbHistorian);

        revalidate();
    }
}

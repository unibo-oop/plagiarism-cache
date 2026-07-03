package it.rentalmanage.view;

import it.rentalmanage.model.IModel;

/**
 * Created by nicolapanigucci on 26/02/16.
 */
public class StorageVehicle extends Storage {

    public StorageVehicle(final MainFrame prevPanel, final IModel iModel){
        super();

        setAddListener(e -> prevPanel.setPanel(new AddVehicle(prevPanel, iModel, iModel.getAllCar().keySet())));
        setTable(new TableVehicle(prevPanel, iModel));

        prevPanel.setVisibleBtnBackListener(true, e -> prevPanel.setPanel(new HomePage(prevPanel, iModel)));
    }

}

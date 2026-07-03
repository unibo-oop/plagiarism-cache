package it.rentalmanage.view;

import it.rentalmanage.model.ICar;

import javax.swing.table.TableModel;
import java.util.Map;

/**
 * Created by nicolapanigucci on 04/05/16.
 */
public interface ICustomTableModelVehicle extends TableModel{

    /**
     * Estrae la lista dei veicoli
     * @param vehicleList
     */
    void setElement(Map<String,ICar> vehicleList);
}

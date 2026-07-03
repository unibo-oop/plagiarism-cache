package it.rentalmanage.view;

import it.rentalmanage.model.ICar;

import javax.swing.table.TableModel;
import java.util.Map;

/**
 * Created by nicolapanigucci on 03/05/16.
 */
public interface ICTMHistorianVehicle extends TableModel{

    /**
     * Estrae la lista delle persone che hanno noleggiato 'iCar'
     * @param carMap
     * @param iCar
     */
    void setElement(Map<String, ICar> carMap, ICar iCar);
}

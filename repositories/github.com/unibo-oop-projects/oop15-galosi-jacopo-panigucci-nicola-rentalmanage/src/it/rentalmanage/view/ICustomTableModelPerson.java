package it.rentalmanage.view;

import it.rentalmanage.model.IPerson;

import javax.swing.table.TableModel;
import java.util.Map;

/**
 * Created by nicolapanigucci on 04/05/16.
 */
public interface ICustomTableModelPerson extends TableModel {

    /**
     * Estrae la lista delle persone
     * @param personList
     */
    void setElement(Map<String ,IPerson> personList);
}

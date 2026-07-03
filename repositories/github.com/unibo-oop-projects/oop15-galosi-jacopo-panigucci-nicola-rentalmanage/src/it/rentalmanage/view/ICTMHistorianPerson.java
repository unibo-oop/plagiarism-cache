package it.rentalmanage.view;

import it.rentalmanage.model.IPerson;

import javax.swing.table.TableModel;
import java.util.Map;

/**
 * Created by nicolapanigucci on 03/05/16.
 */
public interface ICTMHistorianPerson extends TableModel{

    /**
     * Estrae la lista delle auto noleggiate da 'iPerson'
     * @param historianPerson
     * @param iPerson
     */
    void setElement(Map<String, IPerson> historianPerson, IPerson iPerson);
}

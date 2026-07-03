package it.rentalmanage.controller;

import it.rentalmanage.model.IModel;
import it.rentalmanage.model.IPerson;
import it.rentalmanage.view.ICTMHistorianPerson;

import java.util.Map;

/**
 * Created by nicolapanigucci on 03/05/16.
 */
public class TableHistorianPersonController implements ITableHistorianPersonController {

    private IModel model;
    private ICTMHistorianPerson modelHistorianPerson;

    public  TableHistorianPersonController(final IModel iModel, final ICTMHistorianPerson ctmHistorianPerson){
        this.model = iModel;
        this.modelHistorianPerson = ctmHistorianPerson;
    }

    @Override
    public void showHistorianPerson(IPerson person) {
        Map<String, IPerson> historianPerson = this.model.getAllPersonsHistory();
        this.modelHistorianPerson.setElement(historianPerson, person);
        //this.modelHistorianPerson.fireTableDataChanged();
    }
}

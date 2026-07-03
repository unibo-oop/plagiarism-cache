package it.rentalmanage.controller;

import it.rentalmanage.model.IModel;
import it.rentalmanage.model.IPerson;
import it.rentalmanage.view.ICustomTableModelPerson;

import java.util.Map;

/**
 * Created by utente on 04/03/2016.
 */
public class TablePersonController implements ITablePersonController {

    private IModel model;
    private ICustomTableModelPerson customTableModelPerson;

    public TablePersonController(final IModel model, final ICustomTableModelPerson viewModel) {
        this.model = model;
        this.customTableModelPerson = viewModel;
    }


    @Override
    public void showPerson() {
        Map<String,IPerson> personMap = this.model.getAllPersons();
        this.customTableModelPerson.setElement(personMap);
        //this.customTableModelPerson.fireTableDataChanged();
    }
}

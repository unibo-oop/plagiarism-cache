package it.rentalmanage.controller;

import it.rentalmanage.model.IModel;
import it.rentalmanage.model.IPerson;
import it.rentalmanage.model.filemanager.IFileManager;

/**
 * Created by utente on 04/03/2016.
 */
public class AddPersonController implements IAddPersonController {

    private IFileManager manager;
    private IModel iModel;

    public AddPersonController(final IFileManager iFileManager, final IModel iModel) {
        this.manager = iFileManager;
        this.iModel = iModel;
    }

    @Override
    public void writePerson(IPerson person) {
        iModel.addPerson(person);
        manager.writeList(manager.writePersonToJArray(iModel.getAllPersons().values()), "Person");

    }

}

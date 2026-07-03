package it.rentalmanage.controller;

import it.rentalmanage.model.IModel;
import it.rentalmanage.model.IPerson;
import it.rentalmanage.model.filemanager.IFileManager;
import it.rentalmanage.model.filemanager.JSonFileManager;

/**
 * Created by utente on 04/03/2016.
 */
public class PersonController implements IPersonController {

    private IFileManager manager;
    private IPerson person;
    private IModel model;

    public PersonController(final IModel iModel, final IPerson iPerson) {
        this.manager = new JSonFileManager();
        this.model = iModel;
        this.person = iPerson;
    }

    @Override
    public void updatePerson(String surname, String name, String address, String telephone) {

        this.person.setSurname(surname);
        this.person.setName(name);
        this.person.setAddress(address);
        this.person.setPhoneNumber(telephone);

        this.model.removePerson(person);
        model.addPerson(person);
        manager.writeList(manager.writePersonToJArray(model.getAllPersons().values()), "Person");

    }

    @Override
    public void deletePerson(){
        this.model.removePerson(person);
        manager.writeList(manager.writePersonToJArray(model.getAllPersons().values()), "Person");
    }

}

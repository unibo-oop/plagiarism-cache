package it.rentalmanage.view;

import it.rentalmanage.model.*;

/**
 * Created by nicolapanigucci on 22/02/16.
 */
public class StoragePerson extends Storage{

    public StoragePerson(final MainFrame prevPanel, final IModel iModel){
        super();

        setAddListener(e -> prevPanel.setPanel(new AddPerson(prevPanel, iModel, iModel.getAllPersons().keySet())));
        setTable(new TablePerson(prevPanel, iModel));

        prevPanel.setVisibleBtnBackListener(true, e -> prevPanel.setPanel(new HomePage(prevPanel, iModel)));
    }
}

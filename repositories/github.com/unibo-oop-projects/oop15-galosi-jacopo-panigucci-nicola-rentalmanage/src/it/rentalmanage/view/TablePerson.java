package it.rentalmanage.view;

import it.rentalmanage.controller.ITablePersonController;
import it.rentalmanage.controller.TablePersonController;
import it.rentalmanage.model.IModel;
import it.rentalmanage.model.IPerson;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

/**
 * Created by nicolapanigucci on 22/02/16.
 */
public class TablePerson extends JScrollPane {

    private Map<String,IPerson> mapPerson;
    private List<IPerson> listPerson;
    private JTable tbPerson;
    private ITablePersonController tablePersonController;
    private ICustomTableModelPerson customTableModelPerson;

    public TablePerson(final MainFrame prevPanel, final IModel iModel){

        customTableModelPerson = new CustomTableModelPerson();
        this.tbPerson = new JTable(customTableModelPerson);
        this.tablePersonController = new TablePersonController(iModel, customTableModelPerson);
        tablePersonController.showPerson();
        tbPerson.setFillsViewportHeight(true);

        this.setViewportView(tbPerson);

        this.mapPerson = iModel.getAllPersons();

        listPerson = new ArrayList<>();
        Iterator<Map.Entry<String,IPerson>> iterator = mapPerson.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,IPerson> entry = iterator.next();
            listPerson.add(entry.getValue());
        }

        /**
         * Gestisce il doppio click su una riga della tabella.
         * Permette di visualizzare il pannello con tutte le informazioni di una persona
         */
        tbPerson.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                int valueRow = tbPerson.getSelectedRow();

                if((valueRow != -1) && (e.getClickCount() == 2)){

                    Collections.sort(listPerson, (person1, person2) -> {
                        String person1NS = person1.getSurname().toLowerCase() + person1.getName().toLowerCase();
                        String person2NS = person2.getSurname().toLowerCase() + person2.getName().toLowerCase();
                        return person1NS.compareTo(person2NS);
                    });

                    prevPanel.setPanel(new ViewPerson(prevPanel, iModel, listPerson.get(valueRow), mapPerson.keySet()));
                }
            }
        });
        revalidate();

    }
}

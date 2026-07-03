package it.rentalmanage.view;

import it.rentalmanage.model.IPerson;
import javax.swing.table.AbstractTableModel;
import java.util.*;

/**
 * Created by nicolapanigucci on 22/02/16.
 */
public class CustomTableModelPerson extends AbstractTableModel implements ICustomTableModelPerson {

    private List<IPerson> personList;
    private String[] header;

    public CustomTableModelPerson() {
        personList = new ArrayList<>();
        header = new String[]{"Surname", "Name", "Fiscal Code", "Tel", "Driv. Licence"};
    }

    @Override
    public int getRowCount() {
        return this.personList.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public String getColumnName(int column) {
        return header[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        switch (columnIndex){
            case 0:
                return personList.get(rowIndex).getSurname();
            case 1:
                return personList.get(rowIndex).getName();
            case 2:
                return personList.get(rowIndex).getFiscalCode();
            case 3:
                return personList.get(rowIndex).getPhoneNumber();
            case 4:
                return personList.get(rowIndex).getDrivingLicense();
        }

        return "";
    }

    @Override
    public void setElement(Map<String,IPerson> personList){
        setElement(personList.values());
    }

    /**
     * Ordina la Collection di IPerson e la fa visualizzare nella tabella
     * @param personCollection
     */
    private void setElement(Collection<IPerson> personCollection){
        personList.clear();
        List<IPerson> personList = new LinkedList<>(personCollection);

        /**
         * ordinamento
         */
        Collections.sort(personList, (person1, person2) -> {
            String person1NS = person1.getSurname().toLowerCase() + person1.getName().toLowerCase();
            String person2NS = person2.getSurname().toLowerCase() + person2.getName().toLowerCase();
            return person1NS.compareTo(person2NS);
        });

        this.personList.addAll(personList);
    }
}

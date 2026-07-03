package it.rentalmanage.view;

import it.rentalmanage.model.ICar;
import it.rentalmanage.model.IModel;
import it.rentalmanage.model.IRentalPeriod;

import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by nicolapanigucci on 14/03/16.
 */
public class CTMHistorianVehicle extends AbstractTableModel implements ICTMHistorianVehicle {

    private List<IRentalPeriod> iRentalPeriods;
    private String[] header;
    private IModel iModel;
    private SimpleDateFormat sdf;
    private Calendar cal;

    public CTMHistorianVehicle(final IModel iModel){
        this.iRentalPeriods = new ArrayList<>();
        this.header = new String[]{"Fiscal Code", "Surname", "Name", "Start Date", "End Date"};
        this.iModel = iModel;
        this.sdf = new SimpleDateFormat("dd/MM/yyyy");
    }


    @Override
    public int getRowCount() {
        return this.iRentalPeriods.size();
    }

    @Override
    public int getColumnCount() {
        return this.header.length;
    }

    @Override
    public String getColumnName(int column) {
        return this.header[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        String fiscalCode = this.iRentalPeriods.get(rowIndex).getFCode();

        switch (columnIndex){
            case 0:
                return fiscalCode;
            case 1:
                return this.iModel.getPersonFromFCodeHistory(fiscalCode).getSurname();
            case 2:
                return this.iModel.getPersonFromFCodeHistory(fiscalCode).getName();
            case 3:
                this.cal = Calendar.getInstance();
                this.cal.setTime(this.iRentalPeriods.get(rowIndex).getStartDate());
                this.cal.add(Calendar.MONTH,1);
                return this.sdf.format(this.cal.getTime());
            case 4:
                this.cal = Calendar.getInstance();
                this.cal.setTime(this.iRentalPeriods.get(rowIndex).getEndDate());
                this.cal.add(Calendar.MONTH,1);
                return this.sdf.format(this.cal.getTime());
        }

        return "";
    }

    @Override
    public void setElement(Map<String, ICar> carMap, ICar iCar) {
        Iterator<Map.Entry<String,ICar>> iterator = carMap.entrySet().iterator();

        while (iterator.hasNext()){
            Map.Entry<String,ICar> entry = iterator.next();
            String key = entry.getKey();

            if (key.equals(iCar.getNumberPlate())){
                setElement(iCar.getAllTenant());
            }
        }
    }

    /**
     * Ordina la Collection di IRentalPeriod e la fa visualizzare nella tabella
     * @param iRentalPeriodCollection
     */
    private void setElement(Collection<IRentalPeriod> iRentalPeriodCollection){
        this.iRentalPeriods.clear();
        List<IRentalPeriod> list = new LinkedList<>(iRentalPeriodCollection);

        /**
         * ordinamento
         */
        Collections.sort(list, (person1, person2) -> {
            String person1NS = this.iModel.getPersonFromFCodeHistory(person1.getFCode()).getSurname().toLowerCase() +
                    this.iModel.getPersonFromFCodeHistory(person1.getFCode()).getName().toLowerCase();

            String person2NS = this.iModel.getPersonFromFCodeHistory(person2.getFCode()).getSurname().toLowerCase() +
                    this.iModel.getPersonFromFCodeHistory(person2.getFCode()).getName().toLowerCase();
            return person1NS.compareTo(person2NS);
        });

        this.iRentalPeriods.addAll(list);
    }


}

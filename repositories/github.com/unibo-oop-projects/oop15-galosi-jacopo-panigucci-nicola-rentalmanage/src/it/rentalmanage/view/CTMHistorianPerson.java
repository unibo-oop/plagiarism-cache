package it.rentalmanage.view;

import it.rentalmanage.model.IModel;
import it.rentalmanage.model.IPerson;
import it.rentalmanage.model.IRentedCarPeriod;

import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by nicolapanigucci on 09/04/16.
 */
public class CTMHistorianPerson extends AbstractTableModel implements ICTMHistorianPerson {

    private List<IRentedCarPeriod> iRentedCarPeriods;
    private String[] header;
    private IModel iModel;
    private SimpleDateFormat sdf;
    private Calendar cal;

    public CTMHistorianPerson(final IModel iModel){
        this.iRentedCarPeriods = new ArrayList<>();
        this.header = new String[]{"Number Plate", "Manufacturer", "Model", "Price", "Start Date", "End Date"};
        this.iModel = iModel;
        this.sdf = new SimpleDateFormat("dd/MM/yyyy");
    }

    @Override
    public int getRowCount() {
        return this.iRentedCarPeriods.size();
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

        String numberPlate = this.iRentedCarPeriods.get(rowIndex).getNumberPlate();

        switch (columnIndex){
            case 0:
                return numberPlate;
            case 1:
                return this.iModel.getCarFromNumPlateHistory(numberPlate).getManufactorer();
            case 2:
                return this.iModel.getCarFromNumPlateHistory(numberPlate).getModel();
            case 3:
                return this.iRentedCarPeriods.get(rowIndex).getRentedPrice();
            case 4:
                this.cal = Calendar.getInstance();
                this.cal.setTime(this.iRentedCarPeriods.get(rowIndex).getStartDate());
                this.cal.add(Calendar.MONTH,1);
                return this.sdf.format(this.cal.getTime());
            case 5:
                this.cal = Calendar.getInstance();
                this.cal.setTime(this.iRentedCarPeriods.get(rowIndex).getEndDate());
                this.cal.add(Calendar.MONTH,1);
                return this.sdf.format(this.cal.getTime());
        }

        return "";
    }

    @Override
    public void setElement(Map<String ,IPerson> historianPerson, IPerson iPerson){

        Iterator<Map.Entry<String,IPerson>> iterator = historianPerson.entrySet().iterator();

        while (iterator.hasNext()){
            Map.Entry<String,IPerson> entry = iterator.next();
            String key = entry.getKey();

            if (key.equals(iPerson.getFiscalCode())){
                setElement(iPerson.getAllRentedCar());
            }
        }

    }

    /**
     * Ordino la Collection di IRentedCarPeriod e la fa visualizzare nella tabella
     * @param iRentedCarPeriods
     */
    private void setElement(Collection<IRentedCarPeriod> iRentedCarPeriods){
        this.iRentedCarPeriods.clear();
        List<IRentedCarPeriod> list = new LinkedList<>(iRentedCarPeriods);

        /**
         * ordinamento
         */
        Collections.sort(list, (car1, car2) -> {
            String car1NS = this.iModel.getCarFromNumPlateHistory(car1.getNumberPlate()).getManufactorer().toLowerCase() +
                    this.iModel.getCarFromNumPlateHistory(car1.getNumberPlate()).getModel().toLowerCase();

            String car2NS = this.iModel.getCarFromNumPlateHistory(car1.getNumberPlate()).getManufactorer().toLowerCase() +
                    this.iModel.getCarFromNumPlateHistory(car1.getNumberPlate()).getModel().toLowerCase();
            return car1NS.compareTo(car2NS);
        });

        this.iRentedCarPeriods.addAll(list);
    }
}

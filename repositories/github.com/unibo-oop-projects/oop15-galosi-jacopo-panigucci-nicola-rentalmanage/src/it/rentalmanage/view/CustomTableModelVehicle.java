package it.rentalmanage.view;

import it.rentalmanage.controller.Expiring;
import it.rentalmanage.model.ICar;

import javax.swing.table.AbstractTableModel;
import java.util.*;

/**
 * Created by nicolapanigucci on 29/02/16.
 */
public class CustomTableModelVehicle extends AbstractTableModel implements ICustomTableModelVehicle {

    private List<ICar> vehiclesList;
    private String[] header;

    public CustomTableModelVehicle() {
        vehiclesList = new ArrayList<>();
        header = new String[]{"Manufactorer", "Model", "Number Plate", "Rentable", "Rent Price", "Expiry Date"};
    }

    @Override
    public int getRowCount() {
        return this.vehiclesList.size();
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
                return vehiclesList.get(rowIndex).getManufactorer();
            case 1:
                return vehiclesList.get(rowIndex).getModel();
            case 2:
                return vehiclesList.get(rowIndex).getNumberPlate();
            case 3:
                if(vehiclesList.get(rowIndex).isRentable()){
                    return new String("Noleggiabile");
                }
                return new String("Non noleggiabile");
            case 4:
                return vehiclesList.get(rowIndex).getRentPrice();
            case 5:
                List<String> exp = new Expiring(vehiclesList.get(rowIndex)).checkExpiring();
                if (exp.size() == 0 ){
                    return "";
                }
                return exp;
        }

        return "";
    }

    @Override
    public void setElement(Map<String ,ICar> vehicleList){
        setElement(vehicleList.values());
    }

    /**
     * Ordina la Collection di ICar e la fa visualizzare nella lista
     * @param vehicleCollection
     */
    private void setElement(Collection<ICar> vehicleCollection){
        vehiclesList.clear();
        List<ICar> carList = new LinkedList<>(vehicleCollection);

        /**
         * ordinamento
         */
        Collections.sort(carList, (car1, car2) -> {
            String car1MM = car1.getManufactorer().toLowerCase()+car1.getModel().toLowerCase();
            String car2MM = car2.getManufactorer().toLowerCase()+car2.getModel().toLowerCase();
            return car1MM.compareTo(car2MM);
        });

        vehiclesList.addAll(carList);

    }

}

package it.rentalmanage.view;

import it.rentalmanage.controller.ITableVehicleController;
import it.rentalmanage.controller.TableVehicleController;
import it.rentalmanage.model.ICar;
import it.rentalmanage.model.IModel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

/**
 * Created by nicolapanigucci on 29/02/16.
 */
public class TableVehicle extends JScrollPane {

    private Map<String,ICar> carMap;
    private List<ICar> listVehicles;
    private JTable tbVehicles;
    private ITableVehicleController tableVehicleController;
    private ICustomTableModelVehicle customTableModelVehicle;

    public TableVehicle(final MainFrame prevPanel, final IModel iModel){

        this.customTableModelVehicle = new CustomTableModelVehicle();
        this.tbVehicles = new JTable(customTableModelVehicle);
        this.tableVehicleController = new TableVehicleController(iModel, customTableModelVehicle);
        tableVehicleController.showCar();
        tbVehicles.setFillsViewportHeight(true);

        this.setViewportView(tbVehicles);

        this.carMap = iModel.getAllCar();

        listVehicles = new ArrayList<>();
        listVehicles =new ArrayList<>();
        Iterator<Map.Entry<String,ICar>> iterator = carMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,ICar> entry = iterator.next();
            listVehicles.add(entry.getValue());
        }

        /**
         * Gestione doppio click sulla riga della tabella
         */
        tbVehicles.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                int valueRow= tbVehicles.getSelectedRow();
                if((valueRow != -1) && (e.getClickCount() == 2)){
                    Collections.sort(listVehicles, (car1, car2) -> {
                        String car1MM = car1.getManufactorer().toLowerCase()+car1.getModel().toLowerCase();
                        String car2MM = car2.getManufactorer().toLowerCase()+car2.getModel().toLowerCase();
                        return car1MM.compareTo(car2MM);
                    });
                    prevPanel.setPanel(new ViewVehicle(prevPanel, listVehicles.get(valueRow),iModel));

                }

                Collections.sort(listVehicles, (car1, car2) -> {
                    String car1MM = car1.getManufactorer().toLowerCase() + car1.getModel().toLowerCase();
                    String car2MM = car2.getManufactorer().toLowerCase() + car2.getModel().toLowerCase();
                    return car1MM.compareTo(car2MM);
                });
            }
        });

        revalidate();

    }
}

package it.rentalmanage.view;

import it.rentalmanage.controller.*;
import it.rentalmanage.model.IModel;
import it.rentalmanage.model.IPerson;
import it.rentalmanage.model.IRentedCarPeriod;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

/**
 * Created by nicolapanigucci on 05/04/16.
 */
public class TableHistorianPerson extends JScrollPane {

    private List<IRentedCarPeriod> iRentedCarPeriodCollection;
    private JTable tbHistorian;
    private ITableHistorianPersonController tableHistorianPersonController;
    private ICTMHistorianPerson ctmHistorianPerson;

    public TableHistorianPerson(final MainFrame prevPanel, final IModel iModel, final IPerson iPerson){

        this.ctmHistorianPerson = new CTMHistorianPerson(iModel);
        this.tbHistorian = new JTable(ctmHistorianPerson);
        this.tableHistorianPersonController = new TableHistorianPersonController(iModel, ctmHistorianPerson);
        tableHistorianPersonController.showHistorianPerson(iPerson);
        tbHistorian.setFillsViewportHeight(true);

        this.setViewportView(tbHistorian);

        Iterator<Map.Entry<String,IPerson>> iterator = iModel.getAllPersonsHistory().entrySet().iterator();
        while (iterator.hasNext()){

            Map.Entry<String,IPerson> entry = iterator.next();
            String key = entry.getKey();
            if (key.equals(iPerson.getFiscalCode())){
                this.iRentedCarPeriodCollection = (List<IRentedCarPeriod>) iPerson.getAllRentedCar();
            }
        }

        /**
         * Gestisce il doppio click su una riga della tabella.
         * Permette di registrare la riconsegna di un veicolo
         */
        tbHistorian.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                int valueRow = tbHistorian.getSelectedRow();
                if((valueRow!=-1) && (e.getClickCount() == 2) &&
                        !iModel.getCarFromNumPlateHistory(iRentedCarPeriodCollection.get(valueRow).getNumberPlate()).isRentable()){
                    int result = JOptionPane.showOptionDialog(null, "Return a car?", "Return...", JOptionPane.YES_NO_OPTION,
                            JOptionPane.INFORMATION_MESSAGE, null, null, null);
                    if (result == JOptionPane.YES_OPTION){
                        IRestitution restitution = new Restitution(iModel, iPerson, iRentedCarPeriodCollection.get(valueRow));
                        restitution.restitutionCar();
                    }
                    prevPanel.setPanel(new ViewPerson(prevPanel, iModel, iPerson, null));
                }
            }
        });

        /**
         * Permette di colorare i valori di ogni cella della tabella
         */
        tbHistorian.setDefaultRenderer(Object.class, (table, value, isSelected, hasFocus, row, column) -> {

            JLabel label = new JLabel(value.toString());
            label.setOpaque(true);

            label.setBackground(Color.WHITE);

            if (isSelected){
                label.setBackground(Color.lightGray);

            }else {
                label.setBackground(Color.WHITE);
            }

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(iRentedCarPeriodCollection.get(row).getEndDate());
            calendar.add(Calendar.MONTH, 1);

            if (calendar.getTime().after(new Date())){
                label.setForeground(Color.RED);
            }else {
                label.setForeground(Color.GREEN);
            }
            return label;

        });

        revalidate();

    }
}

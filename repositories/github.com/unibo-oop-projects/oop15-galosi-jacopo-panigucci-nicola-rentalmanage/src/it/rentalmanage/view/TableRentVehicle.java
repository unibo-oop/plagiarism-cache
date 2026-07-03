package it.rentalmanage.view;

import it.rentalmanage.controller.ITableRantableController;
import it.rentalmanage.controller.RentController;
import it.rentalmanage.controller.TableRentableController;
import it.rentalmanage.model.*;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * Created by nicolapanigucci on 17/03/16.
 */
public class TableRentVehicle extends JScrollPane {

    private DateFormat format;
    private Calendar startDate;
    private JLabel lblEndDate;
    private JTextField tfEndDate;

    private List<ICar> listRentableVehicles;
    private JTable tbRentableVehicles;
    private ITableRantableController tableRentController;
    private CustomTableModelRentable customTableModelRentable;

    public  TableRentVehicle(final MainFrame prevPanel, final IPerson iPerson, final IModel iModel, final List<ICar> carList){

        this.customTableModelRentable = new CustomTableModelRentable();
        this.tbRentableVehicles = new JTable(customTableModelRentable);
        this.tableRentController = new TableRentableController(iModel, customTableModelRentable);
        tableRentController.showRentableCar();
        tbRentableVehicles.setFillsViewportHeight(true);

        this.setViewportView(tbRentableVehicles);

        /**
         * Data attuale
         */
        this.startDate = Calendar.getInstance();
        this.startDate.setTime(new Date());
        this.startDate.add(Calendar.MONTH, -1);

        this.format = new SimpleDateFormat("dd/MM/yyyy");
        format.setLenient(false);

        listRentableVehicles=new ArrayList<>(carList);

        /**
         * Gestisce il doppio click sulla riga della tabella.
         * Con il doppio click assegno un auto al cliente se passano tutti i controlli
         */
        tbRentableVehicles.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                int valueRow = tbRentableVehicles.getSelectedRow();
                if((valueRow != -1) && (e.getClickCount() == 2)){

                    /**
                     * Controlla se possiede la patente
                     */
                    if(!iPerson.getDrivingLicense().contains(listRentableVehicles.get(valueRow).getRequestedLicense())){

                        JOptionPane.showMessageDialog(TableRentVehicle.this, "You have not the licence! Choose another vehicle");
                        return;
                    }

                    /**
                     * Crea un pannello per inserire la data di fine noleggio
                     */
                    JPanel panel = new JPanel();
                    lblEndDate = new JLabel("End Date : ");
                    tfEndDate = new JTextField(10);
                    tfEndDate.setText("dd/MM/yyyy");
                    panel.add(lblEndDate);
                    panel.add(tfEndDate);

                    boolean isValidDate;
                    do {
                        int result = JOptionPane.showOptionDialog(null,panel,"Start/End Date",JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,null,null,null);
                        if (result == JOptionPane.YES_OPTION){

                            /**
                             * Data di inizio noleggio
                             */
                            Date start = startDate.getTime();

                            /**
                             * Data di fine noleggio
                             */
                            isValidDate = checkDate(tfEndDate.getText());
                            if (isValidDate){
                                try {
                                    Date endDate = format.parse(tfEndDate.getText());
                                    Calendar cal = Calendar.getInstance();
                                    cal.setTime(endDate);
                                    cal.add(Calendar.MONTH, -1);

                                    RentController rentController = new RentController(iModel);
                                    rentController.rentCar(listRentableVehicles.get(valueRow), iPerson, start, cal.getTime());

                                } catch (ParseException e1) {
                                    //ho già controllato la validità della data
                                }
                            }

                        }else {
                            return;
                        }
                    }while (!isValidDate);

                    prevPanel.setPanel(new ViewPerson(prevPanel, iModel, iPerson, null));

                }

                Collections.sort(listRentableVehicles, (car1, car2) -> {
                    String car1MM = car1.getManufactorer().toLowerCase() + car1.getModel().toLowerCase();
                    String car2MM = car2.getManufactorer().toLowerCase() + car2.getModel().toLowerCase();
                    return car1MM.compareTo(car2MM);
                });

            }
        });

        revalidate();
    }

    /**
     * Controlla la validità della data inserita
     * @param date
     * @return true se la data è valida
     */
    private boolean checkDate(String date){

        Date dateValid ;
        Calendar cal ;
        try {
            dateValid = format.parse(date);
            cal = Calendar.getInstance();
            cal.setTime(dateValid);
            cal.add(Calendar.MONTH, -1); //mesi da 0 a 11

            if (startDate.before(cal) && ((cal.get(Calendar.YEAR) - startDate.get(Calendar.YEAR)) < 20)){ //se start viene prima di cal e il noleggio è al massimo di 20 anni
                return true;
            }else {
                JOptionPane.showMessageDialog(this, "The date isn't later than the current!");
                return false;
            }

        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Wrong format date!");
            return false;
        }
    }
}

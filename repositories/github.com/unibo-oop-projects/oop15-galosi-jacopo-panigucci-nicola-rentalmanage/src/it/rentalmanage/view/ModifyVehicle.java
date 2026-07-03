package it.rentalmanage.view;

import it.rentalmanage.controller.*;
import it.rentalmanage.model.*;
import javax.swing.*;
import java.awt.*;

/**
 * Created by nicolapanigucci on 01/03/16.
 */
public class ModifyVehicle extends FormVehicles {

    private JLabel manufacturer;
    private JLabel model;
    private JLabel carSeats;
    private JLabel numberPlate;
    private JTextField tfRentPrice;
    private JComboBox<DrivingLicence> cbDrvLicense;
    private JLabel lblRentable;
    private JLabel isRentable;

    private ICar car;

    public ModifyVehicle(final MainFrame prevPanel, final ICar vehicle, final IModel iModel) {
        super(prevPanel,iModel);

        panelTitle.add(new JLabel("MODIFY VEHICLE"));

        this.car = vehicle;

        this.manufacturer = new JLabel();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        c.insets = otherLines;
        panelForm.add(manufacturer, c);

        this.tfRentPrice = new JTextField(5);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 3;
        c.gridy = 0;
        c.insets = otherLines;
        panelForm.add(tfRentPrice, c);

        this.numberPlate = new JLabel();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        c.insets = otherLines;
        panelForm.add(numberPlate, c);

        this.carSeats = new JLabel();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 3;
        c.gridy = 1;
        c.insets = otherLines;
        panelForm.add(carSeats, c);

        this.model = new JLabel();
        this.model.setHorizontalAlignment(JLabel.CENTER);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 2;
        c.insets = firstLine;
        panelForm.add(model, c);

        this.cbDrvLicense = new JComboBox<>(DrivingLicence.values());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 3;
        c.insets = otherLines;
        c.anchor = GridBagConstraints.CENTER;
        panelForm.add(cbDrvLicense, c);

        this.lblRentable = new JLabel("Stato :");
        this.lblRentable.setHorizontalAlignment(JLabel.RIGHT);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 4;
        c.insets = otherLines;
        panelForm.add(lblRentable, c);

        this.isRentable = new JLabel();
        this.isRentable.setHorizontalAlignment(JLabel.CENTER);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 4;
        c.insets = otherLines;
        panelForm.add(isRentable, c);

        this.manufacturer.setText("  " + vehicle.getManufactorer());
        this.tfRentPrice.setText("" + vehicle.getRentPrice());
        this.numberPlate.setText("  " + vehicle.getNumberPlate());
        this.carSeats.setText("  " + vehicle.getCarSeats());
        this.model.setText("  " + vehicle.getModel());
        this.cbDrvLicense.setSelectedItem(vehicle.getRequestedLicense());

        if (vehicle.isRentable()){
            isRentable.setText("Rentable");
        }else {
            isRentable.setText("Not Rentable");
        }

        btnPay.setVisible(false);
        btnRemove.setVisible(true);
        btnSaveModify.setText("Modify");

        /**
         * Aggiorna i dati del veicolo
         */
        btnSaveModify.addActionListener(e -> {
            if(checkInput()){

                IVehicleController vehicleController = new VehicleController(iModel, this.car);
                vehicleController.updateVehicle(Integer.parseInt(tfRentPrice.getText()), (DrivingLicence) cbDrvLicense.getSelectedItem(),
                        vehicle.getInsuranceCost() ,vehicle.getInsuranceExpiring(), vehicle.getCarTaxCost(),
                        vehicle.getCarTaxDate(), vehicle.getMotTestCost(), vehicle.getMotTestDate());

                prevPanel.setPanel(new StorageVehicle(prevPanel, iModel));
            }
        });

        /**
         * Elimina il veicolo
         */
        btnRemove.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(null, "Do you want erase it?", "Warning", JOptionPane.YES_NO_OPTION);
            IJOptionPaneAnswer optionPaneAnswer = new JOptionePaneAnswer();
            optionPaneAnswer.delVehicle(result, prevPanel, vehicle, iModel);
        });

        prevPanel.setVisibleBtnBackListener(true, e -> prevPanel.setPanel(new ViewVehicle(prevPanel, vehicle, iModel)));

    }

    /**
     * Controlla i valori inseriti dall'utente
     * @return true se sono validi
     */
    private boolean checkInput(){

        String rentPrice = tfRentPrice.getText();
        if(!rentPrice.matches("\\A\\d+\\z")) {
            JOptionPane.showMessageDialog(this, "Wrong rent price!");
            return false;
        }

        if(cbDrvLicense.getSelectedIndex() == -1){
            JOptionPane.showMessageDialog(this, "Wrong license!");
            return false;
        }

        return true;
    }

}

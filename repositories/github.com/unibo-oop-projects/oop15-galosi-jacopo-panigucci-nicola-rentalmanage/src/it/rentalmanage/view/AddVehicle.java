package it.rentalmanage.view;

import it.rentalmanage.controller.AddCarController;
import it.rentalmanage.controller.IAddCarController;
import it.rentalmanage.model.*;
import it.rentalmanage.model.filemanager.JSonFileManager;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.util.*;

/**
 * Created by nicolapanigucci on 01/03/16.
 */
public class AddVehicle extends FormVehicles {

    private JLabel lblInsuranceCost;
    private JLabel lblInsuranceExpiring;
    private JLabel lblCarTaxCost;
    private JLabel lblCarTaxDate;
    private JLabel lblMOTTestCost;
    private JLabel lblMOTTestDate;

    private JTextField tfManufacturer;
    private JTextField tfModel;
    private JTextField tfRentPrice;
    private JTextField tfNumberPlate;
    private JTextField tfInsuranceCost;
    private JTextField tfInsuranceExpiring;
    private JTextField tfCarSeats;
    private JComboBox<DrivingLicence> cbDrvLicense;
    private JTextField tfCarTaxCost;
    private JTextField tfCarTaxDate;
    private JTextField tfMOTTestCost;
    private JTextField tfMOTTestDate;
    private Set<String> allNumbPlate;

    public AddVehicle(final MainFrame prevPanel, final IModel iModel, final Set<String> allNumberPlate) {
        super(prevPanel,iModel);

        this.allNumbPlate = allNumberPlate;

        panelTitle.add(new JLabel("ADD VEHICLE"));

        this.tfManufacturer = new JTextField(15);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        c.insets = otherLines;
        panelForm.add(tfManufacturer, c);

        this.tfModel = new JTextField(15);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 2;
        c.insets = firstLine;
        panelForm.add(tfModel,c);

        this.tfRentPrice = new JTextField(15);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 3;
        c.gridy = 0;
        c.insets = otherLines;
        panelForm.add(tfRentPrice,c);

        this.tfNumberPlate = new JTextField(15);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        c.insets = otherLines;
        panelForm.add(tfNumberPlate,c);

        this.tfCarSeats = new JTextField(15);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 3;
        c.gridy = 1;
        c.insets = otherLines;
        panelForm.add(tfCarSeats,c);

        this.cbDrvLicense = new JComboBox<>(DrivingLicence.values());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 3;
        c.insets = otherLines;
        c.anchor = GridBagConstraints.CENTER;
        panelForm.add(cbDrvLicense,c);

        this.lblInsuranceCost = new JLabel("Insurance Cost : € ", JLabel.RIGHT);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 6;
        c.insets = firstLine;
        panelForm.add(lblInsuranceCost,c);

        this.tfInsuranceCost = new JTextField(15);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 6;
        c.insets = firstLine;
        panelForm.add(tfInsuranceCost,c);

        this.lblInsuranceExpiring = new JLabel("Insurance Expiring : ", JLabel.RIGHT);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 6;
        c.insets = firstLine;
        panelForm.add(lblInsuranceExpiring,c);

        this.tfInsuranceExpiring = new JTextField(15);
        this.tfInsuranceExpiring.setText("dd/MM/yyyy");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 3;
        c.gridy = 6;
        c.insets = firstLine;
        panelForm.add(tfInsuranceExpiring,c);

        this.lblCarTaxCost = new JLabel("Car Tax Cost : € ", JLabel.RIGHT);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 7;
        c.insets = otherLines;
        panelForm.add(lblCarTaxCost,c);

        this.tfCarTaxCost = new JTextField(15);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 7;
        c.insets = otherLines;
        panelForm.add(tfCarTaxCost,c);

        this.lblCarTaxDate = new JLabel("Car Tax Date : ", JLabel.RIGHT);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 7;
        c.insets = otherLines;
        panelForm.add(lblCarTaxDate,c);

        this.tfCarTaxDate = new JTextField(15);
        this.tfCarTaxDate.setText("dd/MM/yyyy");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 3;
        c.gridy = 7;
        c.insets = otherLines;
        panelForm.add(tfCarTaxDate,c);

        this.lblMOTTestCost = new JLabel("MotTest Cost : € ", JLabel.RIGHT);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 8;
        panelForm.add(lblMOTTestCost,c);

        this.tfMOTTestCost = new JTextField(15);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 8;
        panelForm.add(tfMOTTestCost,c);

        this.lblMOTTestDate = new JLabel("MotTest Date : ", JLabel.RIGHT);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 8;
        panelForm.add(lblMOTTestDate,c);

        this.tfMOTTestDate = new JTextField(15);
        this.tfMOTTestDate.setText("dd/MM/yyyy");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 3;
        c.gridy = 8;
        panelForm.add(tfMOTTestDate,c);

        btnPay.setVisible(false);
        btnRemove.setVisible(false);

        /**
         * Salva il veicolo se i campi inseriti sono corretti
         */
        btnSaveModify.setText("Save");
        btnSaveModify.addActionListener(e -> {

            if(checkInput()){

                Date insuranceExpiring = null;
                Date motTestDate = null;
                Date carTaxDate = null;

                int motTestCost = Integer.parseInt(tfMOTTestCost.getText());
                int carTaxCost = Integer.parseInt(tfCarTaxCost.getText());
                int rentPrice = Integer.parseInt(tfRentPrice.getText());
                int carSeats = Integer.parseInt(tfCarSeats.getText());
                int insuranceCost = Integer.parseInt(tfInsuranceCost.getText());

                try {
                    motTestDate = sdf.parse(tfMOTTestDate.getText());
                    carTaxDate = sdf.parse(tfMOTTestDate.getText());
                    insuranceExpiring = sdf.parse(tfInsuranceExpiring.getText());

                    ICar car = new Car(new LinkedList<IRentalPeriod>(), motTestDate, motTestCost, carTaxDate, carTaxCost, rentPrice, (DrivingLicence) cbDrvLicense.getSelectedItem(),
                            carSeats, insuranceExpiring, insuranceCost, tfNumberPlate.getText(), tfManufacturer.getText(), tfModel.getText(), true);
                    IAddCarController controller = new AddCarController(new JSonFileManager(), iModel);
                    controller.writeCar(car);
                } catch (ParseException e1) {

                }

                prevPanel.setPanel(new StorageVehicle(prevPanel, iModel));
            }
        });

        prevPanel.setVisibleBtnBackListener(true, e -> prevPanel.setPanel(new StorageVehicle(prevPanel, iModel)));

    }

    /**
     * Controlla i valori inseriti nei JTextField
     * @return true se sono tutti corretti
     */
    private boolean checkInput(){

        String manufacturer = tfManufacturer.getText();
        if(!manufacturer.matches("\\A[A-Za-z'\\\\ ]+\\z")){
            JOptionPane.showMessageDialog(this, "Wrong manufacturer's name!");
            return false;
        }

        String rentPrice = tfRentPrice.getText();
        if(!rentPrice.matches("\\A\\d+\\z")) {
            JOptionPane.showMessageDialog(this, "Wrong rent price!");
            return false;
        }

        String numberPlate = tfNumberPlate.getText();
        if(!numberPlate.matches("\\A[A-Z0-9]{6}\\z")){
            JOptionPane.showMessageDialog(this, "Wrong number plate!");
            return false;
        }

        if(this.allNumbPlate.contains(numberPlate)){
            JOptionPane.showMessageDialog(this, "Number Plate already used!");
            return false;
        }

        String carSeats = tfCarSeats.getText();
        if(!carSeats.matches("\\A\\d+\\z") && Integer.parseInt(carSeats) > 100) {
            JOptionPane.showMessageDialog(this, "Wrong car seats!");
            return false;
        }

        String model = tfModel.getText();
        if (!model.matches("\\A[A-Za-z0-9'\\\\ ]+\\z")){
            JOptionPane.showMessageDialog(this, "Wrong model!");
            return false;
        }

        if(cbDrvLicense.getSelectedIndex() == -1){
            JOptionPane.showMessageDialog(this, "Wrong license!");
            return false;
        }

        String insuranceCost = tfInsuranceCost.getText();
        if(!insuranceCost.matches("\\A\\d+\\z")) {
            JOptionPane.showMessageDialog(this, "Wrong insurance cost!");
            return false;
        }

        if (!checkDate(tfInsuranceExpiring.getText())){
            JOptionPane.showMessageDialog(this, "Wrong insurance expiring date!");
            return false;
        }

        String carTaxCost = tfCarTaxCost.getText();
        if(!carTaxCost.matches("\\A\\d+\\z")) {
            JOptionPane.showMessageDialog(this, "Wrong car tax cost!");
            return false;
        }

        if (!checkDate(tfCarTaxDate.getText())){
            JOptionPane.showMessageDialog(this, "Wrong car tax date!");
            return false;
        }

        String motTestCost = tfMOTTestCost.getText();
        if(!motTestCost.matches("\\A\\d+\\z")) {
            JOptionPane.showMessageDialog(this, "Wrong MOT test cost!");
            return false;
        }

        if (!checkDate(tfMOTTestDate.getText())){
            JOptionPane.showMessageDialog(this, "wrong MOT test date!");
            return false;
        }

        return true;
    }
}

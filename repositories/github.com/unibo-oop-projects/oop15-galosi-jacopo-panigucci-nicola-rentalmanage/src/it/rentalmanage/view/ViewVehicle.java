package it.rentalmanage.view;

import it.rentalmanage.controller.Expiring;
import it.rentalmanage.model.ICar;
import it.rentalmanage.model.IModel;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by nicolapanigucci on 01/03/16.
 */
public class ViewVehicle extends FormVehicles {

    private JLabel lblRentable;
    private JLabel lblAllTenant;
    private JLabel lblInsuranceCost;
    private JLabel lblInsuranceExpiring;
    private JLabel lblCarTaxCost;
    private JLabel lblCarTaxDate;
    private JLabel lblMOTTestCost;
    private JLabel lblMOTTestDate;

    private JLabel manufacturer;
    private JLabel rentPrice;
    private JLabel numberPlate;
    private JLabel insuranceCost;
    private JLabel insuranceExpiring;
    private JLabel carSeats;
    private JLabel requestedLicense;
    private JLabel carTaxCost;
    private JLabel carTaxDate;
    private JLabel motTestCost;
    private JLabel motTestDate;
    private JLabel model;
    private JLabel isRentable;

    private JButton btnViewAllTenant;

    private static DateFormat dateFormat;

    public ViewVehicle(final MainFrame prevPanel, final ICar vehicle, final IModel iModel) {
        super(prevPanel, iModel);

        this.dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        panelTitle.add(new JLabel("DETAILS VEHICLE"));

        otherLines = new Insets(10, 20, 0, 0);
        firstLine = new Insets(40, 20, 0, 0);

        this.manufacturer = new JLabel();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        c.insets = otherLines;
        panelForm.add(manufacturer, c);

        this.rentPrice = new JLabel();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 3;
        c.gridy = 0;
        c.insets = otherLines;
        panelForm.add(rentPrice, c);

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

        this.requestedLicense = new JLabel();
        this.requestedLicense.setHorizontalAlignment(JLabel.CENTER);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 3;
        c.insets = otherLines;
        panelForm.add(requestedLicense, c);

        this.lblAllTenant = new JLabel("Rental historian :");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 4;
        c.insets = otherLines;
        panelForm.add(lblAllTenant, c);

        this.btnViewAllTenant = new JButton("View");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 4;
        c.insets = otherLines;
        panelForm.add(btnViewAllTenant, c);

        this.lblRentable = new JLabel("Stato :");
        this.lblRentable.setHorizontalAlignment(JLabel.RIGHT);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 5;
        c.insets = otherLines;
        panelForm.add(lblRentable, c);

        this.isRentable = new JLabel();
        this.isRentable.setHorizontalAlignment(JLabel.CENTER);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 5;
        c.insets = otherLines;
        panelForm.add(isRentable, c);

        this.lblInsuranceCost = new JLabel("Insurance Cost : €", JLabel.RIGHT);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 6;
        c.insets = firstLine;
        panelForm.add(lblInsuranceCost, c);

        this.insuranceCost = new JLabel();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 6;
        c.insets = firstLine;
        panelForm.add(insuranceCost,c);

        this.lblInsuranceExpiring = new JLabel("Insurance Expiring :", JLabel.RIGHT);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 6;
        c.insets = firstLine;
        panelForm.add(lblInsuranceExpiring, c);

        this.insuranceExpiring = new JLabel();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 3;
        c.gridy = 6;
        c.insets = firstLine;
        panelForm.add(insuranceExpiring, c);

        this.lblCarTaxCost = new JLabel("Car Tax Cost : €", JLabel.RIGHT);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 7;
        c.insets = otherLines;
        panelForm.add(lblCarTaxCost, c);

        this.carTaxCost = new JLabel();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 7;
        c.insets = otherLines;
        panelForm.add(carTaxCost, c);

        this.lblCarTaxDate = new JLabel("Car Tax Date :", JLabel.RIGHT);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 7;
        c.insets = otherLines;
        panelForm.add(lblCarTaxDate, c);

        this.carTaxDate = new JLabel();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 3;
        c.gridy = 7;
        c.insets = otherLines;
        panelForm.add(carTaxDate, c);

        this.lblMOTTestCost = new JLabel("MotTest Cost : €", JLabel.RIGHT);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 8;
        panelForm.add(lblMOTTestCost, c);

        this.motTestCost = new JLabel();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 8;
        panelForm.add(motTestCost, c);

        this.lblMOTTestDate = new JLabel("MotTest Date :", JLabel.RIGHT);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 8;
        panelForm.add(lblMOTTestDate, c);

        this.motTestDate = new JLabel();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 3;
        c.gridy = 8;
        panelForm.add(motTestDate, c);

        manufacturer.setText(vehicle.getManufactorer());
        rentPrice.setText("" + vehicle.getRentPrice());
        numberPlate.setText(vehicle.getNumberPlate());
        insuranceCost.setText("" + vehicle.getInsuranceCost());
        insuranceExpiring.setText(dateFormat.format(vehicle.getInsuranceExpiring()));
        carSeats.setText("" + vehicle.getCarSeats());
        requestedLicense.setText("" + vehicle.getRequestedLicense());
        carTaxCost.setText("" + vehicle.getCarTaxCost());
        carTaxDate.setText(dateFormat.format(vehicle.getCarTaxDate()));
        motTestCost.setText("" + vehicle.getMotTestCost());
        motTestDate.setText(dateFormat.format(vehicle.getMotTestDate()));
        model.setText(vehicle.getModel());

        if (vehicle.isRentable()){
            isRentable.setText("Rentable");
        }else {
            isRentable.setText("Not Rentable");
        }

        /**
         * Se ci sono delle scadenze imminenti abilito il bottone 'btnPay'
         */
        if (!new Expiring(vehicle).checkExpiring().isEmpty()){
            btnPay.setEnabled(true);

            /**
             * Mostra il frame per aggiornare le date
             */
            btnPay.addActionListener(e -> new FrameExpiryDate(prevPanel, iModel, vehicle));
        }

        btnRemove.setVisible(false);

        if (iModel.getAllCarsHistory().size() == 0 || !iModel.getAllCarsHistory().containsKey(vehicle.getNumberPlate())){
            btnViewAllTenant.setEnabled(false);
        }

        /**
         * Mostra lo storico dei clienti che hanno noleggiato il veicolo
         */
        btnViewAllTenant.addActionListener(e -> prevPanel.setPanel(new RentalHistorianVehicle(prevPanel, iModel, vehicle)));

        btnSaveModify.setText("Modify");
        /**
         * Mostra il pannello per modificare i dati del veicolo
         */
        btnSaveModify.addActionListener(e1 -> prevPanel.setPanel(new ModifyVehicle(prevPanel, vehicle, iModel)));

        prevPanel.setVisibleBtnBackListener(true, e -> prevPanel.setPanel(new StorageVehicle(prevPanel, iModel)));

    }
}

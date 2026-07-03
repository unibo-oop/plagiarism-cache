package it.rentalmanage.view;

import it.rentalmanage.controller.Expiring;
import it.rentalmanage.controller.IVehicleController;
import it.rentalmanage.controller.VehicleController;
import it.rentalmanage.model.ICar;
import it.rentalmanage.model.IModel;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by nicolapanigucci on 01/05/16.
 */
public class FrameExpiryDate extends JFrame {

    private JLabel lblInsuranceCost;
    private JLabel lblInsuranceDate;
    private JLabel lblCarTaxCost;
    private JLabel lblCarTaxDate;
    private JLabel lblMotTestCost;
    private JLabel lblMotTestDate;

    private JTextField tfInsuranceCost;
    private JTextField tfCarTaxCost;
    private JTextField tfMOTTestCost;
    private JTextField tfInsuranceDate;
    private JTextField tfCarTaxDate;
    private JTextField tfMOTTestDate;

    private GridBagConstraints gbc;
    private GridBagLayout gridBagLayout;

    private Expiring expiring;

    private JButton btnSave;
    private Calendar calendar;
    private SimpleDateFormat sdf;

    public FrameExpiryDate(final MainFrame prevPanel, final IModel iModel, final ICar iCar){

        this.setTitle("Expiry dates");
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setSize(480,170);
        this.setResizable(false);

        this.calendar = Calendar.getInstance();
        this.calendar.setTime(new Date());
        this.calendar.add(Calendar.MONTH, -1);
        this.sdf = new SimpleDateFormat("dd/MM/yyyy");

        this.gbc = new GridBagConstraints();

        this.gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{140, 80, 0, 0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        this.setLayout(gridBagLayout);

        Insets insets = new Insets(5, 0, 5, 5);

        this.lblInsuranceCost = new JLabel("Insurance Cost : €");
        gbc.insets = insets;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor= GridBagConstraints.EAST;
        this.add(lblInsuranceCost, gbc);

        this.tfInsuranceCost = new JTextField(4);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = insets;
        gbc.gridx = 1;
        gbc.gridy = 0;
        this.add(tfInsuranceCost, gbc);

        this.lblInsuranceDate = new JLabel("Insurance Date :", JLabel.RIGHT);
        gbc.insets = insets;
        gbc.gridx = 2;
        gbc.gridy = 0;
        this.add(lblInsuranceDate, gbc);

        this.tfInsuranceDate = new JTextField(10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = insets;
        gbc.gridx = 3;
        gbc.gridy = 0;
        this.add(tfInsuranceDate, gbc);

        this.lblCarTaxCost = new JLabel("Car Tax Cost : €", JLabel.RIGHT);
        gbc.insets = insets;
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(lblCarTaxCost, gbc);

        this.tfCarTaxCost = new JTextField(4);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = insets;
        gbc.gridx = 1;
        gbc.gridy = 1;
        this.add(tfCarTaxCost, gbc);

        this.lblCarTaxDate = new JLabel("Car Tax Date :", JLabel.RIGHT);
        gbc.insets = insets;
        gbc.gridx = 2;
        gbc.gridy = 1;
        this.add(lblCarTaxDate, gbc);

        this.tfCarTaxDate = new JTextField(10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = insets;
        gbc.gridx = 3;
        gbc.gridy = 1;
        this.add(tfCarTaxDate, gbc);

        this.lblMotTestCost = new JLabel("MOT Test Cost : €", JLabel.RIGHT);
        gbc.insets = insets;
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(lblMotTestCost, gbc);

        this.tfMOTTestCost = new JTextField(4);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = insets;
        gbc.gridx = 1;
        gbc.gridy = 2;
        this.add(tfMOTTestCost, gbc);

        this.lblMotTestDate = new JLabel("MOT Test Date :", JLabel.RIGHT);
        gbc.insets = insets;
        gbc.gridx = 2;
        gbc.gridy = 2;
        this.add(lblMotTestDate, gbc);

        this.tfMOTTestDate = new JTextField(10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = insets;
        gbc.gridx = 3;
        gbc.gridy = 2;
        this.add(tfMOTTestDate, gbc);

        this.btnSave = new JButton("Save");
        gbc.gridx = 3;
        gbc.gridy = 3;
        this.add(btnSave, gbc);

        /**
         * Salva le nuove date se i campi sono corretti e chiudo il frame
         */
        btnSave.addActionListener(e -> {

            if (checkInput()){

                Date insuranceDate = null;
                Date carTaxDate = null;
                Date motTestDate = null;

                try {
                    insuranceDate = sdf.parse(tfInsuranceDate.getText());
                    carTaxDate = sdf.parse(tfCarTaxDate.getText());
                    motTestDate = sdf.parse(tfMOTTestDate.getText());
                } catch (ParseException e1) {

                }

                IVehicleController vehicleController = new VehicleController(iModel, iCar);
                vehicleController.updateVehicle(iCar.getRentPrice(), iCar.getRequestedLicense(), Integer.parseInt(tfInsuranceCost.getText()),
                        insuranceDate, Integer.parseInt(tfCarTaxCost.getText()), carTaxDate, Integer.parseInt(tfMOTTestCost.getText()),
                        motTestDate);

                this.dispose();
                prevPanel.setPanel(new ViewVehicle(prevPanel,iCar,iModel));
            }
        });

        /**
         * Setta i textfield
         */
        Calendar c = Calendar.getInstance();

        tfInsuranceCost.setText("" + iCar.getCarTaxCost());
        c.setTime(iCar.getInsuranceExpiring());
        tfInsuranceDate.setText("" + sdf.format(c.getTime()));
        tfCarTaxCost.setText("" + iCar.getCarTaxCost());
        c.setTime(iCar.getCarTaxDate());
        tfCarTaxDate.setText("" + sdf.format(c.getTime()));
        tfMOTTestCost.setText("" + iCar.getMotTestCost());
        c.setTime(iCar.getMotTestDate());
        tfMOTTestDate.setText("" + sdf.format(c.getTime()));

        /**
         * Evidenzia in rosso le label delle date scadute o che sono in procinto di scadere
         */
        expiring = new Expiring(iCar);
        List<String> exp = expiring.checkExpiring();
        if (!exp.isEmpty()){
            if (exp.contains(expiring.getTextInsurance())){
                lblInsuranceDate.setForeground(Color.RED);
            }
            if (exp.contains(expiring.getTextCarTax())){
                lblCarTaxDate.setForeground(Color.RED);
            }
            if (exp.contains(expiring.getTextMOTTest())){
                lblMotTestDate.setForeground(Color.RED);
            }
        }

        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    /**
     * Controllo se i valori inseriti sono corretti
     * @return true se tutti i valori sono corretti
     */
    private boolean checkInput(){

        if (!tfInsuranceCost.getText().matches("\\A\\d+\\z")){
            JOptionPane.showMessageDialog(this, "Wrong Insurance Cost!");
            return false;
        }

        if (!checkDate(tfInsuranceDate.getText())){
            JOptionPane.showMessageDialog(this, "Wrong Insurance Date!");
            return false;
        }

        if (!tfCarTaxCost.getText().matches("\\A\\d+\\z")){
            JOptionPane.showMessageDialog(this, "Wrong Car Tax Cost!");
            return false;
        }

        if (!checkDate(tfCarTaxDate.getText())){
            JOptionPane.showMessageDialog(this, "Wrong Car Tax Date!");
            return false;
        }

        if (!tfMOTTestCost.getText().matches("\\A\\d+\\z")){
            JOptionPane.showMessageDialog(this, "Wrong MOT Test Cost!");
            return false;
        }

        if (!checkDate(tfMOTTestDate.getText())){
            JOptionPane.showMessageDialog(this, "Wrong MOT Test Date!");
            return false;
        }

        return true;
    }

    /**
     * Controllo se la data è valida ed è successiva alla data attuale
     * @param date
     * @return true se la data è valida
     */
    private boolean checkDate(String date){
        try {
            Date d = sdf.parse(date);
            Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            cal.add(Calendar.MONTH, -1);

            if (cal.getTime().after(calendar.getTime())){
                return true;
            }

        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }

        return false;
    }

}

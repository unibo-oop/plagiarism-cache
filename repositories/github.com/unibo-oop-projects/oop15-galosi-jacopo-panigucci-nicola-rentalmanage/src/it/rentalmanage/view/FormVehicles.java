package it.rentalmanage.view;

import it.rentalmanage.model.IModel;
import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by nicolapanigucci on 29/02/16.
 */
public class FormVehicles extends JPanel {

    protected SimpleDateFormat sdf;
    protected Date now;
    protected Calendar calNow;

    private JLabel lblManufacturer;
    private JLabel lblRentPrice;
    private JLabel lblNumberPlate;
    private JLabel lblCarSeats;
    private JLabel lblModel;
    private JLabel lblRequestedLicense;

    protected GridBagConstraints c;

    protected JButton btnSaveModify;
    protected JButton btnPay;
    protected  JButton btnRemove;

    private JPanel panelBtn;
    protected JPanel panelTitle;
    protected JPanel panelForm;

    protected Insets otherLines;
    protected Insets firstLine;

    public FormVehicles(final MainFrame prevPanel, final IModel iModel){

        this.setLayout(new BorderLayout());

        this.sdf = new SimpleDateFormat("dd/MM/yyyy");
        //this.sdf.setLenient(false);
        this.now = new Date();
        this.calNow = Calendar.getInstance();
        calNow.setTime(this.now);
        calNow.add(Calendar.MONTH, -1);

        panelTitle = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelForm = new JPanel(new GridBagLayout());
        this.panelBtn = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        c = new GridBagConstraints();
        otherLines = new Insets(10, 0, 0, 0);
        firstLine = new Insets(40, 0, 0, 0);

        this.lblManufacturer = new JLabel("Manufacturer : ", JLabel.RIGHT);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = otherLines;
        panelForm.add(lblManufacturer, c);

        this.lblRentPrice = new JLabel("Rent Price : € ", JLabel.RIGHT);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 0;
        c.insets = otherLines;
        panelForm.add(lblRentPrice, c);

        this.lblNumberPlate = new JLabel("Number Plate : ", JLabel.RIGHT);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.insets = otherLines;
        panelForm.add(lblNumberPlate, c);

        this.lblCarSeats = new JLabel("Car Seats : ", JLabel.RIGHT);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 1;
        c.insets = otherLines;
        panelForm.add(lblCarSeats, c);

        this.lblRequestedLicense = new JLabel("Requested license : ", JLabel.RIGHT);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 3;
        c.insets = otherLines;
        panelForm.add(lblRequestedLicense, c);

        this.lblModel = new JLabel("Model : ", JLabel.RIGHT);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        c.insets = firstLine;
        panelForm.add(lblModel, c);

        this.btnPay = new JButton("Pay");
        btnPay.setEnabled(false);

        this.btnSaveModify = new JButton();
        this.btnRemove = new JButton("Remove");

        panelBtn.add(btnPay);
        panelBtn.add(btnRemove);
        panelBtn.add(btnSaveModify);

        this.add(panelTitle, BorderLayout.NORTH);
        this.add(panelForm, BorderLayout.CENTER);
        this.add(panelBtn, BorderLayout.SOUTH);

        prevPanel.setVisibleBtnBackListener(true, e -> prevPanel.setPanel(new StorageVehicle(prevPanel, iModel)));

    }

    /**
     * Controlla che la data inserita sia successiva la data attuale
     * @param date
     * @return true se la data inserita è successiva la data attuale
     */
    protected boolean checkDate (String date){
        try {
            Date dateValid = sdf.parse(date);
            Calendar cal = Calendar.getInstance();
            cal.setTime(dateValid);
            cal.add(Calendar.MONTH, -1); //mesi da 0 a 11

            if (cal.after(calNow)){
                return true;
            }

        } catch (ParseException e) {
            return false;
        }
        return false;
    }

}

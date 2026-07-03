package it.rentalmanage.view;

import it.rentalmanage.model.ICar;
import it.rentalmanage.model.IModel;
import it.rentalmanage.model.IPerson;
import it.rentalmanage.model.IRentedCarPeriod;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by nicolapanigucci on 10/03/16.
 */
public class ViewPerson extends FormPerson {

    private Map<String,ICar> carMap;
    private List<ICar> listRentableVehicles;

    private JLabel surname;
    private JLabel name;
    private JLabel cf;
    private JLabel address;
    private JLabel telephone;
    private JLabel bDay;
    private JLabel lblallRentedVehicle;

    private JButton btnRentCar;
    private JButton btnViewAllRentedVehicle;

    public ViewPerson(final MainFrame prevPanel, final IModel iModel, final IPerson iPerson, final Set<String> allCfSet) {
        super(prevPanel, iModel, allCfSet);

        panelTitle.add(new JLabel("DETAILS PERSON"));

        this.surname = new JLabel();
        c.gridx = 1;
        c.gridy = 0;
        c.insets = padding;
        panelForm.add(surname, c);

        this.name = new JLabel();
        c.gridx = 1;
        c.gridy = 1;
        c.insets = padding;
        panelForm.add(name, c);

        this.cf = new JLabel();
        c.gridx = 1;
        c.gridy = 2;
        c.insets = padding;
        panelForm.add(cf, c);

        this.bDay = new JLabel();
        c.gridx = 1;
        c.gridy = 3;
        c.insets = padding;
        panelForm.add(bDay, c);

        this.address = new JLabel();
        c.gridx = 1;
        c.gridy = 4;
        c.insets = padding;
        panelForm.add(address, c);

        this.telephone = new JLabel();
        c.gridx = 1;
        c.gridy = 5;
        c.insets = padding;
        panelForm.add(telephone, c);

        this.lblallRentedVehicle = new JLabel("All rented vehicle :");
        this.lblallRentedVehicle.setHorizontalAlignment(JLabel.RIGHT);
        c.gridx = 0;
        c.gridy = 7;
        c.insets = padding;
        c.anchor = GridBagConstraints.EAST;
        panelForm.add(lblallRentedVehicle, c);

        this.btnViewAllRentedVehicle = new JButton("View");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 7;
        c.insets = padding;
        panelForm.add(btnViewAllRentedVehicle, c);

        surname.setText(iPerson.getSurname());
        name.setText(iPerson.getName());
        cf.setText(iPerson.getFiscalCode());
        bDay.setText(sdf.format(iPerson.getBirthDate()));
        address.setText(iPerson.getAddress());
        telephone.setText(iPerson.getPhoneNumber());
        allDrivingLicence.setText(iPerson.getDrivingLicense().toString());

        cbDrvLicense.setVisible(false);
        btnAddDriveLicence.setVisible(false);
        btnRemoveDriveLicence.setVisible(false);
        btnRemove.setVisible(false);

        /**
         * Mostra lo storico delle auto noleggiate dalla persona
         */
        btnViewAllRentedVehicle.addActionListener(e2 -> prevPanel.setPanel(new RentalHistorianPerson(prevPanel, iModel, iPerson)));

        if (iModel.getAllPersonsHistory().size()==0 || !iModel.getAllPersonsHistory().containsKey(iPerson.getFiscalCode())){
            btnViewAllRentedVehicle.setEnabled(false);
        }

        btnRentCar = new JButton("Rent a Car");
        btnRentCar.setEnabled(true);
        panBtnAddCar.add(btnRentCar);


        /**
         * Controllo se ci sono veicoli noleggiabili
         */
        carMap = iModel.getAllCar();
        listRentableVehicles = new ArrayList<>();
        Iterator<Map.Entry<String,ICar>> iterator = carMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,ICar> entry = iterator.next();
            ICar value = entry.getValue();

            if (value.isRentable()){ //requisito necessario
                listRentableVehicles.add(value);
            }
        }

        if (listRentableVehicles.size() == 0){
            btnRentCar.addActionListener(e -> JOptionPane.showMessageDialog(this, "There aren't any rentable vehicles!"));
        }else {
            /**
             * Mostra le auto noleggiabili
             */
            btnRentCar.addActionListener(e -> prevPanel.setPanel(new RentVehicle(prevPanel, iModel, iPerson, listRentableVehicles)));
        }

        /**
         * Controllo se una persona ha già un auto in noleggio
         */
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH,-1);
        for (IRentedCarPeriod rentedCarPeriod : iPerson.getAllRentedCar()){
            if (rentedCarPeriod.getEndDate().after(calendar.getTime())){
                btnRentCar.setEnabled(false);
            }
        }

        btnSave_Modify.setText("Modify");

        /**
         * Mostra il pannello per modificare i dati della persona
         */
        btnSave_Modify.addActionListener(e1 -> prevPanel.setPanel(new ModifyPerson(prevPanel, iPerson, iModel, allCfSet)));

        prevPanel.setVisibleBtnBackListener(true,e -> prevPanel.setPanel(new StoragePerson(prevPanel, iModel)));
    }



}

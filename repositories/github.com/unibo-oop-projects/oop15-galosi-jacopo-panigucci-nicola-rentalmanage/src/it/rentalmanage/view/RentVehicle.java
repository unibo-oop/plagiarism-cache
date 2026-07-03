package it.rentalmanage.view;

import it.rentalmanage.model.ICar;
import it.rentalmanage.model.IModel;
import it.rentalmanage.model.IPerson;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by nicolapanigucci on 17/03/16.
 */
public class RentVehicle extends JPanel {

    private JLabel lblRentableVehicles;
    private JPanel panelDetails;
    private GridBagConstraints gbc;

    public RentVehicle(final MainFrame prevPanel, final IModel iModel, final IPerson iPerson, final List<ICar> carList){

        this.setLayout(new GridBagLayout());

        lblRentableVehicles = new JLabel("RENTABLE VEHICLES");

        panelDetails = new JPanel(new FlowLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        panelDetails.add(lblRentableVehicles, JLabel.CENTER);

        this.add(panelDetails, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;

        this.add(new TableRentVehicle(prevPanel, iPerson, iModel, carList), gbc);

        prevPanel.setVisibleBtnBackListener(true, e -> prevPanel.setPanel(new ViewPerson(prevPanel, iModel, iPerson, null)));

    }
}

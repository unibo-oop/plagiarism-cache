package it.rentalmanage.view;

import it.rentalmanage.model.ICar;
import it.rentalmanage.model.IModel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by nicolapanigucci on 14/03/16.
 */
public class RentalHistorianVehicle extends JPanel{

    private GridBagConstraints gb;

    public RentalHistorianVehicle(final MainFrame prevPanel, final IModel iModel, final ICar car){
        this.setLayout(new GridBagLayout());

        gb = new GridBagConstraints();
        gb.gridx = 0;
        gb.gridy = 0;
        gb.weightx = 1.0;
        gb.weighty = 1.0;
        gb.fill = GridBagConstraints.BOTH;

        this.add(new TableHistorianVehicle(prevPanel, iModel, car), gb);

        prevPanel.setVisibleBtnBackListener(true, e -> prevPanel.setPanel(new ViewVehicle(prevPanel, car, iModel)));

    }

}

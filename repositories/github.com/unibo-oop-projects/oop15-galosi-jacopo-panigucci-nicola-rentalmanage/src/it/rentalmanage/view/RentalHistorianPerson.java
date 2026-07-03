package it.rentalmanage.view;

import it.rentalmanage.model.IModel;
import it.rentalmanage.model.IPerson;

import javax.swing.*;
import java.awt.*;

/**
 * Created by nicolapanigucci on 05/04/16.
 */
public class RentalHistorianPerson extends JPanel {

    private GridBagConstraints gb;

    public RentalHistorianPerson(final MainFrame prevPanel, final IModel iModel, final IPerson iPerson){
        this.setLayout(new GridBagLayout());

        gb = new GridBagConstraints();
        gb.gridx = 0;
        gb.gridy = 0;
        gb.weightx = 1.0;
        gb.weighty = 1.0;
        gb.fill = GridBagConstraints.BOTH;

        this.add(new TableHistorianPerson(prevPanel, iModel, iPerson), gb);

        prevPanel.setVisibleBtnBackListener(true, e -> prevPanel.setPanel(new ViewPerson(prevPanel, iModel, iPerson, null)));

    }
}

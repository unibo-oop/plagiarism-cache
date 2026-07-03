package it.rentalmanage.view;

import it.rentalmanage.model.*;

import javax.swing.*;
import java.awt.*;

/**
 * Created by nicolapanigucci on 26/02/16.
 */
public class HomePage extends JPanel {

    private JLabel lblLogo;
    private JButton btnManageClients;
    private JButton btnManageVehicleS;
    private JPanel panLogo;
    private JPanel panButtons;

    public HomePage(final MainFrame prevPanel, final IModel iModel){

        this.setLayout(new BorderLayout());

        this.lblLogo = new JLabel(new ImageIcon("Images/Logo.png"));
        this.lblLogo.setPreferredSize(new Dimension(400,300));

        this.btnManageClients = new JButton("Manage Person");
        this.btnManageClients.setPreferredSize(new Dimension(this.btnManageClients.getPreferredSize().width*2,
                this.btnManageClients.getPreferredSize().height*2));

        this.btnManageVehicleS = new JButton("Manage Vehicles");
        this.btnManageVehicleS.setPreferredSize(new Dimension(this.btnManageVehicleS.getPreferredSize().width*2,
                this.btnManageVehicleS.getPreferredSize().height*2));

        this.btnManageClients.addActionListener(e -> prevPanel.setPanel(new StoragePerson(prevPanel, iModel)));

        this.btnManageVehicleS.addActionListener(e -> prevPanel.setPanel(new StorageVehicle(prevPanel, iModel)));

        this.panLogo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.panButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));

        this.panLogo.add(this.lblLogo);
        this.panButtons.add(this.btnManageClients);
        this.panButtons.add(this.btnManageVehicleS);

        this.add(this.panLogo, BorderLayout.NORTH);
        this.add(this.panButtons, BorderLayout.CENTER);

        prevPanel.setVisibleBtnBackListener(false, null);

    }

}

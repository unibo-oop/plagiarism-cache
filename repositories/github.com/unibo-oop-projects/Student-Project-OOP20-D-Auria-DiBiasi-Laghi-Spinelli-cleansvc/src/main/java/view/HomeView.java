package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.backupFile.*;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

public class HomeView extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = -1440993813136999810L;
    private static final String TITLE = "CLEAN SERVICE MANAGER";
    private static final double PERCENT = 0.6;
    private JButton btnClienti;
    private JButton btnGrafici;
    private JButton btnStaff;
    private JButton btnProduct;
    private JButton btnAppointment;
    private JButton btnSaveAndExit;
    private SaveAndLoadClients backupClients = new SaveAndLoadClients();
    private SaveAndLoadStaff backupStaff = new SaveAndLoadStaff();
    private SaveAndLoadProducts backupProducts = new SaveAndLoadProducts();
    private SaveAndLoadAppointments backupAppointments = new SaveAndLoadAppointments();
    private SaveAndLoadSubSteps backupSubSteps = new SaveAndLoadSubSteps();
    private JPanel panelClient;
    private JPanel panelCenter;
    private JPanel panelExit;
    private JButton btnStages;


    public HomeView() {

        setTitle(HomeView.TITLE);
        setMinimumSize(new Dimension(1000, 500));

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout(0, 0));
        JPanel mainPanel = new JPanel();
        mainPanel.setMinimumSize(new Dimension(10, 100));
        getContentPane().add(mainPanel);
        mainPanel.setLayout(new BorderLayout(0, 0));

        panelClient = new JPanel();
        panelClient.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(153, 180, 209), new Color(109, 109, 109)), "Clienti", TitledBorder.LEFT, TitledBorder.TOP, null, SystemColor.activeCaption));
        mainPanel.add(panelClient, BorderLayout.NORTH);

        btnClienti = new JButton("Area Clienti");
        panelClient.add(btnClienti);
        btnClienti.setForeground(SystemColor.textText);
        btnClienti.setBackground(SystemColor.activeCaption);
        btnClienti.setPreferredSize(new Dimension(280,70));
        btnClienti.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
        btnClienti.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ClientsView cv = new ClientsView();
                cv.display();
                dispose();
            }

        });
        panelClient.add(btnClienti);

        btnAppointment = new JButton("Area Appuntamenti");
        panelClient.add(btnAppointment);
        btnAppointment.setForeground(SystemColor.textText);
        btnAppointment.setBackground(SystemColor.activeCaption);
        btnAppointment.setPreferredSize(new Dimension(280,70));
        btnAppointment.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));

        panelCenter = new JPanel();
        panelCenter.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(153, 180, 209), new Color(105, 105, 105)), "Admin", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(153, 180, 209)));
        mainPanel.add(panelCenter, BorderLayout.CENTER);

        btnStaff = new JButton("Area Dipendenti");
        panelCenter.add(btnStaff);
        btnStaff.setForeground(SystemColor.textText);
        btnStaff.setBackground(SystemColor.activeCaption);
        btnStaff.setPreferredSize(new Dimension(280,70));
        btnStaff.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));

        btnProduct = new JButton("Area Prodotti");
        panelCenter.add(btnProduct);
        btnProduct.setForeground(SystemColor.textText);
        btnProduct.setBackground(SystemColor.activeCaption);
        btnProduct.setPreferredSize(new Dimension(280,70));
        btnProduct.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));

        btnGrafici = new JButton("Area Grafici");
        panelCenter.add(btnGrafici);

        btnGrafici.setForeground(SystemColor.textText);
        btnGrafici.setBackground(SystemColor.activeCaption);
        btnGrafici.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
        btnGrafici.setPreferredSize(new Dimension(280,70));

        btnStages = new JButton("Area Processi");
        btnStages.addActionListener(e->{
            SubStepView stv = new SubStepView();
            stv.display();
            dispose();
        });
        btnStages.setPreferredSize(new Dimension(280, 70));
        btnStages.setForeground(Color.BLACK);
        btnStages.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
        btnStages.setBackground(SystemColor.activeCaption);
        panelCenter.add(btnStages);

        panelExit = new JPanel();
        mainPanel.add(panelExit, BorderLayout.SOUTH);
        panelExit.setLayout(new BorderLayout(0, 0));

        btnSaveAndExit = new JButton("Salva ed Esci");
        panelExit.add(btnSaveAndExit, BorderLayout.EAST);
        btnSaveAndExit.setForeground(SystemColor.textText);
        btnSaveAndExit.setBackground(SystemColor.activeCaption);
        btnSaveAndExit.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
        btnSaveAndExit.setPreferredSize(new Dimension(280,70));

        btnSaveAndExit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                backupClients.save();
                backupStaff.save();
                backupProducts.save();
                backupAppointments.save();
                backupSubSteps.save();
                dispose();
            }
        });
        btnGrafici.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                AdministratorChartsView av = new AdministratorChartsView();
                av.display();
                dispose();
            }
        });
        btnProduct.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ProductView pv = new ProductView();
                pv.display();
                dispose();
            }
        });
        btnStaff.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                StaffView sv = new StaffView();
                sv.display();
                dispose();
            }
        });
        btnAppointment.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                AppointmentsView av = new AppointmentsView();
                av.display();
                dispose();
            }

        });


    }

    /**
     * 
     */

    public void display() {
        setVisible(true);
        setResizable(true);        
        setMinimumSize(new Dimension(500,500));
        final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int) (dimension.getWidth()*(HomeView.PERCENT)) , (int)(dimension.getHeight() * HomeView.PERCENT));
    }
}

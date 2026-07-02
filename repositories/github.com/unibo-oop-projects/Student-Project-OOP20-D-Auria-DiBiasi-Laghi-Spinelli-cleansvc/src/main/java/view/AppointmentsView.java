package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controller.CompanyImpl;
import model.Appointments;
import utility.ConstantsCleanSvc;
import utility.PopUp;

public class AppointmentsView extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = -6203076048044842383L;
    private JTextField txtClient;
    private JTextField txtDate;
    private JTextField txtHour;
    private final JButton btnNewApp;
    private final JButton btnHome;
    private final JButton btnSearch;
    private final JButton btnRemove;
    private JComboBox<String> appDateHour;

    private CompanyImpl company = CompanyImpl.getInstance();
    private List<Appointments> appointmentsList = company.getAppointment();
    private final String[] cols = new String[] {"Data", "Ora", "Nome", "CF o Partita IVA", "Tempo totale", "Costo totale"};
    private Object[][] data = new Object[0][cols.length];
    private DefaultTableModel model = new DefaultTableModel(data, cols);
    private JTable table = new JTable(model);
    private PopUp popUp = new PopUp();

    public AppointmentsView() {

        setTitle(ConstantsCleanSvc.TITLE);
        setMinimumSize(new Dimension(ConstantsCleanSvc.WIDTH, ConstantsCleanSvc.HEIGHT));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panelTable = new JPanel();
        panelTable.setMinimumSize(new Dimension(ConstantsCleanSvc.PNLS_FULL_WIDTH, ConstantsCleanSvc.PNL_TITLE_HEIGHT));
        panelTable.setBackground(SystemColor.activeCaption);
        panelTable.setLayout(new BorderLayout(ConstantsCleanSvc.BORDERLAYOUT0, ConstantsCleanSvc.BORDERLAYOUT0));

        JPanel panelTitle = new JPanel();
        panelTitle.setMinimumSize(new Dimension(ConstantsCleanSvc.PNLS_FULL_WIDTH, ConstantsCleanSvc.PNL_TITLE_HEIGHT));
        panelTitle.setBackground(SystemColor.activeCaption);
        panelTitle.setLayout(new BorderLayout(ConstantsCleanSvc.BORDERLAYOUT0, ConstantsCleanSvc.BORDERLAYOUT0));

        JLabel lblTitle = new JLabel("Elenco appuntamenti");
        lblTitle.setHorizontalAlignment(SwingConstants.LEFT);
        lblTitle.setForeground(SystemColor.textText);
        lblTitle.setFont(ConstantsCleanSvc.FONT_TITLE);
        panelTitle.add(lblTitle, BorderLayout.WEST);

        btnHome = new JButton("BACK HOME");
        btnHome.setForeground(SystemColor.textText);
        btnHome.setBackground(SystemColor.activeCaption);
        btnHome.setPreferredSize(new Dimension(ConstantsCleanSvc.BTN_HOME_WIDTH, ConstantsCleanSvc.BTN_HOME_HEIGHT));
        btnHome.setFont(ConstantsCleanSvc.FONT);
        btnHome.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                model.setRowCount(0);
                HomeView cv = new HomeView();
                cv.display();
                dispose();
            }

        });
        panelTitle.add(btnHome, BorderLayout.EAST);
        panelTable.add(panelTitle, BorderLayout.NORTH);

        Appointments a;
        for (int i = 0; i < appointmentsList.size(); i++) {
             a = company.getAppointment().get(i);
             model.insertRow(i, new Object[] {a.getDate(), a.getHour(), a.getClient().getName(), a.getClient().getCFPIVA().toUpperCase(), a.getTime(), a.getEarn()});
         }

        table.setPreferredScrollableViewportSize(new Dimension(ConstantsCleanSvc.TABLE_WIDTH, ConstantsCleanSvc.TABLE_HEIGHT));
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);
        panelTable.add(table, BorderLayout.CENTER);
        panelTable.add(new JScrollPane(table));

        final JPanel pnlSearch = new JPanel();
        pnlSearch.setBorder(new TitledBorder(null, "Recupera dati appuntamento", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.activeCaption));
        pnlSearch.setBackground(SystemColor.window);
        pnlSearch.setPreferredSize(new Dimension(ConstantsCleanSvc.PNLS_FULL_WIDTH, ConstantsCleanSvc.PNL_SEARCH_HEIGHT));

        JLabel lblsearchDataOra = new JLabel("Data e ora appuntamento: ");
        lblsearchDataOra.setFont(ConstantsCleanSvc.FONT);
        pnlSearch.add(lblsearchDataOra);

        appDateHour = new JComboBox<>();
        appDateHour.setPreferredSize(new Dimension(ConstantsCleanSvc.SEARCH_CF_BOX_WIDTH, ConstantsCleanSvc.SEARCH_CF_BOX_HEIGHT));
        appDateHour.setBackground(SystemColor.activeCaption);
        appDateHour.setForeground(SystemColor.textText);
        appDateHour.setFont(ConstantsCleanSvc.FONT);
        updateSearchingDateHour(appDateHour);
        pnlSearch.add(appDateHour);

        btnSearch = new JButton("Estrai dati");
        btnSearch.setForeground(SystemColor.textText);
        btnSearch.setBackground(SystemColor.activeCaption);
        btnSearch.setPreferredSize(new Dimension(ConstantsCleanSvc.BTN_HOME_WIDTH, ConstantsCleanSvc.BTN_HOME_HEIGHT));
        btnSearch.setFont(ConstantsCleanSvc.FONT);
        btnSearch.setToolTipText("Recupera i dati per visualizzarli nella sezione sottostante per eliminare l'appuntamento");
        btnSearch.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                if (getIndexAppointmentsSearched() == -1) {
                    popUp.popUpErrorOrMissing();
                } else {
                    writeField(company.getAppointment().get(getIndexAppointmentsSearched()));
                    btnRemove.setEnabled(true);
                }
            }
        });
        pnlSearch.add(btnSearch);

        final JPanel pnlDelete = new JPanel();
        pnlDelete.setBorder(new TitledBorder(null, "Elimina appuntamento", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.activeCaption));
        pnlDelete.setBackground(SystemColor.window);
        pnlDelete.setPreferredSize(new Dimension(ConstantsCleanSvc.PNLS_FULL_WIDTH, ConstantsCleanSvc.PNL_SUBMIT_HEIGHT));
        pnlDelete.setLayout(new BorderLayout(ConstantsCleanSvc.BORDERLAYOUT0, ConstantsCleanSvc.BORDERLAYOUT0));

        final JPanel pnlData = new JPanel();
        pnlData.setBorder(null);
        pnlData.setBackground(SystemColor.window);
        pnlData.setLayout(new GridLayout(ConstantsCleanSvc.GRID3, ConstantsCleanSvc.GRID2, ConstantsCleanSvc.GRID_20_GAP, ConstantsCleanSvc.GRID_2_GAP));

        JLabel labelCFPIVA = new JLabel("Cliente:");
        labelCFPIVA.setFont(ConstantsCleanSvc.FONT);
        pnlData.add(labelCFPIVA);

        txtClient = new JTextField();
        txtClient.setFont(ConstantsCleanSvc.FONT);
        txtClient.setEditable(false);
        pnlData.add(txtClient);

        JLabel labelName = new JLabel("Data:");
        labelName.setFont(ConstantsCleanSvc.FONT);
        pnlData.add(labelName);

        txtDate = new JTextField();
        txtDate.setFont(ConstantsCleanSvc.FONT);
        txtDate.setEditable(false);
        pnlData.add(txtDate);

        JLabel labelAddress = new JLabel("Orario:");
        labelAddress.setFont(ConstantsCleanSvc.FONT);
        pnlData.add(labelAddress);

        txtHour = new JTextField();
        txtHour.setFont(ConstantsCleanSvc.FONT);
        txtHour.setEditable(false);
        pnlData.add(txtHour);

        pnlDelete.add(pnlData, BorderLayout.CENTER);

        final JPanel pnlButtons = new JPanel();
        pnlButtons.setBackground(SystemColor.window);
        pnlButtons.setBorder(null);
        pnlButtons.setLayout(new GridLayout(ConstantsCleanSvc.GRID1, ConstantsCleanSvc.GRID1, ConstantsCleanSvc.GRID_20_GAP, ConstantsCleanSvc.GRID_20_GAP));

        btnRemove = new JButton("Elimina appuntamento");
        btnRemove.setForeground(SystemColor.textText);
        btnRemove.setBackground(SystemColor.activeCaption);
        btnRemove.setFont(ConstantsCleanSvc.FONT);
        btnRemove.setEnabled(false);
        btnRemove.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                if (missingField()) {
                    popUp.popUpErrorOrMissing();
                } else {
                    Optional<Appointments> appointmentsToRemove = company.searchAppointment(getDate(), getHour());
                    if (appointmentsToRemove.equals(Optional.empty())) {
                        popUp.popUpWarning("Appuntamento non trovato.");
                    } else {
                        Boolean confirm = popUp.popUpConfirm("Vuoi eliminare l'appuntamento di " + appointmentsToRemove.get().getClient().getName() + "?");
                        if (confirm) {
                            popUp.popUpInfo("Appuntamento eliminato con successo.");
                            company.removeAppointment(appointmentsToRemove.get());
                            removeAppointmentsToTable(appointmentsToRemove.get());
                            updateSearchingDateHour(appDateHour);
                            clearInsertField();
                            btnRemove.setEnabled(false);
                        } else {
                            popUp.popUpInfo("Eliminazione annullata.");
                        }
                    }
                }
            }
        });
        pnlButtons.add(btnRemove);
        pnlDelete.add(pnlButtons, BorderLayout.SOUTH);

        final JPanel pnlSubmit = new JPanel();
        pnlSubmit.setBorder(new TitledBorder(null, "Inserisci nuovo appuntamento", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.activeCaption));
        pnlSubmit.setBackground(SystemColor.window);
        pnlSubmit.setPreferredSize(new Dimension(ConstantsCleanSvc.PNLS_FULL_WIDTH, ConstantsCleanSvc.PNL_SUBMIT_HEIGHT));

        btnNewApp = new JButton("Vai all'inserimento di un nuovo appuntamento >");
        btnNewApp.setForeground(SystemColor.textText);
        btnNewApp.setBackground(SystemColor.activeCaption);
        btnNewApp.setFont(ConstantsCleanSvc.FONT);
        btnNewApp.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                NewAppointmentView nv = new NewAppointmentView();
                nv.display();
                dispose();
            }
        });
        pnlSubmit.add(btnNewApp);

        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(0)
                .addComponent(panelTable)
                .addGap(0)
                .addComponent(pnlSearch)
                .addGap(0)
                .addComponent(pnlDelete)
                .addGap(0)
                .addComponent(pnlSubmit)
                .addGap(0));

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGap(0)
                .addGroup(layout.createParallelGroup(Alignment.CENTER)
                        .addComponent(panelTable)
                        .addComponent(pnlSearch)
                        .addComponent(pnlDelete)
                        .addComponent(pnlSubmit))
                .addGap(0));
    }

    /**
     * Method that adds a new appointment to table.
     * @param a
     */
    public void addAppointmentsToTable(final Appointments a) {
        model.insertRow(company.getAppointment().size() - 1, new Object[] {a.getDate(), a.getHour(), a.getClient(), a.getTime(), a.getEarn()});
    }

    /**
     * Method that removes a specific appointment (indicated by date and hour) from table.
     * @param a
     */
    public void removeAppointmentsToTable(final Appointments a) {
        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 0).equals(a.getDate()) 
                    && model.getValueAt(i, 1).equals(a.getHour())) {
                model.removeRow(i);
            }
        }
    }

    /**
     * Method that clears fields.
     */
    public void clearInsertField() {
        txtClient.setText("");
        txtDate.setText("");
        txtHour.setText("");
    }

    /**
     * 
     * @return true if all fields are written
     */
    public Boolean missingField() {
        return (getClientName() == null || getDate() == null || getHour() == null);
    }

    /**
     * Method that writes fields for Client (with name and Fiscal Code or P.IVA), date and hour.
     * @param a
     */
    public void writeField(final Appointments a) {
        txtClient.setText(a.getClient().getName() + " " + a.getClient().getCFPIVA().toUpperCase());
        txtDate.setText(a.getDate());
        txtHour.setText(a.getHour());
    }

    /**
     * 
     * @return client's name and Fiscal Code or P.IVA
     */
    public String getClientName() {
        return txtClient.getText();
    }

    /**
     * 
     * @return appointment's date
     */
    public String getDate() {
        return txtDate.getText();
    }

    /**
     * 
     * @return appointment's hour
     */
    public String getHour() {
        return txtHour.getText();
    }

    /**
     * 
     * @return the index of the selected item in JComboBox
     */
    public int getIndexAppointmentsSearched() {
        return appDateHour.getSelectedIndex();
    }

    /**
     * Method that adds date and hour searched in JComboBox appDateHour.
     * @param appDateHour
     */
    public void updateSearchingDateHour(final JComboBox<String> appDateHour) {
        appDateHour.removeAllItems();
        for (Appointments appointments : company.getAppointment()) {
            appDateHour.addItem(appointments.getDate() + " - " + appointments.getHour());
        }
    }

    /**
     * Method that displays the view.
     */
    public void display() {
        setVisible(true);
        setResizable(true);
    }
}

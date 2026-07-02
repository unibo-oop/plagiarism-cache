package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import model.users.Clients;
import model.users.ClientsImpl;
import utility.ConstantsCleanSvc;
import utility.PopUp;
import utility.InputValidator;

public class ClientsView extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 3375687914483476432L;
    private JTextField txtCFPIVA;
    private JTextField txtName;
    private JTextField txtAddress;
    private JTextField txtCity;
    private JTextField txtCAP;
    private JTextField txtMq;
    private JTextField txtTel;
    private JTextField txtEmail;
    private final JButton btnSearch;
    private final JButton btnSubmit;
    private final JButton btnChange;
    private final JButton btnRemove;
    private final JButton btnHome;
    private JComboBox<String> clientCFPIVAs;
    private CompanyImpl company = CompanyImpl.getInstance();

    private final String[] cols = new String[] {"Nome", "Indirizzo", "Città", "CAP", "Struttura (mq)", "Telefono", "Email", "CF o P.IVA"};
    private Object[][] data = new Object[0][cols.length];

    private static final int COL_KEY = 7;

    private DefaultTableModel model = new DefaultTableModel(data, cols);
    private JTable table = new JTable(model);
    private InputValidator validator = new InputValidator();
    private PopUp popUp = new PopUp();

    public ClientsView() {
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

        JLabel lblTitle = new JLabel("Elenco clienti");
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

        for (int i = 0; i < company.getClients().size(); i++) {
            Clients client = company.getClients().get(i);
            model.insertRow(i, new Object[] {client.getName(), client.getAddress(), client.getCity(), client.getCAP(), client.getMqStructure(), client.getTel(), client.getEmail(), client.getCFPIVA().toUpperCase()});
        }

        table.setPreferredScrollableViewportSize(new Dimension(ConstantsCleanSvc.TABLE_WIDTH, ConstantsCleanSvc.TABLE_HEIGHT));
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);
        panelTable.add(table, BorderLayout.CENTER);
        panelTable.add(new JScrollPane(table));

        final JPanel pnlSearch = new JPanel();
        pnlSearch.setBorder(new TitledBorder(null, "Recupera dati clienti", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.activeCaption));
        pnlSearch.setBackground(SystemColor.window);
        pnlSearch.setPreferredSize(new Dimension(ConstantsCleanSvc.PNLS_FULL_WIDTH, ConstantsCleanSvc.PNL_SEARCH_HEIGHT));

        JLabel lblsearchCFPIVA = new JLabel("CF/P.IVA clienti:");
        lblsearchCFPIVA.setFont(ConstantsCleanSvc.FONT);
        pnlSearch.add(lblsearchCFPIVA);

        clientCFPIVAs = new JComboBox<>();
        clientCFPIVAs.setPreferredSize(new Dimension(ConstantsCleanSvc.SEARCH_CF_BOX_WIDTH, ConstantsCleanSvc.SEARCH_CF_BOX_HEIGHT));
        clientCFPIVAs.setBackground(SystemColor.activeCaption);
        clientCFPIVAs.setForeground(SystemColor.textText);
        clientCFPIVAs.setFont(ConstantsCleanSvc.FONT);
        updateSearchingCFPIVAs(clientCFPIVAs);
        pnlSearch.add(clientCFPIVAs);

        btnSearch = new JButton("Estrai dati");
        btnSearch.setForeground(SystemColor.textText);
        btnSearch.setBackground(SystemColor.activeCaption);
        btnSearch.setPreferredSize(new Dimension(ConstantsCleanSvc.BTN_HOME_WIDTH, ConstantsCleanSvc.BTN_HOME_HEIGHT));
        btnSearch.setFont(ConstantsCleanSvc.FONT);
        btnSearch.setToolTipText("Recupera i dati per visualizzarli nella sezione sottostante per modificarli e per eliminare il cliente");
        btnSearch.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                if (getIndexClientSearched() == -1) {
                    popUp.popUpErrorOrMissing();
                } else {
                    writeField(company.getClients().get(getIndexClientSearched()));
                    btnChange.setEnabled(true);
                    btnRemove.setEnabled(true);
                }
            }
        });
        pnlSearch.add(btnSearch);

        final JPanel pnlSubmit = new JPanel();
        pnlSubmit.setBorder(new TitledBorder(null, "Dati cliente", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.activeCaption));
        pnlSubmit.setBackground(SystemColor.window);
        pnlSubmit.setPreferredSize(new Dimension(ConstantsCleanSvc.PNLS_FULL_WIDTH, ConstantsCleanSvc.PNL_SUBMIT_HEIGHT));
        pnlSubmit.setLayout(new BorderLayout(ConstantsCleanSvc.BORDERLAYOUT0, ConstantsCleanSvc.BORDERLAYOUT0));

        final JPanel pnlData = new JPanel();
        pnlData.setBorder(null);
        pnlData.setBackground(SystemColor.window);
        pnlData.setLayout(new GridLayout(ConstantsCleanSvc.GRID4, ConstantsCleanSvc.GRID4, ConstantsCleanSvc.GRID4, ConstantsCleanSvc.GRID_2_GAP));

        JLabel labelCFPIVA = new JLabel("CF/P.IVA:");
        labelCFPIVA.setFont(ConstantsCleanSvc.FONT);
        pnlData.add(labelCFPIVA);

        txtCFPIVA = new JTextField();
        txtCFPIVA.setFont(ConstantsCleanSvc.FONT);
        pnlData.add(txtCFPIVA);

        JLabel labelName = new JLabel("Nome:");
        labelName.setFont(ConstantsCleanSvc.FONT);
        pnlData.add(labelName);

        txtName = new JTextField();
        txtName.setFont(ConstantsCleanSvc.FONT);
        pnlData.add(txtName);

        JLabel labelAddress = new JLabel("Indirizzo:");
        labelAddress.setFont(ConstantsCleanSvc.FONT);
        pnlData.add(labelAddress);

        txtAddress = new JTextField();
        txtAddress.setFont(ConstantsCleanSvc.FONT);
        pnlData.add(txtAddress);

        JLabel labelCity = new JLabel("Città:");
        labelCity.setFont(ConstantsCleanSvc.FONT);
        pnlData.add(labelCity);

        txtCity = new JTextField();
        txtCity.setFont(ConstantsCleanSvc.FONT);
        pnlData.add(txtCity);

        JLabel labelCAP = new JLabel("CAP:");
        labelCAP.setFont(ConstantsCleanSvc.FONT);
        pnlData.add(labelCAP);

        txtCAP = new JTextField();
        txtCAP.setFont(ConstantsCleanSvc.FONT);
        pnlData.add(txtCAP);

        JLabel labelmq = new JLabel("Struttura (mq):");
        labelmq.setFont(ConstantsCleanSvc.FONT);
        pnlData.add(labelmq);

        txtMq = new JTextField();
        txtMq.setFont(ConstantsCleanSvc.FONT);
        pnlData.add(txtMq);

        JLabel labeltel = new JLabel("Telefono:");
        labeltel.setFont(ConstantsCleanSvc.FONT);
        pnlData.add(labeltel);

        txtTel = new JTextField();
        txtTel.setFont(ConstantsCleanSvc.FONT);
        pnlData.add(txtTel);

        JLabel labelemail = new JLabel("Email:");
        labelemail.setFont(ConstantsCleanSvc.FONT);
        pnlData.add(labelemail);

        txtEmail = new JTextField();
        txtEmail.setFont(ConstantsCleanSvc.FONT);
        pnlData.add(txtEmail);
        pnlSubmit.add(pnlData, BorderLayout.CENTER);

        final JPanel pnlButtons = new JPanel();
        pnlButtons.setBackground(SystemColor.window);
        pnlButtons.setBorder(null);
        pnlButtons.setLayout(new GridLayout(ConstantsCleanSvc.GRID1, ConstantsCleanSvc.GRID3, ConstantsCleanSvc.GRID_20_GAP, ConstantsCleanSvc.GRID_20_GAP));

        btnSubmit = new JButton("Inserisci nuovo");
        btnSubmit.setForeground(SystemColor.textText);
        btnSubmit.setBackground(SystemColor.activeCaption);
        btnSubmit.setFont(ConstantsCleanSvc.FONT);
        btnSubmit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                if (!missingField()) {
                    Clients newClient = new ClientsImpl(getCFPIVA(), getName(), getAddress(), getCity(), getCAP(), getTel(), getEmail(),  getMq());
                    if (company.searchClient(newClient.getCFPIVA()).equals(Optional.empty())) {
                        popUp.popUpInfo("Cliente inserito con successo.");
                        company.addClient(newClient);
                        addClientToTable(company.getClients().get(company.getClients().size() - 1));
                        updateSearchingCFPIVAs(clientCFPIVAs);
                        clearInsertField();
                    } else {
                        popUp.popUpError("Cliente già esistente!");
                    }
                } else {
                    popUp.popUpErrorOrMissing();
                }
            }
        });
        pnlButtons.add(btnSubmit);

        btnChange = new JButton("Modifica esistente");
        btnChange.setForeground(SystemColor.textText);
        btnChange.setBackground(SystemColor.activeCaption);
        btnChange.setFont(ConstantsCleanSvc.FONT);
        btnChange.setEnabled(false);
        btnChange.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                if (!missingField()) {
                    Clients changed = new ClientsImpl(getCFPIVA(), getName(), getAddress(), getCity(), getCAP(), getTel(), getEmail(),  getMq());
                    Optional<Clients> toModify = company.searchClient(changed.getCFPIVA());
                    if (toModify.equals(Optional.empty())) {
                        popUp.popUpWarning("Codice Fiscale o Partita IVA inesistente tra i clienti!");
                    } else {
                        popUp.popUpInfo("Cliente modificato con successo.");
                        removeClientToTable(toModify.get());
                        company.removeClient(toModify.get());
                        company.addClient(changed);
                        addClientToTable(changed);
                        updateSearchingCFPIVAs(clientCFPIVAs);
                        clearInsertField();
                        btnChange.setEnabled(false);
                        btnRemove.setEnabled(false);
                    }
                } else {
                    popUp.popUpErrorOrMissing();
                }
            }
        });
        pnlButtons.add(btnChange);

        btnRemove = new JButton("Elimina cliente");
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
                    Optional<Clients> clientToRemove = company.searchClient(getCFPIVA().toUpperCase());
                    if (clientToRemove.equals(Optional.empty())) {
                        popUp.popUpWarning("Cliente non trovato");
                    } else {
                        Boolean confirmed = popUp.popUpConfirm("Vuoi eliminare il cliente '" + clientToRemove.get().getName() + "' ?");
                        if (confirmed) {
                            popUp.popUpInfo("Cliente eliminato con successo.");
                            removeClientToTable(clientToRemove.get());
                            company.removeClient(clientToRemove.get());
                            updateSearchingCFPIVAs(clientCFPIVAs);
                            clearInsertField();
                            btnChange.setEnabled(false);
                            btnRemove.setEnabled(false);
                        } else {
                            popUp.popUpInfo("Eliminazione annullata.");
                        }
                    }
                }
            }
        });
        pnlButtons.add(btnRemove);
        pnlSubmit.add(pnlButtons, BorderLayout.SOUTH);

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
                .addComponent(pnlSubmit)
                .addGap(0));

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGap(0)
                .addGroup(layout.createParallelGroup(Alignment.CENTER)
                        .addComponent(panelTable)
                        .addComponent(pnlSearch)
                        .addComponent(pnlSubmit))
                .addGap(0));
    }

    /**
     * 
     */
    public void clearInsertField() {
        txtCFPIVA.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtCity.setText("");
        txtCAP.setText("");
        txtMq.setText("");
        txtTel.setText("");
        txtEmail.setText("");
    }

    /**
     * 
     * @param c
     */
    public void writeField(final Clients c) {
        txtCFPIVA.setText(c.getCFPIVA().toUpperCase());
        txtName.setText(c.getName());
        txtAddress.setText(c.getAddress());
        txtCity.setText(c.getCity());
        txtCAP.setText(String.valueOf(c.getCAP()));
        txtMq.setText(String.valueOf(c.getMqStructure()));
        txtTel.setText(c.getTel());
        txtEmail.setText(c.getEmail());
    }

    /**
     * 
     * @return true if all field are written
     */
    public Boolean missingField() {
        return (getCFPIVA() == null || getName() == null || getAddress() == null || getCity() == null || getCAP() == Integer.MIN_VALUE || getMq() == Integer.MIN_VALUE || getTel() == null || getEmail() == null);
    }

    /**
     * 
     * @param c
     */
    public void addClientToTable(final Clients c) {
        model.insertRow(company.getClients().size() - 1, new Object[] {c.getName(), c.getAddress(), c.getCity(), c.getCAP(), c.getMqStructure(), c.getTel(), c.getEmail(), c.getCFPIVA().toUpperCase()});
    }

    /**
     * 
     * @param c
     */
    public void removeClientToTable(final Clients c) {
        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, COL_KEY).equals(c.getCFPIVA())) {
                model.removeRow(i);
                return;
            }
        }
    }

    /**
     * 
     * @return index of the JComboBox's item selected
     */
    public int getIndexClientSearched() {
        return clientCFPIVAs.getSelectedIndex();
    }

    /**
     * 
     * @param clientCFPIVAs
     */
    public void updateSearchingCFPIVAs(final JComboBox<String> clientCFPIVAs) {
        clientCFPIVAs.removeAllItems();
        for (Clients client : company.getClients()) {
            clientCFPIVAs.addItem(client.getCFPIVA().toUpperCase() + " - " + client.getName());
        }
    }

    /**
     * 
     * @return CF or P.IVA if is well formatted
     */
    public String getCFPIVA() {
        return validator.isCFPIVA(txtCFPIVA.getText().toUpperCase()) ? txtCFPIVA.getText().toUpperCase() : "";
    }

    /**
     * @return name if is well formatted
     */
    public String getName() {
        return validator.isName(txtName.getText()) ? txtName.getText() : "";
    }

    /**
     * 
     * @return address if is well formatted
     */
    public String getAddress() {
        return validator.isNameAndNum(txtAddress.getText()) ? txtAddress.getText() : "";
    }

    /**
     * 
     * @return city if is well formatted
     */
    public String getCity() {
        return validator.isName(txtCity.getText()) ? txtCity.getText() : "";
    }

    /**
     * 
     * @return CAP if is well formatted
     */
    public int getCAP() {
        return validator.isCAP(txtCAP.getText()) ? Integer.parseInt(txtCAP.getText()) : Integer.MIN_VALUE;
    }

    /**
     * 
     * @return client's structure in square meters if is well formatted
     */
    public int getMq() {
        return validator.isInteger(txtMq.getText()) ? Integer.parseInt(txtMq.getText()) : Integer.MIN_VALUE;
    }

    /**
     * 
     * @return telephone if is well formatted
     */
    public String getTel() {
        return validator.isPhone(txtTel.getText()) ? txtTel.getText() : "";
    }

    /**
     * 
     * @return email if is well formatted
     */
    public String getEmail() {
        return validator.isEmail(txtEmail.getText()) ? txtEmail.getText() : "";
    }

    /**
     * 
     */
    public void display() {
        setVisible(true);
        setResizable(true);
    }
}

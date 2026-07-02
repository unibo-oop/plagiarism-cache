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
import javax.swing.JCheckBox;
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

import model.users.Staff;
import controller.CompanyImpl;
import model.users.StaffImpl;
import utility.ConstantsCleanSvc;
import utility.InputValidator;
import utility.PopUp;

public class StaffView extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = -6791011571687868971L;
    private JTextField txtCFPIVA;
    private JTextField txtName;
    private JTextField txtAddress;
    private JTextField txtCity;
    private JTextField txtCAP;
    private JTextField txtTel;
    private JTextField txtEmail;
    private JCheckBox checkAdmin;
    private final JButton btnSearch;
    private final JButton btnSubmit;
    private final JButton btnChange;
    private final JButton btnRemove;
    private final JButton btnHome;
    private JComboBox<String> staffCFs;

    private CompanyImpl company = CompanyImpl.getInstance();
    private final String[] cols = new String[] {"Nome", "Indirizzo", "Città", "CAP", "Amministratore", "Telefono", "Email", "CF/PIVA"};

    private static final int COL_KEY = 7;
    private Object[][] data = new Object[0][cols.length];
    private DefaultTableModel model = new DefaultTableModel(data, cols);
    private JTable table = new JTable(model);
    private PopUp popUp = new PopUp();
    private InputValidator validator = new InputValidator();

    public StaffView() {
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

        JLabel lblTitle = new JLabel("Elenco dipendenti");
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

        for (int i = 0; i < company.getStaff().size(); i++) {
            Staff staff = company.getStaff().get(i);
            String admin = staff.isAdmin() ? "si" : "no";
            model.insertRow(i, new Object[] {staff.getName(), staff.getAddress(), staff.getCity(), staff.getCAP(), admin, staff.getTel(), staff.getEmail(), staff.getCFPIVA()});
        }
        table.setPreferredScrollableViewportSize(new Dimension(ConstantsCleanSvc.TABLE_WIDTH, ConstantsCleanSvc.TABLE_HEIGHT));
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);
        panelTable.add(table, BorderLayout.CENTER);
        panelTable.add(new JScrollPane(table));

        final JPanel pnlSearch = new JPanel();
        pnlSearch.setBorder(new TitledBorder(null, "Recupera dati dipendente", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.activeCaption));
        pnlSearch.setBackground(SystemColor.window);
        pnlSearch.setPreferredSize(new Dimension(ConstantsCleanSvc.PNLS_FULL_WIDTH, ConstantsCleanSvc.PNL_SEARCH_HEIGHT));

        JLabel lblsearchCFPIVA = new JLabel("CF dipendenti: ");
        lblsearchCFPIVA.setFont(ConstantsCleanSvc.FONT);
        pnlSearch.add(lblsearchCFPIVA);

        staffCFs = new JComboBox<>();
        staffCFs.setPreferredSize(new Dimension(ConstantsCleanSvc.SEARCH_CF_BOX_WIDTH, ConstantsCleanSvc.SEARCH_CF_BOX_HEIGHT));
        staffCFs.setBackground(SystemColor.activeCaption);
        staffCFs.setForeground(SystemColor.textText);
        staffCFs.setFont(ConstantsCleanSvc.FONT);
        updateSearchingCFs(staffCFs);
        pnlSearch.add(staffCFs);

        btnSearch = new JButton("Estrai dati");
        btnSearch.setForeground(SystemColor.textText);
        btnSearch.setBackground(SystemColor.activeCaption);
        btnSearch.setPreferredSize(new Dimension(ConstantsCleanSvc.BTN_HOME_WIDTH, ConstantsCleanSvc.BTN_HOME_HEIGHT));
        btnSearch.setFont(ConstantsCleanSvc.FONT);
        btnSearch.setToolTipText("Recupera i dati per visualizzarli nella sezione sottostante per modificarli e per eliminare il dipendente");
        btnSearch.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                if (getIndexStaffSearched() == -1) {
                    popUp.popUpErrorOrMissing();
                } else {
                    writeField(company.getStaff().get(getIndexStaffSearched()));
                    btnChange.setEnabled(true);
                    btnRemove.setEnabled(true);
                }
            }
        });
        pnlSearch.add(btnSearch);

        final JPanel pnlSubmit = new JPanel();
        pnlSubmit.setBorder(new TitledBorder(null, "Dati dipendente", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.activeCaption));
        pnlSubmit.setBackground(SystemColor.window);
        pnlSubmit.setPreferredSize(new Dimension(ConstantsCleanSvc.PNLS_FULL_WIDTH, ConstantsCleanSvc.PNL_SUBMIT_HEIGHT));
        pnlSubmit.setLayout(new BorderLayout(ConstantsCleanSvc.BORDERLAYOUT0, ConstantsCleanSvc.BORDERLAYOUT0));

        final JPanel pnlData = new JPanel();
        pnlData.setBorder(null);
        pnlData.setBackground(SystemColor.window);
        pnlData.setLayout(new GridLayout(ConstantsCleanSvc.GRID4, ConstantsCleanSvc.GRID4, ConstantsCleanSvc.GRID_20_GAP, ConstantsCleanSvc.GRID_2_GAP));

        JLabel labelCFPIVA = new JLabel("CF:");
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

        JLabel labelmq = new JLabel("Amministratore:");
        labelmq.setFont(ConstantsCleanSvc.FONT);
        pnlData.add(labelmq);

        checkAdmin = new JCheckBox();
        checkAdmin.setBackground(SystemColor.window);
        pnlData.add(checkAdmin);

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
                    Staff staff = new StaffImpl(getCFPIVA().toUpperCase(), getName(), getAddress(), getCity(), getCAP(), getTel(), getEmail(), isAdmin());
                    if (company.searchStaffbyCF(staff.getCFPIVA()).equals(Optional.empty()) && company.searchStaffbyEmail(staff.getEmail()).equals(Optional.empty())) {
                        popUp.popUpInfo("Dipendente inserito con successo.");
                        company.addStaff(staff);
                        addStaffToTable(company.getStaff().get(company.getStaff().size() - 1));
                        updateSearchingCFs(staffCFs);
                        clearInsertField();
                    } else {
                        popUp.popUpError("Dipendente già esistente!");
                    }
                } else {
                    popUp.popUpErrorOrMissing();
                }
            }
        });
        pnlButtons.add(btnSubmit);

        btnChange = new JButton("Aggiorna modifiche");
        btnChange.setForeground(SystemColor.textText);
        btnChange.setBackground(SystemColor.activeCaption);
        btnChange.setFont(ConstantsCleanSvc.FONT);
        btnChange.setEnabled(false);
        btnChange.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                if (!missingField()) {
                    Staff changed = new StaffImpl(getCFPIVA().toUpperCase(), getName(), getAddress(), getCity(), getCAP(), getTel(), getEmail(),  isAdmin());
                    Optional<Staff> toModify = company.searchStaffbyCF(changed.getCFPIVA());
                    if (toModify.equals(Optional.empty())) {
                        popUp.popUpWarning("Codice Fiscale inesistente tra i dipendenti. Non puoi modificare il Codice Fiscale.");
                    } else {
                        popUp.popUpInfo("Dipendente modificato con successo.");
                        removeStaffToTable(toModify.get());
                        company.removeStaff(toModify.get());
                        company.addStaff(changed);
                        addStaffToTable(changed);
                        updateSearchingCFs(staffCFs);
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

        btnRemove = new JButton("Elimina dipendente");
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
                    Optional<Staff> staffToRemove = company.searchStaffbyCF(getCFPIVA().toUpperCase());
                    if (staffToRemove.equals(Optional.empty())) {
                        popUp.popUpWarning("Dipendente non trovato.");
                    } else {
                        String email = popUp.popUpInput("Inserisci email:");
                        if (email == null) {
                            popUp.popUpInfo("Eliminazione annullata.");
                        } else if (validator.isEmail(email)) {
                            Optional<Staff> staffAdmin = company.searchStaffbyEmail(email);
                            if (staffAdmin.equals(Optional.empty())) {
                                popUp.popUpWarning("L'email non esiste!");
                            } else if (staffAdmin.isPresent()) {
                                if (staffAdmin.get().isAdmin()) {
                                    Boolean confirm = popUp.popUpConfirm("Vuoi eliminare il dipendente " + staffToRemove.get().getName() + "?");
                                    if (confirm) {
                                        popUp.popUpInfo("Dipendente eliminato con successo.");
                                        removeStaffToTable(staffToRemove.get());
                                        company.removeStaff(staffToRemove.get());
                                        updateSearchingCFs(staffCFs);
                                        clearInsertField();
                                        btnChange.setEnabled(false);
                                        btnRemove.setEnabled(false);
                                    } else {
                                        popUp.popUpInfo("Eliminazione annullata.");
                                    }
                                } else {
                                    popUp.popUpError("Non hai i permessi necessari!");
                                }
                            }
                        } else {
                            popUp.popUpErrorOrMissing();
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
     * @param s
     */
    public void addStaffToTable(final Staff s) {
        String admin = s.isAdmin() ? "si" : "no";
        model.insertRow(company.getStaff().size() - 1, new Object[] {s.getName(), s.getAddress(), s.getCity(), s.getCAP(), admin, s.getTel(), s.getEmail(), s.getCFPIVA()});
    }

    /**
     * 
     * @param s
     */
    public void removeStaffToTable(final Staff s) {
        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, COL_KEY).equals(s.getCFPIVA())) {
                model.removeRow(i);
            }
        }
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
        checkAdmin.setSelected(false);
        txtTel.setText("");
        txtEmail.setText("");
    }

    /**
     * 
     * @return true if all field are written
     */
    public Boolean missingField() {
        return (getCFPIVA() == null || getName() == null || getAddress() == null || getCity() == null || getCAP() == Integer.MIN_VALUE || getTel() == null || getEmail() == null);
    }

    /**
     * 
     * @param s
     */
    public void writeField(final Staff s) {
        txtCFPIVA.setText(s.getCFPIVA());
        txtName.setText(s.getName());
        txtAddress.setText(s.getAddress());
        txtCity.setText(s.getCity());
        txtCAP.setText(String.valueOf(s.getCAP()));
        checkAdmin.setSelected(s.isAdmin());
        txtTel.setText(s.getTel());
        txtEmail.setText(s.getEmail());
    }

    /**
     * 
     * @return true if is validate
     */
    public String getCFPIVA() {
        return validator.isCFPIVA(txtCFPIVA.getText().toUpperCase()) ? txtCFPIVA.getText().toUpperCase() : "";
    }

    /**
     * 
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
     * @return true if the employee has the administrator permissions
     */
    public Boolean isAdmin() {
        return checkAdmin.isSelected();
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
     * @return the index of the selected item in JComboBox
     */
    public int getIndexStaffSearched() {
        return staffCFs.getSelectedIndex();
    }

    /**
     * 
     * @param staffCFs
     */
    public void updateSearchingCFs(final JComboBox<String> staffCFs) {
        staffCFs.removeAllItems();
        for (Staff staff : company.getStaff()) {
            staffCFs.addItem(staff.getCFPIVA() + " - " + staff.getName());
        }
    }
    /**
     * 
     */
    public void display() {
        setVisible(true);
        setResizable(true);
    }
}

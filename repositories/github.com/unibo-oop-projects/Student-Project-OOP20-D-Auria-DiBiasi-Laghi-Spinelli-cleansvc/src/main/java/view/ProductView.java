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
import model.Products;
import model.ProductsImpl;
import model.step.enumerations.StepType;
import utility.InputValidator;
import utility.PopUp;
import utility.ConstantsCleanSvc;
import controller.ProcessImpl;

public class ProductView extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 3438738368807932420L;
    private final JButton btnHome;
    private final JButton btnSearch;
    private JTextField txtCode;
    private JTextField txtName;
    private JTextField txtDescr;
    private JTextField txtPrice;
    private JTextField txtUsage;
    private final JButton btnSubmit;
    private final JButton btnChange;
    private final JButton btnRemove;
    private CompanyImpl company = CompanyImpl.getInstance();
    private PopUp popUp = new PopUp();
    private InputValidator validator = new InputValidator();
    private final String[] cols = new String[] {"Codice", "Nome", "Descrizione", "Prezzo €/Litro", "Utilizzo Litro/500mq", "Fase sanificazione"};
    private Object[][] data = new Object[0][cols.length];

    private static final int COL_KEY = 0;

    private DefaultTableModel model = new DefaultTableModel(data, cols);
    private JTable table = new JTable(model);
    private JComboBox<String> productCodes;
    private ProcessImpl process = ProcessImpl.getInstance(); 
    private JComboBox<String> comboStep;

    public ProductView() {

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
        panelTitle.setLayout(new BorderLayout(0, 0));

        JLabel lblTitle = new JLabel("Elenco prodotti");
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

        for (int i = 0; i < company.getProducts().size(); i++) {
            Products product = company.getProducts().get(i);
            model.insertRow(i, new Object[] {product.getCode(), product.getName(), product.getDescription(), product.getPricePerLitre(), product.getLitersPer500Mq(), product.getStepType().getType()});
        } 
        table.setPreferredScrollableViewportSize(new Dimension(ConstantsCleanSvc.TABLE_WIDTH, ConstantsCleanSvc.TABLE_HEIGHT));
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true); 
        panelTable.add(table, BorderLayout.CENTER);
        panelTable.add(new JScrollPane(table));

        final JPanel pnlSearch = new JPanel();
        pnlSearch.setBorder(new TitledBorder(null, "Recupera dati prodotto", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.activeCaption));
        pnlSearch.setBackground(SystemColor.window);
        pnlSearch.setPreferredSize(new Dimension(ConstantsCleanSvc.PNLS_FULL_WIDTH, ConstantsCleanSvc.PNL_SEARCH_HEIGHT));

        JLabel lblsearch = new JLabel("Codice prodotti:");
        lblsearch.setFont(ConstantsCleanSvc.FONT);
        pnlSearch.add(lblsearch);

        productCodes = new JComboBox<>();
        productCodes.setPreferredSize(new Dimension(ConstantsCleanSvc.SEARCH_CF_BOX_WIDTH, ConstantsCleanSvc.SEARCH_CF_BOX_HEIGHT));
        productCodes.setBackground(SystemColor.activeCaption);
        productCodes.setForeground(SystemColor.textText);
        productCodes.setFont(ConstantsCleanSvc.FONT);
        updateSearchingCodes(productCodes);
        pnlSearch.add(productCodes);

        btnSearch = new JButton("Estrai dati");
        btnSearch.setForeground(SystemColor.textText);
        btnSearch.setBackground(SystemColor.activeCaption);
        btnSearch.setPreferredSize(new Dimension(ConstantsCleanSvc.BTN_HOME_WIDTH, ConstantsCleanSvc.BTN_HOME_HEIGHT));
        btnSearch.setFont(ConstantsCleanSvc.FONT);
        btnSearch.setToolTipText("Recupera i dati per visualizzarli nella sezione sottostante per modificarli e per eliminare il prodotto");
        btnSearch.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                if (getIndexProductSearched() == -1) {
                    popUp.popUpErrorOrMissing();
                } else {
                    writeField(company.getProducts().get(getIndexProductSearched()));
                    btnChange.setEnabled(true);
                    btnRemove.setEnabled(true);
                }
            }
        });
        pnlSearch.add(btnSearch);

        final JPanel pnlSubmit = new JPanel();
        pnlSubmit.setBorder(new TitledBorder(null, "Dati prodotto", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.activeCaption));
        pnlSubmit.setBackground(SystemColor.window);
        pnlSubmit.setPreferredSize(new Dimension(ConstantsCleanSvc.PNLS_FULL_WIDTH, ConstantsCleanSvc.PNL_SUBMIT_HEIGHT));
        pnlSubmit.setLayout(new BorderLayout(ConstantsCleanSvc.BORDERLAYOUT0, ConstantsCleanSvc.BORDERLAYOUT0));

        final JPanel pnlData = new JPanel();
        pnlData.setBorder(null);
        pnlData.setBackground(SystemColor.window);
        pnlData.setLayout(new GridLayout(ConstantsCleanSvc.GRID3, ConstantsCleanSvc.GRID2, ConstantsCleanSvc.GRID_20_GAP, ConstantsCleanSvc.GRID_2_GAP));

        JLabel labelCode = new JLabel("Codice:");
        labelCode.setFont(ConstantsCleanSvc.FONT);
        pnlData.add(labelCode);

        txtCode = new JTextField();
        txtCode.setFont(ConstantsCleanSvc.FONT);
        pnlData.add(txtCode);

        JLabel labelStep = new JLabel("Step:");
        labelStep.setFont(ConstantsCleanSvc.FONT);
        pnlData.add(labelStep);

        comboStep = new JComboBox<>();
        comboStep.setBackground(SystemColor.activeCaption);
        comboStep.setForeground(SystemColor.textText);
        comboStep.setFont(ConstantsCleanSvc.FONT);
        for (StepType steps : process.getStepTypeList()) {
            comboStep.addItem(steps.getType());
        }
        pnlData.add(comboStep);

        JLabel labelName = new JLabel("Nome:");
        labelName.setFont(ConstantsCleanSvc.FONT);
        pnlData.add(labelName);

        txtName = new JTextField();
        txtName.setFont(ConstantsCleanSvc.FONT);
        pnlData.add(txtName);

        JLabel labelDescr = new JLabel("Descrizione:");
        labelDescr.setFont(ConstantsCleanSvc.FONT);
        pnlData.add(labelDescr);

        txtDescr = new JTextField();
        txtDescr.setFont(ConstantsCleanSvc.FONT);
        pnlData.add(txtDescr);

        JLabel labelPrice = new JLabel("Prezzo €/Litro:");
        labelPrice.setFont(ConstantsCleanSvc.FONT);
        pnlData.add(labelPrice);

        txtPrice = new JTextField();
        txtPrice.setFont(ConstantsCleanSvc.FONT);
        pnlData.add(txtPrice);

        JLabel labelUsage = new JLabel("Utilizzo L/500mq:");
        labelUsage.setFont(ConstantsCleanSvc.FONT);
        pnlData.add(labelUsage);

        txtUsage = new JTextField();
        txtUsage.setFont(ConstantsCleanSvc.FONT);
        pnlData.add(txtUsage);
        pnlSubmit.add(pnlData, BorderLayout.CENTER);

        final JPanel pnlButtons = new JPanel();
        pnlButtons.setBackground(SystemColor.window);
        pnlButtons.setBorder(null);
        pnlButtons.setLayout(new GridLayout(ConstantsCleanSvc.GRID1, ConstantsCleanSvc.GRID2, ConstantsCleanSvc.GRID_20_GAP, ConstantsCleanSvc.GRID_2_GAP));

        btnSubmit = new JButton("Inserisci nuovo");
        btnSubmit.setForeground(SystemColor.textText);
        btnSubmit.setBackground(SystemColor.activeCaption);
        btnSubmit.setFont(ConstantsCleanSvc.FONT);
        btnSubmit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                if (!missingField()) {
                    Products newProduct = new ProductsImpl(getCode(), process.getStepTypeList().get(getIndexSelectedStep()), getName(), getDescription(), getPrice(), getUsage());
                    if (company.searchProduct(newProduct.getCode()).equals(Optional.empty())) {
                        popUp.popUpInfo("Prodotto inserito con successo.");
                        company.addProduct(newProduct);
                        addProductToTable(company.getProducts().get(company.getProducts().size() - 1));
                        updateSearchingCodes(productCodes);
                        clearInsertField();
                    } else {
                        popUp.popUpError("Prodotto già esistente!");
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
                    Products changed = new ProductsImpl(getCode(), process.getStepTypeList().get(getIndexSelectedStep()), getName(), getDescription(), getPrice(), getUsage());
                    Optional<Products> toModify = company.searchProduct(changed.getCode());
                    if (toModify.equals(Optional.empty())) {
                        popUp.popUpWarning("Codice inesistente tra i prodotti.");
                    } else {
                        popUp.popUpInfo("Prodotto modificato con successo.");
                        removeProductToTable(toModify.get());
                        company.removeProduct(toModify.get());
                        company.addProduct(changed);
                        addProductToTable(changed);
                        updateSearchingCodes(productCodes);
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

        btnRemove = new JButton("Elimina prodotto");
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
                    Optional<Products> productToRemove = company.searchProduct(getCode());
                    if (productToRemove.equals(Optional.empty())) {
                        popUp.popUpWarning("Prodotto non trovato!");
                    } else {
                        Boolean confirmed = popUp.popUpConfirm("Vuoi eliminare il prodotto '" + productToRemove.get().getName() + "' ?");
                        if (confirmed) {
                            popUp.popUpInfo("Prodotto eliminato con successo.");
                            removeProductToTable(productToRemove.get());
                            company.removeProduct(productToRemove.get());
                            updateSearchingCodes(productCodes);
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
     * @return product code of the selected item in JComboBox
     */
    public int getIndexProductSearched() {
        return productCodes.getSelectedIndex();
    }
    /**
     * 
     * @param p
     */
    public  void writeField(final Products p) {
        for (StepType steps : process.getStepTypeList()) {
            if (steps.getType().equals(p.getStepType().getType())) {
                comboStep.setSelectedItem(steps.getType());
            }
        }
        txtCode.setText(p.getCode());
        txtName.setText(p.getName());
        txtDescr.setText(p.getDescription());
        txtPrice.setText(String.valueOf(p.getPricePerLitre()));
        txtUsage.setText(String.valueOf(p.getLitersPer500Mq()));
    }
    /**
     * 
     */
    public void clearInsertField() {
        txtCode.setText("");
        comboStep.setSelectedIndex(0);
        txtName.setText("");
        txtDescr.setText("");
        txtPrice.setText("");
        txtUsage.setText("");
    }
    /**
     * 
     * @return true if all text field are written
     */
    public Boolean missingField() {
        return (getCode() == null || getName() == null || getDescription() == null || Double.isNaN(getPrice()) || Double.isNaN(getUsage()));
    }
    /**
     * 
     * @param p
     */
    public void addProductToTable(final Products p) {
        model.insertRow(company.getProducts().size() - 1, new Object[] {p.getCode(), p.getName(), p.getDescription(), p.getPricePerLitre(), p.getLitersPer500Mq(), p.getStepType().getType()});
    }
    /**
     * 
     * @param p
     */
    public void removeProductToTable(final Products p) {
        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, COL_KEY).equals(p.getCode())) {
                model.removeRow(i);
            }
        }
    }

    /**
     * @param productCodes
     */
    public void updateSearchingCodes(final JComboBox<String> productCodes) {
        productCodes.removeAllItems();
        for (Products product : company.getProducts()) {
            productCodes.addItem(product.getCode() + " - " + product.getName());
        }
    }
    /**
     * 
     * @return code if is well formatted
     */
    public String getCode() {
        return validator.isNameAndNum(txtCode.getText()) ? txtCode.getText() : "";
    }
    /**
     * 
     * @return index of selected step type in JComboBox
     */
    public int getIndexSelectedStep() {
        return comboStep.getSelectedIndex();
    }


    /**
     * @return name if is well formatted
     */
    public String getName() {
        return validator.isNameAndNum(txtName.getText()) ? txtName.getText() : "";
    }
    /**
     * 
     * @return description if is well formatted
     */
    public String getDescription() {
        return validator.isNameAndNum(txtDescr.getText()) ? txtDescr.getText() : "";
    }

    /**
     * 
     * @return price if is well formatted
     */
    public double getPrice() {
        return validator.isDouble(txtPrice.getText()) ? Double.parseDouble(txtPrice.getText()) : Double.NaN;
    }

    /**
     * 
     * @return usage if is well formatted
     */
    public double getUsage() {
        return validator.isDouble(txtUsage.getText()) ? Double.parseDouble(txtUsage.getText()) : Double.NaN;
    }

    /**
     * 
     */
    public void display() {
        setVisible(true);
        setResizable(true);
    }
}

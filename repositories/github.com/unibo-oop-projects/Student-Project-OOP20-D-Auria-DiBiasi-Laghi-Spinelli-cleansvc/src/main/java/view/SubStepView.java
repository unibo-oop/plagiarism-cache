package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
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

import controller.ProcessImpl;
import model.step.SubSteps;
import model.step.SubStepsImpl;
import model.step.enumerations.StepType;

import utility.PopUp;
import utility.InputValidator;
import utility.ConstantsCleanSvc;

public class SubStepView extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 3375687914483476432L;
    private static final int COL_KEY = 0;
    private JTextField txtCode;
    private JTextField txtName;
    private JTextField txtDescription;
    private JTextField txtTime;
    private List<JTextField> tfList = new ArrayList<>();
    private JComboBox<String> comboDelete;
    private JComboBox<String> comboSteps;

    private final JButton btnSubmit;
    private final JButton btnRemove;
    private final JButton btnHome;

    private PopUp popUp = new PopUp();
    private InputValidator validator = new InputValidator();

    private ProcessImpl process = ProcessImpl.getInstance();

    private final String[] cols = new String[] {"Codice", "Nome", "Descrizione", "Fase", "Tempo"};
    private Object[][] data = new Object[0][cols.length];
    private DefaultTableModel model = new DefaultTableModel(data, cols);
    private JTable table = new JTable(model);

    public SubStepView() {

        setTitle(ConstantsCleanSvc.TITLE);
        setMinimumSize(new Dimension(ConstantsCleanSvc.WIDTH, ConstantsCleanSvc.HEIGHT));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panelTable = new JPanel();
        panelTable.setMinimumSize(new Dimension(ConstantsCleanSvc.PNLS_SUBSTEPS_WIDTH, ConstantsCleanSvc.PNL_SUBSTEPS_HEIGHT));
        panelTable.setBackground(SystemColor.activeCaption);
        panelTable.setLayout(new BorderLayout(ConstantsCleanSvc.BORDERLAYOUT0, ConstantsCleanSvc.BORDERLAYOUT0));

        JPanel panelTitle = new JPanel();
        panelTitle.setMinimumSize(new Dimension(ConstantsCleanSvc.PNLS_FULL_WIDTH, ConstantsCleanSvc.PNL_SUBSTEPS_HEIGHT2));
        panelTitle.setBackground(SystemColor.activeCaption);
        panelTitle.setLayout(new BorderLayout(ConstantsCleanSvc.BORDERLAYOUT0, ConstantsCleanSvc.BORDERLAYOUT0));

        JLabel lblTitle = new JLabel("Elenco SottoFasi");
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

        for (int i = 0; i < process.getSubStepsList().size(); i++) {
            SubSteps subStep = process.getSubStepsList().get(i);
            model.insertRow(i, new Object[] {subStep.getCode(), subStep.getName(), subStep.getDescription(), subStep.getStepType(), subStep.getTime()});
        } 

        table.setPreferredScrollableViewportSize(new Dimension(ConstantsCleanSvc.TABLE_WIDTH, ConstantsCleanSvc.TABLE_SUBSTESPS_HEIGHT));
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);
        panelTable.add(table, BorderLayout.CENTER);
        panelTable.add(new JScrollPane(table));

        final JPanel pnlSubmit = new JPanel();
        pnlSubmit.setBorder(new TitledBorder(null, "Dati Sottofase", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.activeCaption));
        pnlSubmit.setBackground(SystemColor.window);
        pnlSubmit.setPreferredSize(new Dimension(ConstantsCleanSvc.PNL_SUBMIT_SUBSTEPS_WIDTH, ConstantsCleanSvc.PNL_SUBMIT_SUBSTEPS_HEIGHT));
        pnlSubmit.setMinimumSize(new Dimension(ConstantsCleanSvc.PNL_SUBMIT_SUBSTEPS_WIDTH, ConstantsCleanSvc.PNL_SUBMIT_SUBSTEPS_HEIGHT));
        pnlSubmit.setLayout(new BorderLayout(ConstantsCleanSvc.BORDERLAYOUT0, ConstantsCleanSvc.BORDERLAYOUT0));

        final JPanel pnlData = new JPanel();
        pnlData.setBorder(null);
        pnlData.setBackground(SystemColor.window);
        pnlData.setLayout(new GridLayout(ConstantsCleanSvc.GRID5, ConstantsCleanSvc.GRID2, ConstantsCleanSvc.GRID0, ConstantsCleanSvc.GRID_15_GAP));
 
        JLabel labelCode = new JLabel("Codice:");
        labelCode.setFont(ConstantsCleanSvc.FONT);
        pnlData.add(labelCode);

        txtCode = new JTextField();
        txtCode.setFont(ConstantsCleanSvc.FONT);
        pnlData.add(txtCode);
        tfList.add(txtCode);

        JLabel labelName = new JLabel("Nome:");
        labelName.setFont(ConstantsCleanSvc.FONT);
        pnlData.add(labelName);

        txtName = new JTextField();
        txtName.setFont(ConstantsCleanSvc.FONT);
        pnlData.add(txtName);
        tfList.add(txtName);

        JLabel labelDescription = new JLabel("Descrizione:");
        labelDescription.setFont(ConstantsCleanSvc.FONT);
        pnlData.add(labelDescription);

        txtDescription = new JTextField();
        txtDescription.setFont(ConstantsCleanSvc.FONT);
        pnlData.add(txtDescription);
        tfList.add(txtDescription);

        JLabel labelStep = new JLabel("Step:");
        labelStep.setFont(ConstantsCleanSvc.FONT);
        pnlData.add(labelStep);

        comboSteps = new JComboBox<>();
        comboSteps.setBackground(SystemColor.inactiveCaption);
        comboSteps.setForeground(SystemColor.textText);
        comboSteps.setFont(ConstantsCleanSvc.FONT);
        pnlData.add(comboSteps);

        for (StepType step : process.getStepTypeList()) {
            comboSteps.addItem(step.getType());
        }

        JLabel labelTime = new JLabel("Tempo:");
        labelTime.setFont(ConstantsCleanSvc.FONT);
        pnlData.add(labelTime);

        txtTime = new JTextField();
        txtTime.setFont(ConstantsCleanSvc.FONT);
        pnlData.add(txtTime);
        tfList.add(txtTime);

        pnlSubmit.add(pnlData, BorderLayout.CENTER);

        final JPanel pnlButtons = new JPanel();
        pnlButtons.setBackground(SystemColor.window);
        pnlButtons.setBorder(null);
        pnlButtons.setLayout(new GridLayout(ConstantsCleanSvc.GRID1, ConstantsCleanSvc.GRID1, ConstantsCleanSvc.GRID2,  ConstantsCleanSvc.GRID_2_GAP));

        btnSubmit = new JButton("Inserimento");
        btnSubmit.setForeground(SystemColor.textText);
        btnSubmit.setBackground(SystemColor.activeCaption);
        btnSubmit.setFont(ConstantsCleanSvc.FONT);
        btnSubmit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                if (!missingField()) {
                    SubSteps c = new SubStepsImpl(getCode(), getTime(), getName(), getDescription(), process.getStepTypeList().get(getIndexComboStep()));
                    if (process.searchSubStep(c.getCode()).equals(Optional.empty())) {
                        process.addStep(c);
                        popUp.popUpInfo("Sottofase inserita con successo.");
                        addSubStepToTable(process.getSubStepsList().get(process.getSubStepsList().size() - 1));
                        clearInsertField(); 
                    } else {
                        popUp.popUpWarning("Codice Sotto-Fase già esistente.");
                    }
                } else {
                    popUp.popUpErrorOrMissing();
                }
            }
        });
        pnlButtons.add(btnSubmit);
        pnlSubmit.add(pnlButtons, BorderLayout.SOUTH);

        final JPanel pnlRemove = new JPanel();
        pnlRemove.setBorder(new TitledBorder(null, "Elimina Sottofase", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.activeCaption));
        pnlRemove.setBackground(SystemColor.window);
        pnlRemove.setPreferredSize(new Dimension(ConstantsCleanSvc.PNL_REMOVE_SUBSTEPS_WIDTH, ConstantsCleanSvc.PNL_REMOVE_SUBSTEPS_HEIGHT));
        pnlRemove.setMinimumSize(new Dimension(ConstantsCleanSvc.PNL_REMOVE_SUBSTEPS_WIDTH, ConstantsCleanSvc.PNL_REMOVE_SUBSTEPS_HEIGHT2));

        final JPanel pnlCode = new JPanel();
        pnlCode.setBorder(null);
        pnlCode.setBackground(SystemColor.window);
        pnlCode.setPreferredSize(new Dimension(ConstantsCleanSvc.PNL_CODE_SUBSTEPS_WIDTH, ConstantsCleanSvc.PNL_CODE_SUBSTEPS_HEIGHT));
        pnlCode.setMinimumSize(new Dimension(ConstantsCleanSvc.PNL_CODE_SUBSTEPS_WIDTH, ConstantsCleanSvc.PNL_CODE_SUBSTEPS_HEIGHT));
        pnlCode.setLayout(new GridLayout(ConstantsCleanSvc.GRID1, ConstantsCleanSvc.GRID1, ConstantsCleanSvc.GRID0,  ConstantsCleanSvc.GRID_20_GAP));

        JLabel lblsearchCode = new JLabel("Codice:");
        lblsearchCode.setFont(ConstantsCleanSvc.FONT);
        pnlCode.add(lblsearchCode);

        comboDelete = new JComboBox<>();
        comboDelete.setFont(ConstantsCleanSvc.FONT);
        searchingCode(comboDelete);
        pnlCode.add(comboDelete);

        pnlRemove.add(pnlCode, BorderLayout.CENTER);
 
        final JPanel pnlButtons2 = new JPanel();
        pnlButtons2.setBackground(SystemColor.window);
        pnlButtons2.setBorder(null);
        pnlButtons2.setPreferredSize(new Dimension(ConstantsCleanSvc.PNL_CODE_SUBSTEPS_WIDTH, ConstantsCleanSvc.PNL_CODE_SUBSTEPS_HEIGHT2));
        pnlButtons2.setMinimumSize(new Dimension(ConstantsCleanSvc.PNL_CODE_SUBSTEPS_WIDTH, ConstantsCleanSvc.PNL_CODE_SUBSTEPS_HEIGHT2));
        pnlButtons2.setLayout(new GridLayout(ConstantsCleanSvc.GRID1, ConstantsCleanSvc.GRID1, ConstantsCleanSvc.GRID2,  ConstantsCleanSvc.GRID_2_GAP));

        btnRemove = new JButton("Elimina");
        btnRemove.setForeground(SystemColor.textText);
        btnRemove.setBackground(SystemColor.activeCaption);
        btnRemove.setFont(ConstantsCleanSvc.FONT);
        btnRemove.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                    SubSteps subStepRemove = process.getSubStepsList().get(getSearchingCode());
                        Boolean confirmed = popUp.popUpConfirm("Vuoi eliminare la sottofase " + subStepRemove.getName() + "?");
                        if (confirmed) {
                            popUp.popUpInfo("Sottofase eliminata con successo.");
                            removeSubStepToTable(subStepRemove);
                            process.removeStep(subStepRemove);
                            clearInsertField();
                        } else {
                            popUp.popUpInfo("Eliminazione annullata.");
                       }
            }
        });
        pnlButtons2.add(btnRemove);
        pnlRemove.add(pnlButtons2, BorderLayout.SOUTH);

        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(0)
                .addComponent(panelTitle)
                .addGap(0)
                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(panelTable)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(pnlSubmit)
                                .addGap(0)
                                .addComponent(pnlRemove)))
                .addGap(0));

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGap(0)
                .addGroup(layout.createParallelGroup(Alignment.CENTER)
                        .addComponent(panelTitle)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(panelTable)
                                .addGap(0)
                                .addGroup(layout.createParallelGroup(Alignment.CENTER)
                                        .addComponent(pnlSubmit)
                                        .addComponent(pnlRemove))))
                .addGap(0));

    }

    /**
     * 
     */
    public void clearInsertField() {
        for (JTextField tField : tfList) {
            tField.setText("");
        }
        comboSteps.setSelectedIndex(0);
    }

    /**
     * 
     * @return true if all field are written.
     */
    public Boolean missingField() {
        return (getCode().isEmpty() || getTime() == Integer.MIN_VALUE || getName().isEmpty() || getDescription().isEmpty() || comboSteps.getSelectedItem().equals("Seleziona step"));
    }

    /**
     * 
     * @param subStep
     */
    public void addSubStepToTable(final SubSteps subStep) {
        model.insertRow(process.getSubStepsList().size() - 1, new Object[] {subStep.getCode(), subStep.getName(), subStep.getDescription(), comboSteps.getSelectedItem(), subStep.getTime()});
    }
    /**
     * 
     * @param subStep
     */
    public void removeSubStepToTable(final SubSteps subStep) {
        for (int j = 0; j < model.getRowCount(); j++) {
            if (model.getValueAt(j, COL_KEY).equals(subStep.getCode())) {
                model.removeRow(j);
            }
        }
    }

    /**
     * 
     * @param combo
     */

    public void searchingCode(final JComboBox<String> combo) {
        combo.removeAllItems();
        for (SubSteps subSteps : process.getSubStepsList()) {
            combo.addItem(subSteps.getCode() + " - " + subSteps.getName());
        }
    }

    /**
     * 
     * @return  index of JComboBox's item selected.
     */
    public int getSearchingCode() {
        return comboDelete.getSelectedIndex();
    }

    /**
     * @return code if is well formatted.
     */
    public String getCode() {
        return validator.isNameAndNum(txtCode.getText()) ? txtCode.getText() : "";
    }

    /**
     * @return name if is well formatted.
     */
    public String getName() {
        return validator.isName(txtName.getText()) ? txtName.getText() : "";
    }

    /**
     * @return time if is well formatted.
     */
    public int getTime() {
        return validator.isInteger(txtTime.getText()) ? Integer.parseInt(txtTime.getText()) : Integer.MIN_VALUE;
    }

    /**
     * @return description if is well formatted.
     */
    public String getDescription() {
        return validator.isNameAndNum(txtDescription.getText()) ? txtDescription.getText() : "";
    }

    /**
     * @return index of JComboBox's item selected.
     */
    public int getIndexComboStep() {
        return comboSteps.getSelectedIndex();
    }

    /**
     * 
     */
    public void display() {
        setVisible(true);
        setResizable(true);
    }
}

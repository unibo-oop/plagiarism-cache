package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controller.EmployeeControllerImpl;
import controller.EmployeeInfo;
/**
 *employee view.
 */

public class EmployeesViewImpl extends JPanel implements EmployeesView, View {
    /**
     * 
     */
    private static final long serialVersionUID = -194294366194582629L;
    private static final int TEXT_FIELD = 25;
    private ChoosingPanelImpl cPanel = new ChoosingPanelImpl();
    private JPanel summary = new JPanel();
    private EmployeeControllerImpl controller;
    private final Map<controller.EmployeeInfo, JTextField> map;
    private static final String[] TABLE_COLUMNS = { "Name", "Surname", "Fiscal Code", "City", "Address", "Phone Number", "Email" };
    private JTable table;
    private final JPanel tablePnl;

    /**
     * 
     * @param ctrl
     *              controller
     * @param info
     *              information 
     */
    public EmployeesViewImpl(final EmployeeControllerImpl ctrl, final Object[][] info) {
        this.controller = ctrl;
        //panel to add employees
        JPanel addEmpl = new JPanel();
        addEmpl.setLayout(new BoxLayout(addEmpl, BoxLayout.Y_AXIS));
        addEmpl.setBorder(new TitledBorder("Add Employee"));

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
        JPanel leftPnl = new JPanel();
        leftPnl.setLayout(new BoxLayout(leftPnl, BoxLayout.Y_AXIS));
        JPanel rightPnl = new JPanel();
        rightPnl.setLayout(new BoxLayout(rightPnl, BoxLayout.Y_AXIS));
        container.add(leftPnl);
        container.add(rightPnl);

        //create a series of jlabel associated with a jtextfield each
        this.map = new HashMap<>();
        Stream.of(EmployeeInfo.values()).forEach(ei-> {
                JTextField data = new JTextField(TEXT_FIELD);
            this.map.put(ei, data);
            final JLabel label = new JLabel(ei.getInfo());
            leftPnl.add(layoutPanel(label, FlowLayout.LEFT));
            rightPnl.add(layoutPanel(data, FlowLayout.LEFT));
        });

        //buttons to confirm or reset choices
        JPanel addPnl = new JPanel(new BorderLayout());
        JButton add = new JButton("Add");
        add.addActionListener(e -> { 
            this.controller.confirm(updateMap());
            });
        add.setBackground(Colors.getDarkGreen());
        addPnl.add(add);

        JPanel resetPnl = new JPanel(new BorderLayout());
        JButton reset = new JButton("Reset");
        reset.addActionListener(e -> this.resetTextFields());
        reset.setBackground(Colors.getDarkGreen());
        resetPnl.add(reset);

        //add everything to "add employee" panel
        addEmpl.add(container);
        addEmpl.add(addPnl);
        addEmpl.add(resetPnl);
        addEmpl.setBackground(Colors.getBackgroundColor());
        addEmpl.setVisible(true);
        addEmpl.setMaximumSize(new Dimension(Integer.MAX_VALUE, addEmpl.getMinimumSize().height));

        //panel to remove employees
        JPanel delEmpl = new JPanel(new GridLayout(2, 1));
        delEmpl.setBorder(new TitledBorder("Remove Employee"));
        JLabel deleteEmpl = new JLabel("Select the employee you want to remove.");
        JButton delete = new JButton("Remove");
        delete.setBackground(Colors.getDarkGreen());
        delete.addActionListener(e -> this.controller.removeEmployee());

        delEmpl.add(deleteEmpl);
        delEmpl.add(delete);
        delEmpl.setBackground(Colors.getBackgroundColor());
        delEmpl.setVisible(true);
        delEmpl.setMaximumSize(new Dimension(Integer.MAX_VALUE, delEmpl.getMinimumSize().height));

        cPanel.add(addEmpl);
        cPanel.add(delEmpl);

        //summary panel
        this.tablePnl = new JPanel(new FlowLayout());
        summary.add(tablePnl, BorderLayout.NORTH);
        summary.setBackground(Colors.getBackgroundColor());
        summary.setBorder(new TitledBorder("Summary"));

        this.setLayout(new BorderLayout());

        this.add(cPanel, BorderLayout.WEST);
        this.add(summary, BorderLayout.CENTER);
        this.rebuildTable(info);

    }

    private static JPanel layoutPanel(final JComponent component, final int orientation) {
        final JPanel panel = new JPanel(new FlowLayout(orientation));
        panel.add(component);
        panel.setBackground(Colors.getBackgroundColor());
        return  panel;
    }

    private Map<EmployeeInfo, String> updateMap() {
        final Map<EmployeeInfo, String> updatedMap = new HashMap<>();
        this.map.forEach((ei, data) -> {
            updatedMap.put(ei, data.getText());
        });
        return updatedMap;
    }

    @Override
    public void rebuildTable(final Object[][] info) {
        final DefaultTableModel dtm = new DefaultTableModel(info, TABLE_COLUMNS) {
            /**
             * 
             */
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(final int row, final int column) {
                return false;
            }
        };
        this.table = new JTable(dtm);
        this.table.getSelectionModel().addListSelectionListener(x -> {
            final int row = this.table.convertRowIndexToModel(this.table.getSelectedRow());
            this.controller.selectEmployee(row);
        });
        this.tablePnl.removeAll();
        JScrollPane tableScrollPane = new JScrollPane(this.table);
        tableScrollPane.setBackground(Colors.getBackgroundColor());
        this.tablePnl.add(layoutPanel(tableScrollPane, FlowLayout.CENTER));
        tablePnl.setBackground(Colors.getBackgroundColor());
        this.validate();
        this.repaint();
    }

    @Override
    public void init() {
        // TODO Auto-generated method stub
    }

    @Override
    public void resetTextFields() {
        this.map.forEach((ei, data) -> data.setText(""));
    }

}

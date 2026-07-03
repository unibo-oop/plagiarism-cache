package view;


import java.awt.BorderLayout;

import java.awt.GridLayout;


import javax.swing.BoxLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controller.GreenhouseControllerImpl;
import controller.PlantsToPickTableImpl;

/**
 * View used to easily access to a list of plants that have to
 * be picked today and allows to pick them.
 */
public class ToBePickedView extends JFrame implements View {

    private static final long serialVersionUID = 7529931410458800694L;
    private PlantsToPickTableImpl controller;
    private static final String[] TABLE_COLUMNS = { "Name", "Picking Date", "Position"};
    private final JTable table;
    private final JPanel textPnl = new JPanel();
    private final JPanel tablePnl;
    private final JPanel pickPnl = new JPanel();
    private GreenhouseControllerImpl greenhouseControllerImpl;

    /**
     * @param info
     *              object containing plants to pick today
     * @param controller
     *              controller
     */
    public ToBePickedView(final Object[][] info, final PlantsToPickTableImpl  controller) {
        this.controller = controller;

        this.pickPnl.setLayout(new BorderLayout());
        JButton pickPlant = new JButton("Pick");
        pickPlant.setBackground(Colors.getDarkGreen());
        pickPlant.addActionListener(l -> {
            this.controller.removePlanting();
            new GreenhouseViewImpl(this.greenhouseControllerImpl);
            setVisible(false);
        });
        this.pickPnl.add(pickPlant);
        this.setLayout(new GridLayout(0, 1));
        this.tablePnl = new JPanel();
        this.table = new JTable(new DefaultTableModel(info, TABLE_COLUMNS) {
            /**
             * 
             */
            private static final long serialVersionUID = 1273984729834792L;

            @Override
            public boolean isCellEditable(final int row, final int column) {
                return false;
            }
        });
        this.table.getTableHeader().setReorderingAllowed(false);
        this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        this.table.getSelectionModel().addListSelectionListener(s-> {
            final int row = this.table.convertRowIndexToModel(this.table.getSelectedRow());
            this.controller.selectPlanting(row);
        });

        this.tablePnl.add(this.table);
        JScrollPane scrollPane = new JScrollPane(tablePnl);
        scrollPane.setViewportView(this.table);

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        this.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(new TitledBorder("Plants to pick today"));
        content.setBackground(Colors.getBackgroundColor());

        JLabel text = new JLabel("These are the plants that have to be picked today");
        textPnl.add(text);
        textPnl.setBackground(Colors.getBackgroundColor());

        content.add(textPnl);
        content.add(scrollPane);
        content.add(pickPnl);

        this.setBackground(Colors.getGreenhouseColor());
        this.setContentPane(content);
        this.pack();
        this.setVisible(false);
    }

    @Override
    public void init() {
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
    }

}


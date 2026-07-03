package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controller.ToDoController;

/**
 * The to do class that permits to manage a to do list.
 *
 */
public class ToDoViewImpl extends JPanel implements ToDoView, ActionListener {

    private static final int SIZE_H = (Toolkit.getDefaultToolkit().getScreenSize().height);
    private static final int SIZE_W = (Toolkit.getDefaultToolkit().getScreenSize().width / 3);
    private static final String[] TODO_TABLE_TITLES = { "Description", "Completed"};
    private static final int CF_COLUMN_WIDTH = 70;
    private static final long serialVersionUID = -1582455860803755004L;
    private final JButton btnAddGuest;
    private final JButton rmvGuest;
    private final JButton modify;
    private final ToDoController observer;
    private final JPanel tablePane;
    private final JPanel east;
    private JTable table;
    private final JPanel eastSouth;
    private final JPanel addToDoPane;
    private final JPanel toDoContainer;
    private final JPanel leftBox;
    private final JLabel lblDescription;
    private  final JCheckBox chCompleted;
    private final JPanel rightBox;
    private final JTextArea descriptionArea;
    private  final JPanel container;
    private  final JPanel bordToDoContainer;
    private final JPanel showToDoPane;
    private static final double DB1 = 0.4;
    private static final double DB2 = 0.3;
    private static final int T1 = 15;
    private static final int T2 = 25;
    /**
     * The constructor of the class.
     * @param ctrl
     *        the to do controller
     * @param data
     *        a matrix of to do information
     */
    public ToDoViewImpl(final ToDoController ctrl, final Object[][] data) {
 
       this.observer = ctrl;

        this.setLayout(new BorderLayout());
        this.addToDoPane = new JPanel();
        this.addToDoPane.setBackground(new Color(ViewColor.light.getRed(),
                ViewColor.light.getGreen(),
                ViewColor.light.getBlue()));
        this.addToDoPane.setLayout(new BoxLayout(this.addToDoPane, BoxLayout.X_AXIS));
        this.addToDoPane.setBorder(new TitledBorder("Add a new TO DO"));
        this.addToDoPane.setPreferredSize(new Dimension(SIZE_W, SIZE_H));

        this.toDoContainer = new JPanel();
        this.toDoContainer.setLayout(new BoxLayout(this.toDoContainer, BoxLayout.X_AXIS));

        this.leftBox = new JPanel();
        this.leftBox.setLayout(new BoxLayout(this.leftBox, BoxLayout.Y_AXIS));

        this.lblDescription = new JLabel("Description:");
        this.chCompleted = new JCheckBox("Completed");
        this.chCompleted.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(final ItemEvent e) {
                System.out.println(e.getStateChange() == ItemEvent.SELECTED
                    ? "SELECTED" : "DESELECTED");
                e.setSource(true);
            }
        });

        leftBox.add(wrapperPanel(this.lblDescription, FlowLayout.LEFT));
        leftBox.add(wrapperPanel(this.chCompleted, FlowLayout.LEFT));

        this.rightBox = new JPanel();
        this.descriptionArea = new JTextArea(T1, T2);
        this.descriptionArea.setLineWrap(true);
        this.descriptionArea.setEditable(true);

        this.rightBox.setLayout(new BoxLayout(this.rightBox, BoxLayout.Y_AXIS));
        this.rightBox.add(wrapperPanel(this.descriptionArea, FlowLayout.RIGHT));

        this.toDoContainer.add(this.leftBox);
        this.toDoContainer.add(this.rightBox);

        this.btnAddGuest = new JButton("Confirm");
        this.btnAddGuest.addActionListener(this);
        this.btnAddGuest.setBackground(new Color(ViewColor.lightblue.getRed(), 
                ViewColor.lightblue.getGreen(),
                ViewColor.lightblue.getBlue()));

        this.container = new JPanel();
        this.container.setBackground(new Color(ViewColor.light.getRed(), 
                ViewColor.light.getGreen(),
                ViewColor.light.getBlue()));
        this.container.setLayout(new BoxLayout(this.container, BoxLayout.Y_AXIS));
        this.container.add(this.toDoContainer);
        this.container.add(wrapperPanel(this.btnAddGuest, FlowLayout.CENTER));

        this.bordToDoContainer = new JPanel(new BorderLayout());
        this.bordToDoContainer.setBackground(new Color(ViewColor.light.getRed(),
                ViewColor.light.getGreen(),
                ViewColor.light.getBlue()));
        this.bordToDoContainer.add(this.container, BorderLayout.NORTH);
        this.addToDoPane.add(this.bordToDoContainer);
        this.add(this.addToDoPane, BorderLayout.WEST);


        this.showToDoPane = new JPanel();
        this.showToDoPane.setBackground(new Color(ViewColor.light.getRed(), 
                ViewColor.light.getGreen(),
                ViewColor.light.getBlue()));
        this.showToDoPane.setLayout(new BoxLayout(this.showToDoPane, BoxLayout.X_AXIS));
        this.showToDoPane.setBorder(new TitledBorder("Show to do"));
        this.tablePane = new JPanel(new FlowLayout());
        this.tablePane.setBackground(new Color(ViewColor.light.getRed(),
                ViewColor.light.getGreen(),
                ViewColor.light.getBlue()));
        this.east = new JPanel(new BorderLayout());

        this.east.setBorder(new TitledBorder("Show to do"));
        this.rmvGuest = new JButton("Delete to do");
        this.rmvGuest.setBackground(new Color(ViewColor.lightblue.getRed(),
                ViewColor.lightblue.getGreen(),
                ViewColor.lightblue.getBlue()));
        this.modify = new JButton("Modify to do");
        this.modify.setBackground(new Color(ViewColor.lightblue.getRed(),
                ViewColor.lightblue.getGreen(),
                ViewColor.lightblue.getBlue()));
        this.modify.addActionListener(e-> 
        this.observer.modifyToDo(this.descriptionArea.getText(),
                this.chCompleted.isSelected()));
        this.rmvGuest.addActionListener(e ->this.observer
                .removeToDo(this.descriptionArea.getText()));
        this.east.add(this.tablePane, BorderLayout.NORTH);
        this.east.setBackground(new Color(ViewColor.light.getRed(), ViewColor.light.getGreen(),
                ViewColor.light.getBlue()));
        this.eastSouth = new JPanel(new FlowLayout());
        this.eastSouth.setBackground(new Color(ViewColor.light.getRed(), 
                ViewColor.light.getGreen(),
                ViewColor.light.getBlue()));
        this.eastSouth.add(this.rmvGuest);
        this.eastSouth.add(this.modify);
        this.east.add(this.eastSouth, BorderLayout.CENTER);
        this.add(this.east, BorderLayout.CENTER);

       this.rebuildTable(data);

    }

    private static JPanel wrapperPanel(final JComponent component, final int orientation) {
        final JPanel panel = new JPanel(new FlowLayout(orientation));
        panel.add(component);
        panel.setBackground(new Color(ViewColor.light.getRed(), ViewColor.light.getGreen(),
                ViewColor.light.getBlue()));
        return  panel;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {

        final Object isPressed = e.getSource();

        if (isPressed == this.btnAddGuest) {
            this.observer.confirmToDo(this.getDescriptionArea(), this.getChCompleted());
            this.descriptionArea.setText("");
            this.chCompleted.setSelected(true);
        }
    }


 
    /**
     * 
     * @return boolean
     *          true or false if is completed.
     */
    public boolean getChCompleted() {
        return chCompleted.isSelected();
    }
    /**
     * 
     * @return String
     *          the description.
     */
    public String getDescriptionArea() {
        return descriptionArea.getText();
    }

    @Override
    public void rebuildTable(final Object[][] data) {

        final DefaultTableModel dtm = new DefaultTableModel(data, TODO_TABLE_TITLES) {

            private static final long serialVersionUID = -3607203150074239908L;

            @Override
            public boolean isCellEditable(final int row, final int column) {
                return false;
            }
        };
        this.table = new JTable(dtm);
        this.table.setBackground(new Color(ViewColor.light.getRed(), ViewColor.light.getGreen(),
                ViewColor.light.getBlue()));
        this.table.getColumnModel().getColumn(1).setPreferredWidth(CF_COLUMN_WIDTH);
        this.table.getSelectionModel().addListSelectionListener(x-> {
            final int row = this.table.convertRowIndexToModel(this.table.getSelectedRow());
            this.observer.selectToDo(row);
        });
        this.tablePane.removeAll();
       final JScrollPane tableScroll = new JScrollPane(this.table);
        tableScroll.setPreferredSize(new Dimension((int) (Toolkit.getDefaultToolkit()
                .getScreenSize().getWidth() * DB1), 
                (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * DB2)));

        this.tablePane.add(wrapperPanel(tableScroll, FlowLayout.CENTER));
        this.validate();
        this.repaint();
    }
    /**
     * a static method for return the name of table.
     * @return String[]
     *          the name of the table titles
     */
    public static String[] getToDoTableTitles() {
        return TODO_TABLE_TITLES;
    }

    @Override
    public void init() {
            this.setEnabled(true);
    }
}

package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import controller.DateUtility;
import controller.EmployeeControllerImpl;
import controller.PlantingGeneratorControllerImpl;
/**
 * view used to add a plant to the greenhouse.
 */
public class PlantingGeneratorView extends JFrame implements EmployeesView, View {
    /**
     * 
     */
    private static final long serialVersionUID = 325895284120010040L;
    private static final String[] TABLE_COLUMNS = { "Nome", "Cognome" };
    private static final int NUM_FLOWER_TYPE = 5;
    private static final int DIMENSION = 70;
    private JTable employeeTable;
    private JPanel employeePnl;
    private PlantingGeneratorControllerImpl controller;
    private JScrollPane tableScroll;
    private final ChoosingPanelImpl cPanel = new ChoosingPanelImpl();
    private final ArrayList<JToggleButton> selectBtn = new ArrayList<>();
    private final ButtonGroup flowersBtnGroup = new ButtonGroup(); 
    private final JButton confirm;

    /**
     * 
     * @param ctrl
     *              controller
     */
    public PlantingGeneratorView(final PlantingGeneratorControllerImpl ctrl) {
        this.controller = ctrl;
        Object[][] table = EmployeeControllerImpl.getInstance().getEmployeeTable();
        //panel to select type of plant
        JPanel selectType = new JPanel();
        selectType.setBorder(new TitledBorder("Select type of plant:"));
        selectType.setLayout(new GridLayout(1, NUM_FLOWER_TYPE));

        final URL sunflowerURL = getClass().getResource("/sunflower.png");
        ImageIcon sunflowerIcn = new ImageIcon(new ImageIcon(sunflowerURL).getImage().getScaledInstance(DIMENSION, DIMENSION, 
                Image.SCALE_DEFAULT));
        JToggleButton sunflowerBtn = new JToggleButton();
        sunflowerBtn.setIcon(sunflowerIcn);
        sunflowerBtn.setText("Sunflower");
        sunflowerBtn.setBackground(Colors.getDarkGreen());
        sunflowerBtn.addActionListener(l -> {
            try {
                this.controller.updatePlant(1);
            } catch (NullPointerException e) {
                System.out.println("NullPointerException selectType");
            }
        });

        final URL roseURL = getClass().getResource("/rose.png");
        ImageIcon roseIcn = new ImageIcon(new ImageIcon(roseURL).getImage().getScaledInstance(DIMENSION, DIMENSION,
                Image.SCALE_DEFAULT));
        JToggleButton roseBtn = new JToggleButton();
        roseBtn.setIcon(roseIcn);
        roseBtn.setText("Rose");
        roseBtn.setBackground(Colors.getDarkGreen());
        roseBtn.addActionListener(l -> {
            try {
                this.controller.updatePlant(0);
            } catch (NullPointerException e) {
                System.out.println("NullPointerException selectType");
            }
        });

        final URL lilyURL = getClass().getResource("/lily.png");
        ImageIcon lilyIcn = new ImageIcon(new ImageIcon(lilyURL).getImage().getScaledInstance(DIMENSION, DIMENSION,
                Image.SCALE_DEFAULT));
        JToggleButton lilyBtn = new JToggleButton();
        lilyBtn.setIcon(lilyIcn);
        lilyBtn.setText("Lily");
        lilyBtn.setBackground(Colors.getDarkGreen());
        lilyBtn.addActionListener(l -> {
            try {
                this.controller.updatePlant(3);
            } catch (NullPointerException e) {
                System.out.println("NullPointerException selectType");
            }
        });

        final URL tulipURL = getClass().getResource("/tulip.png");
        ImageIcon tulipIcn = new ImageIcon(new ImageIcon(tulipURL).getImage().getScaledInstance(DIMENSION, DIMENSION,
                Image.SCALE_DEFAULT));
        JToggleButton tulipBtn = new JToggleButton();
        tulipBtn.setIcon(tulipIcn);
        tulipBtn.setText("Tulip");
        tulipBtn.setBackground(Colors.getDarkGreen());
        tulipBtn.addActionListener(l -> {
            try {
                this.controller.updatePlant(2);
            } catch (NullPointerException e) {
                System.out.println("NullPointerException selectType");
            }
        });

        final URL genericURL = getClass().getResource("/generic.png");
        ImageIcon genericIcn = new ImageIcon(new ImageIcon(genericURL).getImage().getScaledInstance(DIMENSION, DIMENSION,
                Image.SCALE_DEFAULT));
        JToggleButton genericBtn = new JToggleButton();
        genericBtn.setIcon(genericIcn);
        genericBtn.setText("Generic");
        genericBtn.setBackground(Colors.getDarkGreen());
        genericBtn.addActionListener(l -> {
            try {
                this.controller.updatePlant(4);
            } catch (NullPointerException e) {
                System.out.println("NullPointerException selectType");
            }
        });

        //create a buttongroup containing previous buttons
        selectBtn.add(roseBtn);
        selectBtn.add(sunflowerBtn);
        selectBtn.add(tulipBtn);
        selectBtn.add(lilyBtn);
        selectBtn.add(genericBtn);
        this.selectBtn.forEach(b -> {
            flowersBtnGroup.add(b);
        });

        for (JToggleButton btn : selectBtn) {
            selectType.add(btn);
        }

        selectType.setBackground(Colors.getBackgroundColor());

        //panel to choose date to pick the plant
        JPanel chooseDate = new JPanel();
        chooseDate.setBorder(new TitledBorder("Choose date to pick it:"));
        JCalendar calendar = new JCalendar();
        calendar.addPropertyChangeListener(l -> this.controller.updateDate(DateUtility.convert(calendar.getDate())));
        chooseDate.add(calendar);
        chooseDate.setBackground(Colors.getBackgroundColor());

        //panel to select employee
        JPanel selectEmpl = new JPanel();
        selectEmpl.setBorder(new TitledBorder("Select an employee:"));
        this.employeePnl = new JPanel(new FlowLayout());
        selectEmpl.add(employeePnl, BorderLayout.NORTH);
        selectEmpl.setBackground(Colors.getBackgroundColor());
        selectEmpl.setMaximumSize(new Dimension(Integer.MAX_VALUE, selectEmpl.getMinimumSize().height));
        this.rebuildTable(table);

        //button to confirm or cancel
        JPanel addplant = new JPanel(new GridLayout(1, 2));
        confirm = new JButton("Add Plant");
        confirm.addActionListener(e -> {
            try {
                this.controller.updatePlanting();
                this.controller.confirm();
            } catch (NullPointerException e2) {
                showError(this, "No plant type selected");
            }

        });
        confirm.setBackground(Colors.getDarkGreen());
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(e -> this.close());

        cancel.setBackground(Colors.getDarkGreen());
        addplant.add(confirm);
        addplant.add(cancel);
        addplant.setBackground(Colors.getBackgroundColor());

        cPanel.add(selectType);
        cPanel.add(chooseDate);
        cPanel.add(selectEmpl);
        cPanel.add(addplant);


        this.setLayout(new BorderLayout());

        this.setContentPane(cPanel);

        this.setBackground(Colors.getBackgroundColor());
        this.setLocationByPlatform(true);
        this.pack();
        this.setResizable(false);
        this.setVisible(true);

    }

    @Override
    public void rebuildTable(final Object[][] employee) {
        final DefaultTableModel dtm = new DefaultTableModel(employee, TABLE_COLUMNS) {
            /**
             * 
             */
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(final int row, final int column) {
                return false;
            }
        };
        this.employeeTable = new JTable(dtm); 
        this.employeeTable.getSelectionModel().addListSelectionListener(e -> {
            final int row = this.employeeTable.convertRowIndexToModel(this.employeeTable.getSelectedRow());
            EmployeeControllerImpl.getInstance().selectEmployee(row);
            this.controller.updateEmployee(row);
            this.tableScroll.setViewportView(this.employeeTable);

        });

        employeeTable.setPreferredScrollableViewportSize(employeeTable.getPreferredSize());
        employeeTable.setFillsViewportHeight(true);
        this.employeePnl.removeAll();
        this.tableScroll = new JScrollPane(this.employeeTable, 
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.employeePnl.add(this.tableScroll, BorderLayout.NORTH);
        this.employeePnl.setBackground(Colors.getBackgroundColor());

        this.validate();
        this.repaint();

    }

    /**
     * method used to hide the frame.
     */
    public void close() {
        this.setVisible(false);
    }
    /**
     * sets the controller used as a parameter.
     * @param ctrl
     *                  the controller itself
     */
    public void setController(final PlantingGeneratorControllerImpl ctrl) {
        this.controller = ctrl;
    }

    @Override
    public void resetTextFields() {

    }

    @Override
    public void init() {
        this.setVisible(true);
    }

}

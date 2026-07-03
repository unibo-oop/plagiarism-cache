package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.time.LocalDate;
import java.util.Calendar;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;

import org.junit.experimental.theories.FromDataPoints;

import com.toedter.calendar.JCalendar;

import controller.DateUtility;
import controller.EmployeeControllerImpl;
import controller.GreenhouseControllerImpl;
import model.PlantImpl;
import model.PlantingImpl;


/**
 * main view of the program. Shows the state of the greenhouse.
 *
 */
public class GreenhouseViewImpl extends JPanel implements GreenhouseView, View {

    /**
     * 
     */
    private static final long serialVersionUID = 6568500909251069681L;
    private static final int DIMENSION = 50;
    private static final int NUM_PLANTS = 40;
    private JToggleButton[] plantsBtns; //list of buttons representing the flowers in the greenhouse
    private JPanel[] plantsPanels = new JPanel[NUM_PLANTS]; //list of buttons representing the flowers in the greenhouse

    private ButtonGroup flowersBtnGroup = new ButtonGroup();
    private JPanel scrollPnl = new JPanel();
    private JPanel plants = new JPanel();
    private final JScrollPane scrollPane = new JScrollPane(plants);
    private ChoosingPanelImpl cPanel = new ChoosingPanelImpl();
    private JPanel greenhouse = new JPanel();
    private JButton addPlant;
    private GreenhouseControllerImpl controller;
    private EmployeeControllerImpl employeeController;
    private PlantingGeneratorView addPlantFrame;
    private PlantImpl select;
    private JCalendar calendar;
    /**
     * builder.
     * @param ctrl
     *              controller
     */
    public GreenhouseViewImpl(final GreenhouseControllerImpl ctrl) {
        this.greenhouse.setLayout(new BorderLayout());
        this.scrollPane.setSize(greenhouse.getSize());
        this.controller = ctrl;
        this.employeeController = EmployeeControllerImpl.getInstance();
        //panel to show plants to pick on the chosen day
        JPanel showGH = new JPanel(new BorderLayout());
        showGH.setBorder(new TitledBorder("Show Plants to pick on this date"));
        showGH.setBackground(Colors.getBackgroundColor());

        //jcalendar to choose date
        JPanel calendarPnl = new JPanel(new BorderLayout());
        this.calendar = new JCalendar();
        //calendar.setBackground(c1);
        calendarPnl.add(calendar, BorderLayout.CENTER);
        calendarPnl.setBackground(Colors.getBackgroundColor());

        //button to show plants
        JPanel updatePnl = new JPanel();
        JButton update = new JButton("Show Plants");
        update.addActionListener(l -> {
            this.controller.getPlantsToPickInDate(DateUtility.convert(calendar.getDate()));
            System.out.println(DateUtility.convert(calendar.getDate()));
        });
        update.setBackground(Colors.getDarkGreen());
        updatePnl.add(update);
        updatePnl.setBackground(Colors.getBackgroundColor());
        updatePnl.setMaximumSize(new Dimension(Integer.MAX_VALUE, updatePnl.getMinimumSize().height));

        //add every component needed by showGH to it
        showGH.add(calendarPnl);
        showGH.add(update, BorderLayout.AFTER_LAST_LINE);
        showGH.setMaximumSize(new Dimension(Integer.MAX_VALUE, showGH.getMinimumSize().height));

        //panel to manage plants
        final JPanel manage = new JPanel(new GridLayout(2, 1));
        manage.setBorder(new TitledBorder("Manage plants"));
        final JLabel info3 = new JLabel("Click the button if you want to add a plant.                              ");
        this.addPlant = new JButton("Add Plant");
        this.addPlant.addActionListener(l -> {
            ctrl.openPlantingGenerator();
        });
        this.addPlant.setBackground(Colors.getDarkGreen());

        manage.add(info3);
        manage.add(addPlant);

        manage.setBackground(Colors.getBackgroundColor());
        manage.setVisible(true);
        manage.setMaximumSize(new Dimension(Integer.MAX_VALUE, manage.getMinimumSize().height));


        this.cPanel.add(showGH);
        this.cPanel.add(manage);


        //create scrollpane to add plants
        this.plants.setLayout(new GridLayout(NUM_PLANTS / 2, 2));

        plantsBtns = new JToggleButton[NUM_PLANTS];
        for (int i = 0; i < NUM_PLANTS; i++) {
            plantsBtns[i] = new JToggleButton("");
            plantsBtns[i].setText("Empty Space");
            plantsBtns[i].setBackground(Colors.getFreeStatusColor());
            plantsBtns[i].addActionListener(actionListener);
            this.flowersBtnGroup.add(plantsBtns[i]);
        }


        //set plant status color
        for (final JToggleButton btn : plantsBtns) {
            this.plants.add(btn);
        }


        this.plants.setBackground(Colors.getBackgroundColor());

        this.greenhouse.add(scrollPane);
        this.greenhouse.setBorder(new TitledBorder("Today's Greenhouse"));
        this.greenhouse.setBackground(Colors.getBackgroundColor());

        this.setLayout(new BorderLayout());

        this.add(cPanel, BorderLayout.WEST);
        this.add(greenhouse, BorderLayout.CENTER);

    }

    @Override
    public void changeStatusColor(final int pos, final boolean status) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setPlantable(final boolean status) {
        //if space is empty (status is free)
        if (status) {
            this.addPlant.setEnabled(true);
        }

    }
    /**
     * gets the right icon associated with the plant.
     * @param name
     *              name of the plant
     * @return
     *          right icon for the plants
     */

    public ImageIcon getPlantIcon(final String name) {

        switch (name) {
        case "Rose":
            final URL roseURL = getClass().getResource("/rose.png");
            final ImageIcon roseIcn = new ImageIcon(new ImageIcon(roseURL).getImage().getScaledInstance(DIMENSION, DIMENSION, Image.SCALE_DEFAULT));
            return roseIcn;
        case "Sunflower":
            final URL sunflowerURL = getClass().getResource("/sunflower.png");
            final ImageIcon sunflowerIcn = new ImageIcon(new ImageIcon(sunflowerURL).getImage().getScaledInstance(DIMENSION, DIMENSION, Image.SCALE_DEFAULT));
            return sunflowerIcn;
        case "Tulip":
            final URL tulipURL = getClass().getResource("/tulip.png");
            final ImageIcon tulipIcn = new ImageIcon(new ImageIcon(tulipURL).getImage().getScaledInstance(DIMENSION, DIMENSION, Image.SCALE_DEFAULT));
            return tulipIcn;
        case "Lily":
            final URL lilyURL = getClass().getResource("/lily.png");
            final ImageIcon lilyIcn = new ImageIcon(new ImageIcon(lilyURL).getImage().getScaledInstance(DIMENSION, DIMENSION, Image.SCALE_DEFAULT));
            return lilyIcn;
        default:
            final URL genericURL = getClass().getResource("/generic.png");
            final ImageIcon genericIcn = new ImageIcon(new ImageIcon(genericURL).getImage().getScaledInstance(DIMENSION, DIMENSION, Image.SCALE_DEFAULT));
            return genericIcn;
        }
    }


    @Override
    public void init() {
        // TODO Auto-generated method stub
    }
    /**
     * creates the plant button with the given information.
     * @param plant
     *              plant with its information
     * @param pos
     *              index of the button
     */
    public void createPlantButton(final PlantingImpl plant, final int pos) {
        try {
            plantsBtns[pos].setLayout(new BorderLayout());
            plantsBtns[pos].setIcon(getPlantIcon(plant.getPlant().getName())); 
            plantsBtns[pos].setText(plant.getPlant().getName() + " - Picking Date: " + plant.getDates().getSecond());
            plantsBtns[pos].setBackground(Colors.getPlantedStatusColor());

        } catch (NullPointerException e) {
            showError(this, "No plant type selected");
        }

    }

    @Override
    public void resetPlantButton(int pos) {
        plantsBtns[pos].setText("Empty Space");
        plantsBtns[pos].setIcon(null);
        plantsBtns[pos].setBackground(Colors.getFreeStatusColor());
    }

    private ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(final ActionEvent actionEvent) {
            AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
            boolean selected = abstractButton.getModel().isSelected();
        }
      };

      /**
       * 
       * @param plant
       *            plant
       */
      public void setPlantSelected(final PlantImpl plant) {
          this.select = plant;
      }
      /**
       * updates the greenhouse view.
       */
      public void updateView() {
          for (PlantingImpl planting: GreenhouseControllerImpl.getInstance().getPlantingManager().getSummaryToPickDate(LocalDate.now())) {
              createPlantButton(planting, GreenhouseControllerImpl.getInstance().getPlantingManager().getSummaryToPickDate(LocalDate.now()).indexOf(planting));
          }
      }
}
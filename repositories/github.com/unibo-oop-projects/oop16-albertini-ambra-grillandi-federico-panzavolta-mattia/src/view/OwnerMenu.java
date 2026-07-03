package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 * The GUI used by the owner to manage the cinema.
 */
public class OwnerMenu extends JFrame {

    private static final long serialVersionUID = 2266624406058379531L;

    private static final double HEIGHT_PERC = 0.15;
    private static final double WIDTH_PERC = 0.4;
    private static final String TEXT = "What would you like to do?"; 
    private static final String TITLE = "Owner Menu";

    private final JButton balance = new JButton("Balance");
    private final JButton booking = new JButton("Booking");
    private final JButton mainMenu = new JButton("Main Menu'");
    private final JButton roomManagment = new JButton("Room Management");
    private final JPanel panel = new JPanel();

    /**
     * Build a new GUI for manage the cinema.
     */
    public OwnerMenu() {
        super();

        //Set dimension of the window
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize((int) (screenSize.getWidth() * WIDTH_PERC), (int) (screenSize.getHeight() * HEIGHT_PERC));

        //Set background color for the panel
        this.panel.setBackground(Color.WHITE);

        //Set border of my panel
        final Border b2 = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),
                TEXT, TitledBorder.CENTER, TitledBorder.TOP);
        this.panel.setBorder(b2);

        //Add the buttons to panel
        this.panel.add(this.roomManagment);
        this.panel.add(this.balance);
        this.panel.add(this.booking);
        this.panel.add(this.mainMenu);

        //Add actionListner to the buttons
        this.roomManagment.addActionListener(e -> {
            //Set the GUI not visible and call the view's method roomManagement
            this.setVisible(false);
            ViewImpl.getView().roomManagement();
        });

        this.balance.addActionListener(e -> {
            //Call the view's method cinemaBalance
            ViewImpl.getView().cinemaBalance();
        });

        this.booking.addActionListener(e -> {
            //Set the GUI not visible and call the view's method setDiscount
            this.setVisible(false);
            ViewImpl.getView().setDiscount(false);
        });

        this.mainMenu.addActionListener(e -> {
            this.setVisible(false);
            ViewImpl.getView().startingView();
        });

        //Fix last setting
        this.setTitle(TITLE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().add(this.panel);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}

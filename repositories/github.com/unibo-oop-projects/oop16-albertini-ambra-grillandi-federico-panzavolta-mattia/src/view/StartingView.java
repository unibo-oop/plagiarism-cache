package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 * The View launched at the start of application.
 */
public class StartingView extends JFrame {

    private static final long serialVersionUID = 5122287437018348209L;

    private static final double HEIGHT_PERC = 0.15;
    private static final double WIDTH_PERC = 0.3;
    private static final int TEXT_DIMENSION = 14;
    private static final String TEXT = "Who you are? Owner, Staff or Client?";
    private static final String TITLE = "Role";

    private final JButton client = new JButton("Client");
    private final JButton owner = new JButton("Owner");
    private final JButton staff = new JButton("Staff");
    private final JPanel panel = new JPanel();

    /**
     * Builds a new GUI.
     */
    public StartingView() {
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

        //Add the three buttons to panel
        this.panel.add(this.owner);
        this.panel.add(this.staff);
        this.panel.add(this.client);

        //Set details for each button
        final Font button = new Font("Button", Font.PLAIN, TEXT_DIMENSION);
        this.owner.setFont(button);
        this.owner.setToolTipText("Click here if you are the owner and want to manage the cinema");
        this.staff.setFont(button);
        this.staff.setToolTipText("Click here if you are a memeber of the staff and want to sell tickets");
        this.client.setFont(button);
        this.client.setToolTipText("Click here if you want to book tickets for a specific movie");

        //Add actionListner to the three button
        this.owner.addActionListener(e -> {
            //Call the view's method verifyOwnerPassword
            ViewImpl.getView().verifyOwnerPassword(this);
        });

        this.staff.addActionListener(e -> {
            //Call the view's method verifyStaffPassword
            ViewImpl.getView().verifyStaffPassword(this);
        });

        this.client.addActionListener(e -> {
            ///Call the view's method setDiscount and set this GUI not visible
            this.setVisible(false);
            ViewImpl.getView().setDiscount(true);
        });

        //Fix last setting
        this.setTitle(TITLE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().add(this.panel);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        //Set owner's password only if he doesn't have it
        if (!ViewImpl.getController().checkOwnerPassword()) {
            ViewImpl.getView().setOwnerPassword();
        }
        //Set staff's password only if he doesn't have it
        if (!ViewImpl.getController().checkStaffPassword()) {
            ViewImpl.getView().setStaffPassword();
        }
    }
}
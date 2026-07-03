package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 * The view for setting the ticket's discounts.
 */
public class SetDiscounts extends JFrame {

    private static final long serialVersionUID = 2615200234610681003L;

    private static final double HEIGHT_PERC = 0.3;
    private static final double WIDTH_PERC = 0.5;
    private static final int DISTANCE = 5;
    private static final String ERROR_MESSAGE = "Invalid Input";
    private static final String TEXT = "Enter information to purchase the ticket in numerical form";
    private static final String TEXT_DIALOG_ONE = "The price amounts to ";
    private static final String TEXT_DIALOG_TWO = "$. You will pay this amount to the box office"
            + " at the entrance of the cinema";
    private static final String TITLE = "Discounts";

    private final boolean online;
    private final JButton mainMenu = new JButton("Main Menu'");
    private final JButton submit = new JButton("Submit");

    private final JLabel tickets = new JLabel("Number of tickets:");
    private final JTextField ticketsText = new JTextField(3);
    private final JLabel ticketsError = new JLabel(ERROR_MESSAGE);

    private final JLabel years = new JLabel("Number of person under 14:");
    private final JTextField yearsText = new JTextField(3);
    private final JLabel yearsError = new JLabel(ERROR_MESSAGE);

    private final JLabel students = new JLabel("Number of University' students:");
    private final JTextField studentsText = new JTextField(3);
    private final JLabel studentsError = new JLabel(ERROR_MESSAGE);
    private final JPanel panel = new JPanel();

    private double price;
    private int nTickets;
    private int nUnder14;

    /**
     * Build a new GUI for setting the ticket's discounts.
     * @param online the parameter to know if the booking is online or not.
     */
    public SetDiscounts(final boolean online) {
        super();

        this.online = online;

        //Set dimension of the window
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize((int) (screenSize.getWidth() * WIDTH_PERC), (int) (screenSize.getHeight() * HEIGHT_PERC));

        //Set layout and background color for the panel
        this.panel.setLayout(new GridBagLayout());
        this.panel.setBackground(Color.WHITE);

        //Set border of my panel
        final Border b2 = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),
                TEXT, TitledBorder.CENTER, TitledBorder.TOP);
        this.panel.setBorder(b2);

        //Set the Component's position in GridBagLayout
        final GridBagConstraints gbc = new GridBagConstraints();

        //Set position of component tickets
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(DISTANCE, 0, 0, DISTANCE * 2);
        this.panel.add(this.tickets, gbc);

        //Set position of component ticketsText
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        this.panel.add(this.ticketsText, gbc);

        //Set position of component ticketsError
        this.ticketsError.setForeground(Color.RED);
        this.ticketsError.setVisible(false);
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        this.panel.add(this.ticketsError, gbc);

        //Set position of component years
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(DISTANCE * 3, 0, 0, DISTANCE * 2);
        gbc.anchor = GridBagConstraints.LINE_END;
        this.panel.add(this.years, gbc);

        //Set position of component yearsText
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        this.panel.add(this.yearsText, gbc);

        //Set position of component yearsError
        this.yearsError.setForeground(Color.RED);
        this.yearsError.setVisible(false);
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        this.panel.add(this.yearsError, gbc);

        //Set position of component students
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(DISTANCE * 3, 0, 0, DISTANCE * 2);
        gbc.anchor = GridBagConstraints.LINE_END;
        this.panel.add(this.students, gbc);

        //Set position of component studentsText
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        this.panel.add(this.studentsText, gbc);

        //Set position of component studentsError
        this.studentsError.setForeground(Color.RED);
        this.studentsError.setVisible(false);
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        this.panel.add(this.studentsError, gbc);

        //Set position of component submit
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(DISTANCE * DISTANCE, 0, 0, DISTANCE * 2);
        this.panel.add(this.submit, gbc);

        //Set position of component mainMenu
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_END;
        this.panel.add(this.mainMenu, gbc);

        //Add actionListener to submit button
        this.submit.addActionListener(this.listenerForSubmit());

        //Add actionListener to mainMenu button
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

    /**
     * @return true if the input parameters are in numerical form, false if are null or not correct.
     */
    private boolean controlDiscount() {
        try {
            //Set the discount's parameters
            this.nTickets = Integer.parseInt(this.ticketsText.getText());
            this.nUnder14 = Integer.parseInt(this.yearsText.getText());
            final int nStudents = Integer.parseInt(this.studentsText.getText());
            if (!ViewImpl.getController().checkDiscount(this.nTickets, this.nUnder14, nStudents)) {
                return false;
            }
            this.price = ViewImpl.getController().booking(this.online);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * @return the ActionListener for the submit button.
     */
    private ActionListener listenerForSubmit() {

        return new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                final boolean discount = controlDiscount();
                if (!discount) {
                    ticketsError.setVisible(true);
                    ticketsText.setText(null);
                    yearsError.setVisible(true);
                    yearsText.setText(null);
                    studentsError.setVisible(true);
                    studentsText.setText(null);
                } else {
                    //Show a MessageDialog, call the view's method selectFilm and set this GUI not visible
                    JOptionPane.showMessageDialog(SetDiscounts.this, TEXT_DIALOG_ONE + price + TEXT_DIALOG_TWO,
                            TITLE, JOptionPane.DEFAULT_OPTION);
                    setVisible(false);
                    ViewImpl.getView().selectFilm(nTickets, nUnder14);
                }
            }
        };
    }
}
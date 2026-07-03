package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 * The GUI to choose seats to sit.
 */
public class SelectSeatsInRoom extends JFrame {

    private static final long serialVersionUID = 7378179324290462602L;

    private static final String CENTRAL_TEXT = "Select the seats you want, "
            + "ONCE YOU SELECT ONE, YOU CAN'T CHANGE THE PROJECTION ANYMORE";
    private static final String NORTHERN_TEXT = "Select the projection you like";
    private static final String TITLE = "Select Seats";

    private final int selectedRoom;
    private final JPanel panel = new JPanel();
    private final List<JRadioButton> list = new ArrayList<>();

    private int nTickets;
    private int selectedProjection;
    private List<Map<Character, List<Boolean>>> projectionMaps;

    /**
     * The constructor of class to select the seats for watching the film.
     * @param selectedRoom the parameter to know the room in which the film are projected.
     * @param nTickets the parameter to know the number of booked tickets.
     */
    public SelectSeatsInRoom(final int selectedRoom, final int nTickets) {
        super();

        //Set nTickets and selectedRoom
        this.selectedRoom = selectedRoom;
        this.nTickets = nTickets;

        //Set dimension of the window
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize((int) (screenSize.getWidth()), (int) (screenSize.getHeight()));

        //Set layout and background color for the panel
        this.panel.setLayout(new BorderLayout());
        this.panel.setBackground(Color.WHITE);

        //Set border of my panel
        final Border b2 = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE),
                NORTHERN_TEXT, TitledBorder.CENTER, TitledBorder.TOP);
        this.panel.setBorder(b2);

        //Insert the component to the panel
        this.panel.add(this.northPanel(), BorderLayout.NORTH);

        //Fix last setting
        this.setTitle(TITLE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().add(this.panel);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * @return the panel to set as central panel.
     */
    private JPanel centerPanel() {
        //Create the central panel
        final JPanel centralPanel = new JPanel();

        this.projectionMaps = ViewImpl.getController().getScreeningListForRoom(this.selectedRoom);

        //Set the dimension of GridLayout
        final int x = this.projectionMaps.get(this.selectedProjection).size();
        final int y = this.projectionMaps.get(this.selectedProjection).values().iterator().next().size();

        //Set layout and background color for the panel
        centralPanel.setLayout(new GridLayout(x, y));
        centralPanel.setBackground(Color.WHITE);

        //Set border of my panel
        final Border b2 = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE),
                CENTRAL_TEXT, TitledBorder.CENTER, TitledBorder.TOP);
        centralPanel.setBorder(b2);

        //Insert one button for each seats in the room
        for (final Map.Entry<Character, List<Boolean>> entry : this.projectionMaps.get(this.selectedProjection)
                .entrySet()) {
            final String fila = new String("F" + entry.getKey());
            for (int i = 0; i < y; i++) {

                //Set name, text and color of JButton
                final JButton button = new JButton(fila + "\nP" + i);
                button.setName(String.valueOf(i));
                button.setForeground(Color.WHITE);

                //If the seats is free, add ActionListener for when it will be pressed 
                if (entry.getValue().get(i).booleanValue()) {
                    button.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(final ActionEvent arg0) {
                            //Change the value of the selected seat and disable the northern panel
                            final int position = Integer.parseInt(button.getName());
                            if (nTickets > 0 || (!entry.getValue().get(position).booleanValue())) {
                                final List<Boolean> newValue = entry.getValue();
                                newValue.set(position, !newValue.get(position).booleanValue());
                                entry.setValue(newValue);
                                list.stream().forEach(i -> i.setEnabled(false));

                                //Change color of the button, red if becomes false, white otherwise
                                if (!newValue.get(position).booleanValue()) {
                                    button.setForeground(Color.RED);
                                    //Decreases the number of remained tickets and if became 0, start the CheckUp
                                    nTickets--;
                                    if (nTickets == 0) {
                                        new CheckUp();
                                    }
                                } else {
                                    //Incremented the number of remained tickets
                                    button.setForeground(Color.WHITE);
                                    nTickets++;
                                }
                            }
                        }
                    });
                } else {
                    button.setEnabled(false);
                }
                //Add button to central panel
                centralPanel.add(button);
            }
        }
        return centralPanel;
    }

    /**
     * @param selectedRoom the parameter to know the room in which the film are projected.
     * @return the panel to set as northern panel.
     */
    private JPanel northPanel() {
        //Create the northern panel
        final JPanel northPanel = new JPanel();

        //Set background color for the panel
        northPanel.setBackground(Color.WHITE);

        //Insert one button for each projection in panel
        final int numProjections = ViewImpl.getController().getScreeningList().get(this.selectedRoom);
        final ButtonGroup bg = new ButtonGroup();
        for (int i = 0; i < numProjections; i++) {
            //Set text and name for JButton
            final JRadioButton button = new JRadioButton("Serata n° " + (i + 1), false);
            button.setName(String.valueOf(i));
            button.addActionListener(e -> {
                //Set the selected projection, add the central panel to the GUI and refresh it
                this.selectedProjection = Integer.parseInt(button.getName());
                this.panel.add(this.centerPanel(), BorderLayout.CENTER);
                this.validate();
            });
            //Add the button at list of button for disable in the future
            list.add(button);

            //Add the button to the ButtonGroup and to the panel
            bg.add(button);
            northPanel.add(button);
        }
        return northPanel;
    }

    /**
     * This class build a JDialog to ask at the user the confirm of the selected seats.
     */
    private final class CheckUp extends JDialog {

        private static final long serialVersionUID = 8297385410397788874L;
        private static final String TEXT = "You have selected your seats for the projection, do you wanna comfirm?";
        private static final String TITLE = "Check Up";

        /**
         * This is the constructor of the class CheckUp.
         */
        CheckUp() {
            super();

            //Build a showMessageDialog to show the price of tickets at the user 
            final int answer = JOptionPane.showConfirmDialog(this, TEXT, TITLE, JOptionPane.YES_NO_OPTION);

            //Set the GUI not visible, return the new seats' map and return to startingView
            if (answer == JOptionPane.YES_OPTION) {
                ViewImpl.getController().setSeatsPosition(projectionMaps.get(selectedProjection),
                        selectedProjection, selectedRoom);
                SelectSeatsInRoom.this.setVisible(false);
                ViewImpl.getView().startingView();
            }
        }
    }
}
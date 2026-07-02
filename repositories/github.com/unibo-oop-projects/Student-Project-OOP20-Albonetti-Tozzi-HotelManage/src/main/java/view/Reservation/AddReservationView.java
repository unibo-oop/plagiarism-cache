package view.Reservation;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;
import com.toedter.calendar.JDateChooser;

import controller.Reservation.ControllerReservation;
import controller.Reservation.ControllerReservationImpl;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Optional;

public class AddReservationView extends JFrame {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    private static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    private static final int VERTICALGAPCOMPONENT = 18;
    private static final int SUBTITLEFONTDIM = 20;
    private static final int LABELFONTDIM = 15;
    private static final int VERTICALCONTAINERGAP = 85;
    private static final int HORIZONTALCONTAINERGAP = 250;
    private static final int HORIZONTALGAPCOMPONENT = 36;
    private static final int VERTICALGAPFRAME = 23;
    private static final int HORIZONTALGAPFRAME = 66;
    private static final int VERTICALGAPNORTHPANEL = 20;
    private static final int CHECKINLABELPREF = 105;
    private static final int ROOMLABELPREF = 110;
    private static final int HORIZONTALGROUPGAP = 34;
    private static final Font FONT = new Font("Tahoma", Font.PLAIN, LABELFONTDIM);

    private final JPanel infoPanel = new JPanel();
    private final JPanel buttonPanel = new JPanel();
    private final JTextField roomTextField = new JTextField();
    private final JTextField clientTextField = new JTextField();
    private final JLabel infoLabel = new JLabel("Inserire i dati della prenotazione");
    private final JButton saveButton = new JButton("Salva");
    private final JButton cancelButton = new JButton("Annulla");
    private final JPanel dataPanel = new JPanel();
    private final JLabel clientLabel = new JLabel("Cliente");
    private final JLabel checkInLabel = new JLabel("Data check-in");
    private final JLabel checkOutLabel = new JLabel("Data check-out");
    private final JLabel roomLabel = new JLabel("Stanza");
    private final JDateChooser checkInDateChooser = new JDateChooser();
    private final JDateChooser checkOutDateChooser = new JDateChooser();
    private final FlowLayout infoPanelLayout = new FlowLayout();
    private final BorderLayout frameLayout = new BorderLayout();
    private final FlowLayout buttonPanelLayout = new FlowLayout();
    private final GroupLayout dataPanelLayout = new GroupLayout(dataPanel);
    private final JButton deleteButton = new JButton("Elimina");

    /**
     * Create the frame.
     */
    public AddReservationView() {

        ControllerReservation reservationController = new ControllerReservationImpl();

        // Initialize the frame
        this.getContentPane().setLayout(frameLayout);
        this.frameLayout.setVgap(VERTICALGAPFRAME);
        this.frameLayout.setHgap(HORIZONTALGAPFRAME);
        this.setTitle("Aggiungi prenotazione");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setBounds(100, 100, SCREEN_WIDTH / 3, SCREEN_HEIGHT / 2);

        // Initialize the north panel
        this.getContentPane().add(infoPanel, BorderLayout.NORTH);
        this.infoPanel.setLayout(infoPanelLayout);
        this.infoPanelLayout.setVgap(VERTICALGAPNORTHPANEL);
        this.infoLabel.setFont(new Font("Tahoma", Font.PLAIN, SUBTITLEFONTDIM));
        this.infoPanel.add(infoLabel);

        // Initialize the south panel
        this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        buttonPanelLayout.setAlignment(FlowLayout.TRAILING);
        this.buttonPanel.setLayout(buttonPanelLayout);
        this.buttonPanel.add(cancelButton);

        /**
         * Action listener for save button
         */
        this.saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                String cf = clientTextField.getText();
                Optional<Date> dateIn = Optional.ofNullable(checkInDateChooser.getDate());
                Optional<Date> dateOut = Optional.ofNullable(checkOutDateChooser.getDate());
                String room = roomTextField.getText();

                if (!cf.isEmpty() && dateIn.isPresent() && dateOut.isPresent() && !room.isEmpty()) {
                    reservationController.addReservation(cf, dateIn.get(), dateOut.get(), Integer.parseInt(room));
                    System.out.println("Ho aggiunto la prenotazione");
                    clientTextField.setText("");
                    checkInDateChooser.setDate(null);
                    checkOutDateChooser.setDate(null);
                    roomTextField.setText("");
                    JDialogView successDialog = new JDialogView("SUCCESSO", "Inserimento avvenuto con SUCCESSO");
                    successDialog.setVisible(true);

                } else {
                    JDialogView failDialog = new JDialogView("ERRORE", "Inserimento FALLITO. Inserire tutti i dati correttamente");
                    failDialog.setVisible(true);
                    System.out.println("Errore nel salvataggio della prenotazione");
                }
            }
        });

        /**
         * Action listener for delete button
         */
        this.deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                String cf = clientTextField.getText();
                Optional<Date> dateIn = Optional.ofNullable(checkInDateChooser.getDate());
                Optional<Date> dateOut = Optional.ofNullable(checkOutDateChooser.getDate());
                String room = roomTextField.getText();

                if (!cf.isEmpty() && dateIn.isPresent() && dateOut.isPresent() && !room.isEmpty()) {
                    reservationController.removeReservation(cf, dateIn.get(), dateOut.get(), Integer.parseInt(room));
                    System.out.println("Ho rimosso la prenotazione");
                    clientTextField.setText("");
                    checkInDateChooser.setDate(null);
                    checkOutDateChooser.setDate(null);
                    roomTextField.setText("");
                    JDialogView successDialog = new JDialogView("SUCCESSO", "Rimozione avvenuta con SUCCESSO");
                    successDialog.setVisible(true);
                } else {
                    JDialogView failDialog = new JDialogView("ERRORE", "Rimozione FALLITA. Inserire tutti i dati correttamente");
                    failDialog.setVisible(true);
                    System.out.println("Errore nell' eliminazione della prenotazione");
                }
            }
        });

        this.buttonPanel.add(saveButton);
        this.buttonPanel.add(deleteButton);

        // Initialize center panel
        this.getContentPane().add(dataPanel, BorderLayout.CENTER);
        this.clientLabel.setFont(FONT);
        this.checkInLabel.setFont(FONT);
        this.checkOutLabel.setFont(FONT);
        this.roomLabel.setFont(FONT);
        this.roomTextField.setColumns(10);
        this.clientTextField.setColumns(10);
        this.dataPanel.setLayout(dataPanelLayout);
        dataPanelLayout.setHorizontalGroup(dataPanelLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(dataPanelLayout.createSequentialGroup().addGap(HORIZONTALGAPCOMPONENT)
                        .addGroup(dataPanelLayout.createParallelGroup(Alignment.LEADING, false)
                                .addComponent(clientLabel, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
                                .addComponent(checkInLabel, GroupLayout.PREFERRED_SIZE, CHECKINLABELPREF, GroupLayout.PREFERRED_SIZE)
                                .addComponent(checkOutLabel)
                                .addComponent(roomLabel, GroupLayout.DEFAULT_SIZE, ROOMLABELPREF, Short.MAX_VALUE))
                        .addGap(HORIZONTALGAPCOMPONENT)
                        .addGroup(dataPanelLayout.createParallelGroup(Alignment.LEADING).addGroup(dataPanelLayout
                                .createParallelGroup(Alignment.LEADING, false).addComponent(roomTextField)
                                .addComponent(checkOutDateChooser, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                                        Short.MAX_VALUE)
                                .addComponent(checkInDateChooser, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                                        Short.MAX_VALUE))
                                .addComponent(clientTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(HORIZONTALCONTAINERGAP, Short.MAX_VALUE)));
        dataPanelLayout.setVerticalGroup(dataPanelLayout.createParallelGroup(Alignment.LEADING).addGroup(dataPanelLayout
                .createSequentialGroup().addGap(HORIZONTALGROUPGAP)
                .addGroup(dataPanelLayout
                        .createParallelGroup(Alignment.LEADING).addComponent(clientLabel).addComponent(clientTextField,
                                GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(VERTICALGAPCOMPONENT)
                .addGroup(
                        dataPanelLayout.createParallelGroup(Alignment.LEADING)
                                .addGroup(dataPanelLayout.createSequentialGroup()
                                        .addComponent(checkInDateChooser, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addGap(VERTICALGAPCOMPONENT)
                                        .addComponent(checkOutDateChooser, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addGap(VERTICALGAPCOMPONENT)
                                        .addComponent(roomTextField, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGroup(dataPanelLayout.createSequentialGroup().addComponent(checkInLabel)
                                        .addGap(VERTICALGAPCOMPONENT).addComponent(checkOutLabel)
                                        .addGap(VERTICALGAPCOMPONENT).addComponent(roomLabel)))
                .addContainerGap(VERTICALCONTAINERGAP, Short.MAX_VALUE)));
        dataPanelLayout.setAutoCreateGaps(true);
    }

    private class JDialogView extends JDialog {

        private static final long serialVersionUID = 1L;
        private static final int POSITIONX = 7;
        private static final int POSITIONY = 3;
        private static final int WIDTH = 4;
        private static final int HEIGHT = 10;

        JDialogView(final String title, final String message) {
            JPanel textPanel = new JPanel();
            JLabel textLabel = new JLabel(message);
            this.setBounds(SCREEN_WIDTH / POSITIONX, SCREEN_HEIGHT / POSITIONY, SCREEN_WIDTH / WIDTH, SCREEN_HEIGHT / HEIGHT);
            this.setTitle(title);
            this.setLayout(new BorderLayout());
            this.getContentPane().add(textPanel); textPanel.add(textLabel);
        }
    }

}

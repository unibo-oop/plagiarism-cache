package view.Reservation;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import java.awt.Font;

import controller.Reservation.ControllerReservation;
import controller.Reservation.ControllerReservationImpl;
import model.Reservation.Reservation;
import javax.swing.UIManager;

public class ShowReservationView extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    private static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    private static final int FONTDIM = 12;
    private final JTextArea textArea = new JTextArea();
    private final JScrollPane scrollPane = new JScrollPane();
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Create the frame.
     */
    public ShowReservationView() {
        ControllerReservation reservationController = new ControllerReservationImpl();
        Set<Reservation> reservations = reservationController.getAllReservation();

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setBounds(100, 100, SCREEN_WIDTH / 3, SCREEN_HEIGHT / 2);
        this.scrollPane.setBorder(UIManager.getBorder("DesktopIcon.border"));
        this.scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.getContentPane().add(scrollPane, BorderLayout.CENTER);
        this.textArea.setFont(new Font("Tahoma", Font.PLAIN, FONTDIM));
        this.setTitle("Prenotazioni attive");
        this.scrollPane.setViewportView(textArea);
        this.textArea.setEditable(false);

        this.textArea.setText("\nCF\t\tNOME\tCOGNOME\tCHECK-IN\tCHECK-OUT\tNUMERO STANZA\n\n");
        for (Reservation reservation : reservations) {
            this.textArea.append(reservation.getClient().getId().toUpperCase() + "\t" + reservation.getClient().getName().toUpperCase() + "\t" 
                    + reservation.getClient().getSurname().toUpperCase() + "\t" + this.dateFormatter.format(reservation.getDateIn()) + "\t" 
                    + this.dateFormatter.format(reservation.getDateOut()) + "\t" + reservation.getRoom().getNumber() + "\n");
        }
    }
}
